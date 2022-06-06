package com.example.jgrafy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {

    public static Parent getRoot() {
        return root;
    }

    private static Parent root;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/ui.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root,  Color.LIGHTSTEELBLUE);
        stage.setTitle("JGrafy");
        stage.setX(50);
        stage.setY(50);
        Image icon = new Image("Logo.png");
        stage.getIcons().add(icon);

        stage.setFullScreen(false);
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}