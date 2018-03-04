package edu.uark.uarkregisterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.EmployeeCount;
import edu.uark.uarkregisterapp.models.api.EmployeeLogin;
import edu.uark.uarkregisterapp.models.api.services.EmployeeCountService;
import edu.uark.uarkregisterapp.models.api.services.EmployeeLoginService;
import edu.uark.uarkregisterapp.models.transition.EmployeeCountTransition;
import edu.uark.uarkregisterapp.models.transition.EmployeeLoginTransition;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;
//import edu.uark.uarkregisterapp.models.transition.EmployeeLoginTransition;

public class EmployeeLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.employeeCountTransition = new EmployeeCountTransition();
        this.employeeTransition = new EmployeeTransition();
        (new CheckEmployeeCount()).execute();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //(new CheckEmployeeCount()).execute();

        //Log.d("I HOPE THIS DISPLAYS!", "      " + employeeCountTransition.getCount());
        //if(employeeCountTransition.getCount() == -100) {
        //    Intent intent = new Intent(this.getApplicationContext(), CreateEmployeeScreen.class);
        //    this.startActivity(intent);
        //}
        //else if(employeeCountTransition.getCount() > 0) {

        //}

    }

    private EditText getEmployeeIDEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_id);
    }

    private EditText getEmployeePassowrdEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_password);
    }

    public void employeeLoginButtonOnClick(View view){
        //(new CheckEmployeeCount()).execute();
        //Intent intent = new Intent(this.getApplicationContext(), HomeScreenActivity.class);
        //intent.putExtra("employeeID", this.getEmployeeIDEditText().getText().toString());
        //this.startActivity(intent);
        if (!this.validateInput()) {
        			return;
        	}

        //employeeTransition.setEmployeeID(this.getEmployeeIDEditText().getText().toString());
        //employeeTransition.setPassWord(this.getEmployeePassowrdEditText().getText().toString());
        (new CheckEmployeeCredentials()).execute();
    }

    private boolean validateInput() {
		boolean inputIsValid = true;
		String validationMessage = StringUtils.EMPTY;

		if (StringUtils.isBlank(this.getEmployeeIDEditText().getText().toString())) {
			validationMessage = this.getString(R.string.validation_employee_id);
			inputIsValid = false;
		}

		if (inputIsValid && StringUtils.isBlank(this.getEmployeePassowrdEditText().getText().toString())) {
			validationMessage = this.getString(R.string.validation_employee_password);
			inputIsValid = false;
		}


		if (!inputIsValid) {
			new AlertDialog.Builder(this).
				setMessage(validationMessage).
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
		}

		return inputIsValid;
	}


    private class CheckEmployeeCount extends AsyncTask<Void, Void, Integer> {


        @Override
        protected void onPreExecute() {
            this.loadingEmployeeCountAlert.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            ApiResponse<EmployeeCount> apiResponse = (new EmployeeCountService()).getEmployeeCount(); //employeeCount : was in the parameters of .getEmployeeCount()
            //Log.d("I HOPE THIS DISPLAYS!", "    " + apiResponse.getData().getCount());

            if (apiResponse.isValidResponse()) {
                return (apiResponse.getData().getCount());
                //Log.d("I HOPE THIS DISPLAYS!", "      " + employeeCountTransition.getCount());
            }
            else {
                return -100;
            }

            //return apiResponse.isValidResponse();
        }

        @Override
        protected void onPostExecute(Integer employeeCountValue) {
            loadingEmployeeCountAlert.dismiss();
            //Log.d("I HOPE THIS DISPLAYS!", "    " + employeeCountValue);
            if (employeeCountValue == -100) {
                new android.support.v7.app.AlertDialog.Builder(EmployeeLoginActivity.this).
                        setMessage(R.string.alert_dialog_employee_count_retrieval_failure).
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
            }
            else if(employeeCountValue <=0) {
                employeeCountTransition.setCount(employeeCountValue);
                Intent intent = new Intent(EmployeeLoginActivity.this, CreateEmployeeScreen.class);
                EmployeeLoginActivity.this.startActivity(intent);
                //Log.d("I HOPE THIS DISPLAYS!", "     " + employeeCountTransition.getCount());
            }
        }

        private AlertDialog loadingEmployeeCountAlert;

        private CheckEmployeeCount() {
            this.loadingEmployeeCountAlert = new AlertDialog.Builder(EmployeeLoginActivity.this).
                    setMessage(R.string.alert_dialog_retrieving_employee_count).
                    create();
        }
    }

    private class CheckEmployeeCredentials extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected void onPreExecute() {
            this.loadingEmployeeCredentialsAlert.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            EmployeeLogin employeeLogin = (new EmployeeLogin()).
                setEmployeeID(getEmployeeIDEditText().getText().toString()).
                setPassWord(getEmployeePassowrdEditText().getText().toString());
            //employeeLogin.setEmployeeID(employeeTransition.getEmployeeID());
            //employeeLogin.setPassWord(employeeTransition.getPassWord());
            ApiResponse<Employee> apiResponse = (new EmployeeLoginService()).getEmployee(employeeLogin);
            if(apiResponse.isValidResponse()) {
                employeeTransition.setId(apiResponse.getData().getId());
                employeeTransition.setFirstName(apiResponse.getData().getFirstName());
                employeeTransition.setLastName(apiResponse.getData().getLastName());
                employeeTransition.setEmployeeID(apiResponse.getData().getEmployeeID());
                employeeTransition.setActive(apiResponse.getData().getActive());
                employeeTransition.setRole(apiResponse.getData().getRole());
                employeeTransition.setManager(apiResponse.getData().getManager());
                employeeTransition.setPassWord(apiResponse.getData().getPassWord());
                employeeTransition.setCreatedOn(apiResponse.getData().getCreatedOn());
            }
            //Log.d("Name of Login??", "     0" + employeeTransition.getFirstName() + employeeTransition.getLastName());
            //Log.d("Name of Login??", "     0" + apiResponse.getData().getId() +"    " + apiResponse.getData().getFirstName() + "     " + apiResponse.getData().getLastName() +
            //"      " + apiResponse.getData().getEmployeeID() + "      " + apiResponse.getData().getActive() + "       " + apiResponse.getData().getRole() + "        " + apiResponse.getData().getManager() +
            //"       " + apiResponse.getData().getPassWord() + "      " + apiResponse.getData().getCreatedOn());
            return apiResponse.isValidResponse();
        }

        @Override
        protected void onPostExecute(Boolean successfulResponse) {
            loadingEmployeeCredentialsAlert.dismiss();
//            if (apiResponse.isValidResponse()) {
//                //productListAdapter.notifyDataSetChanged();
//                employeeCount = apiResponse.getData();
//                //this.loadingEmployeeCountAlert.dismiss();
//                if (employeeCount.getCount() == 0) {
//                    this.loadingEmployeeCountAlert.dismiss();
//                    Intent intent = new Intent(getApplicationContext(), CreateEmployeeScreen.class);
//                }
//            }
            //this.loadingEmployeeCountAlert.dismiss();

            if (!successfulResponse) {
                new android.support.v7.app.AlertDialog.Builder(EmployeeLoginActivity.this).
                        setMessage(R.string.alert_dialog_employee_login_failure).
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
            }
            else {
                //Log.d("Name of Login??", "     0" + employeeTransition.getId() +"    " + employeeTransition.getFirstName() + "     " + employeeTransition.getLastName() +
                //        "      " + employeeTransition.getEmployeeID() + "      " + employeeTransition.getActive() + "       " + employeeTransition.getRole() + "        " + employeeTransition.getManager() +
                //        "       " + employeeTransition.getPassWord() + "      " + employeeTransition.getCreatedOn());
                new android.support.v7.app.AlertDialog.Builder(EmployeeLoginActivity.this).
                        setMessage(R.string.alert_dialog_employee_login_success).
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
                Intent intent = new Intent(EmployeeLoginActivity.this, HomeScreenActivity.class);
                intent.putExtra(
                        getString(R.string.intent_extra_employee),
                        employeeTransition
                );
                EmployeeLoginActivity.this.startActivity(intent);

            }
        }

        private AlertDialog loadingEmployeeCredentialsAlert;

        private CheckEmployeeCredentials() {
            this.loadingEmployeeCredentialsAlert = new AlertDialog.Builder(EmployeeLoginActivity.this).
                    setMessage(R.string.alert_dialog_retrieving_employee_credentials).
                    create();
        }
    }
    //private EmployeeCount employeeCount;
    private EmployeeCountTransition employeeCountTransition;
    private EmployeeTransition employeeTransition;
}
