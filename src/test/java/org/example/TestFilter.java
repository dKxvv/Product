package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void testCountByFilter() {
        Packaging bag = new Packaging("Мешок", 0.2);

        // Создаём упакованные товары
        PackagedWeightProduct sugar = new PackagedWeightProduct("Сахар", "Белый", 10.0, bag);
        PackagedWeightProduct salt = new PackagedWeightProduct("Соль", "Морская", 5.0, bag);
        PackagedWeightProduct flour = new PackagedWeightProduct("Мука", "Пшеничная", 7.0, bag);

        ProductBatch batch = new ProductBatch("Тестовая партия", sugar, salt, flour);
        ProductService service = new ProductService();

        // Фильтр товаров, начинающихся на "С"
        Filter sFilter = new BeginStringFilter("С");
        assertEquals(2, service.countByFilter(batch, sFilter)); // Сахар, Соль

        // Фильтр товаров, начинающихся на "М"
        Filter mFilter = new BeginStringFilter("М");
        assertEquals(1, service.countByFilter(batch, mFilter)); // Мука

        // Фильтр по длине названия (от 3 до 4 символов)
        Filter lengthFilter = new LengthFilter(3, 4);
        assertEquals(2, service.countByFilter(batch, lengthFilter)); // Сахар (5) — нет, Соль (4) — да, Мука (4) — да → 2 товара
    }

    @Test
    void testCountByFilterWithMixedTypes() {
        Packaging bag = new Packaging("Мешок", 0.2);
        Packaging box = new Packaging("Коробка", 0.5);

        // Смешанные типы упакованных товаров
        PackagedWeightProduct sugar = new PackagedWeightProduct("Сахар", "Рафинад", 10.0, bag);
        PackagedPieceProduct books = new PackagedPieceProduct("Книги", "Учебники", 0.8, 5, box);
        PackagedWeightProduct salt = new PackagedWeightProduct("Соль", "Крупная", 5.0, bag);

        ProductBatch batch = new ProductBatch("Смешанная партия", sugar, books, salt);
        ProductService service = new ProductService();

        Filter endsWithR = new EndsWithFilter("р");
        assertEquals(1, service.countByFilter(batch, endsWithR));
        // Фильтр по началу с "К"
        Filter beginsWithK = new BeginStringFilter("К");
        assertEquals(1, service.countByFilter(batch, beginsWithK)); // Только "Книги"

        // Фильтр по окончанию на "р"
    }

    @Test
    void testCountByFilterEmptyResult() {
        Packaging bag = new Packaging("Мешок", 0.2);
        PackagedWeightProduct sugar = new PackagedWeightProduct("Сахар", "Белый", 10.0, bag);
        PackagedWeightProduct salt = new PackagedWeightProduct("Соль", "Морская", 5.0, bag);

        ProductBatch batch = new ProductBatch("Партия", sugar, salt);
        ProductService service = new ProductService();

        // Нет товаров, начинающихся на "З"
        Filter zFilter = new BeginStringFilter("З");
        assertEquals(0, service.countByFilter(batch, zFilter));

    }

    @Test
    void testCountByFilterNullSafety() {
        Packaging bag = new Packaging("Мешок", 0.2);
        PackagedWeightProduct product = new PackagedWeightProduct("Товар", "Описание", 1.0, bag);
        ProductBatch batch = new ProductBatch("Партия", product);
        ProductService service = new ProductService();

        // Проверка защиты от null (должно бросить исключение)
        assertThrows(NullPointerException.class, () ->
                service.countByFilter(null, new BeginStringFilter("Т"))
        );

        assertThrows(NullPointerException.class, () ->
                service.countByFilter(batch, null)
        );
    }

    @Test
    void testTotalWeightCalculation1() {
        Packaging bag = new Packaging("Мешок", 0.2);
        Packaging box = new Packaging("Коробка", 0.5);

        PackagedWeightProduct sugar = new PackagedWeightProduct("Сахар", "Рафинад", 10.0, bag);

        PackagedPieceProduct books = new PackagedPieceProduct("Книги", "Учебники", 0.8, 5, box);

        PackagedWeightProduct salt = new PackagedWeightProduct("Соль", "Крупная", 5.0, bag);

        ProductBatch batch = new ProductBatch("Партия", sugar, books, salt);

        // Сахар: 10.2  + Книги: 4.5  + Соль: 5.2  = 19.9
        double expectedTotal = 10.2 + 4.5 + 5.2;
        assertEquals(expectedTotal, batch.getTotalWeight(), 0.00000001);
    }

    @Test
    void testTotalWeightCalculation2() {
        Packaging bag = new Packaging("Мешок", 0.2);

        // Создаём упакованные товары
        PackagedWeightProduct sugar = new PackagedWeightProduct("Сахар", "Белый", 10.0, bag);
        PackagedWeightProduct salt = new PackagedWeightProduct("Соль", "Морская", 5.0, bag);
        PackagedWeightProduct flour = new PackagedWeightProduct("Мука", "Пшеничная", 7.0, bag);

        ProductBatch batch = new ProductBatch("Тестовая партия", sugar, salt, flour);
        double expectedTotal = 10.2 + 5.2 + 7.2; //22.6
        assertEquals(expectedTotal, batch.getTotalWeight(), 0.00000001);
    }

    @Test
    void testTotalWeightCalculation3() {
        Packaging bag = new Packaging("Мешок", 0.2);
        PackagedWeightProduct product = new PackagedWeightProduct("Товар", "Описание", 1.0, bag);
        ProductBatch batch = new ProductBatch("Партия", product);

        // Ожидаем: 1.0 кг товара + 0.2 кг упаковка = 1.2 кг брутто
        double expectedTotal = 1.2;

        assertEquals(expectedTotal, batch.getTotalWeight(), 0.00000001);
    }
    @Test
    void testTotalWeightCalculation4() {
        Packaging bag = new Packaging("Мешок", 0.2);
        PackagedWeightProduct sugar = new PackagedWeightProduct("Сахар", "Белый", 10.0, bag);
        PackagedWeightProduct salt = new PackagedWeightProduct("Соль", "Морская", 5.0, bag);

        ProductBatch batch = new ProductBatch("Партия", sugar, salt);
        double expectedTotal =10.2 + 5.2; //15.4
        assertEquals(expectedTotal, batch.getTotalWeight(), 0.00000001);
    }
}