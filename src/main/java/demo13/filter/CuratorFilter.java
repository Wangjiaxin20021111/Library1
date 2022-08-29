package demo13.filter;

import demo13.controller.Result;
import demo13.util.LoggerUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

import static demo13.Constant.Code.GET_ERR;

/**登录拦截接口
 * @author 25043
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = "/loginServlet/curatorLogin")
public class CuratorFilter implements Filter {
    private static final Logger logger= LoggerUtil.getLogger();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //涉及到4个作用域
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object o = request.getSession().getAttribute("resultCurator");
        System.out.println("o的值是...." + o);
        //说明用户登录了
        if (o != null) {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //说明用户未登录
            //跳转到登陆页面
            logger.info("有人尝试未登录访问资源!");
            Result result = new Result(GET_ERR, null, "请先登录!");
            request.setAttribute("tip", result);
            request.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
