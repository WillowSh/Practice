package com.example.startpractice.service;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.QuestionEntityMapper;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import com.example.startpractice.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionEntityMapper questionEntityMapper;
    public int addQuestionInfo(QuestionEntity questionEntity){
        //questionEntity.setId(UUIDUtil.getOneUUID());

        int result= questionEntityMapper.insert(questionEntity);
        return result;
    }

    public int deleteQuestionById(QuestionEntity questionEntity){
        int questionResult=questionEntityMapper.deleteQuestionById(questionEntity);
        return questionResult;
    }

    public int modifyQuestionInfo(QuestionEntity questionEntity){

        int questionResult=questionEntityMapper.modifyQuestionInfo(questionEntity);
        return questionResult;
    }

    public List<QuestionEntity> queryQuestionList(QuestionEntity questionEntity){

        List<QuestionEntity> result=questionEntityMapper.queryQuestionList(questionEntity);
        return result;
    }

    public List<QuestionEntity> queryQuestionList2(QuestionEntity questionEntity){

        List<QuestionEntity> result=questionEntityMapper.queryQuestionList2(questionEntity);
        return result;
    }
    public List<QuestionEntity> queryQuestionContent(QuestionEntity questionEntity){

        List<QuestionEntity> result=questionEntityMapper.queryQuestionContent(questionEntity);
        return result;
    }


    public List<QuestionEntity> queryQuestionListForStat(QuestionEntity questionEntity){

        List<QuestionEntity> result=questionEntityMapper.queryQuestionListForStat(questionEntity);
        return result;
    }
}
