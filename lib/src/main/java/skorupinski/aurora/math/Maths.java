package skorupinski.aurora.math;

public class Maths {
    
    public static float pitagorean(float a, float b) {
        double aPow = Math.pow(a, 2);
        double bPow = Math.pow(b, 2);

        return (float) Math.sqrt(aPow + bPow);
    }

}
