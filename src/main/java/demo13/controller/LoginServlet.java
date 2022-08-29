package demo13.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 25043
 */
@WebServlet("/loginServlet/*")
public class LoginServlet extends BaseServlet {
    /**
     * 馆长登录
     * 请求对象@param request
     * 响应对象@param response
     * 抛出的异常@throws Exception
     */
    public void curatorLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = (Result) request.getSession().getAttribute("resultCurator");
        request.setAttribute("resultCurator", result);
        request.getRequestDispatcher("/curator.jsp").forward(request, response);
    }

    /**
     * 普通员工的登录
     * 请求对象@param request
     * 响应对象@param response
     * 异常@throws ServletException
     * 异常@throws IOException
     */
    public void commonStafflogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = (Result) request.getSession().getAttribute("CommonStaff");
        request.setAttribute("CommonStaff", result);
        request.getRequestDispatcher("/CommonStaff.jsp").forward(request, response);
    }
}
