package com.hebe.report.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hebe.report.R;
import com.hebe.report.activity.MyReportListActivity;
import com.hebe.report.base.BaseFragment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Hebe on 16/7/5.
 */

public class MeFragment extends BaseFragment {

    @ViewInject(R.id.myreport_layout)
    private LinearLayout myreport_layout;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me_layout,null);
        x.view().inject(this,view);

        myreport_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyReportListActivity.class));
            }
        });
        return view;
    }
}
