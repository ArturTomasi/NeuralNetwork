package redeneural.data;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private List<Neuron> neurons;

    public Network() {
        neurons = new ArrayList();
    }

    public void addNeurons(int count) {
        for (int i = 0; i < count; i++) {
            neurons.add(new Neuron());
        }
    }

    public void setInputs(List<Integer> inputs) {
        for (Neuron n : neurons)
            n.setInputs(inputs);
    }

    public List<Double> getOutputs() {
        List<Double> outputs = new ArrayList();
        for (Neuron n : neurons)
            outputs.add(n.getOutput());

        return outputs;
    }

    public void adjustWeights(List<Double> goodOutput) {
    	for (int i = 0; i < 26; i++) {
            double delta = goodOutput.get(i) - neurons.get(i).getOutput();
            neurons.get(i).adjustWeights(delta);
        }
    }
    
    public List<Neuron> getNeurons() {
    	return neurons;
    }
    
    public void setNeurons(List<Neuron> neurons) {
    	this.neurons = neurons;
    }

}
