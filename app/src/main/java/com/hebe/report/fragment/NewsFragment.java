package com.hebe.report.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hebe.report.R;
import com.hebe.report.base.BaseFragment;
import com.hebe.report.bean.MyReport;
import com.hebe.report.bean.NewsListBean;
import com.hebe.report.utils.Utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Hebe on 16/7/5.
 */

public class NewsFragment extends BaseFragment {

    private View view;
    @ViewInject(R.id.news_list)
    private ListView news_list;
    private NewsAdapter adapter;
    private NewsListBean newsListBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_layout,null);
        x.view().inject(this,view);
        adapter = new NewsAdapter();
        news_list.setAdapter(adapter);
        getReportList();
        return view;
    }

    private class NewsAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsListBean==null?0:newsListBean.getData().getList().size();
        }

        @Override
        public Object getItem(int position) {
            return newsListBean==null?null:newsListBean.getData().getList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.news_item_layout, null, false);
                holder.news_item_img = (ImageView) convertView.findViewById(R.id.news_item_img);
                holder.news_item_title = (TextView) convertView.findViewById(R.id.news_item_title);
                holder.news_item_time = (TextView) convertView.findViewById(R.id.news_item_time);
                holder.news_item_from = (TextView) convertView.findViewById(R.id.news_item_from);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            x.image().bind(holder.news_item_img,newsListBean.getData().getList().get(position).getImage());
            holder.news_item_title.setText(newsListBean.getData().getList().get(position).getTitle());
            holder.news_item_time.setText(newsListBean.getData().getList().get(position).getAdd_time());
            holder.news_item_from.setText(newsListBean.getData().getList().get(position).getSource());

            return convertView;
        }
    }

    private class ViewHolder{
        ImageView news_item_img;
        TextView news_item_title;
        TextView news_item_time;
        TextView news_item_from;
    }

    public void getReportList(){
        RequestParams params = Utils.getDefaultParams("App/newsList");
        params.addBodyParameter("user_token", Utils.getUserToken(getActivity()));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                NewsListBean bean = Utils.jsonParase(result,NewsListBean.class);
                if (bean != null && bean.getCode() == 200){
                    newsListBean = bean;
                    adapter.notifyDataSetChanged();
                }else {
                    showToast("请重试");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
