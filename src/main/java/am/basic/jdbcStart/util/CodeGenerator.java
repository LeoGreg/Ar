package am.basic.jdbcStart.util;

public class CodeGenerator {
    public static String digits = "0123456789";

    public static String getGeneratedCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(digits.charAt((int) (Math.random() * digits.length())));
        }
        return stringBuilder.toString();
    }
}
