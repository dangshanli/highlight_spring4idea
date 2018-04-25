package recursion;

/**
 * @author luzj
 * @description:
 * @date 2018/4/25
 */
public class Fact {

    /**
     * 阶乘
     * @param n
     * @return
     */
    static int factorial(int n) {
        if (n == 1)
            return 1;
        else if(n == 0)
            return 1;
        else
            return n * factorial(n-1);
    }

    /**
     * 打印
     * @param n
     * @return
     */
    static int print(int n){
        if (n == 0)
            return 0;
        else {
            int x = n;
            while (x > 0) {
                System.out.print(" ");
                x--;
            }
            System.out.println(n);
            return print(n-1);
        }
    }

    public static void main(String[] args) {
//        int s = factorial(10);
//        System.out.println(s);
        int a = print(10);
        System.err.println(a);
    }


}
