package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.HulkEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Shield extends GameEntity implements Interactable {

    public Shield(Pane pane) {
        super(pane);
        setImage(Globals.shield);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            SnakeHead head = (SnakeHead) entity;
            if (!head.hasShield()) {
                head.setShield();
                destroy();
            }
        } else if (entity instanceof HulkEnemy) {
            HulkEnemy hulk = (HulkEnemy) entity;
            if (hulk.isRunning()) hulk.hulkSmash(this);
        }
    }

    @Override
    public String getMessage() {
    return "Got power-up-shield :)";
  }

}
