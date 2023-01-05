package parsers;

import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.Section;
import egonzalez.opanga.assessment.parsers.AsyncFileConfigurationParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parsers.mocks_factory.SectionMockFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AsyncFileConfigurationParserTest {
    private static AsyncFileConfigurationParser parser;
    private static final String path = "./src/main/resources/test/configs/test_config";

    @BeforeAll
    public static void beforeAll() {
        File file = new File(path);
        parser = new AsyncFileConfigurationParser(file);
    }

    @Test
    public void parse_should_asyncReturnParsedSection() {
        // Arrange
        Section expected = SectionMockFactory.create();

        // Act
        Section result = parser.parse();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected.getName(), result.getName());

        List<Property<?>> expectedProperties = expected.getContent().getProperties();
        List<Property<?>> resultProperties = result.getContent().getProperties();
        Assertions.assertEquals(expectedProperties.size(), resultProperties.size());
        for (int i = 0; i < expectedProperties.size(); i++) {
            Assertions.assertEquals(expectedProperties.get(i).getName(), resultProperties.get(i).getName());
            Assertions.assertEquals(expectedProperties.get(i).getClass(), resultProperties.get(i).getClass());
            Assertions.assertEquals(expectedProperties.get(i).getValue().getClass(), resultProperties.get(i).getValue().getClass());
        }

        Map<String, Section> expectedSections = expected.getContent().getSubsections();
        Map<String, Section> resultSubsections = result.getContent().getSubsections();
        Assertions.assertEquals(expectedSections.size(), resultSubsections.size());
        Assertions.assertEquals(expectedSections.get("system1").getName(), resultSubsections.get("system1").getName());
    }
}
