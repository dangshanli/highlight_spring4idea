package common_toolkits;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/18.
 */
public class HttpClientUtil {
    @Autowired
    HttpClient httpClient;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param clazz 返回实体类类型
     * @param url 接口地址
     * @param <T>
     * @return 返回实体类实例
     */
    public <T> T httpGet(Class<T> clazz,String url){
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            if ("".equals(content)) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
           return mapper.readValue(content,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 参数为json格式
     *
     * @param clazz
     * @param body
     * @param url
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T httpPostJson(Class<T> clazz, String body, String url) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
        // httpPost.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();

        String content = EntityUtils.toString(httpEntity);
        if(StringUtils.isEmpty(content)){
            throw new Exception("接口返回数据为空, url: "+ url + "  body: " + body);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, clazz);
    }

    /**
     * form表单为参数
     *
     * @param url
     * @param params
     * @return
     */
    public Map<String, String> doPost(String url, Map<String, String> params) {
        Map<String, String> resultMap = new HashMap<>();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setRedirectStrategy(new DefaultRedirectStrategy() {
            @Override
            protected boolean isRedirectable(String method) {
                return true;
            }
        });

        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
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
            Map<String, String> headerParam = new HashMap();
            HttpEntity entity = httpResponse.getEntity();
            resultMap.put("code", httpResponse.getStatusLine().getStatusCode() + "");
            resultMap.put("content", EntityUtils.toString(entity));
            httpPost.releaseConnection();
            closeableHttpClient.close();
        } catch (Exception e) {
            logger.error("httpclientutil请求异常", e);
        }

        return resultMap;
    }

    /**
     * get
     * @param url
     * @return
     */
    public Map<String, String> doGet(String url) {
        Map<String, String> resultMap = new HashMap<>();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setRedirectStrategy(new DefaultRedirectStrategy() {
            @Override
            protected boolean isRedirectable(String method) {
                return true;
            }
        });
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000).setConnectionRequestTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            resultMap.put("code", httpResponse.getStatusLine().getStatusCode() + "");
            resultMap.put("content", EntityUtils.toString(entity));
            httpGet.releaseConnection();
            closeableHttpClient.close();
        } catch (Exception e) {
            logger.error("httpclientutil请求异常", e);
        }
        return resultMap;
    }

}
