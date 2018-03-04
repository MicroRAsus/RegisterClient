package edu.uark.uarkregisterapp.models.transition;

import android.os.Parcel;
import android.os.Parcelable;

import edu.uark.uarkregisterapp.models.api.EmployeeCount;


/**
 * Created by sherr on 3/3/2018.
 */

public class EmployeeCountTransition implements Parcelable {
    
        private int count;
        public int getCount() {
            return this.count;
        }
        public EmployeeCountTransition setCount(int count) {
            this.count = count;
            return this;
        }

        @Override
        public void writeToParcel(Parcel destination, int flags) {
            destination.writeInt(this.count);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Parcelable.Creator<EmployeeCountTransition> CREATOR = new Parcelable.Creator<EmployeeCountTransition>() {
            public EmployeeCountTransition createFromParcel(Parcel employeeCountTransitionParcel) {
                return new EmployeeCountTransition(employeeCountTransitionParcel);
            }

            public EmployeeCountTransition[] newArray(int size) {
                return new EmployeeCountTransition[size];
            }
        };

        public EmployeeCountTransition() {
            this.count = -1;
        }

        public EmployeeCountTransition(EmployeeCount employeeCount) {
            this.count = employeeCount.getCount();
        }

        private EmployeeCountTransition(Parcel employeeCountTransitionParcel) {
            this.count = employeeCountTransitionParcel.readInt();
        }
    }