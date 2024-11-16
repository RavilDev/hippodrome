import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    private Horse horse;

    @BeforeEach
    void setUp(){
        horse = new Horse("Saharok", 1, 1);
    }

    @Test
    void testFirstConstructorParameterForNull() {
        IllegalArgumentException exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1, 1);
        });
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "' ', 30, 10",
            "'\t', 35, 20",
            "'', 40, 30"
    })
    void testFirstConstructorParameterForSpace(String name, int speed, int distance ) {
        IllegalArgumentException exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            new Horse(name, speed, distance);
        });
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testSecondConstructorParameter() {
        IllegalArgumentException exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Saharok", -1, 1);
        });
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void testThirdConstructorParameter() {
        IllegalArgumentException exception;
        exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Saharok", 1, -1);
        });
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        Assertions.assertEquals("Saharok", horse.getName());
    }

    @Test
    void getSpeed() {
        Assertions.assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horseWithoutDistance = new Horse("Saharok", 1);
        Assertions.assertEquals(1, horse.getDistance());
        Assertions.assertEquals(0, horseWithoutDistance.getDistance());
    }

    @Test
    void testMoveWithVerify() {
        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            horse.move();
            utilities.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7})
    void testMove(double mockRandomValue) {
        try (MockedStatic<Horse> utilities = Mockito.mockStatic(Horse.class)) {
            utilities.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockRandomValue);
            horse.move();
            assertEquals(1 + 1 * mockRandomValue, horse.getDistance());
        }
    }

}