package skorupinski.aurora.graphics.animations;

import java.util.HashMap;

import skorupinski.aurora.graphics.sprites.Sprite;

public class Animation {
    
    private Sprite[] frames;

    private int defaultFrameInterspace;

    private int frameIteration;

    private int frameInterspace;

    private int framesAfterLastUpdate;

    private HashMap<Integer, Runnable> functions;

    public Animation(Sprite[] frames, int frameInterspace) {
        this.frames = frames;

        this.defaultFrameInterspace = frameInterspace;
        this.frameInterspace = frameInterspace;

        frameIteration = 0;
        framesAfterLastUpdate = 0;

        functions = new HashMap<>();
    }

    public Sprite displayFrame() {
        Sprite frame = frames[frameIteration];

        if(framesAfterLastUpdate >= frameInterspace) {
            nextFrame();
            framesAfterLastUpdate = 0;

            if(functions.containsKey(frameIteration)) {
                invokeFunction();
            }
        } else {
            framesAfterLastUpdate++;
        }
        return frame;
    }

    public void resetAnimation() {
        frameIteration = 0;
    }

    public void adjustFrameInterspace(float game_speed) {
        frameInterspace = (int) (defaultFrameInterspace / game_speed);
    }

    private void nextFrame() {
        frameIteration++;
        if(frameIteration == frames.length) {
            frameIteration = 0;
            framesAfterLastUpdate = 0;
        }
    }

    private void invokeFunction() {
        Runnable function = functions.get(frameIteration);
        function.run();
    }

    public void inheritState(Animation animation) {
        setFrameIteration(animation.getFrameIteration());
        setFramesAfterLastUpdate(animation.getFramesAfterLastUpdate());
    }

    public int getFrameInterspace() {
        return frameInterspace;
    }

    public int getFramesAfterLastUpdate() {
        return framesAfterLastUpdate;
    }

    public int getFrameIteration() {
        return frameIteration;
    }

    public void setFrameIteration(int frame_iteration) {
        this.frameIteration = frame_iteration;
    }

    public void setFramesAfterLastUpdate(int frames) {
        this.framesAfterLastUpdate = frames;
    }

    public void setFunction(int[] frameIndexes, Runnable function) {
        for(int index : frameIndexes) {
            functions.put(index, function);
        }
    }
}