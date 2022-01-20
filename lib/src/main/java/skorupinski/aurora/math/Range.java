package skorupinski.aurora.math;

public class Range {
    
    private float lower;

    private float upper;

    public Range(float lower, float upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public boolean contains(float number) {
        if(number >= lower && number < upper) {
            return true;
        }
        return false;
    }

    public float getLower() {
        return lower;
    }

    public float getUpper() {
        return upper;
    }

    public float getMid() {
        return (lower + upper) / 2;
    }
    
}