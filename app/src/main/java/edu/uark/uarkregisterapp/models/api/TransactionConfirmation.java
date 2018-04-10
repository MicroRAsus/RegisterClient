package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.uark.uarkregisterapp.models.api.fields.ProductFieldName;
import edu.uark.uarkregisterapp.models.api.fields.TransactionFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;

public class TransactionConfirmation implements ConvertToJsonInterface, LoadFromJsonInterface<TransactionConfirmation> {
	private int recordID;
	private Date createdOn;
	
	public int getRecordID()
	{
		return this.recordID;
	}
	
	public TransactionConfirmation setRecordID(int recordID)
	{
		this.recordID = recordID;
		return this;
	}
	
	public Date getCreatedOn()
	{
		return this.createdOn;
	}
	
	public TransactionConfirmation setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
		return this;
	}
	
	public TransactionConfirmation()
	{
		this.recordID = 0;
		this.createdOn = new Date();
	}
	
	public TransactionConfirmation(int recordID, Date createdOn)
	{
		this.recordID = recordID;
		this.createdOn = createdOn;
	}
	
	@Override
	public TransactionConfirmation loadFromJson(JSONObject rawJsonObject) {
		
		this.recordID = rawJsonObject.optInt(TransactionFieldName.RECORD_ID.getFieldName());
		//this.createdOn = rawJsonObject.optDouble(TransactionFieldName.AMOUNT.getFieldName());
		
		String value = rawJsonObject.optString(ProductFieldName.CREATED_ON.getFieldName());
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
			jsonObject.put(TransactionFieldName.RECORD_ID.getFieldName(), this.recordID);
			jsonObject.put(ProductFieldName.CREATED_ON.getFieldName(), (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)).format(this.createdOn));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
}
