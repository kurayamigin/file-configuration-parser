package egonzalez.opanga.assessment.parsers;

import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.Section;
import egonzalez.opanga.assessment.models.raws.RawContent;
import egonzalez.opanga.assessment.models.raws.RawSection;
import egonzalez.opanga.assessment.utils.ContentUtils;
import egonzalez.opanga.assessment.utils.PropertyUtils;
import egonzalez.opanga.assessment.utils.SectionUtils;
import egonzalez.opanga.assessment.utils.ValidationUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

public class FileConfigurationParser implements IConfigurationParser {
    protected String headerToken = "Runtime";
    protected final File configurationFile;
    public FileConfigurationParser(File file) {
        this.configurationFile = file;
    }

    @Override
    public Section parse() {
        try {
            Scanner s = new Scanner(configurationFile);
            Section main = parseSection(s);
            ValidationUtils.validateMainHeaderToken(main.getName(), headerToken);
            return main;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(MessageProvider.messages.FILE_NOT_FOUND);
        }
    }



    protected Section parseSection(Scanner s) {
        Section section = new Section();
        RawSection rawSection = SectionUtils.scanSection(s);
        String name = rawSection.getRawName();
        section.setName(name);

        String rawContent = rawSection.getRawContent();
        RawContent content = ContentUtils.scanContent(new Scanner(rawContent));

        fillContent(section, content);
        return section;
    }

    protected void fillContent(Section section, RawContent content) {
        fillContentProperties(section, content.getRawProperties());
        fillContentSubsections(section, content.getRawSections());
    }

    protected void fillContentProperties(Section section, List<String> propertyLines) {
        propertyLines.forEach(line -> {
            Property<?> property = PropertyUtils.getProperty(line);
            //ValidationUtils.validateDuplicatedProperty(property, section.getContent().getProperties());
            section.getContent().getProperties().add(property);
        });
    }



    protected void fillContentSubsections(Section section, List<String> sectionsLines) {
        sectionsLines.forEach(line -> {
            Section subsection = parseSection(new Scanner(line));
            //ValidationUtils.validateDuplicatedSection(subsection.getName(), section.getContent().getSubsections());
            section.getContent().getSubsections().put(subsection.getName(), subsection);
        });
    }
}

