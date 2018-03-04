package three_hundred_examples.six_chapter;

/**
 * @author luzj
 * @description: 单件模式
 * @date 2018/3/1
 */
public class SingletonEmperor {
    private static SingletonEmperor emperor = null;

    /**
     * 私有化构造函数
     */
    private SingletonEmperor(){

    }

    public static SingletonEmperor getInstance(){
        if (emperor == null) {
            emperor = new SingletonEmperor();
        }
            return emperor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.hashCode() != obj.hashCode())
            return false;
        else if(this != obj)
            return false;
        else
            return super.equals(obj);
    }

    //测试
    public static void main(String[] args) {
        SingletonEmperor emperor1 = SingletonEmperor.getInstance();
        SingletonEmperor emperor2 = SingletonEmperor.getInstance();
        System.out.println(emperor1.hashCode());
        System.out.println(emperor2.hashCode());
        if (emperor1.equals(emperor2)){
            System.out.println(true);
        }
    }
}
