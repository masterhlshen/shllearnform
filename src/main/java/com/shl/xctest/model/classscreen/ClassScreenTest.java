package com.shl.xctest.model.classscreen;

import com.shl.util.Dbutil;
import com.shl.util.HandleResult;
import com.shl.util.JsonUtils;
import com.shl.xctest.net.NetUtil;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ClassScreenTest {
    public static void main(String[] args) throws IOException {
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D:/upload/a.zip"));
        zipDic(new File("D:\\DingDing\\dingdingdownlaod"), zout);
        zout.close();
    }

    static void zipDic(File file, ZipOutputStream zout) throws IOException {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            ZipEntry entry = new ZipEntry(file.getAbsolutePath());
            zout.putNextEntry(entry);
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    zipDic(f, zout);
                }
            }
            return;
        }

        FileInputStream input = new FileInputStream(file);
        byte[] buff = new byte[1024];
        int len = 0;
        ZipEntry entry = new ZipEntry(file.getAbsolutePath());
        zout.putNextEntry(entry);
        while ((len = input.read(buff)) != -1) {
            zout.write(buff, 0, len);
        }
        input.close();

    }

    static void render(LabelDTO dto, Map data) {
        if (dto == null) {
            return;
        }
        LabelDTO next = null;
        if (dto.dep == 1) {
            if (dto.value == null) {
                dto.value = data.get("campus_id") + "";
                dto.label = data.get("campus_name") + "";
                dto.children = new LinkedList<>();
                next = new LabelDTO(2);
                dto.children.add(next);
            } else {
                boolean b = false;
                for (LabelDTO child : dto.children) {
                    if ((data.get("grade_id") + "").equals(child.value)) {
                        b = true;
                        next = child;
                        break;
                    }
                }
                if (!b) {
                    next = new LabelDTO(2);
                    dto.children.add(next);
                }
            }

            render(next, data);
        } else if (dto.dep == 2) {
            if (dto.value == null) {
                dto.value = data.get("grade_id") + "";
                dto.label = data.get("grade_name") + "";
                dto.children = new LinkedList<>();
                next = new LabelDTO(3);
                dto.children.add(next);
            } else {
                boolean b = false;
                for (LabelDTO child : dto.children) {
                    if ((data.get("class_id") + "").equals(child.value)) {
                        b = true;
                        next = child;
                        break;
                    }
                }
                if (!b) {
                    next = new LabelDTO(2);
                    dto.children.add(next);
                }
            }
            render(next, data);
        } else if (dto.dep == 3) {
            dto.label = data.get("class_name") + "";
            dto.value = data.get("id") + "";
        }
    }

    static class LabelDTO {
        public LabelDTO(int dep) {
            this.dep = dep;

        }

        int dep;
        String value;
        String label;
        List<LabelDTO> children;
    }

    private static void cardTrans() throws IOException {
        Map<String, Object> varMap = new HashMap<>(8);
        varMap.put("clientId", "8aa576ab914743b19147da78c0716f1a");
        varMap.put("clientSecret", "b6fcea8c92024819adbc4409f72d4edd");
        HandleResult handleResult = NetUtil.postJson(varMap, "http://localhost:8080/xcoffice/api/clientToken/login/client", HandleResult.class);
        Map data = (Map) handleResult.getData();
        String xckToken = Objects.toString(data.get("access_token"), "");
        System.out.println(xckToken);
        String sql = "select external_userid from edu_base_student where  domain_id='0' ";
        Dbutil db = new Dbutil();
        List<Map> stuList = db.findListMapByNativeSql(sql, new HashMap(8));
        int cardNum = 11111111;
        for (Map ele : stuList) {
            new Thread(getTarget(xckToken, ele.get("external_userid") + "")).start();
            cardNum++;
        }
    }

    private static Runnable getTarget(String xckToken, String cardNum) {
        return new Runnable() {
            @Override
            public void run() {

                Map<String, Object> paraMap = new HashMap<>();
                // author2.0 token
                paraMap.put("xckj_token", xckToken);
                // mac地址
                paraMap.put("mac", "c0:84:7d:58:f4:da");
                // 0：课程/虚拟课程（指定打卡）考勤
                paraMap.put("where", 0);
                // 卡号
                paraMap.put("cardNum", cardNum);
                // 是否需要转换卡号
                paraMap.put("noTrans", "1");
                // isNew 空代表课表1.0，不为空代表课表2.0
                paraMap.put("isNew", "1");
                HandleResult res = null;
                try {
                    // 打卡接口 content-type application/x-www-form-urlencoded
                    res = NetUtil.post(paraMap, "http://localhost:8080/xcoffice/api/classcreen/dcattmanage/sign", HandleResult.class);
                    System.out.println(JsonUtils.writeValueAsString(res));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        };
    }

    static void initCard() {
        // 初始化卡号
        String sql = "select id from edu_base_student ";
        Dbutil db = new Dbutil();
        List<Map> data = db.findListMapByNativeSql(sql, new HashMap(8));
        String insertSql = "update edu_base_student set external_userid=:cardNum where id=:id ";
        Map<String, Object> varMap = new HashMap<>(8);
        int cardNum = 11111111;
        for (Map ele : data) {
            String id = Objects.toString(ele.get("id"), "");
            String trans = Integer.toHexString(cardNum);
            int index = trans.length();
            List<String> list = new LinkedList<>();
            // 卡号转换规则
            while (index > 0) {
                list.add(trans.substring(index - 2, index));
                index -= 2;
            }
            String realCardNum = String.join("", list);
            varMap.put("id", id);
            varMap.put("cardNum", realCardNum.toUpperCase());
            db.executeUpdate(insertSql, varMap);
            cardNum++;
        }

    }
}
