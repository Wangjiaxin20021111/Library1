package Test;

import demo13.Exception.DaoException;
import demo13.dao.impl.BaseDao;
import demo13.po.Book;
import demo13.util.JavaDataBaseConnectUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试查询Dao层的代码
 *
 * @author 25043
 */
public class DaoDemoTest {

    private BaseDao baseDao;

    @Before
    public void DaoDemoTestBefore() {
        baseDao = new BaseDao();
    }

    /**
     * 测试BaseDao的查询单个对象的方法
     */
    @Test(expected = DaoException.class)
    public void testQueryOne() {
        //测试查询成功的情况
        String sql = "select bookId,isbn,name,count,prize,writer,bookState from book where bookId=1";
        Book book = baseDao.queryObject(sql, Book.class);
        //真实的情况
        String actual = book.toString();
        //测试的情况
        Book book2 = new Book();
        book2.setBookState(1);
        book2.setBookId(1);
        book2.setPrize(30);
        book2.setWriter("XiaoMing");
        book2.setIsbn("Minhua");
        book2.setName("Dictionary");
        book2.setCount(2777);
        String except = book2.toString();
        //比较两个是否一样
        Assert.assertEquals(except, actual);

        //测试查询失败的情况之一，sql语句异常
        String sql1 = "select bookId,isbn,name,count,prize,writer,bookState from boo where bookId=1";
        Book book1 = baseDao.queryObject(sql1, Book.class);

    }

    /**
     * 测试BaseDao的查询集合的方法
     */
    @Test(expected = DaoException.class)
    public void testQueryList() {
        //查询集合
        String sql = "select bookId,isbn,name,count,prize,writer,bookState from book where bookId=1";
        List<Book> list = baseDao.queryList(sql, Book.class);
        //
        String real=list.toString();
        List<Book> bookList=new ArrayList<>();
        Book book2 = new Book();
        book2.setBookState(1);
        book2.setBookId(1);
        book2.setPrize(30);
        book2.setWriter("XiaoMing");
        book2.setIsbn("Minhua");
        book2.setName("Dictionary");
        book2.setCount(2723);
        bookList.add(book2);
        String except = bookList.toString();
        //比较两个是否一样
        Assert.assertEquals(except, real);
        //测试查询失败的情况
        String sql1 = "select bookId,isbn,name,count,prize,writer,bookState from boo";
        List<Book> list1 = baseDao.queryList(sql1, Book.class);
    }

    /**
     * 测试Dao层的更新方法
     */
    @Test(expected = DaoException.class)
    public void testUpdate() {
        String sql = "insert into book(isbn,name,count,prize,writer,bookState) values(?,?,?,?,?,?);";
        //输出结果1
        Assert.assertEquals(1, baseDao.help(sql, "123", "345", 3, 99, "Mark", 1));
        String sql1 = "update book set bookState=1 where bookId =1";
        //输出结果1
        Assert.assertEquals(1, baseDao.help(sql1));
    }

    /**
     * 测试获取连接的方法
     */
    @Test
    public void testConnection() {
        //输出结果com.mysql.cj.jdbc.ConnectionImpl@1638935
        System.out.println(JavaDataBaseConnectUtil.getConnection());
    }

    @After
    public void end() {
        System.out.println("测试结束!!");
    }
}
