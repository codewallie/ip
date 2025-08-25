import java.util.Scanner;

public class Bro {
    private static final String NAME = "Bro";
    private static final String HORIZONTAL_LINE = "_____________________________________";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Tasks tasks = new Tasks(FileIO.loadData());

        printHello();
        String input = readInput();
        while (input != null) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                tasks.listTasks();
            } else if (input.startsWith("mark")) {
                tasks.markTask(input);
            } else if (input.startsWith("unmark")) {
                tasks.unmarkTask(input);
            } else if (input.startsWith("delete")) {
                tasks.deleteTask(input);
            } else {
                tasks.addTask(input);
            }
            
            input = readInput();
        }
        printBye();
    }

    private static String readInput() {
        String input = scanner.nextLine();
        return (input.isBlank() ? "none" : input);
    }

    private static void printHello() {
        System.out.println(
                String.format(
                        "\t%s\n\tHello! I'm %s\n\tWhat can I do for you?\n\t%s",
                        HORIZONTAL_LINE, 
                        NAME, 
                        HORIZONTAL_LINE));
    }

    private static void printBye() {
        System.out.println(
                String.format(
                        "\t%s\n\tBye. Hope to see you again soon!\n\t%s",
                        HORIZONTAL_LINE, 
                        HORIZONTAL_LINE));
    }
}
