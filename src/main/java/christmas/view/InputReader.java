package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputReader {

    private InputReader() {
    }

    public static String readInput() {
        return readLine();
    }

    public static List<String> readInputs(final String delimiter) {
        return Arrays.stream(readLine().split(delimiter))
                .map(String::trim)
                .toList();
    }

    private static String readLine() {
        return Console.readLine()
                .trim();
    }
}
