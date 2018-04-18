//Created By Austin
package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.services.EmployeeService;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;


public class CreateEmployeeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee_screen);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //Austin:
        this.employeeTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_employee));

        //(new SaveCreatedEmployee()).execute();
        //Need to create employeeTransition object to pass to next screen see ProductViewActivity.java
    }
    @Override
    protected void onResume()
    {
        super.onResume();
    }
    //test this next
    //Austin: Working on this:
    public void createEmployeeButtonOnClick(View view)
    {
        //(new SaveCreatedEmployee()).execute();
        if (!this.validateInput())
        {
            return;
        }
        (new CreateEmployeeTask()).execute(
                (new Employee())
                .setActive("active")
                .setFirstName(this.getEmployeeFirstName().getText().toString())
                .setLastName(this.getEmployeeLastName().getText().toString())
                .setPassWord(this.getEmployeePassword().getText().toString())
                .setManager("manager")
        );

        //At this point it will only take you to the homescreenactivity.
        //Intent intent = new Intent(this.getApplicationContext(), TransactionStartActivity.class);
        //this.startActivity(intent);
    }

    private EditText getEmployeeFirstName()
    {
        return (EditText) this.findViewById(R.id.firstNameLabel);
    }

    private EditText getEmployeeLastName()
    {
        return (EditText) this.findViewById(R.id.lastNameLabel);
    }

    private EditText getEmployeePassword()
    {
        return (EditText) this.findViewById(R.id.employeePasswordLabel);
    }

    private boolean validateInput()
    {
        boolean inputIsValid = true;
        String validationMessage = StringUtils.EMPTY;

        if (StringUtils.isBlank(this.getEmployeeFirstName().getText().toString()))
        {
            //validationMessage = this.getString(R.string.validation_product_count);
            validationMessage = "Employee First Name may not be empty.";
            inputIsValid = false;
        }

        if (StringUtils.isBlank(this.getEmployeeLastName().getText().toString()))
        {
            //validationMessage = this.getString(R.string.validation_product_count);
            validationMessage = "Employee Last Name may not be empty.";
            inputIsValid = false;
        }

        if (StringUtils.isBlank(this.getEmployeePassword().getText().toString()))
        {
            validationMessage = "Employee Password may not be empty.";
            inputIsValid = false;
        }

        if (!inputIsValid)
        {
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
    //Austin
    private class CreateEmployeeTask extends AsyncTask<Employee, Void, ApiResponse<Employee>> {
        @Override
        protected void onPreExecute() {
            this.createEmployeeAlert = new AlertDialog.Builder(CreateEmployeeScreen.this)
                    .setMessage(R.string.alert_dialog_create_employee_validation_password_invalid)
                    .create();
            this.createEmployeeAlert.show();
        }

        @Override
        protected ApiResponse<Employee> doInBackground(Employee... employees) {
            if (employees.length > 0) {
                return (new EmployeeService()).createEmployee(employees[0]);
            } else {
                return (new ApiResponse<Employee>())
                        .setValidResponse(false);
            }
        }

        @Override
        protected void onPostExecute(ApiResponse<Employee> apiResponse) {
            this.createEmployeeAlert.dismiss();
            if (!apiResponse.isValidResponse()) {
                new AlertDialog.Builder(CreateEmployeeScreen.this)
                        .setMessage(R.string.alert_dialog_employee_save_failure)
                        .create()
                        .show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
            intent.putExtra(
                    getString(R.string.intent_extra_employee)
                    , new EmployeeTransition(apiResponse.getData())
            );
            startActivity(intent);
        }
        private AlertDialog createEmployeeAlert;
    }
    private EmployeeTransition employeeTransition;
}
