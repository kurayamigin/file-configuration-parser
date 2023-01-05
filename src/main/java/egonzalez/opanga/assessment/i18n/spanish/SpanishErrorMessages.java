package egonzalez.opanga.assessment.i18n.spanish;

import egonzalez.opanga.assessment.i18n.AbstractErrorMessages;

public class SpanishErrorMessages extends AbstractErrorMessages {

    // Singleton
    private static SpanishErrorMessages instance;
    public static SpanishErrorMessages getInstance() {
        if (instance == null)
            instance = new SpanishErrorMessages();
        return instance;
    }

    private SpanishErrorMessages() {
        super.HEADER_SYNTAX_ERROR = "Error de sintaxis en la cabezera";
        super.INVALID_HEADER_NAME = "Nombre de cabezera inválido";
        super.SYNTAX_ERROR = "Error de sintaxis";
        super.BRACES_ERROR = "Error en las llaves";
        super.PROPERTY_ERROR = "Error en la sintaxis de una propierdad";
        super.DUPLICATED_SECTION = "Sección duplicada";
        super.FILE_NOT_FOUND = "Archivo no encontrado";
        super.THREAD_ERROR = "Error con los hilos de ejecución";
        super.INVALID_PROPERTY_TYPE = "Tipo de propiedad inválido";

        super.TEMPLATE_DUPLICATED_PROPERTY = "La propiedad %s se encuentra duplicada";
        super.TEMPLATE_HEADER_NOT_MATCH = "La cabecera %s no concuerda con el token de cabecera '%s'.";

    }


}
