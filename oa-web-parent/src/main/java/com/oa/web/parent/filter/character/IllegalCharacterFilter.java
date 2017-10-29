package com.oa.web.parent.filter.character;

import com.alibaba.fastjson.JSONObject;
import com.oa.core.utils.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字符过滤
 * <p/>
 * Created by [张渊]
 * 2017/8/10 10:51
 */
public class IllegalCharacterFilter implements Filter {

    private String[] characterParams = null;

    private boolean OK = true;

    public void destroy() {

    }

    /**
     * 此程序块主要用来解决参数带非法字符等过滤功能
     */

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        servletResponse.setHeader("Set-Cookie", "name=value; HttpOnly");
        boolean status = false;

        String url = servletRequest.getRequestURI();
        java.util.Enumeration params = request.getParameterNames();

        String param = "";
        String paramValue = "";

        for (String characterParam : characterParams) {
            if (url.contains(characterParam)) {
                status = true;
                break;
            }
        }


        while (params.hasMoreElements()) {
            if (url.contains("workFlowAction") || url.contains("/modules/role/") || url.contains("/alert") || url.contains("httpAct") || url.contains("osAct") || url.contains("/port")) {
                break;
            }

            param = (String) params.nextElement();

            if (((url.indexOf("notice/manager/save") > 0 || url.indexOf("notice/manager/update") > 0) &&
                    (param.equals("noticeDesc")) || param.equals("editorValue")) ||
                    ((url.indexOf("/bizIntroduce/manager/save") > 0 || url.indexOf("/bizIntroduce/manager/update") > 0) &&
                            (param.equals("content")) || param.equals("editorValue")) ||
                    ((url.indexOf("/surveyQues/manager/addSurveyQuestion") > 0 || url.indexOf("/surveyQues/manager/updateSurveyQuestion") > 0) &&
                            (param.equals("editorValue")) || param.equals("descr")) ||
                    ((url.indexOf("/question/manager/addQuestion") > 0 || url.indexOf("/question/manager/updateQuestion") > 0) &&
                            (param.equals("editorValue")) || param.equals("quesDesc"))
                    ) {
                continue;
            }

            String[] values = request.getParameterValues(param);
            paramValue = "";


            if (OK) {// 过滤字符串为0个时 不对字符过滤

                for (String value : values) {
                    paramValue = paramValue + value;
                }

                if (StringUtil.isNotNull(paramValue)) {
                    for (String characterParam : characterParams) {
                        if (paramValue.contains(characterParam)) {
                            status = true;
                            break;
                        }
                    }
                }

                if (status)
                    break;
            }
        }
        String requestType = servletRequest.getHeader("X-Requested-With");

        if (
                url.contains("/Copy of") ||
                        url.contains("/_") ||
                        url.contains("/.") ||
                        url.contains("/~") ||
                        url.contains("/Old")

                )
            status = true;

        if (status) {
            if (!StringUtil.isEmpty(requestType) && "XMLHttpRequest".toLowerCase().equals(requestType.toLowerCase())) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("illegalCharacter", "illegalCharacter");
                jsonObject.put("info", "系统消息");
                ((HttpServletResponse) response).setStatus(404);
                servletResponse.getWriter().write(jsonObject.toString());
            } else {
                ((HttpServletResponse) response).setStatus(404);
                servletResponse.sendRedirect(servletRequest.getContextPath() + "/views/error/error404.jsp");
            }
        } else
            arg2.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        if (config.getInitParameter("characterParams").length() < 1)
            OK = false;
        else
            this.characterParams = config.getInitParameter("characterParams").split(",");
    }

}
