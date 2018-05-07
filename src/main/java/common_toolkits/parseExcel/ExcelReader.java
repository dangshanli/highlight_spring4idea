package common_toolkits.parseExcel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luzj
 * @description: 使用Poi读取Excel信息
 * @date 2018/3/29
 */
public class ExcelReader {

    /**
     * 读取Excel文件，打印其中的内容
     *
     * @param excel
     * @throws IOException
     * @throws InvalidFormatException
     */
    public void printExcel(File excel) throws IOException, InvalidFormatException {

        //不管是.xls 还是 .xlsx 文件都可以解析
        Workbook workbook = WorkbookFactory.create(excel);
        System.out.println(excel.getName() + "有" + workbook.getNumberOfSheets() + "个sheet");

        //todo 获取sheet名称，使用lambda表达式
        System.out.println("sheets name:");
        workbook.forEach(sheet -> {
            System.out.println("\t" + sheet.getSheetName());
        });
        Map<Integer, List<String>> data = new HashMap<>();
        int j = 0;
        //todo 获取第一张sheet表的数据
        Sheet sheet1 = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();


        sheet1.forEach(row -> {
            List<String> list = new ArrayList<>();
            row.forEach(cell -> {
                if (cell.getColumnIndex() == 0) {
                    XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getCellStyle();
                    XSSFColor color = cellStyle.getFillForegroundColorColor();
                    byte[] b = null;
                    if (color != null && color instanceof XSSFColor)
                        b = color.getARGB();

                    if (b != null) {
                        for (byte by : b) {
                            System.out.print((int) by+"\t");
                        }
                        java.awt.Color awtColor = new java.awt.Color(change(b[1]), change(b[2]), change(b[3]), change(b[0]));
                        if (awtColor.equals(java.awt.Color.RED))
                            System.out.println(awtColor.toString()+"===="+"red"+"===="+dataFormatter.formatCellValue(cell));
                    }
                }

                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            });
            data.put(row.getRowNum(), list);
        });

        //todo 关闭workbook
        workbook.close();
    }

    /**
     * 将poi的argb转化成awt的标准argb数值
     * @param number
     * @return
     */
    private int change(int number) {
        return ((number & 0x0f0) >> 4) * 16 + (number & 0x0f);
    }


    public void testXXX(File excel) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(excel);
        Sheet sheet = wb.getSheetAt(0);
        System.err.println("sheet:"+sheet);
        Row row = sheet.getRow(0);
        System.err.println("row:"+row);
        try {
            Cell cell = row.getCell(1);
        }catch (NullPointerException e){
            System.out.println("2444");

        }

        DataFormatter formatter = new DataFormatter();
//        String s = formatter.formatCellValue(cell);


    }
    public static void main(String[] args) {
        String path = "C:\\sftp_test\\sample_spreadsheet.xlsx";
        String path2 = "C:\\sftp_test\\经分及领导首页移动业务渠道运营指标优化需求.xlsx";
        String path3 = "./fill_colors.xlsx";
        String path4 = "C:\\sftp_test\\samplexxx.xlsx";
        File excel = new File(path4);

        try {
            new ExcelReader().testXXX(excel);
        } catch (IOException e) {
            System.err.println("找不到解析的Excel文件");
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            System.err.println("无效的Excel格式");
            e.printStackTrace();
        }
    }

}
