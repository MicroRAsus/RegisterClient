package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum TransactionFieldName implements FieldNameInterface {
	RECORD_ID ("recordID"),
	CREATED_ON("createdOn"),
	CASHIER_ID("cashierID"),
	AMOUNT("amount"),
	TRANS_TYPE("transType"),
	REFERENCE_ID("referenceID");
	
	private String fieldName;
	public String getFieldName() {
		return this.fieldName;
	}
	
	TransactionFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
