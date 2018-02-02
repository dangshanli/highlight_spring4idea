package common_toolkits.twent_useful_segments;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description: 将java对象包装成Json格式的数据
 * @date 2018/2/2 11:07
 */
public class CreateJson {
    public static JsonObject buildJson() {
        JsonObject jsonObject = new JsonObject();

        //todo 添加目录属性
        jsonObject.addProperty("category", "it");

        //todo 添加语言属性，语言属性是一个json数组，下面有子列表
        //todo 构建json数组，并向里面加JsonObject
        JsonArray jsonArray = new JsonArray();
        //todo 想构建子对象列表，就用一个JSonArray装
        JsonObject lan1 = new JsonObject();
        lan1.addProperty("id", "1");
        lan1.addProperty("name", "Java");
        lan1.addProperty("ide", "IDEA");

        JsonObject lan2 = new JsonObject();
        lan2.addProperty("id", 2);
        lan2.addProperty("name", "Swift");
        lan2.addProperty("ide", "Xcode");

        jsonArray.add(lan1);
        jsonArray.add(lan2);

        jsonObject.add("languages", jsonArray);
        //todo 添加pop属性
        jsonObject.addProperty("pop", true);

        //todo 打印json对象
//        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    public static void main(String[] args) {
        JsonObject json = buildJson();
        System.out.println(json.toString());
    }
}
