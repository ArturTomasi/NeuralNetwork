package neuralnetwork.util;

import java.io.File;

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
    
    
    public String getImage( String name )
    {
        
        return getClass().getResource( "/neuralnetwork/assets/img/" + name ).toString() + File.separator;
                
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
