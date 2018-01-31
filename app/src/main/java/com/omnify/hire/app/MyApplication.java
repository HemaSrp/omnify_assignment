package com.omnify.hire.app;

import android.app.Application;

/**
 * Created by hema on 31/1/18.
 */

public class MyApplication extends Application {

    public static final String ACTION = "com.omnify.hire.Broadcast";
    public static final String MERGESORT = "MergeSortArray";
    public static final String QUICKSORT = "QuickSortArray";

    public static int[] randomNum = new int[20];

    public static String REGENERATE_ARRAY = "RegenerateArray";

}
