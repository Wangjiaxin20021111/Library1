package demo13.controller;

import demo13.Service.CommonStaffService;
import demo13.Service.impl.CommonStaffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 25043
 */
@WebServlet("/commonStaffServlet/*")
public class CommonStaffServlet extends BaseServlet {

    private final CommonStaffService commonStaffService = CommonStaffServiceImpl.getInstance();

    /**
     * 馆长查看并且管理普通员工
     */
    public void selectCommonStaff(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Result result = commonStaffService.selectAll();
        request.setAttribute("curatorList", result);
        System.out.println(result);
        request.getRequestDispatcher("/curatorList.jsp").forward(request, response);
    }

    /**
     * 拉黑员工
     * 请求@param request
     * 响应@param response
     * 异常@throws IOException
     * 异常@throws ServletException
     */
    public void blackTheCommonStaff(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commonStaffId = request.getParameter("commonStaffId");
        String state = request.getParameter("state");
        request.getSession().removeAttribute("CommonStaff");
        Result result = commonStaffService.selectAndCheck(commonStaffId, state);
        request.setAttribute("curatorList", result);
        request.getRequestDispatcher("/curatorList.jsp").forward(request, response);
    }
}
