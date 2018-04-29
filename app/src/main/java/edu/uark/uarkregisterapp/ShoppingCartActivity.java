package edu.uark.uarkregisterapp;
//Initially Created by Austin Brown, to be edited by Alicia
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.TransactionConfirmation;
import edu.uark.uarkregisterapp.models.api.services.StoreTransactionService;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class ShoppingCartActivity extends AppCompatActivity {

    private ProductListAdapter productListAdapter;
    private EmployeeTransition employeeTransition;
    private TransactionTransition transactionTransition = new TransactionTransition();
    public static ArrayList<Product> summaryCart = new ArrayList<Product>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
	    this.employeeTransition = this.getIntent().getParcelableExtra("intent_extra_employee");
	    this.productListAdapter = new ProductListAdapter(this, transactionTransition.getProductArrayList());
	    this.getShoppingListView().setAdapter(this.productListAdapter);
        DecimalFormat df = new DecimalFormat("#.00");
        this.getCartTotal().setText("Cost: $" + df.format(calculateCartCost()));
    }
	private ListView getShoppingListView()
	{
		return (ListView) this.findViewById(R.id.list_view_cart);
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
	    transactionTransition = this.getIntent().getParcelableExtra("intent_extra_transaction_transition_to_ShoppingCartActivity");
        if(transactionTransition != null)
        {
	        ArrayList<Product> testing = transactionTransition.getProductArrayList();
	        Log.d("message", String.format("%d", testing.size()));
	        for (Product temp : testing)
	        {
		        Log.d("message", String.format("%s     %f\nstupid", temp.getLookupCode(), temp.getPrice()));
	        }
	        this.productListAdapter = new ProductListAdapter(this, transactionTransition.getProductArrayList());
	        this.getShoppingListView().setAdapter(this.productListAdapter);
	        DecimalFormat df = new DecimalFormat("#.00");
	        this.getCartTotal().setText("Cost: $" + df.format(calculateCartCost()));
        }
    }

    public void cancelTransactionButtonOnClick(View view){
        Intent intent = new Intent(ShoppingCartActivity.this, HomeScreenActivity.class);
        intent.putExtra(
                getString(R.string.intent_extra_employee),
                employeeTransition
        );
        ShoppingCartActivity.this.startActivity(intent);
    }
    public void checkoutButtonOnClick(View view)
    {
	    (new StoreTransactionOnServer()).execute();
	    transactionTransition.setAmount(calculateCartCost());
	    summaryCart = transactionTransition.getProductArrayList();
    }
    public void findItemsButtonOnClick(View view)
    {
	    if(transactionTransition == null)
	    {
	    	transactionTransition = new TransactionTransition();
	    	transactionTransition.setCashierID(employeeTransition.getEmployeeID());
	    }
        Intent intent = new Intent(this.getApplicationContext(), FindProductsActivity.class);
	    intent.putExtra(
			    getString(R.string.intent_extra_transaction_transition_from_ShoppingCartActivity),
			    transactionTransition
	    );
	    this.startActivity(intent);
    }
    private ListView getShoppingCartListView()
    {
        return (ListView) this.findViewById(R.id.list_view_cart);
    }
    private double calculateCartCost()
    {
        double cartCost = 0.00;
        //for(Product temp: cart)
	    for(Product temp: transactionTransition.getProductArrayList())
        {
            cartCost += temp.getPrice() * temp.getCount();
        }
        return cartCost;
    }
    private TextView getCartTotal()
    {
        return (TextView) this.findViewById(R.id.cart_total);
    }
    private class StoreTransactionOnServer extends AsyncTask<Void, Void, Boolean> {
	    @Override
	    protected void onPreExecute() {
            this.loadingTransactionDetailsFromServerAlert.show();
            loadingTransactionDetailsFromServerAlert.dismiss();
        }
	    @Override
	    protected Boolean doInBackground(Void... params) {
		    Transaction transaction = new Transaction(transactionTransition);
            ApiResponse<TransactionConfirmation> apiResponse = (new StoreTransactionService()).getTransactionConfirmation(transaction);
            if(apiResponse.isValidResponse()) {
                transactionTransition.setRecordID(apiResponse.getData().getRecordID());
                transactionTransition.setCreatedOn(apiResponse.getData().getCreatedOn());
            }
            if(employeeTransition != null) {
                transactionTransition.setCashierID(employeeTransition.getEmployeeID());
            }
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
}
