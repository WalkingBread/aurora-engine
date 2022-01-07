package skorupinski.aurora.geometry;

import java.lang.reflect.Array;

import skorupinski.aurora.math.Vector2;

public class CollisionDetector {

    private CollisionDetector() {}

    private static class Projection {
        private float min;
        private float max;
    
        public Projection(float min, float max) {
            this.min = min;
            this.max = max;
        }
    
        public boolean overlap(Projection p) {
            return !(p.max < this.min || this.max < p.min);
        }
    }

    public static boolean collide(Shape s1, Shape s2) {
        Vector2[] axes = concatAxes(s1.getAxes(), s2.getAxes()); 

        for (Vector2 axis : axes) { 
            Projection s1Proj = project(s1, axis);
            Projection s2Proj = project(s2, axis);

            if (!s1Proj.overlap(s2Proj)) {
                return false;
            }
        }
        return true;
    }

    private static Projection project(Shape s, Vector2 axis) {
        Vector2[] vertices = s.getVertices();

        float min = axis.dot(vertices[0]); 
        float max = min;
        for (int i = 1; i < vertices.length; i++) {
            float p = axis.dot(vertices[i]); 
            if (p < min) {
                min = p;
            } else if (p > max) {
                max = p;
            }
        }
        return new Projection(min, max);
    }

    private static Vector2[] concatAxes(Vector2[] a1, Vector2[] a2) {
        int a1Length = a1.length;
        int a2Length = a2.length;

        Vector2[] res = (Vector2[]) Array.newInstance(a1.getClass().getComponentType(), a1Length + a2Length);
        System.arraycopy(a1, 0, res, 0, a1Length);
        System.arraycopy(a2, 0, res, a1Length, a2Length);

        return res;
    }
}
