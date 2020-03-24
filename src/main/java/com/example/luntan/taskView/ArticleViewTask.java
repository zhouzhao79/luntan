package com.example.luntan.taskView;

import com.example.luntan.util.RedisUtils;
import com.example.luntan.mapper.QuestionMapper;
import com.example.luntan.model.Question;
import com.example.luntan.model.QuestionExample;
import com.example.luntan.service.QuesstionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 这是一个定时类
 */
@Component
@Slf4j
public class ArticleViewTask {

    @Resource
    private RedisUtils redisUtil;
    @Resource
    QuesstionService articleService;
    @Autowired
    QuestionMapper questionMapper;

	// 每天凌晨一点执行
    //0 0/5 0-0 * * ?  每半小时一次
    //0 24 11 ? * *   每天十一点24 触发
   // @Scheduled(cron = "0 0 1 * * ? ")
    @Scheduled(cron = "0 0/30 0-23 * * ? ")
    @Transactional(rollbackFor=Exception.class)
    public void createHyperLog() {
        log.info("浏览量入库开始");

        List<Question> list = questionMapper.selectByExample(new QuestionExample());
        for (Question question:list
             ) {
            // 获取每一篇文章在redis中的浏览量，存入到数据库中
            String key  = "articleId_"+question.getId();
            long view = redisUtil.size(key);
            if(view>0){
                Question articleDO = questionMapper.selectByPrimaryKey(question.getId());
                Integer views = (int)view + articleDO.getViewCount();
                articleDO.setViewCount(views);
                int num = questionMapper.updateByPrimaryKey(articleDO);
                if (num != 0) {
                    log.info("数据库更新后的浏览量为：{}", views);
                    redisUtil.del(key);
                }
            }
        }
//        list.forEach(articleId ->{
//
//        });
        log.info("浏览量入库结束");
    }

}
