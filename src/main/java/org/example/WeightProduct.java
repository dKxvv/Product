package org.example;

import java.util.Objects;

public class WeightProduct extends Product {
    public WeightProduct(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && o instanceof WeightProduct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), WeightProduct.class);
    }

    @Override
    public String toString() {
        return String.format("Весовой товар{название='%s', описание='%s'}",
                getName(), getDescription());
    }
}
