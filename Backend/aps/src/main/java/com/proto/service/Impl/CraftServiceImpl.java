package com.proto.service.Impl;

import com.proto.dao.CraftDao;
import com.proto.pojo.Craft;
import com.proto.service.CraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CraftServiceImpl implements CraftService {
    @Autowired
    private CraftDao craftDao;

    public List<Craft> findAll(){
        List<Craft> list;
        try {
            list=craftDao.findAll();
        }catch (Exception e){
            return null;
        }
        return list;
    }
}
