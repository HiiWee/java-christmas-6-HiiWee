package christmas.exception;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionResolver {

    private static final String ERROR_PREFIX_FORMAT = "[ERROR] %s";

    private ExceptionResolver() {
    }

    public static <T> void resolveProcessAfterInput(final Consumer<T> consumer, final Supplier<T> supplier) {
        try {
            consumer.accept(supplier.get());
        } catch (IllegalArgumentException | IllegalStateException e) {
            printExceptionMessage(e.getMessage());
            resolveProcessAfterInput(consumer, supplier);
        }
    }

    public static <T> T resolveInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            printExceptionMessage(e.getMessage());
            return resolveInput(supplier);
        }
    }

    private static void printExceptionMessage(final String message) {
        System.out.println(String.format(ERROR_PREFIX_FORMAT, message));
    }
}
