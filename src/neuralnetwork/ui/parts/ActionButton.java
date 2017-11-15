package neuralnetwork.ui.parts;

import java.util.EventListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

/**
 * @author artur
 */
public class ActionButton
    extends 
        Button
{
    public static abstract class ActionHandler 
        implements 
            EventListener, EventHandler<ActionEvent>
    {
        @Override
        public void handle( ActionEvent t ) 
        {
            onEvent( t );
        }
        
        public abstract void onEvent( Event t );
    }
    
    private ActionButton(){}
    
    public ActionButton( String name, ActionHandler handler ) 
    {
        setText( name );
        setOnAction( handler );
        setCursor( Cursor.HAND );
        setAlignment( Pos.CENTER_LEFT );
        setMinWidth( 100 );
        setMaxWidth( 150 );

        setStyle( "-fx-background-radius: 10; " +
                  "-fx-background-color: #ECEFF1; " +
                  "-fx-border-color: #607D8B;" +
                  "-fx-text-fill: #455A64;" +
                  "-fx-border-radius: 10; " +
                  "-fx-border-width: 2;"      +
                  "-fx-font-size: 10;"      +
                  "-fx-font-weight: bolder;"  );
    }
    
}
