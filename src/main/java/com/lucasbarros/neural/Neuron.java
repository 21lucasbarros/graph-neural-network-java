package com.lucasbarros.neural;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private double value = 0;
    private double bias = 0;

    private List<Connection> inputConnections;
    private List<Connection> outputConnections;

    // construtor simples
    public Neuron() {
        // as listas começam vazias
        inputConnections = new ArrayList<>();
        outputConnections = new ArrayList<>();
    }

    public void addInputConnection(Connection connection) {
        inputConnections.add(connection);
    }

    public void addOutputConnection(Connection connection) {
        outputConnections.add(connection);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public List<Connection> getOutputConnections() { return outputConnections; }

    // função ReLU: retorna 0 se x < 0, caso contrário retorna x
    private double relu(double x) {
        return Math.max(0, x);
    }

    public void calcularValor() {
        double sum = 0;

        for (Connection connection : inputConnections) {
            sum += connection.getFromNeuron().getValue() * connection.getWeight();
        }

        sum += bias;

        this.value = relu(sum);
    }
}