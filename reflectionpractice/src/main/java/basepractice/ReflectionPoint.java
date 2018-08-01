package basepractice;

import java.util.Date;

/**
 * @author luzj
 * @description:
 * @date 2018/7/31
 */
public class ReflectionPoint {
    private Date birthday = new Date();

    private int x;
    public int y;
    public String str1 = "ball";
    public String str2 = "basketball";
    public String str3 = "itcast";

    public ReflectionPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ReflectionPoint(String str1, String str2, String str3) {
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
    }

    @Override
    public String toString() {
        return str1 + ":" + str2 + ":" + str3;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ReflectionPoint other = (ReflectionPoint) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
