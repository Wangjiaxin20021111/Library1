package Test;

import TestMessage.Constant;
import demo13.Constant.Code;
import demo13.Service.BookService;
import demo13.Service.impl.BookServiceImpl;
import demo13.controller.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * 测试BookService的接口以及他的实现类
 */
public class TestBookService {

    /**
     * 待测试的Service对象
     */
    private BookService bookService;


    @Before
    public void beforeInit() {
        bookService = BookServiceImpl.getBookService();
    }

    /**
     * 测试bookService的查询所有方法
     */
    @Test
    public void testSelectBookAll() {
        //获取一个结果集
        Result result = bookService.selectBookAll();
        //测试状态码
        Assert.assertEquals(result.getCode(), Code.GET_OK);
        //测试消息提示
        Assert.assertEquals(result.getMessage(), Constant.selectSuccess);
    }

    /**
     * 测试借出书本
     */
    @Test
    public void testSelectTheseBookThenBorrow() {
        //获取一个结果集
        Result result = bookService.selectBookAll();
        System.out.println("生成的结果集" + result);
        //测试使用的参数，此处假定对图书借出3本，这种情况默认成功
        String number = "1";
        String count = "3";
        Result result1 = bookService.selectTheseBookThenBorrow(number, count);
        //成功的情况
        Assert.assertEquals(Code.UPDATE_OK, result1.getCode());
        //失败情况之一:非法输入
        String number1 = "1";
        String count1 = "3A";
        Result result2 = bookService.selectTheseBookThenBorrow(number1, count1);
        //测试失败的情况
        Assert.assertEquals(Code.UPDATE_ERR, result2.getCode());
    }

    /**
     * 测试归还图书的接口
     */
    @Test
    public void testSelectTheseBookThenReturn() {
        //获取一个结果集
        Result result = bookService.selectBookAll();
        //展示信息
        System.out.println(result);
        String number = "1";
        String count = "30";
        //获得结果集
        Result result1 = bookService.selectTheseBookThenReturn(number, count);
        Assert.assertEquals(Code.UPDATE_OK, result1.getCode());
        //错误的输入
        String number1 = "1";
        String count1 = "A23";
        Result result2 = bookService.selectTheseBookThenReturn(number1, count1);
        Assert.assertEquals(Code.UPDATE_ERR, result2.getCode());
    }

    /**
     * 测试新增图书
     */
    @Test
    public void testAddBook() {

        String isbn = "123";

        String name = "123";

        String count = "123";

        String prize = "123";

        String writer = "123";
        Result result = bookService.addBook(isbn, name, prize, writer, count);
        //检验结果
        Assert.assertEquals(Code.SAVE_OK, result.getCode());
        //测试非法输入的情况，数量非法
        String countWrong = "-1";
        Result result1 = bookService.addBook(isbn, name, prize, writer, countWrong);
        Assert.assertEquals(Code.SAVE_ERR, result1.getCode());
        //测试非法输入的情况，价格错误
        String prize1 = "wrong!";
        Result result2 = bookService.addBook(isbn, name, prize1, writer, countWrong);
        Assert.assertEquals(Code.SAVE_ERR, result2.getCode());
    }

    @After
    public void end() {
        System.out.println("测试结束!!");
    }
}
