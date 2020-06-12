package am.basic.jdbcStart.filter;


import am.basic.jdbcStart.controller.StartServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static am.basic.jdbcStart.util.Constants.Message.SESSION_EXPIRED_MESSAGE;
import static am.basic.jdbcStart.util.Constants.PagesLinks.*;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_MESSAGE_ATTRIBUTE_KEY;
import static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_USER_ATTRIBUTE_KEY;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("starting");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(PARAMETER_USER_ATTRIBUTE_KEY) == null) {
            request.setAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY, SESSION_EXPIRED_MESSAGE);
            request.getRequestDispatcher(START_URL).forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }


    @Override
    public void destroy() {
        System.out.println("ending");
    }
}
