package com.eligere.validations;

public class ISBNValidator {

    public static boolean isValidISBN(String isbn) {
        isbn = isbn.replaceAll("-", "").replaceAll(" ", ""); // Remove hyphens if present

        // Check if ISBN is valid ISBN-10 or ISBN-13
        return isValidISBN10(isbn) || isValidISBN13(isbn);
    }

    private static boolean isValidISBN10(String isbn) {
        if (isbn.length() != 10) return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c)) return false;
            sum += (10 - i) * Character.getNumericValue(c);
        }

        char lastChar = isbn.charAt(9);
        if (lastChar == 'X') {
            sum += 10;
        } else if (!Character.isDigit(lastChar)) {
            return false;
        } else {
            sum += Character.getNumericValue(lastChar);
        }

        return sum % 11 == 0;
    }

    private static boolean isValidISBN13(String isbn) {
        if (isbn.length() != 13) return false;

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            char c = isbn.charAt(i);
            if (!Character.isDigit(c)) return false;
            sum += (i % 2 == 0) ? Character.getNumericValue(c) : 3 * Character.getNumericValue(c);
        }

        char lastChar = isbn.charAt(12);
        if (!Character.isDigit(lastChar)) return false;

        return (10 - (sum % 10)) % 10 == Character.getNumericValue(lastChar);
    }
}