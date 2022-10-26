package com.shl.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExcelImportUtil {


    /**
     * 解析excel数据到list中
     *
     * @param filepath 文件路径
     * @param header   表头
     * @return
     */
    public static List<Object[]> excelToList(String filepath, String[][] header) {
        List<Object[]> list = new ArrayList<Object[]>();
        //判断文件是否存在
        File file = new File(filepath);
        if (!file.exists()) {
            System.out.println("===========文件不存在=============");
        }
        FileInputStream input;
        try {
            input = new FileInputStream(filepath);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
                XSSFSheet sheet = wb.getSheetAt(numSheet);
                int count = sheet.getLastRowNum();
                for (int i = 1; i <= count; i++) {
                    XSSFRow row = sheet.getRow(i);
                    Object[] obj = new Object[header.length];
                    for (int j = 0; j < header.length; j++) {
                        if ("string".equals(header[j][1] + "")) {
                            obj[j] = getCellStringValue(row, j);
                        } else if ("number".equals(header[j][1] + "")) {
                            obj[j] = getCellNumberValue(row, j);
                        } else if ("date".equals(header[j][1] + "")) {
                            obj[j] = getCellDateValue(row, j, header[j][3] + "");
                        }
                    }
                    list.add(obj);
                    // 学生信息
					/*
					String school = getCellStringValue(row, 0); // 学校
					String trueName = getCellStringValue(row, 1); // 姓名
					String sex = getCellStringValue(row, 2); // 性别
					String usercard = getCellStringValue(row, 3); // 身份证
					String type = getCellStringValue(row, 8);// 用户类型
					String tel = getCellStringValue(row, 9);// 联系方式
					*/
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析excel数据到list中
     *
     * @param filepath 文件路径
     * @param header   表头
     * @return
     */
    public static Map excelToListMap(String filepath, String[][] header) {
        List<Map> list = new LinkedList<>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //判断文件是否存在
        File file = new File(filepath);
        if (!file.exists()) {
            System.out.println("<==该文件路径下未发现文件=======123========>");
            resultMap.put("result", "该文件路径下未发现文件");
            return resultMap;
        }
        FileInputStream input;
        try {
            input = new FileInputStream(filepath);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            //判断列表是否正确
            XSSFRow headrow = sheet.getRow(0);
            for (int k = 0; k < header.length; k++) {
                String headValue = getCellStringValue(headrow, k);
                if (headValue != null && ("".equals(headValue) || !headValue.equals(header[k][0]))) {
                    resultMap.put("result", "第" + (k + 1) + "列为" + header[k][0]);
                    return resultMap;
                }
            }
            int count = sheet.getLastRowNum();
            for (int i = 1; i <= count; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    break;
                }
                Map<String, Object> map = new HashMap<String, Object>();
                for (int j = 0; j < header.length; j++) {
                    Object obj = null;
                    if ("string".equals(header[j][1] + "")) {
                        obj = getCellStringValue(row, j);
                    } else if ("number".equals(header[j][1] + "")) {
                        obj = getCellNumberValue(row, j);
                    } else if ("date".equals(header[j][1] + "")) {
                        obj = getCellDateValue(row, j, header[j][3] + "");
                    } else if ("stringDate".equals(header[j][1] + "")) {
                        obj = getCellStringValue(row, j);
                    }
                    map.put(header[j][2], obj == null ? "" : obj);
                }
                list.add(map);
            }
            if (list == null || list.size() == 0) {
                resultMap.put("result", "上传文件中无数据！");
            } else {
                resultMap.put("list", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "上传文件存在问题");
        }
        return resultMap;
    }

    /**
     * 解析表头不固定excel
     *
     * @param filePath
     * @param header
     * @param headerAdd
     * @param headTitle
     * @return
     */
    public static Map excelToListMap(String filePath, String[][] header, String[][] headerAdd, List<String> headTitle) {
        List<Map> list = new ArrayList<Map>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //判断文件是否存在
        File file = new File(filePath);
        if (!file.exists()) {
            resultMap.put("result", "该文件路径下未发现文件");
            return resultMap;
        }
        FileInputStream input;
        try {
            input = new FileInputStream(filePath);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            //判断列表是否正确
            XSSFRow headrow = sheet.getRow(0);
            for (int k = 0; k < header.length; k++) {
                String headValue = getCellStringValue(headrow, k);
                if (headValue != null && ("".equals(headValue) || !headValue.equals(header[k][0]))) {
                    resultMap.put("result", "第" + (k + 1) + "列为" + header[k][0]);
                    return resultMap;
                }
            }
            int lastRowNum = sheet.getLastRowNum();
            int columnCnt = headrow.getPhysicalNumberOfCells();
            for (int i = 1; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    break;
                }
                Map<String, Object> map = new HashMap<String, Object>();
                for (int j = 0; j < header.length; j++) {
                    Object obj = null;
                    if ("string".equals(header[j][1] + "")) {
                        obj = getCellStringValue(row, j);
                    } else if ("number".equals(header[j][1] + "")) {
                        obj = getCellNumberValue(row, j);
                    } else if ("date".equals(header[j][1] + "")) {
                        obj = getCellDateValue(row, j, header[j][3] + "");
                    } else if ("stringDate".equals(header[j][1] + "")) {
                        obj = getCellStringValue(row, j);
                    }
                    map.put(header[j][2], obj == null ? "" : obj);
                }
                int headIndex = 0;
                for (int k = header.length; k < columnCnt; k++) {
                    //循环表头  20210407
                    if (headIndex >= headerAdd.length) {
                        headIndex = 0;
                    }
                    String[] header_ = headerAdd[headIndex];
                    headIndex = headIndex + 1;
                    Object obj = null;
                    //{"教学班","string","teaching_class"}
                    if ("string".equals(header_[1] + "")) {
                        obj = getCellStringValue(row, k);
                    } else if ("number".equals(header_[1] + "")) {
                        obj = getCellNumberValue(row, k);
                    } else if ("date".equals(header_[1] + "")) {
                        obj = getCellDateValue(row, k, header_[3] + "");
                    } else if ("stringDate".equals(header_[1] + "")) {
                        obj = getCellStringValue(row, k);
                    }
                    double c = (k - header.length) / headerAdd.length;
                    int subscrip_index = (int) Math.ceil(c);
                    map.put(header_[2] + "-" + subscrip_index, obj == null ? "" : obj);
                    if (i == 1) { //只添加第一行数据对应的列
                        headTitle.add(header_[2] + "-" + subscrip_index);
                    }
                }
                list.add(map);
            }
            if (list == null || list.size() == 0) {
                resultMap.put("result", "上传文件中无数据！");
            } else {
                resultMap.put("list", list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "上传文件存在问题");
        }
        return resultMap;
    }

    public static String getCellStringValue(XSSFRow row, int i) {
        XSSFCell cell = row.getCell(i);
        String result = "";
        if (cell == null) {
            return "";
        } else {
            //cell.setCellType(cell.CELL_TYPE_STRING);
            cell.setCellType(CellType.STRING);
            result = cell.getRichStringCellValue().getString();
        }
        result = result.trim();
        return result;
    }

    public static double getCellNumberValue(XSSFRow row, int i) {
        XSSFCell cell = row.getCell(i);
        if (cell == null) {
            return 0;
        } else {
            return cell.getNumericCellValue();
        }
    }

    /**
     * 获取excel中的日期格式数据
     */
    public static String getCellDateValue(XSSFRow row, int i, String format) {
        String result = "";
        XSSFCell cell = row.getCell(i);
        if (CellType.NUMERIC == cell.getCellTypeEnum()) {
            //判断是否为日期类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                //用于转化为日期格式
                Date d = cell.getDateCellValue();
                DateFormat formater = new SimpleDateFormat(format);
                result = formater.format(d);
            } else {
                // 用于格式化数字，只保留数字的整数部分
                DecimalFormat df = new DecimalFormat("########");
                result = df.format(cell.getNumericCellValue());
            }
        } else if (CellType.STRING == cell.getCellTypeEnum()) {
            result = getCellStringValue(row, i);
        }
        return result;
    }







}