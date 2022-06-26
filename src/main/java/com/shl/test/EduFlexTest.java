package com.shl.test;

import com.shl.util.CommonUtil;
import com.shl.util.Dbutil;
import com.shl.util.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class EduFlexTest {
    public static void main(String[] args) {
        EduFlexTest eduFlexTest = new EduFlexTest();
        eduFlexTest.insertIntoFlexRecord("custom_auto_20210816181544889", "0");
    }

    // TODO 向弹性离校数据表插入数据
    void insertIntoFlexRecord(String tableName, String domainId) {
        String sql = "insert into custom_auto_20210816181544889(id,apply_time,student_code_input," +
                " whether_to_eat,study_phase_id,created_date,optlock,domain_id,module_version,form_state,isdelete) values (:id,:apply_time,:student_code_input," +
                ":whether_to_eat,:study_phase_id,:created_date,:optlock,:domain_id,:module_version,:form_state,:isdelete)";
        List<String> stuCodeByDomainId = findStuCodeByDomainId(domainId);
        Dbutil db = new Dbutil();
        Map<String, Object> varMap = new HashMap<>(16);
        Date date = new Date();
        String now = new SimpleDateFormat("yyyy-MM-dd").format(date);
        int i = 0;
        int success = 0;
        for (String stuCode : stuCodeByDomainId) {
            varMap.put("id", CommonUtil.UUID());
            varMap.put("apply_time", now);
            varMap.put("student_code_input", stuCode);
            varMap.put("whether_to_eat", JsonUtils.writeValueAsString(eatMap.get((i & 1))));
            varMap.put("study_phase_id", "");
            varMap.put("created_date", date);
            varMap.put("optlock", 0);
            varMap.put("domain_id", domainId);
            varMap.put("module_version", 0);
            varMap.put("form_state", 100);
            varMap.put("isdelete", 0);
            success += db.executeUpdate(sql, varMap);
            varMap.clear();
            i++;
        }
        System.out.println(">>>>>>共" + stuCodeByDomainId.size() + "条，成功" + success + "条");

    }

    // TODO 向弹性离校请假表中插入数据
    void insertInfoFlexSubRecord(String tableName, String domainId) {
        String sql = "insert into " + tableName + "(id, apply_user,holiday_time,optlock,domain_id,isdelete) " +
                "values(:id, :apply_user,:holiday_time,0,:domain_id,0) ";
    }

    List<String> findStuCodeByDomainId(String domainId) {
        List<String> result = new LinkedList<>();
        String sql = "select student_code from edu_base_student where domain_id = :domainId and isdelete = 0 and status = 0 ";
        Dbutil db = new Dbutil();
        Map<String, Object> varMap = new HashMap<>(4);
        varMap.put("domainId", domainId);
        List<Map> data = db.findListMapByNativeSql(sql, varMap);
        for (Map ele : data) {
            result.add(ele.get("student_code") + "");
        }
        return result;
    }

    Map<Integer, Map<String, String>> eatMap = new HashMap<>();

    {
        Map<String, String> e = new HashMap<>(4);
        e.put("linkname", "是");
        e.put("id", "是");
        eatMap.put(0, e);
        e = new HashMap<>(4);
        e.put("linkname", "否");
        e.put("id", "否");
        eatMap.put(1, e);
    }
}
