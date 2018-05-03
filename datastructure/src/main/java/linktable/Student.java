package linktable;

/**
 * @author luzj
 * @description: 作为测试用的节点data用
 * @date 2018/4/26
 */
public class Student {
    private String name;
    private String teacher;
    private int age;

    public Student() {
    }

    public Student(String name, String teacher, int age) {
        this.name = name;
        this.teacher = teacher;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:" + name + ",teacher:" + teacher + ",age:" + age;
    }
}
