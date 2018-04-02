package edu.uark.uarkregisterapp.models.transition;

/**
 * Created by Russell on 4/1/2018.
 */

//Does not contain copy constructor for TransactionTransition(Transaction transaction) becasue
//class does not exist yet

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;

import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.commands.converters.ByteToUUIDConverterCommand;
import edu.uark.uarkregisterapp.commands.converters.UUIDToByteConverterCommand;
import android.text.TextUtils;


public class TransactionTransition implements Parcelable{
    private UUID id;
    public UUID getId() {
        return this.id;
    }
    public TransactionTransition setId(UUID id) {
        this.id = id;
        return this;
    }

    private String transactionID;
    public String gettransactionID() {
        return this.transactionID;
    }
    public TransactionTransition settransactionID(String transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    private Date createdOn;
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public TransactionTransition setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;

    }
    private String employee;
    public String getEmployee(){return this.employee;}
    public TransactionTransition setEmployee(String employee){
        this.employee = employee;
        return this;
    }

    private ArrayList<Product> productArrayList;

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }
    public TransactionTransition setProductArray(ArrayList<Product> arrayList){
        this.productArrayList = arrayList;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.employee);
        destination.writeString(this.transactionID);
        destination.writeLong(this.createdOn.getTime());
        destination.writeList(this.productArrayList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<TransactionTransition> CREATOR = new Parcelable.Creator<TransactionTransition>() {
        public TransactionTransition createFromParcel(Parcel transactionTransitionParcel) {
            return new TransactionTransition(transactionTransitionParcel);
        }

        public TransactionTransition[] newArray(int size) {
            return new TransactionTransition[size];
        }
    };

    public TransactionTransition(){
        this.id = new UUID(0, 0);
        this.transactionID = "";
        this.createdOn = new Date();
        this.employee = "";
        this.productArrayList = new ArrayList<Product>();
    }
    public TransactionTransition(Parcel transactionTransitionParcel){
        this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(transactionTransitionParcel.createByteArray()).execute();
        this.transactionID = transactionTransitionParcel.readString();
        this.employee = transactionTransitionParcel.readString();
        this.createdOn = new Date();
        this.createdOn.setTime(transactionTransitionParcel.readLong());
        this.productArrayList = transactionTransitionParcel.readArrayList(Product.class.getClassLoader());

    }
}
