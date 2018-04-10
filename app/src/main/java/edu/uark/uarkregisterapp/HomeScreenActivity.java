package edu.uark.uarkregisterapp;

//Created by Russell Schuljak 3/4/2018

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Parcelable;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.api.Employee;




public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.employeeTransition = this.getIntent().getParcelableExtra("intent_extra_employee");
        this.userName = /*this.employeeTransition.getFirstName() + */ " "; /* + this.employeeTransition.getLastName();*/
        //We have to transition employee information to ShoppingCartActivity for this call to be valid upon returning
        //to HomeScreenActivity, So I adjusted it to make all navigation work in the interim.  -Austin
    }
    @Override
    protected void onResume(){
        super.onResume();
        //The app was crashing because userName could not be resolved when HomeScreenActivity was returned to from Shopping Cart
        //Need to keep working on this, need to pass employee Transition to the Shopping cart Activity and back. See comment in onCreate.
        //this.employeeTransition = this.getIntent().getParcelableExtra("intent_extra_employee");
        //this.userName = this.employeeTransition.getFirstName() + " " + this.employeeTransition.getLastName();
        this.getUserNameTextView().setText("Hello " + this.userName + getResources().getString(R.string.text_view_main_menu));
    }
    private TextView getUserNameTextView(){
        return (TextView) this.findViewById(R.id.text_main_menu);
    }
    public void startTransactionButtonOnClick(View view){
        Intent intent = new Intent(this.getApplicationContext(), ShoppingCartActivity.class);
        intent.putExtra(
                getString(R.string.intent_extra_employee),
                employeeTransition
        );
        this.startActivity(intent);
        //Implemented by Austin
    }
    public void createEmployeeButtonOnClick(View view){
        this.startActivity(new Intent(getApplicationContext(), CreateEmployeeScreen.class));
        //Implemented by Austin
    }
    public void salesReportCashierButtonOnClick(View view){
        //create alert dialog
        AlertDialog.Builder build = new AlertDialog.Builder(this).
                setMessage("This functionality has not yet been implemented yet.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        build.create().show();

    }
    public void salesReportProductButtonOnClick(View view){
        //create alert dialog
        AlertDialog.Builder build = new AlertDialog.Builder(this).
                setMessage("This functionality has not yet been implemented yet.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        build.create().show();

    }
    public void logOutButtonOnClick(View view){
        //returns to the home page of the application
        Intent intent = new Intent(this.getApplicationContext(), LandingActivity.class);
        this.startActivity(intent);

    }

    //receive emplooyee name for activity
    private String userName;
    private EmployeeTransition employeeTransition;
}
