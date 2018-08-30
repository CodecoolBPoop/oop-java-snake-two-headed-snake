package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.powerups.Shield;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HulkEnemy extends Enemy {

    private long stepCounter = 0;
    private int smashTimer = 61;
    private static int damage = 100;
    private static final int speed = 3;
    private boolean running = true;
    private List<GameEntity> toDestroy = new LinkedList<>();

    public HulkEnemy(Pane pane) {
        super(pane, damage, speed);

        setImage(Globals.hulkEnemy);
        setViewport(new Rectangle2D(0, 280, 80, 90));
        pane.getChildren().add(this);

        spawn();
    }

    @Override
    void spawn() {
        Random rnd = new Random();

        int direction;

        if (rnd.nextBoolean()) {
            setX(0);
            direction = 90;
            setScaleX(1);
        } else {
            setX(Globals.WINDOW_WIDTH);
            direction = 270;
            setScaleX(-1);
        }

        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        super.heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {

        if (running) {
            super.step();
            interactIfPossible();
        } else {
            smashTimer--;
            if (smashTimer == 25) toDestroy.forEach(GameEntity::destroy);
            if (smashTimer == 0) {
                smashTimer = 61;
                running = true;
            }
        }

        if (stepCounter % 10 == 0) {
            setViewport(running ? RunAnimation.getNextFrame() : SmashAnimation.getNextFrame());
        }


        stepCounter++;
    }

    private enum RunAnimation {
        FRAME1 (new Rectangle2D(3, 280, 77, 90)),
        FRAME2 (new Rectangle2D(83, 280, 62, 90)),
        FRAME3 (new Rectangle2D(148, 280, 92, 90)),
        FRAME4 (new Rectangle2D(242, 290, 70, 90)),
        FRAME5 (new Rectangle2D(315, 290, 70, 90)),
        FRAME6 (new Rectangle2D(386, 300, 88, 90));

        private Rectangle2D frame;
        private static int nextFrame = 0;

        public static Rectangle2D getNextFrame() {
            Rectangle2D requestedFrame = RunAnimation.values()[nextFrame++].frame;
            if (nextFrame > 5) nextFrame = 0;
            return requestedFrame;
        }

        RunAnimation(Rectangle2D frame) {
            this.frame = frame;
        }
    }

    private enum SmashAnimation {
        FRAME1 (new Rectangle2D(8, 95, 62, 90)),
        FRAME2 (new Rectangle2D(85, 95, 68, 95)),
        FRAME3 (new Rectangle2D(160, 95, 55, 100)),
        FRAME4 (new Rectangle2D(233, 95, 77, 90)),
        FRAME5 (new Rectangle2D(318, 102, 67, 90)),
        FRAME6 (new Rectangle2D(430, 205, 80, 75));

        private Rectangle2D frame;
        private static int nextFrame = 0;

        public static Rectangle2D getNextFrame() {
            Rectangle2D requestedFrame = SmashAnimation.values()[nextFrame++].frame;
            if (nextFrame > 5) nextFrame = 0;
            return requestedFrame;
        }

        SmashAnimation(Rectangle2D frame) {
            this.frame = frame;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void hulkSmash(GameEntity entity) {
        if (entity instanceof Shield) toDestroy.add(entity);
        running = false;
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            hulkSmash(entity);
            ((SnakeHead) entity).changeHealth(-damage);
        }
    }
}
