package org.example;

public class EndsWithFilter implements Filter {
    private final String suffix;

    public EndsWithFilter(String suffix) {
        if (suffix == null || suffix.trim().isEmpty()) {
            throw new IllegalArgumentException("Окончание не может быть пустым");
        }
        this.suffix = suffix.trim();
    }

    @Override
    public boolean apply(String str) {
        if (str == null) return false;
        return str.endsWith(suffix);
    }

    @Override
    public String toString() {
        return "EndsWithFilter{suffix='" + suffix + "'}";
    }
}