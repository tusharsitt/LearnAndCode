package parser;

import model.DataRecord;

public interface DataParser {
    DataRecord parse(String line) throws IllegalArgumentException;
}