package org.example;

import java.util.Objects;
public class Packaging
{
    private final String name;
    private final double weight;

    public Packaging(String name, double weight) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название упаковки не может быть пустым");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Вес упаковки не может быть отрицательным");
        }
        this.name = name.trim();
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Packaging packaging = (Packaging) o;
        return Double.compare(weight, packaging.weight) == 0 && Objects.equals(name, packaging.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }

    @Override
    public String toString() {
        return String.format("Упаковка{название='%s', вес=%.3f кг}", name, weight);
    }
}
