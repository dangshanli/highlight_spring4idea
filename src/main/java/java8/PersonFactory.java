package java8;

/**
 * @author luzj
 * @description:
 * @date 2018/4/23
 */
public interface PersonFactory<P extends Person> {
    P create (String firstName,String lastName);
    default double sqrt(double s){
        return Math.sqrt(s);
    }
}
