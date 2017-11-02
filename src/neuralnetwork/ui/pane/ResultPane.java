package neuralnetwork.ui.pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import neuralnetwork.data.Letter;
import neuralnetwork.util.ResourceLocator;

public class ResultPane 
    extends 
        JPanel
{
    public ResultPane() 
    {
        initComponents();
    }

    public void setBackgroundResult( Letter letter ) 
    {
    	this.image = ResourceLocator.getInstance().getImage( letter + ".png" );
    
        imagePane.repaint();
    }

    public void clear() 
    {
        image = this.image = ResourceLocator.getInstance().getImage( "blank.png" );

        imagePane.repaint();
    }
        
    private void initComponents()
    {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(410, 450));
        
        outputLabel = new JLabel( "Resultado:" );
        outputLabel.setFont( new Font("Arial", 20, 30 ) );

        imagePane = new JPanel()
        {
            @Override
            protected void paintComponent( Graphics g ) 
            {
                super.paintComponent(g); 
                
                if ( image != null )
                {
                    g.drawImage( image, 0, 0, this );
                }
            }
        };
        
        imagePane.setPreferredSize( new Dimension( 400, 400 ) );
        imagePane.setBackground( Color.WHITE );
        
        add( outputLabel );
        add( imagePane );
    }
    
    private JLabel outputLabel;
    private Image image;
    private JPanel imagePane;
}
