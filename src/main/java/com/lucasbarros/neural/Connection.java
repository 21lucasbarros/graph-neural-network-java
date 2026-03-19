package com.lucasbarros.neural;

public class Connection {

    private Neuron fromNeuron; // neurônio de origem
    private Neuron toNeuron;   // neurônio de destino
    private double weight;     // peso da conexão — define a influência do neurônio de origem

    // ao criar a conexão, ela se registra automaticamente nos dois neurônios
    public Connection(Neuron fromNeuron, Neuron toNeuron, double weight) {
        this.fromNeuron = fromNeuron;
        this.toNeuron = toNeuron;
        this.weight = weight;

        fromNeuron.addOutputConnection(this);
        toNeuron.addInputConnection(this);
    }

    public Neuron getFromNeuron() { return fromNeuron; }
    public Neuron getToNeuron()   { return toNeuron; }
    public double getWeight()     { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    // ajusta o peso com base no gradiente do backpropagation
    // peso += learningRate * valor do neurônio de origem * delta do neurônio de destino
    public void atualizarPeso(double learningRate) {
        weight = weight + (learningRate * fromNeuron.getValue() * toNeuron.getDelta());
    }
}
