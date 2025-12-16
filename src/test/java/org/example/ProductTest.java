package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

abstract class ProductTest {

    protected abstract Product createProduct(String name, String description);
    @Test
    void testConstructorValidData() {
        Product product = createProduct("Молоко", "Свежее молоко");
        assertEquals("Молоко", product.getName());
        assertEquals("Свежее молоко", product.getDescription());
    }
    @Test
    void testConstructorEmptyName() {
        assertThrows(IllegalArgumentException.class, () ->
                createProduct("", "Описание"));
        assertThrows(IllegalArgumentException.class, () ->
                createProduct("   ", "Описание"));
        assertThrows(IllegalArgumentException.class, () ->
                createProduct(null, "Описание"));
    }

    @Test
    void testConstructorNullDescription() {
        Product product = createProduct("Товар", null);
        assertEquals("", product.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Product p1 = createProduct("Товар", "Описание");
        Product p2 = createProduct("Товар", "Описание");
        Product p3 = createProduct("Другой товар", "Описание");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testToString() {
        Product product = createProduct("Товар", "Тестовое описание");
        String str = product.toString();
        assertTrue(str.contains("Товар"));
        assertTrue(str.contains("Тестовое описание"));
    }

}

class WeightProductTest extends ProductTest {
    @Override
    protected Product createProduct(String name, String description) {
        return new WeightProduct(name, description);
    }

    @Test
    void testSpecificWeightProductMethods() {
        WeightProduct product = new WeightProduct("Мука", "Пшеничная мука высший сорт");
        assertEquals(0, product.getWeight(), 0.001);
    }
}

class PieceProductTest extends ProductTest {
    @Override
    protected Product createProduct(String name, String description) {
        return new PieceProduct(name, description, 0.5);
    }

    @Test
    void testConstructorValidData() {
        PieceProduct product = new PieceProduct("Книга", "Учебник", 0.8);
        assertEquals("Книга", product.getName());
        assertEquals("Учебник", product.getDescription());
        assertEquals(0.8, product.getPieceWeight(), 0.001);
        assertEquals(0.8, product.getWeight(), 0.001);
    }

    @Test
    void testConstructorInvalidWeight() {
        assertThrows(IllegalArgumentException.class, () ->
                new PieceProduct("Товар", "Описание", 0));
        assertThrows(IllegalArgumentException.class, () ->
                new PieceProduct("Товар", "Описание", -0.1));
    }

    @Test
    void testEqualsAndHashCode() {
        PieceProduct p1 = new PieceProduct("Товар", "Описание", 0.5);
        PieceProduct p2 = new PieceProduct("Товар", "Описание", 0.5);
        PieceProduct p3 = new PieceProduct("Товар", "Описание", 0.6);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }
}