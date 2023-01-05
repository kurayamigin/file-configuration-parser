package egonzalez.opanga.assessment.olders;

import egonzalez.opanga.assessment.parsers.IConfigurationParser;
import egonzalez.opanga.assessment.errors.BadSectionSyntaxException;
import egonzalez.opanga.assessment.models.*;
import egonzalez.opanga.assessment.models.properties.BooleanProperty;
import egonzalez.opanga.assessment.models.properties.KeyValueProperty;
import egonzalez.opanga.assessment.models.properties.MultiValueProperty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Deprecated
public class ConfigParser {
    private String header = "Runtime";
    private Stack<Character> braces;
    private int cursor;
    private final File file;

    boolean readingProperty = false;
    public ConfigParser(File file) {
        braces = new Stack<>();
        cursor = 0;
        this.file = file;
    }

    private String getName(String section) {

        StringBuilder header = new StringBuilder();

        // Get Header
        char c = section.charAt(cursor++);
        while (c != ' ' && c != '{') {
            header.append(c);
            c = section.charAt(cursor++);
        }
        cursor++;

        return header.toString();
    }


    private Section manageSection(String name, String s) {
        Section section = new Section();
        section.setName(name);
        braces.push('{');
        List<String> props = new ArrayList<>();

        while (s.charAt(cursor) != '}') {
            if (s.charAt(cursor) != '\n' && s.charAt(cursor) != '{')
                readingProperty = true;


            if (s.charAt(cursor) == '{') {
                if (props.size() > 1) throw new BadSectionSyntaxException();
                String innerName = getName(s);
                //Call recursions
                Section inner = manageSection(innerName, s);
                section.getContent().getSubsections().put(name, inner);
            }
            if (s.charAt(cursor) == '}') {
                braces.pop();
            }

            //Get Line
            StringBuilder line = new StringBuilder();
            while (s.charAt(cursor) != '\n') {
                line.append(s.charAt(cursor));
                cursor++;
            }
            String[] data = analyzeLine(line.toString().trim());

            if (s.charAt(cursor) == '\n' && data.length != 0) {
                TypeEnum type = validateContent(data);
                Property<?> property = null;
                switch (type) {
                    case Boolean:
                        property = new BooleanProperty(data[0], true);
                        break;
                    case KeyValue:
                        property = new KeyValueProperty(data[0], data[1]);
                        break;
                    case Property:
                        property = new MultiValueProperty(data[0], Arrays.copyOfRange(data, 1, data.length - 1));
                        break;
                }
                if (property != null)
                    section.getContent().getProperties().add(property);
            }
            cursor++;
        }
        return section;
    }

    private String[] analyzeLine(String line) {
        int i = 0;
        List<String> split = new ArrayList<>();
        while (i < line.length()) {
            StringBuilder p = new StringBuilder();
            while (line.charAt(i) != ' ') {
                p.append(line.charAt(i));
                i++;
            }
            if (!p.toString().trim().equals(""))
                split.add(p.toString().trim());
            i++;
        }

        return split.toArray(new String[0]);
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

    public Section parse() {
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Section main = new Section();

        return main;
    }
}








//class Type {
//    private TypeEnum typeEnum;  // KeyValue | Bool | Array (ports 123 123 124)
//}
