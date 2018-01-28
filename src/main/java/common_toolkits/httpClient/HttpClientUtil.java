package common_toolkits.httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/28.
 * 模拟get、post请求工具类
 */
public class HttpClientUtil {

    /**
     * 包装get请求
     *
     * @param url 只需要处理一个url
     * @return
     */
    public Map<String, String> doGet(String url) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig config = RequestConfig.custom().
                setConnectionRequestTimeout(10000).
                setConnectTimeout(50000).
                build();
        httpGet.setConfig(config);
        CloseableHttpResponse response = null;
        Map<String, String> result = new HashMap<>();
        try {
            response = closeableHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result.put("code", response.getStatusLine().getStatusCode() + "");
            result.put("content", EntityUtils.toString(entity));
            httpGet.releaseConnection();
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * post请求方法包装
     * @param url
     * @param params 请求参数需要包装处理
     * @return
     */
    public Map<String, String> doPost(String url, Map<String, String> params) {
        Map<String, String> resultMap = new HashMap<>();
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        try {
            //请求体
            if (params != null && !params.isEmpty()) {
                List formParams = new ArrayList();
                for (String key : params.keySet()) {
                    formParams.add(new BasicNameValuePair(key, params.get(key)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
            }

            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            resultMap.put("code", httpResponse.getStatusLine().getStatusCode() + "");
            resultMap.put("content", EntityUtils.toString(entity));
            httpPost.releaseConnection();
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 参数为json字符串
     * @param clazz 需要返回的实体类型对象
     * @param body 请求参数
     * @param url
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T httpPostJson(Class<T> clazz, String body, String url) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        //todo 包装请求实体
        StringEntity entity = new StringEntity(body, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
        httpPost.setEntity(entity);
        //todo 执行请求
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse response = closeableHttpClient.execute(httpPost);
        //todo 解析返回参数
        HttpEntity httpEntity = response.getEntity();
        String content = EntityUtils.toString(httpEntity);
        if(StringUtils.isEmpty(content)){
            throw new Exception("接口返回数据为空, url: "+ url + "  body: " + body);
        }
        closeableHttpClient.close();
        httpPost.releaseConnection();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, clazz);
    }
}
