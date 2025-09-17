package bro.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void constructor_setsDescriptionAndDates() {
        Event e = new Event("desc", "2/12/2019 0000", "2/12/2019 1000");
        assertEquals("desc", e.getDescription());
        assertNotNull(e.from);
        assertNotNull(e.to);
    }

    @Test
    public void toDataString_returnsCorrectFormat() {
        Event e = new Event("desc", true, "2/12/2019 0000", "2/12/2019 1000");
        assertTrue(e.toDataString().startsWith("E|1|desc|2/12/2019 0000|2/12/2019 1000"));
    }
}
