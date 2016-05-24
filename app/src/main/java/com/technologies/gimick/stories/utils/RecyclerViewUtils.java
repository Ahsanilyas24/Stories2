package com.technologies.gimick.stories.utils;


/*
This class is used for list view called as recyler view in android.

 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;


public class RecyclerViewUtils {

    public static LinearLayoutManager getVerticalLinearLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    public static LinearLayoutManager getHorizontalLinearLayoutManager(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        return manager;
    }

    public static GridLayoutManager getGridLayoutManager(Context context) {
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        return manager;
    }
}
