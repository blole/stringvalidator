/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dev.blole;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumericStringTest {
    @Test
    void invalidNumericStringsAreNotAllowed() {
        assertThrows(NumberFormatException.class, (() -> new NumericString("")));
        assertThrows(NumberFormatException.class, (() -> new NumericString(" ")));
        assertThrows(NumberFormatException.class, (() -> new NumericString("-")));
        assertThrows(NumberFormatException.class, (() -> new NumericString("-1")));
        assertThrows(NumberFormatException.class, (() -> new NumericString(" 2")));
        assertThrows(NumberFormatException.class, (() -> new NumericString("3 ")));
        assertThrows(NumberFormatException.class, (() -> new NumericString("lol")));
    }

    @Test
    void checksumCalculatedCorrectly() {
        assertChecksum(0, "0");
        assertChecksum(8, "1");
        assertChecksum(6, "02");
        assertChecksum(4, "03");
        assertChecksum(1, "9");
        assertChecksum(9, "10");
        assertChecksum(7, "11");
        assertChecksum(2, "99");
        assertChecksum(8, "100");
        assertChecksum(6, "101");
        assertChecksum(0, "9999999999");
        assertChecksum(8, "10000000000");
        assertChecksum(1, "999999999999999999999");
        assertChecksum(9, "1000000000000000000000");
    }

    private void assertChecksum(int expectChecksum, String input) {
        assertEquals(expectChecksum, new NumericString(input).luhnChecksum());
    }
}
