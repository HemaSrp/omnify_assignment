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

/**
 * This is used to display the random numbers and display the button to perform sorting.
 */
public class UnsortedFragment extends Fragment implements View.OnClickListener {
    /**
     * Callback to interact with the activity
     */
    private RegenerateArrayButtonClick mCallback;

    /**
     * To display the random text
     */
    private TextView randomNoText;

    /**
     * Instance of the fragment
     *
     * @return unsortedFragment
     */
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
        Button btnRegenerateArray = v.findViewById(R.id.btn_regenerator_array);
        Button btnSortArray = v.findViewById(R.id.btn_sort_array);
        btnRegenerateArray.setOnClickListener(this);
        btnSortArray.setOnClickListener(this);
        randomNoText = v.findViewById(R.id.randomno_array);
        randomNoText.setText(generateRandomNumString());

        return v;
    }

    /**
     * This method is ised to generate the random number.
     *
     * @return random number
     */
    private String generateRandomNumString() {
        String randomNoStr = "";
        Random rand = new Random();
        //For generating two digit random numbers
        int minimum = 10;
        int maximum = 99;

        //create a string of 20 random numbers
        for (int i = 0; i < 20; i++) {
            MyApplication.randomNum[i] = minimum + rand.nextInt((maximum - minimum) + 1);
            randomNoStr = randomNoStr + String.valueOf(MyApplication.randomNum[i]) + " ";
        }

        return randomNoStr;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_regenerator_array) {

            mCallback.onClickButton(getResources().getString(R.string.regenerateArray));

        } else if (v.getId() == R.id.btn_sort_array) {
            //Start sorting service
            mCallback.onClickButton(getResources().getString(R.string.sortArray));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RegenerateArrayButtonClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement RegenerateArrayButtonClick");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    /**
     * To interact with the activity
     */
    public interface RegenerateArrayButtonClick {
        void onClickButton(String text);
    }
}
