package neuralnetwork.ui.pane;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import static javafx.scene.layout.VBox.setMargin;

public class OutputPane 
    extends 
        VBox
{
    public OutputPane() 
    {
        initComponents();
    }
    
    public void clear()
    {
        outputTextArea.setText( "" );
    }
    
    public void setOutputs( double... outputs ) 
    {
        StringBuilder sb = new StringBuilder();
        
        for ( int i = 0; i < outputs.length; i++ ) 
        {
            int letterValue = i + 65;
            
            sb.append((char) letterValue);
            double value = outputs[ i ];
            //if (value < 0.01) { value = 0; }
            if (value > 0.99) { value = 1; }

            value *= 1000;
            int x = (int) (value);
            value = x / 1000.0;

            sb.append( "\t " ).append( value).append("\n" );
        }
        
        outputTextArea.setText( sb.toString() );
    }
    
    private void initComponents()
    {
        setPrefSize( 200, 575 );

        outputLabel = new Label( "Saida:" );
        
        outputLabel.setStyle( "-fx-padding: 10;" + 
                              "-fx-font-weight: bolder;" +
                              "-fx-font-size: 26pt;" +
                              "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                              "-fx-text-fill: #607D8B" );
        
        outputTextArea = new TextArea();
        outputTextArea.setEditable( false );
        outputTextArea.setPrefSize( 200, 500 );

        setMargin( outputTextArea, new Insets( 10 ) );        
        
        getChildren().addAll( outputLabel, outputTextArea );
    }
    
    private TextArea outputTextArea;
    private Label outputLabel;
}
