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
        Workbook workbook = WorkbookFactory.create(excel);
//        XSSFWorkbook workbook = new XSSFWorkbook(excel);
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
//                        System.out.println(b.length);
                        java.awt.Color awtColor = new java.awt.Color(change(b[1]), change(b[2]), change(b[3]), change(b[0]));
                        if (awtColor.equals(java.awt.Color.RED))
                            System.out.println(awtColor.toString()+"===="+"red"+"===="+dataFormatter.formatCellValue(cell));
                    }
                }

                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            });
            data.put(row.getRowNum(), list);
//            System.out.println(list);
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


/*    private ColorInfo excelColor2U0F(Color color) {
        if (color == null)
            return null;
        ColorInfo ci = null;
        if (color instanceof XSSFColor) {
            XSSFColor xssfColor = (XSSFColor) color;
            byte[] b = xssfColor.getARGB();
            System.out.println("argb_length:" + b.length);
            if (b != null) {
                System.out.println((int) b[0] + ":" + (int) b[1]);
                ci = ColorInfo.fromARGB(b[0], b[1], b[2], b[3]);
            }
        } else if (color instanceof HSSFColor) {
            HSSFColor hssfColor = (HSSFColor) color;
            short[] s = hssfColor.getTriplet();
            if (s != null)
                ci = ColorInfo.fromARGB(s[0], s[1], s[2]);
        }
        return ci;

    }*/

    public static void main(String[] args) {
        String path = "C:\\sftp_test\\sample_spreadsheet.xlsx";
        String path2 = "C:\\sftp_test\\经分及领导首页移动业务渠道运营指标优化需求.xlsx";
        String path3 = "./fill_colors.xlsx";
        File excel = new File(path2);

        try {
            new ExcelReader().printExcel(excel);
        } catch (IOException e) {
            System.err.println("找不到解析的Excel文件");
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            System.err.println("无效的Excel格式");
            e.printStackTrace();
        }
    }

}
