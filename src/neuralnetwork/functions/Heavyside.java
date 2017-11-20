package neuralnetwork.functions;

/**
 * 
 * @author artur
 */
public class Heavyside 
    implements 
        Function 
{
    @Override
    public double evaluate( double arg )
    {
        if ( arg > 0d )
        {
            return 1d;
        } 
        
        return 0d;
    }

    @Override
    public double evaluateDerived( double arg )
    {
        return 1d;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
