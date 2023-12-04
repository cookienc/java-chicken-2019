package util;

import java.util.function.Function;
import java.util.function.Supplier;
import view.OutputView;

public class Retry {


    public static <T> T execute(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return execute(supplier);
        }
    }

    public static <T, R> R execute(Function<T, R> function, T input) {
        try {
            return function.apply(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return execute(function, input);
        }
    }

    public static void execute(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            execute(runnable);
        }
    }
}