package neuralnetwork.functions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artur
 */
public class FunctionPrototypeFactory 
{
    private static Sigmoid sigmoid       = new Sigmoid();
    private static Heavyside heavyside   = new Heavyside();
    private static Hyperbolic hyperbolic = new Hyperbolic();
    
    public static Function makeSigmoid()
    {
        try
        {
            return (Function) sigmoid.clone();
        }
        
        catch ( Exception e )
        {
            Logger.getGlobal().log( Level.SEVERE, e.getMessage() );
        }
        
        return null;
    }
    
    public static Function makeFunction( String function )
    {
        switch ( function )
        {
            case "Sigmoid":
                return makeSigmoid();
            case "Hyperbolic":
                return makeHyperbolic();
            case "Heavyside":
                return makeHeavyside();
            default:
                return makeSigmoid();
        }
    }
    
    public static Function makeHeavyside()
    {
        try
        {
            return (Function) heavyside.clone();
        }
        
        catch ( Exception e )
        {
            Logger.getGlobal().log( Level.SEVERE, e.getMessage() );
        }
        
        return null;
    }
    
    public static Function makeHyperbolic()
    {
        try
        {
            return (Function) hyperbolic.clone();
        }
        
        catch ( Exception e )
        {
            Logger.getGlobal().log( Level.SEVERE, e.getMessage() );
        }
        
        return null;
    }
}
