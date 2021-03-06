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
		this.createdOn = this.transactionTransition.getCreatedOn();
		this.refId = this.transactionTransition.getRecordID();
		this.amount = this.transactionTransition.getAmount();
		this.productListAdapter = new ProductListAdapter(this, ShoppingCartActivity.summaryCart);
		this.getSummaryListView().setAdapter(this.productListAdapter);
	}
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
	private String dateToString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy h:m a");
		String result = formatter.format(date);
		return result;
	}
	public void returnHomeOnClick(View view){
		Intent intent = new Intent(this.getApplicationContext(), HomeScreenActivity.class);
		this.startActivity(intent);
	}
	private ListView getTransactionListView() {
		return (ListView) this.findViewById(R.id.list_view_products);
	}
}