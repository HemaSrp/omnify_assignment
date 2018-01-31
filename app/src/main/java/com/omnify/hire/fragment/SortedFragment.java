package com.omnify.hire.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omnify.hire.R;
import com.omnify.hire.app.MyApplication;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

/**
 * This class is used to display the sorted array in fragment
 */
public class SortedFragment extends Fragment {

    /**
     * Table view to display the sorted array
     */
    private TableView<String[]> sortedTableView;

    /**
     * Cardview
     */
    private CardView cardViewSort;

    /**
     * To display the sort the text message
     */
    private TextView sortTheTextMessage;

    /**
     * Recieve an action from the service after it completes the sorting
     */
    private BroadcastReceiver getSortedArrays = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String[] headers = {getActivity().getString(R.string.quick_sort_title), getActivity().getString(R.string.merge_sort_title)};
            String[][] data = new String[20][20];
            sortedTableView.setVisibility(View.VISIBLE);
            cardViewSort.setVisibility(View.VISIBLE);
            sortTheTextMessage.setVisibility(View.GONE);
            //Get sorted arrays from service
            int quickSortArray[] = intent.getIntArrayExtra(MyApplication.QUICKSORT);
            int mergeSortArray[] = intent.getIntArrayExtra(MyApplication.MERGE_SORT);

            for (int i = 0; i < 20; i++) {
                //fill first row of table with quicksort values
                data[i][0] = String.valueOf(quickSortArray[i]);

                //fill second row of table with mergesort values
                data[i][1] = String.valueOf(mergeSortArray[i]);
            }

            sortedTableView.setDataAdapter(new SimpleTableDataAdapter(getActivity(), data));
            sortedTableView.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), headers));
        }
    };


    /**
     * Instance of the fragment
     *
     * @return fragment
     */
    public static Fragment newInstance() {
        SortedFragment sortedFragment = new SortedFragment();
        return sortedFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(getSortedArrays);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(MyApplication.ACTION);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(getSortedArrays, filter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sorted, container, false);
        sortedTableView = v.findViewById(R.id.sorted_tableview);
        cardViewSort = v.findViewById(R.id.cardviewSort);
        sortTheTextMessage = v.findViewById(R.id.sortText);
        return v;
    }
}