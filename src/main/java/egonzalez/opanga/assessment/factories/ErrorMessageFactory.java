package egonzalez.opanga.assessment.factories;

import egonzalez.opanga.assessment.i18n.AbstractErrorMessages;
import egonzalez.opanga.assessment.i18n.LanguageEnum;
import egonzalez.opanga.assessment.i18n.english.EnglishErrorMessages;
import egonzalez.opanga.assessment.i18n.spanish.SpanishErrorMessages;

public class ErrorMessageFactory {
    public static AbstractErrorMessages create(LanguageEnum language) {
        switch (language) {
            case MX: case SP:
                return SpanishErrorMessages.getInstance();
            case US:
                return EnglishErrorMessages.getInstance();
        }
        return null;
    }
}
