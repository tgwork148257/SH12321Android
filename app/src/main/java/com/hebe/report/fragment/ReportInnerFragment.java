package com.hebe.report.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseFragment;
import com.hebe.report.bean.ReportItemBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hebe on 7-5-005.
 */

public class ReportInnerFragment extends BaseFragment{

    private View view;
    private List<ReportItemBean> itemBeens = new ArrayList<ReportItemBean>();

    public void setItemBeens(List<ReportItemBean> items) {
        itemBeens = items;
    }

    @ViewInject(R.id.report_grid)
    private GridView report_grid;

    private ReportItemAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_report_inner_layout,null);
        x.view().inject(this,view);
        adapter = new ReportItemAdapter();
        report_grid.setAdapter(adapter);
        return view;
    }

    private class ReportItemAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return itemBeens.size();
        }

        @Override
        public Object getItem(int position) {
            return itemBeens.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.report_item,null,false);
            int height = (view.getHeight() - 2*dip2px(getActivity(),1))/3;
            int width = (getDisplayMetrics().widthPixels - 2*dip2px(getActivity(),1))/3;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width,height);
            convertView.setLayoutParams(params);
            ImageView icon = (ImageView) convertView.findViewById(R.id.report_item_icon);
            if (itemBeens.get(position).getDrawableId() != 0)
                icon.setImageDrawable(getResources().getDrawable(itemBeens.get(position).getDrawableId()));

            TextView name = (TextView) convertView.findViewById(R.id.report_item_name);
            name.setText(itemBeens.get(position).getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemBeens.get(position).getDrawableId() != 0)
                    showToast(itemBeens.get(position).getName());
                }
            });
            return convertView;
        }
    }
}
