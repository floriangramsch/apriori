package src;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Tree extends JPanel {
    private final List<List<Node>> coords;
    private final double k;

    public Tree(List<List<Node>> coords, double k) {
        this.coords = coords;
        this.k = k;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Kreise zeichnen
        for (List<Node> ebene : this.coords) {
            for (Node node : ebene){
                int x = (int) node.get_x();
                int y = (int) node.get_y();
                int size = (int) node.get_size(); // Durchmesser des Kreises
                float supp = node.get_supp();
                g.setFont(new Font("Arial", Font.PLAIN, size/3));
                if (supp >= this.k) {
                    g.setColor(new Color(1, 170, 42)); // Farbe des Kreises
                } else {
                    g.setColor(Color.BLACK); // Farbe des Kreises
                }
                if (node.negative) {
                    g.setColor(Color.BLUE); // Farbe des Kreises
                } else if (node.positive) {
                    g.setColor(Color.RED);
                }

                g.drawOval(x, y, size, size); // Kreis zeichnen
                g.drawString(node.get_value(), x+size/4, y+size/3);
                String supp_str = String.valueOf(supp);
                g.drawString(supp_str, x+size/4, y+3*size/4);
            }
        }
    }
}
