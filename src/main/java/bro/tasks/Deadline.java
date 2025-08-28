package bro.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (Exception e) {
            System.out.println("\tError parsing date/time. Please use the format: d/M/yyyy HHmm");
        }
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (Exception e) {
            System.out.println("\tError parsing date/time. Please use the format: d/M/yyyy HHmm");
        }
    }

    public boolean isOnDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate checkDate = LocalDate.parse(date, dateFormatter);
            return by.toLocalDate().isEqual(checkDate);        
        } catch (Exception e) {
            System.out.println("\tError parsing date. Please use the format: d/M/yyyy");
            return false;
        }
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
        try {
            return String.format("[D] [%s] %s (by: %s)", 
                getStatusIcon(), 
                description,
                by.format(printFormat));
        } catch (Exception e) {
            System.out.println("\tError parsing date. Please use the format: d/M/yyyy");
            return "";
        }
    }
}