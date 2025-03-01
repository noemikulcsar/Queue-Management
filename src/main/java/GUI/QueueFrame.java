package GUI;

import Logic.Scheduler;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class QueueFrame extends JFrame {
    private final JPanel waitingPanel;
    private final JPanel queuePanel;

    public QueueFrame()
    {
        setTitle("Queue Viewing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000,600));

        Color backgroundColor = new Color(0xBE90D4);

        getContentPane().setBackground(backgroundColor);

        waitingPanel = new JPanel();
        waitingPanel.setLayout(new BoxLayout(waitingPanel, BoxLayout.Y_AXIS));
        JLabel waitingLabel = new JLabel("Waiting Clients:");
        waitingPanel.add(waitingLabel);
        JScrollPane waitingScrollPane = new JScrollPane(waitingPanel);
        getContentPane().add(waitingScrollPane, BorderLayout.WEST);

        queuePanel = new JPanel();
        queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.Y_AXIS));
        JLabel queueLabel = new JLabel("Queues:");
        queuePanel.add(queueLabel);
        JScrollPane queueScrollPane = new JScrollPane(queuePanel);
        getContentPane().add(queueScrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateFrame(int currentTime, List<Task> generatedTasks, Scheduler scheduler)
    {
        waitingPanel.removeAll();
        JLabel waitingLabel = new JLabel("Waiting Clients:");
        waitingPanel.add(waitingLabel);
        for (Task task : generatedTasks)
        {
            ClientPanel clientPanel = new ClientPanel(task.getId(), task.getArrivalTime(), task.getServiceTime(), Color.RED);
            waitingPanel.add(clientPanel);
        }
        queuePanel.removeAll();
        JLabel queueLabel = new JLabel("Queues:");
        queuePanel.add(queueLabel);
        for (int i = 0; i < scheduler.getServers().size(); i++)
        {
            Server server = scheduler.getServers().get(i);
            JPanel serverPanel = new JPanel();
            serverPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel serverLabel = new JLabel("Queue " + (i + 1) + ": ");
            serverPanel.add(serverLabel);
            if (server.getCurrentTask() != null)
            {
                ClientPanel currentTaskPanel = new ClientPanel(server.getCurrentTask().getId(), server.getCurrentTask().getArrivalTime(), server.getCurrentTask().getServiceTime(), Color.GREEN);
                serverPanel.add(currentTaskPanel);
            }
            for (Task task : server.getTasks()) {
                ClientPanel clientPanel = new ClientPanel(task.getId(), task.getArrivalTime(), task.getServiceTime(), Color.YELLOW);
                serverPanel.add(clientPanel);
            }
            queuePanel.add(serverPanel);
        }
        boolean timeLabelExists = false;
        for (Component component : getContentPane().getComponents())
        {
            if (component instanceof JLabel) {
                timeLabelExists = true;
                ((JLabel) component).setText("Current Time: " + currentTime);
                break;
            }
        }
        if (!timeLabelExists)
        {
            JLabel timeLabel = new JLabel("Current Time: " + currentTime);
            getContentPane().add(timeLabel, BorderLayout.NORTH);
        }
        revalidate();
        repaint();
    }
}

