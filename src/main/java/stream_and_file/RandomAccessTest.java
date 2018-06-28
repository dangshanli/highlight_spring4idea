package stream_and_file;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author luzj
 * @description: 以二进制格式读写文件
 * 1 dataOutput 写文件
 * 2 RandomAccessFile(文件随机访问类) 读取文件
 * 3 RandomAccessFile 有一个指针，可以通过 seek(long)改变指针位置，来从任意位置读写文件字节
 * @date 2018/6/28
 */
public class RandomAccessTest {

    public static void main(String[] args) throws IOException {
        Employee[] staffs = new Employee[3];
        staffs[0] = new Employee("Carl", 46000, 1988, 12, 11);
        staffs[1] = new Employee("Harry", 55000, 1998, 11, 15);
        staffs[2] = new Employee("Tony", 23000, 1978, 4, 10);

        String path = "src/main/resource/emp2.dat";
        //todo 写入 Employee 数据进入
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path))) {
            for (Employee e : staffs) {
                writeData(out, e);
            }
        }

        //todo 随机访问文件类 读取文件
        try (RandomAccessFile in = new RandomAccessFile(path, "r")) {
            int n = (int) (in.length() / Employee.RECORD_SIZE);

            Employee[] newStaff = new Employee[n];
            for (int i = 0; i < n; i++) {
                newStaff[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                newStaff[i] = readData(in);
            }

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }


    /**
     * 将Employee对象的状态值写入到输出流
     *
     * @param out
     * @param e
     * @throws IOException
     */
    public static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeInt(e.getSalary());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(e.getHireDay());

        out.writeInt(calendar.get(Calendar.YEAR));
        out.writeInt(calendar.get(Calendar.MONTH));
        out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 从 DataInput 中读取状态值，然后实例化一个Employee对象
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);

        int salary = in.readInt();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m, d);
    }
}