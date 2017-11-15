package neuralnetwork.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;


public class CustomPanel 
    extends 
        JPanel 
{
    private List<Section> sections;
    private int width;
    private int height;
    private int count;

    public CustomPanel( int w, int h, int count )
    {
        super();
        
        this.width = w;
        this.height = h;
        
        this.count = count;
        
        initComponents();
    }

    @Override
    protected void paintComponent( Graphics g ) 
    {
        super.paintComponent(g);

        generateSections(g);
        
        drawSections(g);

    }

    private void generateSections( Graphics g ) 
    {
        g.setColor( Color.LIGHT_GRAY);

        sections.stream().forEach( s -> 
        {
            g.drawLine( 0, s.getY(), width, s.getY() );

            g.drawLine( s.getX(), 0, s.getX(), height );
        } );
    }

    private void drawSections(Graphics g) 
    {
        g.setColor( Color.BLACK );
        
        sections.stream().filter( s -> ( s.isActive() ) ).forEach( s -> 
        {
            g.fillRect( s.getX(), s.getY(), s.getWidth(), s.getHeight() );
        } );
    }

     public List<Integer> getPixels() 
    {
        List<Integer> pixels = new ArrayList();
    
        sections.stream().forEach( s -> { pixels.add( s.isActive() ? 1 : 0 ); } );

        return pixels;
    }
    
    public void clear() 
    {
        sections.stream().forEach( s -> { s.setActive( false ); } );

        repaint();
    }

    public void drawLetter( List<Integer> pixels )
    {
        for ( int i = 0; i < pixels.size(); i++ )
        {
            if ( pixels.get(i) == 1 )
            {
                sections.get( i ).setActive( true );
            }
            
            else
            {
                sections.get( i ).setActive( false );
            }
        }

        repaint();
    }

    public List<Section> getSections() 
    {
        return sections;
    }
    
    
    private void generateSections() 
    {
        sections = new ArrayList();

        for ( int i = 0; i < count; i++ ) 
        {
            for ( int j = 0; j < count; j++ ) 
            {
                sections.add( new Section( i * (width / count), j * (height / count), width / count, height / count ) );
            }
        }

        repaint();
    }
    
    private void initComponents()
    {
        setPreferredSize( new Dimension( width, height ) );
        
        setBackground( Color.WHITE );

        generateSections();
    }
}
