package egonzalez.opanga.assessment.factories;

import egonzalez.opanga.assessment.errors.InvalidPropertyException;
import egonzalez.opanga.assessment.i18n.MessageProvider;
import egonzalez.opanga.assessment.models.Property;
import egonzalez.opanga.assessment.models.TypeEnum;
import egonzalez.opanga.assessment.models.properties.BooleanProperty;
import egonzalez.opanga.assessment.models.properties.KeyValueProperty;
import egonzalez.opanga.assessment.models.properties.MultiValueProperty;
import egonzalez.opanga.assessment.models.properties.PortProperty;
import egonzalez.opanga.assessment.utils.GeneralUtils;
import egonzalez.opanga.assessment.utils.ValidationUtils;

import java.util.Arrays;

public class PropertyFactory {

    public static Property<?> create(String[] data) {
        TypeEnum type = getType(data);
        ValidationUtils.validatePropertyName(data[0]);
        switch (type) {
            case Port:
                Integer[] integers = GeneralUtils.str2int(Arrays.copyOfRange(data, 1, data.length));
                return new PortProperty(data[0], integers);
            case Boolean:
                return new BooleanProperty(data[0], true);
            case KeyValue:
                return new KeyValueProperty(data[0], data[1]);
            case Property:
                return new MultiValueProperty(data[0], Arrays.copyOfRange(data, 1, data.length));
        }
        throw new InvalidPropertyException(MessageProvider.messages.INVALID_PROPERTY_TYPE);
    }


    public static TypeEnum getType(String[] data) {
        if (data.length == 1)
            return TypeEnum.Boolean;
        if (data.length > 1 && data[0].trim().equals(PortProperty.KEY))
            return TypeEnum.Port;
        if (data.length == 2)
            return TypeEnum.KeyValue;
        return TypeEnum.Property;
    }
}
