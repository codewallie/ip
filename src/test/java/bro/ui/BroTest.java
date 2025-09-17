package bro.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BroTest {
    @Test
    public void bro_constructor_initializesWithoutException() {
        assertDoesNotThrow(() -> new Bro());
    }
}
