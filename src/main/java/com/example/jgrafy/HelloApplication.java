package com.example.jgrafy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/ui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  Color.LIGHTSTEELBLUE);
        stage.setTitle("JGrafy");
        stage.setX(50);
        stage.setY(50);
        Image icon = new Image("Logo.png");
        stage.getIcons().add(icon);

        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}