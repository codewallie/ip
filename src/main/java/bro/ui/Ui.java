package bro.ui;

import java.util.Scanner;

public class Ui {
    private static final String NAME = "Bro";
    private static final String HORIZONTAL_LINE = "_____________________________________";
    private static Scanner scanner = new Scanner(System.in);

    public String readInput() {
        String input = scanner.nextLine();
        return (input.isBlank() ? "blank" : input);
    }

    public void printHello() {
        System.out.println(
                String.format(
                        "\t%s\n\tHello! I'm %s\n\tWhat can I do for you?\n\t%s",
                        HORIZONTAL_LINE, 
                        NAME, 
                        HORIZONTAL_LINE));
    }

    public void printBye() {
        System.out.println(
                String.format(
                        "\t%s\n\tBye. Hope to see you again soon!\n\t%s",
                        HORIZONTAL_LINE, 
                        HORIZONTAL_LINE));
    }

}
