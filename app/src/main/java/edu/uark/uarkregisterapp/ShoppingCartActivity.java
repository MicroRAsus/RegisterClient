package edu.uark.uarkregisterapp;
//Initially Created by Austin Brown, to be edited by Alicia
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.TransactionConfirmation;
import edu.uark.uarkregisterapp.models.api.services.StoreTransactionService;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class ShoppingCartActivity extends AppCompatActivity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
	    this.employeeTransition = this.getIntent().getParcelableExtra("intent_extra_employee");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void cancelTransactionButtonOnClick(View view){
        //Need to add code to delete cart prior to making activity switch
        //Intent intent = new Intent(this.getApplicationContext(), HomeScreenActivity.class);
        //this.startActivity(intent);
        Intent intent = new Intent(ShoppingCartActivity.this, HomeScreenActivity.class);
        intent.putExtra(
                getString(R.string.intent_extra_employee),
                employeeTransition
        );
        ShoppingCartActivity.this.startActivity(intent);

    }//^Austin Brown 3/30/18*/

    public void checkoutButtonOnClick(View view){
	    (new StoreTransactionOnServer()).execute();
    }
    //^Austin Brown 3/30/18
    public void findItemsButtonOnClick(View view){
        Intent intent = new Intent(this.getApplicationContext(), FindProductsActivity.class);
        this.startActivity(intent);
    }
    //^Austin Brown 3/30/18
    
    
    
    
    private class StoreTransactionOnServer extends AsyncTask<Void, Void, Boolean> {
	
	    @Override
	    protected void onPreExecute() {
		    this.loadingTransactionDetailsFromServerAlert.show();
	    }
	
	    @Override
	    protected Boolean doInBackground(Void... params) {
		    Transaction transaction = new Transaction(transactionTransition);
		    //Log.d("message", String.format("The value of transtype is: %s", transaction.getTransType()));
		    
            ApiResponse<TransactionConfirmation> apiResponse = (new StoreTransactionService()).getTransactionConfirmation(transaction);
            if(apiResponse.isValidResponse()) {
                transactionTransition.setRecordID(apiResponse.getData().getRecordID());
                transactionTransition.setCreatedOn(apiResponse.getData().getCreatedOn());
            }
		    //Log.d("message", String.format("The value of transtype is: %s", transactionTransition.getTransType()));
		    //Temporary for testing
		    transactionTransition.setAmount(50.01);
            transactionTransition.setCashierID(employeeTransition.getEmployeeID());
		    //Log.d("message",String.format("These are the fields of the TransactionTransition object:\ncashiedid: %s\namount: %s\ntranstype: %s\nreferenceid: %s\nrecordID: %s\ncreatedOn: %s", transactionTransition.getCashierID(), transactionTransition.getAmount(), transactionTransition.getTransType(), transactionTransition.getReferenceID(), transactionTransition.getRecordID(), transactionTransition.getCreatedOn()));
		
		    return apiResponse.isValidResponse();
	    }
	
	    @Override
	    protected void onPostExecute(Boolean successfulResponse) {
		    loadingTransactionDetailsFromServerAlert.dismiss();
		
		    if (!successfulResponse) {
			    new android.support.v7.app.AlertDialog.Builder(ShoppingCartActivity.this).
					    setMessage(R.string.alert_dialog_transaction_storage_failure).
					    setPositiveButton(
							    R.string.button_dismiss,
							    new DialogInterface.OnClickListener() {
								    public void onClick(DialogInterface dialog, int id) {
									    dialog.dismiss();
								    }
							    }
					    ).
					    create().
					    show();
		    } else {
			    new android.support.v7.app.AlertDialog.Builder(ShoppingCartActivity.this).
					    setMessage(R.string.alert_dialog_transaction_storage_success).
					    setPositiveButton(
							    R.string.button_dismiss,
							    new DialogInterface.OnClickListener() {
								    public void onClick(DialogInterface dialog, int id) {
									    dialog.dismiss();
								    }
							    }
					    ).
					    create().
					    show();
			    //Log.d("message",String.format("These are the fields of the TransactionTransition object:\ncashiedid: %s\namount: %s\ntranstype: %s\nreferenceid: %s\nrecordID: %s\ncreatedOn: %s", transactionTransition.getCashierID(), transactionTransition.getAmount(), transactionTransition.getTransType(), transactionTransition.getReferenceID(), transactionTransition.getRecordID(), transactionTransition.getCreatedOn()));
			
			    Intent intent = new Intent(ShoppingCartActivity.this, SummaryScreenActivity.class);
			    intent.putExtra(
					    getString(R.string.intent_extra_transaction),
					    transactionTransition
			    );
			    ShoppingCartActivity.this.startActivity(intent);
			
		    }
	    }
	
	    private AlertDialog loadingTransactionDetailsFromServerAlert;
	
	    private StoreTransactionOnServer() {
		    this.loadingTransactionDetailsFromServerAlert = new AlertDialog.Builder(ShoppingCartActivity.this).
				    setMessage(R.string.alert_dialog_retrieving_employee_credentials).
				    create();
	    }
    }
	    private EmployeeTransition employeeTransition;
	    private TransactionTransition transactionTransition = new TransactionTransition();
}
