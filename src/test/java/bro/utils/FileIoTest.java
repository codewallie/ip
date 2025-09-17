package bro.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import bro.tasks.Task;

public class FileIoTest {
    @Test
    public void loadData_returnsList() {
        ArrayList<Task> tasks = FileIo.loadData();
        assertNotNull(tasks);
        assertTrue(tasks instanceof ArrayList);
    }

    @Test
    public void addEntry_andDeleteEntry_doNotThrow() {
        assertDoesNotThrow(() -> FileIo.addEntry("T|0|testEntry"));
        assertDoesNotThrow(() -> FileIo.deleteByEntry("T|0|testEntry"));
    }
}
