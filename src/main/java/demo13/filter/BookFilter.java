package demo13.filter;

import demo13.Constant.Code;
import demo13.controller.Result;
import demo13.util.LoggerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

/**对图书管理接口的封装
 * @author 25043
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/bookController/*")
public class BookFilter implements Filter {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object o = request.getSession().getAttribute("resultCurator");
        Object o1 = request.getSession().getAttribute("CommonStaff");
        if (o != null || o1 != null) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //说明用户未登录
            //跳转到登陆页面
            logger.info("有人尝试未登录访问书籍资源!");
            Result result = new Result(Code.GET_ERR, null, "请先登录!");
            request.setAttribute("tip", result);
            request.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
