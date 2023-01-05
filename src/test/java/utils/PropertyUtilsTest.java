package utils;

import egonzalez.opanga.assessment.errors.InvalidPropertyException;
import egonzalez.opanga.assessment.errors.PropertyParseException;
import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.properties.MultiValueProperty;
import egonzalez.opanga.assessment.utils.PropertyUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class PropertyUtilsTest {

    @Test
    public void getProperty_should_splitLine() {
        // Arrange
        String line = "prop1    value5 value7";
        Property<String[]> expected = new MultiValueProperty("prop1", "value5", "value7");

        // Act
        Property<?> result = PropertyUtils.getProperty(line);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getName(), result.getName());
        Assertions.assertEquals(expected.getValue().getClass(), result.getValue().getClass());

        String[] resultValues = (String[])result.getValue();
        Assertions.assertEquals(expected.getValue().length, resultValues.length);

        for (int i = 0; i < resultValues.length; i++) {
            Assertions.assertEquals(expected.getValue()[i], resultValues[i]);
        }
    }

    @Test
    public void getProperty_should_throwPropertyParseException() {
        // Arrange
        String line = "";

        // Act
        Executable act = () -> PropertyUtils.getProperty(line);

        // Assert
        Assertions.assertThrows(PropertyParseException.class, act);
    }
}
