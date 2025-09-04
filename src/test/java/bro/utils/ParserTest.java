package bro.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void getCommandData_markWithoutNumber_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("mark")[0]);
    }

    @Test
    public void getCommandData_unmarkWithoutNumber_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("unmark")[0]);
    }

    @Test
    public void getCommandData_validDeadline_success() {
        assertEquals("deadline",
                new Parser()
                        .getCommandData("deadline return book /by 2/12/2019 0000")[0]);
    }

    @Test
    public void getCommandData_deadlineWithoutTime_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("deadline return book /by 2/12/2019")[0]);
    }

    @Test
    public void getCommandData_deadlineInvalidDate_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("deadline return book /by 60/12/2019 1000")[0]);
    }

    @Test
    public void getCommandData_deadlineWithoutBy_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("deadline return book 2/12/2019 1000")[0]);
    }

    @Test
    public void getCommandData_validEvent_success() {
        assertEquals("event",
                new Parser()
                        .getCommandData("event return book /from 2/12/2019 0000 /to 2/12/2019 1000")[0]);
    }

    @Test
    public void getCommandData_eventWithoutFrom_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("event return book")[0]);
    }

    @Test
    public void getCommandData_eventWithoutTo_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("event return book /from 2/12/2019 1000")[0]);
    }

    @Test
    public void getCommandData_eventWithoutCommand_failed() {
        assertEquals("error",
                new Parser()
                        .getCommandData("deadline return book 2/12/2019 0000 2/12/2019")[0]);
    }
}
