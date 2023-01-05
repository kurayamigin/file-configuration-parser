package egonzalez.opanga.assessment.i18n;


import egonzalez.opanga.assessment.i18n.english.EnglishErrorMessages;
import egonzalez.opanga.assessment.i18n.spanish.SpanishErrorMessages;

public class MessageProvider {
    // by default
    public static AbstractErrorMessages messages = EnglishErrorMessages.getInstance();

    public static void setLanguage(LanguageEnum language) {
        switch (language) {
            case MX: case SP:
                messages = SpanishErrorMessages.getInstance();
                break;
            case US:
                messages = EnglishErrorMessages.getInstance();
        }
    }


}
