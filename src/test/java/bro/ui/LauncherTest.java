package bro.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LauncherTest {
    @Test
    public void main_runsWithoutException() {
        assertDoesNotThrow(() -> Launcher.main(new String[]{}));
    }
}
