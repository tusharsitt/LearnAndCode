package transformer;

import model.DataRecord;

public interface DataTransformer {
    void transform(DataRecord record);
}
