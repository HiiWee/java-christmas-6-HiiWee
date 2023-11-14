package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.input.InputCommonValidator;
import java.util.Arrays;
import java.util.List;

public class InputReader {

    private static final String DELIMITER = ",";

    private InputReader() {
    }

    public static int readDate() {
        String inputNumber = readLine();
        InputCommonValidator.validateDate(inputNumber);
        return Integer.parseInt(inputNumber);
    }

    public static List<String> readMenus() {
        List<String> inputs = Arrays.stream(readLine().split(DELIMITER))
                .map(String::trim)
                .toList();
        InputCommonValidator.validateInputMenus(inputs);
        return inputs;
    }

    private static String readLine() {
        return Console.readLine()
                .trim();
    }
}
