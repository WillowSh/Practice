package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.AnswerEntity;
import com.example.startpractice.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnswerEntityMapper {
    List<AnswerEntity> queryAnswerList(AnswerEntity answerEntity);
    List<AnswerEntity> queryAnswerList2(AnswerEntity answerEntity);
    List<AnswerEntity> queryAnswerForStat(AnswerEntity answerEntity);
    int insert(AnswerEntity answerEntity);
}
