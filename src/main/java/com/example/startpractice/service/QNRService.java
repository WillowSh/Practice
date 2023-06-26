package com.example.startpractice.service;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.QNREntityMapper;
import com.example.startpractice.dao.entity.QNREntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QNRService {
    @Autowired
    private QNREntityMapper qnrEntityMapper;

    public int addQNRInfo(QNREntity qnrEntity){

        //qnrEntity.setId(UUIDUtil.getOneUUID());
        qnrEntity.setCreationDate(new Date());

        int result= qnrEntityMapper.insert(qnrEntity);
        return result;
    }

    //查询项目列表
    public List<QNREntity> queryQNRList(QNREntity qnrEntity){

        List<QNREntity> result=qnrEntityMapper.queryQNRList(qnrEntity);
        return result;
    }
    public List<QNREntity> queryQNR(QNREntity qnrEntity){

        List<QNREntity> result=qnrEntityMapper.queryQNR(qnrEntity);
        return result;
    }

}
