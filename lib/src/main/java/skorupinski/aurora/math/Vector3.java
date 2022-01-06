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

    public Vector3 rotateX(Vector3 rotationMid, float angle) {
        Vector3 temp = rotationMid.substract(this);
        Matrix m = temp.toMatrix();

        m = Constants.getXRotationMatrix(angle).multiply(m);
        return new Vector3(
            m.getValueAt(0, 0), 
            m.getValueAt(1, 0), 
            m.getValueAt(2, 0)
        );
    }

    public Vector3 rotateY(Vector3 rotationMid, float angle) {
        Vector3 temp = rotationMid.substract(this);
        Matrix m = temp.toMatrix();

        m = Constants.getYRotationMatrix(angle).multiply(m);
        return new Vector3(
            m.getValueAt(0, 0), 
            m.getValueAt(1, 0), 
            m.getValueAt(2, 0)
        );
    }

    public Vector3 rotateZ(Vector3 rotationMid, float angle) {
        Vector3 temp = rotationMid.substract(this);
        Matrix m = temp.toMatrix();

        m = Constants.getZRotationMatrix(angle).multiply(m);
        return new Vector3(
            m.getValueAt(0, 0), 
            m.getValueAt(1, 0), 
            m.getValueAt(2, 0)
        );
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }
}
