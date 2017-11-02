package neuralnetwork.io;

import neuralnetwork.util.ResourceLocator;
import neuralnetwork.data.TrainingSet;
import neuralnetwork.data.Network;
import neuralnetwork.data.Letter;
import neuralnetwork.data.Neuron;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author artur
 */
public class FileUtilities 
{
//    private static File biasValues = new File( DIRECTORY, "biasValues.txt");
//    
//    private static File weightValues = new File(DIRECTORY, "weightValues.txt");
//    
//    private static File goodPixels = new File(DIRECTORY, "goodPixels.txt");
//    
    
    public static List< List<TrainingSet> > readTrainingSets() 
    {
    	List<List<TrainingSet>> trainingSets = new ArrayList();
        
        for ( Letter letter : Letter.values() )
        {
            List<TrainingSet> listSets = new ArrayList();
            
            for ( List<Integer> list : readFromFile( ResourceLocator.getInstance().getPathText( letter + ".txt" ) ) )
            {
            	listSets.add( new TrainingSet( list, letter.getOutputs() ) );
            }
            
            trainingSets.add( listSets );
        }

            return trainingSets;
    }

    private static List<List<Integer>> readFromFile( String filename )
    {
        List<List<Integer>> inputs = new ArrayList();

        try 
        {
            InputStream in = new FileInputStream(filename);
           
            BufferedReader reader = new BufferedReader( new InputStreamReader(in) );
        
            String line;
            
            while ( (line = reader.readLine()) != null )
            {
                List<Integer> input = new ArrayList();
             
                for ( int i = 0; i < line.length(); i++ )
                {
                    int value = 0;
                 
                    input.add( Integer.parseInt( String.valueOf( line.charAt(i) ) ) );
                }
                
                inputs.add(input);
            }
            
            reader.close();
        } 
        
        catch ( IOException e )
        {
            Logger.getGlobal().log( Level.SEVERE, e.getMessage() );
        }

        return inputs;
    }
    
    public static void showTextsABCSize() 
    {
        try 
        {
            List<String> results = new ArrayList();

            for ( Letter l : Letter.values() )
            {
                InputStream in = new FileInputStream(  ResourceLocator.getInstance().getPathText( l + ".txt" ) );

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int numbLines = 0;

                while ((reader.readLine()) != null) {
                    numbLines++;
                }

                results.add( l + ":" + numbLines + " results");
                reader.close();
            }
    	}
        
        catch ( Exception e )
        {
            Logger.getGlobal().log( Level.SEVERE, e.getMessage() );
        }
    }

    public static void saveToFile(List<Integer> input, Letter filename) {
        try {
            File file = ResourceLocator.getInstance().getFileText( filename + ".txt" );
            
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
            for (Integer i : input) {
                pw.write(Integer.toString(i));
            }
            pw.write("\n");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
