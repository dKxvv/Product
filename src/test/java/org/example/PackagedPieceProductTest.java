package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PackagedPieceProductTest {

    @Test
    void testConstructorValidData() {
        String name = "Книга";
        String description = "Учебник";
        double pieceWeight = 0.8;
        int quantity = 10;
        Packaging packaging = new Packaging("Коробка", 0.5);

        PackagedPieceProduct packagedProduct = new PackagedPieceProduct(
                name, description, pieceWeight, quantity, packaging
        );

        // Проверяем унаследованные поля
        assertEquals(name, packagedProduct.getName());
        assertEquals(description, packagedProduct.getDescription());
        assertEquals(pieceWeight, packagedProduct.getPieceWeight(), 0.001);

        // Проверяем собственные поля
        assertEquals(packaging, packagedProduct.getPackaging());
        assertEquals(quantity, packagedProduct.getQuantity());
    }

    @Test
    void testConstructorInvalidData() {
        String name = "Книга";
        String description = "Учебник";
        double pieceWeight = 0.8;
        Packaging packaging = new Packaging("Коробка", 0.5);

        // Нулевое или отрицательное количество
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(name, description, pieceWeight, 0, packaging));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(name, description, pieceWeight, -1, packaging));

        // Нулевая упаковка
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(name, description, pieceWeight, 5, null));

        // pieceWeight <= 0 проверяется в конструкторе PieceProduct → унаследовано
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(name, description, 0.0, 5, packaging));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(name, description, -0.1, 5, packaging));
    }

    @Test
    void testWeights() {
        PackagedPieceProduct packagedProduct = new PackagedPieceProduct(
                "Книга", "Учебник", 0.8, 5, new Packaging("Коробка", 0.5)
        );

        assertEquals(4.0, packagedProduct.getNetWeight(), 0.001);       // 5 * 0.8
        assertEquals(4.5, packagedProduct.getGrossWeight(), 0.001);    // 4.0 + 0.5
        assertEquals(0.8, packagedProduct.getWeight(), 0.001);         // вес одной штуки (контракт PieceProduct)
    }

    @Test
    void testEqualsAndHashCode() {
        Packaging packaging = new Packaging("Коробка", 0.5);

        PackagedPieceProduct p1 = new PackagedPieceProduct("Книга", "Учебник", 0.8, 5, packaging);
        PackagedPieceProduct p2 = new PackagedPieceProduct("Книга", "Учебник", 0.8, 5, packaging);
        PackagedPieceProduct p3 = new PackagedPieceProduct("Книга", "Учебник", 0.8, 3, packaging);
        PackagedPieceProduct p4 = new PackagedPieceProduct("Журнал", "Научный", 0.3, 5, packaging);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        assertNotEquals(p1, p3); // разное количество
        assertNotEquals(p1, p4); // разные имя/описание/вес штуки
        assertNotEquals(p1, null);
        assertNotEquals(p1, new Object());
        assertEquals(p1, p1); // рефлексивность
    }

    @Test
    void testToString() {
        PackagedPieceProduct product = new PackagedPieceProduct(
                "Книга", "Учебник", 0.800, 5, new Packaging("Коробка", 0.5)
        );

        String str = product.toString();
        assertTrue(str.contains("Книга"));
        assertTrue(str.contains("Учебник"));
        assertTrue(str.contains("0,800"));
        assertTrue(str.contains("количество=5"));
        assertTrue(str.contains("Коробка"));
    }
}