package three_hundred_examples.seven_chapter;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author luzj
 * @description:
 * 1.利用反射动态调用对象方法：静态方法 和成员方法
 * 2.这种调用的强大之处在于可以在运行时确定调用的方法，而不必在编译时写好调用方法
 * @date 2018/3/6
 */
public class RefMethodInvoke {
    /**
     * 动态调用方法：
     * 1.静态方法
     * 2.成员方法
     */
    public static void dynamicInvokeMethod() {
        try {
            //todo 调用静态方法 sin(double)
            Method sin = Math.class.getDeclaredMethod("sin", Double.TYPE);
            Double sin1 = (Double) sin.invoke(null, new Integer(1));
            System.out.println("1的正弦值：" + sin1);
            System.out.println("调用String类的非静态方法equals:");

            //todo 成员方法equals()
            Method equals = String.class.getDeclaredMethod("equals", Object.class);
            Boolean mrsoft = (Boolean) equals.invoke(new String("ada wong"), "ada wong");
            System.out.println("字符串是否是ada wong:" + mrsoft);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        dynamicInvokeMethod();
    }
}
