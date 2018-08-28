package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import javafx.scene.layout.Pane;

public class SimpleEnemy extends Enemy {

    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane, damage);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        spawn();
    }
}
