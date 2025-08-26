import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, formatter);
    }

    public String toDataString() {
        return String.format("D|%d|%s|%s", 
            (isDone ? 1 : 0), 
            description,
            by.format(formatter));
    }
    
    @Override
    public String toString() {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");
        return String.format("[D] [%s] %s (by: %s)", 
            getStatusIcon(), 
            description,
            by.format(printFormat));
    }
}