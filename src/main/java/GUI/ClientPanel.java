package GUI;

import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JPanel {
    private final int id;
    private final int arrivalTime;
    private final int serviceTime;
    private final Color color;

    public ClientPanel(int id, int arrivalTime, int serviceTime, Color color) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.color = color;

        setPreferredSize(new Dimension(70, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        g.drawString("("+id+", "+arrivalTime+", "+serviceTime+")", 5, 15);
    }
}
