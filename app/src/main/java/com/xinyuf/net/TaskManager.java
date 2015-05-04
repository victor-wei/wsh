package com.xinyuf.net;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.xinyuf.bean.Article;
import com.xinyuf.wsh.MainActivity;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaofg on 15/5/2.
 */
public class TaskManager {

    private static TaskManager taskManager=new TaskManager();

    private TaskManager(){}

    public static TaskManager getInstance(){
        return taskManager;
    }

    private static ExecutorService pool;

    public void addTask(OnTaskListener taskListener){
        this.taskListener=taskListener;
        if(pool==null){
            pool= Executors.newFixedThreadPool(12);
        }
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean result = HTMLParse.parseArticle(UrlUtil.ARCHIVES_URL);
                    if(result){
                        mHandler.sendEmptyMessage(1);
                    }else{
                        mHandler.sendEmptyMessage(-1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void addTask(final Article article,OnTaskListener taskListener){
        this.taskListener=taskListener;
        if(pool==null){
            pool= Executors.newFixedThreadPool(12);
        }
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Article result = HTMLParse.parseArticleContent(article);
                    if(result!=null&&result.getContent()!=null){
                        // update db
                        result.update(result.getId());
                        mHandler.sendEmptyMessage(1);
                    }else{
                        mHandler.sendEmptyMessage(-1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                if(taskListener!=null)
                    taskListener.onSucceed();
            }else if(msg.what==-1){
                if (taskListener!=null)
                    taskListener.onFailed();
            }
        }
    };

    private OnTaskListener taskListener;
    public interface OnTaskListener{
        public void onSucceed();
        public void onFailed();
    }

}
