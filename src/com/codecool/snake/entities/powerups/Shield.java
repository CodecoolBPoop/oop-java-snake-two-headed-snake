package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Shield extends GameEntity implements Interactable{
  public Shield(Pane pane) {
    super(pane);
    setImage(Globals.shield);
    pane.getChildren().add(this);

    Random rnd = new Random();
    setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
    setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
  }

  @Override
  public void apply(SnakeHead snakeHead) {
    if (!snakeHead.hasShielding()) {
      snakeHead.setShielding();
      destroy();
    }
  }

  @Override
  public String getMessage() {
    return "Got power-up-shield :)";
  }

}
