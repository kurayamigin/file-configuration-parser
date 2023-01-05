package egonzalez.opanga.assessment.models.properties;

import egonzalez.opanga.assessment.models.Property;

public class PortProperty extends Property<Integer[]> {
    public static String KEY = "ports";

    public PortProperty(String name, Integer... value) {
        super(name, value);
    }
}
