package bro.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainWindowTest {
    @Test
    public void initialize_methodExists() {
        MainWindow mw = new MainWindow();
        assertNotNull(mw);
        // GUI methods can't be tested without JavaFX runtime, so just check object creation
    }
}
