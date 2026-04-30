package src/ser515.smells;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Smell1AlmostBestTest {

    // ===== Normal cases =====
    @Test
    void testNormalValues() {
        assertEquals(1, Smell1AlmostBest.toPower(2, 0));
        assertEquals(4, Smell1AlmostBest.toPower(2, 2));
        assertEquals(8, Smell1AlmostBest.toPower(2, 3));
        assertEquals(27, Smell1AlmostBest.toPower(3, 3));
        assertEquals(125, Smell1AlmostBest.toPower(5, 3));
    }

    // ===== Boundary cases =====
    @Test
    void testZeroCases() {
        assertEquals(1, Smell1AlmostBest.toPower(0, 0));
        assertEquals(0, Smell1AlmostBest.toPower(0, 3));
        assertEquals(1, Smell1AlmostBest.toPower(5, 0));
    }

    @Test
    void testNegativeBase() {
        assertEquals(-2, Smell1AlmostBest.toPower(-2, 1));
        assertEquals(4, Smell1AlmostBest.toPower(-2, 2));
        assertEquals(-8, Smell1AlmostBest.toPower(-2, 3));
    }

    // ===== Error case =====
    @Test
    void testNegativeExponent() {
        assertThrows(IllegalArgumentException.class, () -> {
            Smell1AlmostBest.toPower(2, -1);
        });
    }

    // ===== Large value =====
    @Test
    void testLargeExponent() {
        assertEquals(1024, Smell1AlmostBest.toPower(2, 10));
    }
}