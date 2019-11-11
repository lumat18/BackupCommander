package com.starter;

import com.log.Log;
import com.log.LogManager;
import com.log.LogPrinter;
import com.repository.Directories;
import com.repository.RepoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class MainMenuFX {

    public static GridPane menu(Stage stage){
        Button workingDirectoryInitializer = new Button("Work Directory");
        Text workDirectoryText = new Text("Working directory: <none>");
        Text repoDirectoryText = new Text("Repo directory: <none>");
        Text repoStatus = new Text("");

        Button repoInitializer = new Button("Initialize Repo");
        Button commitBackup = new Button("Commit Backup");
        commitBackup.setDisable(true);

        Text logStatus = new Text("");
        Button readLog = new Button("Read Log");
        readLog.setDisable(true);

        workingDirectoryInitializer.setOnAction(x -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Chose work directory");
            File defaultDirectory = new File(System.getProperty("user.dir") + File.separator);
            chooser.setInitialDirectory(defaultDirectory);
            File selectedDirectory = chooser.showDialog(stage);

            if (selectedDirectory != null) {
                Directories.setWorkingDir(selectedDirectory.getPath());
                Directories.setRepoDir(Directories.getWorkingDir() + "\\.repo");
                workDirectoryText.setText(Directories.getWorkingDir());
                repoStatus.setText("");
//                readLog.setDisable(true);
            }
        });

//        Button repoInitializer = new Button("Initialize Repo");
        repoInitializer.setOnAction(x-> {
            if(Directories.getRepoDir() != null){
                if(RepoManager.repoInit()){
                    repoStatus.setText("Repository already exists.");
                }
                else{
                    RepoManager.repoInit();
                    repoStatus.setText("Creating a new repository in the folder .repo.");
                }
                repoDirectoryText.setText(Directories.getRepoDir());
                commitBackup.setDisable(false);
                readLog.setDisable(false);
            }
            else{
                repoStatus.setText("No work directory selected.");
            }
        });

//        Button commitBackup = new Button("Commit Backup");
        commitBackup.setOnAction(x-> {
            commitBackupConfirm();
            repoStatus.setText("");
        });

        readLog.setOnAction(x -> {
            Path repoPath = Paths.get(Directories.getRepoDir());
            Path logPath = Paths.get(repoPath.toString()+"\\log.txt");

            if(!LogPrinter.logFileExist(logPath)){
                logStatus.setText("You have to create initial log first");
            }
            else {
                logStatus.setText("");
                LogPrinter.readLogFile(repoPath);
                showLogsWindow();
//                LogPrinter.printFullLog();
                LogPrinter.log.clear();
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 500);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(workDirectoryText, 0, 6);
        gridPane.add(repoDirectoryText, 0, 7);
        gridPane.add(workingDirectoryInitializer, 1, 0);
        gridPane.add(repoStatus, 0,1);
        gridPane.add(repoInitializer, 1,1);
        gridPane.add(commitBackup, 1 ,2);
        gridPane.add(logStatus, 0 ,3);
        gridPane.add(readLog, 1 ,3);
        return gridPane;
    }
    public static void commitBackupConfirm (){
        Stage stageCommitBackupConfirmation = new Stage();
        stageCommitBackupConfirmation.initModality(Modality.APPLICATION_MODAL);
        stageCommitBackupConfirmation.setResizable(false);
        stageCommitBackupConfirmation.setTitle("Do you want to commit new backup?");
        try {
            stageCommitBackupConfirmation.getIcons().add(MainMenuFX.icon());
        }
        catch (Exception e){}

        Text confirmQuery = new Text("Do you really want to commit backup?");
        Button yes = new Button("Yes");
        Button no = new Button("No");

        yes.setOnAction(x -> {
            logManagerWindow();
            stageCommitBackupConfirmation.close();
        });
        no.setOnAction(x -> {
            stageCommitBackupConfirmation.close();
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(350, 100);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(confirmQuery, 0 , 0);
        gridPane.add(yes,0,1);
        gridPane.add(no, 1,1);

        Group group = new Group(gridPane);
        Scene scene = new Scene(group, 350, 100);
        stageCommitBackupConfirmation.setScene(scene);
        stageCommitBackupConfirmation.show();
    }
    public static void logManagerWindow(){
        Stage logManagerWindow = new Stage();
        logManagerWindow.initModality(Modality.APPLICATION_MODAL);
        logManagerWindow.setResizable(false);
        logManagerWindow.setTitle("Fill in the fields");
        try {
            logManagerWindow.getIcons().add(MainMenuFX.icon());
        }
        catch (Exception e){}

        Text username = new Text("Username:");
        Text description = new Text("Description:");
        TextField usernameFill = new TextField();
        Text usernameWarning = new Text("");
        usernameWarning.setFill(Color.RED);
        Text descriptionWarning = new Text("");
        descriptionWarning.setFill(Color.RED);
        TextArea descriptionFill = new TextArea();
        descriptionFill.setPrefHeight(100);
        descriptionFill.setPrefWidth(150);
        descriptionFill.setWrapText(true);

        Button commit = new Button("Commit");
        Button cancel = new Button("Cancel");

        commit.setOnAction(x -> {
            usernameWarning.setText("");
            descriptionWarning.setText("");
            String usernameString = usernameFill.getText();
            String descriptionString = descriptionFill.getText().replaceAll("\n", " ");
            if(usernameString.isEmpty()){
                usernameWarning.setText("This field must be filled!");
            }
            if(descriptionString.isEmpty()){
                descriptionWarning.setText("This field must be filled");
            }
            if(!usernameString.isEmpty() && !descriptionString.isEmpty()) {
                LogManager.logExistOrCreateFile(Directories.getRepoDir(),usernameString, descriptionString);
                logManagerWindow.close();
            }
        });
        cancel.setOnAction(x -> {
            logManagerWindow.close();
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(350, 250);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(username, 0 , 0);
        gridPane.add(usernameFill, 1,0);
        gridPane.add(usernameWarning, 1,1);
        gridPane.add(description,0,2);
        gridPane.add(descriptionFill, 1,2);
        gridPane.add(descriptionWarning, 1, 3);
        gridPane.add(commit, 0, 4);
        gridPane.add(cancel, 1,4);

        Group group = new Group(gridPane);
        Scene scene = new Scene(group, 350, 250);
        logManagerWindow.setScene(scene);
        logManagerWindow.show();
    }
    public static void showLogsWindow(){
        Stage showLogsWindow = new Stage();
        showLogsWindow.initModality(Modality.APPLICATION_MODAL);
        showLogsWindow.setTitle("Pick a log to show");
        try {
            showLogsWindow.getIcons().add(MainMenuFX.icon());
        }
        catch (Exception e){}

        List<Log> logsList = LogPrinter.provideLogsFX();
        Map<String, Log> logsMap = new HashMap<>();
        logsList.stream().forEach(x -> logsMap.put(x.getDateTime(), x));

        ObservableList<String> logDates = FXCollections.observableArrayList(logsMap.keySet());

        ListView<String> logDatesList = new ListView<String>(logDates);
        logDatesList.setPrefSize(300,250);

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(350, 350);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(logDatesList,0,0);

        Group group = new Group(gridPane);
        Scene scene = new Scene(group, 350, 350);
        showLogsWindow.setScene(scene);
        showLogsWindow.show();
    }

    public static Group getGroup(Stage stage) {
        return new Group(menu(stage));
    }
    public static Image icon() throws Exception{
        FileInputStream inputStream = new FileInputStream("horse_icon.jpg");
        return new Image(inputStream);
    }
}
