package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

/**
 * Created by Lyru on 2/27/2018 at 10:00PM.
 */

public enum EmployeeFieldName implements FieldNameInterface {
    ID("id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    EMPLOYEE_ID("employee_id"),
    ACTIVE("active"),
    ROLE("role"),
    MANAGER("manager"),
    PASSWORD("password"),
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
