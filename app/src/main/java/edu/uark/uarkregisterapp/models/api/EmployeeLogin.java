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
    private String employee_id;
    private String password;

    public String getEmployeeID() {
        return this.employee_id;
    }
    public EmployeeLogin setEmployeeID(String employee_id) {
        this.employee_id = employee_id;
        return this;
    }

    public String getPassWord() {
        return this.password;
    }
    public EmployeeLogin setPassWord(String password) {
        this.password = password;
        return this;
    }

    @Override
    public EmployeeLogin loadFromJson(JSONObject rawJsonObject) {
        //String value = rawJsonObject.optString(EmployeeFieldName.ID.getFieldName());
        //if (!StringUtils.isBlank(value)) {
            //this.id = UUID.fromString(value);
        //}

        this.employee_id = rawJsonObject.optString(EmployeeLoginFieldName.EMPLOYEE_ID.getFieldName());
        this.password = rawJsonObject.optString(EmployeeLoginFieldName.PASSWORD.getFieldName());

//        if (!StringUtils.isBlank(value)) {
//            try {
//                this.createdOn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US).parse(value);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(EmployeeLoginFieldName.EMPLOYEE_ID.getFieldName(), this.employee_id);
            jsonObject.put(EmployeeLoginFieldName.PASSWORD.getFieldName(), this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public EmployeeLogin() {
        this.employee_id = "";
        this.password = "";
    }

    // Cole: from what I understand, we do not need to pass the employee credentials between activities.
    // That is why I did not adapt the below constructor for this class.
//    public EmployeeLogin(EmployeeTransition employeeTransition) {
//        this.employee_id = employeeTransition.getLastName();
//        this.password = employeeTransition.getPassWord();
//    }
}
