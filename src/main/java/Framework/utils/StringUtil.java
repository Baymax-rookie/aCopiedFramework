package Framework.utils;

public class StringUtil {
    public static boolean isEmpty(String text) {
        return text == null || "".equals(text);
    }

    public static String append(String... array) {
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
            sb.append(str);
        }
        return sb.toString();
    }
}
