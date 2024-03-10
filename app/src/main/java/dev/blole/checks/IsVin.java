package dev.blole.checks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

/** VIN = Vehicle identification number **/
public class IsVin implements ValidityCheck {
    public static IsVin INSTANCE = new IsVin();

    private Pattern regex = Pattern.compile("^[ABCDEFGHJKLMNOPRSTUWXYZ]{3}(\\d\\d[\\dABCDEFGHJKLMNPRSTUWXYZ])$");

    @Override
    public boolean valid(@Nullable String input) {
        if (input == null) {
            return false;
        }

        Matcher matcher = regex.matcher(input);

        if (!matcher.matches()) {
            return false;
        }

        String last3 = matcher.group(1);
        return !last3.equals("000");
    }

}