package parsers.mocks_factory;

import egonzalez.opanga.assessment.models.Content;
import egonzalez.opanga.assessment.models.Section;
import egonzalez.opanga.assessment.models.properties.BooleanProperty;
import egonzalez.opanga.assessment.models.properties.KeyValueProperty;
import egonzalez.opanga.assessment.models.properties.MultiValueProperty;
import egonzalez.opanga.assessment.models.properties.PortProperty;

public class SectionMockFactory {

    public static Section create() {
        Section section = new Section();
        section.setName("runtime");

        section.getContent().getProperties().add(new KeyValueProperty("key1", "value1"));
        section.getContent().getProperties().add(new KeyValueProperty("key2", "value2"));
        section.getContent().getProperties().add(new BooleanProperty("flag1"));
        section.getContent().getProperties().add(new BooleanProperty("Flag45"));

        // System1
        Section system = new Section();
        system.setName("system1");
        system.getContent().getProperties().add(new KeyValueProperty("prop1", "value1"));
        system.getContent().getProperties().add(new KeyValueProperty("prop2", "value2"));
        system.getContent().getProperties().add(new PortProperty("ports", 1234, 5678, 9102));
        section.getContent().getSubsections().put("system1", system);

        // Subsystem1
        Section subsystem = new Section();
        subsystem.setName("subsystem1");
        subsystem.getContent().getProperties().add(new MultiValueProperty("prop3", "value1", "value2", "value3"));
        subsystem.getContent().getProperties().add(new BooleanProperty("flag2"));
        system.getContent().getSubsections().put("subsystem1", subsystem);

        return section;
    }
}
