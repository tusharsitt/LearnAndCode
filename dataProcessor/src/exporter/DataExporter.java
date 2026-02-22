package exporter;

import model.DataRecord;

import java.util.List;

public interface DataExporter {
    boolean supportsFormat(String format);
    void export(List<DataRecord> records, String filePath);
}