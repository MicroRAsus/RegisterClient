package edu.uark.uarkregisterapp;
//Initially Created by Austin Brown, to be edited by Alicia
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class ShoppingCartActivity extends AppCompatActivity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
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
        //additionally need to invoke server side code prior to making activity switch
	    //Perhaps permanently removed by cole
        //Intent intent = new Intent(this.getApplicationContext(), SummaryScreenActivity.class);
        //this.startActivity(intent);
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
		    Transaction transaction = (new Transaction(transactionTransition));
		
		    //To be adapted by Cole later
//            ApiResponse<Transaction> apiResponse = (new StoreTransactionS()).getEmployee(employeeLogin);
//            if(apiResponse.isValidResponse()) {
//                transactionTransition.setId(apiResponse.getData().getId());
//                transactionTransition.setFirstName(apiResponse.getData().getFirstName());
//                transactionTransition.setLastName(apiResponse.getData().getLastName());
//                transactionTransition.setEmployeeID(apiResponse.getData().getEmployeeID());
//                transactionTransition.setActive(apiResponse.getData().getActive());
//                transactionTransition.setRole(apiResponse.getData().getRole());
//                transactionTransition.setManager(apiResponse.getData().getManager());
//                transactionTransition.setPassWord(apiResponse.getData().getPassWord());
//                transactionTransition.setCreatedOn(apiResponse.getData().getCreatedOn());
//            }
		
		   // return apiResponse.isValidResponse();
		    
		    
		    //MUST BE CHANGED LATER!!
		    return true;
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
        //Do we even need this here? --Cole
	    private EmployeeTransition employeeTransition;
     
     
	    private TransactionTransition transactionTransition;
}
