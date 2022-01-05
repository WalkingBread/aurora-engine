package skorupinski.aurora.math;

public class Vector2 {

    public final float x;

    public final float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distanceFrom(Vector2 pos) {
        Vector2 distance = substract(pos);

        return Maths.pitagorean(distance.x, distance.y);
    }

    public float getLength() {
        return Maths.pitagorean(x, y);
    }

    
    public Vector2 substract(Vector2 v) {
        return new Vector2(
            x - v.x,
            y - v.y
        );
    }

    public Vector2 add(Vector2 v) {
        return new Vector2(
            x + v.x,
            y + v.y
        );
    }

    public float dotProduct(Vector2 v) {
        return (x * v.x) + (y * v.y);
    }

    public Vector2 normalize() {
        return new Vector2(
            x * (1 / getLength()),
            y * (1 / getLength())
        );
    }

    public Vector2i toVector2i() {
        return new Vector2i((int) x, (int) y);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
