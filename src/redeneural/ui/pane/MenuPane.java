package redeneural.ui.pane;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import redeneural.data.Letter;


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
    	
//    	testLetterButton.addActionListener( new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						disabled();
//						int correctRecognitions = 0;
//	    				int totalTrys = 0;
//	    				
//	    				boolean wrong = false;
//	    	    			
//    	    			testLetterButton.setText("Testing Letter [".concat(testLetterComboBox.getSelectedItem().toString()).concat("]"));
//    	    			testLetterButton.setForeground(Color.BLUE);
//    	    			
//    	    			int size = networkTrainer.getNumbOfInputsLetter(testLetterComboBox.getSelectedItem().toString());
//    	    			for(int j = 0; j < size; j++) {
//    	    				
//    	    				inputPane.drawLetter(networkTrainer.getInputFromLetter(testLetterComboBox.getSelectedItem().toString(), j));
//    	    				
//	    	                try {
//	    	                	Thread.sleep(300);
//	    					} catch (Exception e2) {
//	    					}
//	    	                
//	    	                int index = transformFunction();
//	    	                
//	    	                try {
//	    	                	Thread.sleep(300);
//	    					} catch (Exception e2) {
//	    					}
//
//	    	                if(index == testLetterComboBox.getSelectedIndex()) {
//	    	                	//resultPane.setCustomizedColor(Color.GREEN);
//	    	                	resultPane.setBackground(Color.GREEN);
//	    	                	correctRecognitions++;
//	    	                	wrong = false;
//	    	                } else {
//	    	                	//resultPane.setCustomizedColor(Color.RED);
//	    	                	resultPane.setBackground(Color.RED);
//	    	                	wrong = true;
//	    	                }
//	    	                //resultPane.setBackgroundResult(arrayABC[i]);
//	    	                totalTrys++;
//	    	                try {
//	    	                	Thread.sleep(500);
//	    					} catch (Exception e2) {
//	    					}
//	    	                if(wrong) {
//	    	                	testLetterButton.setText("Training Wrong Letter");
//	    	                	testLetterButton.setForeground(Color.RED);
//	    	                	networkTrainer.train(1000, testLetterComboBox.getSelectedItem().toString());
//	    	                	testLetterButton.setForeground(Color.BLACK);
//	    	                	testLetterButton.setText("Test Letter:");
//	    	                }
//	    	                //resultPane.setCustomizedColor(Color.BLACK);
//	    	                resultPane.setBackground(Color.WHITE);
//	    	                resultPane.clear();
//    	    			}
//	    	        	  	    		
//	    	    		//JOptionPane.showMessageDialog(null, "The program recognized ".concat(correctRecognitions + " of 78 letters"));
//	    	    		inputPane.clear();
//	    	    		resultPane.clear();
//	    				//}
//	    	    		enabled();
//	    	    		testLetterButton.setText("Test Letter:");
//	    	    		testLetterButton.setForeground(Color.BLACK);
//	    				JOptionPane.showMessageDialog(null, "The program recognized ".concat(correctRecognitions + " of " + totalTrys + " letters"));	    	    		
//	    			
//					}
//				}).start();
//				
//			}
//		});
////    	
//    	testAllLettersButton.addActionListener( e ->
//        {
//            new Thread( () ->
//            {
//                disabled();
//                
//                int correctRecognitions = 0;
//                int totalTrys = 0;
//	    	    		
//                boolean wrong = false;
//                
//                for( int i = 0; i < 26; i++ )
//                {
//                    testAllLettersButton.setText("Testing Letter [".concat(Letter.values()[i].toString()).concat("]"));
//                    testAllLettersButton.setForeground(Color.BLUE);
//	    	    			
//                    int size = networkTrainer.getNumbOfInputsLetter(Letter.values()[i].toString());
//                    
//                    for(int j = 0; j < size; j++) 
//                    {
//                        inputPane.drawLetter(networkTrainer.getInputFromLetter(Letter.values()[i].toString(), j));
//                        int index = transformFunction();
//		    	
//                        if(index == i) {
//                                //resultPane.setCustomizedColor(Color.GREEN);
//                                resultPane.setBackground(Color.GREEN);
//                                correctRecognitions++;
//                                wrong = false;
//                        } else {
//                                //resultPane.setCustomizedColor(Color.RED);
//                                resultPane.setBackground(Color.RED);
//                                wrong = true;
//                        }
//		    	                //resultPane.setBackgroundResult(arrayABC[i]);
//                        totalTrys++;
//
//                        if(wrong) {
//                                testAllLettersButton.setText("Training Wrong Letter");
//                                testAllLettersButton.setForeground(Color.RED);
//                                doTrain(1000, Letter.values()[i] );
//                                testAllLettersButton.setForeground(Color.BLACK);
//                                testAllLettersButton.setText("Test All Letters");
//                        }
//                        //resultPane.setCustomizedColor(Color.BLACK);
//                        resultPane.setBackground(Color.WHITE);
//                    }
//                }
//               
//                clear();
//                enabled();
//                testAllLettersButton.setText("Test All Letters");
//                testAllLettersButton.setForeground(Color.BLACK);
//                JOptionPane.showMessageDialog(null, "The program recognized ".concat(correctRecognitions + " of " + totalTrys + " letters"));
//	    	    		
//            } ).start();
//        } );
    	
    	trainAllLettersButton.addActionListener( e ->
        {
            new Thread( () -> 
            {
                disabled();
                trainAllLettersButton.setText("Training All...");
                trainAllLettersButton.setForeground(Color.RED);
                
                doTrainAll();

                enabled();

                trainAllLettersButton.setForeground(Color.BLACK);
                trainAllLettersButton.setText("Train All Letters");
            } ).start();
        } );

        trainNetworkButton.addActionListener( e ->
        {
            new Thread( () -> 
            {
                disabled();
                
                trainNetworkButton.setText("Training...");
                
                trainNetworkButton.setForeground(Color.RED);
                
                Letter letter = (Letter) trainAsComboBox.getSelectedItem();
                
                int number = 0;
                
                try 
                {
                    number = Integer.parseInt(trainingAmountTextField.getText());
                }
                catch (Exception x) {}

    
                doTrain( letter, number );
                
                trainNetworkButton.setText("Train X times:");
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
        
        testLetterButton = new JButton("Test Letter:");
        add(testLetterButton, gbc);
        
        testLetterComboBox = new JComboBox( Letter.values() );
        testLetterComboBox.setAlignmentX(CENTER_ALIGNMENT);
        add(testLetterComboBox, gbc);
        
        add(Box.createVerticalStrut(30), gbc);
        
        testAllLettersButton = new JButton("Test All Letters");
        add(testAllLettersButton, gbc);

//        add(Box.createVerticalStrut(10), gbc);
//        
//        delayTest = new JLabel("Delay (ms)");
//        centerPanel.add(delayTest, gbc);
        
//        testAllComboBox = new JComboBox<>(new String[]{"0", "10", "50", "100"});
//        testAllComboBox.setAlignmentX(CENTER_ALIGNMENT);
//        centerPanel.add(testAllComboBox, gbc);
        
        add(new JSeparator(SwingConstants.VERTICAL), gbc);
        add(Box.createVerticalStrut(20), gbc);
        
        trainAllLettersButton = new JButton("Train All Letters");
        add(trainAllLettersButton, gbc);
        
        add(Box.createVerticalStrut(20), gbc);
                
        add(new JLabel("Train as:", SwingConstants.CENTER), gbc);

        trainAsComboBox = new JComboBox( Letter.values() );
        trainAsComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        trainAsComboBox.setMaximumSize(new Dimension((int) trainAsComboBox.getPreferredSize().getWidth(), 30));
        add(trainAsComboBox, gbc);

        add(Box.createVerticalStrut(30));

        trainNetworkButton = new JButton("Train X times:");

        trainingAmountTextField = new JFormattedTextField("1000");
        trainingAmountTextField.setMaximumSize(new Dimension(100, 30));
        trainingAmountTextField.setPreferredSize(new Dimension(100, 30));
        trainingAmountTextField.setHorizontalAlignment(JTextField.CENTER);
        trainingAmountTextField.setFont(new Font("Serif", 20, 20));
        
        add(trainNetworkButton, gbc);
        add(trainingAmountTextField, gbc);

        add(Box.createVerticalStrut(70));

        recognizeResultButton = new JButton(">>>");
        add(recognizeResultButton, gbc);

        add(Box.createVerticalStrut(30));

        clearButton = new JButton("Clear");
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(clearButton, gbc);
    }
    
    
    private JButton clearButton;
    private JButton recognizeResultButton;
    private JButton trainNetworkButton;
    private JButton trainAllLettersButton;
    private JButton testAllLettersButton;
    private JButton testLetterButton;
    
    private JTextField trainingAmountTextField;
    private JComboBox<String> trainAsComboBox;
    private JComboBox<String> testLetterComboBox;
}
