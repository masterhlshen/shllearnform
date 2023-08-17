package com.shl.work;

import com.shl.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 处理团队工作任务表格
 *
 * @author shl
 */
public class DealTaskExcel {
    public static void main(String[] args) throws IOException {
        String filePath = "D:/xclog/团队周工作汇总表.xlsx";

        FileInputStream input = new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(input);

        int sheetNums = wb.getNumberOfSheets();

        String sheetname = "2023年8月";
        boolean exist = false;
        for (int i = 0; i < sheetNums; i++) {
            XSSFSheet sheetAt = wb.getSheetAt(i);
            if (sheetname.equals(sheetAt.getSheetName())) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            wb.createSheet(sheetname);
        }

        // TODO 读取sheet
        List<List<String>> data = new LinkedList<>();
        for (int i = 0; i < sheetNums; i++) {
            XSSFSheet sheetAt = wb.getSheetAt(i);
            if (sheetname.equals(sheetAt.getSheetName())) {
                continue;
            }
            int maxRow = sheetAt.getLastRowNum();
            System.out.println(maxRow + " " + sheetAt.getSheetName());
            Map<String, String> mergeValueCache = new HashMap<>();
            for (int rowIndex = 2; rowIndex <= maxRow; rowIndex++) {
                XSSFRow row = sheetAt.getRow(rowIndex);
                int maxCol = row.getLastCellNum();
                int isEmpty = 0;
                List<String> rowList = new LinkedList<>();
                for (int colIndex = 0; colIndex < maxCol; colIndex++) {
                    XSSFCell cell = row.getCell(colIndex);
                    String value = getCellStringValue(row, colIndex);
                    MergeInfo mergeInfo = getMergeInfo(sheetAt, rowIndex, colIndex);
                    if (mergeInfo != null) {
                        if (StringUtils.isNotBlank(value)) {
                            mergeValueCache.put("" + mergeInfo.firstRow + "_" + mergeInfo.lastRow + "_" +
                                    mergeInfo.firstColum + "_" + mergeInfo.lastColumn, value);
                        } else {
                            value = mergeValueCache.get("" + mergeInfo.firstRow + "_" + mergeInfo.lastRow + "_" +
                                    mergeInfo.firstColum + "_" + mergeInfo.lastColumn);

                        }
                    }
                    if (StringUtils.isBlank(value)) {
                        isEmpty++;
                    }
                    rowList.add(value);
                }
                if (maxCol > isEmpty) {
                    data.add(rowList);
                }
            }

        }
        for (List<String> ele : data) {
            System.out.println(JsonUtils.writeValueAsString(ele));
        }

        if (!exist) {
            // TODO 暂时不考虑
            FileOutputStream out = new FileOutputStream(filePath);

            wb.write(out);
            wb.close();
            out.close();
        }

    }


    static MergeInfo getMergeInfo(Sheet sheet, int row, int column) {
        MergeInfo res = null;
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    res = new MergeInfo(firstRow, firstColumn, lastRow, lastColumn);
                }
            }
        }
        return res;
    }

    static String getCellStringValue(XSSFRow row, int i) {
        XSSFCell cell = row.getCell(i);
        String result = "";
        if (cell == null) {
            return "";
        } else {
            if (StringUtils.isNotBlank(cell.getCellStyle().getDataFormatString()) && cell.getCellStyle().getDataFormatString().contains("/")) {
                Date dateCellValue = cell.getDateCellValue();
                result = Objects.isNull(dateCellValue) ? "" : sdf.format(dateCellValue);
            } else {
                cell.setCellType(CellType.STRING);
                result = cell.getRichStringCellValue().getString();
            }
        }
        result = result.trim();
        return result;
    }

    static class MergeInfo {
        int firstRow, lastColumn, firstColum, lastRow;

        public MergeInfo(int firstRow, int firstColum, int lastRow, int lastColumn) {
            this.firstRow = firstRow;
            this.lastColumn = lastColumn;
            this.firstColum = firstColum;
            this.lastRow = lastRow;
        }
    }

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
}
