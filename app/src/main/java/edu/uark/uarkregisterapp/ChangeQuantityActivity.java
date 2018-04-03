package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class ChangeQuantityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_quantity);
    }


}
