package com.starter;

import com.repository.Directories;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainMenuFX {
    public static GridPane menu(Stage stage){
        Button workingDirectoryInitializer = new Button("Work Directory");
        Text nameLabel = new Text("Working directory: <none>");

        workingDirectoryInitializer.setOnAction(x -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Chose work directory.");
            File defaultDirectory = new File(System.getProperty("user.dir") + File.separator);
            chooser.setInitialDirectory(defaultDirectory);
            File selectedDirectory = chooser.showDialog(stage);

            if (selectedDirectory != null) {
                Directories.setWorkingDir(selectedDirectory.getPath());
                nameLabel.setText(Directories.getWorkingDir());
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(workingDirectoryInitializer, 1, 0);
        return gridPane;
    }
    public static Group getGroup(Stage stage) {
        return new Group(menu(stage));
    }
}
