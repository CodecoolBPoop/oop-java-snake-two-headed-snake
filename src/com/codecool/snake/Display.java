package com.codecool.snake;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.R;

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

    public void score(double score) { scoreBar.setText(String.valueOf((int) score)); }


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

    public void gameOverScreen() {
        VBox modal = new VBox();
        modal.setAlignment(Pos.CENTER);
        modal.getChildren().add(new Label("Your score: " + (int) Globals.score + "."));
        modal.getChildren().add(new Label("Press 'R' to restart."));
        Scene modalScene = new Scene(modal, 300, 200);
        modalScene.setOnKeyReleased(event -> {
            if (event.getCode() == R) Main.restart(Globals.primaryStage);
        });
        Stage modalWindow = new Stage();
        modalWindow.setScene(modalScene);
        modalWindow.setTitle("Game Over!");
        modalWindow.initModality(Modality.WINDOW_MODAL);
        modalWindow.initOwner(Globals.primaryStage);
        modalWindow.show();
    }
}
