package com.lucasbarros.neural;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

    private double value = 0; // valor calculado pelo feedForward
    private double bias  = 0; // valor fixo somado antes do ReLU
    private double delta = 0; // erro propagado pelo backpropagation

    private List<Connection> inputConnections;
    private List<Connection> outputConnections;

    public Neuron() {
        inputConnections  = new ArrayList<>();
        outputConnections = new ArrayList<>();
    }

    public void addInputConnection(Connection connection)  { inputConnections.add(connection); }
    public void addOutputConnection(Connection connection) { outputConnections.add(connection); }

    public List<Connection> getInputConnections()  { return inputConnections; }
    public List<Connection> getOutputConnections() { return outputConnections; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public double getBias() { return bias; }
    public void setBias(double bias) { this.bias = bias; }

    public double getDelta() { return delta; }
    public void setDelta(double delta) { this.delta = delta; }

    // função de ativação: zera valores negativos e mantém os positivos
    private double relu(double x) {
        return Math.max(0, x);
    }

    // derivada do ReLU: retorna 1 se o neurônio está ativo, 0 se não está
    // usada no backpropagation para saber se o neurônio pode aprender
    public double reluDerivada(double x) {
        return x > 0 ? 1 : 0;
    }

    // calcula o valor do neurônio somando todas as entradas * pesos, mais o bias, e aplica ReLU
    public void calcularValor() {
        double sum = 0;

        for (Connection connection : inputConnections) {
            sum += connection.getFromNeuron().getValue() * connection.getWeight();
        }

        sum += bias;
        this.value = relu(sum);
    }
}