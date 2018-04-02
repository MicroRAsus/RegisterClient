package edu.uark.uarkregisterapp.models.api;

/**
 * Created by Lyru on 2/27/2018 at 2:33PM
 */
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import edu.uark.uarkregisterapp.models.api.fields.EmployeeFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

public class Employee implements ConvertToJsonInterface, LoadFromJsonInterface<Employee> {
    private String first_name;
    private String last_name;
    private String employee_id;
    private String active;
    private String role;
    private String manager;
    private String password;
    private UUID id;
    private Date createdOn;
    public String getFirstName() {
        return this.first_name;
    }
    public Employee setFirstName(String first_name) {
        this.first_name = first_name;
        return this;
    }
    public String getLastName() {
        return this.last_name;
    }

    public Employee setLastName(String last_name) {
        this.last_name = last_name;
        return this;
    }
    public String getEmployeeID() {
        return this.employee_id;
    }
    public Employee setEmployeeID(String employee_id) {
        this.employee_id = employee_id;
        return this;
    }
    public String getActive() {
        return this.active;
    }
    public Employee setActive(String active) {
        this.active = active;
        return this;
    }
    public String getRole() {
        return this.role;
    }
    public Employee setRole(String role) {
        this.role = role;
        return this;
    }
    public String getManager() {
        return this.manager;
    }
    public Employee setManager(String manager) {
        this.manager = manager;
        return this;
    }
    public String getPassWord() {
        return this.password;
    }
    public Employee setPassWord(String password) {
        this.password = password;
        return this;
    }
    public UUID getId() {
        return this.id;
    }
    public Employee setId(UUID id) {
        this.id = id;
        return this;
    }
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public Employee setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public Employee loadFromJson(JSONObject rawJsonObject) {
        String value = rawJsonObject.optString(EmployeeFieldName.ID.getFieldName());
        if (!StringUtils.isBlank(value)) {
            this.id = UUID.fromString(value);
        }

        this.first_name = rawJsonObject.optString(EmployeeFieldName.FIRST_NAME.getFieldName());
        this.last_name = rawJsonObject.optString(EmployeeFieldName.LAST_NAME.getFieldName());
        this.employee_id = rawJsonObject.optString(EmployeeFieldName.EMPLOYEE_ID.getFieldName());
        this.active = rawJsonObject.optString(EmployeeFieldName.ACTIVE.getFieldName());
        this.role = rawJsonObject.optString(EmployeeFieldName.ROLE.getFieldName());
        this.manager = rawJsonObject.optString(EmployeeFieldName.MANAGER.getFieldName());
        this.password = rawJsonObject.optString(EmployeeFieldName.PASSWORD.getFieldName());

        value = rawJsonObject.optString(EmployeeFieldName.CREATED_ON.getFieldName());
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
            jsonObject.put(EmployeeFieldName.ID.getFieldName(), this.id.toString());
            jsonObject.put(EmployeeFieldName.FIRST_NAME.getFieldName(), this.first_name);
            jsonObject.put(EmployeeFieldName.LAST_NAME.getFieldName(), this.last_name);
            jsonObject.put(EmployeeFieldName.ROLE.getFieldName(), this.role);
            jsonObject.put(EmployeeFieldName.ACTIVE.getFieldName(), this.active);
            jsonObject.put(EmployeeFieldName.EMPLOYEE_ID.getFieldName(), this.employee_id);
            jsonObject.put(EmployeeFieldName.PASSWORD.getFieldName(), this.password);
            jsonObject.put(EmployeeFieldName.MANAGER.getFieldName(), this.manager);
            jsonObject.put(EmployeeFieldName.CREATED_ON.getFieldName(), (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)).format(this.createdOn));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public Employee() {
        this.first_name = "";
        this.last_name = "";
        this.employee_id = "";
        this.active = "";
        this.role = "";
        this.manager = "";
        this.password = "";
        this.id = new UUID(0,0);
        this.createdOn = new Date();
    }

    public Employee(EmployeeTransition employeeTransition) {
        this.first_name = employeeTransition.getFirstName();
        this.last_name = employeeTransition.getLastName();
        this.employee_id = employeeTransition.getLastName();
        this.active = employeeTransition.getActive();
        this.role = employeeTransition.getRole();
        this.manager = employeeTransition.getManager();
        this.password = employeeTransition.getPassWord();
        this.id = employeeTransition.getId();
        this.createdOn = employeeTransition.getCreatedOn();
    }
}
