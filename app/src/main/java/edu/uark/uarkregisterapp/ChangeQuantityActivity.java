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
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class ChangeQuantityActivity extends AppCompatActivity {

    private ProductTransition productTransition;
    private double price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_quantity);
        this.productTransition = this.getIntent().getParcelableExtra("intent_extra_product_FindProductsActivity");
        this.transactionTransition = this.getIntent().getParcelableExtra("intent_extra_transaction_transition_FindProductsActivity");
        price = this.productTransition.getPrice();
    }
    @Override
    protected void onResume(){
        super.onResume();
        this.setProductIDTextView().setText(this.productTransition.getLookupCode());
        this.setProductPriceTextView().setText(doubleToString(this.productTransition.getPrice()));
    }
    public String doubleToString(Double d){
        return d.toString();
    }
    public void saveButtonOnClick(View view) {
        if (this.validateInput())
        {

            this.productTransition.setCount(Integer.parseInt(this.setProductCountEditText().getText().toString()));
            Product productToAdd = new Product(this.productTransition);
            ArrayList<Product> temp = transactionTransition.getProductArrayList();
            temp.add(productToAdd);
            transactionTransition.setProductArray(temp);
            Intent intent = new Intent(this.getApplicationContext(), ShoppingCartActivity.class);
            intent.putExtra("intent_extra_transaction_transition_to_ShoppingCartActivity", this.transactionTransition);
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
    }
    private EditText setProductCountEditText() {
        return (EditText) this.findViewById(R.id.edit_text_set_product_count);
    }
    private TextView setProductPriceTextView(){
        return (TextView) this.findViewById(R.id.text_view_product_price);
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
    private TransactionTransition transactionTransition = new TransactionTransition();
}
