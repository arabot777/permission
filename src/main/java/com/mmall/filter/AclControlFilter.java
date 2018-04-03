package com.mmall.filter;


import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.mmall.common.ApplicationContextHelper;
import com.mmall.common.JsonData;
import com.mmall.common.RequestHolder;
import com.mmall.model.SysUser;
import com.mmall.service.SysCoreService;
import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description: 权限拦截
 * User: lenovo
 * Date: 2018-04-03
 * Time: 11:08
 */
@Slf4j
public class AclControlFilter implements Filter {

    private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();

    private final static String noAuthUrl = "/sys/user/noAuth.page";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //定义白名单
        String exclusionUrls = filterConfig.getInitParameter("exclusionUrls");
        List<String> exclusionUrlList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(exclusionUrls);
        exclusionUrlSet = Sets.newConcurrentHashSet(exclusionUrlList);
        exclusionUrlSet.add(noAuthUrl);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath  = request.getServletPath();
        Map requestMap = request.getParameterMap();
        //白名单处理逻辑
        if (exclusionUrlSet.contains(servletPath)){
            filterChain.doFilter(servletRequest,servletResponse);
        }

        SysUser sysUser = RequestHolder.getCurrentUser();
        if (sysUser == null){
            log.info("somenoe visit {}, but no login,parameter:{}",servletPath , JsonMapper.obj2String(requestMap));
            noAuth(request,response);
            return;
        }
        SysCoreService sysCoreService = ApplicationContextHelper.popBean(SysCoreService.class);
        if (!sysCoreService.hasUrlAcl(servletPath)){
            log.info("{} visit {}, but no login, parameter:{}", JsonMapper.obj2String(sysUser), servletPath, JsonMapper.obj2String(requestMap));
            noAuth(request,response);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }

    /**
     * 无权限访问
     * @param request
     * @param response
     */
    public void noAuth(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        if (servletPath.endsWith(".json")){
            JsonData jsonData = JsonData.fail("没有访问权限,如需访问请联系管理员");
            response.setHeader("Content-Type","application/json");
            response.getWriter().print(JsonMapper.obj2String(jsonData));
            return;
        }else{
            clientRedirect(noAuthUrl, response);
            return;
        }
    }

    private void clientRedirect(String url, HttpServletResponse response) throws IOException{
        response.setHeader("Content-Type", "text/html");
        response.getWriter().print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "<title>跳转中...</title>\n" + "</head>\n" + "<body>\n" + "跳转中，请稍候...\n" + "<script type=\"text/javascript\">//<![CDATA[\n"
                + "window.location.href='" + url + "?ret='+encodeURIComponent(window.location.href);\n" + "//]]></script>\n" + "</body>\n" + "</html>\n");
    }


    @Override
    public void destroy() {

    }
}
