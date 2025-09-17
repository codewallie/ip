package bro.commands;

import bro.tasks.Task;
import bro.tasks.Tasks;
import bro.utils.FileIo;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Creates a new UnmarkCommand.
     */
    @Override
    public String execute(Tasks tasks) {
        try {
            Task task = tasks.getEntry(taskIndex);
            String output = task.markAsUndone();
            FileIo.updateByDescription(task.toDataString());
            return output;
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number!";
        } catch (NumberFormatException e) {
            return "Please provide a valid task number!";
        } catch (Exception e) {
            return "Error!";
        }
    }
    
}
