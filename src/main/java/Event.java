import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    public String toDataString() {
        return String.format("E|%d|%s|%s|%s", 
            (isDone ? 1 : 0), 
            description,
            from.format(formatter),
            to.format(formatter));
    }
    
    @Override
    public String toString() {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        return String.format("[E] [%s] %s (from: %s to: %s)", 
            getStatusIcon(), 
            description,
            from.format(printFormat),
            to.format(printFormat));
    }
}