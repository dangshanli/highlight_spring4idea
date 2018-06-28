package stream_and_file;

/**
 * @author luzj
 * @description:
 * @date 2018/6/7
 */
public class Person {
    String name;
    String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "name:"+name+",gender:"+gender;
    }
}
