package validator;

import model.DataRecord;

import java.util.List;

public interface DataValidator {
    boolean isValid(DataRecord record);
    List<String> getValidationErrors();
}