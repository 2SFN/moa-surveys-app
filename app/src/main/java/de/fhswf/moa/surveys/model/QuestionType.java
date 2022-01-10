package de.fhswf.moa.surveys.model;

/**
 * Defines possible types of questions as an enumerable.
 *
 * The QuestionsType's value describe the identifier that is used for (de)serialization and all
 * other API communication.
 */
public enum QuestionType {
    INFO("INFO"),
    INPUT("INPUT"),
    SINGLE_SELECT("SINGLE_SELECT"),
    MULTI_SELECT("MULTI_SELECT"),
    RATING("RATING");

    private final String value;

    QuestionType(String value) {
        this.value = value;
    }

    /**
     * Parse any string as a question type.
     *
     * @param type Question type as String.
     * @return Corresponding field of QuestionType.
     * @throws RuntimeException In case the given type doesn't match any of the fields.
     */
    public static QuestionType parse(String type) {
        for(QuestionType c : values()) {
            if(c.getValue().equals(type))
                return c;
        }

        throw new RuntimeException(String.format("'%s' is not a valid QuestionType!", type));
    }

    public String getValue() {
        return value;
    }
}
