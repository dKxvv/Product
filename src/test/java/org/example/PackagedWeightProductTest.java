package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PackagedWeightProductTest {

    @Test
    void testConstructorValidData() {
        // Данные
        String name = "Сахар";
        String description = "Рафинированный сахар";
        double productWeight = 10.0;
        Packaging packaging = new Packaging("Мешок", 0.2);

        // Создание объекта
        PackagedWeightProduct packagedProduct = new PackagedWeightProduct(name, description, productWeight, packaging);

        // Проверки через унаследованные методы
        assertEquals(name, packagedProduct.getName());
        assertEquals(description, packagedProduct.getDescription());
        assertEquals(packaging, packagedProduct.getPackaging());
        assertEquals(productWeight, packagedProduct.getProductWeight(), 0.001);
        assertEquals(productWeight, packagedProduct.getWeight(), 0.001); // getWeight() должен возвращать вес товара
    }

    @Test
    void testConstructorInvalidData() {
        String name = "Сахар";
        String description = "Рафинированный сахар";
        Packaging packaging = new Packaging("Мешок", 0.2);

        // Нулевой вес
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedWeightProduct(name, description, 0.0, packaging));
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedWeightProduct(name, description, -1.0, packaging));

        // Нулевая упаковка
        assertThrows(IllegalArgumentException.class, () ->
                new PackagedWeightProduct(name, description, 10.0, null));
    }

    @Test
    void testWeights() {
        PackagedWeightProduct packagedProduct = new PackagedWeightProduct(
                "Сахар", "Рафинированный сахар", 10.0, new Packaging("Мешок", 0.2)
        );

        assertEquals(10.0, packagedProduct.getNetWeight(), 0.001);
        assertEquals(10.2, packagedProduct.getGrossWeight(), 0.001);
        assertEquals(10.0, packagedProduct.getWeight(), 0.001); // getWeight() = нетто-вес
    }

    @Test
    void testEqualsAndHashCode() {
        Packaging packaging = new Packaging("Мешок", 0.2);

        PackagedWeightProduct p1 = new PackagedWeightProduct("Сахар", "Рафинированный сахар", 10.0, packaging);
        PackagedWeightProduct p2 = new PackagedWeightProduct("Сахар", "Рафинированный сахар", 10.0, packaging);
        PackagedWeightProduct p3 = new PackagedWeightProduct("Сахар", "Рафинированный сахар", 5.0, packaging);
        PackagedWeightProduct p4 = new PackagedWeightProduct("Соль", "Крупная соль", 10.0, packaging);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        assertNotEquals(p1, p3); // разный вес
        assertNotEquals(p1, p4); // разное название/описание
        assertNotEquals(p1, new Object()); // не тот тип
        assertNotEquals(p1, null); // null

        // Рефлексивность
        assertEquals(p1, p1);
    }

    @Test
    void testToString() {
        PackagedWeightProduct product = new PackagedWeightProduct(
                "Сахар", "Рафинированный сахар", 10.0, new Packaging("Мешок", 0.2)
        );

        String str = product.toString();
        assertTrue(str.contains("Сахар"));
        assertTrue(str.contains("Рафинированный сахар"));
        assertTrue(str.contains("10.000"));
        assertTrue(str.contains("Мешок"));
        assertTrue(str.contains("0.200"));
    }
}