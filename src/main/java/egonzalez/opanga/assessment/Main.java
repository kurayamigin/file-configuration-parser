package egonzalez.opanga.assessment;

import egonzalez.opanga.assessment.i18n.LanguageEnum;
import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.Section;
import egonzalez.opanga.assessment.parsers.AsyncFileConfigurationParser;
import egonzalez.opanga.assessment.parsers.FileConfigurationParser;
import egonzalez.opanga.assessment.parsers.IConfigurationParser;
import egonzalez.opanga.assessment.parsers.MultiThreadingFileConfigurationParser;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        String path = "./src/main/resources/config2";
        MessageProvider.setLanguage(LanguageEnum.MX);
        IConfigurationParser parser = new FileConfigurationParser(new File(path));
        IConfigurationParser asyncParser = new AsyncFileConfigurationParser(new File(path));
        IConfigurationParser multiThreadingParser = new MultiThreadingFileConfigurationParser(new File(path));
        parser.parse(); // Run to prepare possible cache preloading and not affect time measurement.
        asyncParser.parse();


        // Synchronous
        Instant start = Instant.now();
        Section section = parser.parse();
        long timeElapsed = Duration.between(start, Instant.now()).toMillis();
        System.out.printf("execution time for sync: %d \n", timeElapsed);

        // Asynchronous with Futures
        Instant start1 = Instant.now();
        Section asyncSection = asyncParser.parse();
        long timeElapsed1 = Duration.between(start1, Instant.now()).toMillis();
        System.out.printf("execution time for async/futures: %d \n", timeElapsed1);

    }
}
