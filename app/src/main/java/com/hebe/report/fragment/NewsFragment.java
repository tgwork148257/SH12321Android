package com.hebe.report.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebe.report.R;
import com.hebe.report.base.BaseFragment;

/**
 * Created by Hebe on 16/7/5.
 */

public class NewsFragment extends BaseFragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_layout,null);
        return view;
    }
}
