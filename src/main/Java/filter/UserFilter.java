package filter;


//@WebFilter("/product/*")
//public class UserFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
//        Auth auth = (Auth) session.getAttribute("user");
//        if(auth == null){
//            ((HttpServletResponse)servletResponse).sendRedirect("/hotel/Source");
//            return;
//        }
//        if(!auth.getRole().getName().equals("USER")){
//            ((HttpServletResponse)servletResponse).sendRedirect("/hotel/Source");
//            return;
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//
//    }
//}