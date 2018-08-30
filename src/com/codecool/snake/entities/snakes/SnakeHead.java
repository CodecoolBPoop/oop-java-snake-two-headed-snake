package com.codecool.snake.entities.snakes;

import com.codecool.snake.Display;
import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health = 100;
    private int score = 0;
    private boolean shield;
    private  int shieldTimer = 0;
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
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }

        if (stepCounter % 60 == 0) updateScore(1);

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
            tail = new SnakeBody(pane, tail);
        }
    }

    public void removePart() {
            tail.destroy();
    }

    public void changeHealth(int diff) {
        health += diff;
        display.health(health);
    }

    public void updateScore(int diff) {
        score += diff;
        display.score(score);
    }

    public boolean hasShielding() {
        return shield;
    }

    public void setShielding() {
        shield = true;
        this.setImage(Globals.snakeHead1);
        shieldTimer = 600;
    }

}
