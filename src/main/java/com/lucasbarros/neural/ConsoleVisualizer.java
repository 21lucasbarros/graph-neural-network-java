package com.lucasbarros.neural;

public class ConsoleVisualizer {

    public static void animarRede(NeuralNetwork neurons, int delayMillis) {
        for (int i = 0; i < neurons.getCamadas().size(); i++) {
            for (Neuron n : neurons.getCamadas().get(i).getNeurons()) {
                System.out.println("[" + String.format("%.2f", n.getValue()) + " ]");
            }
        }
        System.out.println();
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
