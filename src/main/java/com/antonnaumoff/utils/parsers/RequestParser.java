package com.antonnaumoff.utils.parsers;

import org.apache.log4j.Logger;

import javax.portlet.ActionRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RequestParser {

    public static int checkString(String string, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer i = null;
        try {
            i = Integer.parseInt(string);
        } catch (NullPointerException e) {
            return i;

        } catch (Exception e) {
            (Logger.getLogger("utils/exceptions")).warn("Some message", e);
            request.setAttribute("message", "Some problem with database, please, try later");
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
        return i;
    }

    public static Integer parseIntWithDefaultValue(ActionRequest request, String value) {
        Integer result = null;
        try {
            result = Integer.parseInt(request.getParameter(value));
        } catch (Exception ignored) {

        }
        if(result==null){
            result = 0;
        }
        return result;
    }

    public static Date parseDateDeafultValue(ActionRequest actionRequest, String value) {

        Date result = null;

        try {
            result = Date.valueOf(actionRequest.getParameter(value));
        } catch (Exception ignored) {

        }
        if (result == null) {

            result = Date.valueOf("0001-01-01");
        }
        return result;
    }

}






