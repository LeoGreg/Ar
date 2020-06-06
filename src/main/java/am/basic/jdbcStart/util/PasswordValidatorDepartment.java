package am.basic.jdbcStart.util;

public class PasswordValidatorDepartment {


    public static boolean checkPassword(String password) {
        String cleanString = password.replaceAll("\\p{Punct}", "");
        String cleanLetters = cleanString.replaceAll("[0-9]", "");
        String cleanNumbers = cleanString.replaceAll("[A-Za-z]", "");
        String cleanLowerLetters = cleanLetters.replaceAll("[A-Z]", "");
        String cleanUpperLetters = cleanLetters.replaceAll("[a-z]", "");
        return password == null || password.length() < 8 || cleanNumbers.length() < 1 || cleanUpperLetters.length() < 1 || cleanLowerLetters.length() < 1 || (password.length() - cleanString.length()) < 1;
    }

}
