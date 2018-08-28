package com.codecool.snake.entities.snakes;

import com.codecool.snake.Display;
import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import javafx.geometry.Point2D;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health = 100;
    private int score = 0;
    private int stepCounter = 0;
    private Display display;


    public SnakeHead(Game pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        display = pane.getDisplay();

        display.health(health);
        display.score(score);

        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }


    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        interactIfPossible();

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }

        if (stepCounter % 60 == 0) updateScore(1);

        stepCounter++;
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            tail = new SnakeBody(pane, tail);
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        display.health(health);
    }

    public void updateScore(int diff) {
        score += diff;
        display.score(score);
    }
}
