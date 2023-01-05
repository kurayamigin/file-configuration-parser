package egonzalez.opanga.assessment.utils;

import egonzalez.opanga.assessment.errors.BracesException;
import egonzalez.opanga.assessment.errors.SectionSyntaxException;
import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.raws.RawSection;

import java.util.Scanner;
import java.util.Stack;


public class SectionUtils {

    public static RawSection scanSection(Scanner s) {
        s.useDelimiter("");
        String name = scanSectionName(s);
        ValidationUtils.validateSectionName(name);
        String content = scanSectionContent(s);
        return new RawSection(name, content);
    }

    private static String scanSectionContent(Scanner s) {
        Stack<String> braces = new Stack<>();
        braces.push("{");
        StringBuilder content = new StringBuilder();
        String c;
        while (s.hasNext() && !braces.isEmpty()) {
            c = s.next();
            if (c.equals("{") || c.equals("}"))
                ValidationUtils.handleBraces(braces, c);

            if (c.equals("}") && braces.isEmpty()) // Do not include last closing-brace;
                break;
            content.append(c);
        }

        //Validations
        if (!braces.isEmpty()) throw new BracesException(MessageProvider.messages.BRACES_ERROR);
        // Validate remaining of data;
        StringBuilder remaining = new StringBuilder();
        s.forEachRemaining(remaining::append);
        if (!remaining.toString().trim().equals("")) throw new SectionSyntaxException(MessageProvider.messages.SYNTAX_ERROR);
        return content.toString().trim();
    }

    private static String scanSectionName(Scanner s) {
        StringBuilder name = new StringBuilder();
        String c = s.next();
        while (!c.equals("{")) {
            name.append(c);
            c = s.next();
        }
        return name.toString().trim();
    }
}
