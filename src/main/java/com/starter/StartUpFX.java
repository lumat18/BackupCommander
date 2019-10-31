package com.starter;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class StartUpFX extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FileInputStream inputStream = new FileInputStream("horse_icon.jpg");
        Image image = new Image(inputStream);
        primaryStage.getIcons().add(image);

        Group group = new Group();
        Scene scene = new Scene(group, 640, 480);

        primaryStage.setTitle(MainMenu.title());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
