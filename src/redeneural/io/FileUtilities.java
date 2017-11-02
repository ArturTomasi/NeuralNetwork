package redeneural.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import redeneural.data.*;
import redeneural.util.*;

/**
 * @author artur
 */
public class FileUtilities 
{

    private static File DIRECTORY = new File( FileUtilities.class.getResource( "/") + "/NeuralNetwork/");
    
    private static File temporaryFileABC;
    
    private static File biasValues = new File(DIRECTORY, "biasValues.txt");
    
    private static File weightValues = new File(DIRECTORY, "weightValues.txt");
    
    private static File goodPixels = new File(DIRECTORY, "goodPixels.txt");
    
    private static String[] arrayABC = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	
    public static void createDirectoryAndFiles() throws IOException {
		if(!DIRECTORY.exists()) {
			DIRECTORY.mkdir();
		}
		
		if(!biasValues.exists()) {
			biasValues.createNewFile();
		}
		
		if(!weightValues.exists()) {
			weightValues.createNewFile();
		}
		
		if(!goodPixels.exists()) {
			goodPixels.createNewFile();
		}
		
		for(int i = 0; i < arrayABC.length; i++) {
			temporaryFileABC = new File(DIRECTORY, arrayABC[i] + ".txt");
			if(!temporaryFileABC.exists()) {
				temporaryFileABC.createNewFile();
			}
		}
	}
	
    public static List<List<TrainingSet>> readTrainingSets() {
    	List<List<TrainingSet>> trainingSets = new ArrayList();
        
        for (int i = 0; i < 26; i++) {
            char letterValue = (char) (i + 65);
            Letter letter = Letter.valueOf( String.valueOf(letterValue) );
            
            String fileABCPath = DIRECTORY + "/" + letter + ".txt";
            List<TrainingSet> listSets = new ArrayList();
            for (List<Integer> list : readFromFile(fileABCPath)) {
            	listSets.add(new TrainingSet(list, GoodOutputs.getInstance().getGoodOutput(letter)));
            }
            trainingSets.add(listSets);
        }

        return trainingSets;
    }

    private static List<List<Integer>> readFromFile(String filename) {
        List<List<Integer>> inputs = new ArrayList();

        try {
            InputStream in = new FileInputStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> input = new ArrayList();
                for (int i = 0; i < line.length(); i++) {
                    int value = 0;
                    try {
                        value = Integer.parseInt(String.valueOf(line.charAt(i)));
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }
                    input.add(value);
                }
                inputs.add(input);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputs;
    }
    
    public static void showTextsABCSize() {
    	List<String> results = new ArrayList();
    	String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    	for(int i = 0; i < 26; i++) {
	    	try {
	            InputStream in = new FileInputStream(DIRECTORY + "/" + letters[i] + ".txt");
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	            
	            int numbLines = 0;
	            while ((reader.readLine()) != null) {
	                numbLines++;
	            }
	            results.add(letters[i] + ":" + numbLines + " results");
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
    	
    }

    public static void saveToFile(List<Integer> input, Letter filename) {
        try {
            File file = new File(DIRECTORY + "/" + filename + ".txt");
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
    
    public static void saveWeigths(List<Neuron> neurons) {
        try {
            File file = new File(DIRECTORY + "/weightValues.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (Neuron neuron : neurons) {
            	for (Double weight : neuron.getWeights()) {
            		pw.write(weight + "&");
            	}
            	pw.write("\n");
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveBiasWeight(List<Neuron> neurons) {
        try {
            File file = new File(DIRECTORY + "/biasValues.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for (Neuron neuron : neurons) {
            	pw.write(neuron.getBiasWeight() + "\n");
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void restoreWeightsToNeurons(Network network) {
    	
    	List<Neuron> neuronsRestored = new ArrayList();
    	List<Double> biasWeights = new ArrayList();
    	try {
            InputStream in = new FileInputStream(new File(DIRECTORY + "/biasValues.txt"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
            	biasWeights.add(Double.parseDouble(line));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try {
            InputStream in = new FileInputStream(new File(DIRECTORY + "/weightValues.txt"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
            	String[] arrayWeights = line.split("&");
            	List<Double> weightsNeurons = new ArrayList();
            	for(int i = 0; i < arrayWeights.length; i++) {
            		weightsNeurons.add(Double.parseDouble(arrayWeights[i]));
            	}
            	neuronsRestored.add(new Neuron(weightsNeurons, biasWeights.get(index)));
            	index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	if(neuronsRestored.size() != 0) {
    		network.setNeurons(neuronsRestored);
    	}

    }
    
    public static void cleanDocument(String filename) {
    	PrintWriter writer;
		try {
			File file = new File("src/resources/" + filename + ".txt");
			writer = new PrintWriter(new FileOutputStream(file));
	    	writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

}
