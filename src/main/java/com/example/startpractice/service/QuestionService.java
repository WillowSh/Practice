package com.example.startpractice.service;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.QuestionEntityMapper;
import com.example.startpractice.dao.entity.ProjectEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
