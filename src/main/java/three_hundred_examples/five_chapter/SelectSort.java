package three_hundred_examples.five_chapter;

import java.util.Random;
import java.util.Scanner;

/**
 * @author luzj
 * @description: 使用java进行，选择排序
 * @date 2018/3/1
 */
public class SelectSort {
    /**
     * 生成一个数组，大小100
     * @return
     */
    private static int[] generatorArr(){
        int[] arr = new int[100];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(5000);
        }
        return arr;
    }

    /**
     * 选择排序
     * @param arr
     */
    public static int[] selectSort(int[] arr){

        /**
         * 从第0位开始，之后的每一个数与它比较，把最小的数排到i的位置
         */
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                //todo 小的数固定到i的位置
                if (arr[j] < arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 打印数组
     * @param arr
     */
    private static void printArr(int[] arr){
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+" ");
            if ((i+1)%10==0)
                System.out.println();
        }
        System.out.println("================================");
    }

    /**
     * 字符串反转
     * @return
     */
    public static String reverseArr(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length/2; i++) {
            char temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
        return new String(arr);
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
      /*  int[] arr = generatorArr();
        printArr(arr);

        arr = selectSort(arr);
        printArr(arr);*/
        System.out.println(reverseArr());
    }
}
