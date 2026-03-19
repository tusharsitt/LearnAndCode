package services;

import exporter.DataExporter;
import logger.Logger;
import model.DataRecord;
import parser.DataParser;
import reader.DataReader;
import statistics.StatisticsCalculator;
import transformer.DataTransformer;
import validator.DataValidator;

import java.util.ArrayList;
import java.util.List;

public class DataProcessorService {
    private final Logger logger;
    private final DataReader reader;
    private final DataParser parser;
    private final DataValidator validator;
    private final DataTransformer transformer;
    private final StatisticsCalculator statsCalculator;
    private final List<DataExporter> exporters;

    private int errorCount = 0;

    public DataProcessorService(
            Logger logger, DataReader reader, DataParser parser,
            DataValidator validator, DataTransformer transformer,
            StatisticsCalculator statsCalculator, List<DataExporter> exporters) {
        this.logger = logger;
        this.reader = reader;
        this.parser = parser;
        this.validator = validator;
        this.transformer = transformer;
        this.statsCalculator = statsCalculator;
        this.exporters = exporters;
    }

    public List<DataRecord> process(String inputFilePath) {
        logger.info("Starting data processing for: " + inputFilePath);
        List<DataRecord> validRecords = new ArrayList<>();

        try {
            List<String> rawLines = reader.readLines(inputFilePath);

            for (String line : rawLines) {
                if (line == null || line.trim().isEmpty()) continue;

                try {
                    DataRecord record = parser.parse(line);

                    if (validator.isValid(record)) {
                        transformer.transform(record);
                        validRecords.add(record);
                    } else {
                        errorCount++;
                    }
                } catch (Exception e) {
                    errorCount++;
                    logger.error("Error processing line: " + line + " - " + e.getMessage());
                }
            }

            statsCalculator.calculate(validRecords);
            logger.info("Successfully processed " + validRecords.size() + " records.");

        } catch (Exception e) {
            logger.error("Fatal processing error: " + e.getMessage());
        }

        return validRecords;
    }

    public void exportData(List<DataRecord> records, String filePath, String format) {
        for (DataExporter exporter : exporters) {
            if (exporter.supportsFormat(format)) {
                exporter.export(records, filePath);
                return;
            }
        }
        logger.error("No exporter found for format: " + format);
    }
}