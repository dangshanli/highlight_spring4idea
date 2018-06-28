package stream_and_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author luzj
 * @description: 将对象以文本格式存储
 * 1 使用PrintWrite做为输出流
 * 2 Scanner 作为输入流
 * 3 文本存储对象状态
 * @date 2018/6/27
 */
public class TextFileTest {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String path = "src/main/resource/emp.dat";
        Employee[] staffs = new Employee[3];
        staffs[0] = new Employee("Carl", 24000, 1988, 12, 11);
        staffs[1] = new Employee("Harry", 34000, 1978, 5, 19);
        staffs[2] = new Employee("Tony", 44000, 1968, 2, 13);

        //将对象信息写入本地文件
        try (PrintWriter out = new PrintWriter(path, "utf-8")) {
            writeData(staffs, out);
        }

        //从本地文件读取对象信息
        try (Scanner in = new Scanner(new FileInputStream(path), "utf-8")) {
            Employee[] newStaffs = readData(in);
            for (Employee e : newStaffs) {
                System.out.println(e);
            }
        }

    }

    /**
     * 将 Employee 对象写入输出流
     *
     * @param employees
     * @param out
     */
    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);

        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    /**
     * 从Scanner 对象读取 Employee 数组
     *
     * @param in
     * @return
     */
    private static Employee[] readData(Scanner in) {
        int length = in.nextInt();
        in.nextLine();

        Employee[] employees = new Employee[length];
        for (int i = 0; i < length; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    /**
     * 通过 PrintWrite 写入 Employee 对象的信息
     *
     * @param out
     * @param e
     */
    public static void writeEmployee(PrintWriter out, Employee e) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(e.getHireDay());
        out.println(e.getName() + "|" + e.getSalary() + "|" + calendar.get(Calendar.YEAR) + "|" +
                (calendar.get(Calendar.MONTH) + 1) + "|" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 从 Scanner读取数据，包装成对象
     *
     * @param in
     * @return
     */
    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();

        String[] tokens = line.split("\\|");
        String name = tokens[0];
        int salary = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        int month = Integer.parseInt(tokens[3]);
        int day = Integer.parseInt(tokens[4]);
        return new Employee(name, salary, year, month, day);
    }
}
