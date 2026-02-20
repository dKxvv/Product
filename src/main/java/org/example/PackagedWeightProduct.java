package org.example;

import java.util.Objects;

public class PackagedWeightProduct extends WeightProduct implements PackagedItem { // ← ДОБАВЛЕНО
    private final double productWeight; // масса товара (нетто) в кг
    private final Packaging packaging;  // упаковка

    public PackagedWeightProduct(String name, String description, double productWeight, Packaging packaging) {
        super(name, description);

        if (productWeight <= 0) {
            throw new IllegalArgumentException("Вес товара должен быть положительным");
        }
        if (packaging == null) {
            throw new IllegalArgumentException("Упаковка не может быть null");
        }

        this.productWeight = productWeight;
        this.packaging = packaging;
    }


    public Packaging getPackaging() {
        return packaging;
    }

    @Override
    public double getGrossWeight() {
        return productWeight + packaging.getWeight();
    }

    @Override
    public double getWeight() {
        return productWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackagedWeightProduct that)) return false;
        if (!super.equals(that)) return false;
        return Double.compare(that.productWeight, productWeight) == 0 &&
                Objects.equals(packaging, that.packaging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productWeight, packaging);
    }

    @Override
    public String toString() {
        return String.format("Упакованный весовой товар{название='%s', описание='%s', вес товара=%.3f кг, %s}",
                getName(), getDescription(), productWeight, packaging);
    }
}