package neuralnetwork.data;

/**
 *
 * @author artur
 */
public interface Configuration 
{
    public void putProperty( String property, Object value );
    
    public int getInt( String property );
    
    public int getInt( String property, int def );
    
    public double getDouble( String property );
    
    public double getDouble( String property, double def );
    
    public Object getProperty( String property );
    
    public Object getProperty( String property, Object def );
}
