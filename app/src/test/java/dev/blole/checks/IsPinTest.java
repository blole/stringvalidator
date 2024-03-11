package dev.blole.checks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IsPinTest {
    @Test
    void correctlyFormattedPinsAreValid() {
        assertTrue(IsPin.INSTANCE.valid("19780202-2389"));
        assertTrue(IsPin.INSTANCE.valid("19820411-2380"));
    }

    @Test
    void incorrectlyFormattedPinsAreInvalid() {
        assertFalse(IsPin.INSTANCE.valid(null));
        assertFalse(IsPin.INSTANCE.valid(""));
        assertFalse(IsPin.INSTANCE.valid(" "));
        assertFalse(IsPin.INSTANCE.valid("00"));
        assertFalse(IsPin.INSTANCE.valid("12345678-1234"));

        assertFalse(IsPin.INSTANCE.valid("820411-2380"));
        assertFalse(IsPin.INSTANCE.valid("198204112380"));
    }

    @Test
    void todoValidateDates() {
        // TODO: we could also validate the pin corresponds to a valid date or
        // samordningsnummer
        assertFalse(IsPin.INSTANCE.valid("19999999-1234"));
    }

    @Test
    void todoHandlePinsWithoutCenturyAndMinus() {
        // TODO: It's annoying for users to have to always add the initial 19/20 and -
        assertFalse(IsPin.INSTANCE.valid("7802022389"));
    }

    @Test
    void todoHandlePinsWithPlus() {
        // TODO: it's also valid to supply a + instead of a - to indicate the person is
        // over a 100 years old
        assertFalse(IsPin.INSTANCE.valid("780202+2389"));
    }
}
