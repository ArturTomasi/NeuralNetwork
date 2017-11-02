package redeneural.data;

import java.util.List;

public class TrainingSet {

	// The inputs you want to train your software to know
    private List<Integer> inputs;
    
    // The outputs you want you program to answer for the inputs given
    private List<Double> goodOutput;

    public TrainingSet(List<Integer> inputs, List<Double> goodOutput) {
        this.inputs = inputs;
        this.goodOutput = goodOutput;
    }

    public List<Integer> getInputs() {
        return inputs;
    }

    public List<Double> getGoodOutput() {
        return goodOutput;
    }
}
