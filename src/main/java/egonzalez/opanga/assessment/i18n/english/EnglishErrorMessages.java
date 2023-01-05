package egonzalez.opanga.assessment.i18n.english;

import egonzalez.opanga.assessment.i18n.AbstractErrorMessages;

public class EnglishErrorMessages extends AbstractErrorMessages {

    // Singleton
    private static EnglishErrorMessages instance;
    public static EnglishErrorMessages getInstance() {
        if (instance == null)
            instance = new EnglishErrorMessages();
        return instance;
    }

    private EnglishErrorMessages() {
        super.HEADER_SYNTAX_ERROR = "Section header syntax error";
        super.INVALID_HEADER_NAME = "Invalid header name";
        super.SYNTAX_ERROR = "Syntax error";
        super.BRACES_ERROR = "Error with braces";
        super.PROPERTY_ERROR = "Error parsing property";
        super.DUPLICATED_SECTION = "Trying to insert duplicates subsections";
        super.FILE_NOT_FOUND = "File not found";
        super.THREAD_ERROR = "Thread interruption";
        super.INVALID_PROPERTY_TYPE = "Type doesn't recognized";

        super.TEMPLATE_DUPLICATED_PROPERTY = "%s property is duplicated";
        super.TEMPLATE_HEADER_NOT_MATCH = "%s does not match with required '%s' header";
    }


}
