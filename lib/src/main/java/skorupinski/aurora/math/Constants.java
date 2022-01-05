package skorupinski.aurora.math;

public class Constants {

    public static final Matrix orthographicProjectionMatrix = new Matrix(new float[][]{
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 0}
    });

    public static Matrix getXRotationMatrix(float angle) {
        return new Matrix(new float[][]{
            {1, 0, 0},
            {0, (float) Math.cos(angle), (float) -Math.sin(angle)},
            {0, (float) Math.sin(angle), (float) Math.cos(angle)}
        });
    }

    public static Matrix getYRotationMatrix(float angle) {
        return new Matrix(new float[][]{
            {(float) Math.cos(angle), 0, (float) Math.sin(angle)},
            {0, 1, 0},
            {(float) -Math.sin(angle), 0, (float) Math.cos(angle)}
        });
    }

    public static Matrix getZRotationMatrix(float angle) {
        return new Matrix(new float[][]{
            {(float) Math.cos(angle), (float) -Math.sin(angle), 0},
            {(float) Math.sin(angle), (float) Math.cos(angle), 0},
            {0, 0, 1}
        });
    }

    public static Matrix getIsometricRotationMatrix() {
        Matrix yAxisRotationMx = new Matrix(new float[][]{
            {(float) Math.sqrt(3), 0, (float) -Math.sqrt(3)},
            {1, 2, 1},
            {(float) Math.sqrt(2), (float) -Math.sqrt(2), (float) Math.sqrt(2)}
        });

        return yAxisRotationMx.multiply(1 / (float) Math.sqrt(6));
    }

}
