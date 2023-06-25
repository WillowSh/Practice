package com.example.startpractice.service;

import com.example.startpractice.dao.AnswerEntityMapper;
import com.example.startpractice.dao.entity.AnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswerService {
    @Autowired
    AnswerEntityMapper answerEntityMapper;

    public List<AnswerEntity> queryAnswerList(AnswerEntity answerEntity){

        List<AnswerEntity> result=answerEntityMapper.queryAnswerList(answerEntity);
        return result;
    }
}
