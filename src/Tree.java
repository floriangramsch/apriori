package src;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree extends JPanel {
    private final List<List<Node>> coords;

    public Tree(List<List<Node>> coords) {
        this.coords = coords;
    }

//    public void add_Coord(List<Integer> coord) {
//        this.coords.add(coord);
//    }
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
                if (supp >= 0.4) {
                    g.setColor(Color.RED); // Farbe des Kreises
                } else {
                    g.setColor(Color.BLACK); // Farbe des Kreises
                }

                g.drawOval(x, y, size, size); // Kreis zeichnen
                g.drawString(node.get_value(), x+size/4, y+size/3);
                String supp_str = String.valueOf(supp);
                g.drawString(supp_str, x+size/4, y+3*size/4);
            }
        }
    }
}
