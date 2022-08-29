package demo13.dao;

import demo13.po.Book;

import java.util.List;

/**
 * @author 25043
 */
public interface BookDao {
    /**
     * 查询全部图书
     * 图书集合@return
     */
    List<Book> selectAll();

    /**
     * 借出图书
     * 书本id@param bookId
     * 书本剩余数量@param count
     */
    void borrowOut(String bookId, String count);

    /**
     * 通过bookId获取图书
     * 书本编号@param bookId
     * 书本@return
     */
    Book getBookById(String bookId);

    /**
     * 修改图书的状态
     * 书本id@param bookId
     * 状态@param i
     */
    void changeState(String bookId, int i);

    /**
     * 借入图书
     * 书本id@param bookId
     * 书本状态@param i
     */
    void borrowIn(int bookId, int i);

    /**
     * 修改状态
     * 书本id@param bookId
     * 书本状态@param i
     */
    void changeState1(String bookId, int i);

    /**
     * 插入书本
     * 出版社@param isbn
     * 书本名称@param name
     * 价格@param prize
     * 作者@param writer
     * 剩余数量@param count
     * 插入@return
     */
    int insertBook(String isbn, String name, String prize, String writer, String count);
}
