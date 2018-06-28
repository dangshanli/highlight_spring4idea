package stream_and_file;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luzj
 * @description: 雇员实体类
 * @date 2018/6/27
 */
public class Employee {
    public static final int NAME_SIZE = 40;
    public static final int RECORD_SIZE = 96;
    private String name;
    private int salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String name, int salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    public Employee(String name, int salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String t = year + "-" + month + "-" + day;

        try {
            hireDay = sdf.parse(t);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return name + "在" + sdf.format(hireDay) + "加入公司,月薪" + salary + "元";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }
}
