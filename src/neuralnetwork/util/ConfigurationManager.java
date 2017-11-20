package neuralnetwork.util;

import java.util.HashMap;
import java.util.Map;
import neuralnetwork.data.Configuration;

/**
 *
 * @author artur
 */
public class ConfigurationManager 
    implements 
        Configuration
{
    private static ConfigurationManager instance;
    
    public static ConfigurationManager getInstance()
    {
        if ( instance == null )
        {
            instance = new ConfigurationManager();
        }
        
        return instance;
    }
    
    private Map<String, Object> properties = new HashMap();
    
    public ConfigurationManager()
    {
        properties.put( "network.hidden.layers",   1 );
        properties.put( "network.hidden.neurons",  200 );
        properties.put( "network.learning",        0.6 );
        properties.put( "network.looping",         500 );
        properties.put( "network.error",           0.03 );
        properties.put( "network.function",        "Sigmoid" );
    }

    @Override
    public void putProperty( String property, Object value )
    {
        properties.put( property, value );
    }
    
    @Override
    public int getInt( String property )
    {
        return getInt( property, 0 );
    }
    
    @Override
    public int getInt( String property, int def )
    {
        return Integer.valueOf( String.valueOf( properties.getOrDefault( property, def ) ) );
    }
    
    @Override
    public double getDouble( String property )
    {
        return getDouble( property, 0d );
    }
    
    @Override
    public double getDouble( String property, double def )
    {
        return Double.valueOf( String.valueOf( properties.getOrDefault( property, def ) ) );
    }
    
    @Override
    public Object getProperty( String property )
    {
        return getProperty( property, "" );
    }
    
    @Override
    public Object getProperty( String property, Object def )
    {
        return properties.getOrDefault( property, def );
    }
    
}
