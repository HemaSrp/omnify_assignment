package com.omnify.hire.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.omnify.hire.R;
import com.omnify.hire.app.MyApplication;
import com.omnify.hire.fragment.SortedFragment;
import com.omnify.hire.fragment.UnsortedFragment;
import com.omnify.hire.service.SortingService;

/**
 * This class is used to load the unsorted fragment and sorted fragment.
 */
public class MainActivity extends AppCompatActivity implements UnsortedFragment.RegenerateArrayButtonClick {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //Load both fragments into top and bottom pane of the screen
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_unsorted, UnsortedFragment.newInstance())
                .replace(R.id.fragment_sorted, SortedFragment.newInstance())
                .commit();
    }


    @Override
    public void onClickButton(String text) {
        if (text.equals(MyApplication.REGENERATE_ARRAY)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_unsorted, UnsortedFragment.newInstance())
                    .commit();
        } else {
            //Start sorting service
            Intent i= new Intent(getApplicationContext(), SortingService.class);
            startService(i);
        }
    }
}
