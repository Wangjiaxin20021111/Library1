package demo13.controller;

import demo13.Service.CommonStaffService;
import demo13.Service.CuratorService;
import demo13.Service.impl.CommonStaffServiceImpl;
import demo13.Service.impl.CuratorServiceImpl;
import demo13.po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 25043
 */
@WebServlet("/userController/*")
public class UserController extends BaseServlet {
    private final CuratorService curatorService = CuratorServiceImpl.getInstance();
    private final CommonStaffService commonStaffService = CommonStaffServiceImpl.getInstance();

    /**
     * 处理登录的接口
     * 请求@param request
     * 响应@param response
     * 异常@throws IOException
     * 异常@throws ServletException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Result result = curatorService.login(userName, password);
        HttpSession session = request.getSession();
        if (result.getData() != null) {
            session.setAttribute("resultCurator", result);
            response.sendRedirect("http://localhost:8080/demo15_war_exploded/loginServlet/curatorLogin");
        } else {
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    /**
     * 普通员工登录
     */
    public void commonStaffLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Result result = commonStaffService.commonStaffLogin(userName, password);
        HttpSession session = request.getSession();
        System.out.println(result);
        if (result.getData() != null && ((User) result.getData()).getCommonStaff().getState() == 1) {
            session.setAttribute("CommonStaff", result);
            response.sendRedirect("http://localhost:8080/demo15_war_exploded/loginServlet/commonStafflogin");
        } else {
            request.setAttribute("CommonStaff", result);
            request.getRequestDispatcher("/CommonStaff.jsp").forward(request, response);
        }

    }
}
