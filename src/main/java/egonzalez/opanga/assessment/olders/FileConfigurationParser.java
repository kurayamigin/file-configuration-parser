package egonzalez.opanga.assessment.olders;

import egonzalez.opanga.assessment.parsers.IConfigurationParser;
import egonzalez.opanga.assessment.models.*;
import egonzalez.opanga.assessment.models.properties.BooleanProperty;
import egonzalez.opanga.assessment.models.properties.KeyValueProperty;
import egonzalez.opanga.assessment.models.properties.MultiValueProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

@Deprecated
public class FileConfigurationParser {
    private File file;
    private final Scanner reader;

    public FileConfigurationParser(File file) throws FileNotFoundException {
        this.file = file;
        this.reader = new Scanner(file);
    }


    public Section parse() {
        if (!reader.hasNext()) throw new RuntimeException();

        //Get header
        String name = getHeader();

        //Get Content
        String content = getContent(reader);
        return createSection(name, reader);
    }



    private Section createSection(String name, Scanner reader) {
        Section section = new Section();
        section.setName(name);

        //Get Content
        String content = getContent(reader);
        processContent(section, content);
        return section;
    }

    private String getContent(Scanner reader) {
        StringBuilder content = new StringBuilder();
        Stack<String> braces = new Stack<>();

        String brace = reader.next(); //Move cursor to brace
        if (!brace.equals("{")) throw new RuntimeException("No brace found, syntax error.");
        braces.push("{");

        reader.useDelimiter("");
        while (reader.hasNext()) {
            String d = reader.next();
            if (d.equals("{"))
                braces.push("{");
            else if (d.equals("}"))
                braces.pop();

            if (d.equals("}") && braces.size() == 0) // Do not include last closing-brace;
                break;
            content.append(d);
        }
        if (braces.size() != 0)
            throw new RuntimeException("Lack of some braces");
        return content.toString().trim();
    }

    private void processContent(Section section, String content) {
        // Find Properties or Subsections
        System.out.println(content);
        Scanner reader = new Scanner(content);
        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            String[] data = line.split("\\p{javaWhitespace}+");
            TypeEnum type = validateContent(data);

            if (type.equals(TypeEnum.Section)) {
                Section inner = new Section();
                String name = data[0];
                String innerContent = getContent(reader);
//                section.getSubsections().put(name, createSection(name, new Scanner(getContent(reader)));


            } else
                section.getContent().getProperties().add(propertyFactory(type, data));
        }
    }

    private String getHeader() {
        return reader.next().trim(); //name of the program
    }

    private TypeEnum validateContent(String[] content) {
        if (content.length == 1) {
            return TypeEnum.Boolean;
        }
        if (content.length == 2 && content[1].equals("{")) {
            return TypeEnum.Section;
        }
        if (content.length == 2) {
            return TypeEnum.KeyValue;
        }
        return TypeEnum.Property;
    }

    Property<?> propertyFactory(TypeEnum type, String[] data) {
        switch (type) {
            case Boolean:
                return new BooleanProperty(data[0], true);
            case KeyValue:
                return new KeyValueProperty(data[0], data[1]);
            case Property:
                return new MultiValueProperty(data[0], Arrays.copyOfRange(data, 1, data.length - 1));
        }
        throw new RuntimeException("Type doesnot recognized");
    }
}