package neuralnetwork.ui.pane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import neuralnetwork.data.Letter;


public abstract class MenuPane 
    extends 
        JPanel
{
    public MenuPane() 
    {
        initComponents();
        
        initListeners();
    }
    
    public abstract int transformFunction();
  
    public abstract void clear();
    
    public abstract void enabled();
    
    public abstract void disabled();
    
    public abstract void doTrain( Letter letter, int number );

    public abstract void doTrainAll();
    
            
    private void initListeners() 
    {
    	trainAllLettersButton.addActionListener( e ->
        {
            new Thread( () -> 
            {
                disabled();
                trainAllLettersButton.setText("Treinando letras...");
                trainAllLettersButton.setForeground(Color.RED);
                
                doTrainAll();

                enabled();

                trainAllLettersButton.setForeground(Color.BLACK);
                trainAllLettersButton.setText( "Carregar Treinamento" );
            } ).start();
        } );

        trainNetworkButton.addActionListener( e ->
        {
            new Thread( () -> 
            {
                disabled();
                
                Letter letter = (Letter) trainAsComboBox.getSelectedItem();
                
                trainNetworkButton.setText( "Treinando letra: " + letter  );
                
                trainNetworkButton.setForeground(Color.RED);
                
                int number = 0;
                
                try 
                {
                    number = Integer.parseInt(trainingAmountTextField.getText());
                }
                catch (Exception x) {}

    
                doTrain( letter, number );
                
                trainNetworkButton.setText( "Treinar Letra:" );
                trainNetworkButton.setForeground(Color.BLACK);
                
                enabled();
                
            } ).start();
        } );
        
        clearButton.addActionListener( e -> clear() );

        recognizeResultButton.addActionListener( s -> trainAsComboBox.setSelectedIndex( transformFunction() ) );
    }
    
    private void initComponents()
    {
        setLayout(new GridBagLayout());
        
        setPreferredSize(new Dimension(200, 450));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
        gbc.anchor = GridBagConstraints.CENTER;

        add(new JSeparator(SwingConstants.VERTICAL), gbc);
        add(Box.createVerticalStrut(20), gbc);
        
        trainAllLettersButton = new JButton( "Carregar Treinamento" );
        add(trainAllLettersButton, gbc);
        
        add(Box.createVerticalStrut(20), gbc);
                
        add(new JLabel( "Treinar como:", SwingConstants.CENTER), gbc);

        trainAsComboBox = new JComboBox( Letter.values() );
        trainAsComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        trainAsComboBox.setMaximumSize(new Dimension((int) trainAsComboBox.getPreferredSize().getWidth(), 30));
        add(trainAsComboBox, gbc);

        add(Box.createVerticalStrut(30));

        trainNetworkButton = new JButton( "Treinar Letra: " );

        trainingAmountTextField = new JFormattedTextField( "5000" );
        trainingAmountTextField.setMaximumSize(new Dimension(100, 30));
        trainingAmountTextField.setPreferredSize(new Dimension(100, 30));
        trainingAmountTextField.setHorizontalAlignment(JTextField.CENTER);
        trainingAmountTextField.setFont(new Font("Serif", 20, 20));
        
        add(trainNetworkButton, gbc);
        add(trainingAmountTextField, gbc);

        add(Box.createVerticalStrut(70));

        recognizeResultButton = new JButton( "Reconhecer" );
        add(recognizeResultButton, gbc);

        add(Box.createVerticalStrut(30));

        clearButton = new JButton("Limpar");
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(clearButton, gbc);
    }
    
    
    private JButton clearButton;
    private JButton recognizeResultButton;
    private JButton trainNetworkButton;
    private JButton trainAllLettersButton;
    
    private JTextField trainingAmountTextField;
    private JComboBox<String> trainAsComboBox;
}
