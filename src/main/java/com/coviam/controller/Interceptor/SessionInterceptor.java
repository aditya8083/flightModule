package com.coviam.controller.Interceptor;

import com.coviam.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    Logger log;
    @Autowired
    UUIDUtil uUIDUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        log.debug("In preHandle we are Intercepting the Request");
        log.debug("RequestURI::" + request.getRequestURI());
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("interactionId", uUIDUtil.getUniqueId());
        if(StringUtils.isBlank((String)request.getAttribute("sessionId"))){
            httpSession.setAttribute("sessionId", uUIDUtil.getUniqueId());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
        log.debug("_________________________________________");
        log.debug("In postHandle request processing " );
        log.debug("_________________________________________");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
        log.debug("________________________________________");
        log.debug("In afterCompletion Request Completed");
        log.debug("________________________________________");
    }
}