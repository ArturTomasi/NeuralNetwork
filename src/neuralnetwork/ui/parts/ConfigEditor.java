package neuralnetwork.ui.parts;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import neuralnetwork.util.ConfigurationManager;

/**
 *
 * @author artur
 */
public class ConfigEditor 
    extends 
        AbstractEditor
{
    public ConfigEditor( EditorCallback callback ) 
    {
        super(callback);
        
        initComponents();
        
        setTitle( "Configurações" );
        
        setHeaderText( "Editor de Configurações" );

        setSource( source );
    } 
    
    
    @Override
    protected void obtainInput() 
    {
        ConfigurationManager.getInstance().putProperty( "network.hidden.layers",   layersField.getInt() );
        ConfigurationManager.getInstance().putProperty( "network.hidden.neurons",  neuronField.getInt() );
        ConfigurationManager.getInstance().putProperty( "network.looping",         loopingField.getInt() );
        ConfigurationManager.getInstance().putProperty( "network.learning",        learningField.getDouble() );
        ConfigurationManager.getInstance().putProperty( "network.error",           errorField.getDouble() );
        ConfigurationManager.getInstance().putProperty( "network.function",        functionField.getValue() );
    }
    
    
    @Override
    protected void resize() 
    {
        layersField.setMinWidth( getWidth() - 250  );
        
        getDialogPane().requestLayout();
    }

    @Override
    protected void setSource( Object source ) 
    {
        layersField.setInteger( ConfigurationManager.getInstance().getInt( "network.hidden.layers" ) );
        neuronField.setInteger( ConfigurationManager.getInstance().getInt( "network.hidden.neurons" ) );
        loopingField.setInteger( ConfigurationManager.getInstance().getInt( "network.looping" ) );
        learningField.setDouble( ConfigurationManager.getInstance().getDouble( "network.learning" ) );
        errorField.setDouble( ConfigurationManager.getInstance().getDouble( "network.error" ) );
        functionField.setValue( ConfigurationManager.getInstance().getProperty( "network.function" ) );
    }
    
    private void initComponents()
    {
        gridPane.setVgap( 20 );
        gridPane.setHgap( 20 );
        gridPane.setStyle( "-fx-padding: 30;" );
    
        gridPane.add( lbLayers,          0, 0, 1, 1 );
        gridPane.add( layersField,       1, 0, 3, 1 );
    
        gridPane.add( lbNeuron,          0, 1, 1, 1 );
        gridPane.add( neuronField,       1, 1, 3, 1 );
    
        gridPane.add( lbLearning,        0, 2, 1, 1 );
        gridPane.add( learningField,     1, 2, 3, 1 );
        
        gridPane.add( lbLooping,         0, 3, 1, 1 );
        gridPane.add( loopingField,      1, 3, 3, 1 );
        
        gridPane.add( lbError,           0, 4, 1, 1 );
        gridPane.add( errorField,        1, 4, 3, 1 );
        
        gridPane.add( lbFunction,        0, 5, 1, 1 );
        gridPane.add( functionField,     1, 5, 3, 1 );
        
        getDialogPane().setContent( gridPane );
    }
    
    private GridPane gridPane               = new GridPane();
    
    private Label lbLayers                  = new Label( "Camadas Ocultas");
    private NumberTextField layersField     = new NumberTextField();
    
    private Label lbNeuron                  = new Label( "Neuronios Ocultos");
    private NumberTextField neuronField     = new NumberTextField();
    
    private Label lbLearning                = new Label( "Taxa Aprendizado");
    private NumberTextField learningField   = new NumberTextField();
    
    private Label lbLooping                 = new Label( "Quantidade de Interações" );
    private NumberTextField loopingField    = new NumberTextField();
    
    private Label lbError                   = new Label( "Erro Máximo" );
    private NumberTextField errorField      = new NumberTextField();
    
    private Label lbFunction                = new Label( "Função de Transferência" );
    private ComboBox functionField          = new ComboBox( FXCollections.observableArrayList( "Sigmoid", "Heavyside", "Hyperbolic" ) );
}
