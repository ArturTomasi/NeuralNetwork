package neuralnetwork.ui.pane;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import neuralnetwork.data.Letter;
import neuralnetwork.ui.parts.ActionButton;
import neuralnetwork.ui.parts.ConfigEditor;
import neuralnetwork.ui.parts.EditorCallback;
import neuralnetwork.util.GenericObserver;


public class MenuPane 
    extends 
        HBox
{
    public static enum Events
    {
        ON_RECOGNIZE(),
        ON_CLEAR(),
        ON_TRAIN(),
        ON_LOAD_LETTER(),
        ON_LOAD_NETWORK(),
        ON_CONFIG();
        
        private Letter letter;

        public Letter getLetter() 
        {
            return letter;
        }

        public void setLetter( Letter letter ) 
        {
            this.letter = letter;
        }
    }
    
    private GenericObserver _observer;
    
    public MenuPane( GenericObserver observer ) 
    {
        this._observer = observer;
        
        initComponents();
    }
            
    public void selectLetter( int index )
    {
        trainAsComboBox.getSelectionModel().select( index );
    }
    
    
    public Letter selectLetter()
    {
        return trainAsComboBox.getValue();
    }
    
    
    private void send( Events event )
    {
        event.setLetter( selectLetter() );
        
        _observer.notify( event );
    }
    
    private void initComponents()
    {
        trainAsComboBox.setCursor( Cursor.HAND );
        trainAsComboBox.setMinWidth( 100 );
        trainAsComboBox.setMaxWidth( 150 );

        trainAsComboBox.setStyle( "-fx-background-radius: 10; " +
                                    "-fx-background-color: #ECEFF1; " +
                                    "-fx-border-color: #607D8B;" +
                                    "-fx-text-fill: #455A64;" +
                                    "-fx-border-radius: 10; " +
                                    "-fx-border-width: 2;"      +
                                    "-fx-font-size: 10;"      +
                                    "-fx-font-weight: bolder;"  );
         
        Separator sep1 = new Separator( Orientation.VERTICAL );
        sep1.setStyle( "-fx-background-color: #607D8B; " +
                       "-fx-border-color: #ECEFF1;"  );
         
        Separator sep2 = new Separator( Orientation.VERTICAL );
        sep2.setStyle( "-fx-background-color: #607D8B; " +
                       "-fx-border-color: #ECEFF1;"  );
        
        getChildren().addAll( loadNetworkButton, loadButton, recognizeButton, clearButton, 
                              sep1, 
                              trainAsComboBox, trainNetworkButton, 
                              sep2,
                              configButton );
        
        setMargin( loadButton, new Insets( 10 ) );
        setMargin( loadNetworkButton, new Insets( 10 ) );
        setMargin( recognizeButton, new Insets( 10 ) );
        setMargin( clearButton, new Insets( 10 ) );
        setMargin( trainAsComboBox, new Insets( 10 ) );
        setMargin( trainNetworkButton, new Insets( 10 ) );
        setMargin( configButton, new Insets( 10 ) );
        setMargin( sep2, new Insets( 10 ) );
        setMargin( sep1, new Insets( 10 ) );
    }
    
    
    private ActionButton clearButton = new ActionButton( "Limpar", new ActionButton.ActionHandler() 
    {
        @Override
        public void onEvent( Event t )
        {
            send( Events.ON_CLEAR );
        }
    } );
    
    private ActionButton recognizeButton = new  ActionButton( "Reconhecer", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) 
        {
            send( Events.ON_RECOGNIZE );
        }
    } );
    
    
    private ActionButton loadButton = new  ActionButton( "Carregar Letras", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) 
        {
            Thread thread = new Thread( () -> 
            {
                send( Events.ON_LOAD_LETTER ); 
            } );
            
            thread.setDaemon( true );
            thread.start();
        }
    } );
    
    private ActionButton loadNetworkButton = new  ActionButton( "Carregar Rede Neural", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) 
        {
            Thread thread = new Thread( () -> 
            {
                send( Events.ON_LOAD_NETWORK ); 
            } );
            
            thread.setDaemon( true );
            thread.start();
        }
    } );
    
    private ActionButton trainNetworkButton = new  ActionButton( "Treinar Letra", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) 
        {
            Thread thread = new Thread( () -> 
            {
                send( Events.ON_TRAIN );
            } );
            
            thread.setDaemon( true );
            thread.start();
        }
    } );
    
    private ActionButton configButton = new  ActionButton( "Configurar", new ActionButton.ActionHandler()
    {
        @Override
        public void onEvent( Event t ) 
        {
            new ConfigEditor( new EditorCallback( t ) 
            {
                @Override
                public void handle(Event event) 
                {
                    send( Events.ON_CONFIG );
                }
            } ).show();
        }
    } );
    
    private ComboBox<Letter> trainAsComboBox = new ComboBox( FXCollections.observableArrayList( Letter.values() ) );
}
