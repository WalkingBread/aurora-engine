package skorupinski.aurora.math;

public class Vector3 {

    public final float x;

    public final float y;

    public final float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public float distanceFrom(Vector3 pos) {
        Vector3 distance = substract(pos);
        float distance2d = Maths.pitagorean(distance.x, distance.y);

        return Maths.pitagorean(distance.z, distance2d);
    }

    public Vector3 substract(Vector3 v) {
        return new Vector3(
            x - v.x,
            y - v.y,
            z - v.z
        );
    }

    public Vector3 add(Vector3 v) {
        return new Vector3(
            x + v.x,
            y + v.y,
            z + v.z
        );
    }

    public Vector2 multiply(float n) {
        return new Vector2(
            x * n, 
            y * n
        );
    }

    public Vector3 multiply(Vector3 v) {
        return new Vector3(
            x * v.x, 
            y * v.y,
            z * v.z
        );
    }

    public Vector3 divide(float n) {
        return new Vector3(
            x / n, 
            y / n,
            z / n
        );
    }

    public Vector3 divide(Vector3 v) {
        return new Vector3(
            x / v.x, 
            y / v.y,
            z / v.z
        );
    }

    public float getLength() {
        return distanceFrom(new Vector3(0, 0, 0));
    }

    public float dot(Vector3 v) {
        return (x * v.x) + (y * v.y) + (z * v.z);
    }

    public Vector3 normalize() {
        return new Vector3(
            x * (1 / getLength()),
            y * (1 / getLength()),
            z * (1 / getLength())
        );
    }

    public Matrix toMatrix() {
        return new Matrix(new float[][]{
            {x}, {y}, {z}
        });
    }

    public Vector3 rotate(Vector3 rotationMid, Vector3 angles) {
        Vector3 temp = substract(rotationMid);
        Matrix m = temp.toMatrix();

        m = Constants.getXRotationMatrix(angles.x).multiply(m);
        m = Constants.getYRotationMatrix(angles.y).multiply(m);
        m = Constants.getZRotationMatrix(angles.z).multiply(m);

        Vector3 rotated = new Vector3(
            m.getValueAt(0, 0), 
            m.getValueAt(1, 0), 
            m.getValueAt(2, 0)
        );

        return rotated.add(rotationMid);
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

    public static Vector3 midBetween(Vector3 p1, Vector3 p2) {
        return p1.add(p2).divide(2);
    }
}
