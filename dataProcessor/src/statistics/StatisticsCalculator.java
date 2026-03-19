package statistics;

import model.DataRecord;

import java.util.List;
import java.util.Map;

public interface StatisticsCalculator {
    void calculate(List<DataRecord> records);
    Map<String, Number> getStatistics();
}