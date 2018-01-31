package com.omnify.hire.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.omnify.hire.R;
import com.omnify.hire.app.MyApplication;

import java.util.Random;

public class UnsortedFragment extends Fragment implements View.OnClickListener {

    regenerateArrayButtonClick mCallback;
    TextView randomNoText;

    public UnsortedFragment() {
    }

    public static Fragment newInstance() {
        UnsortedFragment unsortedFragment = new UnsortedFragment();
        return unsortedFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_unsorted, container, false);
        Button btnRegenerateArray = v.findViewById(R.id.regen_btn);
        Button btnSortArray = v.findViewById(R.id.sort_btn);
        btnRegenerateArray.setOnClickListener(this);
        btnSortArray.setOnClickListener(this);
        randomNoText = v.findViewById(R.id.randomno_array);
        randomNoText.setText(generateRandomNumString());

        return v;
    }

    private String generateRandomNumString(){
        String randomNoStr="";
        Random rand = new Random();
        //For generating two digit random numbers
        int minimum = 10;
        int maximum = 99;

        //create a string of 20 random numbers
        for(int i=0; i<20; i++) {
            MyApplication.randomNum[i] = minimum + rand.nextInt((maximum - minimum) + 1);
            randomNoStr = randomNoStr + String.valueOf(MyApplication.randomNum[i]) + " ";
        }

        return randomNoStr;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.regen_btn) {

            mCallback.sendText(getResources().getString(R.string.regenerateArray));

        } else if (v.getId() == R.id.sort_btn) {
            //Start sorting service
            mCallback.sendText(getResources().getString(R.string.sortArray));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (regenerateArrayButtonClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement regenerateArrayButtonClick");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }


    public interface regenerateArrayButtonClick {
        void sendText(String text);
    }
}
