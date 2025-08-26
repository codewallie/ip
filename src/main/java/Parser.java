import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Parser() {

    }

    public String[] getCommandData(String input) {
        String[] commandData;
        if (input.equals("bye")) {
            commandData = new String[] {"bye"};
        } else if (input.equals("list")) {
            commandData = new String[] {"list"};
        } else if (input.startsWith("mark")) {
            commandData = new String[] {"mark", 
                input.replace("mark", "")
                .stripLeading()
            };
            if (commandData.length < 2 || commandData[1].isBlank()) {
                System.out.println("\tPlease provide a task number to mark!");
                return new String[] {"error"};
            }
        } else if (input.startsWith("unmark")) {
            commandData = new String[] {"unmark", 
                input.replace("unmark", "")
                .stripLeading()
            };
            if (commandData.length < 2 || commandData[1].isBlank()) {
                System.out.println("\tPlease provide a task number to unmark!");
                return new String[] {"error"};
            }
        } else if (input.startsWith("delete")) {
            commandData = new String[] {"delete", 
                input.replace("delete", "")
                .stripLeading()
            };
            if (commandData.length < 2 || commandData[1].isBlank()) {
                System.out.println("\tPlease provide a task number to delete!");
                return new String[] {"error"};
            }
        } else if (input.startsWith("tasks on")) {
            commandData = new String[] {"tasks on", 
                input.replace("tasks on", "")
                .stripLeading()
            };
            if (!dateTimeIsValid(commandData[1] + " 0000")) {
                System.out.println("\tPlease provide a date in the format: d/M/yyyy");
                return new String[] {"error"};
            }
        } else if (input.startsWith("todo")) {
            input = input.replace("todo", "").stripLeading();
            if (input.isBlank()) {
                System.out.println("\tPlease provide a description for the todo!");
                return new String[] {"error"};
            }
            commandData = new String[] {"todo", input};
        } else if (input.startsWith("deadline")) {
            String[] inputParts = input.replace("deadline", "")
                .stripLeading()
                .split(" /by ");
            if (inputParts[0].isBlank()) {
                System.out.println("\tPlease provide a description for the deadline!");
                return new String[] {"error"};
            } else if (inputParts.length < 2) {
                System.out.println("\tPlease provide a deadline!");
                return new String[] {"error"};
            } else if (!dateTimeIsValid(inputParts[1])) {
                System.out.println("\tPlease provide a deadline in the format: d/M/yyyy HHmm");
                return new String[] {"error"};
            }
            commandData = new String[] {"deadline", inputParts[0], inputParts[1]};
        } else if (input.startsWith("event")) {
            String[] inputParts = input.replace("event", "")
                .stripLeading()
                .split(" /from ");
            if (inputParts[0].isBlank()) {
                System.out.println("\tPlease provide a description for the event!");
                return new String[] {"error"};
            } else if (inputParts.length < 2) {
                System.out.println("\tPlease provide an event time range!");
                return new String[] {"error"};
            }

            String[] fromTo = inputParts[1].split(" /to ");
            if (fromTo.length < 2) {
                System.out.println("\tPlease provide both start and end times for the event!");
                return new String[] {"error"};
            } else if (!dateTimeIsValid(fromTo[0]) || !dateTimeIsValid(fromTo[1])) {
                System.out.println("\tPlease provide event times in the format: d/M/yyyy HHmm");
                return new String[] {"error"};
            }
            commandData = new String[] {"event", inputParts[0], fromTo[0], fromTo[1]};
        } else {
            commandData = new String[] {"unknown"};
        }
        return commandData;
    }

    private boolean dateTimeIsValid(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
