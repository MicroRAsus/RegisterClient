package edu.uark.uarkregisterapp.models.api.enums;

import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

/**
 * Created by sherr on 3/4/2018.
 */

public enum EmployeeLoginApiMethod implements PathElementInterface {
    NONE(""),
    EMPLOYEE_LOGIN("Login");

    @Override
    public String getPathValue() {
        return value;
    }

    private String value;

    EmployeeLoginApiMethod(String value) {
        this.value = value;
    }
}