package bro.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bro.commands.Command;
import bro.commands.CommandError;

public class ParserTest {
    @Test
    public void getCommand_byeCommand_returnsByeCommand() {
        Command cmd = new Parser().getCommand("bye");
        assertEquals("bro.commands.ByeCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_listCommand_returnsListCommand() {
        Command cmd = new Parser().getCommand("list");
        assertEquals("bro.commands.ListCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_markCommand_valid_returnsMarkCommand() {
        Command cmd = new Parser().getCommand("mark 2");
        assertEquals("bro.commands.MarkCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_unmarkCommand_valid_returnsUnmarkCommand() {
        Command cmd = new Parser().getCommand("unmark 1");
        assertEquals("bro.commands.UnmarkCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_deleteCommand_valid_returnsDeleteCommand() {
        Command cmd = new Parser().getCommand("delete 3");
        assertEquals("bro.commands.DeleteCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_tasksOnCommand_valid_returnsTasksOnCommand() {
        Command cmd = new Parser().getCommand("tasks on 2/12/2019");
        assertEquals("bro.commands.TasksOnCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_todoCommand_valid_returnsTodoCommand() {
        Command cmd = new Parser().getCommand("todo something");
        assertEquals("bro.commands.TodoCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_findCommand_valid_returnsFindCommand() {
        Command cmd = new Parser().getCommand("find keyword");
        assertEquals("bro.commands.FindCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_unknownCommand_returnsUnknownCommand() {
        Command cmd = new Parser().getCommand("foobar");
        assertEquals("bro.commands.UnknownCommand", cmd.getClass().getName());
    }

    @Test
    public void getCommand_deadlineCommand_invalidDate_returnsCommandError() {
        Command cmd = new Parser().getCommand("deadline test /by 99/99/9999 9999");
        assertTrue(cmd instanceof CommandError);
    }
}
