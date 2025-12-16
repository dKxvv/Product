package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PackagedPieceProductTest {
    @Test
    void testConstructorValidData() {
        PieceProduct product = new PieceProduct("Книга", "Учебник", 0.8);
        Packaging packaging = new Packaging("Коробка", 0.5);
        PackagedPieceProduct packagedProduct = new PackagedPieceProduct(product, 10, packaging);

        assertEquals(product, packagedProduct.getProduct());
        assertEquals(packaging, packagedProduct.getPackaging());
        assertEquals(10, packagedProduct.getQuantity());
        assertEquals(10, packagedProduct.getPieceCount());
    }

    @Test
    void testConstructorInvalidData() {
        PieceProduct product = new PieceProduct("Книга", "Учебник", 0.8);
        Packaging packaging = new Packaging("Коробка", 0.5);

        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(null, 10, packaging));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(product, 10, null));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(product, 0, packaging));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedPieceProduct(product, -1, packaging));
    }

    @Test
    void testWeights() {
        PieceProduct product = new PieceProduct("Книга", "Учебник", 0.8);
        Packaging packaging = new Packaging("Коробка", 0.5);
        PackagedPieceProduct packagedProduct = new PackagedPieceProduct(product, 5, packaging);

        // Вес нетто: 5 * 0.8 = 4.0 кг
        assertEquals(4.0, packagedProduct.getNetWeight(), 0.001);
        // Вес брутто: 4.0 + 0.5 = 4.5 кг
        assertEquals(4.5, packagedProduct.getGrossWeight(), 0.001);
    }

    @Test
    void testEqualsAndHashCode() {
        PieceProduct product = new PieceProduct("Книга", "Учебник", 0.8);
        Packaging packaging = new Packaging("Коробка", 0.5);

        PackagedPieceProduct p1 = new PackagedPieceProduct(product, 5, packaging);
        PackagedPieceProduct p2 = new PackagedPieceProduct(product, 5, packaging);
        PackagedPieceProduct p3 = new PackagedPieceProduct(product, 3, packaging);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}
