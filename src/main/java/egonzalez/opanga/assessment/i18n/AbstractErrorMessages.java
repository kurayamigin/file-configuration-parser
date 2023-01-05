package egonzalez.opanga.assessment.i18n;

public abstract class AbstractErrorMessages {
    public String BRACES_ERROR = "Error with braces";
    public String SYNTAX_ERROR = "Syntax error";
    public String HEADER_SYNTAX_ERROR = "Section header syntax error";
    public String INVALID_HEADER_NAME = "Invalid header name";
    public String PROPERTY_ERROR = "Error parsing property";
    public String DUPLICATED_SECTION = "Trying to insert duplicates subsections";
    public String FILE_NOT_FOUND = "File not found";
    public String THREAD_ERROR = "Thread interruption";
    public String INVALID_PROPERTY_TYPE = "Type doesn't recognized";
    public String INVALID_PROPERTY_NAME = "Invalid property name";

    // Template
    public String TEMPLATE_DUPLICATED_PROPERTY = "%s property is duplicated";
    public String TEMPLATE_HEADER_NOT_MATCH = "%s does not match with required '%s' header";
}
