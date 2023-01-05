package egonzalez.opanga.assessment.errors;

public abstract class AbstractSyntaxException extends RuntimeException {
    public AbstractSyntaxException() {
    }

    public AbstractSyntaxException(String message) {
        super(message);
    }
}
