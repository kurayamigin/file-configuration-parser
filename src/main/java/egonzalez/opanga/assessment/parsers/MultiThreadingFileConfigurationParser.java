package egonzalez.opanga.assessment.parsers;

import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.Section;
import egonzalez.opanga.assessment.models.multi_threading.ThreadValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class MultiThreadingFileConfigurationParser extends FileConfigurationParser {

    public MultiThreadingFileConfigurationParser(File file) {
        super(file);
    }

    @Override
    protected void fillContentSubsections(Section section, List<String> sectionsLines) {
        List<ThreadValue<Section>> threads = new ArrayList<>();
        sectionsLines.forEach(line -> {
            Supplier<Section> supplier = () -> parseSection(new Scanner(line));
            ThreadValue<Section> thread = new ThreadValue<>(supplier);
            thread.start();
            threads.add(thread);
        });

        // Wait threads
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(MessageProvider.messages.THREAD_ERROR);
            }
        });

        threads.stream().map(ThreadValue::getValue).forEach(s -> {
            //ValidationUtils.validateDuplicatedSection(s.getName(), section.getContent().getSubsections());
            section.getContent().getSubsections().put(s.getName(), s);
        });
    }
}
