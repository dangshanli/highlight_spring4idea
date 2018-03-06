package three_hundred_examples.seven_chapter;

/**
 * @author luzj
 * @description: 学生实体类
 * @date 2018/3/6
 */
public class Student {
    private String name;
    private int id;
    private boolean gender;
    private double account;

    @Override
    public String toString() {
        return "id:"+id+",name:"+name+",gender:"+gender+",account:"+account;
    }

}
