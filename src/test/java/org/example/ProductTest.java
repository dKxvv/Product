package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product createProduct(String name, String description) {
        return new Product(name, description);
    }

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

class WeightProductTest {

    private WeightProduct createWeightProduct(String name, String description) {
        return new WeightProduct(name, description);
    }

    @Test
    void testConstructorValidData() {
        WeightProduct product = createWeightProduct("Мука", "Пшеничная мука");
        assertEquals("Мука", product.getName());
        assertEquals("Пшеничная мука", product.getDescription());
    }

    @Test
    void testConstructorEmptyName() {
        assertThrows(IllegalArgumentException.class, () ->
                createWeightProduct("", "Описание"));
        assertThrows(IllegalArgumentException.class, () ->
                createWeightProduct("   ", "Описание"));
        assertThrows(IllegalArgumentException.class, () ->
                createWeightProduct(null, "Описание"));
    }

    @Test
    void testConstructorNullDescription() {
        WeightProduct product = createWeightProduct("Товар", null);
        assertEquals("", product.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        WeightProduct p1 = createWeightProduct("Товар", "Описание");
        WeightProduct p2 = createWeightProduct("Товар", "Описание");
        WeightProduct p3 = createWeightProduct("Другой товар", "Описание");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testToString() {
        WeightProduct product = createWeightProduct("Товар", "Тестовое описание");
        String str = product.toString();
        assertTrue(str.contains("Товар"));
        assertTrue(str.contains("Тестовое описание"));
    }

    @Test
    void testGetWeight() {
        WeightProduct product = createWeightProduct("Сахар", "Рафинированный");
        assertEquals(0.0, product.getWeight(), 0.001);
    }
}

class PieceProductTest {

    @Test
    void testConstructorValidData() {
        PieceProduct product = new PieceProduct("Книга", "Учебник", 0.8);
        assertEquals("Книга", product.getName());
        assertEquals("Учебник", product.getDescription());
        assertEquals(0.8, product.getPieceWeight(), 0.001);
        assertEquals(0.8, product.getWeight(), 0.001);
    }

    @Test
    void testConstructorInvalidName() {
        assertThrows(IllegalArgumentException.class, () ->
                new PieceProduct("", "Описание", 0.5));
        assertThrows(IllegalArgumentException.class, () ->
                new PieceProduct("   ", "Описание", 0.5));
        assertThrows(IllegalArgumentException.class, () ->
                new PieceProduct(null, "Описание", 0.5));
    }

    @Test
    void testConstructorNullDescription() {
        PieceProduct product = new PieceProduct("Товар", null, 0.5);
        assertEquals("", product.getDescription());
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

    @Test
    void testToString() {
        PieceProduct product = new PieceProduct("Товар", "Тестовое описание", 0.3);
        String str = product.toString();
        assertTrue(str.contains("Товар"));
        assertTrue(str.contains("Тестовое описание"));
        assertTrue(str.contains("0.300"));
    }
}