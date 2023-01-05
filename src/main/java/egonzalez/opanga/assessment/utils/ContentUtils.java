package egonzalez.opanga.assessment.utils;

import egonzalez.opanga.assessment.errors.BracesException;
import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.raws.RawContent;

import java.util.Scanner;
import java.util.Stack;


public class ContentUtils {

    public static RawContent scanContent(Scanner s) {
        s.useDelimiter("");
        RawContent content = new RawContent();
        while (s.hasNext()) {
            StringBuilder p = new StringBuilder();
            boolean isSection = false;
            String c = s.next();
            p.append(c);
            while (!c.equals("\n") && s.hasNext()) {
                // IF is section
                if (c.equals("{")) {
                    p.append(getStringContent(s));
                    content.getRawSections().add(p.toString().trim());
                    isSection = true;
                    break;
                }
                c = s.next();
                p.append(c);

            }
            //maybe p empty
            String b = p.toString().trim();
            if (!isSection && !b.equals(""))
                content.getRawProperties().add(b);
        }
        return content;
    }

    private static String getStringContent(Scanner s) {
        Stack<String> braces = new Stack<>();
        StringBuilder content = new StringBuilder();
        String c;

        braces.push("{");
        while (!braces.isEmpty() && s.hasNext()) {
            c = s.next();
            content.append(c);
            ValidationUtils.handleBraces(braces, c);
        }
        if (!braces.isEmpty()) throw new BracesException(MessageProvider.messages.BRACES_ERROR);

        return content.toString().trim();
    }
}

