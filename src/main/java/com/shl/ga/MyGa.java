package com.shl.ga;


import org.apache.commons.collections4.map.LinkedMap;

import java.util.*;

/**
 * 遗传算法排课
 * @author 花菜
 * @date 2021/5/22 0:31
 */

public class MyGa {

    //种群的规模（0-100）
    private Integer popsize = 32;
    //种群的变异概率
    private Double mutprob = 0.3;
    //精英种群的个数
    private Integer elite = 15;
    //进化代数（100-500）
    private Integer maxiter = 500;
    //所有的种群 每一个种群中存放需要编排的课程列表
    private List<List<Schedule>> population;

    public MyGa() {
    }

    /**
     * ga主体
     * 参数：教学任务信息列表 、所有可以使用的教师的id列表 、需要排到第几节课
     * @param schedules
     * @param roomRange
     * @return
     */
    public List<Schedule> evolution(List<Schedule> schedules, List<String> roomRange){
        //冲突最小的染色体的冲突得分，若为0则说明已获得最优的解，可返回
        int bestScore = 0;
        //最优的课程编排结果
        List<Schedule> bestSchedule = new ArrayList<>();
        init_population(schedules, roomRange);
        for(int i = 0 ; i < this.maxiter ; i++){
            List<List<Schedule>> newPopulation = new ArrayList<>();
            //获取冲突结果
            LinkedMap<List<Schedule> , Integer> cost_map = schedule_cost(this.population, this.elite);
            for(List<Schedule> key : cost_map.keySet()){
                //若发现冲突结果为0 则说明可将其当做最优排课结果返回
                bestScore = cost_map.get(key);
                if(bestScore == 0) {
                    bestSchedule = key;
                    return bestSchedule;
                }
            }
            //精英种群集合
            for(List<Schedule> key : cost_map.keySet()){
                newPopulation.add(key);
            }
            while (newPopulation.size() < this.popsize){
                List<Schedule> tmp = new ArrayList<>();
                if(Math.random() < this.mutprob){
                    //落在变异概率内 变异
                    tmp = mutate(newPopulation, roomRange);
                }else{
                    //交叉
                    tmp = crossover(newPopulation);
                }
                newPopulation.add(tmp);
            }
            this.population = newPopulation;
        }
        return bestSchedule;
    }

    /**
     * 变异 根据精英种群集合 在将其中随机一个染色体变异后 返回变异后的染色体
     * @param eiltePopulation
     * @param roomRange
     * @return
     */
    List<Schedule> mutate(List<List<Schedule>> eiltePopulation , List<String> roomRange ){
        Random random = new Random();
        //选择变异哪一个精英种群中的染色体
        int getIndex = random.nextInt(eiltePopulation.size());
        List<Schedule> ep = eiltePopulation.get(getIndex);
        for(Schedule s : ep){
            int pos = random.nextInt(3);
            if(pos == 0){
                s.setClassroomid( roomRange.get(random.nextInt(roomRange.size())) );
            }else if(pos == 1){
                s.setWeekday((int)( 1 + Math.random() * (5 - 1 + 1)));
            }else if(pos == 2){
                s.setSlot((int)( 1 + Math.random() * (4 - 1 + 1)));
            }
        }
        return ep;
    }

    /**
     * 交叉 根据精英种群集合 在将其中两个染色体交叉后 返回一个新的染色体
     * @param eiltePopulation
     * @return
     */
    List<Schedule> crossover(List<List<Schedule>> eiltePopulation){
        Random random = new Random();
        //选择变异哪两个精英种群
        int getIndex1 = random.nextInt(eiltePopulation.size());
        int getIndex2 = random.nextInt(eiltePopulation.size());

        List<Schedule> e1 = eiltePopulation.get(getIndex1);
        List<Schedule> e2 = eiltePopulation.get(getIndex2);
        int pos = random.nextInt(3);
        for(int i = 0 ; i < e1.size() ; i++ ){
            if(pos == 0){
                e1.get(i).setClassroomid( e2.get(i).getClassroomid());
            }else if(pos == 1){
                e1.get(i).setWeekday( e2.get(i).getWeekday());
            }else if(pos == 2){
                e1.get(i).setSlot( e2.get(i).getSlot());
            }
        }
        return e1;
    }


    /**
     * 随机初始化不同的种群
     * @param schedules
     * @param roomRange
     */
    void init_population( List<Schedule> schedules , List<String> roomRange ){
        this.population = new ArrayList<>();
        for(int i = 0 ; i < this.popsize ; i++){
            List<Schedule> entity = new ArrayList<>();
            for(int j = 0; j < schedules.size() ; j++ ){
                Schedule tmp = schedules.get(j);
                tmp.random_init(roomRange);
               /* entity.add(new Schedule(
                        tmp.getTeachtaskid(),
                        tmp.getClassroomid(),
                        tmp.getWeekday(),
                        tmp.getSlot()
                ));*/
            }
            this.population.add(entity);
        }
    }


    /**
     * 计算课表种群的冲突。
     * 返回：精英种群--精英种群中排名第一的染色体若冲突值为0则说明是可以作为最优解返回
     * 当被测试课表冲突为0的时候，这个课表就是个符合规定的课表。
     * 冲突检测遵循下面几条规则：
     * 同一个教室在同一个时间只能有一门课。
     * 同一个班级在同一个时间只能有一门课。
     * 同一个教师在同一个时间只能有一门课。
     * 但是对于目前系统中已经将班级、教师、课程拼成了一条教学任务
     * 故只需要满足 同一个教室在同一个时间 只能有一各教学任务
     * @param population
     * @param elite
     * @return
     */
    LinkedMap<List<Schedule> , Integer> schedule_cost(List<List<Schedule>> population , Integer elite){
        LinkedMap<List<Schedule> , Integer> utilMap = new LinkedMap();
        LinkedMap<List<Schedule> , Integer> resMap = new LinkedMap();
        List<Integer> conflicts = new ArrayList<>();
        //一个染色体有多长==》有多少课程需要安排
        int n = population.get(0).size();

        for(List<Schedule> p : population){
            //对种群遍历，求种群中染色体的适应度
            int conflict = 0;
            for(int i = 0 ;i < n-1 ;i++){
                for(int j = i+1 ;j < n ; j++){
                    //check course in same time and same room
                    //检查冲突 需保证 在同一天 同一节课 下的 同一个教室中没有两个课程
                    if(p.get(i).getClassroomid().equals(p.get(j).getClassroomid())  &&
                            p.get(i).getWeekday()  == p.get(j).getWeekday() &&
                            p.get(i).getSlot()  == p.get(j).getSlot() ){
                        conflict += 1;
                    }
                }
            }
            utilMap.put( p , conflict);
        }
        //根据冲突值排序
        List<Map.Entry<List<Schedule> , Integer>> entryList = new ArrayList<Map.Entry<List<Schedule> , Integer>>(utilMap.entrySet());
        Collections.sort(entryList,new Comparator<Map.Entry< List<Schedule> , Integer > >() {
            @Override
            public int compare(Map.Entry<List<Schedule> , Integer> me1, Map.Entry<List<Schedule> , Integer> me2){
                //按照从小到大的顺序排列
                return me1.getValue().compareTo(me2.getValue());
            }
        });
        Iterator<Map.Entry<List<Schedule> , Integer>> iter = entryList.iterator();
        Map.Entry<List<Schedule> , Integer> tmpEntry = null;
        int flag = 0;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            resMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            flag++;
            if(flag == elite)
                break;
        }
        //说明一下：此处的resMap的大小会变化 因为排序后可能会出现相同的种群情况 因为把value值更新了
        return resMap;
    }


}