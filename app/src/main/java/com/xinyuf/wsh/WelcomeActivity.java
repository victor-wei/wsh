package com.xinyuf.wsh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.xinyuf.bean.Article;
import com.xinyuf.net.HTMLParse;
import com.xinyuf.net.TaskManager;

import org.litepal.crud.DataSupport;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TaskManager.getInstance().addTask(new TaskManager.OnTaskListener() {
            @Override
            public void onSucceed() {
                //save db
                DataSupport.deleteAll(Article.class);
                DataSupport.saveAll(HTMLParse.articles);
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onFailed() {
                Log.v("jsoup", "parse failed!");
            }
        });
    }

}
