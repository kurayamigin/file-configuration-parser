package egonzalez.opanga.assessment.olders;

import java.util.Stack;

@Deprecated
public class Strings {

    static String[] split(String s) {
        Stack<String> braces = new Stack<>();
        int i = 0;
        StringBuilder candidate = new StringBuilder();
        while (s.charAt(i) != '{') {
            candidate.append(s.charAt(i));
            i++;
        }
        braces.push("{");
        i++;

        StringBuilder content = new StringBuilder();
        while (s.charAt(i) != '}') {
            i++;
            Character d = s.charAt(i);
            if (d.equals('{'))
                braces.push("{");
            else if (d.equals('}'))
                braces.pop();

            if (d.equals('}') && braces.size() == 0) // Do not include last closing-brace;
                break;
            content.append(d);
        }
        String[] ss = new String[2];
        ss[0] = candidate.toString().trim();
        ss[1] = content.toString().trim();
        return ss;
    }
}
