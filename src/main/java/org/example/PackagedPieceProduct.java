package org.example;

import java.util.Objects;

public class PackagedPieceProduct {
    private final Packaging packaging;
    private final PieceProduct product;
    private final int quantity;

    public PackagedPieceProduct(PieceProduct product, int quantity, Packaging packaging) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }
        if (packaging == null) {
            throw new IllegalArgumentException("Упаковка не может быть null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
        this.product = product;
        this.quantity = quantity;
        this.packaging = packaging;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public PieceProduct getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPieceCount() {
        return quantity;
    }

    public double getNetWeight() {
        return product.getPieceWeight() * quantity;
    }

    public double getGrossWeight() {
        return getNetWeight() + packaging.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagedPieceProduct that = (PackagedPieceProduct) o;
        return quantity == that.quantity &&
                packaging.equals(that.packaging) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packaging, product);
    }

    @Override
    public String toString() {
        return String.format("Упакованный штучный товар{товар=%s, количество=%d шт, %s}",
                product, quantity, packaging);
    }

}
