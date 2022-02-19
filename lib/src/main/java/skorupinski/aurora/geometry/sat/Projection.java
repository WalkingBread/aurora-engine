package skorupinski.aurora.geometry.sat;

public class Projection {
    
    public float min;

    public float max;

    public Projection(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public boolean overlap(Projection p) {
        return !(p.max < this.min || this.max < p.min);
    }
}
