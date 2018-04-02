package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum TransactionFieldName implements FieldNameInterface {
	TRASACTION_ID("transactionID"),
	CREATED_ON("createdOn"),
	TOTAL_PRICE("totalPrice"),
	EMPLOYEE_ID("employeeID");
	
	private String fieldName;
	public String getFieldName() {
		return this.fieldName;
	}
	
	TransactionFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
