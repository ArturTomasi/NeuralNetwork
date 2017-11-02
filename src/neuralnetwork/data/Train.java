package neuralnetwork.data;

import java.util.List;
import neuralnetwork.io.FileUtilities;

public class Train
{
    private Network network;
    
    private List<List<TrainingSet>> trainingSets;
    
    public Train() 
    {
        this.network = new Network();
    
        this.network.addNeurons( Letter.values().length );
        
        this.trainingSets = FileUtilities.readTrainingSets();

        FileUtilities.showTextsABCSize();
    }

    public void trainAll() 
    {  	
        for ( Letter value : Letter.values() ) 
        {
            train( 10000, value);
        }
    }
    
    public void train( long count, Letter letter)
    {
        for ( long i = 0; i < count; i++) 
        {
            int index = ((int) ( Math.random() * trainingSets.get( letter.ordinal() ).size()));       
        
            TrainingSet set = trainingSets.get(letter.ordinal()).get(index);
            
            network.setInputs(set.getInputs());
            
            network.adjustWeights( set.getGoodOutput() );
        }
    }
    
    public int getNumbOfInputsLetter( Letter letter )
    {
        List<TrainingSet> set = trainingSets.get(letter.ordinal());
    
        return set.size();
    }
    
    public List<Integer> getInputFromLetter( Letter letter, int index )
    {
    	TrainingSet set = trainingSets.get(letter.ordinal()).get(index);
    
        return set.getInputs();
    }
    
    public List<Integer> getRandomInputFromLetter( Letter letter )
    {
        int index = ((int) (Math.random() * trainingSets.get(letter.ordinal()).size()));       
    
        TrainingSet set = trainingSets.get(letter.ordinal()).get(index);
    	
        return set.getInputs();
    }

    public void setInputs(List<Integer> inputs) 
    {
        network.setInputs(inputs);
    }

    public void addTrainingSet( TrainingSet newSet, Letter letter )
    {
        trainingSets.get(letter.ordinal()).add(newSet);
    }

    public List<Double> getOutputs() 
    {
        return network.getOutputs();
    }
    
    public List<List<TrainingSet>> getTrainingSets()
    {
    	return trainingSets;
    }

}
