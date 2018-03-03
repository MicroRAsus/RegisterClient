
package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.uark.uarkregisterapp.models.api.EmployeeCount;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }
    //removed by Austin 3/2/18
    public void displayAllProductsButtonOnClick(View view) {
        this.startActivity(new Intent(getApplicationContext(), ProductsListingActivity.class));
    }

    public void createProductButtonOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ProductViewActivity.class);

        intent.putExtra(
                getString(R.string.intent_extra_product),
                new ProductTransition()
        );

        this.startActivity(intent);
    }
    /*(Austin): Uncertain of how to check total employee count (value stored in server)
    putting a note in the GroupMe.
     */
    public void goToEmployeeLoginOnClick(View view) {
        //This logic should be somewhere in EmployeeLoginActivity.java
	    /*EmployeeCount temp;
        temp = new EmployeeCount();
        if(temp.getCount() <= 0) {
            this.startActivity(new Intent(getApplicationContext(), CreateEmployeeScreen.class));
        }
	    else {
            this.startActivity(new Intent(getApplicationContext(), EmployeeLoginActivity.class));
        }*/
        Intent intent = new Intent(getApplicationContext(), EmployeeLoginActivity.class);
        this.startActivity(intent);
    }
	/*
	/(Austin)/
	 */
}