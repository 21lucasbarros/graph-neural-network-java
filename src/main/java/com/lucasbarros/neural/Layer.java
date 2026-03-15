package com.lucasbarros.neural;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Neuron> neurons = new ArrayList<>();

    public Layer(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }
}
