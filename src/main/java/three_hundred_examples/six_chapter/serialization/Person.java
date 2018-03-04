package three_hundred_examples.six_chapter.serialization;

import java.io.Serializable;

/**
 * @author luzj
 * @description:
 * @date 2018/3/2
 */
public class Person implements Serializable{
    public String name;
    public int code;

    @Override
    public String toString() {
        return "name:"+name+",code:"+code+",hashcode:"+this.hashCode();
    }
}
