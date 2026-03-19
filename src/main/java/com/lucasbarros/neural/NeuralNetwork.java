package com.lucasbarros.neural;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    // a rede é composta por várias camadas, cada uma com seus neurônios
    // exemplo: [[a, b], [h], [o]] → entrada, escondida, saída
    private List<Layer> camadas;

    // a rede começa vazia — as camadas são adicionadas pelo Main
    public NeuralNetwork() {
        camadas = new ArrayList<>();
    }

    // adiciona uma camada à rede — a ordem define entrada → escondida → saída
    public void adicionarCamada(Layer camada) {
        camadas.add(camada);
    }

    public List<Layer> getCamadas() {
        return camadas;
    }

    // percorre as camadas e calcula o valor de cada neurônio
    // pula a camada de entrada (i == 0) pois seus valores são definidos manualmente
    public void feedForward() {
        for (int i = 0; i < camadas.size(); i++) {
            if (i == 0) {
                continue;
            }
            for (Neuron n : camadas.get(i).getNeurons()) {
                n.calcularValor(); // soma entradas * pesos + bias e aplica ReLU
            }
        }
    }

    // ajusta os pesos da rede com base no erro entre o valor produzido e o esperado
    // esse processo é chamado de backpropagation
    public void backpropagate(double esperado, double learningRate) {

        // etapa 1: calcula o delta do neurônio de saída
        // delta = (esperado - valor) * derivada do ReLU
        // o delta representa o quanto esse neurônio contribuiu para o erro
        Neuron ultimoNeuronio = camadas.get(camadas.size() - 1).getNeurons().get(0);
        ultimoNeuronio.setDelta(
                (esperado - ultimoNeuronio.getValue()) * ultimoNeuronio.reluDerivada(ultimoNeuronio.getValue())
        );

        // etapa 2: propaga o delta de trás para frente pelas camadas escondidas
        // cada neurônio recebe o delta proporcional à sua contribuição para o erro
        for (int i = (camadas.size() - 2); i > 0; i--) {
            for (Neuron n : camadas.get(i).getNeurons()) {
                double soma = 0;

                // soma o peso * delta de cada neurônio da camada seguinte
                for (Connection c : n.getOutputConnections()) {
                    soma += c.getWeight() * c.getToNeuron().getDelta();
                }

                n.setDelta(n.reluDerivada(n.getValue()) * soma);
            }
        }

        // etapa 3: atualiza os pesos de todas as conexões
        // cada peso é ajustado na direção que reduz o erro
        for (int i = 0; i < camadas.size(); i++) {
            for (Neuron n : camadas.get(i).getNeurons()) {
                for (Connection c : n.getInputConnections()) {
                    c.atualizarPeso(learningRate);
                }
            }
        }
    }
}