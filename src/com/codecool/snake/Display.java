package com.codecool.snake;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class Display extends FlowPane {
  private Label healthBar;
  private Label scoreBar;
  private HBox hbox = new HBox();

  Display() {
      hbox.getStyleClass().add("hbox");
      this.getChildren().add(hbox);
      addHealthBar();
      addScoreBar();
  }

  public void health(int health) {
    healthBar.setText(String.valueOf(health));
  }

  public void score(int score) { scoreBar.setText(String.valueOf(score)); }


  private void addHealthBar() {
    healthBar = new Label();
    healthBar.getStyleClass().add("bar");
    healthBar.getStyleClass().add("health");
    hbox.getChildren().add(healthBar);
  }

  private void addScoreBar() {
    scoreBar = new Label();
    scoreBar.getStyleClass().add("bar");
    scoreBar.getStyleClass().add("score");
    hbox.getChildren().add(scoreBar);
  }
}
