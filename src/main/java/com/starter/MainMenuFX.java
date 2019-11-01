package com.starter;

import com.repository.Directories;
import com.repository.RepoManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
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
            chooser.setTitle("Chose work directory");
            File defaultDirectory = new File(System.getProperty("user.dir") + File.separator);
            chooser.setInitialDirectory(defaultDirectory);
            File selectedDirectory = chooser.showDialog(stage);

            if (selectedDirectory != null) {
                Directories.setWorkingDir(selectedDirectory.getPath());
                Directories.setRepoDir(Directories.getWorkingDir() + "/.repo");
                nameLabel.setText(Directories.getWorkingDir());
            }
        });

        Button repoInitializer = new Button("Initialize Repo");
        repoInitializer.setOnAction(x-> {
            if(Directories.getRepoDir() != null){
                RepoManager.repoInit();
            }
        });

        Stage stageCommitBackupConfirmation = new Stage();
        stageCommitBackupConfirmation.setTitle("Do you want to commit new backup?");

        Button commitBackup = new Button("Commit Backup");
        commitBackup.setOnAction(x-> {
            stageCommitBackupConfirmation.setScene(commitBackupConfirm());
            stageCommitBackupConfirmation.show();
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(workingDirectoryInitializer, 1, 0);
        gridPane.add(repoInitializer, 1,1);
        gridPane.add(commitBackup, 1 ,2);
        return gridPane;
    }
    public static Scene commitBackupConfirm (){
        

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        Group group = new Group(gridPane);
        return new Scene(group, 300, 200);
    }
    public static Group getGroup(Stage stage) {
        return new Group(menu(stage));
    }
}
