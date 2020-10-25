package com.proto.service.Impl;

import com.proto.dao.EquipmentDao;
import com.proto.pojo.Equipment;
import com.proto.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;

    public boolean save(Equipment equipment){
        try {
            equipmentDao.save(equipment);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteById(Integer id){
        try {
            equipmentDao.deleteById(id);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean update(Equipment equipment){
        return save(equipment);
    }

    public List<Equipment> findAll(){
        List<Equipment> list;
        try {
            list=equipmentDao.findAll();
        }catch (Exception e){
            return null;
        }
        return list;
    }
}
