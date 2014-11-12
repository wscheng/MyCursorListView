package com.example.weisheng_cheng.mycursorlistview;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WeiSheng_Cheng on 2014/11/12.
 */
public class MyListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_list_view, container, false);//super.onCreateView(inflater, container, savedInstanceState);
    }
}
