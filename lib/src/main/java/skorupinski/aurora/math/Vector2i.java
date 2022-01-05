package skorupinski.aurora.math;

public class Vector2i {
    
    public final int x;

    public final int y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2 toVector2() {
        return new Vector2(x, y);
    }
}
