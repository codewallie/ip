package bro.ui;

import bro.tasks.Tasks;
import bro.utils.FileIo;
import bro.utils.Parser;

/**
 * The main class for the Bro application, responsible for initializing and running the program.
 */
public class Bro {
    private Ui ui;
    private Parser parser;
    private Tasks tasks;

    /**
     * Initializes the Bro application by setting up the user interface, the user input parser and loading existing tasks from storage.
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

    /**
     * The main method to launch the Bro application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bro().run();
    }
}
