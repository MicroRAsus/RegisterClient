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
        (new SaveCreatedEmployee()).execute();

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
    private class SaveCreatedEmployee extends AsyncTask<Void, Void, Boolean>
    {
        @Override
        protected void onPreExecute()
        {
            this.savingEmployeeAlert.show();
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            int value = ((int)(Math.random()*9000)+1000);
            String val = Integer.toString(value);
            Employee employee = (new Employee()).
                    setFirstName(getEmployeeFirstName().getText().toString()).
                    setLastName(getEmployeeLastName().getText().toString()).
                    setPassWord(getEmployeePassword().getText().toString()).
                    setEmployeeID(val).
                    setActive("1").
                    setManager("0");
            //Manager status and Active status currenty hardcoded above - Austin
            ApiResponse<Employee> apiResponse = (
                    (employee.getId().equals(new UUID(0, 0)))
                            ? (new EmployeeService()).createEmployee(employee)
                            : (new EmployeeService()).updateEmployee(employee)
            );
            //This if needs to be invoked for 6 fields ? - Austin
            //e.g. First, Last, Password, active, Employee ID, Manager
            if (apiResponse.isValidResponse())
            {

                employeeTransition.setFirstName(apiResponse.getData().getFirstName());
                employeeTransition.setLastName(apiResponse.getData().getLastName());
                employeeTransition.setPassWord(apiResponse.getData().getPassWord());
                employeeTransition.setActive(apiResponse.getData().getActive());
                employeeTransition.setEmployeeID(apiResponse.getData().getEmployeeID());
                employeeTransition.setManager(apiResponse.getData().getManager());
            }

            return apiResponse.isValidResponse();
        }

        @Override
        protected void onPostExecute(Boolean successfulSave)
        {
            String message;

            savingEmployeeAlert.dismiss();

            if (successfulSave)
            {
                message = getString(R.string.alert_dialog_employee_save_success);
            }
            else
            {
                message = getString(R.string.alert_dialog_employee_save_failure);
            }

            new AlertDialog.Builder(CreateEmployeeScreen.this).
                    setMessage(message).
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

        private AlertDialog savingEmployeeAlert;

        private SaveCreatedEmployee()
        {
            this.savingEmployeeAlert = new AlertDialog.Builder(CreateEmployeeScreen.this).
                    setMessage(R.string.alert_dialog_employee_save).
                    create();
        }
    }

    private class DeleteEmployeeTask extends AsyncTask<Void, Void, Boolean>
    {
        @Override
        protected void onPreExecute()
        {
            this.deletingEmployeeAlert.show();
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            return (new EmployeeService())
                    .deleteEmployee(employeeTransition.getId())
                    .isValidResponse();
        }

        @Override
        protected void onPostExecute(final Boolean successfulSave)
        {
            String message;

            deletingEmployeeAlert.dismiss();

            if (successfulSave)
            {
                message = getString(R.string.alert_dialog_employee_delete_success);
            }
            else
            {
                message = getString(R.string.alert_dialog_employee_delete_failure);
            }

            new AlertDialog.Builder(CreateEmployeeScreen.this).
                    setMessage(message).
                    setPositiveButton(
                            R.string.button_dismiss,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    if (successfulSave) {
                                        finish();
                                    }
                                }
                            }
                    ).
                    create().
                    show();
        }

        private AlertDialog deletingEmployeeAlert;

        private DeleteEmployeeTask()
        {
            this.deletingEmployeeAlert = new AlertDialog.Builder(CreateEmployeeScreen.this).
                    setMessage(R.string.alert_dialog_employee_delete).
                    create();
        }
    }
    private EmployeeTransition employeeTransition;
}
