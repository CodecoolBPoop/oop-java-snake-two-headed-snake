package com.codecool.snake;

import com.codecool.snake.entities.enemies.ChompEnemy;
import com.codecool.snake.entities.enemies.HulkEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.HealthPack;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.Shield;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.org.apache.bcel.internal.generic.GotoInstruction;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        this.getStylesheets().add("css/main.css");
        this.getStyleClass().add("main");

        Globals.display = new Display();

        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new ChompEnemy(this);
        new HulkEnemy(this);

        new SimplePowerUp(this);
        new SimplePowerUp(this);
        new SimplePowerUp(this);
        new SimplePowerUp(this);

        new HealthPack(this);
        new Shield(this);

        this.getChildren().add(Globals.display);
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
                case R:
                    Globals.gameLoop.stop();
                    Main.restart(Globals.primaryStage);
            }
        });
        Globals.gameLoop = new GameLoop(this);
        Globals.gameLoop.start();
    }
}
