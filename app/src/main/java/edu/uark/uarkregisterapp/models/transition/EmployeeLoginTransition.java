package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import edu.uark.uarkregisterapp.models.api.EmployeeLogin;

/**
 * Created by sherr on 3/4/2018.
 */

public class EmployeeLoginTransition  implements Parcelable {

    private String employee_id;

    public String getEmployeeID() {
        return this.employee_id;
    }

    public EmployeeLoginTransition setEmployeeID(String employee_id) {
        this.employee_id = employee_id;
        return this;
    }



    private String password;

    public String getPassWord() {
        return this.password;
    }

    public EmployeeLoginTransition setPassWord(String manager) {
        this.password = manager;
        return this;
    }


    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeString(this.employee_id);
        destination.writeString(this.password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<EmployeeLoginTransition> CREATOR = new Parcelable.Creator<EmployeeLoginTransition>() {
        public EmployeeLoginTransition createFromParcel(Parcel employeeLoginTransitionParcel) {
            return new EmployeeLoginTransition(employeeLoginTransitionParcel);
        }

        public EmployeeLoginTransition[] newArray(int size) {
            return new EmployeeLoginTransition[size];
        }
    };

    public EmployeeLoginTransition() {
        this.employee_id = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
    }

    public EmployeeLoginTransition(EmployeeLogin employeeLogin) {
        this.employee_id = employeeLogin.getEmployeeID();
        this.password = employeeLogin.getPassWord();
    }

    private EmployeeLoginTransition(Parcel employeeLoginTransitionParcel) {
        this.employee_id = employeeLoginTransitionParcel.readString();
        this.password = employeeLoginTransitionParcel.readString();
    }
}