
package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

//import edu.uark.uarkregisterapp.models.api.EmployeeCount;
//import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class CreateEmployeeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee_screen);
    }

    public void createEmployeeButtonOnClick(View view) {
        //At this point it will only take you to the homescreenactivity.
        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        this.startActivity(intent);
    }
    /*Still need to implement server side code for this Screen,
    e.g. need to communicate each entered field for the Create
    button on press. Additionally, need to understand and resolve
    warnings considering Hardcoded Strings. Investigate using
    @string resource instead.
    */
}
