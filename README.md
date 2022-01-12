# Aurora Engine
**Java game engine with wide support for isometric perspective as well as plain 2D.**

### Purpose
The goal of this project is to provide a straightforward and begginer-friendly environment for creating games in Java with huge inpact on isometric perspective support. It aims to spare you as much effort as possible in setting up an isometric game, but also leaves a lot of flexibility in the design itself.

### Getting started
To set up your window with running game loop all you need to do is:
```java
public static void main(String[] args) {
 
       Window window = new Window();
       window.setTitle("test");
       window.setSize(400, 400);

       GameConfig config = new GameConfig();
       config.fps(60);
       config.tps(60);

       Game.init(window, config);

       GameEventHandler handler = new GameEventHandler() {
           @Override
           public void onTick() {
               // executed every update
           }

           @Override
           public void onFrame(Painter painter) {
               // executed every frame
               // space for drawing 
           }

           @Override
           public void onStart() {
               // executed at game loop start
           }

           @Override
           public void onClose() {
               // executed at game termination
           }
       };
    
       Game.addGameEventHandler(handler);
       Game.start();
}
```
