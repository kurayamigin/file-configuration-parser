package egonzalez.opanga.assessment.utils;

import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.Section;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class PrintUtils {
        public static void print(Section section, int subsectionIndex) {
            if (section == null) return;

            String tab = getTab(subsectionIndex);
            System.out.printf(tab + "%s: \n", section.getName());
            for (Property<?> property : section.getContent().getProperties()) {
                System.out.printf(tab + "%s %s: %s \n", property.getClass().getSimpleName(), property.getName(), getValues(property.getValue()));
            }

            subsectionIndex++;
            for (Section subsection : section.getContent().getSubsections().values()) {
                System.out.println();
                print(subsection, subsectionIndex);
            }
        }

        private static String getTab(int subsectionIndex) {
            StringBuilder tab = new StringBuilder();
            for (int i = 0; i < subsectionIndex; i++) {
                tab.append("  ");
            }
            return tab.toString();
        }

        public static String getValues(Object value) {
            StringBuilder s = new StringBuilder();

            if (value.getClass().isArray()) {
                Object[] values = (Object[]) value;
                Arrays.stream(values).forEach(v -> s.append(v).append(" "));
            } else {
                s.append(value).append(" ");
            }
            return StringUtils.stripEnd(s.toString(), null);
        }
}
