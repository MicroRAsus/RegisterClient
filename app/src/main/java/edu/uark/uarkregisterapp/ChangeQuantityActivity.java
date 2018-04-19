package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;
import edu.uark.uarkregisterapp.ShoppingCartActivity;

public class ChangeQuantityActivity extends AppCompatActivity {

    private ProductTransition productTransition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_quantity);
        this.productTransition = this.getIntent().getParcelableExtra("intent_extra_product");
    }
    @Override
    protected void onResume(){
        //print the product lookup code on top of acitivty
        super.onResume();
        this.setProductIDTextView().setText(this.productTransition.getLookupCode());
    }

    public void saveButtonOnClick(View view) {
        if (this.validateInput())
        {

            this.productTransition.setCount(Integer.parseInt(this.setProductCountEditText().getText().toString()));
            Product productToAdd = new Product(this.productTransition);

            Intent intent = new Intent(this.getApplicationContext(), ShoppingCartActivity.class);
            intent.putExtra("intent_product_transition_extra", this.productTransition);
            /*intent.putExtra(
                    getString(R.string.intent_extra_employee),
                    employeeTransition
            );*/
            this.startActivity(intent);
            return;
        }
        else{
            String validationMessage = this.getString(R.string.validation_product_count);
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

        //This is your job russel(Cmon Austin you know you how to spell my name Russell), make a save button and have it add items to cart
        //I have done the rest for you. The arrayList is in ShoppingCartActivity.
    }

    private EditText setProductCountEditText() {
        return (EditText) this.findViewById(R.id.edit_text_set_product_count);
    }

    private TextView setProductIDTextView(){
        return (TextView) this.findViewById(R.id.text_view_product_lookup_code);
    }

    private boolean validateInput() {
        boolean inputIsValid = true;
        String validationMessage = StringUtils.EMPTY;
        if (!inputIsValid && StringUtils.isBlank(this.setProductCountEditText().getText().toString())) {
            validationMessage = this.getString(R.string.validation_product_count);
            inputIsValid = false;
        }

        try {
            if (Integer.parseInt(this.setProductCountEditText().getText().toString()) < 0) {
                validationMessage = this.getString(R.string.validation_product_count);
                inputIsValid = false;
            }
        } catch (NumberFormatException nfe) {
            validationMessage = this.getString(R.string.validation_product_count);
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

}
