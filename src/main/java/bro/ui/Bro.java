package bro.ui;

import bro.tasks.Tasks;
import bro.utils.FileIO;

/**
 * The main class for the Bro application, responsible for initializing and running the program.
 */
public class Bro {
    private Ui ui;

    /**
     * Initializes the Bro application by setting up the user interface, the user input parser and loading existing tasks from storage.
     */
    public Bro() {
        try {
            this.ui = new Ui(
                new Tasks(FileIO.loadData())
            );
        } catch (Exception e) {
            System.out.println("Error initializing Bro.");
        }
    }

    /**
     * Starts the Bro application, allowing user interaction.
     */
    public void run() {
        ui.run();
    }

    /**
     * The main method to launch the Bro application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bro().run();
    }
}
