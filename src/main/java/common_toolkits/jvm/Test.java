package common_toolkits.jvm;

/**
 * @author luzj
 * @description: javap -v Test.class //反编译字节码文件，查看字节码信息
 * @date 2018/4/23
 */
public class Test {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = calc(a,b);
    }

    static int calc(int a,int b){
        return (int) Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
    }

}


