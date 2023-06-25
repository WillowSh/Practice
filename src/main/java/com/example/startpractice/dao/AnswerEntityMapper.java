package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnswerEntityMapper {
    List<AnswerEntity> queryAnswerList(AnswerEntity answerEntity);
}
