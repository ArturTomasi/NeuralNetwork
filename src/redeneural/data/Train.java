package redeneural.data;

import java.util.List;
import redeneural.io.FileUtilities;

public class Train {

    private static final int NEURON_COUNT = 26;

    private Network network;
    private List<List<TrainingSet>> trainingSets;
    
    public Train() 
    {
        this.network = new Network();
    
        this.network.addNeurons(NEURON_COUNT);
        
        this.trainingSets = FileUtilities.readTrainingSets();

        FileUtilities.restoreWeightsToNeurons(network);
        
        FileUtilities.showTextsABCSize();
    }

    public void trainAll() 
    {  	
    	for( int i = 0; i < Letter.values().length; i++ )
        {   		    		
            train( 1000, Letter.values()[i]);
    	}
    }
    
    public void train( long count, Letter letter)
    {
        for ( long i = 0; i < count; i++) 
        {
            int index = ((int) ( Math.random() * trainingSets.get( letter.ordinal() ).size()));       
        
            TrainingSet set = trainingSets.get(letter.ordinal()).get(index);
            network.setInputs(set.getInputs());
            network.adjustWeights(set.getGoodOutput());
        }
        FileUtilities.saveWeigths(network.getNeurons());
        FileUtilities.saveBiasWeight(network.getNeurons());
    }
    
    public int getNumbOfInputsLetter( Letter letter) {
        List<TrainingSet> set = trainingSets.get(letter.ordinal());
    	return set.size();
    }
    
    public List<Integer> getInputFromLetter(Letter letter, int index) {
    	TrainingSet set = trainingSets.get(letter.ordinal()).get(index);
    	return set.getInputs();
    }
    
    public List<Integer> getRandomInputFromLetter(Letter letter) {
        int index = ((int) (Math.random() * trainingSets.get(letter.ordinal()).size()));       
        TrainingSet set = trainingSets.get(letter.ordinal()).get(index);
    	return set.getInputs();
    }

    public void setInputs(List<Integer> inputs) {
        network.setInputs(inputs);
    }

    public void addTrainingSet(TrainingSet newSet, Letter letter) {
        trainingSets.get(letter.ordinal()).add(newSet);
    }

    public List<Double> getOutputs() {
        return network.getOutputs();
    }
    
    public List<List<TrainingSet>> getTrainingSets() {
    	return trainingSets;
    }

}
