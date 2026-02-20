package org.example;

@FunctionalInterface
public interface Filter {
    boolean apply(String str);
}