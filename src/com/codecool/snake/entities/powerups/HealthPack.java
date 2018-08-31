package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.ChompEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class HealthPack extends GameEntity implements Interactable {
    public HealthPack(Pane pane) {
        super(pane);
        setImage(Globals.powerupHealth);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            ((SnakeHead) entity).changeHealth(10);
            destroy();
        } else if (entity instanceof ChompEnemy) {
            destroy();
        }
    }

    @Override
    public String getMessage() {
    return "Got power-up :)";
    }

}
