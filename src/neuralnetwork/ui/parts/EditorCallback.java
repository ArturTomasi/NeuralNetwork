/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork.ui.parts;

import java.util.Properties;
import javafx.event.EventHandler;


/**
 *
 * @author artur
 * @param <T>
 */
public abstract class EditorCallback<T> 
    implements 
        EventHandler
{
    public T source;
    public Properties properties = new Properties();

    public EditorCallback( T source ) 
    {
        this.source = source;
    }
}
