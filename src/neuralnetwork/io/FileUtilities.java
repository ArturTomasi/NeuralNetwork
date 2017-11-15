package neuralnetwork.io;

import neuralnetwork.util.ResourceLocator;
import neuralnetwork.data.Letter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author artur
 */
public class FileUtilities 
{
    public static Map<Letter, List<double[]>> loadInputs()
    {
        Map<Letter, List<double[]>> letterInput = new HashMap();
        
        try
        {
            for ( Letter letter : Letter.values() )
            {
                List<double[]> inputs = letterInput.getOrDefault( letter, new ArrayList() );
                
                String path = ResourceLocator.getInstance().getPathText( letter + ".txt" );

                Files.lines( Paths.get( path ) ).forEach( line ->
                {
                    double input [] = new double[ line.length() ];
                    
                    for ( int i = 0; i < line.length(); i++ )
                    {
                        input[i] = Double.parseDouble( String.valueOf( line.charAt(i) ) );
                    }

                    inputs.add( input );
                } ); 
                
                letterInput.put( letter, inputs );
            }
        }
        
        catch ( Exception e )
        {
            Logger.getGlobal().log( Level.SEVERE, e.getMessage() );
        }
        
        return letterInput;
    }
    
    public static void saveInput( List<Integer> input, Letter filename )
    {
        try 
        {
            File file = ResourceLocator.getInstance().getFileText( filename + ".txt" );
            
            PrintWriter pw = new PrintWriter( new FileOutputStream( file, true ) );
            
            for ( Integer i : input )
            {
                pw.write( Integer.toString( i ) );
            }
            
            pw.write("\n");
            
            pw.close();
        } 
        
        catch ( Exception e ) 
        {
            Logger.getGlobal().log( Level.SEVERE, e.getMessage() );
        }
    }
}
