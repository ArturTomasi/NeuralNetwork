package neuralnetwork.data;

import java.io.Serializable;

/**
 * @author artur
 */
public class Layer 
    implements 
        Serializable
{
    protected Neuron[] neurons;
    protected int size;

    public Layer( int size, int previousSize ) 
    {
        this.size = size;
        
        neurons = new Neuron[size];
        
        for ( int i = 0; i < size; i ++ )
        {
            neurons[i] = new Neuron( previousSize );
        }
    }
}
