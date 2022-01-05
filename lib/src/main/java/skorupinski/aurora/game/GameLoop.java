package skorupinski.aurora.game;

import skorupinski.aurora.utils.Time;

public class GameLoop implements Runnable {

    private boolean running;

    private int targetFps = 60;

    private int targetTps = 120;

    private int fps;
    
    private int tps;

    private long lastUpdate = 0;

    private long lastFrame = 0;

    private long lastSecond = 0;

    private float gameSpeed = 1;

    GameLoop(int targetFps, int targetTps) {
        this.targetFps = targetFps;
        this.targetTps = targetTps;
    }

    @Override
    public void run() {
        long time = Time.getMillis();
        lastFrame = time;
        lastUpdate = time;

        int fpsCount = 0;
        int tpsCount = 0;

        float timeBetweenFrames = 1000 / targetFps;
        float timeBetweenUpdates = 1000 / targetTps;

        while (running) {
            time = Time.getMillis();

            if (time - lastSecond >= 1000) {
                fps = fpsCount;
                tps = tpsCount;

                lastSecond = time;
                fpsCount = 0;
                tpsCount = 0;
            }

            if (time - lastUpdate >= timeBetweenUpdates) {
                gameSpeed = (float) (time - lastUpdate) / timeBetweenUpdates;

                Game.tick();

                lastUpdate = time;
                tpsCount++;
            }

            if(time - lastFrame >= timeBetweenFrames) {
                Game.frame();

                lastFrame = time;
                fpsCount++;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getFps() {
        return fps;
    }

    public int getTps() {
        return tps;
    }

    public float getGameSpeed() {
        return gameSpeed;
    }

    void start() {
		if(!running) {
            running = true;
            new Thread(this).start();
        }
	}
	
	void stop() {
		if(running) {
		    running = false;
		    System.exit(0);
        }
    }
}

