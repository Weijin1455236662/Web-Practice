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
            BomServiceForApp bs=new BomServiceForApp();
            List<BomItem> bomItemList=bs.getAllBom();
            while (bomItemList.size()>0) {
                Craft craft = new Craft();
                String material_code = bomItemList.get(0).getMaterialCode();
                String technology = bomItemList.get(0).getTechnology();
                int human_num = bomItemList.get(0).getPersonnelNumber();
                String capacity = bomItemList.get(0).getStandardCapacity();
                String human_res = "";
                String equipment_res = "";
                craft.setMaterial_code(Integer.parseInt(material_code));
                craft.setHuman_num(human_num);
                craft.setCapacity(Integer.parseInt(capacity.substring(0, capacity.length() - 4)));
                List<Integer> humans = new ArrayList<>();
                List<Integer> equipments = new ArrayList<>();
                while (bomItemList.size() > 0 && bomItemList.get(0).getMaterialCode().equals(material_code) && bomItemList.get(0).getTechnology().equals(technology)) {
                    BomItem item = bomItemList.get(0);
                    if(!item.getResource().equals("")){
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
                    human_res = human_res.substring(1, human_res.length());
                }
                craft.setHuman_res(human_res);
                if (equipment_res.length() > 0) {
                    equipment_res = equipment_res.substring(1, equipment_res.length());
                }
                craft.setEquipment_res(equipment_res);
                craftDao.save(craft);

            }


            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
