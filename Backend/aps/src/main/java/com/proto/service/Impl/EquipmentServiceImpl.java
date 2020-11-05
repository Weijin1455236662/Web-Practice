package com.proto.service.Impl;

import com.proto.dao.EquipmentDao;
import com.proto.pojo.Equipment;
import com.proto.service.EquipmentService;
import com.proto.service.client.erpservice.resource.ResourceItem;
import com.proto.service.client.erpservice.resource.ResourceServiceForApp;
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


    public boolean importEquipmentData(){
        try {
            ResourceServiceForApp resourceServiceForApp=new ResourceServiceForApp();
            List<ResourceItem> lineResourceList=resourceServiceForApp.getLineResource();
            for (ResourceItem item:lineResourceList){
                Equipment e=new Equipment();
                e.setName(item.getName());
                e.setAmount(item.getAmount());
                e.setType(item.getType());
                equipmentDao.save(e);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
