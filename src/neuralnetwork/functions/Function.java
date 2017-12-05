package neuralnetwork.functions;

import java.io.Serializable;

/**
 *
 * @author artur
 */
public interface Function 
    extends 
        Cloneable, Serializable
{
    public double evaluate( double arg );
    
    public double evaluateDerived( double arg );
}
