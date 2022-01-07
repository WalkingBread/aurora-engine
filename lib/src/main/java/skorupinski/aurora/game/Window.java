package skorupinski.aurora.game;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Canvas;

import javax.swing.JFrame;

import skorupinski.aurora.events.Mouse;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector2i;

public class Window {
    
    private JFrame window;

    private Canvas canvas;

    private Vector2 resizeFactor;

    private Vector2 oldSize;

    public Window() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new Canvas();
    }

    public void setTitle(String title) {
        window.setTitle(title);
    }

    public void setSize(int width, int height) {
        window.setSize(width, height);
    }

    public void setSize(Vector2i size) {
        setSize(size.x, size.y);
    }

    public void setMinSize(int width, int height) {
        window.setMinimumSize(new Dimension(width, height));
    }

    public void setMinSize(Vector2i size) {
        setMinSize(size.x, size.y);
    }

    public void setFixedSize() {
        window.setResizable(false);
    }

    public void removeTitleBar() {
        window.setUndecorated(true);
    }

    public Vector2i getSize() {
        return new Vector2i(getWidth(), getHeight());
    }

    public int getWidth() {
        return window.getWidth();
    }

    public int getHeight() {
        return window.getHeight();
    }

    private void addCloseListener() {
        window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
                Game.terminate();
			}
        });
    }

    private void createResizeListener() {
        oldSize = getSize().toVector2();
        resizeFactor = new Vector2(1, 1);

        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                Vector2 newSize = getSize().toVector2();

                resizeFactor = new Vector2(
                    oldSize.x / newSize.x,
                    oldSize.y / newSize.y
                );
        
                oldSize = newSize;
            }
        });
    } 

    public Vector2 getResizeFactor() {
        return resizeFactor;
    }

    protected Vector2i getMaximumSize() {
        Dimension max = window.getMaximumSize();
        int w = (int) max.getWidth();
        int h = (int) max.getHeight();

        return new Vector2i(w, h);
    }

    void setMouse(Mouse mouse) {
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
    }

    Canvas getCanvas() {
        return canvas;
    }

    void setup() {
        window.setLocationRelativeTo(null);

        createResizeListener();
        addCloseListener();
        
        window.add(canvas);
        window.setVisible(true);
    } 
}
