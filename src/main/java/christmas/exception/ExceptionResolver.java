package christmas.exception;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionResolver {

    private static final String ERROR_PREFIX_FORMAT = "[ERROR] %s";

    private ExceptionResolver() {
    }

    public static <T> void resolveProcessAfterInput(final Supplier<T> inputSupplier, final Consumer<T> consumer) {
        try {
            consumer.accept(inputSupplier.get());
        } catch (IllegalArgumentException | IllegalStateException e) {
            printExceptionMessage(e.getMessage());
            resolveProcessAfterInput(inputSupplier, consumer);
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
