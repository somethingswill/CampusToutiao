package com.campusToutiao.CampusToutiaoEE.service.impl;

import com.campusToutiao.CampusToutiaoEE.dao.CommentMapper;
import com.campusToutiao.CampusToutiaoEE.dao.NewsCategoryMapper;
import com.campusToutiao.CampusToutiaoEE.dao.NewsMapper;
import com.campusToutiao.CampusToutiaoEE.dao.UserMapper;
import com.campusToutiao.CampusToutiaoEE.entity.News;
import com.campusToutiao.CampusToutiaoEE.entity.NewsCategory;
import com.campusToutiao.CampusToutiaoEE.entity.User;
import com.campusToutiao.CampusToutiaoEE.entity.pojo.ToutiaoResult;
import com.campusToutiao.CampusToutiaoEE.service.INewsService;
import com.campusToutiao.CampusToutiaoEE.utils.ToutiaoUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NewsCategoryMapper newsCategoryMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ToutiaoResult postNews(Integer categoryId, String newsTitle, String newsContent, Integer userId) {
        if (null == categoryId) {
            return new ToutiaoResult(1, "资讯分类为空，发布资讯失败");
        }
        if (StringUtils.isBlank(newsTitle)) {
            return new ToutiaoResult(1, "资讯标题为空，发布资讯失败");
        }
        if (StringUtils.isBlank(newsContent)) {
            return new ToutiaoResult(1, "资讯内容为空，发布资讯失败");
        }
        // 设置属性
        News news = new News();
        news.setCategoryId(categoryId);
        news.setNewsTitle(newsTitle);
        news.setNewsContent(newsContent);
        news.setLikeCount(0);
        news.setDislikeCount(0);
        news.setCommentCount(0);
        news.setUserId(userId);
        // 解析资讯内容中的图片，挑选一张作为首页的展示图
        Document document = Jsoup.parse(newsContent);
        Element img = document.selectFirst("img");
        if (null != img) {
            // 用户发布的资讯中有图片
            String imgUrl = img.attr("src");
            news.setNewsImgs(imgUrl);
        } else {
            // 用户发布的资讯中无图片，这里随机生成一张图片
            news.setNewsImgs("https://www.gravatar.com/avatar/" + ToutiaoUtils.uuidStr() + "?s=230&d=identicon");
        }
        news.setCreateDate(new Date());
        // 写入数据库
        newsMapper.insertSelective(news);
        return new ToutiaoResult(0, "发布资讯成功");
    }

    @Override
    public ToutiaoResult getNewsListByCategoryId(Integer categoryId, Integer page, Integer pageSize) {

        List<News> newsList = new ArrayList<>();
        // sql中limit的索引值
        int pageIndex = (page - 1) * pageSize;

        // categoryId为-1，表示要查询全部类型的资讯
        if (-1 == categoryId) {
            newsList = newsMapper.getAllNewsListInPage(pageIndex, pageSize);
        } else {
            newsList = newsMapper.getNewsListByCategoryIdInPage(categoryId, pageIndex, pageSize);
        }

        // 要返回的资讯的详细信息
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (News news : newsList) {
            User user = userMapper.selectByPrimaryKey(news.getUserId());
            Map<String, Object> mp = ToutiaoUtils.object2Map(news);
            // 存入类型名
            NewsCategory newsCategory = newsCategoryMapper.selectByPrimaryKey(news.getCategoryId());
            mp.put("categoryName", newsCategory.getCategoryName());
            // 存入用户名、昵称
            mp.put("postUsername", user.getUsername());
            mp.put("postNickname", user.getNickname());
            resultList.add(mp);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("newsList", resultList);
        return new ToutiaoResult(0, resultMap);

    }

    @Override
    public ToutiaoResult getNewsByNewsId(Integer newsId) {

        News news = newsMapper.selectByPrimaryKey(newsId);
        User user = userMapper.selectByPrimaryKey(news.getUserId());
        Map<String, Object> tempMp = ToutiaoUtils.object2Map(news);
        // 存入昵称
        tempMp.put("postNickname", user.getNickname());

        Map<String, Object> mp = new HashMap<>();
        mp.put("newsInfo", tempMp);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult getHotNewsList() {

        List<News> hotNewsList = newsMapper.getHotNewsList();

        Map<String, Object> mp = new HashMap<>();
        mp.put("hotNewsList", hotNewsList);
        return new ToutiaoResult(0, mp);
    }

    @Override
    public ToutiaoResult delNewsByNewsId(Integer newsId) {
        newsMapper.deleteByPrimaryKey(newsId);
        // 去删除这条资讯的所有评论
        commentMapper.delCommentByNewsId(newsId);
        return new ToutiaoResult(0, "删除资讯成功");
    }

    @Override
    public ToutiaoResult batchDelNews(List<Integer> delNewsIdList) {
        // 循环删除资讯和资讯对应的评论
        for (Integer newsId : delNewsIdList) {
            newsMapper.deleteByPrimaryKey(newsId);
            commentMapper.delCommentByNewsId(newsId);
        }
        return new ToutiaoResult(0, "批量删除资讯成功");
    }

    @Override
    public ToutiaoResult getAllNewsCount() {
        int newsTotal = newsMapper.getAllNewsCount();

        Map<String, Object> mp = new HashMap<>();
        mp.put("newsTotal", newsTotal);
        return new ToutiaoResult(0, mp);
    }

}
