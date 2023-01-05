package egonzalez.opanga.assessment.i18n;


import egonzalez.opanga.assessment.factories.ErrorMessageFactory;
import egonzalez.opanga.assessment.i18n.english.EnglishErrorMessages;

public class MessageProvider {
    public static AbstractErrorMessages messages = EnglishErrorMessages.getInstance();     // by default: English Provider;

    public static void setLanguage(LanguageEnum language) {
        AbstractErrorMessages provider = ErrorMessageFactory.create(language);
        if (provider != null)
            messages = provider;
    }
}
