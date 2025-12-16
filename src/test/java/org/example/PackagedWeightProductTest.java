package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PackagedWeightProductTest {
    @Test
    void testConstructorValidData() {
        WeightProduct product = new WeightProduct("Сахар", "Рафинированный сахар");
        Packaging packaging = new Packaging("Мешок", 0.2);
        PackagedWeightProduct packagedProduct = new PackagedWeightProduct(product, 10.0, packaging);

        assertEquals(product, packagedProduct.getProduct());
        assertEquals(packaging, packagedProduct.getPackaging());
        assertEquals(10.0, packagedProduct.getProductWeight(), 0.001);
    }

    @Test
    void testConstructorInvalidData() {
        WeightProduct product = new WeightProduct("Сахар", "Рафинированный сахар");
        Packaging packaging = new Packaging("Мешок", 0.2);

        assertThrows(IllegalArgumentException.class, () ->
                new PackagedWeightProduct(null, 10.0, packaging));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedWeightProduct(product, 10.0, null));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedWeightProduct(product, 0, packaging));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedWeightProduct(product, -1.0, packaging));
    }

    @Test
    void testWeights() {
        WeightProduct product = new WeightProduct("Сахар", "Рафинированный сахар");
        Packaging packaging = new Packaging("Мешок", 0.2);
        PackagedWeightProduct packagedProduct = new PackagedWeightProduct(product, 10.0, packaging);

        assertEquals(10.0, packagedProduct.getNetWeight(), 0.001);
        assertEquals(10.2, packagedProduct.getGrossWeight(), 0.001);
    }

    @Test
    void testEqualsAndHashCode() {
        WeightProduct product = new WeightProduct("Сахар", "Рафинированный сахар");
        Packaging packaging = new Packaging("Мешок", 0.2);

        PackagedWeightProduct p1 = new PackagedWeightProduct(product, 10.0, packaging);
        PackagedWeightProduct p2 = new PackagedWeightProduct(product, 10.0, packaging);
        PackagedWeightProduct p3 = new PackagedWeightProduct(product, 5.0, packaging);

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}

