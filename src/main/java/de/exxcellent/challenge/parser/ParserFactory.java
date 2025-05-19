package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.parser.csv.CsvParser;

/**
 * Factory class for creating Parser instances.
 */
public class ParserFactory {

    /**
     * Returns a Parser implementation based on the given type.
     *
     * @param type the parser type
     * @return a Parser instance
     */
    public static Parser getParser(ParserType type) {
        return switch (type) {
            case CSV -> new CsvParser();
        };
    }
}
