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
    //private ProductTransition productTransition;
    //private Product temp = new Product();
    private EmployeeTransition employeeTransition;
    private TransactionTransition transactionTransition = new TransactionTransition();
    //public ArrayList<Product> cart = new ArrayList<Product>();
    public static ArrayList<Product> summaryCart = new ArrayList<Product>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
	    this.employeeTransition = this.getIntent().getParcelableExtra("intent_extra_employee");
	    //this.productTransition = this.getIntent().getParcelableExtra("intent_extra_product");
	    //Product temp = new Product();
        //temporary items in cart to test ListView:
        /*for(int i = 0; i < 5; i++)
        {
            addCartItem(temp);
            cart.get(i).setCount((int)(Math.random() * 5));
        }*/
        //Log.d("message",String.format("These are the fields of the TransactionTransition object:\ncashiedid: %s", transactionTransition.getProductArrayList()));
        //this.productListAdapter = new ProductListAdapter(this, this.cart);
	    //this.productListAdapter = new ProductListAdapter(this, transactionTransition.getProductArrayList());
	    //this.getProductsListView().setAdapter(this.productListAdapter);
	    this.productListAdapter = new ProductListAdapter(this, transactionTransition.getProductArrayList());
	    this.getShoppingListView().setAdapter(this.productListAdapter);
        DecimalFormat df = new DecimalFormat("#.00");
        this.getCartTotal().setText("Cost: $" + df.format(calculateCartCost()));
        ///this.getShoppingCartListView().setAdapter(this.productListAdapter);
//	    if(transactionTransition == null) {
//		    Log.d("message", "The productTransition is null.");
//	    }
//	    else
//	    {
//		    Log.d("message2", "The productTransition is not null");
//	    }
    }
	
	private ListView getShoppingListView()
	{
		return (ListView) this.findViewById(R.id.list_view_cart);
	}

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  // Respond to the action bar's Up/Home button
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //ProductTransition temp = this.getIntent().getParcelableExtra("intent_product_transition_extra");
	    //transactionTransition = this.getIntent().getParcelableExtra(R.string.intent_extra_transaction_transition_ShoppingCartActivity);
	    transactionTransition = this.getIntent().getParcelableExtra("intent_extra_transaction_transition_to_ShoppingCartActivity");
        Log.d("message", "I am here!");
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
	    //this.productListAdapter = new ProductListAdapter(this, transactionTransition.getProductArrayList());
	    //productListAdapter.notifyDataSetChanged();
	    //Product product  = new Product(temp);
        //this.addCartItem(product);
        //this.getShoppingCartListView().setAdapter(this.productListAdapter);
        //this.addCartItem(Product(this.getIntent().getParcelableExtra("intent_product_transition_updated")));
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

    public void checkoutButtonOnClick(View view)
    {
	    (new StoreTransactionOnServer()).execute();
	    //summaryCart = this.cart;
	    transactionTransition.setAmount(calculateCartCost());
	    summaryCart = transactionTransition.getProductArrayList();
    }
    //^Austin Brown 3/30/18
    public void findItemsButtonOnClick(View view)
    {
//	    if(transactionTransition == null) {
//		    Log.d("message", "The productTransition is null.");
//	    }
//	    else
//	    {
//		    Log.d("message2", "The productTransition is not null");
//	    }
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
        //Get employee data to transfer too
        this.startActivity(intent);
    }
    //^Austin Brown 3/30/18
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
            loadingTransactionDetailsFromServerAlert.dismiss(); //This solves WindowManager Error, but not all errors
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
            Log.d("message",String.format("These are the fields of the TransactionTransition object:\ncashiedid: %s\namount: %s\ntranstype: %s\nreferenceid: %s\nrecordID: %s\ncreatedOn: %s", transactionTransition.getCashierID(), transactionTransition.getAmount(), transactionTransition.getTransType(), transactionTransition.getReferenceID(), transactionTransition.getRecordID(), transactionTransition.getCreatedOn()));
            //transactionTransition.setAmount(50.01);

            //Temporary fix to NPE (If anyone knows how to fix it, that would be great...)
            if(employeeTransition != null) {
                transactionTransition.setCashierID(employeeTransition.getEmployeeID()); //Debugger says this is null on second attempt for some reason
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


//    public void addCartItem(Product tempProduct)
//    {
//        //cart.add(tempProduct);
//	    ArrayList<Product> temp = transactionTransition.getProductArrayList();
//	    temp.add(tempProduct);
//	    transactionTransition.setProductArray(temp);
//    }
}
