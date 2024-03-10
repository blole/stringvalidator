package dev.blole.checks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import dev.blole.NumericString;

/** PIN = Personal identity number **/
public class IsPin implements ValidityCheck {
    public static IsPin INSTANCE = new IsPin();

    private Pattern regex = Pattern.compile("^\\d{2}(\\d{6})-(\\d{4})$");

    @Override
    public boolean valid(@Nullable String input) {
        if (input == null) {
            return false;
        }

        Matcher matcher = regex.matcher(input);

        if (!matcher.matches()) {
            return false;
        }

        NumericString shortPin = new NumericString(matcher.group(1) + matcher.group(2));
        return shortPin.isValidLuhn();
    }
}
