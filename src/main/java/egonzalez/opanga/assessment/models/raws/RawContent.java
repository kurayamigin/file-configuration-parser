package egonzalez.opanga.assessment.models.raws;

import java.util.ArrayList;
import java.util.List;

public class RawContent {
    private final List<String> rawProperties;
    private final List<String> rawSections;

    public RawContent() {
        this.rawProperties = new ArrayList<>();
        this.rawSections = new ArrayList<>();
    }

    public List<String> getRawSections() {
        return rawSections;
    }

    public List<String> getRawProperties() {
        return rawProperties;
    }
}
