package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.OptionEntity;
import com.example.startpractice.dao.entity.OptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface OptionEntityMapper {

    int insert(OptionEntity optionEntity);

    List<OptionEntity> queryOptionList(OptionEntity optionEntity);
}
