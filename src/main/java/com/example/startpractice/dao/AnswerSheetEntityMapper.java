package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.AnswerEntity;
import com.example.startpractice.dao.entity.AnswerSheetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnswerSheetEntityMapper {
    List<AnswerSheetEntity> queryAnswerSheetList(AnswerSheetEntity answerSheetEntity);
}
