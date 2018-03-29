package com.mmall.common;

import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description: 处理全局异常
 * User: shanliu
 * Date: 2018-03-28
 * Time: 22:55
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //拿到当前返回的url
        String url = request.getRequestURI().toString();
        ModelAndView mv;
        //定一个全局的异常
        String defaultMsg = "System error";
        //定义接口请求  .json ,  .page
        //这里要求项目中所有请求json数据，都使用.json结尾
        if (url.endsWith(".json")) {
            //只允许自定义的异常抛给用户
            if (ex instanceof PermissionException || ex instanceof ParamException){
                JsonData result = JsonData.fail(ex.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            }else {
                log.error("unknow json exception,url:"+url,ex);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView",result.toMap());
            }
        }else if (url.endsWith(".page")){ //这里要求项目中所有请求page数据，都使用.page结尾
            JsonData result = JsonData.fail(defaultMsg);
            log.error("unknow page exception,url:"+url,ex);
            mv = new ModelAndView("exception",result.toMap());
        }else {
            JsonData result = JsonData.fail(defaultMsg);
            log.error("unknow exception,url:"+url,ex);
            mv = new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
