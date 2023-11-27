package src;

import java.util.List;

public class Node {
    private List<Float> coords;
    private float supp;
    private StringBuilder value;
    float size;

    public Node(List<Float> coords, float supp, StringBuilder value, float size) {
        this.coords = coords;
        this.supp = supp;
        this.value = value;
        this.size = size;
    }

    public float get_x() {
        return this.coords.get(0);
    }
    public float get_y() {
        return this.coords.get(1);
    }
    public float get_size() {
        return this.size;
    }
    public float get_supp() {
        return this.supp;
    }
    public String get_value() {
        return this.value.toString();
    }
}
