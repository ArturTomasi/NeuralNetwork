package neuralnetwork.ui.pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import neuralnetwork.ui.DrawingPanel;

public class InputPane 
    extends 
        JPanel
{
    public InputPane() 
    {
        initComponents();
    }
    
    public List<Integer> getPixels()
    {
        return drawingPanel.getPixels();
    }
    
    public void drawLetter( List<Integer> pixels )
    {
        drawingPanel.drawLetter( pixels );
    }

    public void clear()
    {
        drawingPanel.clear();
    }
    
    private void initComponents()
    {
        setBackground( Color.LIGHT_GRAY );
        setPreferredSize( new Dimension( 410, 450 ) );
        
        inputLabel = new JLabel( "Entrada:" );
        inputLabel.setFont( new Font( "Arial", 20, 30 ) );
        
        drawingPanel = new DrawingPanel(400, 400, 20 );

        add( inputLabel );
        add( drawingPanel );
    }
    
    private DrawingPanel drawingPanel;
    private JLabel inputLabel;
}
