package egonzalez.opanga.assessment.utils;

import egonzalez.opanga.assessment.errors.AbstractSyntaxException;
import egonzalez.opanga.assessment.errors.InvalidPropertyException;
import egonzalez.opanga.assessment.errors.PropertyParseException;
import egonzalez.opanga.assessment.factories.PropertyFactory;
import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.Property;

public class PropertyUtils {

    public static Property<?> getProperty(String line) {
        if (line.isEmpty()) throw new PropertyParseException(MessageProvider.messages.PROPERTY_ERROR);
        String[] data = line.split("\\p{javaWhitespace}+");
        try {
            return PropertyFactory.create(data);
        } catch (InvalidPropertyException e) {
            throw e;
        } catch (Exception e) {
            throw new PropertyParseException(MessageProvider.messages.PROPERTY_ERROR + ": " + line);
        }
    }


}
