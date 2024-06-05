package elizagn.utils;

public class StringUtils {
    public static boolean isAlphaNumeric(String string) {
        for (char symbol : string.toCharArray()) {
            if (!Character.isLetterOrDigit(symbol)) {
                return false;
            }
        }
        return true;
    }
}
