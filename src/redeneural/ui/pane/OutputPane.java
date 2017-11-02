package redeneural.ui.pane;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OutputPane 
    extends 
        JPanel
{
    public OutputPane() 
    {
        initComponents();
    }
    
    public void setOutputs( List<Double> outputs ) 
    {
        StringBuilder sb = new StringBuilder();
        
        for ( int i = 0; i < outputs.size(); i++ ) 
        {
            int letterValue = i + 65;
            
            sb.append((char) letterValue);
            double value = outputs.get(i);
            //if (value < 0.01) { value = 0; }
            if (value > 0.99) { value = 1; }

            value *= 1000;
            int x = (int) (value);
            value = x / 1000.0;

            sb.append( "\t " ).append( value).append("\n" );
        }
        
        outputTextArea.setText( sb.toString() );
    }
    
    private void initComponents()
    {
        setPreferredSize(new Dimension( 200, 430 ) );

        outputTextArea = new JTextArea();
        outputTextArea.setPreferredSize(new Dimension(200, 430));
        
        add(outputTextArea);
    }
    
    private JTextArea outputTextArea;
}
