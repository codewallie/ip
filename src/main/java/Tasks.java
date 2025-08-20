import java.util.ArrayList;

public class Tasks {
     private ArrayList<Task> tasks;
     
     public Tasks() {
        this.tasks = new ArrayList<>();
     }

     public boolean markTask(String input) {
        try {
         tasks.get(
            Integer.parseInt(
                input.replace("mark ", ""))
                - 1)
            .markAsDone();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tInvalid task number!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("\tPlease provide a valid task number!");
            return false;
        }
     }

     public boolean unmarkTask(String input) {
        try {
            tasks.get(
                Integer.parseInt(
                    input.replace("unmark ", ""))
                    - 1)
                .markAsUndone();
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tInvalid task number!");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("\tPlease provide a valid task number!");
            return false;
        }
     }

     public void addTask(String input) {
        if (input.startsWith("todo ")) {
            tasks.add(new Todo(input.replace("todo ", "")));
        } else if (input.startsWith("deadline ")) {
            String[] inputParts = input.replace("deadline ", "").split(" /by ");
            tasks.add(new Deadline(inputParts[0], inputParts[1]));
        } else if (input.startsWith("event ")) {
            String[] inputParts = input.replace("event ", "").split(" /from ");
            String[] fromTo = inputParts[1].split(" /to ");
            tasks.add(new Event(inputParts[0], fromTo[0], fromTo[1]));
        } else {
            System.out.println("\tUnknown command!");
            return;
        }
        
        printLine();
        System.out.println(
            String.format(
                "\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.",
                tasks.get(tasks.size() - 1), tasks.size()));
        printLine();
     }

     public void listTasks() {
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                String.format("\t%d. %s", 
                    i + 1, tasks.get(i)));
        }
        printLine();
    }

    private void printLine() {
        System.out.println("\t_____________________________________");
    }
}
