package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        spawn();
    }

    private void spawn() {
        int speed = 1;
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
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    private double computeDirection(boolean isHorizontal, boolean isZero) {
        double angle = new Random().nextDouble() * 180;

        if (isHorizontal) {
            if (isZero) return angle;
            else return angle + 180;
        } else {
            if (isZero) return angle + 90;
            else {
                if (angle <= 90) return angle;
                else return angle + 270;
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
    public void apply(SnakeHead player) {
        if (!player.hasShielding()) player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
