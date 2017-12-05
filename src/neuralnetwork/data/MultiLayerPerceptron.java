package neuralnetwork.data;

import java.io.Serializable;
import neuralnetwork.functions.Function;

/**
 * @author artur
 */
public class MultiLayerPerceptron 
    implements 
        Cloneable, Serializable
{
    private static final long serialVersionUID = -7830597081189326543L;
    
    public static double LEARNING_RATIO = 0.6;
    
    private double _learning;
    
    private Layer[] _layers;
    private Function _transferFunction;

    /**
     * MultiLayerPerceptron
     * 
     * @param function
     * 
     * Função de tranferencia de pesos entre camadas
     * 
     * @param learning 
     * 
     * Taxa de aprendizado
     * 
     * @param nNeurons 
     * 
     * Array com numero de neuronios por camada
     * 
     * Exemplo: { 400, 8000, 26 } 
     * 
     * 3Camadas - (1 Oculta)
     * 
     * 400 - Neuronios de Entrada
     * 800 - Neuronios da Camada oculta
     * 26  - Neuronios na saida 
     */
    public MultiLayerPerceptron( Function function, double learning, Integer... nNeurons )
    {
        this._transferFunction = function;
        this._learning = learning;

        this._layers = new Layer[ nNeurons.length ];

        for(int i = 0; i < nNeurons.length; i++)
        {
            _layers[i] = new Layer( nNeurons[i], i != 0 ? nNeurons[ i - 1 ] : 0 );
        }
    }

    /**
     * setTransferFunction
     * 
     * @param transferFunction 
     */
    public void setTransferFunction( Function transferFunction )
    {
        this._transferFunction = transferFunction;
    }

    /**
     * 
     * @param learning 
     */
    public void setLearning( double learning )
    {
        this._learning = learning;
    }

    /**
     * 
     * @param layers 
     */
    public void setLayers( Layer[] layers ) 
    {
        this._layers = layers;
    }

    /**
     * 
     * @return 
     */
    public Layer[] getLayers() 
    {
        return _layers;
    }
    
    /**
     * execute
     * 
     * @param input
     * 
     * Cuidar para ser o mesmo numero definido ao instanciar a camada Multilayer
     * 
     * @return output
     * 
     * Neuronios ativados na ultima camada
     */
    public double[] execute( double[] input )
    {
        /**
         * Variaveis de auxilio
         */
        int i, j, k;
        
        double new_value;
        
        /**
         * Output será defindo atravez do numero de neuronios da ultima camada
         */
        double output[] = new double[ _layers[ _layers.length - 1 ].size ];
		
        /**
         * definie os inputs na primeira camada
         */
        for(i = 0; i < _layers[ 0 ].size; i++ )
        {
            _layers[ 0 ].neurons[ i ].value = input[ i ];
        }
	
        /**
         * Executa as camadas ocultas e camada de saida, aplicando os pesos e a funcao de transferencia para cada neuronio
         *
         */
        for( k = 1; k < _layers.length; k++ )
        {
            for( i = 0; i < _layers[ k ].size; i++ )
            {
                new_value = 0.0;
                
                for( j = 0; j < _layers[ k - 1 ].size; j++ )
                {
                    new_value += _layers[ k ].neurons[ i ].weights[ j ] * _layers[ k - 1 ].neurons[ j ].value;
                }

                new_value += _layers[ k ].neurons[ i ].bias;

                _layers[ k ].neurons[ i ].value = _transferFunction.evaluate( new_value );
            }
        }
        
        /**
         * Repassa o outup gerado na execucao dos neuronios acima, buscando entao os valores da ultima camada
         */
        for( i = 0; i < _layers[ _layers.length - 1].size; i++ )
        {
            output[i] = _layers[ _layers.length - 1 ].neurons[ i ].value;
        }

        return output;
    }

    /**
     * backpropagation
     * 
     * Backpropagation para aprendizagem assistida
     * 
     * @param input 
     * @param output
     * 
     * @return error
     */
    public double backpropagation( double[] input, double[] output )
    {
        /**
         * Executa a propagacao dos inputs para as camadas da rede neural
         */
        double new_output[] = execute( input );
        
        /**
         * Variaveis de auxilio
         */
        double error;
        int i, j, k;

        /**
         * Calcula o erro da saida (output)
         */
        for(i = 0; i < _layers[ _layers.length - 1 ].size; i++ )
        {
            error = output[ i ] - new_output[ i ];
            
            _layers[ _layers.length - 1 ].neurons[ i ].delta = error * _transferFunction.evaluateDerived( new_output[ i ] );
        } 
	
		
        for( k = _layers.length - 2; k >= 0; k-- )
        {
            /**
             * Calcula o erro da camada atual e recalcula o delta do neoronio
             */
            for( i = 0; i < _layers[k].size; i++)
            {
                error = 0d;
            
                for( j = 0; j < _layers[ k + 1 ].size; j++ )
                {
                    error += _layers[ k + 1 ].neurons[ j ].delta * _layers[ k + 1 ].neurons[ j ].weights[ i ];
                }

                _layers[ k ].neurons[ i ].delta = error * _transferFunction.evaluateDerived(_layers[ k ].neurons[ i ].value );				
            }

            /**
             * Atualiza os pesos da proxima camada 
             */
            for( i = 0; i < _layers[ k + 1 ].size; i++ )
            {
                for( j = 0; j < _layers[ k ].size; j++ )
                {
                    _layers[ k + 1 ].neurons[ i ].weights[ j ] += _learning * _layers[ k + 1 ].neurons[ i ].delta * _layers[ k ].neurons[ j ].value;
                }
                
                _layers[ k + 1 ].neurons[ i ].bias += _learning * _layers[ k + 1 ].neurons[ i ].delta;
            }
        }	
		
        /**
         * Calcula o erro final
         */
        error = 0.0;

        for( i = 0; i < output.length; i++ )
        {
            error += Math.abs( new_output[ i ] - output[ i ] );
        }

        error = error / output.length;

        return error;
    }
}
