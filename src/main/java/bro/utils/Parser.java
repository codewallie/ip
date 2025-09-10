package bro.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input commands and extracts relevant data.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Parses the user input and extracts the command and its associated data.
     *
     * @param input The user input string.
     * @return An array of strings where the first element is the command and the
     *         subsequent elements are the associated data.
     */
    public String[] getCommandData(String input) {
        String[] commandData;
        if (input.equals("bye")) {
            commandData = new String[] { "bye" };

        } else if (input.equals("list")) {
            commandData = new String[] { "list" };

        } else if (input.startsWith("mark")) {
            commandData = parseMarkCommand(input);

        } else if (input.startsWith("unmark")) {
            commandData = parseUnmarkCommand(input);

        } else if (input.startsWith("delete")) {
            commandData = parseDeleteCommand(input);

        } else if (input.startsWith("tasks on")) {
            commandData = parseTasksOnCommand(input);

        } else if (input.startsWith("todo")) {
            commandData = parseTodoCommand(input);

        } else if (input.startsWith("deadline")) {
            commandData = parseDeadlineCommand(input);

        } else if (input.startsWith("event")) {
            commandData = parseEventCommand(input);

        } else {
            commandData = new String[] { "unknown" };
        }

        assert commandData != null : "commandData should not be null";
        return commandData;
    }

    private String[] parseMarkCommand(String input) {
        String[] commandData = new String[] { "mark",
                input.replace("mark", "")
                        .stripLeading()
        };
        if (commandData.length < 2 || commandData[1].isBlank()) {
            System.out.println("\tPlease provide a task number to mark!");
            return new String[] { "error", "Please provide a task number to mark!" };
        }
        return commandData;
    }

    private String[] parseUnmarkCommand(String input) {
        String[] commandData = new String[] { "unmark",
                input.replace("unmark", "")
                        .stripLeading()
        };
        if (commandData.length < 2 || commandData[1].isBlank()) {
            System.out.println("\tPlease provide a task number to unmark!");
            return new String[] { "error", "Please provide a task number to unmark!" };
        }
        return commandData;
    }

    private String[] parseDeleteCommand(String input) {
        String[] commandData = new String[] { "delete",
                input.replace("delete", "")
                        .stripLeading()
        };
        if (commandData.length < 2 || commandData[1].isBlank()) {
            System.out.println("\tPlease provide a task number to delete!");
            return new String[] { "error", "Please provide a task number to delete!" };
        }
        return commandData;
    }

    private String[] parseTasksOnCommand(String input) {
        String[] commandData = new String[] { "tasks on",
                input.replace("tasks on", "")
                        .stripLeading()
        };
        if (!dateTimeIsValid(commandData[1] + " 0000")) {
            System.out.println("\tPlease provide a date in the format: d/M/yyyy");
            return new String[] { "error", "Please provide a date in the format: d/M/yyyy" };
        }
        return commandData;
    }

    private String[] parseTodoCommand(String input) {
        input = input.replace("todo", "").stripLeading();
        if (input.isBlank()) {
            System.out.println("\tPlease provide a description for the todo!");
            return new String[] { "error", "Please provide a description for the todo!" };
        }
        String[] commandData = new String[] { "todo", input };
        return commandData;
    }

    private String[] parseDeadlineCommand(String input) {
        String[] inputParts = input.replace("deadline", "")
                .stripLeading()
                .split(" /by ");
        if (inputParts[0].isBlank()) {
            System.out.println("\tPlease provide a description for the deadline!");
            return new String[] { "error", "Please provide a description for the deadline!" };
        } else if (inputParts.length < 2) {
            System.out.println("\tPlease provide a deadline!");
            return new String[] { "error", "Please provide a deadline!" };
        } else if (!dateTimeIsValid(inputParts[1])) {
            System.out.println("\tPlease provide a deadline in the format: d/M/yyyy HHmm");
            return new String[] { "error", "Please provide a deadline in the format: d/M/yyyy HHmm" };
        }
        String[] commandData = new String[] { "deadline", inputParts[0], inputParts[1] };
        return commandData;
    }

    private String[] parseEventCommand(String input) {
        String[] inputParts = input.replace("event", "")
                .stripLeading()
                .split(" /from ");
        if (inputParts[0].isBlank()) {
            System.out.println("\tPlease provide a description for the event!");
            return new String[] { "error", "Please provide a description for the event!" };
        } else if (inputParts.length < 2) {
            System.out.println("\tPlease provide an event time range!");
            return new String[] { "error", "Please provide an event time range!" };
        }

        String[] fromTo = inputParts[1].split(" /to ");
        if (fromTo.length < 2) {
            System.out.println("\tPlease provide both start and end times for the event!");
            return new String[] { "error", "Please provide both start and end times for the event!" };
        } else if (!dateTimeIsValid(fromTo[0]) || !dateTimeIsValid(fromTo[1])) {
            System.out.println("\tPlease provide event times in the format: d/M/yyyy HHmm");
            return new String[] { "error", "Please provide event times in the format: d/M/yyyy HHmm" };
        }
        String[] commandData = new String[] { "event", inputParts[0], fromTo[0], fromTo[1] };
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
