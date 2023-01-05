package egonzalez.opanga.assessment.models.properties;

import egonzalez.opanga.assessment.models.Property;

public class KeyValueProperty extends Property<String> {
    private String key;
    private String value;

    public KeyValueProperty(String name, String value) {
        super(name, value);
        key = name;
        this.value = value;
    }
}