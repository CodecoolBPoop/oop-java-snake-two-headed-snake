package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPack;
import com.codecool.snake.entities.powerups.Shield;
import com.codecool.snake.entities.powerups.SimplePowerUp;
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
        int frame = (int) (now / 16666666.6667);
        if (frame % 600 == 0) new SimpleEnemy(game); // 10 seconds
        if (frame % 900 == 0) new SimplePowerUp(game); // 15 seconds
        if (frame % 1950 == 0) new HealthPack(game); // 32.5 seconds
        if (frame % 2100 == 0) new Shield(game); // 35 seconds
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable) gameObject;
                animObject.step();
            }
        }
        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}
