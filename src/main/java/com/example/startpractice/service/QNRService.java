package com.example.startpractice.service;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.QNREntityMapper;
import com.example.startpractice.dao.entity.QNREntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QNRService {
    @Autowired
    private QNREntityMapper qnrEntityMapper;

    public int addQNRInfo(QNREntity qnrEntity){

        qnrEntity.setId(UUIDUtil.getOneUUID());
        qnrEntity.setCreationDate(new Date());

        int result= qnrEntityMapper.insert(qnrEntity);
        return result;
    }

}
