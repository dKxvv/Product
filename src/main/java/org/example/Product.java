package org.example;

import java.util.Objects;

public class Product {
    private final String name;
    private final String description;

    public Product(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.name = name.trim();
        this.description = (description != null) ? description.trim() : "";
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Базовая реализация: обычный товар без веса → вес = 0
    public double getWeight() {
        return 0.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return String.format("Товар{название='%s', описание='%s'}", name, description);
    }
}