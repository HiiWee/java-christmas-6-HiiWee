package christmas.exception;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionResolver {

    private ExceptionResolver() {
    }

    public static <T> void resolveProcessAfterInput(final Consumer<T> consumer, final Supplier<T> supplier) {
        try {
            consumer.accept(resolveInput(supplier));
        } catch (IllegalArgumentException | IllegalStateException e) {
            printExceptionMessage(e.getMessage());
            resolveProcessAfterInput(consumer, supplier);
        }
    }

    private static <T> T resolveInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            printExceptionMessage(e.getMessage());
            return resolveInput(supplier);
        }
    }

    private static void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
