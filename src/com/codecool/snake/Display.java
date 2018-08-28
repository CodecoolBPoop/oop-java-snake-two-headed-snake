package com.codecool.snake;

import javafx.scene.control.Label;

public class Display {
  private Game game;
  private Label healthBar;
  private Label scoreBar;

  Display(Game game) {
    this.game = game;
    addHealthBar();
    addScoreBar();
  }

  public void health(int health) {
    healthBar.setText(String.valueOf(health));
  }

  private void addHealthBar() {
    healthBar = new Label();
    healthBar.setTranslateX(10);
    healthBar.setTranslateY(10);
    healthBar.getStyleClass().add("bar");
    healthBar.getStyleClass().add("health");
    healthBar.getStylesheets().add("css/main.css");
    game.getChildren().add(healthBar);
  }

  private void addScoreBar() {
    scoreBar = new Label(String.valueOf(0));
    scoreBar.setTranslateY(10);
    scoreBar.setTranslateX(Globals.WINDOW_WIDTH - 10);
    scoreBar.getStyleClass().add("bar");
    scoreBar.getStyleClass().add("score");
    scoreBar.getStylesheets().add("css/main.css");
    game.getChildren().add(scoreBar);
  }
}
