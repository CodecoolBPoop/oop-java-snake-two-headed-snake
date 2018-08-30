package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.ChompEnemy;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeBody extends GameEntity implements Animatable, Interactable {

    private SnakeHead head;
    private GameEntity parent;
    private Queue<Vec2d> history = new LinkedList<>();
    private static final int historySize = 10;

    public SnakeBody(Pane pane, GameEntity parent, SnakeHead head) {
        super(pane);
        this.parent = parent;
        this.head = head;
        setImage(Globals.snakeBody);

        // place it visually below the current tail
        List<Node> children = pane.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        setX(xc);
        setY(yc);
        for (int i = 0; i < historySize; i++) {
            history.add(new Vec2d(xc, yc));
        }
    }

    public void step() {
        Vec2d pos = history.poll(); // remove the oldest item from the history
        setX(pos.x);
        setY(pos.y);
        history.add(new Vec2d(parent.getX(), parent.getY())); // add the parent's current position to the beginning of the history
    }


    public GameEntity getPreviousPart() {
        return parent;
    }


    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof ChompEnemy) {
            head.removePart();
        }
    }

    @Override
    public String getMessage() {
        return "";
    }
}
