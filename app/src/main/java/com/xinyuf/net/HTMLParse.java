package com.xinyuf.net;

import com.xinyuf.bean.Article;
import com.xinyuf.bean.Category;
import com.xinyuf.bean.Tags;
import com.xinyuf.util.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaofg on 15/5/2.
 */
public class HTMLParse {

    public static List<Article> articles=new ArrayList<Article>();

    public static boolean parseArticle(String url) throws IOException {
        Document doc=Jsoup.connect(url).get();
        Elements links = doc.select("ul.archives-list a[href]");

        if(links!=null&&links.size()>0){
            articles.clear();
            for(Element link:links){
                String mUrl=link.attr("href");
                String mTitle=Utils.parseArticleTitle(link.text());
                if(mTitle!=null&&mUrl!=null){
                    Article article=new Article();
                    article.setUrl(mUrl);
                    article.setTitle(mTitle);
                    articles.add(article);
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public static Article parseArticleContent(Article article) throws IOException {
        if(article==null)
            return null;

        Document doc=Jsoup.connect(article.getUrl()).get();
        Elements pics=doc.select("div.entry-cover img[src]");
        Elements dates=doc.select("time.entry-date");
        Elements categories=doc.select("span.categories-links a[href]");
        Elements audios=doc.select("audio a[href]");
        Elements tags=doc.select("footer.entry-footer span.tags-links a[href]");
        Elements author=doc.select("a.author span.fn");

        if(pics!=null&&pics.size()>0)
            article.setPicture(pics.first().attr("src"));
        if(dates!=null&&dates.size()>0)
            article.setDate(dates.first().text());
            article.setEffectDate(Utils.getArticleEffectDate());
        if(categories!=null&&categories.size()>0) {
            List<Category> list = new ArrayList<Category>();
            for (Element category : categories) {
                Category category1=new Category();
                category1.setName(category.text());
                category1.setUrl(category.attr("href"));
                list.add(category1);
            }
            article.setCategory(list);
        }
        if(audios!=null&& audios.size()>0)
            article.setAudio(audios.first().text());
        if(tags!=null&&tags.size()>0){
            List<Tags> list=new ArrayList<Tags>();
            for (Element tag:tags){
                Tags tag1=new Tags();
                tag1.setTag(tag.text());
                tag1.setUrl(tag.attr("href"));
                list.add(tag1);
            }
            article.setTags(list);
        }
        //
        doc.select("div.yueting-skin").remove();
        Elements contents=doc.select("div.entry-content");
        if(contents!=null&&contents.size()>0)
            article.setContent(contents.html());
        if(author!=null)
            article.setAuthor(author.first().text());

        return article;
    }
}
