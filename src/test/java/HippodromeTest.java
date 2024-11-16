import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void testConstructorThrowsExceptionForNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void testConstructorThrowsExceptionForEmptyList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<>());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> expected = createHorses(30);
        Hippodrome hippodrome = new Hippodrome(expected);
        List<Horse> actual = hippodrome.getHorses();

        Assertions.assertEquals(expected.size(), actual.size(), "The size of the horse lists should match.");
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertSame(expected.get(i), actual.get(i), "The horse at index " + i + " should be the same object.");
        }
    }

    @Test
    void move() {
        List<Horse> expected = createMockHorses();
        Hippodrome hippodrome = new Hippodrome(expected);
        hippodrome.move(); // по сути тут move это moveAllHorses, который у horses вызывает move()

        for (Horse mock: expected) {
            Mockito.verify(mock).move();
        }
    }

    @Test
    void getWinner() {
        Horse saharok = new Horse("Saharok", 100, 100);
        List<Horse> horses = createHorses(5);
        horses.add(saharok);
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(saharok.getDistance(), hippodrome.getWinner().getDistance());
    }

    private List<Horse> createHorses(int horseCount) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= horseCount; i++) {
            String name = "Horse" + i;
            double speed = i * 1.5;
            double distance = i * 2.5;
            horses.add(new Horse(name, speed, distance));
        }
        return horses;
    }

    private List<Horse> createMockHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Horse horseMock = Mockito.mock(Horse.class);
            horses.add(horseMock);
        }
        return horses;
    }
 }