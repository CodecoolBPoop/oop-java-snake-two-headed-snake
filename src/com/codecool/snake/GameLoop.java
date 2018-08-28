package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class GameLoop extends AnimationTimer {
    private Pane game;

    GameLoop(Pane pane) {
        super();
        game = pane;
    }

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        if ((int) (now / 16666666.6667) % 900 == 0) new SimpleEnemy(game);
        if ((int) (now / 16666666.6667) % 1200 == 0) new SimplePowerup(game);
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}
