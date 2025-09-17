package bro.commands;

import bro.tasks.Tasks;

public class UnknownCommand extends Command {
    /**
     * Creates a new UnknownCommand.
     */
    public UnknownCommand() {
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        return "I'm sorry, but I don't know what that means!";
    }
    
}
