package com.omnify.hire.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.omnify.hire.app.MyApplication;

/**
 * This service class runs in background to perform quick sort and merge sort.
 */
public class SortingService extends IntentService {


    public SortingService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //sort arrays
        int quickSortArray[] = MyApplication.randomNum;
        quickSort(quickSortArray, 0, quickSortArray.length - 1);

        int mergeSortArray[] = MyApplication.randomNum;
        mergeSort(mergeSortArray, 0, mergeSortArray.length);

        //Send a Broadcast Intent with the two sorted arrays
        Intent broadcastintent = new Intent(MyApplication.ACTION);
        broadcastintent.putExtra(MyApplication.QUICKSORT, quickSortArray);
        broadcastintent.putExtra(MyApplication.MERGE_SORT, mergeSortArray);
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastintent);
    }

    /**
     * This method is used to perform the quick sort
     *
     * @param array array
     * @param low   low value
     * @param high  high value
     */
    private void quickSort(int[] array, int low, int high) {
        if (array == null || array.length == 0 || low >= high)
            return;

        //Pick the pivot- middle element of the given array
        int mid = low + (high - low) / 2;
        int pivot = array[mid];

        int i = low;
        int j = high;
        while (i <= j) {

            //increment i till it reaches pivot
            while (array[i] < pivot)
                i++;

            //decrement j till it reaches pivot
            while (array[j] > pivot)
                j--;

            if (i <= j) {
                //swap
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        //The array has been divided into two parts
        //Recursively sort each of them
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    /**
     * This method is used to perform the merge sort
     *
     * @param array array
     * @param low   low value
     * @param high  high value
     */
    private void mergeSort(int[] array, int low, int high) {
        int size = high - low;
        if (size <= 1)
            return;

        int mid = low + size / 2;

        // Recursively sort each section
        mergeSort(array, low, mid);
        mergeSort(array, mid, high);

        //Merge two sorted arrays
        int[] temp = new int[size];
        int i = low;
        int j = mid;

        for (int k = 0; k < size; k++) {
            if (i == mid)
                temp[k] = array[j++];

            else if (j == high)
                temp[k] = array[i++];

            else if (array[j] < array[i])
                temp[k] = array[j++];

            else
                temp[k] = array[i++];
        }

        System.arraycopy(temp, 0, array, low, size);
    }
}