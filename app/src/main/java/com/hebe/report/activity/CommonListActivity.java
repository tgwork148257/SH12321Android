package com.hebe.report.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用列表
 * Created by Hebe on 7-11-011.
 */

public class CommonListActivity extends BaseActivity {

    @ViewInject(R.id.navi_title)
    private TextView navi_title;
    @ViewInject(R.id.navi_back)
    private ImageView navi_back;
    @ViewInject(R.id.commonlist)
    private ListView commonlist;

    private List<String> items = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commonlist_activity);
        x.view().inject(this);
        navi_title.setText(getIntent().getStringExtra("title"));
        items = getIntent().getStringArrayListExtra("items");
        navi_back.setVisibility(View.VISIBLE);
        navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commonlist.setAdapter(new CommonListAdapter());
        commonlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("address",items.get(position));
                setResult(101,intent);
                finish();
            }
        });
    }

    private class CommonListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(CommonListActivity.this).inflate(R.layout.commonlist_item,null,false);
            TextView tv = (TextView) convertView.findViewById(R.id.commonlist_tv);
            tv.setText(items.get(position));
            return convertView;
        }
    }
}
