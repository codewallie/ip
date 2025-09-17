package bro.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void constructor_setsDescriptionAndDate() {
        Deadline d = new Deadline("desc", "2/12/2019 0000");
        assertEquals("desc", d.getDescription());
        assertNotNull(d.by);
    }

    @Test
    public void toDataString_returnsCorrectFormat() {
        Deadline d = new Deadline("desc", true, "2/12/2019 0000");
        assertTrue(d.toDataString().startsWith("D|1|desc|2/12/2019 0000"));
    }
}
