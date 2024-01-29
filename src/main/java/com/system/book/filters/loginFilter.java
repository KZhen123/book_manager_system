package com.system.book.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1. 实现Filter接口
 * 2. 重写doFilter
 * 3. 配置过滤器（注解）
 */
@WebFilter(filterName = "Filter", urlPatterns = {"/page/admin/*", "/page/reader/*", "/book/*", "/borrow/*", "/category/*", "/feedback/*", "/notice/*"})
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 请求-doFilter-放行-执行业务代码-doFilter-响应
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 参数父转子
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // 获得session域
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            // 跳转到登录
//            httpServletResponse.sendRedirect("/book_manager_system/page/user/login.html");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open ('/book_manager_system/page/user/login.html','_top')");
            out.println("</script>");
            out.println("</html>");
        } else {
            // 放行
            // 过滤器链，可以传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
