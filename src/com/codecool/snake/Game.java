package com.codecool.snake;

import com.codecool.snake.entities.enemies.ChompEnemy;
import com.codecool.snake.entities.enemies.HulkEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    private  Display display =  new Display();

    public Game() {

        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new ChompEnemy(this);
        new HulkEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        this.getChildren().add(display);
    }

    public Display getDisplay() {
        return display;
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop(this);
        Globals.gameLoop.start();
    }
}
