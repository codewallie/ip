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

     public void listTasks() {
        System.out.println(HORIZONTAL_LINE + "\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                String.format("\t%d. %s", 
                    i + 1, tasks.get(i)));
        }
        System.out.println(HORIZONTAL_LINE);
    }

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
}
