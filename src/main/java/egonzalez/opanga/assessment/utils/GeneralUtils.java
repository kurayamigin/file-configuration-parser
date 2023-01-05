package egonzalez.opanga.assessment.utils;

public class GeneralUtils {

    public static Integer[] str2int(String[] strings) {
        Integer[] integers = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }
}
