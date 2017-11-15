package neuralnetwork.functions;

/**
 *
 * @author artur
 */
public interface Function 
{
    public double evaluate( double arg );
    
    public double evaluateDerived( double arg );
}
