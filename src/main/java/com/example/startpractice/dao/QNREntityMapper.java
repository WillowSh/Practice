package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.QNREntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QNREntityMapper {

    int insert(QNREntity qnrEntity);
}
