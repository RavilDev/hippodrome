import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Disabled
    @Test
    @Timeout(22)
    void main() throws Exception {
        Main.main(new String[]{});
    }
}