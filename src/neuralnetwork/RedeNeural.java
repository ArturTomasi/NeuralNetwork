package neuralnetwork;

import java.awt.Dimension;
import neuralnetwork.ui.ApplicationView;

public class RedeNeural 
{
    public static void main(String[] args) 
    {
        ApplicationView application = new ApplicationView();
        
        application.setVisible( true );
        application.setSize( new Dimension( 1260, 500 ) );
    }
}
