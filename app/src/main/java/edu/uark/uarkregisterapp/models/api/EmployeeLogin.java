package edu.uark.uarkregisterapp.models.api;

import org.json.JSONException;
import org.json.JSONObject;
import edu.uark.uarkregisterapp.models.api.fields.EmployeeLoginFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;


/**
 * Created by sherr on 3/1/2018.
 */

public class EmployeeLogin implements ConvertToJsonInterface, LoadFromJsonInterface<EmployeeLogin> {
    private String employeeID;
    private String passWord;

    public String getEmployeeID() {
        return this.employeeID;
    }
    public EmployeeLogin setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    public String getPassWord() {
        return this.passWord;
    }
    public EmployeeLogin setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    @Override
    public EmployeeLogin loadFromJson(JSONObject rawJsonObject) {

        this.employeeID = rawJsonObject.optString(EmployeeLoginFieldName.EMPLOYEE_ID.getFieldName());
        this.passWord = rawJsonObject.optString(EmployeeLoginFieldName.PASSWORD.getFieldName());

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(EmployeeLoginFieldName.EMPLOYEE_ID.getFieldName(), this.employeeID);
            jsonObject.put(EmployeeLoginFieldName.PASSWORD.getFieldName(), this.passWord);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public EmployeeLogin() {
        this.employeeID = "";
        this.passWord = "";
    }



    // Cole: from what I understand, we do not need to pass the employee credentials between activities.
    // That is why I did not adapt the below constructor for this class.
//    public EmployeeLogin(EmployeeTransition employeeTransition) {
//        this.employeeID = employeeTransition.getLastName();
//        this.passWord = employeeTransition.getPassWord();
//    }
}
