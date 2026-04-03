package com.lucasbarros.neural;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NetworkPanel extends JPanel {

    private NeuralNetwork network;

    public NetworkPanel(NeuralNetwork network) {
        this.network = network;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<Layer> camadas = network.getCamadas();
        int totalCamadas = camadas.size();
        int w = getWidth(), h = getHeight();
        int raio = 30;

        // calcula posições
        int[][] posX = new int[totalCamadas][];
        int[][] posY = new int[totalCamadas][];

        for (int i = 0; i < totalCamadas; i++) {
            int total = camadas.get(i).getNeurons().size();
            posX[i] = new int[total];
            posY[i] = new int[total];
            int x = (int) ((i + 1) * (w / (totalCamadas + 1.0)));
            for (int j = 0; j < total; j++) {
                posY[i][j] = (int) ((j + 1) * (h / (total + 1.0)));
                posX[i][j] = x;
            }
        }

        // desenha linhas
        g2.setColor(Color.GRAY);
        for (int i = 0; i < totalCamadas - 1; i++) {
            for (int j = 0; j < posX[i].length; j++) {
                for (int k = 0; k < posX[i + 1].length; k++) {
                    g2.drawLine(posX[i][j], posY[i][j], posX[i + 1][k], posY[i + 1][k]);
                }
            }
        }

        // desenha círculos
        for (int i = 0; i < totalCamadas; i++) {
            List<Neuron> neuronios = camadas.get(i).getNeurons();
            for (int j = 0; j < neuronios.size(); j++) {
                int x = posX[i][j], y = posY[i][j];
                double valor = neuronios.get(j).getValue();

                // cor baseada na camada: entrada -> escondida -> saída
                Color cores = new Color(37, 99, 235);

                g2.setColor(cores);
                g2.fillOval(x - raio, y - raio, raio * 2, raio * 2);
                g2.setColor(cores.darker());
                g2.drawOval(x - raio, y - raio, raio * 2, raio * 2);

                g2.setFont(new Font("Courier New", Font.PLAIN, 16));
                FontMetrics fm = g2.getFontMetrics();
                String texto = String.format("%.2f", valor);
                g2.setColor(Color.WHITE);
                g2.drawString(texto, x - fm.stringWidth(texto) / 2, y + 5);
            }
        }
    }
}