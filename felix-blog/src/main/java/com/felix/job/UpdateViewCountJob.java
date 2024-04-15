package com.felix.job;

import com.felix.constants.SystemConstants;
import com.felix.domain.entity.Article;
import com.felix.service.ArticleService;
import com.felix.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName:UpdateViewCountJob
 * Package:com.felix.job
 * Description:
 * 定时任务每隔10分钟把Redis中的浏览量更新到数据库中
 * @Author FelixT
 * @Create 2024/4/12 20:07
 * @Version 1.0
 */
@Component
public class UpdateViewCountJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/10 * * * ? ")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.VIEW_COUNT_KEY);
        List<Article> articles = viewCountMap.entrySet()
                .stream().map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);
    }
}
