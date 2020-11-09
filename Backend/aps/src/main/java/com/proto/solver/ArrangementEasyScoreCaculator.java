package com.proto.solver;

import com.proto.pojo.Arrangement;
import com.proto.pojo.SubOrder;
import com.proto.pojo.Team;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.List;
public class ArrangementEasyScoreCaculator implements EasyScoreCalculator<Arrangement, HardSoftScore> {

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
        int current_time;
        int current_day;
        int sum;
        String[] currentTeamList=null;
        String[] currentEquipmentList=null;

        for(SubOrder a : subOrderList){
            if(a.getTeamList()!=null&&a.getTimeslot()!=null&&a.getEquipment()!=null){
                teamLista = a.getTeamList().getTeamList();

                result = false;
                current_time = a.getTimeslot().getTime();
                current_day = a.getTimeslot().getDate().getDayOfWeek().getValue();
                for(int i=0;i<teamLista.size();i++){
                    begin_time = teamLista.get(i).getBegin_time();
                    end_time = teamLista.get(i).getEnd_time();
                    begin_day = teamLista.get(i).getBegin_day();
                    end_day = teamLista.get(i).getEnd_day();
                    if(end_time-begin_time>0){
                        if(current_time<begin_time||current_time>end_time){
                            result = true;
                            break;
                        }
                    }else{
                        if(current_time>end_time&&current_time<begin_time){
                            result = true;
                            break;
                        }
                    }

                    if(current_day<begin_day||current_day>end_day){
                        result = true;
                        break;
                    }
                }
                if(result){
                    hardScore--;
                }

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

                result = false;
                currentTeamList = a.getHuman_res().split("_");
                currentEquipmentList = a.getEquipment_res().split("_");
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


                for(SubOrder b : subOrderList){
                    if(b.getTeamList()!=null&&b.getTimeslot()!=null&&b.getEquipment()!=null){
                        teamListb = b.getTeamList().getTeamList();

                        if((a.getTimeslot().getTime()==b.getTimeslot().getTime())&&a.getId()<b.getId()){
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
                }
            }
        }

        return HardSoftScore.of(hardScore,softScore);
    }
}
