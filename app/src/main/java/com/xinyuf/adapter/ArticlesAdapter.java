package com.xinyuf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xinyuf.bean.Article;
import com.xinyuf.wsh.R;

import java.util.List;

/**
 * Created by zhaofg on 15/5/3.
 */
public class ArticlesAdapter extends BaseAdapter {

    private Context mContext;
    private List<Article> list;

    public ArticlesAdapter(Context context,List<Article> list){
        this.mContext=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView mHolder=null;
        if(convertView==null){
            mHolder=new HolderView();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.article_list_item,null);
            mHolder.img= (ImageView) convertView.findViewById(R.id.article_img);
            mHolder.title= (TextView) convertView.findViewById(R.id.article_title);
            mHolder.time= (TextView) convertView.findViewById(R.id.create_time);
            mHolder.category= (TextView) convertView.findViewById(R.id.article_category);
            convertView.setTag(mHolder);
        }else{
            mHolder= (HolderView) convertView.getTag();
        }
        Article article=list.get(position);
        Picasso.with(mContext).load(article.getPicture()).into(mHolder.img);
        mHolder.title.setText(article.getTitle());
        mHolder.time.setText(article.getDate());
        if(article.getCategory()!=null&&article.getCategory().size()>0) {
            mHolder.category.setText(article.getCategory().get(0).getName());
        }
        return convertView;
    }

    static class HolderView{
        ImageView img;
        TextView title;
        TextView time;
        TextView category;
    }
}
