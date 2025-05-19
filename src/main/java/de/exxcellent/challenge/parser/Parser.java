package de.exxcellent.challenge.parser;

import java.util.List;

/**
 * Parser interface for parsing data from files.
 */
public interface Parser {

    /**
     * Parses the given file and converts each row into an object of the given type.
     *
     * @param filePath the path to the file to parse
     * @param type     the class type to map the data to
     * @param <T>      the type of the resulting objects
     * @return a list of parsed objects
     * @throws ParsingException if reading or parsing the file fails
     */
    <T> List<T> parse(String filePath, Class<T> type) throws ParsingException;
}
