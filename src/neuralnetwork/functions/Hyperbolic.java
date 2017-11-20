package neuralnetwork.functions;

/**
 * 
 * @author artur
 */
public class Hyperbolic 
    implements 
        Function 
{
    @Override
    public double evaluate( double arg )
    {
        return Math.tanh( arg );
    }

    @Override
    public double evaluateDerived( double arg )
    {
        return 1 - Math.pow( arg, 2);
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException 
    {
        return super.clone();
    }
}
