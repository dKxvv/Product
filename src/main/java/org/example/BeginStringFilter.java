package org.example;

public class BeginStringFilter implements Filter {
        private final String pattern;
        public BeginStringFilter(String pattern) {
            if (pattern == null || pattern.trim().isEmpty()) {
                throw new IllegalArgumentException("Шаблон не может быть пустым");
            }
            this.pattern = pattern.trim();
        }

        @Override
        public boolean apply(String str) {
            if (str == null) return false;
            return str.startsWith(pattern);
        }

        @Override
        public String toString() {
            return "BeginStringFilter{pattern='" + pattern + "'}";
        }
    }
