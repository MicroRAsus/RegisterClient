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
//    private UUID id;
//    public UUID getId() {
//        return this.id;
//    }
//    public TransactionTransition setId(UUID id) {
//        this.id = id;
//        return this;
//    }
    
    
    private String cashierid;
    private double amount;
    private String transtype;
    private int referenceid;
	private int recordID;
	private Date createdOn;
    
    public String getCashierID() {
        return this.cashierid;
    }
    public TransactionTransition setCashierID(String cashierid) {
        this.cashierid = cashierid;
        return this;
    }
    
    public double getAmount() {
        return this.amount;
    }
    public TransactionTransition setAmount(double amount) {
        this.amount = amount;
        return this;
    }
    
    public String getTransType() {
        return this.transtype;
    }
    public TransactionTransition setTransType(String transtype) {
        this.transtype = transtype;
        return this;
    }
    
    public int getReferenceID() {
        return this.referenceid;
    }
    public TransactionTransition setReferenceID(int referenceid) {
        this.referenceid = referenceid;
        return this;
    }

    public int getRecordID() {
        return this.recordID;
    }
    public TransactionTransition setRecordID(int recordID) {
        this.recordID = recordID;
        return this;
    }
    
    public Date getCreatedOn() {
        return this.createdOn;
    }
    public TransactionTransition setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;

    }
    
    private ArrayList<Product> productArrayList;

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }
    public TransactionTransition setProductArray(ArrayList<Product> arrayList){
        this.productArrayList = arrayList;
        return this;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        //destination.writeByteArray((new UUIDToByteConverterCommand()).setValueToConvert(this.id).execute());
        destination.writeString(this.cashierid);
        destination.writeDouble(this.amount);
        destination.writeString(this.transtype);
        destination.writeInt(this.referenceid);
        destination.writeInt(this.recordID);
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
    	this.cashierid = "0";
    	this.amount = 0.0;
    	this.transtype = "S";
    	this.referenceid = 0;
    	this.recordID = 0;
        this.createdOn = new Date();
        this.productArrayList = new ArrayList<Product>();
    }
    
    public TransactionTransition(Parcel transactionTransitionParcel){
        //this.id = (new ByteToUUIDConverterCommand()).setValueToConvert(transactionTransitionParcel.createByteArray()).execute();
	    this.cashierid = transactionTransitionParcel.readString();
	    this.amount = transactionTransitionParcel.readInt();
	    this.transtype = transactionTransitionParcel.readString();
	    this.referenceid = transactionTransitionParcel.readInt();
	    this.recordID = transactionTransitionParcel.readInt();
        this.createdOn = new Date();
        this.createdOn.setTime(transactionTransitionParcel.readLong());
        this.productArrayList = transactionTransitionParcel.readArrayList(Product.class.getClassLoader());
    }
}
