package common_toolkits;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/17.
 * 解决网站跨域访问的问题
 */
@Component
public class CrossFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 方案一，设置response返回属性"Access-Control-Allow-Origin"
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        //todo 设置返回头response Head属性
        String origin = req.getHeader("Origin");
        rep.setHeader("Access-Control-Allow-Origin",origin);
        /*//直接设置成*也可以，但是获取origin可以设置可访问域名列表进行过滤
        /*//*表示什么域名都可以访问了
        rep.setHeader("Access-Control-Allow-Origin","*");*/

        rep.setHeader("Access-Control-Allow-Method","POST,GET,OPTIONS,DELETE");
        rep.setHeader("Access-Control-Max-Age","3600");//设置缓存时间，3600秒
        rep.setHeader("Access-Control-Allow-Header","Origin, No-Cache, X-Requested-With, " +
                "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, " +
                "X-E4M-With,userId,token");//设置返回属性列表，这里根据情况添加，不一定要这些
        rep.setHeader("Access-Control-Allow-Credentials","true");
        rep.setHeader("XDomainRequestAllowed","1");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
