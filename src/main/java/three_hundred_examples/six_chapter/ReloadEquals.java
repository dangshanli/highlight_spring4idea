package three_hundred_examples.six_chapter;

/**
 * @author luzj
 * @description:
 * 1.重写equals方法
 * 2.重写toString方法
 * 3.重写hash方法
 * @date 2018/3/1
 */
public class ReloadEquals {
private String name;
private int age;

    public ReloadEquals(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 重写equals方法
     * 关键是三方面：
     * 1.obj是否为空
     * 2.两者hash相同
     * 3.两者的Class类型是否相同
     * 4.两者的成员变量状态是否一致
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this != obj)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        ReloadEquals reload = (ReloadEquals) obj;
        return (name.equals(reload.name)) && (age== reload.age);
    }

    @Override
    public String toString() {
        return "name:"+name+",age:"+age;
    }

    @Override
    public int hashCode() {
        return 7*name.hashCode()+11*new Integer(age).hashCode();
    }
}
