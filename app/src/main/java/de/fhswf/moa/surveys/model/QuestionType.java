package de.fhswf.moa.surveys.model;

/**
 * Definiert mögliche Frage-Typen als Enumerable.
 * <p>
 * Das value-Feld beschreibt den Identifier des jeweiligen Typen als String, so wie er in der
 * Kommunikation mit der API verwendet wird.
 */
public enum QuestionType {
    INFO("INFO"),
    INPUT("INPUT"),
    SINGLE_SELECT("SINGLE_SELECT"),
    MULTI_SELECT("MULTI_SELECT"),
    RATING("RATING"),
    END("END");


    private final String value;

    QuestionType(String value) {
        this.value = value;
    }

    /**
     * Interpretiere einen String als Frage-Typen.
     *
     * @param type String-Darstellung eines Frage-Typen.
     * @return Entsprechendes QuestionType Feld.
     * @throws RuntimeException Geworfen, wenn der String zu keinem Feld passt.
     */
    public static QuestionType parse(String type) {
        for (QuestionType c : values()) {
            if (c.getValue().equals(type))
                return c;
        }

        throw new RuntimeException(String.format("'%s' is not a valid QuestionType!", type));
    }

    public String getValue() {
        return value;
    }
}
