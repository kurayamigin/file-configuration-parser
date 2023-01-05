package egonzalez.opanga.assessment;

import egonzalez.opanga.assessment.i18n.LanguageEnum;
import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.Section;
import egonzalez.opanga.assessment.parsers.AsyncFileConfigurationParser;
import egonzalez.opanga.assessment.parsers.FileConfigurationParser;
import egonzalez.opanga.assessment.parsers.IConfigurationParser;
import egonzalez.opanga.assessment.parsers.MultiThreadingFileConfigurationParser;
import egonzalez.opanga.assessment.utils.PrintUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {
    private static final String defaultPath = "./src/main/resources/test/configs/config2";
    public static void main(String[] args) {
        String path = args.length != 0
                ? args[0]
                : defaultPath;

        MessageProvider.setLanguage(LanguageEnum.MX);
        IConfigurationParser parser = new FileConfigurationParser(new File(path));
        IConfigurationParser asyncParser = new AsyncFileConfigurationParser(new File(path));
        IConfigurationParser multiThreadingParser = new MultiThreadingFileConfigurationParser(new File(path));

        // Synchronous
        Section section = parser.parse();

        // Asynchronous with Futures
        //Section asyncSection = asyncParser.parse();
        // Multi-threading Async
        //Section threadSection = multiThreadingParser.parse();

        PrintUtils.print(section, 0);
    }


}
