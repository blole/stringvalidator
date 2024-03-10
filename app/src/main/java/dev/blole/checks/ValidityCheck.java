package dev.blole.checks;

import javax.annotation.Nullable;

public interface ValidityCheck {
    boolean valid(@Nullable String input);
}
