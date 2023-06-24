package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.OptionEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OptionEntityMapper {

    int insert(OptionEntity optionEntity);

}
