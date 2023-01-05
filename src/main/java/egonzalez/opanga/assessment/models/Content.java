package egonzalez.opanga.assessment.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Content {
    private final List<Property<?>> properties;
    private final Map<String, Section> subsections;

    public Content() {
        this.properties = new ArrayList<>();
        this.subsections = new HashMap<>();
    }

    public List<Property<?>> getProperties() {
        return properties;
    }

    public Map<String, Section> getSubsections() {
        return subsections;
    }
}
