public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String toDataString() {
        return String.format("D|%d|%s|%s", 
            (isDone ? 1 : 0), 
            description,
            by);
    }
    
    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", 
            getStatusIcon(), 
            description,
            by);
    }
}