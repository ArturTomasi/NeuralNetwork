package neuralnetwork.ui.parts;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Callback;

/**
 * @author artur
 * @param <T>
 */
public abstract class AbstractEditor<T>
    extends 
        Dialog<T>
{
    public EditorCallback callback;
    public T source;
    private ButtonType btSave   = new ButtonType( "Salvar",   ButtonData.OK_DONE );
    private ButtonType btCancel = new ButtonType( "Cancelar", ButtonData.CANCEL_CLOSE );
    private ButtonType selectedButton;
    
    private AbstractEditor(){}
    
    public AbstractEditor( EditorCallback<T> callback ) 
    {
        this.callback = callback;
        source = callback.source;
        
        initComponents();
    }
    
    private boolean onSave()
    {
        obtainInput();

        callback.handle( new Event( source, this, EventType.ROOT ) );
        
        return true;
    }
    
    protected void onCancel(){} //Sobreescrever somente em alguns casos

    private void initComponents()
    {
        setTitle( "Editor" );
        setHeaderText( "Editor de Items" );
        setResizable( false );
        
        getDialogPane().getButtonTypes().addAll( btCancel , btSave );
        
        getDialogPane().setPrefSize( 800, 550 );
    
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        
        widthProperty().addListener( new ChangeListener<Number>() 
        {
            @Override 
            public void changed( ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth )
            {
               resize();
            }
        } );
        
        heightProperty().addListener(new ChangeListener<Number>() 
        {
            @Override 
            public void changed( ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) 
            {
                resize();
            }
        } );
        
        setOnCloseRequest( new EventHandler() {

            @Override
            public void handle( Event t ) 
            {
                if(  selectedButton == btSave )
                {
                    if ( ! onSave() )
                    {
                        t.consume();
                    }
                }
                
                else
                {
                    onCancel();
                }
            }
        } );

        setResultConverter( new Callback() 
        {
            @Override
            public Object call(  Object p ) 
            {
                return selectedButton = (ButtonType) p;
            }
        } );
    }
    
    public void open()
    {
        showAndWait();
    }
    
    protected abstract void obtainInput();
    protected abstract void resize();
    protected abstract void setSource( T source );
}
