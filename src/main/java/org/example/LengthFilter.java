package org.example;

public class LengthFilter implements Filter {
        private final int minLength;
        private final int maxLength;

        public LengthFilter(int minLength, int maxLength) {
            if (minLength < 0) {
                throw new IllegalArgumentException("Минимальная длина не может быть отрицательной");
            }
            if (minLength > maxLength) {
                throw new IllegalArgumentException("Минимальная длина не может быть больше максимальной");
            }
            this.minLength = minLength;
            this.maxLength = maxLength;
        }

        @Override
        public boolean apply(String str) {
            if (str == null) return false;
            int length = str.length();
            return length >= minLength && length <= maxLength;
        }

        @Override
        public String toString() {
            return String.format("LengthFilter{min=%d, max=%d}", minLength, maxLength);
        }
}
