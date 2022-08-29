package Test;

import demo13.Constant.Code;
import demo13.Service.CuratorService;
import demo13.Service.impl.CuratorServiceImpl;
import demo13.controller.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * 测试馆长登录的接口
 */
public class TestCuratorService {

    /**
     * 待测试的Service
     */
    private CuratorService curatorService;

    @Before
    public void initTest(){
        curatorService=CuratorServiceImpl.getInstance();
    }
    /**
     * 测试馆长登录
     */
    @Test
    public void testCuratorLogin(){

        String userName="JIM";

        String password="20021111aA#";
        //测试登录成功的情况
        Result result=curatorService.login(userName,password);
        //输出结果
        System.out.println("测试的数据:"+result.getData());
        Assert.assertEquals(Code.GET_OK,result.getCode());
        //测试登录失败
        String userName1="JIM";
        String password1="123456";
        //测试登录
        Result result1=curatorService.login(userName1,password1);
        Assert.assertEquals(Code.GET_ERR,result1.getCode());

    }
    @After
    public void end(){
        System.out.println("测试结束!!");
    }
}
