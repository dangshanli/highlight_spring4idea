package three_hundred_examples.six_chapter.serialization;

/**
 * @author luzj
 * @description:
 * @date 2018/3/2
 */
public class TestExtSerial {
    public static void main(String[] args) {
        //测试父子序列化Nancy
        String path = "src/main/resource/nancy.ser";
        SerialUtil<Nancy> serialUtil = new SerialUtil<>(path);

        Nancy nancy = new Nancy();
        nancy.age = 123;
        nancy.name= "nancy";
        nancy.code = 213;

        serialUtil.serialObj(nancy);

        serialUtil.deSerialObj();
    }
}
