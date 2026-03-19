package validator;

import model.DataRecord;

import java.util.ArrayList;
import java.util.List;

public class StandardDataValidator implements DataValidator {
    private final List<String> errors = new ArrayList<>();

    @Override
    public boolean isValid(DataRecord record) {
        boolean valid = true;
        if (record.getId() == null || record.getId().isEmpty()) {
            errors.add("Record missing ID");
            valid = false;
        }
        if (record.getName() == null || record.getName().isEmpty()) {
            errors.add("Record " + record.getId() + " missing name");
            valid = false;
        }
        return valid;
    }


    @Override
    public List<String> getValidationErrors() {
        return errors;
    }
}