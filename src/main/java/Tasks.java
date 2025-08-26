import java.util.ArrayList;

public class Tasks {
     private ArrayList<Task> tasks;
     private static final String HORIZONTAL_LINE = "\t_____________________________________\n";
     
     public Tasks() {
        this.tasks = new ArrayList<>();
     }

     public Tasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
     }

     public void markTask(String input) {
        try {
            Task task = tasks.get(
                Integer.parseInt(
                    input.replace("mark ", ""))
                    - 1);
            task.markAsDone();
            FileIO.updateByDescription(task.toDataString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL_LINE + "\tInvalid task number!" + HORIZONTAL_LINE);
        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE + "\tPlease provide a valid task number!" + HORIZONTAL_LINE);
        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE + "\tERROR!" + HORIZONTAL_LINE);
        }
     }

     public void unmarkTask(String input) {
        try {
            Task task = tasks.get(
                Integer.parseInt(
                    input.replace("unmark ", ""))
                    - 1);
            task.markAsUndone();
            FileIO.updateByDescription(task.toDataString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL_LINE + "\tInvalid task number!" + HORIZONTAL_LINE);
        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE + "\tPlease provide a valid task number!" + HORIZONTAL_LINE);
        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE + "\tERROR!" + HORIZONTAL_LINE);
        }
     }

     public void addTask(String input) {
        if (input.startsWith("todo")) {
            input = input.replace("todo", "").stripLeading();
            if (input.isBlank()) {
                System.out.println("\tPlease provide a description for the todo!");
                return;
            }
            tasks.add(new Todo(input));
            FileIO.addEntry("T|0|" + input + "\n");

        } else if (input.startsWith("deadline")) {
            String[] inputParts = input.replace("deadline", "")
                .stripLeading()
                .split(" /by ");
            if (inputParts[0].isBlank()) {
                System.out.println("\tPlease provide a description for the deadline!");
                return;
            } else if (inputParts.length < 2) {
                System.out.println("\tPlease provide a deadline!");
                return;
            }
            Deadline deadline = new Deadline(inputParts[0], inputParts[1]);
            if (deadline.toString().isBlank()) {
                return;
            }
            tasks.add(deadline);
            FileIO.addEntry("D|0|" + inputParts[0] + "|" + inputParts[1] + "\n");

        } else if (input.startsWith("event")) {
            String[] inputParts = input.replace("event", "")
                .stripLeading()
                .split(" /from ");
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
            Event event = new Event(inputParts[0], fromTo[0], fromTo[1]);
            if (event.toString().isBlank()) {
                return;
            }
            tasks.add(event);
            FileIO.addEntry("E|0|" + inputParts[0] + "|" + fromTo[0] + "|" + fromTo[1] + "\n");

        } else {
            System.out.println(HORIZONTAL_LINE + "\tUnknown command!" + HORIZONTAL_LINE);
            return;
        }
        
        System.out.println(
            String.format(
                "%s\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.%s",
                HORIZONTAL_LINE, 
                tasks.get(tasks.size() - 1), 
                tasks.size(),
                HORIZONTAL_LINE));
     }

     public void deleteTask(String input) {
        input = input.replace("delete", "").replaceFirst(" ", "");
        if (input.isBlank()) {
            System.out.println(HORIZONTAL_LINE + "\tPlease provide a task number to delete!" + HORIZONTAL_LINE);
            return;
        }
        try {
            Task task = tasks.get(Integer.parseInt(input) - 1);
            String deletedTaskData = task.toDataString();
            tasks.remove(Integer.parseInt(input) - 1);
            FileIO.deleteByEntry(deletedTaskData);
            System.out.println(
                String.format(
                    "%s\tNoted. I've removed this task:\n\t%s\n\tNow you have %d tasks in the list.%s",
                    HORIZONTAL_LINE, task.toString(), tasks.size(), HORIZONTAL_LINE));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL_LINE + "\tInvalid task number!" + HORIZONTAL_LINE);
        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE + "\tPlease provide a valid task number!" + HORIZONTAL_LINE);
        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE + "\tERROR!" + HORIZONTAL_LINE);
        }
     }

     public void listTasks() {
        System.out.println(HORIZONTAL_LINE + "\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                String.format("\t%d. %s", 
                    i + 1, tasks.get(i)));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTasksOn(String input) {
        String date = input.replace("show tasks on", "")
            .stripLeading();
        if (date.isBlank()) {
            System.out.println(HORIZONTAL_LINE + "\tPlease provide a date!" + HORIZONTAL_LINE);
            return;
        }
        System.out.println(HORIZONTAL_LINE + "\tHere are the tasks on " + date + ":");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isOnDate(date)) {
                    System.out.println(
                        String.format("\t%d. %s", 
                            i + 1, deadline));
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isOnDate(date)) {
                    System.out.println(
                        String.format("\t%d. %s", 
                            i + 1, event));
                }
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
