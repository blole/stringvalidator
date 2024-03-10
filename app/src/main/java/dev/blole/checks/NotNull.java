package dev.blole.checks;

import javax.annotation.Nullable;

public class NotNull implements ValidityCheck {
    public static NotNull INSTANCE = new NotNull();

    @Override
    public boolean valid(@Nullable String input) {
        return input != null;
    }
}
