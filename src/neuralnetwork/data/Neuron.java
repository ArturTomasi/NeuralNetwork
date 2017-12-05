package neuralnetwork.data;

import java.io.Serializable;

public class Neuron 
    implements 
        Serializable
{
    protected double value, delta, bias;
    protected int previousLayerSize;
    protected double weights[];
	
    public Neuron( int previousLayerSize )
    {
        this.previousLayerSize = previousLayerSize;
        
        bias  = Math.random() / 10000000000000.0;
        delta = Math.random() / 10000000000000.0;
        value = Math.random() / 10000000000000.0;
        
        weights = new double[ previousLayerSize ];
        
        for ( int i = 0; i < previousLayerSize; i++ ) 
        {
            weights[ i ] =  Math.random() / 10000000000000.0;
        }
    }
}
