package src;

import java.util.List;

public class Node {
    private final List<Float> coords;
    private final float supp;
    private final StringBuilder value;
    float size;
    boolean positive;
    boolean negative;

    public Node(List<Float> coords, float supp, StringBuilder value, float size) {
        this.coords = coords;
        this.supp = supp;
        this.value = value;
        this.size = size;
        this.positive = false;
        this.negative = false;
    }

    public void set_negative() {
        this.negative = true;
    }
    public void set_positive() {
        this.positive = true;
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
