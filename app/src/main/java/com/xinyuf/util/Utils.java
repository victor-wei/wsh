package com.xinyuf.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zhaofg on 15/5/2.
 */
public class Utils {

    public static Date getArticleEffectDate(){
        GregorianCalendar gc=new GregorianCalendar();
        gc.setTime(new Date());
        gc.add(Calendar.HOUR,2);
        return gc.getTime();
    }

    public static String []Category={
        "Read","VOL","FM","第xxx期","music","NO","广播剧"
    };

    public static String parseArticleTitle(String title){
        if(title==null){
            return null;
        }
        if(title.contains("] ")){
            return title.split("] ")[1];
        }else if(title.contains("VOL.")){
            return title.split(" ")[1];
        }else if(title.contains("广播剧")||title.contains("致网友")){
            return null;
        }else{
            return null;
        }
    }
}
