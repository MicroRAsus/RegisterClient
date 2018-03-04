package edu.uark.uarkregisterapp.models.api.enums;

import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

/**
 * Created by sherr on 3/1/2018.
 */

public enum EmployeeCountApiMethod implements PathElementInterface {
    NONE(""),
    EMPLOYEE_COUNT("EmployeeCount");

    @Override
    public String getPathValue() {
        return value;
    }

    private String value;

    EmployeeCountApiMethod(String value) {
        this.value = value;
    }
}