package neuralnetwork.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class DrawingPanel 
    extends 
        CustomPanel
            implements 
                MouseMotionListener,
                MouseListener 
{
    /**
     * DrawingPanel
     * 
     * @param w
     * @param h
     * @param count 
     */
    public DrawingPanel( int w, int h, int count ) 
    {
        super( w, h, count );

        /**
         * Registra eventos
         */
        addMouseMotionListener( this );
        
        addMouseListener( this );
    }

    @Override
    public void mouseDragged( MouseEvent e ) { paintSections( e ); }
    
    @Override
    public void mouseClicked( MouseEvent e ) { paintSections( e ); }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mousePressed( MouseEvent e ) {}

    @Override
    public void mouseReleased( MouseEvent e ) {}

    @Override
    public void mouseEntered( MouseEvent e ) {}

    @Override
    public void mouseExited( MouseEvent e ) {}
    

    /**
     * paintSections
     * 
     * Desenha itens na tela
     * 
     * @param e MouseEvent
     */
    private void paintSections( MouseEvent e ) 
    {
        /**
         * Percore os ativando ou não dependendo do botão pressionado
         */
        getSections().stream().filter( (s) -> ( isValid( e, s ) ) ).forEach( ( s ) -> 
        {
            s.setActive(  SwingUtilities.isLeftMouseButton( e ) );
        } );

        /**
         * Atualiza Tela pintando os pontos ativos
         */
        repaint();
    }
    
    private boolean isValid( MouseEvent e, Section s )
    {
        return  e.getX() >= s.getX() && 
                e.getX() <= s.getX() + s.getWidth() && 
                e.getY() >= s.getY() && 
                e.getY() <= s.getY() + s.getHeight();
    }
}
