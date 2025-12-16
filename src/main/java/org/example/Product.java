package org.example;

import java.util.Objects;
public abstract class Product {
    private final String name;
    private final String description;

        protected Product(String name, String description) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Название товара не может быть пустым");
            }
            this.name = name.trim();
            if (description != null) {
                this.description = description.trim();
            } else {
                this.description = "";
            }
        }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract double getWeight();
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
