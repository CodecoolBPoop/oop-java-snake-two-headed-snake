package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.Random;

public abstract class Enemy extends GameEntity implements Animatable, Interactable {
    private int damage;
    private int speed;
    Point2D heading;
    private boolean isFlipped = false;

    Enemy(Pane pane, int damage, int speed) {
        super(pane);
        this.damage = damage;
        this.speed = speed;
    }

    void spawn() {
        Random rnd = new Random();

        boolean isHorizontal = rnd.nextBoolean();
        boolean isZero = rnd.nextBoolean();

        if (isHorizontal) {
            setX(isZero ? 0 : Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        } else {
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(isZero ? 0 : Globals.WINDOW_HEIGHT);
        }

        double direction = computeDirection(isHorizontal, isZero);

        if (direction < 180 && isFlipped) flip();
        else if (direction > 180 && !isFlipped) flip();

        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    private void flip() {
        isFlipped = !isFlipped;
        this.setScaleX(isFlipped ? -1 : 1);
    }

    private double computeDirection(boolean isHorizontal, boolean isZero) {
        double angle = new Random().nextDouble() * 180;

        if (isHorizontal) {
            if (isZero) return angle;
            else return angle + 180;
        } else {
            if (isZero) return angle + 90;
            else {
                if (angle <= 90) return angle + 270;
                else return angle;
            }
        }
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            spawn();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead && this instanceof SimpleEnemy) {
            SnakeHead head = (SnakeHead) entity;
            if (!head.hasShield()) head.changeHealth(-damage);
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return damage + " damage";
    }
}
