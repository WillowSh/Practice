package com.example.startpractice.service;

import com.example.startpractice.common.utils.UUIDUtil;
import com.example.startpractice.dao.OptionEntityMapper;
import com.example.startpractice.dao.entity.OptionEntity;
import com.example.startpractice.dao.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    @Autowired
    OptionEntityMapper optionEntityMapper;

    public int addOptionInfo(OptionEntity optionEntity){
        //optionEntity.setId(UUIDUtil.getOneUUID());

        int result= optionEntityMapper.insert(optionEntity);
        return result;
    }

    public List<OptionEntity> queryOptionList(OptionEntity optionEntity){

        List<OptionEntity> result=optionEntityMapper.queryOptionList(optionEntity);
        return result;
    }
    
}
