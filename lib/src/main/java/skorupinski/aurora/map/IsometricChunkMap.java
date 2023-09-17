package skorupinski.aurora.map;

import java.util.ArrayList;
import java.util.List;

import skorupinski.aurora.geometry.isometric.Object3D;
import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.geometry.shapes.Shape;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector2i;
import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.rendering.IsometricRenderingOrder;
import skorupinski.aurora.rendering.Renderable;

public class IsometricChunkMap extends Renderable<Isometric> {

    private int chunkSize;

    private Vector2i sizeInChunks;

    private Vector2 realSize;

    private Chunk[][] chunks;

    private List<Object3D> objects;

    private List<? extends Object3D> entities;

    public IsometricChunkMap(Vector2i sizeInChunks, int chunkSize) {
        super(new Isometric(0, 0, 0));

        this.sizeInChunks = sizeInChunks;
        this.chunkSize = chunkSize;

        realSize = sizeInChunks.toVector2().multiply(chunkSize);

        objects = new ArrayList<>();
        entities = new ArrayList<>();

        createMap();
    }

    public void createMap() {
        chunks = new Chunk[sizeInChunks.x][sizeInChunks.y];
        for(int i = 0; i < sizeInChunks.x; i++) {
            for(int j = 0; j < sizeInChunks.y; j++) {
                float x = i * chunkSize;
                float y = j * chunkSize;
                chunks[j][i] = new Chunk(new Vector2(x, y), this);
            }
        }
    }

    private void drawChunks(Painter painter, Camera camera) {
        for(int x = 0; x < sizeInChunks.x; x++) {
            for(int y = 0; y < sizeInChunks.y; y++) {
                Chunk chunk = chunks[x][y];
                if(chunk.inCamera(camera)) {
                    chunk.display(camera, painter);
                }
            }
        }
    }

    @Override
    protected void draw(Painter painter, Vector2 position, Camera camera) {
        drawChunks(painter, camera);

        List<Object3D> all = new ArrayList<>(objects);
        all.addAll(entities);

        IsometricRenderingOrder iro = new IsometricRenderingOrder(all, camera);
        iro.display(camera, painter);
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public Vector2i getChunkPosition(Isometric isometric) {
        return isometric.vectorXY().divide(sizeInChunks.toVector2()).toVector2i();
    }

    public Chunk getChunk(Isometric isometric) {
        Vector2i chunkPosition = getChunkPosition(isometric);
        return chunks[chunkPosition.x][chunkPosition.y];
    }
    
    public Vector2 getRealSize() {
        return realSize;
    }

    public Vector2i getSizeInChunks() {
        return sizeInChunks;
    }

    @Override
    public Shape getShape() {
        return null;
    }
    
}
