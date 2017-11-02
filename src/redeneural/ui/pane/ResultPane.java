package redeneural.ui.pane;

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
import redeneural.data.Letter;

public class ResultPane 
    extends 
        JPanel
{
    public ResultPane() 
    {
        initComponents();
    }
    
    public Image getImageFromResource( Letter letter) 
    {
        try 
        {
            return ImageIO.read(( getClass().getResourceAsStream( "/redeneural/assets/img/" + letter + ".png")));
        }
        
        catch ( IOException e )
        {
           Logger.getGlobal().log( Level.SEVERE, null, e );
        }
    	
        return null;
    }

    public void setBackgroundResult( Letter letter ) 
    {
    	this.image = getImageFromResource(letter);
    
        repaint();
    }

    public void clear() 
    {
    	try 
        {
            image = ImageIO.read( ( getClass().getResourceAsStream( "/redeneural/assets/img/blank.png")));
    	
            repaint();
        
        } 
        
        catch ( IOException e )
        {
            Logger.getGlobal().log( Level.SEVERE, null, e );
        }
    }
    
     protected void paintComponent( Graphics g )
     {
        super.paintComponent( g );
        
        if ( image != null )
        {
            g.drawImage( image, 0, 0, this );
        }
    }
        
    private void initComponents()
    {
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(410, 450));
        
        outputLabel = new JLabel( "OUTPUT:" );
        outputLabel.setFont( new Font("Arial", 20, 30 ) );

        imagePane = new JPanel();
        imagePane.setPreferredSize( new Dimension( 400, 400 ) );
        imagePane.setBackground( Color.WHITE );
        
        add( outputLabel );
        add( imagePane );
    }
    
    private JLabel outputLabel;
    private Image image;
    private JPanel imagePane;
}