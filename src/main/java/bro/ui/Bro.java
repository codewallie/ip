package bro.ui;

import java.io.IOException;

import bro.tasks.Tasks;
import bro.utils.FileIo;
import bro.utils.Parser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import bro.commands.Command;

/**
 * The main class for the Bro application, responsible for initializing and
 * running the program.
 */
public class Bro extends Application {
    private Parser parser;
    private Tasks tasks;

    private Bro bro = this;

    /**
     * Initializes the Bro application by setting up the user interface, the user
     * input parser and loading existing tasks from storage.
     */
    public Bro() {
        try {
            this.parser = new Parser();
            this.tasks = new Tasks(FileIo.loadData());
        } catch (Exception e) {
            System.out.println("Error initializing Bro.");
        }
    }

    /**
     * Starts the Bro application, allowing user interaction.
     */
    public String getResponse(String input) {
        Command command = parser.getCommand(input);
        return command.execute(tasks);
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

    }
}
