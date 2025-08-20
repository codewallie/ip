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
            printLine();
            System.out.println("\tInvalid task number!");
            printLine();
            return false;
        } catch (NumberFormatException e) {
            printLine();
            System.out.println("\tPlease provide a valid task number!");
            printLine();
            return false;
        } catch (Exception e) {
            printLine();
            System.out.println("\tERROR!");
            printLine();
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
            printLine();
            System.out.println("\tInvalid task number!");
            printLine();
            return false;
        } catch (NumberFormatException e) {
            printLine();
            System.out.println("\tPlease provide a valid task number!");
            printLine();
            return false;
        } catch (Exception e) {
            printLine();
            System.out.println("\tERROR!");
            printLine();
            return false;
        }
     }

     public void addTask(String input) {
        if (input.startsWith("todo")) {
            input = input.replace("todo", "");
            if (input.isBlank()) {
                System.out.println("\tPlease provide a description for the todo!");
                return;
            }
            tasks.add(new Todo(input));
        } else if (input.startsWith("deadline")) {
            String[] inputParts = input.replace("deadline", "").split(" /by ");
            inputParts[0] = inputParts[0].replaceFirst(" ", "");
            if (inputParts[0].isBlank()) {
                System.out.println("\tPlease provide a description for the deadline!");
                return;
            } else if (inputParts.length < 2) {
                System.out.println("\tPlease provide a deadline!");
                return;
            }
            tasks.add(new Deadline(inputParts[0], inputParts[1]));

        } else if (input.startsWith("event")) {
            String[] inputParts = input.replace("event", "").split(" /from ");
            inputParts[0] = inputParts[0].replaceFirst(" ", "");
            if (inputParts[0].isBlank()) {
                System.out.println("\tPlease provide a description for the event!");
                return;
            } else if (inputParts.length < 2) {
                System.out.println("\tPlease provide an event time range!");
                return;
            }

            String[] fromTo = inputParts[1].split(" /to ");
            if (fromTo.length < 2) {
                System.out.println("\tPlease provide both start and end times for the event!");
                return;
            }
            tasks.add(new Event(inputParts[0], fromTo[0], fromTo[1]));
        } else {
            printLine();
            System.out.println("\tUnknown command!");
            printLine();
            return;
        }
        
        printLine();
        System.out.println(
            String.format(
                "\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.",
                tasks.get(tasks.size() - 1), tasks.size()));
        printLine();
     }

     public void deleteTask(String input) {
        input = input.replace("delete", "").replaceFirst(" ", "");
        if (input.isBlank()) {
            System.out.println("\tPlease provide a task number to delete!");
            return;
        }
        printLine();
        try {
            String deletedTaskInfo = tasks.get(Integer.parseInt(input) - 1).toString();
            tasks.remove(Integer.parseInt(input) - 1);
            System.out.println(
                String.format(
                    "\tNoted. I've removed this task:\n\t%s\n\tNow you have %d tasks in the list.",
                    deletedTaskInfo, tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tInvalid task number!");
        } catch (NumberFormatException e) {
            System.out.println("\tPlease provide a valid task number!");
        } catch (Exception e) {
            System.out.println("\tERROR!");
        }
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
