package egonzalez.opanga.assessment.models.properties;

import egonzalez.opanga.assessment.models.Property;

public class MultiValueProperty extends Property<String[]> {
    public MultiValueProperty(String name, String... value) {
        super(name, value);
    }
}


