package edu.uark.uarkregisterapp.models.api.services;

import org.json.JSONObject;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.EmployeeCount;
import edu.uark.uarkregisterapp.models.api.EmployeeLogin;
import edu.uark.uarkregisterapp.models.api.enums.ApiObject;
import edu.uark.uarkregisterapp.models.api.enums.EmployeeCountApiMethod;
import edu.uark.uarkregisterapp.models.api.enums.EmployeeLoginApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

/**
 * Created by sherr on 3/4/2018.
 */

public class EmployeeLoginService extends BaseRemoteService {

    String trash;

    public ApiResponse<Employee> getEmployee(EmployeeLogin employeeLogin) { //EmployeeCount employeeCount : was in parameters
        return this.readEmployeeFromResponse(
                this.<Employee>performPostRequest(
                        this.buildPath((new PathElementInterface[] { EmployeeLoginApiMethod.EMPLOYEE_LOGIN}), trash)
                        , employeeLogin.convertToJson()
                )
        );
    }



    private ApiResponse<Employee> readEmployeeFromResponse(ApiResponse<Employee> apiResponse) {
        JSONObject rawJsonObject = this.rawResponseToJSONObject(
                apiResponse.getRawResponse()
        );

        if (rawJsonObject != null) {
            apiResponse.setData(
                    (new Employee()).loadFromJson(rawJsonObject)
            );
        }

        return apiResponse;
    }

    public EmployeeLoginService() { super(ApiObject.EMPLOYEE); }
}
