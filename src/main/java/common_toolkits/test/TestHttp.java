package common_toolkits.test;

import common_toolkits.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author luzj
 * @package common_toolkits.test
 * @description:
 * @date 2018/2/5 16:13
 */
public class TestHttp {
    public static void main(String[] args) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        JSONArray otpList = null;
        otpList = httpClientUtil.
                httpGet(JSONArray.class, "http://10.60.19.172:8090/v1/contact/getOTPbyLoginId?loginId=AGK50050");
        if (otpList != null) {
            JSONObject object = (JSONObject) otpList.get(0);
            System.out.println(object.get("gangwei"));
        } else {
            System.out.println(233);
        }
    }
}
