package bro.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UiTest {
    @Test
    public void printHello_returnsCorrectMessage() {
        String msg = Ui.printHello();
        assertTrue(msg.contains("Hello! I'm Bro"));
        assertTrue(msg.contains("What can I do for you?"));
    }

    @Test
    public void printBye_returnsCorrectMessage() {
        Ui ui = new Ui();
        String msg = ui.printBye();
        assertEquals("Bye. Hope to see you again soon!", msg);
    }
}
