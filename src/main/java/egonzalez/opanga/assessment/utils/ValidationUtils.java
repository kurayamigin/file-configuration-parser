package egonzalez.opanga.assessment.utils;

import egonzalez.opanga.assessment.errors.*;
import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.Section;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;

public class ValidationUtils {
    private final static String sectionNameRegex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
    private final static String propertyNameRegex = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";

    public static void validateSectionName(String name) {
        String[] headers = name.split("\\p{javaWhitespace}+");
        if (headers.length != 1 || headers[0].isEmpty()) throw new HeaderException(MessageProvider.messages.HEADER_SYNTAX_ERROR);
        if (!headers[0].matches(sectionNameRegex)) throw new HeaderException(MessageProvider.messages.INVALID_HEADER_NAME);
    }

    public static void validatePropertyName(String propertyName) {
        if (!propertyName.matches(propertyNameRegex)) throw new InvalidPropertyException(MessageProvider.messages.INVALID_PROPERTY_NAME + ": " + propertyName);
    }

    public static void validateDuplicatedProperty(Property<?> property, List<Property<?>> properties) {
        Predicate<Property<?>> alreadyExist = p -> p.getName().equals(property.getName());
        if (properties.stream().anyMatch(alreadyExist))
            throw new DuplicatePropertyException(String.format(MessageProvider.messages.TEMPLATE_DUPLICATED_PROPERTY, property.getName()));
    }

    public static void validateMainHeaderToken(String name, String header) {
        if (!name.equalsIgnoreCase(header)) {
            String error = String.format(MessageProvider.messages.TEMPLATE_HEADER_NOT_MATCH, name, header);
            throw new HeaderException(error);
        }
    }

    public static void handleBraces(Stack<String> braces, String brace) {
        if (brace.equals("{"))
            braces.push("{");
        else if (brace.equals("}")) {
            if (braces.isEmpty()) throw new BracesException(MessageProvider.messages.BRACES_ERROR);
            braces.pop();
        }
    }

    public static void validateDuplicatedSection(String sectionName, Map<String, Section> sections) {
        if (sections.containsKey(sectionName))
            throw new DuplicateSectionException(MessageProvider.messages.DUPLICATED_SECTION + ": " + sectionName);
    }
}
