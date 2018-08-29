package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import javafx.scene.layout.Pane;

public class ChompEnemy extends Enemy {

    private static final int damage = 20;
    private static final int speed = 2;
    private long stepCounter = 0;
    private boolean chomp = false;

    public ChompEnemy(Pane pane) {
        super(pane, damage, speed);

        setImage(Globals.chompEnemy);
        pane.getChildren().add(this);

        spawn();
    }

    @Override
    public void step() {
        super.step();

        interactIfPossible();

        if (stepCounter % 30 == 0) {
            setImage(chomp ? Globals.chompEnemy : Globals.chompEnemy2);
            chomp = !chomp;
        }

        stepCounter++;
    }
}
