package common_toolkits.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author luzj
 * @description:
 * @date 2018/4/12
 */
public class TestXX {
    public static void main(String[] args) {
        File file = new File("C:\\sftp_test\\samplexxx.xlsx");
        try {
            Workbook wb = WorkbookFactory.create(file);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(0);
            System.out.println(row.getRowNum());

            Row row1 = sheet.getRow(1);
            System.out.println(row1.getRowNum());




        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
