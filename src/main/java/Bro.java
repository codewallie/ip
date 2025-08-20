public class Bro {
    private static final String name = "Bro";
    private static final String horizontalLine = "_____________________________________";

    public static void main(String[] args) {
        printHello();
        printBye();
    }

    private static void printHello() {
        System.out.println(
                String.format(
                        "%s\nHello! I'm %s\nWhat can I do for you?",
                        horizontalLine, name));
    }

    private static void printBye() {
        System.out.println(
                String.format(
                        "%s\nBye. Hope to see you again soon!\n%s",
                        horizontalLine, horizontalLine));
    }
}
