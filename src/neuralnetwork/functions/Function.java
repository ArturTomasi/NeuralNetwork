package neuralnetwork.functions;

/**
 *
 * @author artur
 */
public interface Function 
    extends 
        Cloneable
{
    public double evaluate( double arg );
    
    public double evaluateDerived( double arg );
}
