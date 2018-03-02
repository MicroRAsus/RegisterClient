package edu.uark.uarkregisterapp.models.api.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.EmployeeCount;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.enums.ApiObject;
import edu.uark.uarkregisterapp.models.api.enums.ProductApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class EmployeeCountService extends BaseRemoteService {


    public ApiResponse<EmployeeCount> getEmployeeCount() {
        return this.readEmployeeCountFromResponse(
                this.<EmployeeCount>performGetRequest(
                        this.buildPath()
                )
        );
    }



    private ApiResponse<EmployeeCount> readEmployeeCountFromResponse(ApiResponse<EmployeeCount> apiResponse) {
        JSONObject rawJsonObject = this.rawResponseToJSONObject(
                apiResponse.getRawResponse()
        );

        if (rawJsonObject != null) {
            apiResponse.setData(
                    (new EmployeeCount()).loadFromJson(rawJsonObject)
            );
        }

        return apiResponse;
    }

    public EmployeeCountService() { super(ApiObject.PRODUCT); }
}
