package java8;

/**
 * @author luzj
 * @description:
 * @date 2018/4/21
 */
public interface Formula {
    double calculate(int a);

    /**
     * 扩展方法 接口定义时，使用默认实现
     * @param a
     * @return
     */
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
