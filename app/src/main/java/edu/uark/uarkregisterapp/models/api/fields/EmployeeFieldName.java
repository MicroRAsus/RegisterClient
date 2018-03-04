package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

/**
 * Created by Lyru on 2/27/2018 at 10:00PM.
 */

public enum EmployeeFieldName implements FieldNameInterface {
    ID("id"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    EMPLOYEE_ID("employeeID"),
    ACTIVE("active"),
    ROLE("role"),
    MANAGER("manager"),
    PASSWORD("passWord"),
    API_REQUEST_STATUS("apiRequestStatus"),
    API_REQUEST_MESSAGE("apiRequestMessage"),
    CREATED_ON("createdOn");

    private String fieldName;
    public String getFieldName() {
        return this.fieldName;
    }

    EmployeeFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
