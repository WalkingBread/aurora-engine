package skorupinski.aurora.map;

import skorupinski.aurora.geometry.Rectangle;
import skorupinski.aurora.geometry.positions.Isometric;
import skorupinski.aurora.graphics.Painter;
import skorupinski.aurora.math.Vector2;
import skorupinski.aurora.math.Vector2i;
import skorupinski.aurora.rendering.Camera;
import skorupinski.aurora.rendering.Renderable;

public class IsometricChunkMap extends Renderable<Isometric> {

    private int chunkSize;

    private Vector2i sizeInChunks;

    private Vector2 realSize;

    private Chunk[][] chunks;

    public IsometricChunkMap(Vector2i sizeInChunks, int chunkSize) {
        super(new Isometric(0, 0, 0));

        this.sizeInChunks = sizeInChunks;
        this.chunkSize = chunkSize;

        realSize = sizeInChunks.toVector2().multiply(chunkSize);

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

    @Override
    protected void draw(Painter painter, Vector2 position, Camera camera) {
        for(int x = 0; x < sizeInChunks.x; x++) {
            for(int y = 0; y < sizeInChunks.y; y++) {
                Chunk chunk = chunks[x][y];
                if(chunk.inCamera(camera)) {
                    chunk.display(camera, painter);
                }
            }
        }
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
    public Rectangle getRectangle() {
        return null;
    }
    
}
