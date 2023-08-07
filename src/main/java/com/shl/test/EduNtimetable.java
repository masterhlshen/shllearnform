package com.shl.test;

import com.shl.util.CommonUtil;
import com.shl.util.Dbutil;

import java.util.HashMap;
import java.util.Map;

public class EduNtimetable {

    public static void main(String[] args) {
        String sql = "INSERT INTO edu_n_timetable_cd (id, created_by, created_date, created_org, isdelete, last_modified_by, last_modified_date, last_modified_org, optlock, domain_id, apm, class_name, course_id, course_name, day_of_week, end_time, grade_id, parent_id, room_name, row_index, start_time, teacher_ids, teacher_names, template_id, week_in_term, term_id, class_id, class_sort, belongdate, room_id, change_status, campus_id, year, plan_id, plan_status, ct, t_class_id, dsz) VALUES (:id, NULL, NULL, NULL, '0', NULL, NULL, NULL, '0', '0', 'AM', '初三30班', NULL, '白云初中体育', '2', '520', '8aa376486cb1f681016cb62bb65e0072', NULL, NULL, '1', '480', NULL, NULL, '2c90abc486067808018606c11e85012b', '5', '2c90abb8732e3edd0173329c3da00261', '2c90abb875877aef017587b5e89d0033', '30', '2023-03-07 00:00:00', NULL, NULL, NULL, NULL, '2c90abc486a52ac40186a549a2b200a4', '1', '0', NULL, NULL)";

        Dbutil db = new Dbutil();
        Map<String, Object> varMap = new HashMap<>(8);
        int success = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            varMap.put("id", CommonUtil.UUID());
            success += db.executeUpdate(sql, varMap);
        }
        System.out.println("rows = " + success);
        System.out.println("耗时" + (System.currentTimeMillis() - start) / 1000.0 + "秒");
    }
}
