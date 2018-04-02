package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.fields.ProductFieldName;
import edu.uark.uarkregisterapp.models.api.fields.TransactionFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class Transaction implements ConvertToJsonInterface, LoadFromJsonInterface<Transaction> {
	
	private Integer transactionID;
	private Date createdOn;
	private double totalPrice;
	private String employeeID;
	
	public int getTransactionID() {
		return this.transactionID;
	}
	
	public Transaction setTransactioinId(int transactionID) {
		this.transactionID = transactionID;
		return this;
	}
	
	public Date getCreatedOn() {
		return this.createdOn;
	}
	
	public Transaction setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}
	
	public Transaction setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
		return this;
	}
	
	public String getEmployeeID() {
		return this.employeeID;
	}
	
	public Transaction setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
		return this;
	}
	
	
	@Override
	public Transaction loadFromJson(JSONObject rawJsonObject) {
		
		this.transactionID = rawJsonObject.optInt(TransactionFieldName.TRASACTION_ID.getFieldName());
		this.totalPrice = rawJsonObject.optDouble(TransactionFieldName.TOTAL_PRICE.getFieldName());
		this.employeeID = rawJsonObject.optString(TransactionFieldName.EMPLOYEE_ID.getFieldName());
		
		String value = rawJsonObject.optString(TransactionFieldName.CREATED_ON.getFieldName());
		if (!StringUtils.isBlank(value)) {
			try {
				this.createdOn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return this;
	}
	
	@Override
	public JSONObject convertToJson() {
		JSONObject jsonObject = new JSONObject();
		
		try {
			jsonObject.put(TransactionFieldName.TRASACTION_ID.getFieldName(), this.transactionID.toString());
			jsonObject.put(TransactionFieldName.EMPLOYEE_ID.getFieldName(), this.employeeID);
			jsonObject.put(TransactionFieldName.TOTAL_PRICE.getFieldName(), this.totalPrice);
			jsonObject.put(TransactionFieldName.CREATED_ON.getFieldName(), (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)).format(this.createdOn));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public Transaction() {
		this.transactionID = -100;
		this.employeeID = "";
		this.totalPrice = 0;
		this.createdOn = new Date();
	}
	
	public Transaction(TransactionTransition transactionTransition) {
		this.transactionID = transactionTransition.gettransactionID();
		this.employeeID = transactionTransition.getEmployeeID();
		this.createdOn = transactionTransition.getCreatedOn();
	}
}

