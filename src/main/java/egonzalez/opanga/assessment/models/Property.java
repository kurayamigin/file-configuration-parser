package egonzalez.opanga.assessment.models;

public abstract class Property<ValueType> {
    private String name;
    private ValueType value;

    public Property(String name, ValueType value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ValueType getValue() {
        return value;
    }

    public void setValue(ValueType value) {
        this.value = value;
    }
}
