package common_toolkits.twent_useful_segments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author luzj
 * @package common_toolkits.twent_useful_segments
 * @description:
 * @date 2018/2/7 17:50
 */
public class SendHttpReq {

    public void sendHttpReq(){
        try {
            URL my_url = new
                    URL("http://10.60.19.119:8091/v1/qr/qrCodeData/findByLoginId?loginId=AGK50050");
            BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));

            String str = "";
            while (null != (str = br.readLine())){
                System.out.println(str);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SendHttpReq().sendHttpReq();
    }


}
