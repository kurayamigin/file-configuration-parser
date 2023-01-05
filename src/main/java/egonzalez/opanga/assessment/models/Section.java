package egonzalez.opanga.assessment.models;

public class Section {
    private String name;
    private Content content = new Content();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}