package neuralnetwork.data;

import java.util.List;
import java.util.Map;
import neuralnetwork.functions.FunctionPrototypeFactory;
import neuralnetwork.functions.Sigmoid;
import neuralnetwork.io.FileUtilities;

/**
 * 
 * @author artur
 */
public class Network 
{
    private MultiLayerPerceptron _network;
    
    private int sizePanel;
    private int _looping   = 500;
    private double  _error = 0.03;
    
    /**
     * Network
     * 
     * @param sizePanel 
     */
    public Network( int sizePanel )
    {
        this.sizePanel = sizePanel;

        /**
         * Inicializa as Rede
         * 
         * Passando a função de transferencia e os neuronios das camadas
         * 
         * 400 de entrada
         * 20  na camada oculta 
         * 26  na saida (letras)
         */
        _network = new MultiLayerPerceptron( FunctionPrototypeFactory.makeSigmoid(), MultiLayerPerceptron.LEARNING_RATIO, sizePanel*sizePanel, sizePanel, Letter.values().length );
    }
    
    /**
     * train
     * 
     * Realiza o treinamento da rede com inputs
     */
    public void train()
    {
        /**
         * Carrega inputs com letras pre inseridas
         */
        Map<Letter, List<double[]>> letterMapping = FileUtilities.loadInputs();
        
        /**
         * Percore cada uma das letras 
         */
        for( int i = 0; i < _looping; i++ )
        {
            loop : for ( Letter letter : Letter.values() )
            {
                List<double[]> inputs = letterMapping.get( letter );

                double error = 1d;
                
                /**
                 * Realiza o treinamento assistido por backpropagations
                 */
                for ( double[] bytes : inputs )
                {
                    error = _network.backpropagation( bytes, letter.getOutputs() );
                    
                    /**
                     * Controla taxa de error aceitavel
                     */
                    if ( error < _error ) continue loop;
                    
                    System.out.println( letter + " foi treina a uma taxa de erro: " + error );
                }
            }
        }
    }
    
    public void train( Letter letter, double[] inputs )
    {
        /**
         * Realiza o treinamento assistido por backpropagations
         */
        for( int i = 0; i < _looping; i++ )
        {
            double error =_network.backpropagation( inputs, letter.getOutputs() );
            
            /**
             * Controla taxa de error aceitavel
             */
            if ( error < _error )
            {
                break;
            }

            System.out.println( letter + " foi treina a uma taxa de erro: " + error );
        }
    }
    
    /**
     * configurate
     * 
     * @param config 
     * 
     * Recarega Rede com novos parametros configurados
     */
    public void configurate( Configuration config )
    {
        _looping = config.getInt( "network.looping" );
        _error   = config.getDouble( "network.error" );
        
        int _layers      = config.getInt( "network.hidden.layers" );
        int _neurons     = config.getInt( "network.hidden.neurons" );
        double _learning = config.getDouble( "network.learning" );
        String _function = String.valueOf( config.getProperty( "network.function" ) );
        
        Integer nLayers [] = new Integer[ _layers + 2 ];
        
        /**
         * Hidden
         */
        for ( int i = 1 ; i < nLayers.length -1; i++ )
        {
            nLayers[ i ] = _neurons;
        }
        
        /**
         * Input
         */
        nLayers[ 0 ] = sizePanel*sizePanel;
        
        /**
         * Output
         */
        nLayers[ nLayers.length -1 ] = Letter.values().length;
        
        _network = new MultiLayerPerceptron( FunctionPrototypeFactory.makeFunction( _function ), _learning, nLayers );
    }
            
    /**
     * recognize
     * 
     * @param inputs
     *
     * Pixels gerados no painel de desenho, sendo 1 pintado e 0 em branco
     * 
     * @return percentual de ativacao dos neuronios
     */
    public double[] recognize( double[] inputs )
    {
        return _network.execute( inputs );
    }
    
    /**
     * converter
     * 
     * @param inputs
     *
     * Converte os pixel pra tipagem da rede
     * 
     * @return dados convertidos
     */
    public double[] converter( List<Integer> inputs )
    {
        double [] parse = new double[ inputs.size() ];
        
        for ( int i = 0; i < inputs.size(); i++ )
        {
            parse[i] = inputs.get( i );
        }

        return parse;
    }
    
}
