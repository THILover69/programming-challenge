package de.exxcellent.challenge.parser;

/**
 * Exception class which is thrown when there is an error during parsing.
 */
public class ParsingException extends RuntimeException {
    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingException(String message) {
        super(message);
    }
}
