package bro.tasks;
import java.util.ArrayList;

import bro.utils.FileIO;

/**
 * Manages an ArrayList of tasks, allowing addition, deletion, marking, unmarking, and listing of tasks.
 */
public class Tasks {
     private ArrayList<Task> tasks;
     private static final String HORIZONTAL_LINE = "\t_____________________________________\n";
     
     /**
      * Creates a new Tasks object with an empty task list.
      */
     public Tasks() {
        this.tasks = new ArrayList<>();
     }

     /**
      * Creates a new Tasks object with the given task list.
      * @param tasks The initial list of tasks.
      */
     public Tasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
     }

     /**
      * Marks the task at the supplied index as done.
      * @param input The input array where the second element is the task index.
      */
     public void markTask(String[] input) {
        try {
            Task task = tasks.get(
                Integer.parseInt(input[1]) - 1);
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

     /**
      * Marks the task at the supplied index as not done.
      * @param input The input array where the second element is the task index.
      */
     public void unmarkTask(String[] input) {
        try {
            Task task = tasks.get(
                Integer.parseInt(input[1]) - 1);
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

     /**
      * Adds a new task to the task list based on the command.
      * @param input The input array where the first element is the command and the subsequent elements are the task details.
      */
     public void addTask(String[] input) {
        String command = input[0];
        if (command.equals("todo")) {
            Todo todo = new Todo(input[1]);
            tasks.add(todo);
            FileIO.addEntry(todo.toDataString() + "\n");
        } else if (command.equals("deadline")) {
            Deadline deadline = new Deadline(input[1], input[2]);
            tasks.add(deadline);
            FileIO.addEntry(deadline.toDataString() + "\n");
        } else if (command.equals("event")) {
            Event event = new Event(input[1], input[2], input[3]);
            tasks.add(event);
            FileIO.addEntry(event.toDataString() + "\n");
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

     /**
      * Deletes the task at the supplied index from the task list.
      * @param input The input array where the second element is the task index.
      */
     public void deleteTask(String[] input) {
        try {
            Task task = tasks.get(Integer.parseInt(input[1]) - 1);
            String deletedTaskData = task.toDataString();
            tasks.remove(Integer.parseInt(input[1]) - 1);
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

    /**
     * Lists all tasks in the task list.
     */
     public void listTasks() {
        System.out.println(HORIZONTAL_LINE + "\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                String.format("\t%d. %s", 
                    i + 1, tasks.get(i)));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows all tasks that occur on the specified date.
     * @param input The input array where the second element is the date in d/M/yyyy format.
     */
    public void showTasksOn(String[] input) {
        String date = input[1];
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return sb.toString();
    }
}
