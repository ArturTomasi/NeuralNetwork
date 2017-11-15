package neuralnetwork.ui.pane;

import java.awt.Font;
import java.util.List;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import neuralnetwork.ui.DrawingPanel;

public class InputPane 
    extends 
        VBox
{
    public static final int PANEL_SIZE = 20;
    
    public InputPane() 
    {
        initComponents();
    }
    
    public List<Integer> getPixels()
    {
        return drawingPanel.getPixels();
    }
    
    public void drawLetter( List<Integer> pixels )
    {
        drawingPanel.drawLetter( pixels );
    }

    public void clear()
    {
        drawingPanel.clear();
    }
    
    private void initComponents()
    {
        setPrefSize( 525, 575 );
           
        inputLabel = new Label( "Entrada:" );
        inputLabel.setStyle( "-fx-padding: 10;" + 
                             "-fx-font-weight: bolder;" +
                             "-fx-font-size: 26pt;" +
                             "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                             "-fx-text-fill: #607D8B" );
        
        drawingPanel = new DrawingPanel( 500, 500, PANEL_SIZE );

        SwingNode embed = new SwingNode();
        embed.setContent( drawingPanel );
        
        setMargin( embed, new Insets( 10 ) );
            
        getChildren().addAll( inputLabel, embed );
    }
    
    private DrawingPanel drawingPanel;
    private Label inputLabel;
}
