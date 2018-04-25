package recursion;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luzj
 * @description:
 * 汉诺塔问题：
 * 1.起源柱子移动 (n-1)个 到辅助柱子
 * 2.起源柱子移动最后1个到目标柱子
 * 3.辅助柱子移动n-1个到目标柱子
 * 如此循环......
 * @date 2018/4/25
 */
public class TowerOfHanoi {
    /**
     *汉诺塔问题
     * @param n 圆盘数
     * @param from 起源柱子
     * @param to 目标柱
     * @param aux 辅助柱子
     */
    static void towersOfHanoi(int n,char from,char to,char aux){
        if (n == 1)
            System.out.println(from+"-->"+to);
        else if (n == 2){
            System.out.println(from+"-->"+aux);
            System.out.println(from+"-->"+to);
            System.out.println(aux+"-->"+to);
        } else {
            towersOfHanoi(n-1,from,aux,to);
            System.out.println(from+"-->"+to);
            towersOfHanoi(n-1,aux,to,from);
        }
    }

    /**
     * 测试数组排序问题
     * @param A
     * @param index
     * @return
     */
    static int isArrayIsSortedOrder(int[] A,int index){
        if (A.length == 1)
            return 1;
        else if (index == 1)
            return 1;
        else {
            return A[index] <= A[index-1]?0:isArrayIsSortedOrder(A,index-1);
        }

    }
    static int[] A = new int[10];
    static AtomicInteger s = new AtomicInteger(0);

    /**
     * 生成n位的2进制串
     * @param n
     */
    static void binary(int n){
        if (n < 1) {
            System.out.print(s.get()+":");
            for (int i:A) {
                System.out.print(i+"\t");
            }
            System.out.println();
            s.incrementAndGet();
        }
        else {
            A[n-1] = 0;
            binary(n-1);
            A[n-1] = 1;
            binary(n-1);
        }
    }
    static AtomicInteger x = new AtomicInteger(0);
    static char[] B = new char[5];

    /**
     * 生成n位k进制串，k < 16，即最高16进制
     * @param n
     * @param k
     */
    static void k_string(int n,int k){
        if (n<1){
            System.out.print(x.get()+":");
            for (char i:B) {
                System.out.print(i+"\t");
            }
            System.out.println();
            x.incrementAndGet();
        }else {
            for (int i = 0; i < k; i++) {
                B[n-1] = generatorK(i);
                k_string(n-1,k);
            }
        }
    }

   static char generatorK(int k){
        switch (k){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return (char) (k+48);
            case 10:return 'a';
            case 11:return 'b';
            case 12:return 'c';
            case 13:return 'd';
            case 14:return 'e';
            case 15:return 'f';
            default:return 0;
        }
    }

    public static void main(String[] args) {
//        towersOfHanoi(3,'A','C','B');
//        int[] A = {12,13,15,16,17};
//        int s = isArrayIsSortedOrder(A,A.length-1);
//        System.out.println(s);

//        binary(10);
        k_string(5,16);

    }
}
