import java.util.Scanner;

public class Bro {
    private static final String name = "Bro";
    private static final String horizontalLine = "_____________________________________";
    private static final String exitCmd = "bye";

    public static void main(String[] args) {
        printHello();
        String input = readInput();
        while (input != null && !input.equals(exitCmd)) {
            printMsg(input);
            input = readInput();
        }
        printBye();
    }

    private static String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return "none";
        }
        return input;
    }

    private static void printMsg(String msg) {
        System.out.println(String.format(
                        "\t%s\n\t%s\n\t%s",
                        horizontalLine, msg, horizontalLine));
    }

    private static void printHello() {
        System.out.println(
                String.format(
                        "\t%s\n\tHello! I'm %s\n\tWhat can I do for you?\n\t%s",
                        horizontalLine, name, horizontalLine));
    }

    private static void printBye() {
        System.out.println(
                String.format(
                        "\t%s\n\tBye. Hope to see you again soon!\n\t%s",
                        horizontalLine, horizontalLine));
    }
}
