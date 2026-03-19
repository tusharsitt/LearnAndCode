package parser;

import model.DataRecord;

import java.time.LocalDate;

public class CsvDataParser implements DataParser {
    @Override
    public DataRecord parse(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        String id = parts[0].trim();
        String name = parts[1].trim();
        Double value = Double.parseDouble(parts[2].trim());
        LocalDate date = parts.length >= 4 ? LocalDate.parse(parts[3].trim()) : null;

        return new DataRecord(id, name, value, date);
    }
}
