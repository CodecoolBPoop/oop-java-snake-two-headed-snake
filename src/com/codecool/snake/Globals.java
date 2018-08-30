package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeHead1 = new Image("snake_head1.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("ghost_enemy.png");
    public static Image chompEnemy = new Image("chomp1_enemy.png");
    public static Image chompEnemy2 = new Image("chomp2_enemy.png");
    public static Image hulkEnemy = new Image("hulk.gif");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image powerupHealth = new Image("powerup_health.png");
    public static Image shield = new Image("shield.png");

    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static Stage primaryStage;
    public static Display display;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
