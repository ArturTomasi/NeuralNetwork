package neuralnetwork.functions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artur
 */
public class FunctionPrototypeFactory 
{
    private Sigmoid sigmoid;
    private Heavyside heavyside;
    private Hyperbolic hyperbolic;
    
    public FunctionPrototypeFactory()
    {
        heavyside  = new Heavyside();
        hyperbolic = new Hyperbolic();
        sigmoid    = new Sigmoid();
    }
    
    public Function makeFunction( String function )
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
    
    public Function makeSigmoid()
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
    
    public Function makeHeavyside()
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
    
    public Function makeHyperbolic()
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
