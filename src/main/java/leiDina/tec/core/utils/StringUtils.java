package main.java.leiDina.tec.core.utils;

/**
 * A utility class that has several methods for string manipulation.
 *
 * @author vitor.alves
 */
public abstract class StringUtils {

    /**
     * A method that replaces a <>\n</> with commas and splits the string at the commas.
     *
     * @param string a {@link String}.
     * @return the {@param string} split.
     */
    public static String[] replaceNewLineAndSplitComma(String string){
        if (isNotEmpty(string)) {
            return string.trim().replace("\n", ",").split(",");
        }
        return new String[]{};
    }

    /**
     * Removes white space from the string.
     *
     * @param string a {@link String}.
     * @return the {@param string} with no white space.
     */
    public static String removeSpaces(String string) {
        return string.replaceAll("^\\s+", "");
    }

    /**
     * Checks if the string is null or empty.
     *
     * @param string a {@link String}.
     * @return If string is not empty than <>true</> otherwise <>false</>.
     */
    public static boolean isNotEmpty(String string) {
        if (string == null) {
            return false;
        }
        return !string.isEmpty();
    }

    /**
     * Checks if the string is null or empty.
     *
     * @param string a {@link String}.
     * @return If string is not empty than <>false</> otherwise <>true</>.
     */
    public static boolean isEmpty(String string) {
        return !isNotEmpty(string);
    }

    /**
     * Splits a string by a regular expresion.
     *
     * @param string a {@link String}.
     * @param regex a regular expresion.
     * @return an array of string.
     */
    public static String[] split(String string, String regex) {
        if (isNotEmpty(string)) {
            return string.split(regex);
        }
        return new String[]{};
    }
}
