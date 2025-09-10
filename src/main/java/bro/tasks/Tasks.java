package bro.tasks;

import java.util.ArrayList;

import bro.utils.FileIo;

/**
 * Manages an ArrayList of tasks, allowing addition, deletion, marking,
 * unmarking, and listing of tasks.
 */
public class Tasks {
    private static final String HORIZONTAL_LINE = "\t_____________________________________\n";
    private ArrayList<Task> tasks;

    /**
     * Creates a new Tasks object with an empty task list.
     */
    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new Tasks object with the given task list.
     *
     * @param tasks The initial list of tasks.
     */
    public Tasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks the task at the supplied index as done.
     *
     * @param input The input array where the second element is the task index.
     * @return A message indicating the task has been marked as done.
     */
    public String markTask(String[] input) {
        try {
            Task task = tasks.get(
                    Integer.parseInt(input[1]) - 1);
            String output = task.markAsDone();
            FileIo.updateByDescription(task.toDataString());
            return output;

        } catch (IndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tInvalid task number!"
                    + HORIZONTAL_LINE);

        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tPlease provide a valid task number!"
                    + HORIZONTAL_LINE);

        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tERROR!"
                    + HORIZONTAL_LINE);
        }
        return "Error!";
    }

    /**
     * Marks the task at the supplied index as not done.
     *
     * @param input The input array where the second element is the task index.
     * @return A message indicating the task has been marked as not done.
     */
    public String unmarkTask(String[] input) {
        try {
            Task task = tasks.get(
                    Integer.parseInt(input[1]) - 1);
            String output = task.markAsUndone();
            FileIo.updateByDescription(task.toDataString());
            return output;

        } catch (IndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tInvalid task number!"
                    + HORIZONTAL_LINE);

            return "Invalid task number!";

        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tPlease provide a valid task number!"
                    + HORIZONTAL_LINE);

            return "Please provide a valid task number!";

        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tERROR!"
                    + HORIZONTAL_LINE);

            return "Error!";
        }
    }

    /**
     * Adds a new task to the task list based on the command.
     *
     * @param input The input array where the first element is the command and the
     *              subsequent elements are the task details.
     * @return A message indicating the task has been added.
     */
    public String addTask(String[] input) {
        String command = input[0];
        if (command.equals("todo")) {
            Todo todo = new Todo(input[1]);
            tasks.add(todo);
            FileIo.addEntry(todo.toDataString() + "\n");

        } else if (command.equals("deadline")) {
            Deadline deadline = new Deadline(input[1], input[2]);
            tasks.add(deadline);
            FileIo.addEntry(deadline.toDataString() + "\n");

        } else if (command.equals("event")) {
            Event event = new Event(input[1], input[2], input[3]);
            tasks.add(event);
            FileIo.addEntry(event.toDataString() + "\n");

        } else {
            System.out.println(HORIZONTAL_LINE
                    + "\tUnknown command!"
                    + HORIZONTAL_LINE);
            return "Unknown command!";
        }

        System.out.println(
                String.format(
                        "%s\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.%s",
                        HORIZONTAL_LINE,
                        tasks.get(tasks.size() - 1),
                        tasks.size(),
                        HORIZONTAL_LINE));

        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                tasks.get(tasks.size() - 1),
                tasks.size());
    }

    /**
     * Deletes the task at the supplied index from the task list.
     *
     * @param input The input array where the second element is the task index.
     * @return A message indicating the task has been deleted.
     */
    public String deleteTask(String[] input) {
        try {
            Task task = tasks.get(Integer.parseInt(input[1]) - 1);
            String deletedTaskData = task.toDataString();
            tasks.remove(Integer.parseInt(input[1]) - 1);
            FileIo.deleteByEntry(deletedTaskData);
            System.out.println(
                    String.format(
                            "%s\tNoted. I've removed this task:\n\t%s\n\tNow you have %d tasks in the list.%s",
                            HORIZONTAL_LINE,
                            task.toString(),
                            tasks.size(),
                            HORIZONTAL_LINE));

            return String.format(
                    "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                    task.toString(),
                    tasks.size());

        } catch (IndexOutOfBoundsException e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tInvalid task number!"
                    + HORIZONTAL_LINE);

            return "Invalid task number!";

        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tPlease provide a valid task number!"
                    + HORIZONTAL_LINE);

            return "Please provide a valid task number!";

        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE
                    + "\tERROR!"
                    + HORIZONTAL_LINE);

            return "Error!";
        }
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return A string representation of all tasks in the list.
     */
    public String listTasks() {
        System.out.println(HORIZONTAL_LINE + "\tHere are the tasks in your list:");
        String output = "Here are the tasks in your list:";

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                    String.format("\t%d. %s",
                            i + 1, tasks.get(i)));

            output += String.format("\n%d. %s",
                    i + 1, tasks.get(i));
        }

        System.out.println(HORIZONTAL_LINE);
        return output;
    }

    /**
     * Shows all tasks that occur on the specified date.
     *
     * @param input The input array where the second element is the date in d/M/yyyy
     *              format.
     * @return A message listing all tasks on the specified date.
     */
    public String showTasksOn(String[] input) {
        String date = input[1];
        if (date.isBlank()) {
            System.out.println(HORIZONTAL_LINE
                    + "\tPlease provide a date!"
                    + HORIZONTAL_LINE);
            return "Please provide a date!";
        }

        System.out.println(HORIZONTAL_LINE
                + "\tHere are the tasks on "
                + date + ":");

        String output = "Here are the tasks on " + date + ":";

        for (int i = 0; i < tasks.size(); i++) {
            output += getInfoIfTaskOn(date, i);
        }

        System.out.println(HORIZONTAL_LINE);
        return output;
    }

    /**
     * Helper method to get task info if it occurs on the specified date.
     *
     * @param date The date to check in the format "d/M/yyyy".
     * @param index The index of the task in the task list.
     * @return A string representation of the task if it occurs on the specified date, an empty string otherwise.
     */
    private String getInfoIfTaskOn(String date, int index) {
        String output = "";
        Task task = tasks.get(index);
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            if (deadline.isOnDate(date)) {
                System.out.println(
                        String.format("\t%d. %s", index + 1, deadline));

                output = String.format("\n%d. %s", index + 1, deadline);
            }
        } else if (task instanceof Event) {
            Event event = (Event) task;
            if (event.isOnDate(date)) {
                System.out.println(
                        String.format("\t%d. %s", index + 1, event));

                output = String.format("\n%d. %s", index + 1, event);
            }
        }
        return output;
    }

    /**
     * Finds and lists all tasks that contain the specified keyword in their
     * description.
     *
     * @param input The input array where the second element is the keyword to
     *              search for.
     */
    public String findTasks(String[] input) {
        String keyword = input[1];
        System.out.println(HORIZONTAL_LINE + "\tHere are the matching tasks in your list:");

        String output = "Here are the matching tasks in your list:";

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.description.contains(keyword)) {
                System.out.println(
                        String.format("\t%d. %s",
                                i + 1, task));

                output += String.format("\n%d. %s", i + 1, task);
            }
        }

        System.out.println(HORIZONTAL_LINE);
        return output;
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
