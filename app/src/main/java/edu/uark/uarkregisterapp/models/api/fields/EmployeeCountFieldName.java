package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

/**
 * Created by sherr on 3/1/2018.
 */

public enum EmployeeCountFieldName implements FieldNameInterface {

    COUNT("employeeCount");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    EmployeeCountFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
