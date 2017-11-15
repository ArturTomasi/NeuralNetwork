package neuralnetwork;

import javafx.application.Application;
import javafx.stage.Stage;
import neuralnetwork.ui.ApplicationView;

public class NeuralNetwork 
    extends 
        Application
{
    public static void main(String[] args) throws Exception
    {
        launch( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        new ApplicationView().start( new Stage() );
    }
}
