/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menutest;

import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed Hammad
 */
public class MenuTest extends Application {

    TextArea textArea;
    FileChooser fileChooser = new FileChooser();
    File chosenFile = new File("");

    @Override
    public void start(Stage primaryStage) {

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem newItem = new MenuItem("New");
        newItem.setOnAction(e -> {
            textArea.setText("");
            primaryStage.setTitle("Untitled - Notpad");
        });
        MenuItem newWindowItem = new MenuItem("New Window");
        newWindowItem.setOnAction(e -> {
            new MenuTest().start(new Stage());
        });
        KeyCharacterCombination newCharacterCombination = new KeyCharacterCombination(
                "N",
                KeyCombination.ModifierValue.UP,
                KeyCombination.ModifierValue.DOWN,
                KeyCombination.ModifierValue.UP,
                KeyCombination.ModifierValue.UP,
                KeyCombination.ModifierValue.UP);
        newItem.acceleratorProperty().setValue(newCharacterCombination);
        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> {
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files Only", "*.txt"),
                    new FileChooser.ExtensionFilter("PDF Files Only", "*.pdf"),
                    new FileChooser.ExtensionFilter("PDF and text Files", "*.txt", "*.pdf")
            );

            chosenFile = fileChooser.showOpenDialog(primaryStage);
            /*----------------------*/
            try {
                BufferedReader br = new BufferedReader(new FileReader(chosenFile));
                primaryStage.setTitle(chosenFile.getName() + "- Notpad");
                String line = "";
                textArea.setText("");
                while ((line = br.readLine()) != null) {
                    textArea.appendText(line + "\n");

                }
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {
                Logger.getLogger(MenuTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        KeyCharacterCombination newItemCharacterCombination = new KeyCharacterCombination(
                "N",
                KeyCombination.ModifierValue.DOWN,
                KeyCombination.ModifierValue.DOWN,
                KeyCombination.ModifierValue.UP,
                KeyCombination.ModifierValue.UP,
                KeyCombination.ModifierValue.UP);
        newWindowItem.acceleratorProperty().setValue(newItemCharacterCombination);
        MenuItem saveItem = new MenuItem("Save");

        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Save Button is pressed");
                if (!chosenFile.isFile()) {
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files Only", "*.txt"));
                    chosenFile = fileChooser.showSaveDialog(primaryStage);
                    primaryStage.setTitle(chosenFile.getName() + "- Notepad");
                    try {
                        FileWriter fw = new FileWriter(chosenFile);
                        fw.write(textArea.getText());
                        fw.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        FileWriter fw = new FileWriter(chosenFile);
                        fw.write(textArea.getText());
                        fw.close();
                    } catch (FileNotFoundException ex) {

                    } catch (IOException ex) {
                        Logger.getLogger(MenuTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        MenuItem saveAsItem = new MenuItem("Save as");
        saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files Only", "*.txt"));
                chosenFile = fileChooser.showSaveDialog(primaryStage);
                primaryStage.setTitle(chosenFile.getName() + "- Notepad");
                try {
                    FileWriter fw = new FileWriter(chosenFile);
                    fw.write(textArea.getText());
                    fw.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        menuBar.getMenus()
                .addAll(fileMenu);

        Menu projectMenu = new Menu("Prject");
        MenuItem laravelItem = new MenuItem("Laravel");
        MenuItem javaItem = new MenuItem("Java");

        projectMenu.getItems()
                .addAll(laravelItem, javaItem);

        textArea = new TextArea();

        BorderPane borderPane = new BorderPane();

        borderPane.setTop(menuBar);

        borderPane.setCenter(textArea);
        Scene scene = new Scene(borderPane, 500, 500);

        fileMenu.getItems()
                .addAll(newItem, newWindowItem, openItem, saveItem, saveAsItem, projectMenu, exitItem);
        primaryStage.setTitle(
                "Hello World!");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    
    
   public void saveFunctin(File chosenFile) {
       
   }

            /**
             * @param args the command line arguments
             */

            public static void main(String[] args) {
        launch(args);
    }
            
}
