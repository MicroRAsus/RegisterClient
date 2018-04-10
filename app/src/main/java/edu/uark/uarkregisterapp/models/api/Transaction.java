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
	
	private String cashierid;
	private double amount;
	private String transtype;
	private int referenceid;
	
	public String getCashierID() {
		return this.cashierid;
	}
	
	public Transaction setCashierID(String cashierid) {
		this.cashierid = cashierid;
		return this;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public Transaction setAmount(double amount) {
		this.amount = amount;
		return this;
	}
	
	public String getTransType() {
		return this.transtype;
	}
	
	public Transaction setTransType(String transtype) {
		this.transtype = transtype;
		return this;
	}
	
	public int getReferenceID() {
		return this.referenceid;
	}
	
	public Transaction setReferenceID(int referenceid) {
		this.referenceid = referenceid;
		return this;
	}
	
	public Transaction() {
		this.cashierid = "0";
		this.amount = 50.01;
		this.transtype = "S";
		this.referenceid = 0;
	}
	
	@Override
	public Transaction loadFromJson(JSONObject rawJsonObject) {
		
		this.cashierid = rawJsonObject.optString(TransactionFieldName.CASHIER_ID.getFieldName());
		this.amount = rawJsonObject.optDouble(TransactionFieldName.AMOUNT.getFieldName());
		this.transtype = rawJsonObject.optString(TransactionFieldName.TRANS_TYPE.getFieldName());
		this.referenceid = rawJsonObject.optInt(TransactionFieldName.REFERENCE_ID.getFieldName());
		
		
		return this;
	}
	
	@Override
	public JSONObject convertToJson() {
		JSONObject jsonObject = new JSONObject();
		
		try {
			jsonObject.put(TransactionFieldName.CASHIER_ID.getFieldName(), this.cashierid);
			jsonObject.put(TransactionFieldName.AMOUNT.getFieldName(), this.amount);
			jsonObject.put(TransactionFieldName.TRANS_TYPE.getFieldName(), this.transtype);
			jsonObject.put(TransactionFieldName.REFERENCE_ID.getFieldName(), this.referenceid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	public Transaction(TransactionTransition transactionTransition) {
		this.cashierid = transactionTransition.getCashierID();
		this.amount = transactionTransition.getAmount();
		this.transtype = transactionTransition.getTransType();
		this.referenceid = transactionTransition.getReferenceID();
		for(Product element : transactionTransition.getProductArrayList())
		{
			amount += element.getPrice();
		}
	}
}

