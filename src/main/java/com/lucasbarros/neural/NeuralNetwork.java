package com.lucasbarros.neural;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    // a rede é composta por várias camadas
    // cada camada é uma lista de neurônios
    // exemplo: [[a, b], [h], [o]] → entrada, escondida, saída
    private List<Layer> camadas;

    // a rede começa sem nenhuma camada, elas são adicionadas pelo Main
    public NeuralNetwork() {
        camadas = new ArrayList<>();
    }

    // adiciona uma camada de neurônios à rede
    // a ordem de chamada define a ordem das camadas: entrada → escondida → saída
    public void adicionarCamada(Layer camada) {
        camadas.add(camada);
    }

    // retorna todas as camadas da rede
    public List<Layer> getCamadas() {
        return camadas;
    }

    // percorre todas as camadas e calcula o valor de cada neurônio
    // a camada de entrada (i == 0) é pulada porque seus valores são definidos manualmente no Main
    // as demais camadas calculam seus valores com base nas conexões e pesos da camada anterior
    public void feedForward() {
        for(int i = 0; i < camadas.size(); i++) {
            if(i == 0) {
                continue; // neurônios de entrada já têm valor, não precisam calcular
            }

            for(Neuron n : camadas.get(i).getNeurons()) {
                n.calcularValor(); // soma entradas * pesos + bias e aplica ReLU
            }
        }
    }
}