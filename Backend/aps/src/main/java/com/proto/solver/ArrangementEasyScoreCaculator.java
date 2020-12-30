package com.proto.solver;

import com.proto.pojo.Arrangement;
import com.proto.pojo.SubOrder;
import com.proto.pojo.Team;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.List;
public class ArrangementEasyScoreCaculator implements EasyScoreCalculator<Arrangement, HardSoftScore> {

    /*
        前提条件：
            单个订单不能拖到第二天
            没有倒班
            员工工作时间相同
     */

    @Override
    public HardSoftScore calculateScore(Arrangement arrangement){
        List<SubOrder> subOrderList = arrangement.getSubOrderList();

        int hardScore = 0;
        int softScore = 0;
        boolean result = false;
        boolean found = false;
        List<Team> teamLista;
        List<Team> teamListb;

        int begin_time;
        int end_time;
        int begin_day;
        int end_day;
        int current_begin_timea;
        int current_end_timea;
        int current_begin_timeb;
        int current_end_timeb;
        int current_day;
        int sum;
        String[] currentTeamList=null;
        String[] currentEquipmentList=null;

        for(SubOrder a : subOrderList){
            if(a.getTeamList()!=null&&a.getTimeslot()!=null&&a.getEquipment()!=null){
                teamLista = a.getTeamList().getTeamList();
                current_begin_timea = a.getTimeslot().getTime();
                current_end_timea = current_begin_timea + a.getLast_time();
                if(current_end_timea>=86400){
                    hardScore--;
                }

                if(a.getType()==0||a.getType()==1) {
                    current_day = a.getTimeslot().getDate().getDayOfWeek().getValue();
                    for(int i=0;i<teamLista.size();i++){
                        begin_time = teamLista.get(i).getBegin_time();
                        end_time = teamLista.get(i).getEnd_time();
                        begin_day = teamLista.get(i).getBegin_day();
                        end_day = teamLista.get(i).getEnd_day();
                        if(current_begin_timea<begin_time||current_end_timea>end_time){
                            hardScore--;
                        }

                        if(current_day<begin_day||current_day>end_day){
                            hardScore--;
                        }
                    }
                }

                if(a.getType()==1){
                    result = false;
                    sum = 0;
                    for (int i=0;i<teamLista.size();i++){
                        sum += teamLista.get(i).getNum();
                    }
                    if(sum<a.getCapacity()){
                        result = true;
                    }
                    if(result){
                        hardScore--;
                    }
                }

                result = false;
                currentTeamList = a.getHuman_res().split("_");
                currentEquipmentList = a.getEquipment_res().split("_");
                if(a.getType()==0||a.getType()==1) {
                    for(int i=0;i<teamLista.size();i++){
                        found = false;
                        for(int j=0;j<currentTeamList.length;j++){
                            if(Integer.parseInt(currentTeamList[j])==teamLista.get(i).getTeamid()){
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            result = true;
                            break;
                        }
                    }
                    if(result){
                        hardScore--;
                    }else{
                        found = false;
                        for(int i=0;i<currentEquipmentList.length;i++){
                            if(Integer.parseInt(currentEquipmentList[i])==a.getEquipment().getEquipmentid()){
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            result = true;
                        }
                        if(result){
                            hardScore--;
                        }
                    }
                }else{
                    found = false;
                    for(int i=0;i<currentEquipmentList.length;i++){
                        if(Integer.parseInt(currentEquipmentList[i])==a.getEquipment().getEquipmentid()){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        result = true;
                    }
                    if(result){
                        hardScore--;
                    }
                }


                for(SubOrder b : subOrderList){
                    if(b.getTeamList()!=null&&b.getTimeslot()!=null&&b.getEquipment()!=null&&a.getId()<b.getId()){

                        teamListb = b.getTeamList().getTeamList();
                        current_begin_timeb = b.getTimeslot().getTime();
                        current_end_timeb = current_begin_timeb + b.getLast_time();
                        if(current_end_timeb>=86400){
                            hardScore--;
                        }

                        if(a.getTimeslot().getDate().isEqual(b.getTimeslot().getDate())) {
                            if((!(current_begin_timea<current_begin_timeb&&current_end_timea<current_begin_timeb))&&(!(current_begin_timea>current_end_timeb))) {
                                result = false;
                                for(int i=0;i<teamLista.size();i++){
                                    for(int j=0;j<teamListb.size();j++){
                                        if(teamLista.get(i).getTeamid()==teamListb.get(j).getTeamid()){
                                            result = true;
                                            break;
                                        }
                                    }
                                }
                                if (result) {
                                    hardScore--;
                                }

                                if(a.getEquipment().getEquipmentid()==b.getEquipment().getEquipmentid()){
                                    hardScore--;
                                }
                            }
                        }
                        //时间冲突的两个订单应该不能有相同的组或相同的设备



                        if(a.getParent_id()==b.getParent_id()&&a.getProduct_id()==b.getProduct_id()){
                            if(a.getType()==0&&b.getType()==1||a.getType()==1&&b.getType()==2){
                                if(a.getTimeslot().getDate().isAfter(b.getTimeslot().getDate())){
                                    hardScore--;
                                }else if(a.getTimeslot().getDate().isEqual(b.getTimeslot().getDate())){
                                    if(current_begin_timeb<current_end_timea){
                                        hardScore--;
                                    }
                                }
                            }
                        }

                        if(a.getTeamList().equals(b.getTeamList())&&a.getEquipment().getEquipmentid()!=b.getEquipment().getEquipmentid()){
                            if(current_begin_timea<current_begin_timeb) {
                                if (current_begin_timeb - current_end_timea < 360) {
                                    hardScore--;
                                }
                            }else if(current_begin_timea>current_begin_timeb){
                                if(current_begin_timea - current_end_timeb < 360){
                                    hardScore--;
                                }
                            }
                        }

                    }
                }
            }
        }

        return HardSoftScore.of(hardScore,softScore);
    }
}
