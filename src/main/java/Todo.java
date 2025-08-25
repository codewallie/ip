public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toDataString() {
        return String.format("T|%d|%s", 
            (isDone ? 1 : 0), 
            description);
    }

    @Override
    public String toString() {
        return String.format("[T] [%s] %s", 
            getStatusIcon(), 
            description);
    }
}
