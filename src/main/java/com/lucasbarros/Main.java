package com.lucasbarros;

import com.lucasbarros.neural.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1. criando os neurônios de cada camada
        // a e b → entrada | h → escondida | o → saída
        Neuron a = new Neuron();
        Neuron b = new Neuron();
        Neuron h = new Neuron();
        Neuron o = new Neuron();

        // 2. definindo os valores de entrada da rede
        // apenas os neurônios de entrada recebem valor manual
        // h e o terão seus valores calculados automaticamente pelo feedForward
        a.setValue((Math.random() * 10));
        b.setValue((Math.random() * 10));

        // 3. criando as conexões entre os neurônios com seus pesos
        // cada conexão define quanto um neurônio influencia o próximo
        // a → h com peso 0.5 | b → h com peso 0.3 | h → o com peso 0.8
        Connection c1 = new Connection(a, h, 0.5);
        Connection c2 = new Connection(b, h, 0.3);
        Connection c3 = new Connection(h, o, 0.8);

        // 4. definindo o bias de cada neurônio que calcula valor
        // o bias é somado antes do ReLU e ajuda a rede a ajustar a ativação
        h.setBias(1.0);
        o.setBias(-0.5);

        // 5. organizando os neurônios em listas e agrupando em camadas
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

        // 6. registrando as camadas na rede na ordem correta: entrada → escondida → saída
        NeuralNetwork grafo = new NeuralNetwork();
        grafo.adicionarCamada(inputLayer);
        grafo.adicionarCamada(hiddenLayer);
        grafo.adicionarCamada(outputLayer);

        // 7. executa o feedforward: percorre as camadas e calcula os valores de h e o
        grafo.feedForward();

        // 8. exibe o valor final no console
        ConsoleVisualizer.animarRede(grafo, 500);
        System.out.println("valor do neurônio de saída: " + o.getValue());

        // 9. abre a janela gráfica com a visualização animada da rede
        JFrame frame = new JFrame("Neural Network");
        NetworkPanel painel = new NetworkPanel(grafo);
        frame.add(painel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha o programa ao fechar a janela
        frame.setVisible(true);
    }
}