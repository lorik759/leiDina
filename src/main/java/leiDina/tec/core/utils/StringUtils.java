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

    public static boolean isNotEmpty(String string) {
        if (string == null) {
            return false;
        }
        return !string.isEmpty();
    }
}
