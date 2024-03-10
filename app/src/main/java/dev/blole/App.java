package dev.blole;

import dev.blole.checks.IsPin;
import dev.blole.checks.NotEmpty;

public class App {
    public static void main(String[] args) {
        ValidityChecker checker = new ValidityChecker()
                .add(NotEmpty.INSTANCE, "is empty")
                .add(IsPin.INSTANCE, "not a personal identification number");

        for (String arg : args) {
            String errorMessage = checker.verify(arg);
            if (errorMessage == null) {
                System.out.format("%s: is valid\n", arg);
            } else {
                System.out.format("%s: %s\n", arg, errorMessage);
            }
        }
    }
}
