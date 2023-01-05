package egonzalez.opanga.assessment.parsers;

import egonzalez.opanga.assessment.models.Section;
import egonzalez.opanga.assessment.utils.ValidationUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class AsyncFileConfigurationParser extends FileConfigurationParser {

    public AsyncFileConfigurationParser(File file) {
        super(file);
    }

    @Override
    protected void fillContentSubsections(Section section, List<String> sectionsLines) {
        List<CompletableFuture<Section>> threads = new ArrayList<>();
        sectionsLines.forEach(line -> {
            CompletableFuture<Section> sectionCompletableFuture = CompletableFuture.supplyAsync(() -> parseSection(new Scanner(line)));
            threads.add(sectionCompletableFuture);
        });

        threads.stream().map(CompletableFuture::join).forEach(s -> {
            ValidationUtils.validateDuplicatedSection(s.getName(), section.getContent().getSubsections());
            section.getContent().getSubsections().put(s.getName(), s);
        });
    }
}
