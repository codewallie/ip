package bro.ui;

import java.util.Scanner;

/**
 * Handles user interactions, including reading input and displaying messages.
 */
public class Ui {
    private static final String NAME = "Bro";
    private static final String HORIZONTAL_LINE = "_____________________________________";
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Reads a line of input from the user.
     * @return The input string, or "blank" if the input is empty.
     */
    public String readInput() {
        String input = scanner.nextLine();
        return (input.isBlank() ? "blank" : input);
    }

    /**
     * Prints the welcome message when the application starts.
     */
    public void printHello() {
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
    public void printBye() {
        System.out.println(
                String.format(
                        "\t%s\n\tBye. Hope to see you again soon!\n\t%s",
                        HORIZONTAL_LINE, 
                        HORIZONTAL_LINE));
    }

}
