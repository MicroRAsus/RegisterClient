package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.UUID;

import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import edu.uark.uarkregisterapp.models.api.Employee;

/**
 * Created by Lyru on 2/27/2018 at 3:03PM
 */

public class EmployeeTransition  implements Parcelable {
    private UUID id;

    public UUID getId() {
        return this.id;
    }

    public EmployeeTransition setId(UUID id) {
        this.id = id;
        return this;
    }

    private String first_name;

    public String getFirstName() {
        return this.first_name;
    }

    public EmployeeTransition setFirstName(String first_name) {
        this.first_name = first_name;
        return this;
    }

    private String last_name;

    public String getLastName() {
        return this.last_name;
    }

    public EmployeeTransition setLastName(String last_name) {
        this.last_name = last_name;
        return this;
    }

    private String employee_id;

    public String getEmployeeID() {
        return this.employee_id;
    }

    public EmployeeTransition setEmployeeID(String employee_id) {
        this.employee_id = employee_id;
        return this;
    }

    private String active;

    public String getActive() {
        return this.active;
    }

    public EmployeeTransition setActive(String active) {
        this.active = active;
        return this;
    }

    private String role;

    public String getRole() {
        return this.role;
    }

    public EmployeeTransition setRole(String role) {
        this.role = role;
        return this;
    }

    private String manager;

    public String getManager() {
        return this.manager;
    }

    public EmployeeTransition setManager(String manager) {
        this.manager = manager;
        return this;
    }

    private String password;

    public String getPassWord() {
        return this.password;
    }

    public EmployeeTransition setPassWord(String manager) {
        this.password = manager;
        return this;
    }

    private Date createdOn;

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public EmployeeTransition setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.first_name);
        destination.writeString(this.last_name);
        destination.writeString(this.employee_id);
        destination.writeString(this.active);
        destination.writeString(this.role);
        destination.writeString(this.manager);
        destination.writeString(this.password);
        destination.writeLong(this.createdOn.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<EmployeeTransition> CREATOR = new Parcelable.Creator<EmployeeTransition>() {
        public EmployeeTransition createFromParcel(Parcel employeeTransitionParcel) {
            return new EmployeeTransition(employeeTransitionParcel);
        }

        public EmployeeTransition[] newArray(int size) {
            return new EmployeeTransition[size];
        }
    };

    public EmployeeTransition() {
        this.id = new UUID(0, 0);
        this.createdOn = new Date();
        this.first_name = StringUtils.EMPTY;
        this.last_name = StringUtils.EMPTY;
        this.employee_id = StringUtils.EMPTY;
        this.active = StringUtils.EMPTY;
        this.role = StringUtils.EMPTY;
        this.manager = StringUtils.EMPTY;
        this.password = StringUtils.EMPTY;
    }

    public EmployeeTransition(Employee employee) {
        this.id = employee.getId();
        this.first_name = employee.getFirstName();
        this.createdOn = employee.getCreatedOn();
        this.last_name = employee.getLastName();
        this.employee_id = employee.getEmployeeID();
        this.active = employee.getActive();
        this.role = employee.getRole();
        this.manager = employee.getManager();
        this.password = employee.getPassWord();
    }

    private EmployeeTransition(Parcel employeeTransitionParcel) {
        this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(employeeTransitionParcel.createByteArray()).execute();
        this.first_name = employeeTransitionParcel.readString();
        this.last_name = employeeTransitionParcel.readString();
        this.employee_id = employeeTransitionParcel.readString();
        this.active = employeeTransitionParcel.readString();
        this.role = employeeTransitionParcel.readString();
        this.manager = employeeTransitionParcel.readString();
        this.password = employeeTransitionParcel.readString();
        this.createdOn = new Date();
        this.createdOn.setTime(employeeTransitionParcel.readLong());
    }
}