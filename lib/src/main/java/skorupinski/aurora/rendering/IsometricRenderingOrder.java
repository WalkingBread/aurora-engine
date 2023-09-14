package skorupinski.aurora.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import skorupinski.aurora.geometry.isometric.Object3D;
import skorupinski.aurora.geometry.sat.AxisProjector;
import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.graphics.Painter;

public class IsometricRenderingOrder {

    private List<Object3D> order;

    private Comparator<Object3D> comp;

    private Camera camera;
    
    public IsometricRenderingOrder(List<Object3D> objects, Camera camera) {
        this.camera = camera;

        initComparator();

        order = sortInOrder(objects);
    }

    private void initComparator() {
        comp = new Comparator<Object3D>(){

            @Override
            public int compare(Object3D o1, Object3D o2) {
                Integer o1i = 1;
                Integer o2i = 0;

                Shape s1 = o1.getDisplayedShape(camera);
                Shape s2 = o2.getDisplayedShape(camera);

                if(AxisProjector.collide(s1, s2) && AxisProjector.inFront(o2, o1)) {
                    o1i = 0;
                    o2i = 1;
                }

                return o1i.compareTo(o2i);
            }
        };
    }

    private List<Object3D> sortInOrder(List<Object3D> objects) {
        List<Object3D> order = new ArrayList<>(objects);
        Collections.sort(order, comp);
        return order;
    }

    public List<Object3D> getOrder() {
        return order;
    }

    public void display(Camera camera, Painter painter) {
        for(Object3D o : order) {
            o.display(camera, painter);
        }
    }

}
