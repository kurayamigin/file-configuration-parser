package egonzalez.opanga.assessment.models.raws;

public class RawSection {
    private String rawName;
    private String rawContent;

    public RawSection(String rawName, String rawContent) {
        this.rawName = rawName;
        this.rawContent = rawContent;
    }

    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }
}
