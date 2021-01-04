package com.proto.service.Impl;

import com.proto.dao.CraftDao;
import com.proto.dao.EquipmentDao;
import com.proto.dao.TeamDao;
import com.proto.pojo.Craft;
import com.proto.pojo.Equipment;
import com.proto.pojo.Team;
import com.proto.service.CraftService;
import com.proto.service.client.erpservice.bom.BomItem;
import com.proto.service.client.erpservice.bom.BomServiceForApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CraftServiceImpl implements CraftService {
    @Autowired
    private CraftDao craftDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private EquipmentDao equipmentDao;




    public List<Craft> findAll(){
        List<Craft> list;
        try {
            list=craftDao.findAll();
        }catch (Exception e){
            return null;
        }
        return list;
    }

    public boolean importCraftData(){
        try {
            craftDao.deleteAll();
            BomServiceForApp bs=new BomServiceForApp();
            List<BomItem> bomItemList=bs.getAllBom();


            while (bomItemList.size()>0) {
                Craft craft = new Craft();
                String material_code = bomItemList.get(0).getMaterialCode();
                int human_num = bomItemList.get(0).getPersonnelNumber();
                String capacity = bomItemList.get(0).getStandardCapacity();
                craft.setMaterial_code(Integer.parseInt(material_code));
                craft.setHuman_num(human_num);
                craft.setCapacity(Integer.parseInt(capacity.substring(0, capacity.length() - 4)));
                while (bomItemList.size() > 0 && bomItemList.get(0).getMaterialCode().equals(material_code) && bomItemList.get(0).getTechnology().equals("打弹片")) {
                    BomItem item = bomItemList.get(0);
                    String punch_capacity = item.getStandardCapacity();
                    craft.setPunch_capacity(Integer.parseInt(punch_capacity.substring(0, punch_capacity.length() - 4)));
                    if (!item.getResource().equals("")) {
                        System.out.println(item.getTechnology() + item.getMaterialCode());
                        String resource = item.getResource().replaceAll("（", "(").replaceAll(" ", "").split("\\(")[0];
                        Team t = teamDao.findByName(resource);
                        if (t == null) {
                            Equipment e = equipmentDao.findByName(resource);
                            if (e == null) {
                                e = new Equipment();
                                e.setName(resource);
                                e.setAmount(1);
                                e.setType(item.getResourceType());
                                equipmentDao.save(e);
                                e = equipmentDao.findByName(resource);
                            }
                            craft.setPunch_equipment_res(""+e.getEquipmentid());
                        }else{
                            craft.setPunch_human_res(""+t.getTeamid());
                        }
                    }
                    bomItemList.remove(0);
                }

                String human_res = "";
                String equipment_res = "";
                List<Integer> humans = new ArrayList<>();
                List<Integer> equipments = new ArrayList<>();
                while (bomItemList.size() > 0 && bomItemList.get(0).getMaterialCode().equals(material_code) && bomItemList.get(0).getTechnology().equals("装配")) {
                    BomItem item = bomItemList.get(0);
                    if(!item.getResource().equals("")){
                        System.out.println(item.getTechnology() + item.getMaterialCode());
                        String resource = item.getResource().replaceAll("（","(").replaceAll(" ", "").split("\\(")[0];
                        Team t = teamDao.findByName(resource);
                        if (t == null) {
                            Equipment e = equipmentDao.findByName(resource);
                            if (e == null) {
                                e = new Equipment();
                                e.setName(resource);
                                e.setAmount(1);
                                e.setType(item.getResourceType());
                                equipmentDao.save(e);
                                e = equipmentDao.findByName(resource);
                            }
                            if (!equipments.contains(e.getEquipmentid())) {
                                equipment_res = equipment_res + "_" + e.getEquipmentid();
                                equipments.add(e.getEquipmentid());
                            }

                        } else {
                            if (!humans.contains(t.getTeamid())) {
                                human_res = human_res + "_" + t.getTeamid();
                                humans.add(t.getTeamid());
                            }
                        }
                    }
                    bomItemList.remove(0);
                }
                if (human_res.length() > 0) {
                    human_res = human_res.substring(1);
                }
                craft.setHuman_res(human_res);
                if (equipment_res.length() > 0) {
                    equipment_res = equipment_res.substring(1);
                }
                craft.setEquipment_res(equipment_res);
                if(bomItemList.size() > 0 && bomItemList.get(0).getMaterialCode().equals(material_code) && bomItemList.get(0).getTechnology().equals("测试")){
                    BomItem item = bomItemList.get(0);
                    craft.setTest_equipment_res(""+equipmentDao.findByName(item.getResource()).getEquipmentid());
                    String test_capacity = item.getStandardCapacity();
                    craft.setTest_capacity(Integer.parseInt(test_capacity.substring(0, test_capacity.length() - 4)));
                    bomItemList.remove(0);
                }
                craftDao.save(craft);
            }


            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
