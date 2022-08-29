package demo13.dao.impl;

import demo13.dao.BookDao;
import demo13.po.Book;

import java.util.List;

/**
 * @author 25043
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    public static final BookDao INSTANCE = new BookDaoImpl();

    private BookDaoImpl() {

    }

    public static BookDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Book> selectAll() {
        String sql = "select bookId,isbn,name,count,prize,writer from book";
        return this.queryList(sql, Book.class);
    }


    @Override
    public void borrowOut(String bookId, String count) {
        String sql = "update book set count=count-? where bookId=?";
        this.help(sql, count, bookId);
    }

    @Override
    public Book getBookById(String bookId) {
        String sql = "select bookId,isbn,name,count,prize,writer from book where bookId=?";
        return this.queryObject(sql, Book.class, bookId);
    }

    @Override
    public void changeState(String bookId, int i) {
        String sql = "update book set bookState=? where bookId= ?";
        String sql1 = "update book set count=0 where bookId= ?";
        this.help(sql, i, bookId);
        this.help(sql1, bookId);
    }

    @Override
    public void borrowIn(int bookId, int i) {
        String sql = "update book set count=count+? where bookId=?";
        this.help(sql, i, bookId);
    }

    @Override
    public void changeState1(String bookId, int i) {
        String sql = "update book set bookState=? where bookId= ?";
        this.help(sql, i, bookId);
    }

    @Override
    public int insertBook(String isbn, String name, String prize, String writer, String count) {
        String sql = "insert into book(isbn,name,prize,writer,count) values(?,?,?,?,?)";
        return this.help(sql, isbn, name, prize, writer, count);
    }
}
