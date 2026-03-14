package com.lucasbarros.neural;

public class ConsoleVisualizer {

    public static void animarRede(Neuron[] neurons, int delayMillis) {
        for(Neuron n : neurons) {
            System.out.println("[" + String.format("%.2f", n.getValue()) + " ]");
        }
        System.out.println();
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
