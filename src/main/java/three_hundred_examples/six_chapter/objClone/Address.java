package three_hundred_examples.six_chapter.objClone;

/**
 * @author luzj
 * @description:
 * 1.可克隆对象，可实现浅克隆
 *
 * 2.作为DeepClone对象的引用类型，如果DeepClone对象需要克隆，则需要克隆Address对象
 * @date 2018/3/1
 */
public class Address implements Cloneable {
    public String city;
    public int code;

    public Address() {
    }

    public Address(String city, int code) {
        this.city = city;
        this.code = code;
    }

    @Override
    public String toString() {
        return "city:"+city+",code:"+code+",hashcode:"+this.hashCode();
    }

    /**
     * 重写clone方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Address clone() throws CloneNotSupportedException {
        Address address = null;
        address = (Address) super.clone();
        return address;
    }
}
