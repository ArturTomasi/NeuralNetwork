package neuralnetwork.functions;

public class Sigmoid 
   implements 
        Function
{
    private static Sigmoid instance;
    
    public static Sigmoid getInstance()
    {
        if ( instance == null )
        {
            instance = new Sigmoid();
        }
        
        return instance;
    }
    
    private Sigmoid(){}
    
    @Override
    public double evaluate( Object... args )
    {
        if ( args != null && args.length == 1 && ( args[0] instanceof Double ) )
        {
            Double val = Double.class.cast( args[0] );
            
            return ( 1 / ( 1 + Math.exp( -val ) ) ); 
        }
        
        return 0.0;
    }
    
}
