package main.java.leiDina.tec.core.utils;

/**
 * @author vitor.alves
 */
public class StringUtils {

    public static String[] replaceNewLineAndSplitComma(String string){
        if (isNotEmpty(string)) {
            return string.trim().replace("\n", ",").split(",");
        }
        return new String[]{};
    }

    public static String removeSpaces(String string) {
        return string.replaceAll("^\\s+", "");
    }

    public static boolean isNotEmpty(String string) {
        if (string == null) {
            return false;
        }
        return !string.isEmpty();
    }

    public static boolean isEmpty(String string) {
        return !isNotEmpty(string);
    }

    public static String[] split(String string, String regex) {
        if (isNotEmpty(string)) {
            return string.split(regex);
        }
        return new String[]{};
    }
}
