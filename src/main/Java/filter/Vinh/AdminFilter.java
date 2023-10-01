package filter.Vinh;


import model.Vinh.Auth;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/book/*")
//public class AdminFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
//        Auth auth = (Auth) session.getAttribute("user");
//        if(auth == null){
//            ((HttpServletResponse)servletResponse).sendRedirect("/auth");
//            return;
//        }
//        if(!auth.getRole().getName().equals("ADMIN")){
//            ((HttpServletResponse)servletResponse).sendRedirect("/auth");
//            return;
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//
//    }
//}