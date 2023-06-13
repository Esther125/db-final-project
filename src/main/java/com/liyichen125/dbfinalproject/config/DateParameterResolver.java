package com.liyichen125.dbfinalproject.config;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateParameterResolver implements HandlerMethodArgumentResolver {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Date.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String parameterValue = webRequest.getParameter(parameter.getParameterName());
        if (parameterValue != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                return dateFormat.parse(parameterValue);
            } catch (ParseException e) {
                // 处理日期转换异常
            }
        }
        return null;
    }
}
