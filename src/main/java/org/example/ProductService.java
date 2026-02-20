package org.example;

import java.util.List;

public class ProductService {

    public int countByFilter(ProductBatch batch, Filter filter) {
        if (batch == null) {
            throw new NullPointerException("Партия не может быть null");
        }
        if (filter == null) {
            throw new NullPointerException("Фильтр не может быть null");
        }
        List<PackagedItem> items = batch.getItems();

        int count = 0;

        for (PackagedItem item : items) {
            String name = item.getName();

            if (filter.apply(name)) {
                count++;
            }
        }

        return count;
    }
}