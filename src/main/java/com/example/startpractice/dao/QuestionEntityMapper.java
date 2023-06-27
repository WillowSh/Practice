package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.QuestionEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionEntityMapper {

    int insert(QuestionEntity questionEntity);

    int deleteQuestionById(QuestionEntity questionEntity);

    int modifyQuestionInfo(QuestionEntity questionEntity);

    List<QuestionEntity> queryQuestionList(QuestionEntity questionEntity);
    List<QuestionEntity> queryQuestionList2(QuestionEntity questionEntity);
    List<QuestionEntity> queryQuestionContent(QuestionEntity questionEntity);

    List<QuestionEntity> queryQuestionListForStat(QuestionEntity questionEntity);
}
