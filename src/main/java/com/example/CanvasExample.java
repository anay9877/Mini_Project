package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CanvasExample extends Application {

    @Override
    public void start(Stage stage) {

        Canvas canvas = new Canvas(600, 400);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw a rectangle
        gc.fillRect(100, 100, 200, 100);

        // Draw text
        gc.fillText("Hello JavaFX!", 100, 250);

        StackPane root = new StackPane(canvas);

        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("JavaFX Canvas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}