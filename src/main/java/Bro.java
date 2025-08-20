import java.util.Scanner;
import java.util.ArrayList;

public class Bro {
    private static final String name = "Bro";
    private static final String horizontalLine = "_____________________________________";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        printHello();
        String input = readInput();
        while (input != null) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printTasks();
            } else if (input.startsWith("mark ")) {
                tasks.get(
                    Integer.parseInt(
                        input.replace("mark ", ""))
                        - 1)
                    .markAsDone();
            } else if (input.startsWith("unmark ")) {
                tasks.get(
                    Integer.parseInt(
                        input.replace("unmark ", ""))
                        - 1)
                    .markAsUndone();
            } else {
                tasks.add(new Task(input));
                printMsg(input);
            }
            
            input = readInput();
        }
        printBye();
    }

    private static String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return (input.isBlank() ? "none" : input);
    }

    private static void printTasks() {
        System.out.println(
            String.format("\t%s\n\tHere are the tasks in your list:", 
                horizontalLine));
        
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                String.format("\t%d. %s", 
                    i + 1, tasks.get(i)));
        }
        
        System.out.println(
            String.format("\t%s", 
                horizontalLine));
    }

    private static void printMsg(String msg) {
        System.out.println(String.format(
                        "\t%s\n\tadded: %s\n\t%s",
                        horizontalLine, 
                        msg, 
                        horizontalLine));
    }

    private static void printHello() {
        System.out.println(
                String.format(
                        "\t%s\n\tHello! I'm %s\n\tWhat can I do for you?\n\t%s",
                        horizontalLine, 
                        name, 
                        horizontalLine));
    }

    private static void printBye() {
        System.out.println(
                String.format(
                        "\t%s\n\tBye. Hope to see you again soon!\n\t%s",
                        horizontalLine, 
                        horizontalLine));
    }
}
