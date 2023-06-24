package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionEntityMapper {

    int insert(QuestionEntity questionEntity);
}
