package demo13.Service.impl;

import demo13.Service.BookService;
import demo13.Constant.Code;
import demo13.controller.Result;
import demo13.dao.BookDao;
import demo13.dao.impl.BookDaoImpl;
import demo13.po.Book;

import java.util.List;

import static demo13.Constant.Code.*;

/**
 * @author 25043
 */
public class BookServiceImpl implements BookService {
    private final BookDao bookDao = BookDaoImpl.getInstance();

    private static final BookService bookService = new BookServiceImpl();

    private BookServiceImpl() {

    }

    public static BookService getBookService() {
        return bookService;
    }

    /**
     * 查询全部书本
     * 查询集合@return
     */
    @Override
    public Result selectBookAll() {
        //书本集合
        List<Book> list = bookDao.selectAll();
        //提示查询成功
        if (list != null && list.size() > 0) {

            return new Result(Code.GET_OK, list, "查询成功");
        }
        return new Result(Code.GET_ERR, null, "暂未有需要查询的书籍");
    }

    /**
     * 查询全部书本集合，并且借出
     * 书本Id@param bookId
     * 剩余数量@param count
     * 查询结果@return
     */
    @Override
    public Result selectTheseBookThenBorrow(String bookId, String count) {

        Book book1 = bookDao.getBookById(bookId);
        List<Book> list = bookDao.selectAll();
        if (book1 != null) {

            try {
                int getCount = Integer.parseInt(count);
                if (getCount <= 0) {
                    return new Result(UPDATE_ERR, getList(list), "请不要输入不合法数字");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(UPDATE_ERR, getList(list), "请不要输入不合法数字");
            }
            //符合借出的条件
            if (book1.getCount() > Integer.parseInt(count)) {
                //借出书本
                bookDao.borrowOut(bookId, count);
                List<Book> list1 = bookDao.selectAll();
                return new Result(UPDATE_OK, getList(list1), "借出成功!");
            } else if (book1.getCount() == Integer.parseInt(count)) {
                //符合条件，借出书本
                bookDao.borrowOut(bookId, count);
                bookDao.changeState(bookId, 0);
                List<Book> list1 = bookDao.selectAll();
                return new Result(UPDATE_OK, getList(list1), "修改成功!");
            } //不符合借出书本的条件
            else if (book1.getCount() < Integer.parseInt(count)) {
                List<Book> list1 = bookDao.selectAll();
                return new Result(UPDATE_ERR, getList(list1), "书本已经没办法借出这么多!");
            }
        }
        return new Result(UPDATE_ERR, list, "查询错误，未查询到次书");
    }

    /**
     * 归还书本
     * 书本Id@param bookId
     * 书本数量@param count
     * 结果集@return
     */
    @Override
    public Result selectTheseBookThenReturn(String bookId, String count) {
        List<Book> list = bookDao.selectAll();
        try {
            int realCount = Integer.parseInt(count);
            if (realCount <= 0) {
                return new Result(UPDATE_ERR, list, "请勿非法输入");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Result(UPDATE_ERR, list, "请勿非法输入");
        }

        //借入书本
        bookDao.borrowIn(Integer.parseInt(bookId), Integer.parseInt(count));
        //修改图书状态
        bookDao.changeState1(bookId, 1);

        return new Result(UPDATE_OK, getList(list), "成功借出!");
    }

    /**
     * 新增书本
     * isbn@param isbn
     * 名称@param name
     * 价格@param prize
     * 作者@param writer
     * 数量@param count
     * 结果集@return
     */
    @Override
    public Result addBook(String isbn, String name, String prize, String writer, String count) {

        List<Book> list1 = bookDao.selectAll();
        //对异常数据的校验
        try {
            float prize1 = Float.parseFloat(prize);
            int count1 = Integer.parseInt(count);
            if (prize1 <= 0 || count1 <= 0) {
                return new Result(SAVE_ERR, list1, "输入错误");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Result(SAVE_ERR, list1, "输入错误");
        }
        //添加书籍
        int add = bookDao.insertBook(isbn, name, prize, writer, count);
        //添加之后查询
        List<Book> list2 = bookDao.selectAll();
        if (add == 1) {
            return new Result(SAVE_OK, list2, "添加成功");
        } else {
            return new Result(SAVE_ERR, list2, "输入数据错误");
        }
    }

    private List<Book> getList(List<Book> list1) {
        return list1;
    }
}
