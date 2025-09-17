package bro.commands;

import bro.tasks.Tasks;

public class ByeCommand extends Command {
    /**
     * Creates a new ByeCommand.
     */
    public ByeCommand() {
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        return "Goodbye! Hope to see you again soon!";
    }
    
}
