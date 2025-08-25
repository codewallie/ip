public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String toDataString() {
        return String.format("E|%d|%s|%s|%s", 
            (isDone ? 1 : 0), 
            description,
            from,
            to);
    }
    
    @Override
    public String toString() {
        return String.format("[E] [%s] %s (from: %s to: %s)", 
            getStatusIcon(), 
            description,
            from,
            to);
    }
}