package factories.test_cases;

import egonzalez.opanga.assessment.models.properties.BooleanProperty;
import egonzalez.opanga.assessment.models.properties.KeyValueProperty;
import egonzalez.opanga.assessment.models.properties.MultiValueProperty;
import egonzalez.opanga.assessment.models.properties.PortProperty;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class PropertyFactory_Create_TestCasesProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(
                        new String[]{"ports", "1234", "1234", "4321"},
                        new PortProperty("ports", new Integer[]{1234,1234,4321})),
                Arguments.of(
                        new String[]{"property1", "value1", "value2"},
                        new MultiValueProperty("property1", "value1")
                ),
                Arguments.of(
                        new String[]{"key1", "value1"},
                        new KeyValueProperty("key1", "value1")
                ),
                Arguments.of(
                        new String[]{"flag1"},
                        new BooleanProperty("flag1", true)
                )
        );
    }
}
