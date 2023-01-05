package factories;

import egonzalez.opanga.assessment.factories.PropertyFactory;
import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.properties.PortProperty;
import factories.test_cases.PropertyFactory_Create_TestCasesProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;


public class PropertyFactoryTest {

    @Test
    public void create_should_createPortProperty() {
        // Arrange
        String[] input = new String[]{"ports", "1234", "1234", "4321"};
        PortProperty expected = new PortProperty("ports", new Integer[]{1234,1234,4321});

        // Act
        Property<?> result = PropertyFactory.create(input);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getName(), result.getName());
        Assertions.assertEquals(expected.getValue().getClass(), result.getValue().getClass());

        Integer[] resultValues = (Integer[])result.getValue();
        Assertions.assertEquals(expected.getValue().length, resultValues.length);

        for (int i = 0; i < resultValues.length; i++) {
            Assertions.assertEquals(expected.getValue()[i], resultValues[i]);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(PropertyFactory_Create_TestCasesProvider.class)
    public void create_should_createProperty(String[] input, Property<?> expected) {
        // Act
        Property<?> result = PropertyFactory.create(input);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getName(), result.getName());
        Assertions.assertEquals(expected.getValue().getClass(), result.getValue().getClass());
    }
}
