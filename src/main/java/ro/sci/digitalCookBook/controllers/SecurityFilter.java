package ro.sci.digitalCookBook.controllers;

import org.springframework.security.core.userdetails.UserDetails;
import ro.sci.digitalCookBook.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("currentUser");

        String url = ((HttpServletRequest) request).getRequestURL().toString();

        if (url.contains("events")) {
            if (user == null) {
                HttpServletResponse servletResponse = (HttpServletResponse) response;
                //servletResponse.sendError(401);
                servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                servletResponse.setHeader("Location", "/login");
                return;
            }
        }

        System.out.println("Thread name: " + Thread.currentThread().getName()
                +
                ", current user: " + (user != null ? user.getUserName() : null));


        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
