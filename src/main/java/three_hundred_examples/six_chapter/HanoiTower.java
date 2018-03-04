package three_hundred_examples.six_chapter;

/**
 * @author luzj
 * @description:
 * 1.汉诺塔问题
 * 2.A B C三个底盘，A从小到大顺序摆着n阶盘子，B为中介，把A的盘子移动到C，顺序不变
 * @date 2018/3/1
 */
public class HanoiTower {
    public static void moveDish(int level,char from,char inter,char to){
        if (level == 1)
            System.out.println("从"+from+"移动1号盘子到"+to);
        else{
            moveDish(level-1,from,to,inter);
            System.out.println("从"+from+"移动"+level+"号盘子到"+to);
            moveDish(level-1,inter,from,to);
        }


    }

    public static void main(String[] args) {
        moveDish(3,'A','B','C');
    }


}
