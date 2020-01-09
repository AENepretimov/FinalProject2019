package httphandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public static String requestBodyToString(InputStream input) {
        StringBuilder stringBuilder = new StringBuilder();
        new BufferedReader(new InputStreamReader(input)).lines().forEach(
                string -> stringBuilder.append(string).append("\n")
        );
        return stringBuilder.toString();
    }
}
