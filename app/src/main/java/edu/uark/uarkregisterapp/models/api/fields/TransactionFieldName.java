package edu.uark.uarkregisterapp.models.api.fields;

import edu.uark.uarkregisterapp.models.api.interfaces.FieldNameInterface;

public enum TransactionFieldName implements FieldNameInterface {
	RECORD_ID ("recordid"),
	CASHIER_ID("cashierid"),
	AMOUNT("amount"),
	TRANS_TYPE("transtype"),
	REFERENCE_ID("referenceid");
	
	private String fieldName;
	public String getFieldName() {
		return this.fieldName;
	}
	
	TransactionFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
