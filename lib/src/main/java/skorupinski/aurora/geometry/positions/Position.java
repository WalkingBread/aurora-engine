package skorupinski.aurora.geometry.positions;

public abstract class Position {

    protected Position() {}

    public abstract Object vector();

    @Override
    public String toString() {
        return vector().toString();
    }
}
