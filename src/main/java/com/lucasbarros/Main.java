package com.lucasbarros;

import com.lucasbarros.neural.Connection;
import com.lucasbarros.neural.ConsoleVisualizer;
import com.lucasbarros.neural.Neuron;

public class Main {

    public static void main(String[] args) {

        // criando neurônios
        Neuron a = new Neuron();
        Neuron b = new Neuron();
        Neuron h = new Neuron();
        Neuron o = new Neuron();

        // criando conexões
        Connection c1 = new Connection(a, h, 0.5);
        Connection c2 = new Connection(b, h, 0.3);
        Connection c3 = new Connection(h, o, 0.8);

        // definindo valores de entrada
        a.setValue(10);
        b.setValue(4);

        // definindo bias
        h.setBias(1.0);
        o.setBias(-0.5);

        // array com todos os neurônios para facilitar animação
        Neuron[] allNeurons = {a, b, h, o};

        // mostra estado inicial
        ConsoleVisualizer.animarRede(allNeurons, 500);

        // calcula neurônio escondido e mostra animação
        h.calcularValor();
        ConsoleVisualizer.animarRede(allNeurons, 500);

        // calcula neurônio de saída e mostra animação
        o.calcularValor();
        ConsoleVisualizer.animarRede(allNeurons, 500);

        // valor final
        System.out.println("valor do neurônio de saída: " + o.getValue());
    }

}