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
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.ProductService;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

//import edu.uark.uarkregisterapp.models.api.EmployeeCount;
//import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class CreateEmployeeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee_screen);
        //Austin:
        //this.EmployeeTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_product));
        //Need to create employeeTransition object to pass to next screen see ProductViewActivity.java

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void createEmployeeButtonOnClick(View view) {
        //At this point it will only take you to the homescreenactivity.
        Intent intent = new Intent(this.getApplicationContext(), TransactionStartActivity.class);
        this.startActivity(intent);
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

    private boolean validateInput() {
        boolean inputIsValid = true;
        String validationMessage = StringUtils.EMPTY;

        if (StringUtils.isBlank(this.getEmployeeFirstName().getText().toString())) {
            //validationMessage = this.getString(R.string.validation_product_count);
            validationMessage = "Employee First Name may not be empty.";
            inputIsValid = false;
        }

        if (!inputIsValid && StringUtils.isBlank(this.getEmployeeLastName().getText().toString())) {
            //validationMessage = this.getString(R.string.validation_product_count);
            validationMessage = "Employee Last Name may not be empty.";
            inputIsValid = false;
        }

        if (!inputIsValid && StringUtils.isBlank(this.getEmployeePassword().getText().toString())) {
            validationMessage = "Employee Password may not be empty.";
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
    //Austin
    /*private class SaveCreatedEmployee extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            this.
        }

        //@Override
        //protected Boolean doInBackground(Void... params) {
            /*Product product = (new Product()).
                    setId(productTransition.getId()).
                    setLookupCode(getProductLookupCodeEditText().getText().toString()).
                    setCount(Integer.parseInt(getProductCountEditText().getText().toString()));

            ApiResponse<Product> apiResponse = (
                    (product.getId().equals(new UUID(0, 0)))
                            ? (new ProductService()).createProduct(product)
                            : (new ProductService()).updateProduct(product)
            );
            //This if needs to be invoked for 3 fields instead of 2
            //e.g. First, Last, Password
            if (apiResponse.isValidResponse()) {
                productTransition.setCount(apiResponse.getData().getCount());
                productTransition.setLookupCode(apiResponse.getData().getLookupCode());
            }*/

            //return apiResponse.isValidResponse();
        //}
    //}
    /*Still need to implement server side code for this Screen,
    e.g. need to communicate each entered field for the Create
    button on press. Additionally, need to understand and resolve
    warnings considering Hardcoded Strings. Investigate using
    @string resource instead.
    */
}
