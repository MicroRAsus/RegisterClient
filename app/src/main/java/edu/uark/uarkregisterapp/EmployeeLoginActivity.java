package edu.uark.uarkregisterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import org.apache.commons.lang3.StringUtils;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.EmployeeCount;
import edu.uark.uarkregisterapp.models.api.EmployeeLogin;
import edu.uark.uarkregisterapp.models.api.services.EmployeeCountService;
import edu.uark.uarkregisterapp.models.api.services.EmployeeLoginService;
import edu.uark.uarkregisterapp.models.transition.EmployeeCountTransition;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;


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

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private EditText getEmployeeIDEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_id);
    }

    private EditText getEmployeePassowrdEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_password);
    }

    public void employeeLoginButtonOnClick(View view){

        if (!this.validateInput()) { return;}

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

            if (apiResponse.isValidResponse()) {
                return (apiResponse.getData().getCount());
            }
            else {
                return -100;
            }

        }

        @Override
        protected void onPostExecute(Integer employeeCountValue) {
            loadingEmployeeCountAlert.dismiss();

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

                new android.support.v7.app.AlertDialog.Builder(EmployeeLoginActivity.this).
                        setMessage(R.string.alert_dialog_no_employees_exist_message).
                        setPositiveButton(
                                R.string.button_dismiss,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                }
                        ).
                        setTitle(R.string.alert_dialog_no_employees_exist).
                        create().
                        show();

                Intent intent = new Intent(EmployeeLoginActivity.this, CreateEmployeeScreen.class);
                EmployeeLoginActivity.this.startActivity(intent);
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

            return apiResponse.isValidResponse();
        }

        @Override
        protected void onPostExecute(Boolean successfulResponse) {
            loadingEmployeeCredentialsAlert.dismiss();

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

    private EmployeeCountTransition employeeCountTransition;
    private EmployeeTransition employeeTransition;
}
