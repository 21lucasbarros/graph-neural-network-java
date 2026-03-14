package com.lucasbarros.neural;

public class Connection {
    private Neuron fromNeuron;
    private Neuron toNeuron;
    private double weight;

    public Connection(Neuron fromNeuron, Neuron toNeuron, double weight) {
        this.fromNeuron = fromNeuron;
        this.toNeuron = toNeuron;
        this.weight = weight;

        fromNeuron.addOutputConnection(this);
        toNeuron.addInputConnection(this);
    }

    public Neuron getFromNeuron() {
        return fromNeuron;
    }

    public Neuron getToNeuron() {
        return toNeuron;
    }

    public double getWeight() {
        return weight;
    }
}
