package edu.uark.uarkregisterapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.EmployeeCount;
import edu.uark.uarkregisterapp.models.api.services.EmployeeCountService;

public class EmployeeLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        (new CheckEmployeeCount()).execute();
    }

    private EditText getEmployeeIDEditText() {
        return (EditText) this.findViewById(R.id.edit_text_employee_id);
    }

    public void employeeLoginButtonOnClick(View view){
        //(new CheckEmployeeCount()).execute();
        Intent intent = new Intent(this.getApplicationContext(), HomeScreenActivity.class);
        intent.putExtra("employeeID", this.getEmployeeIDEditText().getText().toString());
        this.startActivity(intent);
    }

    private class CheckEmployeeCount extends AsyncTask<Void, Void, ApiResponse<EmployeeCount>> {


        @Override
        protected void onPreExecute() {
            this.loadingEmployeeCountAlert.show();
        }

        @Override
        protected ApiResponse<EmployeeCount> doInBackground(Void... params) {
            ApiResponse<EmployeeCount> apiResponse = (new EmployeeCountService()).getEmployeeCount(); //employeeCount : was in the parameters of .getEmployeeCount()

            /*//Start Austin:
            //We need to request an employee count from the webservices.
            //Something along the lines of:
            EmployeeCount temp;
            temp = new EmployeeCount();
            if(temp.getCount() <= 0) {
                this.startActivity(new Intent(getApplicationContext(), CreateEmployeeScreen.class));
            }
	        else {
                this.startActivity(new Intent(getApplicationContext(), EmployeeLoginActivity.class));
            }
            //But using ApiResponse
            //End Austin*/

//            if (apiResponse.isValidResponse()) {
//                products.clear();
//                products.addAll(apiResponse.getData());
//            }

            return apiResponse;
        }

        @Override
        protected void onPostExecute(ApiResponse<EmployeeCount> apiResponse) {
            if (apiResponse.isValidResponse()) {
                //productListAdapter.notifyDataSetChanged();
                employeeCount = apiResponse.getData();
                //this.loadingEmployeeCountAlert.dismiss();
                if (employeeCount.getCount() == 0) {
                    this.loadingEmployeeCountAlert.dismiss();
                    Intent intent = new Intent(getApplicationContext(), CreateEmployeeScreen.class);
                }
            }
            //this.loadingEmployeeCountAlert.dismiss();

            if (!apiResponse.isValidResponse()) {
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
        }

        private AlertDialog loadingEmployeeCountAlert;

        private CheckEmployeeCount() {
            this.loadingEmployeeCountAlert = new AlertDialog.Builder(EmployeeLoginActivity.this).
                    setMessage(R.string.alert_dialog_retrieving_employee_count).
                    create();
        }
    }

    private class CheckEmployeeCredentials extends AsyncTask<Void, Void, ApiResponse<EmployeeCount>> {


        @Override
        protected void onPreExecute() {
            this.loadingEmployeeCountAlert.show();
        }

        @Override
        protected ApiResponse<EmployeeCount> doInBackground(Void... params) {
            ApiResponse<EmployeeCount> apiResponse = (new EmployeeCountService()).getEmployeeCount(); //employeeCount : was in the parameters of .getEmployeeCount()

            /*//Start Austin:
            //We need to request an employee count from the webservices.
            //Something along the lines of:
            EmployeeCount temp;
            temp = new EmployeeCount();
            if(temp.getCount() <= 0) {
                this.startActivity(new Intent(getApplicationContext(), CreateEmployeeScreen.class));
            }
	        else {
                this.startActivity(new Intent(getApplicationContext(), EmployeeLoginActivity.class));
            }
            //But using ApiResponse
            //End Austin*/

//            if (apiResponse.isValidResponse()) {
//                products.clear();
//                products.addAll(apiResponse.getData());
//            }

            return apiResponse;
        }

        @Override
        protected void onPostExecute(ApiResponse<EmployeeCount> apiResponse) {
            if (apiResponse.isValidResponse()) {
                //productListAdapter.notifyDataSetChanged();
                employeeCount = apiResponse.getData();
                //this.loadingEmployeeCountAlert.dismiss();
                if (employeeCount.getCount() == 0) {
                    this.loadingEmployeeCountAlert.dismiss();
                    Intent intent = new Intent(getApplicationContext(), CreateEmployeeScreen.class);
                }
            }
            //this.loadingEmployeeCountAlert.dismiss();

            if (!apiResponse.isValidResponse()) {
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
        }

        private AlertDialog loadingEmployeeCountAlert;

        private CheckEmployeeCount() {
            this.loadingEmployeeCountAlert = new AlertDialog.Builder(EmployeeLoginActivity.this).
                    setMessage(R.string.alert_dialog_retrieving_employee_count).
                    create();
        }
    }
    private EmployeeCount employeeCount;
}
