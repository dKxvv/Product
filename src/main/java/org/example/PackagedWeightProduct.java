package org.example;

import java.util.Objects;

public class PackagedWeightProduct {
        private final Packaging packaging;
        private final WeightProduct product;
        private final double productWeight; // вес товара в кг

        public PackagedWeightProduct(WeightProduct product, double productWeight, Packaging packaging) {
            if (product == null) {
                throw new IllegalArgumentException("Товар не может быть пустым");
            }
            if (packaging == null) {
                throw new IllegalArgumentException("Упаковка не может быть пустым");
            }
            if (productWeight <= 0) {
                throw new IllegalArgumentException("Вес товара должен быть положительным");
            }
            this.product = product;
            this.productWeight = productWeight;
            this.packaging = packaging;
        }

        public Packaging getPackaging() {
            return packaging;
        }

        public WeightProduct getProduct() {
            return product;
        }

        public double getProductWeight() {
            return productWeight;
        }

        // Масса нетто (только товара)
        public double getNetWeight() {
            return productWeight;
        }

        // Масса брутто (товара + упаковки)
        public double getGrossWeight() {
            return productWeight + packaging.getWeight();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PackagedWeightProduct that = (PackagedWeightProduct) o;
            return Double.compare(that.productWeight, productWeight) == 0 &&
                    packaging.equals(that.packaging) &&
                    product.equals(that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(packaging, product, productWeight);
        }

        @Override
        public String toString() {
            return String.format("Упакованный весовой товар{товар=%s, вес товара=%.3f кг, %s}",
                    product, productWeight, packaging);
        }
}
