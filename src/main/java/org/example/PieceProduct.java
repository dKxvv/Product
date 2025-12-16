package org.example;

import java.util.Objects;

public class PieceProduct extends Product {
    private final double pieceWeight; // вес одной штуки в кг

    public PieceProduct(String name, String description, double pieceWeight) {
        super(name, description);
        if (pieceWeight <= 0) {
            throw new IllegalArgumentException("Вес одной штуки должен быть положительным");
        }
        this.pieceWeight = pieceWeight;
    }

    public double getPieceWeight() {
        return pieceWeight;
    }

    @Override
    public double getWeight() {
        return pieceWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (!(o instanceof PieceProduct)) return false;
        PieceProduct that = (PieceProduct) o;
        return Double.compare(that.pieceWeight, pieceWeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pieceWeight);
    }

    @Override
    public String toString() {
        return String.format("Штучный товар{название='%s', описание='%s', вес штуки=%.3f кг}",
                getName(), getDescription(), pieceWeight);
    }
}