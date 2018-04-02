package edu.uark.uarkregisterapp;
//Austin Brown 3/30/18
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.EmployeeLogin;
import edu.uark.uarkregisterapp.models.api.services.EmployeeLoginService;

public class SummaryScreenActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary_screen);
	}
	//^Austin Brown 3/30/18
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	//^Austin Brown 3/30/18


//	private class StoreTransactionOnServer extends AsyncTask<Void, Void, Boolean> {
//
//		@Override
//		protected void onPreExecute() {
//			this.loadingEmployeeCredentialsAlert.show();
//		}
//
//		@Override
//		protected Boolean doInBackground(Void... params) {
//			EmployeeLogin employeeLogin = (new EmployeeLogin()).
//					setEmployeeID(getEmployeeIDEditText().getText().toString()).
//					setPassWord(getEmployeePassowrdEditText().getText().toString());
//
//			ApiResponse<Employee> apiResponse = (new EmployeeLoginService()).getEmployee(employeeLogin);
//			if(apiResponse.isValidResponse()) {
//				employeeTransition.setId(apiResponse.getData().getId());
//				employeeTransition.setFirstName(apiResponse.getData().getFirstName());
//				employeeTransition.setLastName(apiResponse.getData().getLastName());
//				employeeTransition.setEmployeeID(apiResponse.getData().getEmployeeID());
//				employeeTransition.setActive(apiResponse.getData().getActive());
//				employeeTransition.setRole(apiResponse.getData().getRole());
//				employeeTransition.setManager(apiResponse.getData().getManager());
//				employeeTransition.setPassWord(apiResponse.getData().getPassWord());
//				employeeTransition.setCreatedOn(apiResponse.getData().getCreatedOn());
//			}
//
//			return apiResponse.isValidResponse();
//		}
//
//		@Override
//		protected void onPostExecute(Boolean successfulResponse) {
//			loadingEmployeeCredentialsAlert.dismiss();
//
//			if (!successfulResponse) {
//				new android.support.v7.app.AlertDialog.Builder(EmployeeLoginActivity.this).
//						setMessage(R.string.alert_dialog_employee_login_failure).
//						setPositiveButton(
//								R.string.button_dismiss,
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog, int id) {
//										dialog.dismiss();
//									}
//								}
//						).
//						create().
//						show();
//			}
//			else {
//				new android.support.v7.app.AlertDialog.Builder(EmployeeLoginActivity.this).
//						setMessage(R.string.alert_dialog_employee_login_success).
//						setPositiveButton(
//								R.string.button_dismiss,
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog, int id) {
//										dialog.dismiss();
//									}
//								}
//						).
//						create().
//						show();
//
//				Intent intent = new Intent(EmployeeLoginActivity.this, HomeScreenActivity.class);
//				intent.putExtra(
//						getString(R.string.intent_extra_employee),
//						employeeTransition
//				);
//				EmployeeLoginActivity.this.startActivity(intent);
//
//			}
//		}
//
//	private AlertDialog loadingTransactionDetailsFromServerAlert;
//
//	private StoreTransactionOnServer() {
//		this.loadingTransactionDetailsFromServerAlert = new AlertDialog.Builder(SummaryScreenActivity.this).
//				setMessage(R.string.alert_dialog_retrieving_employee_credentials).
//				create();
//	}
}