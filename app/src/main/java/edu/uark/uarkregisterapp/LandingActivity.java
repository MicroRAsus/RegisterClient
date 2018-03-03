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
	//NOTE: ***I have temporarily modified line 19 to transfer to my activity (employeeLogin) for testing ***
	public void displayAllProductsButtonOnClick(View view) {
		this.startActivity(new Intent(getApplicationContext(), EmployeeLoginActivity.class));
		//this.startActivity(new Intent(getApplicationContext(), ProductsListingActivity.class));
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
	    EmployeeCount temp;
        temp = new EmployeeCount();
        if(temp.getCount() <= 0) {
            this.startActivity(new Intent(getApplicationContext(), CreateEmployeeScreen.class));
        }
	    else {
            this.startActivity(new Intent(getApplicationContext(), EmployeeLoginActivity.class));
        }
	}
}
