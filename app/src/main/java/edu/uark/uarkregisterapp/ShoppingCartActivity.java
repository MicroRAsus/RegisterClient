package edu.uark.uarkregisterapp;
//Initially Created by Austin Brown, to be edited by Alicia
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

public class ShoppingCartActivity extends AppCompatActivity {

    private EmployeeTransition employeeTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void cancelTransactionButtonOnClick(View view){
        //Need to add code to delete cart prior to making activity switch
        //Intent intent = new Intent(this.getApplicationContext(), HomeScreenActivity.class);
        //this.startActivity(intent);
        Intent intent = new Intent(ShoppingCartActivity.this, HomeScreenActivity.class);
        intent.putExtra(
                getString(R.string.intent_extra_employee),
                employeeTransition
        );
        ShoppingCartActivity.this.startActivity(intent);

    }//^Austin Brown 3/30/18*/

    public void checkoutButtonOnClick(View view){
        //additionally need to invoke server side code prior to making activity switch
        Intent intent = new Intent(this.getApplicationContext(), SummaryScreenActivity.class);
        this.startActivity(intent);
    }
    //^Austin Brown 3/30/18
    public void findItemsButtonOnClick(View view){
        Intent intent = new Intent(this.getApplicationContext(), FindProductsActivity.class);
        this.startActivity(intent);
    }
    //^Austin Brown 3/30/18
}
