package neuralnetwork.functions;

/**
 * 
 * @author artur
 */
public class Sigmoid 
   implements 
        Function
{
    @Override
    public double evaluate( double arg )
    {
        return ( 1 / ( 1 + Math.exp( -arg ) ) ); 
    }

    @Override
    public double evaluateDerived( double arg )
    {
        return ( arg - Math.pow( arg, 2 ) );
    }

    @Override
    protected Object clone() throws CloneNotSupportedException 
    {
        return super.clone();
    }
}
