package com.xinyuf.wsh;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.xinyuf.adapter.ArticlesAdapter;
import com.xinyuf.bean.Article;
import com.xinyuf.net.HTMLParse;
import com.xinyuf.net.TaskManager;
import com.xinyuf.net.UrlUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static com.xinyuf.net.HTMLParse.parseArticleContent;
import static com.xinyuf.net.TaskManager.OnTaskListener;
import static com.xinyuf.net.UrlUtil.*;


public class MainActivity extends AppCompatActivity{

    private PtrFrameLayout ptrFrameLayout;
    private ListView listView;
    private ArticlesAdapter mAdapter;
    private List<Article> mList;
    private List<Article> list;
    private static final int PAGE_SIZE=12;
    private int visibleLastIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptrFrameLayout= (PtrFrameLayout) this.findViewById(R.id.ptr_frame);
        listView= (ListView) this.findViewById(R.id.article_list);
        mAdapter=new ArticlesAdapter(this,getDate());
        listView.setAdapter(mAdapter);

        //下拉刷新
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {

            }
        });

        //上拉自动加载
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int itemsLastIndex = mAdapter.getCount()-1;  //数据集最后一项的索引
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE&& visibleLastIndex == itemsLastIndex) {
                    // 如果是自动加载,可以在这里放置异步加载数据的代码
                    getDate();
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
            }
        });
    }

    private List<Article> getDate(){
        if(list==null) {
            list = new LinkedList<Article>();
        }
        if(mList==null) {
            mList = DataSupport.findAll(Article.class);
            if (mList == null) {
                TaskManager.getInstance().addTask(new TaskManager.OnTaskListener() {
                    @Override
                    public void onSucceed() {
                        //save db
                        DataSupport.saveAll(HTMLParse.articles);
                        mList = DataSupport.findAll(Article.class);
                    }

                    @Override
                    public void onFailed() {
                        Log.v("jsoup", "parse failed!");
                    }
                });
            }
        }
        //
        int size=list.size();
        for (int i=size;i<size+PAGE_SIZE;i++) {
            TaskManager.getInstance().addTask(mList.get(i),null);
        }
        list.addAll(DataSupport.limit(PAGE_SIZE).offset(size).find(Article.class));
        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
