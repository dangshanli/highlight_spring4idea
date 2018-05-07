package common_toolkits.corsinterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/1/18.
 * 跨域访问：方案二
 * 配置拦截器，在前处理中实现response重置
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    private final static Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("添加跨域访问头配置，Access-Control-Allow-Origin:*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE,HEAD");
        response.setHeader("Access-Control-Allow-Headers", "S_ID,content-type");
        response.setHeader("Access-Control-Max-Age", "3600000");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        //让请求不缓存，根据情况使用
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        logger.debug("==========preHandler==========");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("==========postHandler==========");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("==========afterCompletion==========");

        super.afterCompletion(request, response, handler, ex);
    }
}
