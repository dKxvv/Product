package org.example;

import java.util.*;

public class ProductBatch {
    private final String description;
    private final List<PackagedItem> items;


    public ProductBatch(String description, Collection<PackagedItem> items) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание партии не может быть пустым");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Партия должна содержать хотя бы один товар");
        }
        if (items.contains(null)) {
            throw new IllegalArgumentException("Партия не может содержать null-товары");
        }

        this.description = description.trim();
        this.items = List.copyOf(items); // Создаём неизменяемую копию
    }

    @SafeVarargs
    public ProductBatch(String description, PackagedItem... items) {
        this(description, Arrays.asList(items));
    }


    public List<PackagedItem> getItems() {
        return items; // Возвращаем копию или неизменяемый список
    }

    public double getTotalWeight() {
        double total = 0.0;
        for (PackagedItem item : items) {
            total += item.getGrossWeight();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Партия товаров{описание='%s', количество=%d, общая масса=%.2f кг}",
                description, items.size(), getTotalWeight());
    }
}