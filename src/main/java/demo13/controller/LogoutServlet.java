package demo13.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 25043
 */
@WebServlet("/logoutServlet/*")
public class LogoutServlet extends BaseServlet {
    /**
     * 馆长退出登录
     * 请求@param request
     * 响应@param response
     * 异常@throws IOException
     */
    public void curatorLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("resultCurator");
        response.sendRedirect("http://localhost:8080/demo15_war_exploded/login.html");
    }

    /**
     * 普通员工登录
     * 请求@param request
     * 响应 @param response
     * 异常@throws IOException
     */
    public void commonStallLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("CommonStaff");
        response.sendRedirect("http://localhost:8080/demo15_war_exploded/login.html");
    }
}
