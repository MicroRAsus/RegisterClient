package edu.uark.uarkregisterapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;




public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.userName = this.getIntent().getStringExtra("extra.user.name");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        this.getUserNameTextView().setText("Hello " + this.userName + getResources().getString(R.string.text_view_main_menu));
    }
    private TextView getUserNameTextView(){
        return (TextView) this.findViewById(R.id.text_main_menu);
    }
    public void startTransactionButtonOnClick(View view){
        //create alert dialog
        AlertDialog build = new AlertDialog.Builder(HomeScreenActivity.this).create();
        build.setTitle("Functionality not Available");
        build.setMessage("The functionality of this button has not yet been implemented");
        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        build.setButton("Ok", dialogListener);


    }
    public void createEmployeeButtonOnClick(View view){
        //create alert dialog
        AlertDialog build = new AlertDialog.Builder(HomeScreenActivity.this).create();
        build.setTitle("Functionality not Available");
        build.setMessage("The functionality of this button has not yet been implemented");
        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        build.setButton("Ok", dialogListener);

    }
    public void salesReportCashierButtonOnClick(View view){
        //create alert dialog
        AlertDialog build = new AlertDialog.Builder(HomeScreenActivity.this).create();
        build.setTitle("Functionality not Available");
        build.setMessage("The functionality of this button has not yet been implemented");
        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        build.setButton("Ok", dialogListener);

    }
    public void salesReportProductButtonOnClick(View view){
        //create alert dialog
        AlertDialog build = new AlertDialog.Builder(HomeScreenActivity.this).create();
        build.setTitle("Functionality not Available");
        build.setMessage("The functionality of this button has not yet been implemented");
        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
        build.setButton("Ok", dialogListener);

    }
    public void logOutButtonOnClick(View view){
        //returns to the home page of the application
        Intent intent = new Intent(this.getApplicationContext(), LandingActivity.class);
        this.startActivity(intent);

    }

    //receive emplooyee name for activity
    private String userName;
}
