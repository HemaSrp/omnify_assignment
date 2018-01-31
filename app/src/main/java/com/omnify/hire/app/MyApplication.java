package com.omnify.hire.app;

import android.app.Application;

/**
 * This is the application class of the app
 */
public class MyApplication extends Application {

    /**
     * Braodcast action
     */
    public static final String ACTION = "com.omnify.hire.Broadcast";

    /**
     * Constant value for merge sort array
     */
    public static final String MERGE_SORT = "MergeSortArray";

    /**
     * Constant value for quick sort array
     */
    public static final String QUICKSORT = "QuickSortArray";

    /**
     * Random number generator
     */
    public static int[] randomNum = new int[20];

    /**
     * Constant value for regenerate array
     */
    public static String REGENERATE_ARRAY = "RegenerateArray";

}
