package com.liyichen125.dbfinalproject.interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversionInterceptor implements HandlerInterceptor {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String returnDateStr = request.getParameter("return_date");
        if (returnDateStr != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                Date returnDate = dateFormat.parse(returnDateStr);
                request.setAttribute("return_date", returnDate);
            } catch (ParseException e) {
                // 处理日期转换异常
            }
        }
        return true;
    }
}
