package com.shl.test;

import com.shl.util.CommonUtil;
import com.shl.util.Dbutil;
import com.shl.util.JsonUtils;
import com.shl.util.Work;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws Exception {
        /*String[] ids = new String[487];
        int len = ids.length;
        final int idsLen = ids.length;
        int preIndex = 0;
        while (len > 0) {
            int cnt = Math.min(len, 100);
            int to = Math.min(preIndex + cnt, idsLen);
            String[] partIds = Arrays.copyOfRange(ids, preIndex, to);
            System.out.println("len = " + partIds.length);

            System.out.println(JsonUtils.writeValueAsString(partIds) );
            len = len - cnt;
            preIndex += cnt;
        }*/
        int i = 1697379929;
        String s = "59F62B65";
        String trans = Integer.toHexString(i);
        int index = trans.length();
        List<String> list = new LinkedList<>();
        while (index > 0) {
            list.add(trans.substring(index - 2, index));
            index -= 2;
        }
        System.out.println(String.join("", list).toUpperCase());

        Test t = new Test();
        long start = System.currentTimeMillis();
        t.initContainer(29);
        t.splice(1000);
        System.out.println("耗时" + (System.currentTimeMillis() - start) / 1000.0 + "秒");
        // t.trans();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 600);
        System.out.println(c.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        System.out.println(sdf.format(new Date()));


       /* String sql = " update test_aaa set num = (num - 1) where id=:id and (num - 1) >=:r ";
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("id", "1");
        varMap.put("r", 0);
        Dbutil db = new Dbutil();
        for ( i = 0; i < 200; i++) {
            new Thread(new ModiNumRun(db, sql, varMap), i + "_thread").start();
        }*/

        Process pro = Runtime.getRuntime().exec("tasklist | find \"java\"");
        InputStream input = pro.getInputStream();
        byte[] buff = new byte[512];
        int len = -1;
        StringBuilder out = new StringBuilder();
        while ((len = input.read(buff)) != -1) {
            out.append(new String(buff, 0, len));
        }
        System.out.println(out.toString() + " ----- ");

        c = Calendar.getInstance();
        int[] startArr = {c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)};
        System.out.println(Arrays.toString(startArr));

        sdf = new SimpleDateFormat("yyyy-MM-dd-HH-MM-ss");
        System.out.println(sdf.format(new Date()));


        String xClassPrefix = "https://test.xckj.net:9391/xcf-eqp";

        System.out.println(xClassPrefix.substring(0, xClassPrefix.lastIndexOf("/")));



        String[] a = {
                "幻灯片1.JPG",
                "幻灯片10.JPG",
                "幻灯片11.JPG",
                "幻灯片12.JPG",
                "幻灯片13.JPG",
                "幻灯片14.JPG",
                "幻灯片15.JPG",
                "幻灯片16.JPG",
                "幻灯片17.JPG",
                "幻灯片18.JPG",
                "幻灯片19.JPG",
                "幻灯片2.JPG",
                "幻灯片20.JPG",
                "幻灯片21.JPG",
                "幻灯片22.JPG",
                "幻灯片23.JPG",
                "幻灯片3.JPG",
                "幻灯片4.JPG",
                "幻灯片5.JPG",
                "幻灯片6.JPG",
                "幻灯片7.JPG",
                "幻灯片8.JPG",
                "幻灯片9.JPG"
        };

        List<String> aa = Arrays.asList(a);
        aa = aa.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int x = Integer.parseInt(o1.substring(3, o1.lastIndexOf(".JPG")));
                int y = Integer.parseInt(o2.substring(3, o2.lastIndexOf(".JPG")));
                return x - y;
            }

        }).collect(Collectors.toList());

        String colValue = "语文,";
        if (colValue.lastIndexOf(",") == (colValue.length() - 1)) {
            colValue = colValue.substring(0, colValue.length() - 1);
        }
        System.out.println(colValue);

        int result = workDayInMonth(new SimpleDateFormat("yyyy-MM").parse("2021-12"));
        System.out.println("result = " + result);
        System.out.println(new BigDecimal(10).subtract(new BigDecimal(7)).divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        start = System.currentTimeMillis();
        String s2 = "1,2,7,100,200-250,700-800";
        List<Integer> p = new LinkedList<>();
        String[] s2Arr = s2.split(",");
        for (String s1 : s2Arr) {
            if (s1.contains("-")) {
                String[] sr = s1.split("-");
                int A = Integer.parseInt(sr[0]);
                int B = Integer.parseInt(sr[1]);
                while (A <= B) {
                    p.add(A++);
                }
            } else {
                p.add(Integer.parseInt(s1));
            }
        }
        System.out.println(">>>>>>" + (System.currentTimeMillis() - start) / 1000.0);
        System.out.println(">>>>" + Arrays.toString(p.toArray()));

        Map<String, Integer> m = new HashMap<>();
        m.put("1", 1);
        m.put("2", 1);
        m.put("3", 1);
        m.put("4", 1);
        m.put("21", 1);
        Iterator<Map.Entry<String, Integer>> it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> next = it.next();
        }

        System.out.println(JsonUtils.writeValueAsString(m));

        s = "ä½_æ¸©æ£_æµ_è¡¨(è¡¨å__).zip";
        System.out.println(URLDecoder.decode(s, "ISO8859-1"));

        int[] array = {4,2,1,5,3,3,3,-2,-3,0};
        for ( i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
        for (int temp : array) {
            System.out.print(temp + " ");

        }
    }

    static int workDayInMonth(Date month) {

        Calendar c = Calendar.getInstance();
        c.setTime(month);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int i = 1, result = 0;
        while (i <= max) {
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != 1 && dayOfWeek != 7) {
                result++;
            }
            c.add(Calendar.DATE, 1);
            i++;
        }
        return result;
    }

    static class ModiNumRun implements Runnable {
        Dbutil db;
        String sql;
        Map<String, Object> varMap;
        ModiNumRun(Dbutil dbutil, String sql, Map<String, Object> varMap) {
            db = dbutil;
            this.sql = sql;
            this.varMap = varMap;
        }

        @Override
        public void run() {
            int rows = db.executeUpdate(sql, varMap);
            String name = Thread.currentThread().getName();
            if (rows > 0) {
                System.out.println(">>>>>>>>>>" + name + "  更新成功");
            } else {
                System.out.println(">>>>>>" + name + " 未更新");
            }
        }
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static int aabb(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void deal(Map<String, String> map) {
        System.out.println(JsonUtils.writeValueAsString(map));
    }

    public static int[] sortedSquares(int[] A) {
        int a, b;
        int length = A.length;
        if (length == 1) {
            return new int[]{(int) Math.pow(A[0], 2)};
        }
        a = 0;
        b = length - 1;
        int[] result = new int[length];
        if (A[a] >= 0) {
            for (int i = 0; i < length; i++) {
                result[i] = (int) Math.pow(A[i], 2);
            }
            return result;
        } else if (A[b] <= 0) {
            int j = 0;
            for (int i = length - 1; i >= 0; i--) {
                result[i] = (int) Math.pow(A[j], 2);
                j++;
            }
            return result;
        }

        for (int i = length - 1; i >= 0; i--) {
            if (Math.abs(A[a]) > Math.abs(A[b])) {
                result[i] = (int) Math.pow(A[a], 2);
                a++;
            } else {
                result[i] = (int) Math.pow(A[b], 2);
                b--;
            }
        }
        return result;
    }

    static void insertLog(int month, String userAccount, String userName) {
        String sql = "INSERT INTO sys_core_logging (id, created_by, created_date, created_org, isdelete, last_modified_by, last_modified_date, last_modified_org, optlock, domain_id, consume_time, depart_id, ip, module_code, operate_type, remark, result_type, username, module_type, operate, depart_name, module_name, real_name) VALUES (:id, '8aa376486d496c1e016d4e29bbf800f3', :createdDate, '8aa376486cb1f681016cb24d7e160011', '0', NULL, NULL, NULL, '0', '0', '194', '8aa376486cb1f681016cb24d7e160011', '172.16.43.56', 'login', '前台登陆', :userNameExp, 'SUCCESS', :account, '后台', '前台登陆', '运维组', '登陆模块', :userName)";
        Dbutil db = new Dbutil();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, (month - 1));
        c.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        Map<String, Object> varMap = new HashMap<>(8);
        int i = 1;
        while (i <= dayOfMonth) {
            varMap.put("userNameExp", "用户名: " + userName);
            varMap.put("userName", userName);
            varMap.put("account", userAccount);
            varMap.put("id", CommonUtil.UUID());
            varMap.put("createdDate", c.getTime());
            db.executeUpdate(sql, varMap);
            c.add(Calendar.DATE, 1);
            varMap.clear();
            i++;
        }
    }

    static void updateTableTime() {
        String sql = "select  start_time , end_time , last_end_time, id " +
                "from edu_timetable_template_time\n" +
                "where  domain_id = '0' ";
        Dbutil dbutil = new Dbutil();
        List<Object[]> list = dbutil.getDataListBySQL(sql, null);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar c = Calendar.getInstance();
        String[] arr;
        Map<String, Object> varMap = new HashMap<>(4);
        String updateSql = "update edu_timetable_template_time set start_time =:startT, end_time =:endT, last_end_time =:lastT " +
                "where  id =:id  ";
        for (Object[] o : list) {
            varMap.clear();
            String s = sdf.format(getArr(o[0].toString(), c, 230));
            varMap.put("startT", s);
            s = sdf.format(getArr(o[1].toString(), c, 230));
            varMap.put("endT", s);
            s = sdf.format(getArr(o[2].toString(), c, 230));
            varMap.put("lastT", s);
            varMap.put("id", o[3].toString());
            dbutil.executeUpdate(updateSql, varMap);
        }

    }

    private static Date getArr(String s, Calendar c, int offset) {
        clear(c, 0, 0, 0);
        String[] arr = s.split(":");
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0]));
        c.set(Calendar.MINUTE, Integer.parseInt(arr[1]));
        c.add(Calendar.MINUTE, offset);
        return c.getTime();
    }

    private static void clear(Calendar c, int i, int i2, int i3) {
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, i2);
        c.set(Calendar.SECOND, i3);
        c.set(Calendar.MILLISECOND, 0);
    }

    private static void aaa(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n);
    }

//    private static StringBuilder putConnection(Map<String, Object> varMap, String path) throws IOException {
//        if (varMap != null) {
//            StringBuilder params = new StringBuilder();
//            for (Map.Entry<String, Object> entry : varMap.entrySet()) {
//                params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//            }
//            if (params.length() > 0) {
//                params.deleteCharAt(params.length() - 1);
//            }
//            path += ("?" + params.toString());
//        }
//        URL url = new URL(path);
//
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "*/*");
//        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
//        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        conn.setRequestProperty("connection", "keep-alive");
//        conn.setRequestProperty("Charsert", "UTF-8");
//        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
//
//        // 不允许使用缓存
//        conn.setUseCaches(false);
//        conn.connect();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        String line = "";
//        StringBuilder result = new StringBuilder();
//        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
//            result.append(line);
//        }
//        System.out.println(result);
//        return result;
//    }

    static class MyWork implements Work {
        @Override
        public int deal(Map data) {
            System.out.println(">>>>>>>>>>>处理成功");
            return 0;
        }
    }

    class MyWork2 implements Work {

        @Override
        public int deal(Map data) {
            System.out.println(">>>>>>>处理失败");
            return -1;
        }
    }

    static StringBuilder getConnection(String a) throws IOException {
        URL url = new URL(a);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36");
        conn.setRequestProperty("Cookie",
                "azSsQE5NvspcS=5c0AQPutsKpydoKG_eYepbGMFtQV7k66GX6N.KPm7ADlZrMPkShCweCtT0l6ZO8OPKqwLSOAz0PKH0jRo4Hz.DG; azSsQE5NvspcT=53mcGWbrALl0qqqmgiBom7a3d0.faNu1yq2HZFRIrZQN.lBKzBa41lkO5SzXwiCqx4sPZEXBQeVOoye77_0y_CMMpxMRshgc_RbrZoYdbb0DpH9HtSn2nrnGFf4uK5JV53rLGMenx8Rqyv2_RZx.8j5xuYxhL_raMJCyNkE2F3Chvcq4LmJgkp9IPXYfrSy28gQ9CA8maXbWrIFgKTXsF3zJ55YTZhgOHRL2KtlJ41rjXVYM9pqmxt9LNchv7a8kPgfTRMyNGd03k5cuxoEkm6U");
        System.out.println(a);
        conn.connect();
        Map<String, List<String>> map = conn.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line = "";
        StringBuilder result = new StringBuilder();
        while (StringUtils.isNotBlank((line = bufferedReader.readLine()))) {
            result.append(line);
        }
        System.out.println(result);
        return result;
    }

    static class Node {
        List list = new LinkedList();
        Node next;
        Node pre;
    }
    private Node head;
    private Node last;
    private int k;
    void initContainer(int k) {
        this.k = k;
        Node p = null;
        for (int i = 0; i < k; i++) {
            Node n = new Node();
            if (i == 0) {
                head = n;
            }
            if (p != null) {
                p.next = n;
            }

            n.pre = p;
            p = n;

            if ((i + 1) == k) {
                last = n;
            }
        }


    }

    void splice(int n) {
        Node p = head;
        boolean d = true;
        for (int i = 0; i < n; i++) {

            p.list.add(i);
            if (d) {
                p = p.next;
                if (p == null) {
                    p = last;
                    d = false;
                    continue;
                }
            }

            if (!d) {
                p = p.pre;
                if (p == null) {
                    p = head;
                    d = true;
                }
            }


        }
    }



    void trans() {
        Node next = head;
        while (next != null) {
            System.out.println(JsonUtils.writeValueAsString(next.list));
            next = next.next;
        }
    }




}
