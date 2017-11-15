package neuralnetwork.ui;

import java.util.List;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import neuralnetwork.data.Letter;
import neuralnetwork.data.Network;
import neuralnetwork.io.FileUtilities;
import neuralnetwork.ui.pane.InputPane;
import neuralnetwork.ui.pane.MenuPane;
import neuralnetwork.ui.pane.OutputPane;
import neuralnetwork.ui.pane.ResultPane;
import neuralnetwork.util.ConfigurationManager;
import neuralnetwork.util.GenericObserver;

public class ApplicationView 
    extends 
        Application 
{
    private Network network;

    public ApplicationView() 
    {
        network = new Network( InputPane.PANEL_SIZE );

        initComponents();
    }
    
    private void clear() 
    {
        inputPane.clear();
        resultPane.clear();
        outputPane.clear();
    }
    
    private void disable()
    {
        inputPane.setDisable( true );
        resultPane.setDisable( true );
        outputPane.setDisable( true );
        pane.setOpacity( 0.3 );
    }
    
    private void enabled()
    {
        inputPane.setDisable( false );
        resultPane.setDisable( false );
        outputPane.setDisable( false );
        pane.setOpacity( 1.0 );   
    }
    
    private int transformFunction() 
    {
        double inputs  [] = network.converter( inputPane.getPixels() );
        double outputs [] = network.recognize( inputs );

        int index = 0;

        for ( int i = 0; i < outputs.length; i++ )
        {
            if ( outputs[i] > outputs[ index ] ) 
            {
                index = i;
            }
        }

        outputPane.setOutputs( outputs );

        if( index <= 25 ) 
        {
            resultPane.setBackgroundResult( Letter.values()[index] );
        } 

        else 
        {
            resultPane.clear();
        }

        menuPane.selectLetter( index );
        
        return index;
    }
    
    private void doTrain( Letter letter )
    {
        disable();
        
        List<Integer> pixels = inputPane.getPixels();
        
        FileUtilities.saveInput( pixels, letter);

        network.train( letter, network.converter( inputPane.getPixels() ) );				

        resultPane.setBackgroundResult( Letter.values()[ transformFunction() ]);
        
        enabled();
    }
    
     private void train()
     {
        disable();
        
        network.train();
        
        enabled();
     }
     
     @Override
    public void start( Stage stage ) throws Exception 
    {
        initComponents();
        
        stage.setResizable( false );
        stage.setTitle(  "RNA para reconhecimento de caracteres" );
        stage.setScene( scene );
        stage.show();
    }
    
    private void initComponents()
    {
        pane.setTop( menuPane );
        pane.setLeft( inputPane );
        pane.setCenter( resultPane );
        pane.setRight( outputPane );

        pane.setStyle( "-fx-background-color: #ECEFF1;" );
    }
    
    private BorderPane pane = new BorderPane();
    private Scene scene = new Scene( pane );
    
    private InputPane inputPane   = new InputPane();
    private OutputPane outputPane = new OutputPane();
    private ResultPane resultPane = new ResultPane();
    
    private MenuPane menuPane     = new MenuPane( new GenericObserver<MenuPane.Events>() 
    {
        @Override
        public void notify( MenuPane.Events source) 
        {
            switch ( source )
            {
                case ON_CLEAR:
                    clear();
                break;
                case ON_RECOGNIZE:
                    transformFunction();
                break;
                case ON_LOAD:
                    train();
                break;
                case ON_TRAIN:
                    doTrain( source.getLetter() );
                break;
                case ON_CONFIG:
                    network.configurate( ConfigurationManager.getInstance() );
                break;
            }
        }
    } );
}
