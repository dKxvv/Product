package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PackagingTest {

    @Test
    void testConstructorValidData() {
        Packaging packaging = new Packaging("Коробка", 0.5);
        assertEquals("Коробка", packaging.getName());
        assertEquals(0.5, packaging.getWeight(), 0.001);
    }

    @Test
    void testConstructorEmptyName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Packaging("", 0.5));
        assertThrows(IllegalArgumentException.class, () ->
                new Packaging("   ", 0.5));
        assertThrows(IllegalArgumentException.class, () ->
                new Packaging(null, 0.5));
    }

    @Test
    void testConstructorNegativeWeight() {
        assertThrows(IllegalArgumentException.class, () ->
                new Packaging("Коробка", -0.1));
    }

    @Test
    void testEqualsAndHashCode() {
        Packaging p1 = new Packaging("Коробка", 0.5);
        Packaging p2 = new Packaging("Коробка", 0.5);
        Packaging p3 = new Packaging("Пакет", 0.1);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }

    @Test
    void testToString() {
        Packaging packaging = new Packaging("Коробка", 0.5);
        String str = packaging.toString();
        assertTrue(str.contains("Коробка"));
        assertTrue(str.contains("0,500"));
    }
}
