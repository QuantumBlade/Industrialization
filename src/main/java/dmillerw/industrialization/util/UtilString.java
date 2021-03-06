package dmillerw.industrialization.util;

/**
 * Created by Dylan Miller on 1/4/14
 */
public class UtilString {

    /* Helper method that inserts a space before any change from lower case to upper case */
    public static String insertSpacing(String string) {
        StringBuilder sb = new StringBuilder();
        char[] chars = string.toCharArray();

        for (int i=0; i<chars.length; i++) {
            char c = chars[i];

            if (i != chars.length - 1 && (Character.isLowerCase(c) && Character.isUpperCase(chars[i + 1]))) {
                sb.append(c);
                sb.append(" ");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
