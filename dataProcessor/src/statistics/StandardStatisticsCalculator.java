package statistics;

import model.DataRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandardStatisticsCalculator implements StatisticsCalculator {
    private final Map<String, Number> stats = new HashMap<>();

    @Override
    public void calculate(List<DataRecord> records) {
        double totalValue = 0;

        for (DataRecord record : records) {
            if (record.getValue() != null) {
                totalValue += record.getValue();
            }
        }

        stats.put("total_records", records.size());
        stats.put("total_value", totalValue);
        stats.put("average_value", records.isEmpty() ? 0 : totalValue / records.size());
    }

    @Override
    public Map<String, Number> getStatistics() {
        return stats;
    }
}
