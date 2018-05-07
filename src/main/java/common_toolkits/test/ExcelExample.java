package common_toolkits.test;

import common_toolkits.parseExcel.Employee;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author luzj
 * @description:
 * @date 2018/4/16
 */
public class ExcelExample {
    public static final String READ_FILE = "C:\\sftp_test\\sample.xlsx";

    public static void readExcel() throws IOException, InvalidFormatException {
        //读取Excel文件创建workbook对象
        Workbook wb = WorkbookFactory.create(new File(READ_FILE));

        System.out.println("该文档有" + wb.getNumberOfSheets() + "个工作簿:");

        //遍历打印所有工作簿名称，这里使用lambda表达式
        wb.forEach(sheet -> {
            System.out.println("\t" + sheet.getSheetName());
        });

        //遍历工作簿1的所有单元格
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = wb.getSheetAt(0);
        System.out.println("打印所有单元格内容：");
        sheet.forEach(row -> {
            row.forEach(cell -> {
//                System.out.print("\t" + formatter.formatCellValue(cell) + "  ");
                printCell(cell);
            });
            System.out.println();
        });

        wb.close();
    }

    //如果不用dateFormat忽视类型打印，可以根据类型读取cell
    private static void printCell(Cell cell) {

        switch (cell.getCellTypeEnum()) {
            case STRING:
                System.out.print(cell.getStringCellValue());
                break;
            case NUMERIC://分日期和数值两种情况
                if (DateUtil.isCellDateFormatted(cell))
                    System.out.print(cell.getDateCellValue());
                else
                    System.out.print(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                System.out.print(cell.getBooleanCellValue());
                break;
            case FORMULA:
                System.out.print(cell.getCellFormula());
                break;
            default:
                System.out.print("");
        }
        System.out.print("\t");
    }

    private static String[] columns = {"姓名","邮箱","生日","工资"};
    private static List<Employee> employees = new ArrayList<>();
    static {
        Calendar birth = Calendar.getInstance();
        birth.set(1992,1,12);
        employees.add(new Employee("二狗","ergou@qq.com",birth.getTime(),1200));
        birth.set(1993,11,12);
        employees.add(new Employee("韩信","hanxin@qq.com",birth.getTime(),2200));
        birth.set(1995,12,12);
        employees.add(new Employee("卫庄","weizhuang@qq.com",birth.getTime(),3200));
    }
    //创建Excel文件
    public static void writeExcel() throws IOException {
        Workbook wb = new XSSFWorkbook();

        //帮助创建样式、富文本、超链接
        CreationHelper creationHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("雇员");

        //字体
        Font head = wb.createFont();
        head.setBold(true);
        head.setFontHeightInPoints((short) 14);
        head.setColor(IndexedColors.BLUE.getIndex());

        CellStyle headStyle = wb.createCellStyle();
        headStyle.setFont(head);

        //创建表头
        Row headRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headStyle);
        }

        CellStyle dateCellStyle = wb.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy/MM/dd"));

        //创建表格
        int rowNum = 1;
        for (Employee e:employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(e.getName());
            row.createCell(1).setCellValue(e.getEmail());
            Cell cell = row.createCell(2);
            cell.setCellStyle(dateCellStyle);
            cell.setCellValue(e.getDateOfBirth());

            row.createCell(3).setCellValue(e.getSalary());
        }

        //格式调整
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream outputStream = new FileOutputStream("雇员.xlsx");
        wb.write(outputStream);

        outputStream.close();
        wb.close();
    }


    public static void main(String[] args) {
        try {
//            readExcel();
            writeExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
