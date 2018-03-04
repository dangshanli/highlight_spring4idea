package three_hundred_examples.six_chapter.serialization;

import java.io.Serializable;

/**
 * @author luzj
 * @description:
 * @date 2018/3/2
 */
public class Nancy extends Person implements Serializable {
    public int age;

    @Override
    public String toString() {
        return "name:"+name+",code:"+code+",age:"+age+",hashcode"+this.hashCode();
    }
}
