package three_hundred_examples.six_chapter.objClone;

/**
 * @author luzj
 * @description:
 * 1.对象克隆
 *
 * 2.当成员变量是基础类型或者不可变类型（String）时，只需浅克隆：即重写clone()，修改修饰符，和返回类型
 *
 * 3.所谓克隆，即复制对象的成员变量状态，其实是一个新对象，产生新的hashcode
 * @date 2018/3/1
 */
public class ShallowClone implements Cloneable {
    public String name;
    public int age;

    public ShallowClone() {
    }

    public ShallowClone(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name:"+name+",age:"+age+",hashcode:"+this.hashCode();
    }

    /**
     * 重写clone方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public ShallowClone clone() throws CloneNotSupportedException {
        ShallowClone obj = null;
        try {
            obj = (ShallowClone) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
            return obj;
    }


    public static void main(String[] args) {
        ShallowClone obj1 = new ShallowClone("二狗",13);
        ShallowClone obj2 = null;
        try {
            obj2 = obj1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(obj1);
        System.out.println(obj2);

        obj2.name = "王麻子";
        System.out.println(obj2);
    }
}
