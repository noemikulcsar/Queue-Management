package GUI;

import Logic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel numOfClientsLabel;
    private JTextField numOfClientsTextField;
    private JLabel numOfQueuesLabel;
    private JTextField numOfQueuesTextField;
    private JLabel maxSimulationTimeLabel;
    private JTextField maxSimulationTimeTextField;
    private JLabel arrivalTimeBoundsLabel;
    private JTextField arrivalTimeBoundsTextField1;
    private JTextField arrivalTimeBoundsTextField2;
    private JLabel serviceTimeBoundsLabel;
    private JTextField serviceTimeBoundsTextField1;
    private JTextField serviceTimeBoundsTextField2;
    private JLabel algorithmLabel;
    private JComboBox<String> algorithmComboBox;
    private JButton enterButton;
    private SimulationManager simulationManager;

    public SimulationFrame() {
        setTitle("Queues Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2));
        setMinimumSize(new Dimension(400, 300));

        Color backgroundColor = new Color(0xBE90D4);
        Color fontColor = new Color(0x5B3256);

        getContentPane().setBackground(backgroundColor);

        titleLabel = new JLabel("Queues Management", SwingConstants.CENTER);
        titleLabel.setForeground(fontColor);
        add(titleLabel);
        add(new JLabel("")); // Adding an empty label for layout purposes

        numOfClientsLabel = new JLabel("Number of clients:");
        numOfClientsLabel.setForeground(fontColor);
        add(numOfClientsLabel);
        numOfClientsTextField = new JTextField();
        add(numOfClientsTextField);

        numOfQueuesLabel = new JLabel("Number of queues:");
        numOfQueuesLabel.setForeground(fontColor);
        add(numOfQueuesLabel);
        numOfQueuesTextField = new JTextField();
        add(numOfQueuesTextField);

        maxSimulationTimeLabel = new JLabel("Max simulation time:");
        maxSimulationTimeLabel.setForeground(fontColor);
        add(maxSimulationTimeLabel);
        maxSimulationTimeTextField = new JTextField();
        add(maxSimulationTimeTextField);

        arrivalTimeBoundsLabel = new JLabel("Arrival time bounds:");
        arrivalTimeBoundsLabel.setForeground(fontColor);
        add(arrivalTimeBoundsLabel);
        JPanel arrivalTimePanel = new JPanel();
        arrivalTimeBoundsTextField1 = new JTextField(5);
        arrivalTimePanel.add(arrivalTimeBoundsTextField1);
        arrivalTimeBoundsTextField2 = new JTextField(5);
        arrivalTimePanel.add(arrivalTimeBoundsTextField2);
        add(arrivalTimePanel);

        serviceTimeBoundsLabel = new JLabel("Service time bounds:");
        serviceTimeBoundsLabel.setForeground(fontColor);
        add(serviceTimeBoundsLabel);
        JPanel serviceTimePanel = new JPanel();
        serviceTimeBoundsTextField1 = new JTextField(5);
        serviceTimePanel.add(serviceTimeBoundsTextField1);
        serviceTimeBoundsTextField2 = new JTextField(5);
        serviceTimePanel.add(serviceTimeBoundsTextField2);
        add(serviceTimePanel);

        algorithmLabel = new JLabel("Algorithm:");
        algorithmLabel.setForeground(fontColor);
        add(algorithmLabel);

        algorithmComboBox = new JComboBox<>(new String[]{"Shortest Queue", "Shortest Time"});
        algorithmComboBox.setForeground(fontColor); // Set font color for combobox
        algorithmComboBox.setBackground(backgroundColor); // Set background color for combobox
        add(algorithmComboBox);

        enterButton = new JButton("Enter");
        enterButton.setForeground(fontColor); // Set font color for button
        enterButton.setBackground(backgroundColor); // Set background color for button
        add(enterButton);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numOfClients = Integer.parseInt(numOfClientsTextField.getText());
                    int numOfQueues = Integer.parseInt(numOfQueuesTextField.getText());
                    int maxSimulationTime = Integer.parseInt(maxSimulationTimeTextField.getText());
                    int arrivalTimeBound1 = Integer.parseInt(arrivalTimeBoundsTextField1.getText());
                    int arrivalTimeBound2 = Integer.parseInt(arrivalTimeBoundsTextField2.getText());
                    int serviceTimeBound1 = Integer.parseInt(serviceTimeBoundsTextField1.getText());
                    int serviceTimeBound2 = Integer.parseInt(serviceTimeBoundsTextField2.getText());
                    String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                    simulationManager = new SimulationManager(numOfQueues);
                    simulationManager.setNumberOfClients(numOfClients);
                    simulationManager.setTimeLimit(maxSimulationTime);
                    simulationManager.setArrivalTimeBounds(arrivalTimeBound1, arrivalTimeBound2);
                    simulationManager.setServiceTimeBounds(serviceTimeBound1, serviceTimeBound2);
                    simulationManager.setSelectionPolicy(selectedAlgorithm);
                    Thread thread = new Thread(simulationManager);
                    thread.start();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(SimulationFrame.this, "Invalid input! Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
