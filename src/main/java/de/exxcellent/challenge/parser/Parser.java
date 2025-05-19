package de.exxcellent.challenge.parser;

import java.util.List;

public interface Parser {
    <T> List<T> parse(String filePath, Class<T> type) throws ParsingException;
}
