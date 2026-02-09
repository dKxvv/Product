package org.example;

import java.util.Objects;

public class PackagedPieceProduct extends PieceProduct {
    private final int quantity;
    private final Packaging packaging;

    public PackagedPieceProduct(String name, String description, double pieceWeight, int quantity, Packaging packaging) {
        super(name, description, pieceWeight);

        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
        if (packaging == null) {
            throw new IllegalArgumentException("Упаковка не может быть null");
        }

        this.quantity = quantity;
        this.packaging = packaging;
    }

    public int getQuantity() {
        return quantity;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public int getPieceCount() {
        return quantity;
    }


    public double getNetWeight() {
        return getPieceWeight() * quantity;
    }

    public double getGrossWeight() {
        return getNetWeight() + packaging.getWeight();
    }

    @Override
    public double getWeight() {
        return getPieceWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackagedPieceProduct that)) return false;
        if (!super.equals(that)) return false;
        return quantity == that.quantity &&
                Objects.equals(packaging, that.packaging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, packaging);
    }

    @Override
    public String toString() {
        return String.format("Упакованный штучный товар{название='%s', описание='%s', вес штуки=%.3f кг, количество=%d шт, %s}",
                getName(), getDescription(), getPieceWeight(), quantity, packaging);
    }
}
