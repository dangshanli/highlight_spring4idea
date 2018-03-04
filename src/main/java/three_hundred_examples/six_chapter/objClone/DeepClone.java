package three_hundred_examples.six_chapter.objClone;

/**
 * @author luzj
 * @description:
 * 1.深度克隆，即成员变量含有自定义类型时，不光要克隆本对象，还有克隆引用类型的引用对象
 *
 * 2.引用自定义类型必须实现Cloneable接口，做好相关的克隆方法重写
 *
 * 3.如果引用对象不调用clone方法单独克隆，克隆对象就会只是调用同一个引用，
 * 到时候克隆对象的引用类型更改也会影响到被克隆对象，这显然不是我们想要的
 * @date 2018/3/1
 */
public class DeepClone implements Cloneable {
    public String name;
    public int code;
    public Address address;

    public DeepClone() {
    }

    public DeepClone(String name, int code, Address address) {
        this.name = name;
        this.code = code;
        this.address = address;
    }

    @Override
    public String toString() {
        return "name:"+name+",code:"+code+",address:"+address;
    }


    /**
     * 进行深克隆
     * 1.原本的对象克隆
     * 2.引用对象克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public DeepClone clone() throws CloneNotSupportedException {
        DeepClone deepClone = null;
        deepClone = (DeepClone) super.clone();
        //todo 对于自定义类型，需要单独拿出来克隆一遍
        deepClone.address = address.clone();
        return deepClone;
    }

    public static void main(String[] args) {
        Address address = new Address("上海",10);
        DeepClone deepClone1 = new DeepClone("克隆1",30,address);
        DeepClone deepClone2 = null;
        try {
            deepClone2 = deepClone1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(deepClone1);
        System.out.println(deepClone2);

        deepClone2.name = "克隆2";
        System.out.println(deepClone2);
        deepClone2.address.city = "北京";

        System.out.println("===========");
        System.out.println(deepClone1);
        System.out.println(deepClone2);
    }
}
