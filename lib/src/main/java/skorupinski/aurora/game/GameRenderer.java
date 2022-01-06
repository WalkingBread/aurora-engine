package skorupinski.aurora.game;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import skorupinski.aurora.graphics.Painter;

public class GameRenderer {
    private BufferStrategy bs;
    private Graphics2D graphics;
    
    GameRenderer(Canvas canvas) {
        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        graphics = (Graphics2D) bs.getDrawGraphics();
    }

    void render() {
        graphics.dispose();
        bs.show();
    }

    Painter getPainter() {
        graphics = (Graphics2D) bs.getDrawGraphics();
        return new Painter(graphics);
    }
}
