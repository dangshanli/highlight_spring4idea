package three_hundred_examples;

import java.math.BigDecimal;

/**
 * @author luzj
 * @description:
 * 计算 1+ 1/2! + 1/3! + ...  + 1/20!
 * 2.对于精度要求很高的计算要求使用BigDecimal计算
 * @date 2018/2/28
 */
public class CalFactorial {
    public static void main(String[] args) {
        BigDecimal sum = new BigDecimal(0.0);
        BigDecimal factorial = new BigDecimal(1.0);

        int i = 1;
        //todo 阶乘数
        while (i <= 20){
            sum = sum.add(factorial);
            i++;
            //todo 每一个阶乘因子就是前一个因子乘以 (1.0/i)
            factorial = factorial.multiply(new BigDecimal(1.0/i));
        }
        System.out.println("阶乘结果为："+sum);
    }

}
