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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
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
        //Austin:
        this.employeeTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_employee));
        //Need to create employeeTransition object to pass to next screen see ProductViewActivity.java
        /*setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    /*@Override
    protected void onResume() {
        super.onResume();
        this.getEmployeeFirstName().setText(this.employeeTransition.getFirstName());
        this.getEmployeeLastName().setText(this.employeeTransition.getLastName());
        this.getEmployeePassword().setText(this.employeeTransition.getPassWord());
    }*/


    //test this next
    //Austin: Working on this:
    public void createEmployeeButtonOnClick(View view) {
        if (!this.validateInput())
        {
            return;
        }
        (new SaveCreatedEmployee()).execute(
                (new Employee())
                    .setActive("active")
                    .setFirstName(this.getEmployeeFirstName().getText().toString())
                    .setLastName(this.getEmployeeLastName().getText().toString())
                    .setPassWord(this.getEmployeePassword().getText().toString())
                    .setManager("manager")
                    .setRole("role")
                    .setCreatedOn(new Date())
                    //.setClassification(EmployeeClassification.GENERAL_MANAGER)
        );
        //At this point it will only take you to the homescreenactivity.
        //Intent intent = new Intent(this.getApplicationContext(), TransactionStartActivity.class);
        //this.startActivity(intent);
    }

    private EditText getEmployeeFirstName() {
        return (EditText) this.findViewById(R.id.firstNameLabel);
    }

    private EditText getEmployeeLastName() {
        return (EditText) this.findViewById(R.id.lastNameLabel);
    }

    private EditText getEmployeePassword() {
        return (EditText) this.findViewById(R.id.employeePasswordLabel);
    }

    private EditText getPassWordConfirmEditText(){
        return (EditText) this.findViewById(R.id.edit_text_employee_password);
    }

    private boolean validateInput() {
        boolean inputIsValid = true;
        //String validationMessage = StringUtils.EMPTY;

        if (StringUtils.isBlank(this.getEmployeeFirstName().getText().toString())) {
            this.displayValidationAlert(R.string.validation_employee_first_name);
            this.getEmployeeFirstName().requestFocus();
            //validationMessage = this.getString(R.string.validation_employee_first_name);
            //validationMessage = "Employee First Name may not be empty.";
            inputIsValid = false;
        }

        if (inputIsValid && StringUtils.isBlank(this.getEmployeeLastName().getText().toString())) {
            this.displayValidationAlert(R.string.validation_employee_last_name);
            this.getEmployeeLastName().requestFocus();
            //validationMessage = this.getString(R.string.validation_employee_last_name);
            //validationMessage = "Employee Last Name may not be empty.";
            inputIsValid = false;
        }

        if (inputIsValid && StringUtils.isBlank(this.getEmployeePassword().getText().toString())) {
            //validationMessage = "Employee Password may not be empty.";
            //validationMessage = this.getString(R.string.validation_employee_password;
            this.displayValidationAlert(R.string.validation_employee_password);
            this.getEmployeeLastName().requestFocus();
            inputIsValid = false;
        }

        if (inputIsValid && !this.getEmployeePassword().getText().toString().equals(this.getPassWordConfirmEditText().getText().toString())){
            this.displayValidationAlert(R.string.alert_dialog_create_employee_validation_password_invalid);
            this.getEmployeeLastName().requestFocus();
            inputIsValid = false;
        }

        if (!inputIsValid) {
            new AlertDialog.Builder(this).
                    setMessage("Failure").
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

    private void displayValidationAlert(int stringId){
        new AlertDialog.Builder(this)
                .setMessage(stringId)
                .create()
                .show();
    }
    //Austin
    private class SaveCreatedEmployee extends AsyncTask<Employee, Void, ApiResponse<Employee>> {
        @Override
        protected void onPreExecute() {
            this.savingEmployeeAlert = new AlertDialog.Builder(CreateEmployeeScreen.this)
                    .setMessage(R.string.alert_dialog_employee_save)
                    .create();
            this.savingEmployeeAlert.show();
        }

        @Override
        protected ApiResponse<Employee> doInBackground(Employee... employees) {
            /*if (employees.length > 0) {
                return (new EmployeeService()).createEmployee(employees[0]);
            } else {
                return (new ApiResponse<Employee>())
                        .setValidResponse(false);
            }*/
            Employee employee = (new Employee()).setId(employeeTransition.getId()).
                    setFirstName(getEmployeeFirstName().getText().toString()).
                    setLastName(getEmployeeLastName().getText().toString()).
                    setPassWord(getEmployeePassword().getText().toString());
            ApiResponse<Employee> apiResponse = (
                    (employee.getId().equals(new UUID(0,0)))
                        ? (new EmployeeService()).createEmployee(employee)
                            : (new EmployeeService().updateEmployee(employee))
                    );
            if (apiResponse.isValidResponse()){
                employeeTransition.setFirstName(apiResponse.getData().getFirstName());
                employeeTransition.setLastName(apiResponse.getData().getLastName());
                employeeTransition.setPassWord(apiResponse.getData().getPassWord());
            }
            return apiResponse;
        }
            /*String val = ""+((int)(Math.random()*9000)+1000);
            Employee employee = (new Employee()).
                    setFirstName(getEmployeeFirstName().getText().toString()).
                    setLastName(getEmployeeLastName().getText().toString()).
                    setPassWord(getEmployeePassword().getText().toString()).
                    setEmployeeID(val).
                    setActive("1").
                    setManager("0");
            *///Manager status and Active status currenty hardcoded above - Austin
            /*ApiResponse<Employee> apiResponse = (
                    (employee.getId().equals(new UUID(0, 0)))
                            ? (new EmployeeService()).createEmployee(employee)
                            : (new EmployeeService()).updateEmployee(employee)
            );
            //This if needs to be invoked for 6 fields ? - Austin
            //e.g. First, Last, Password, active, Employee ID, Manager
            if (apiResponse.isValidResponse()) {

                employeeTransition.setFirstName(apiResponse.getData().getFirstName());
                employeeTransition.setLastName(apiResponse.getData().getLastName());
                employeeTransition.setPassWord(apiResponse.getData().getPassWord());
                employeeTransition.setActive(apiResponse.getData().getActive());
                employeeTransition.setEmployeeID(apiResponse.getData().getEmployeeID());
                employeeTransition.setManager(apiResponse.getData().getManager());
            }

            return apiResponse.isValidResponse();
        }*/

        @Override
        protected void onPostExecute(ApiResponse<Employee> apiResponse) {
            this.savingEmployeeAlert.dismiss();
            //String message;

            //savingEmployeeAlert.dismiss();

            if (!apiResponse.isValidResponse()) {
                new AlertDialog.Builder(CreateEmployeeScreen.this)
                        .setMessage(R.string.alert_dialog_employee_save_failure)
                        .create()
                        .show();
                return;
            } /*else {
                message = getString(R.string.alert_dialog_employee_save_failure);
            }*/

            Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);

            intent.putExtra(
                    getString(R.string.intent_extra_employee)
                    , new EmployeeTransition(apiResponse.getData())
            );

            startActivity(intent);
            /*new AlertDialog.Builder(CreateEmployeeScreen.this).
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
                    show();*/
        }

        private AlertDialog savingEmployeeAlert;

        /*private SaveCreatedEmployee() {
            this.savingEmployeeAlert = new AlertDialog.Builder(CreateEmployeeScreen.this).
                    setMessage(R.string.alert_dialog_employee_save).
                    create();
        }*/
    }

    /*private class DeleteEmployeeTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            this.deletingEmployeeAlert.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return (new EmployeeService())
                    .deleteEmployee(employeeTransition.getId())
                    .isValidResponse();
        }

        @Override
        protected void onPostExecute(final Boolean successfulSave) {
            String message;

            deletingEmployeeAlert.dismiss();

            if (successfulSave) {
                message = getString(R.string.alert_dialog_employee_delete_success);
            } else {
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

        private DeleteEmployeeTask() {
            this.deletingEmployeeAlert = new AlertDialog.Builder(CreateEmployeeScreen.this).
                    setMessage(R.string.alert_dialog_employee_delete).
                    create();
        }
    }*/
    private EmployeeTransition employeeTransition;
}
