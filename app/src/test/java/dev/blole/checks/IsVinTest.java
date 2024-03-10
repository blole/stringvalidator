package dev.blole.checks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IsVinTest {
    @Test
    void correctlyFormattedVinsAreValid() {
        assertTrue(IsVin.INSTANCE.valid("ABC123"));
        assertTrue(IsVin.INSTANCE.valid("KFS762"));
        assertTrue(IsVin.INSTANCE.valid("DLH15S"));
    }

    @Test
    void incorrectlyFormattedVinsAreInvalid() {
        assertFalse(IsVin.INSTANCE.valid("ABC000"));
        assertFalse(IsVin.INSTANCE.valid("abc123"));
        assertFalse(IsVin.INSTANCE.valid("QFS762"));
        assertFalse(IsVin.INSTANCE.valid("DLH15O"));
    }

    @Test
    void todoHandleInvalidWords() {
        // TODO: some letter combinations aren't actually allowed
        assertTrue(IsVin.INSTANCE.valid("ETT111"));
    }
}
