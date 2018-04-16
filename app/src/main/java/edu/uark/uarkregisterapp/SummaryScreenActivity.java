package edu.uark.uarkregisterapp;
//Austin Brown 3/30/18
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class SummaryScreenActivity extends AppCompatActivity {
	private Date createdOn;
	private int refId;
	private double amount;
	private ProductListAdapter productListAdapter;
	private TransactionTransition transactionTransition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary_screen);
		
		this.transactionTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_transaction));
		//Log.d("message", String.format("The value of transtype is: %s", transactionTransition.getTransType()));
		Log.d("message",String.format("These are the fields of the TransactionTransition object:\ncashiedid: %s\namount: %s\ntranstype: %s\nreferenceid: %s\nrecordID: %s\ncreatedOn: %s", transactionTransition.getCashierID(), transactionTransition.getAmount(), transactionTransition.getTransType(), transactionTransition.getReferenceID(), transactionTransition.getRecordID(), transactionTransition.getCreatedOn()));
		this.createdOn = this.transactionTransition.getCreatedOn();
		this.refId = this.transactionTransition.getReferenceID();
		this.amount = this.transactionTransition.getAmount();
		this.productListAdapter = new ProductListAdapter(this, ShoppingCartActivity.summaryCart);
		this.getSummaryListView().setAdapter(this.productListAdapter);
		/*Put transaction info into list
		this.transaction = new ArrayList<>();
		this.transactionListAdapter = new TransactionListAdapter(this, this.transaction);
		this.getTransactionListView().setAdapter(this.transactionListAdapter);

		this.getCreatedOnTextView().setText(this.createdOn);*/
	}
	//^Austin Brown 3/30/18

	private TextView getCreatedOnTextView(){
		return (TextView) this.findViewById(R.id.text_transaction_createdOn);
	}

	private TextView getReferenceIdTextView(){
		return (TextView) this.findViewById(R.id.text_transaction_Id);
	}

	private TextView getAmountTextView(){
		return (TextView) this.findViewById(R.id.text_transaction_amount);
	}
	private ListView getSummaryListView()
	{
		return (ListView) this.findViewById(R.id.list_view_summary_screen);
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.getCreatedOnTextView().setText(dateToString(this.createdOn));
		this.getReferenceIdTextView().setText(Integer.toString(this.refId));
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		this.getAmountTextView().setText(decimalFormat.format(this.amount));
		this.productListAdapter = new ProductListAdapter(this, ShoppingCartActivity.summaryCart);
		this.getSummaryListView().setAdapter(this.productListAdapter);
	}
	//^Austin Brown 3/30/18

	private String dateToString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy h:m a");
		String result = formatter.format(date);
		return result;
	}
	public void returnHomeOnClick(View view){
		//returns to the home page of the application
		Intent intent = new Intent(this.getApplicationContext(), HomeScreenActivity.class);
		this.startActivity(intent);
	}

	private ListView getTransactionListView() {
		return (ListView) this.findViewById(R.id.list_view_products);
	}



}