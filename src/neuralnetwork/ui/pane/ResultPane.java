package neuralnetwork.ui.pane;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import static javafx.scene.layout.VBox.setMargin;
import neuralnetwork.data.Letter;
import neuralnetwork.util.ResourceLocator;

public class ResultPane 
    extends 
        VBox
{
    public ResultPane() 
    {
        initComponents();
    }

    public void setBackgroundResult( Letter letter ) 
    {
    	image = new Image( ResourceLocator.getInstance().getImage( letter + ".png" ) );
         
        imageView.setImage( image );
        
        requestLayout();
    }

    public void clear() 
    {
        image = new Image( ResourceLocator.getInstance().getImage( "blank.png" ) );
         
        imageView.setImage( image );
        
        requestLayout();
    }
        
    private void initComponents()
    {
        setPrefSize( 525, 575 );
        
        outputLabel = new Label( "Resultado:" );
        
        outputLabel.setStyle( "-fx-padding: 10;" + 
                              "-fx-font-weight: bolder;" +
                              "-fx-font-size: 26pt;" +
                              "-fx-font-family: \"Helvetica, Verdana, sans-serif\";" +
                              "-fx-text-fill: #607D8B" );
 
        image = new Image( ResourceLocator.getInstance().getImage( "blank.png" ) );
                
        imageView = new ImageView( image );
        imageView.setFitHeight( 500 );
        imageView.setFitWidth( 500 );
        setMargin( imageView, new Insets( 10 ) );
        
        getChildren().addAll( outputLabel, imageView );
    }
    
    private Label outputLabel;
    private ImageView imageView;
    private Image image;
}
