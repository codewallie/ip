package bro.ui;
import java.util.Scanner;

import bro.utils.Parser;

import bro.tasks.Tasks;

/**
 * Handles user interactions, including reading input and displaying messages.
 */
public class Ui {
    private static final String NAME = "Bro";
    private static final String HORIZONTAL_LINE = "_____________________________________";
    private static Scanner scanner = new Scanner(System.in);
    private Parser parser;
    private Tasks tasks;

    public Ui(Tasks tasks) {
        this.parser = new Parser();
        this.tasks = tasks;
    }

    public void run() {
        printHello();
        String input = readInput();
        while (input != null) {
            String[] commandData = parser.getCommandData(input);
            String command = commandData[0];
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                tasks.listTasks();
            } else if (command.equals("mark")) {
                tasks.markTask(commandData);
            } else if (command.equals("unmark")) {
                tasks.unmarkTask(commandData);
            } else if (command.equals("delete")) {
                tasks.deleteTask(commandData);
            } else if (command.equals("tasks on")) {
                tasks.showTasksOn(commandData);
            } else if (command.equals("todo")
                || command.equals("deadline")
                || command.equals("event")) {
                tasks.addTask(commandData);
            } else if (command.equals("error")) {
                // do nothing, error message already printed in Parser
            } else if (commandData[0].equals("unknown")) {
                System.out.println("\tI'm sorry, I don't know what that means :(");
            } else {
                System.out.println("\tERROR!");
            }
            input = readInput();
        }
        printBye();
    }

    /**
     * Reads a line of input from the user.
     * @return The input string, or "blank" if the input is empty.
     */
    private static String readInput() {
        String input = scanner.nextLine();
        return (input.isBlank() ? "blank" : input);
    }

    /**
     * Prints the welcome message when the application starts.
     */
    private static void printHello() {
        System.out.println(
                String.format(
                        "\t%s\n\tHello! I'm %s\n\tWhat can I do for you?\n\t%s",
                        HORIZONTAL_LINE, 
                        NAME, 
                        HORIZONTAL_LINE));
    }

    /**
     * Prints the goodbye message when the application ends.
     */
    private static void printBye() {
        System.out.println(
                String.format(
                        "\t%s\n\tBye. Hope to see you again soon!\n\t%s",
                        HORIZONTAL_LINE, 
                        HORIZONTAL_LINE));
    }

}
