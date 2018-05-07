package java8;

/**
 * @author luzj
 * @description:
 * @date 2018/4/21
 */
public class TestFormula {
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a*100);
            }
        };

        //默认函数无法再lambda表达式中被访问
        Formula formula1 = (a)->{
//            return sqrt(100);
            return 233;
        };

        System.out.println(formula.sqrt(100));
        System.out.println(formula.calculate(100));
    }
}
