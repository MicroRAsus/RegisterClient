package edu.uark.uarkregisterapp;
//Austin Brown 3/30/18
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.EmployeeLogin;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.services.EmployeeLoginService;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;
import edu.uark.uarkregisterapp.adapters.TransactionListAdapter;

public class SummaryScreenActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary_screen);
		
		this.transactionTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_transaction));
		//Log.d("message", String.format("The value of transtype is: %s", transactionTransition.getTransType()));
		Log.d("message",String.format("These are the fields of the TransactionTransition object:\ncashiedid: %s\namount: %s\ntranstype: %s\nreferenceid: %s\nrecordID: %s\ncreatedOn: %s", transactionTransition.getCashierID(), transactionTransition.getAmount(), transactionTransition.getTransType(), transactionTransition.getReferenceID(), transactionTransition.getRecordID(), transactionTransition.getCreatedOn()));

		/*Put transaction info into list
		this.transaction = new ArrayList<>();
		this.transactionListAdapter = new TransactionListAdapter(this, this.transaction);
		this.getTransactionListView().setAdapter(this.transactionListAdapter);

		this.getCreatedOnTextView().setText(this.createdOn);*/
	}
	//^Austin Brown 3/30/18

	/*private TextView getCreatedOnTextView(){
		return (TextView) this.findViewById(R.id.text_transaction_createdOn);
	}*/

	@Override
	protected void onResume() {
		super.onResume();
	}
	//^Austin Brown 3/30/18

	//Placeholders for now
	private TransactionTransition transactionTransition;
	private List<Transaction> transaction;
	private TransactionListAdapter transactionListAdapter;
	//private String createdOn = dateToString(transactionTransition.getCreatedOn());

	private String dateToString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy-hh.mm.ss");
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