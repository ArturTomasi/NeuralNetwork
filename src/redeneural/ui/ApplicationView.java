package redeneural.ui;

import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import redeneural.data.Letter;
import redeneural.data.Train;
import redeneural.data.TrainingSet;
import redeneural.io.FileUtilities;
import redeneural.ui.pane.InputPane;
import redeneural.ui.pane.MenuPane;
import redeneural.ui.pane.OutputPane;
import redeneural.ui.pane.ResultPane;
import redeneural.util.GoodOutputs;

public class ApplicationView 
    extends 
        JFrame 
{
    private Train networkTrainer;

    public ApplicationView() 
    {
        super( "RNA para reconhecimento de caracteres" );

        load();

        initComponents();
    }
    
    public void load()
    {
        try
        {
            networkTrainer = new Train();
            
            FileUtilities.createDirectoryAndFiles();
        }
        
        catch ( Exception e )
        {
            Logger.getGlobal().log( Level.SEVERE, null, e );
        }
    }

    private void disabled() 
    {
    	this.setEnabled(false);
    }

    private void enabled() 
    {
    	this.setEnabled(true);
    }
    
    private void clear() 
    {
        inputPane.clear();
        resultPane.clear();
    }
    
    private  int transformFunction() 
    {
        networkTrainer.setInputs( inputPane.getPixels() );

        List<Double> outputs = networkTrainer.getOutputs();

        int index = 0;

        for ( int i = 0; i < outputs.size(); i++ )
        {
            if ( outputs.get(i) != null && outputs.get(i) > outputs.get( index ) ) 
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

        return index;
    }
    
    private void doTrain( Letter letter, int number )
    {
        networkTrainer.addTrainingSet(new TrainingSet(inputPane.getPixels(), GoodOutputs.getInstance().getGoodOutput(letter)), letter);
        
        FileUtilities.saveToFile( inputPane.getPixels(), letter);

        networkTrainer.train(number, letter);				

        resultPane.setBackgroundResult(Letter.values()[transformFunction()]);
    }
    
    
     private void doTrainAll()
     {
        networkTrainer.trainAll();
     }
    
    
    private void initComponents()
    {
        mainPane = new JPanel();
        
        mainPane.setBackground( Color.WHITE );
        
        setContentPane( mainPane );
        
        mainPane.add( inputPane );
        
        mainPane.add( menuPane );
        
        mainPane.add( resultPane );
        
        mainPane.add( outputPane );
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
    private JPanel mainPane;
    
    private InputPane inputPane   = new InputPane();
    private OutputPane outputPane = new OutputPane();
    private ResultPane resultPane = new ResultPane();

    private MenuPane menuPane     = new MenuPane()
    {
        @Override
        public void clear() 
        {
           ApplicationView.this.clear();
        }
        
        @Override
        public int transformFunction() 
        {
            return ApplicationView.this.transformFunction();
        }
        
        @Override
        public void disabled() 
        {
            ApplicationView.this.disabled();
        }

        @Override
        public void enabled()
        {
            ApplicationView.this.enabled();
        }

        @Override
        public void doTrain( Letter letter, int number )
        {
            ApplicationView.this.doTrain( letter, number );
        }

        @Override
        public void doTrainAll() {
            ApplicationView.this.doTrainAll();
        }
    };
}
