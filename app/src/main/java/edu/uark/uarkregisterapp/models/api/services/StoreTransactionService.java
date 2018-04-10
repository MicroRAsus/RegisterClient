package edu.uark.uarkregisterapp.models.api.services;

import org.json.JSONObject;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.TransactionConfirmation;
import edu.uark.uarkregisterapp.models.api.enums.ApiObject;
import edu.uark.uarkregisterapp.models.api.enums.TransactionApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class StoreTransactionService extends BaseRemoteService{
	
	String trash;
	
	public ApiResponse<TransactionConfirmation> getTransactionConfirmation(Transaction transaction) {
		return this.readTransactionConfirmationFromResponse(
				this.<TransactionConfirmation>performPostRequest(
						this.buildPath((new PathElementInterface[] {TransactionApiMethod.TRANSACTION}), trash)
						, transaction.convertToJson()
				)
		);
	}
	
	
	
	private ApiResponse<TransactionConfirmation> readTransactionConfirmationFromResponse(ApiResponse<TransactionConfirmation> apiResponse) {
		JSONObject rawJsonObject = this.rawResponseToJSONObject(
				apiResponse.getRawResponse()
		);
		
		if (rawJsonObject != null) {
			apiResponse.setData(
					(new TransactionConfirmation()).loadFromJson(rawJsonObject)
			);
		}
		
		return apiResponse;
	}
	
	public StoreTransactionService() { super(ApiObject.TRANSACTION); }
}