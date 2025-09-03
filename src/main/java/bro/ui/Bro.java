package bro.ui;

import java.io.IOException;

import bro.tasks.Tasks;
import bro.utils.FileIo;
import bro.utils.Parser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

/**
 * The main class for the Bro application, responsible for initializing and
 * running the program.
 */
public class Bro extends Application {
    private Ui ui;
    private Parser parser;
    private Tasks tasks;

    private Bro bro = this;

    /**
     * Initializes the Bro application by setting up the user interface, the user
     * input parser and loading existing tasks from storage.
     */
    public Bro() {
        try {
            this.ui = new Ui();
            this.parser = new Parser();
            this.tasks = new Tasks(FileIo.loadData());
        } catch (Exception e) {
            System.out.println("Error initializing Bro.");
        }
    }

    /**
     * Starts the Bro application, allowing user interaction.
     */
    public void run() {
        ui.printHello();
        String input = ui.readInput();
        while (input != null) {
            String[] commandData = parser.getCommandData(input);
            String command = commandData[0];
            switch (command) {
                case "bye":
                    input = null;
                    break;
                case "list":
                    tasks.listTasks();
                    break;
                case "mark":
                    tasks.markTask(commandData);
                    break;
                case "unmark":
                    tasks.unmarkTask(commandData);
                    break;
                case "delete":
                    tasks.deleteTask(commandData);
                    break;
                case "tasks on":
                    tasks.showTasksOn(commandData);
                    break;
                case "todo":
                    tasks.addTask(commandData);
                    break;
                case "deadline":
                    tasks.addTask(commandData);
                    break;
                case "event":
                    tasks.addTask(commandData);
                    break;
                case "error":
                    break;
                case "unknown":
                    System.out.println("\tI'm sorry, I don't know what that means :(");
                    break;
                default:
                    System.out.println("\tERROR!");
                    break;
            }
            if (input == null) {
                break;
            }
            input = ui.readInput();
        }
        ui.printBye();
    }

    public String getResponse(String input) {
        String[] commandData = parser.getCommandData(input);
        String command = commandData[0];
        String output;
        switch (command) {
            case "bye":
                output = ui.printBye();
                break;
            case "list":
                output = tasks.listTasks();
                break;
            case "mark":
                output = tasks.markTask(commandData);
                break;
            case "unmark":
                output = tasks.unmarkTask(commandData);
                break;
            case "delete":
                output = tasks.deleteTask(commandData);
                break;
            case "tasks on":
                output = tasks.showTasksOn(commandData);
                break;
            case "todo":
                output = tasks.addTask(commandData);
                break;
            case "deadline":
                output = tasks.addTask(commandData);
                break;
            case "event":
                output = tasks.addTask(commandData);
                break;
            case "error":
                output = commandData[1];
                break;
            case "unknown":
                output = "I'm sorry, I don't know what that means :(";
                System.out.println("\tI'm sorry, I don't know what that means :(");
                break;
            default:
                output = "An error occurred.";
                System.out.println("\tERROR!");
                break;
        }
        return output;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Bro.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBro(bro);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the Bro application.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bro().run();
    }
}
