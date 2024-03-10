package dev.blole;

import java.util.ArrayList;

import javax.annotation.Nullable;

import dev.blole.checks.ValidityCheck;

class ValidityChecker {

    private ArrayList<ValidityCheck> checks = new ArrayList<>();
    private ArrayList<String> errorMessages = new ArrayList<>();

    public ValidityChecker add(ValidityCheck check, String errorMessage) {
        checks.add(check);
        errorMessages.add(errorMessage);
        return this;
    }

    public @Nullable String verify(String input) {
        for (int i = 0; i < checks.size(); i++) {
            if (!checks.get(i).valid(input)) {
                return errorMessages.get(i);
            }
        }
        return null;
    }
}
