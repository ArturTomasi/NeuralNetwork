package neuralnetwork.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ResourceLocator 
{
    private static ResourceLocator instance;
    
    public static ResourceLocator getInstance()
    {
        if ( instance == null )
        {
            instance = new ResourceLocator();
        }
        
        return instance;
    }
    
    private ResourceLocator(){}
    
    
    public Image getImage( String name )
    {
        try
        {
            return ImageIO.read(  getClass().getResourceAsStream( "/neuralnetwork/assets/img/" + name ) );
        }
        
        catch ( IOException e )
        {
           Logger.getGlobal().log( Level.SEVERE, null, e );
        }
        
        return null;
    }
    
    public File getFileText( String name )
    {
        return new File( "resources/" + name );
    }
    
    public String getPathText( String name )
    {
        return "resources/" + name;
    }
}
