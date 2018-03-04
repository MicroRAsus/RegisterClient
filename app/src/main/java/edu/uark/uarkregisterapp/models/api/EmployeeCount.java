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
        //String value = rawJsonObject.optString(EmployeeCountFieldName.Count.getFieldName());
        //if (!StringUtils.isBlank(value)) {
        //    this.id = UUID.fromString(value);
        //}

        this.count = rawJsonObject.optInt(EmployeeCountFieldName.COUNT.getFieldName());

        //value = rawJsonObject.optString(EmployeeCountFieldName.CREATED_ON.getFieldName());
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
            jsonObject.put(EmployeeCountFieldName.COUNT.getFieldName(), this.count);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public EmployeeCount() {
        this.count = -10;
    }

// Cole: from what I understand, we do not need to pass the employee count between activities.
// That is why I did not adapt the below constructor for this class.
//    public EmployeeCount(ProductTransition productTransition) {
//        this.id = productTransition.getId();
//        this.count = productTransition.getCount();
//        this.createdOn = productTransition.getCreatedOn();
//        this.lookupCode = productTransition.getLookupCode();
//    }
}
