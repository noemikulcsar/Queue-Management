package org.example;


import javax.swing.*;
import GUI.SimulationFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimulationFrame frame = new SimulationFrame();
        });
    }
}


