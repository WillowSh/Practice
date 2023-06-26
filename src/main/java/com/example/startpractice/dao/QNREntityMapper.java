package com.example.startpractice.dao;

import com.example.startpractice.dao.entity.QNREntity;
import com.example.startpractice.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QNREntityMapper {

    int insert(QNREntity qnrEntity);

    List<QNREntity> queryQNRList(QNREntity qnrEntity);

    int deleteQNRById(QNREntity qnrEntity);
}
