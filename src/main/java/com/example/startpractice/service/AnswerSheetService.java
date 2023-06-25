package com.example.startpractice.service;

import com.example.startpractice.dao.AnswerEntityMapper;
import com.example.startpractice.dao.AnswerSheetEntityMapper;
import com.example.startpractice.dao.entity.AnswerEntity;
import com.example.startpractice.dao.entity.AnswerSheetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerSheetService {
    @Autowired
    AnswerSheetEntityMapper answerSheetEntityMapper;

    public List<AnswerSheetEntity> queryAnswerSheetList(AnswerSheetEntity answerSheetEntity){

        List<AnswerSheetEntity> result=answerSheetEntityMapper.queryAnswerSheetList(answerSheetEntity);
        return result;
    }
}
