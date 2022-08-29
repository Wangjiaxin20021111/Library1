package demo13.controller;


import demo13.Service.BookService;
import demo13.Service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * @author 25043
 */
@WebServlet("/bookController/*")
public class BookController extends BaseServlet {
    private final BookService bookService = BookServiceImpl.getBookService();

    /**
     * 查询所有图书
     * 请求对象@param request
     * 响应对象@param response
     * 异常@throws ServletException
     * 异常@throws IOException
     */
    public void selectBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Result resultBook = bookService.selectBookAll();
        request.setAttribute("resultBook", resultBook);
        Result resultCommonStaff = (Result) request.getSession().getAttribute("CommonStaff");

        Result resultCurator = (Result) request.getSession().getAttribute("resultCurator");
        request.setAttribute("resultCurator", resultCurator);
        request.setAttribute("CommonStaff", resultCommonStaff);
        request.getRequestDispatcher("/BookWork.jsp").forward(request, response);
    }

    /**
     * 借出图书
     * 请求对象@param request
     * 响应对象@param response
     * 异常@throws ServletException
     * 异常@throws IOException
     */
    public void borrowBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        Result resultCommonStaff = (Result) request.getSession().getAttribute("CommonStaff");

        Result resultCurator = (Result) request.getSession().getAttribute("resultCurator");

        request.setAttribute("resultCurator", resultCommonStaff);
        request.setAttribute("CommonStaff", resultCurator);
        Result resultBook = bookService.selectTheseBookThenBorrow(bookId, count);
        request.setAttribute("resultBook", resultBook);
        request.getRequestDispatcher("/BookWork.jsp").forward(request, response);
    }

    /**
     * 归还图书
     * 请求对象@param request
     * 响应对象@param response
     * 异常@throws ServletException
     * 异常@throws IOException
     */
    public void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        Result resultCommonStaff = (Result) request.getSession().getAttribute("CommonStaff");

        Result resultCurator = (Result) request.getSession().getAttribute("resultCurator");

        request.setAttribute("resultCurator", resultCurator);
        request.setAttribute("CommonStaff", resultCommonStaff);
        Result result = bookService.selectTheseBookThenReturn(bookId, count);
        request.setAttribute("resultBook", result);
        request.getRequestDispatcher("/BookWork.jsp").forward(request, response);
    }

    /**
     * 添加图书
     * 请求@param request
     * 响应@param response
     * 异常@throws IOException
     * 异常@throws ServletException
     */
    public void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String isbn = request.getParameter("ISBN");
        String name = request.getParameter("name");
        String prize = request.getParameter("prize");
        String writer = request.getParameter("writer");
        String count = request.getParameter("count");
        Result result = bookService.addBook(isbn, name, prize, writer, count);
        Result resultCommonStaff = (Result) request.getSession().getAttribute("CommonStaff");

        Result resultResultCurator = (Result) request.getSession().getAttribute("resultCurator");

        request.setAttribute("resultCurator", resultResultCurator);
        request.setAttribute("CommonStaff", resultCommonStaff);
        request.setAttribute("resultBook", result);
        request.getRequestDispatcher("/BookWork.jsp").forward(request, response);
    }
}
