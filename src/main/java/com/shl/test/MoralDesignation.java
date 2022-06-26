package com.shl.test;

import com.shl.util.CommonUtil;
import com.shl.util.Dbutil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoralDesignation {

    public static void main(String[] args) {

        String searchSql = "select cp.id cid, cp.campus_name,gd.id gid, gd.grade_name,cls.id clsid, cls.class_name from edu_base_class cls " +
                "LEFT JOIN edu_base_grade gd on gd.id = cls.grade_id " +
                "LEFT JOIN edu_base_campus cp on cp.id = cls.campus_id " +
                " " +
                "where cls.isdelete = 0 and cls.status = 0 and gd.id is not null;";

        Dbutil db = new Dbutil();
        List<Map> info = db.findListMapByNativeSql(searchSql, new HashMap(1));
        String domainId = "502fb588164c443dbb96f423158a1fe9";
        String sql = "INSERT INTO moral_designation_record (id, created_date, " +
                " isdelete,  optlock, domain_id, campus_id, campus_name, " +
                " class_id, class_name, designation_id, designation_name, grade_id, grade_name, parent_id, sheet_type) " +
                " VALUES (:id, :createdDate, 0, 0, :domainId, :campusId, " +
                ":campusName, :classId, :className, :dId, :dName, :gId, :gName, :pId, 0)";
        Map<String, Object> varMap = new HashMap<>(32);
        Date now = new Date();
        int i = 0;
        for (Map map : info) {
            String dId = "";
            String dName = "";
            if (i % 5 == 0 || "2c90abb8798d6c7101798d92cc260068".equals(map.get("cid"))) {
                dId = CommonUtil.UUID();
                dName = "称号_" + i;
            } else {
                i++;
                continue;
            }
            varMap.put("id", CommonUtil.UUID());
            varMap.put("createdDate", now);
            varMap.put("domainId", domainId);
            varMap.put("campusId", map.get("cid"));
            varMap.put("campusName", map.get("campus_name"));
            varMap.put("classId", map.get("clsid"));
            varMap.put("className", map.get("class_name"));
            varMap.put("dId", dId);
            varMap.put("dName", dName);
            varMap.put("pId", "1");
            varMap.put("gId", map.get("gid"));
            varMap.put("gName", map.get("grade_name"));
            db.executeUpdate(sql, varMap);

            i++;
            varMap.clear();
        }
    }

}
