package com.codecool.snake.entities.snakes;

import com.codecool.snake.Display;
import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Animatable {

    private float speed = 2;
    private final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int tailLength;
    private int health = 100;
    private boolean shield;
    private int shieldTimer = 0;
    private int stepCounter = 0;
    private int gameOverTimeDelay = 70;


    public SnakeHead(Game pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);

        Globals.display.health(health);
        Globals.display.score(Globals.score);

        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }


    public void step() {
        if (health == 0) speed = 0.5F;

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

        // check for game over condition
        if (isOutOfBounds() || health == 0) {
            gameOverTimeDelay--;
            if (gameOverTimeDelay > 0) return;
            System.out.println("Game Over");
            Globals.display.gameOverScreen();
            Globals.gameLoop.stop();
        }

        // check if collided with an enemy or a powerup
        interactIfPossible();

        if (stepCounter % 60 == 0) updateScore(0.5 * tailLength);

        stepCounter++;
        if (shieldTimer > 0) {
            shieldTimer--;
            if (shieldTimer == 0) {
                shield = false;
                this.setImage(Globals.snakeHead);
            }
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            tail = new SnakeBody(pane, tail, this);
            tailLength++;
        }
    }

    public void removePart() {
        GameEntity newTail = ((SnakeBody) tail).getPreviousPart();
        tail.destroy();
        tail = newTail;
        tailLength--;
    }

    public void changeHealth(int diff) {
        health = Math.min(Math.max(health + diff, 0), 100);
        Globals.display.health(health);
    }

    public void updateScore(double diff) {
        Globals.score += diff;
        Globals.display.score(Globals.score);
    }

    public boolean hasShield() {
        return shield;
    }

    public void setShield() {
        shield = true;
        this.setImage(Globals.snakeHead1);
        shieldTimer = 600;
    }

}
