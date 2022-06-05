package lesson1;

import java.util.StringJoiner;

public class Lesson {
    public static void main(String[] args) {
        String client1 = "<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client>";
        System.out.println(client1);
        String result1 = mask(client1);
        System.out.println(result1);
    }
    public static String mask(String text) {

        String hiding = "*";
        int startMarker = text.indexOf("<data>") + 6;
        int endMarker = text.indexOf("</data>");

        if (startMarker < 6 || endMarker < 0 || startMarker == endMarker ) {
            return text;
        }

        String[] parts = text.substring(startMarker, endMarker).split(";");
        StringJoiner joiner = new StringJoiner(";");

        for (int i=0; i<parts.length; i++) {
            if (parts[i].matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")) {
                int length = parts[i].length();
                parts[i] = parts[i].substring(0, 4) + hiding.repeat(length - 8) + parts[i].substring(length - 4, length);

            } else if (parts[i].matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                int at = parts[i].indexOf("@");
                int lastDot = parts[i].lastIndexOf(".");
                parts[i] = parts[i].substring(0, at-1) + hiding + "@" + hiding.repeat(lastDot - at - 1) + parts[i].substring(lastDot);

            } else {
                String[] fioParts = parts[i].split(" ");
                int fLength = fioParts[0].length();
                fioParts[0] = fioParts[0].charAt(0) + hiding.repeat(fLength - 2) + fioParts[0].charAt(fLength - 1);
                fioParts[2] = fioParts[2].charAt(0) + ".";
                parts[i] = String.join(" ", fioParts[0], fioParts[1], fioParts[2]);
            }
            joiner.add(parts[i]);
        }

        return text.substring(0, startMarker) + joiner + text.substring(endMarker);
    }
}
