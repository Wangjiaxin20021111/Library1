package demo13.filter;

import demo13.Constant.Code;
import demo13.controller.Result;
import demo13.util.LoggerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author 25043
 */
@WebFilter(filterName = "commonStaffFilter", urlPatterns = "/commonStaffServlet/*")
public class CommonStaffFilter implements Filter {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //检验馆长是否登录
        Object o = request.getSession().getAttribute("resultCurator");
        if (o != null) {
            //登录了，放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //未登录，跳转登录的页面
            logger.info("有人尝试未登录访问commonStaffServlet资源");
            Result result = new Result(Code.GET_ERR, null, "请先登录!");
            request.setAttribute("tip", result);
            response.sendRedirect("http://localhost:8080/demo15_war_exploded/login.html");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
