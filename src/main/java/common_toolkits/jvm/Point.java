package common_toolkits.jvm;

/**
 * @author luzj
 * @description:
 * @date 2018/4/23
 */
public class Point {
    int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int area(Point b){
        int length = Math.abs(b.y - this.y);
        int width  = Math.abs(b.x-this.x);
        return length * width;
    }
}
