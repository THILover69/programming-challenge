package de.exxcellent.challenge.parser;

import de.exxcellent.challenge.parser.csv.CsvParser;

public class ParserFactory {

    public static Parser getParser(ParserType type) {
        return switch (type) {
            case CSV -> new CsvParser();
        };
    }
}
