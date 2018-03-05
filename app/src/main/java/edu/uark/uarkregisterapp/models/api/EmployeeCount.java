package edu.uark.uarkregisterapp.models.api;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;


import edu.uark.uarkregisterapp.models.api.fields.EmployeeCountFieldName;
import edu.uark.uarkregisterapp.models.api.fields.ProductFieldName;
import edu.uark.uarkregisterapp.models.api.interfaces.ConvertToJsonInterface;
import edu.uark.uarkregisterapp.models.api.interfaces.LoadFromJsonInterface;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

/**
 * Created by sherr on 3/1/2018.
 */


public class EmployeeCount implements ConvertToJsonInterface, LoadFromJsonInterface<EmployeeCount> {

    private int count;
    public int getCount() {
        return this.count;
    }
    public EmployeeCount setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public EmployeeCount loadFromJson(JSONObject rawJsonObject) {

        this.count = rawJsonObject.optInt(EmployeeCountFieldName.COUNT.getFieldName());

        return this;
    }

    @Override
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(EmployeeCountFieldName.COUNT.getFieldName(), this.count);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public EmployeeCount() {
        this.count = -10;
    }

}
