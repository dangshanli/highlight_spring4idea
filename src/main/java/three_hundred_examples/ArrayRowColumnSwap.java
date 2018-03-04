package three_hundred_examples;

/**
 * @author luzj
 * @description: 数组行列互换
 * @date 2018/2/28
 */
public class ArrayRowColumnSwap {
    /**
     * 打印二维数组
     *
     * @param arr
     */
    private static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     * 转换方法
     * @param args
     */
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("行列互换前：");
        printArray(arr);

        //todo 执行转换
        int arr2[][] = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr2[i][j] = arr[j][i];
            }
        }
        System.out.println("执行转换后：");
        printArray(arr2);
    }
}
