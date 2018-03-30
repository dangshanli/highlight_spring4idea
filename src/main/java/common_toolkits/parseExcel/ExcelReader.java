package common_toolkits.parseExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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
     * @param excel
     * @throws IOException
     * @throws InvalidFormatException
     */
    public void printExcel(File excel) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(excel);
        System.out.println(excel.getName() + "有" + workbook.getNumberOfSheets() + "个sheet");

        //todo 获取sheet名称，使用lambda表达式
        System.out.println("sheets name:");
        workbook.forEach(sheet -> {
            System.out.println("\t" + sheet.getSheetName());
        });
        Map<Integer,List<String>> data = new HashMap<>();
        int j = 0;
        //todo 获取第一张sheet表的数据
        Sheet sheet1 = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        sheet1.forEach(row -> {

            List<String> list = new ArrayList<>();
            row.forEach(cell -> {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
//                System.err.print(cellValue + "\t");
            });
            data.put(row.getRowNum(),list);
            System.out.println(list);
        });

//        System.err.println("===========================");
//        System.out.println(data);
        //todo 关闭workbook
        workbook.close();

    }

    public static void main(String[] args) {
        String path = "C:\\sftp_test\\sample_spreadsheet.xlsx";
        String path2 = "C:\\sftp_test\\经分及领导首页移动业务渠道运营指标优化需求.xlsx";
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
