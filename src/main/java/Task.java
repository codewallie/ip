public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
        System.out.println(
            String.format(
                "\tNice! I've marked this task as done:\n\t\t%s",
                toString()));
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println(
            String.format(
                "\tOK, I've marked this task as not done yet:\n\t\t%s",
                toString()));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", 
            getStatusIcon(), 
            description);
    }
}