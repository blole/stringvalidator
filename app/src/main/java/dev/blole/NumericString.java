package dev.blole;

public record NumericString(String numericString) {
    // digits with an even "index" from the right are transformed like this
    private static int[] evenTransform = { 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 };

    public NumericString {
        if (numericString.isEmpty()) {
            throw new NumberFormatException("string is empty");
        }
        if (!numericString.chars().allMatch(Character::isDigit)) {
            throw new NumberFormatException("'" + numericString + "' is not numeric");
        }
    }

    public int luhnChecksum() {
        int sum = 0;

        for (int i = 0; i < numericString.length(); i++) {
            if (i % 2 == numericString.length() % 2) {
                sum += numericString.charAt(i) - '0';
            } else {
                sum += evenTransform[numericString.charAt(i) - '0'];
            }
        }

        return 9 - (sum + 9) % 10;
    }

    public boolean isValidLuhn() {
        NumericString withoutChecksum = new NumericString(numericString.substring(0, numericString.length() - 1));

        return withoutChecksum.luhnChecksum() == numericString.charAt(numericString.length() - 1) - '0';
    }
}
