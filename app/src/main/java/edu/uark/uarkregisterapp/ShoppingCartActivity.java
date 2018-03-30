package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShoppingCartActivity extends AppCompatActivity {

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
        //Implemented by Austin
    }

    public void checkoutButtonOnClick(View view){
        //additionally need to invoke server side code prior to making activity switch
        //Intent intent = new Intent(this.getApplicationContext(), SummaryScreenActivity.class);
        //this.startActivity(intent);
    }
}
