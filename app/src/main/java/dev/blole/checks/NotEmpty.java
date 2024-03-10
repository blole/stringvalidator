package dev.blole.checks;

import javax.annotation.Nullable;

public class NotEmpty implements ValidityCheck {
    public static NotEmpty INSTANCE = new NotEmpty();

    @Override
    public boolean valid(@Nullable String input) {
        return input != null && !input.isEmpty();
    }
}
