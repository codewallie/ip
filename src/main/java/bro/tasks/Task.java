package bro.tasks;

/**
 * Represents a task that has a description and a status (done or not done).
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description. The task is initially not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new Task with the given description and status.
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        printLine();
        if (isDone) {
            System.out.println("\tThis task is already marked as done!");
            printLine();
            return;
        }
        isDone = true;
        
        System.out.println(
            String.format(
                "\tNice! I've marked this task as done:\n\t\t%s",
                toString()));
        printLine();
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        printLine();
        if (!isDone) {
            System.out.println("\tThis task is already marked as not done!");
            printLine();
            return;
        }
        isDone = false;

        System.out.println(
            String.format(
                "\tOK, I've marked this task as not done yet:\n\t\t%s",
                toString()));
        printLine();
    }

    /**
     * Converts the Task to a data string for storage.
     * @return A string representation of the Task for storage.
     */
    public String toDataString() {
        return String.format("|%d|%s", 
            (isDone ? 1 : 0), 
            description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", 
            getStatusIcon(), 
            description);
    }

    private void printLine() {
        System.out.println("\t_____________________________________");
    }
}