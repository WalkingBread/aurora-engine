package skorupinski.aurora.geometry.sat;

import java.lang.reflect.Array;

import skorupinski.aurora.geometry.isometric.Object3D;
import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector3;

public class AxisProjector {

    private AxisProjector() {}

    public static boolean collide(Shape s1, Shape s2) {
        Vector2[] axes = concatAxes(s1.getAxes(), s2.getAxes()); 

        for(Vector2 axis : axes) { 
            Projection s1Proj = project(s1, axis);
            Projection s2Proj = project(s2, axis);

            if(!s1Proj.overlap(s2Proj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean collide(Object3D o1, Object3D o2) {
        Vector3[] axes = new Vector3[] {
            new Vector3(1, 0, 0),
            new Vector3(0, 1, 0),
            new Vector3(0, 0, 1)
        };

        for(Vector3 axis : axes) { 
            Projection o1Proj = project(o1, axis);
            Projection o2Proj = project(o2, axis);

            if(!o1Proj.overlap(o2Proj)) {
                return false;
            }
        }
        return true;
    }

    public static boolean inFront(Object3D o1, Object3D o2) {
        Vector3[] axes = new Vector3[] {
            new Vector3(1, 0, 0),
            new Vector3(0, 1, 0),
            new Vector3(0, 0, 1)
        };

        Projection p1 = null;
        Projection p2 = null;

        for(Vector3 axis : axes) { 
            Projection o1Proj = project(o1, axis);
            Projection o2Proj = project(o2, axis);

            if(o1Proj.max <= o2Proj.min || o2Proj.max <= o1Proj.min) {
                p1 = o1Proj;
                p2 = o2Proj;
                break;
            }
        }

        if(p1.min >= p2.max) {
            return true;

        } else if(p2.min >= p1.max) {
            return false;
        }
        return false;
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

    private static Projection project(Object3D s, Vector3 axis) {
        Vector3[] vertices = s.getVerticesVectors();

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

    private static <T> T[] concatAxes(T[] a1, T[] a2) {
        int a1Length = a1.length;
        int a2Length = a2.length;

        T[] res = (T[]) Array.newInstance(a1.getClass().getComponentType(), a1Length + a2Length);
        System.arraycopy(a1, 0, res, 0, a1Length);
        System.arraycopy(a2, 0, res, a1Length, a2Length);

        return res;
    }
}
