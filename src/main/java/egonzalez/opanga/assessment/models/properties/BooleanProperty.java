package egonzalez.opanga.assessment.models.properties;

import egonzalez.opanga.assessment.models.Property;

public class BooleanProperty extends Property<Boolean> {
    public BooleanProperty(String name, Boolean value) {
        super(name, value);
    }

    public BooleanProperty(String name) {
        super(name, true);
    }
}