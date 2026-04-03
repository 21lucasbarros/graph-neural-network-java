package com.lucasbarros;

import com.lucasbarros.neural.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. criando os neurônios de cada camada
        // a e b -> entrada | h -> escondida | o -> saída
        Neuron a = new Neuron();
        Neuron b = new Neuron();
        Neuron h = new Neuron();
        Neuron o = new Neuron();

        // 2. valores de entrada aleatórios entre 0 e 10
        // apenas a camada de entrada recebe valor manual
        // h e o têm seus valores calculados pelo feedForward
        a.setValue((Math.random() * 10));
        b.setValue((Math.random() * 10));

        // 3. conexões com pesos definem quanto cada neurônio influencia o próximo
        // pesos inicializados aleatoriamente entre -1 e 1 para permitir aprendizado em ambas as direções
        Connection c1 = new Connection(a, h, Math.random() * 2 - 1);
        Connection c2 = new Connection(b, h, Math.random() * 2 - 1);
        Connection c3 = new Connection(h, o, Math.random() * 2 - 1);

        // 4. bias é somado antes do ReLU — também inicializado aleatoriamente entre -1 e 1
        h.setBias(Math.random() * 2 - 1);
        o.setBias(Math.random() * 2 - 1);

        // 5. agrupando os neurônios em listas por camada
        List<Neuron> inputNeurons = new ArrayList<>();
        inputNeurons.add(a);
        inputNeurons.add(b);

        List<Neuron> hiddenNeurons = new ArrayList<>();
        hiddenNeurons.add(h);

        List<Neuron> outputNeurons = new ArrayList<>();
        outputNeurons.add(o);

        // cada Layer encapsula uma lista de neurônios de uma camada
        Layer inputLayer = new Layer(inputNeurons);
        Layer hiddenLayer = new Layer(hiddenNeurons);
        Layer outputLayer = new Layer(outputNeurons);

        // 6. registrando as camadas na rede na ordem correta: entrada -> escondida -> saída
        NeuralNetwork grafo = new NeuralNetwork();
        grafo.adicionarCamada(inputLayer);
        grafo.adicionarCamada(hiddenLayer);
        grafo.adicionarCamada(outputLayer);

        // 7. treinando a rede por 1000 iterações
        // a cada iteração: feedForward calcula os valores, backpropagate ajusta os pesos
        // o objetivo é que o neurônio de saída se aproxime do valor esperado
        double esperado = 5.0;
        double learningRate = 0.01; // tamanho do passo de ajuste dos pesos

        for (int i = 0; i < 1000; i++) {
            grafo.feedForward();
            grafo.backpropagate(esperado, learningRate);

            if (i % 100 == 0) {
                System.out.println("iteração " + i + " → " + o.getValue());
            }
        }

        // 8. exibe o estado final da rede no console
        ConsoleVisualizer.animarRede(grafo, 500);
        System.out.println("valor do neurônio de saída: " + o.getValue());

        // 9. abre a janela gráfica com a visualização da rede após o treinamento
        JFrame frame = new JFrame("Neural Network");
        NetworkPanel painel = new NetworkPanel(grafo);
        frame.add(painel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}