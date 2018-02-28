
package edu.uark.uarkregisterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateEmployeeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee_screen);
    }
    /*Still need to implement server side code for this Screen,
    e.g. need to communicate each entered field for the Create
    button on press. Additionally, need to understand and resolve
    warnings considering Hardcoded Strings. Investigate using
    @string resource instead.
    */
}
