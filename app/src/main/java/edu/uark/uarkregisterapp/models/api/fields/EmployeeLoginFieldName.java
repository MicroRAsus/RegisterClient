package edu.uark.uarkregisterapp.models.api.fields;

import java.lang.reflect.Field;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

/**
 * Created by sherr on 3/1/2018.
 */

public enum EmployeeLoginFieldName implements FieldNameInterface {
    EMPLOYEE_ID("employeeID"),
    PASSWORD("passWord");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    EmployeeLoginFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
