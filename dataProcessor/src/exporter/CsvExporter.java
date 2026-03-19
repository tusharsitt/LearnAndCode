package exporter;

import model.DataRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvExporter implements DataExporter {
    @Override
    public boolean supportsFormat(String format) {
        return "csv".equalsIgnoreCase(format);
    }

    @Override
    public void export(List<DataRecord> records, String filePath) {
        List<String> lines = new ArrayList<>();
        lines.add("ID,NAME,VALUE,DATE,DOUBLED_VALUE,SQUARED_VALUE");

        for (DataRecord record : records) {
            String dateStr = record.getDate().toString() ;
            lines.add(String.format("%s,%s,%s,%s,%s,%s",
                    record.getId(), record.getName(), record.getValue(), dateStr,
                    record.getDoubledValue(), record.getSquaredValue()));
        }

        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV: " + filePath, e);
        }
    }
}
