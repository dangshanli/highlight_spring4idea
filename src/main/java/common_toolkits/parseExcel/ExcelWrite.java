package common_toolkits.parseExcel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author luzj
 * @description: 使用poi创建Excel信息
 * @date 2018/3/29
 */
public class ExcelWrite {

    private static String[] columns = {"Name", "Email", "Date Of Birth", "Salary"};//表格头部
    private static List<Employee> employees = new ArrayList<>();

    //todo 初始化插入数据
    static {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 7, 21);
        employees.add(new Employee("南迁", "nanqian@fox.com", dateOfBirth.getTime(), 100));
        dateOfBirth.set(1995, 10, 23);
        employees.add(new Employee("北海", "beihai@fox.com", dateOfBirth.getTime(), 200));
        dateOfBirth.set(1988, 3, 13);
        employees.add(new Employee("柳梦璃", "liumengli@fox.com", dateOfBirth.getTime(), 300));
        dateOfBirth.set(1944, 12, 5);
        employees.add(new Employee("韩菱纱", "hanlingsha@fox.com", dateOfBirth.getTime(), 400));
    }

    /**
     * 创建一个新的Excel文件
     *
     * @param file 创建Excel文件的路径
     */
    public void createExcel(File file) throws IOException {
        //todo 一个workbook对象代表一个Excel表格
        Workbook workbook = new XSSFWorkbook();
        //todo CreationHelper帮助我们创建 DateFormat、Hyperlink、RichTextString等实例，进行定义cell单元格
        CreationHelper creationHelper = workbook.getCreationHelper();
        //TODO 一个Sheet对象代表一个Excel文件中的一个sheet
        Sheet sheet = workbook.createSheet("雇员");

        //todo 创建单元格的字体样式，用于表头
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        //todo 创建定义cell样式的对象
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        //todo 创建表头
        Row headRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        //todo 日期格式样式
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        //todo 填充表格详细数据
        int rowNum = 1;

        for (Employee e : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(e.getName());
            row.createCell(1).setCellValue(e.getEmail());

            Cell dateCell = row.createCell(2);
            dateCell.setCellValue(e.getDateOfBirth());
            dateCell.setCellStyle(dateCellStyle);

            row.createCell(3).setCellValue(e.getSalary());
        }

        //todo 调整单元格的大小，适应内容长度
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //TODO 将Excel输出到文件
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();

        workbook.close();
    }

    /**
     * 修改Excel文件的内容示例
     * @param file 待修改的Excel文件
     * @throws IOException Excel文件读取发生IO异常
     * @throws InvalidFormatException  Excel格式解析发生异常，比如读取的是一个TXT文件
     */
    @Deprecated//这个代码是有问题的
    public void modifyExcel(File file) throws IOException, InvalidFormatException {
        XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(file);
        //todo 修改第一个sheet表中的第二行，第一个单元格 -> 名称
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);

        if (cell == null)
            cell = row.createCell(0);
        cell.setCellType(CellType.STRING);
        cell.setCellValue("纪伯伦");


//        workbook.
        //todo 将修改的workbook回写到文件中去
        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out);
        }
        workbook.close();
    }

    public void modify2(File file) throws IOException {
        //Read the spreadsheet that needs to be updated
        FileInputStream fsIP= new FileInputStream(file);
//Access the workbook
        XSSFWorkbook wb = new XSSFWorkbook(fsIP);
//Access the worksheet, so that we can update / modify it.
        XSSFSheet worksheet = wb.getSheetAt(0);
// declare a Cell object
        Cell cell = null;
// Access the second cell in second row to update the value
        cell = worksheet.getRow(1).getCell(1);
// Get current cell value value and overwrite the value
        cell.setCellValue("滕王阁");
//Close the InputStream
        fsIP.close();
//Open FileOutputStream to write updates
        FileOutputStream output_file =new FileOutputStream(file);
        //write changes
        wb.write(output_file);
//close the stream
        output_file.close();
    }

    public static void main(String[] args) {
        String path = "C:\\sftp_test\\sample_write.xlsx";
        File file = new File(path);
        /*try {
            new ExcelWrite().createExcel(file);
        } catch (IOException e) {
            System.err.println("文件输出流出现问题，Excel未能正常输出");
            e.printStackTrace();
        }*/

        try {
            new ExcelWrite().modifyExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

       /* try {
            new ExcelWrite().modify2(file);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
