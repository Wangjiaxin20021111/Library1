package Test;

import demo13.Constant.Code;
import demo13.Service.CommonStaffService;
import demo13.Service.impl.CommonStaffServiceImpl;
import demo13.controller.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * 测试CommonStaffService这一个类里面的方法
 */
public class TestCommonStaffService {

    /**
     * 普通员工的service
     */
    private CommonStaffService commonStaffService;

    @Before
    public void initService() {
        commonStaffService = CommonStaffServiceImpl.getInstance();
    }

    /**
     * 测试普通员工登录的接口
     */
    @Test
    public void testCommonStaffLogin() {
        String userName = "JACK";
        String password = "0607101321a";
        //校验结果
        Result result = commonStaffService.commonStaffLogin(userName, password);
        //测试登录成功但是账号被封禁的情况
        Assert.assertEquals(Code.GET_ERR, result.getCode());
        //测试登录失败的情况
        String falseUserName = "JACK";
        String falsePassword = "123456789";
        //校验结果
        Result result1 = commonStaffService.commonStaffLogin(falseUserName, falsePassword);
        Assert.assertEquals(Code.GET_WRONG, result1.getCode());
    }

    /**
     * 测试查询所有员工的接口
     */
    @Test
    public void testSelectAll() {
        Result result = commonStaffService.selectAll();
        Assert.assertEquals(Code.GET_OK,result.getCode());
    }

    /**
     * 查询拉黑员工的方法
     */
    @Test
    public void testBlackTheCommonStaff() {
        Result result = commonStaffService.selectAll();
        //显示所有员工
        System.out.println(result.getData());
        //测试拉黑，解封的方法
        String commonStaffId = "1";
        String state = "0";
        Result result1 = commonStaffService.selectAndCheck(commonStaffId, state);
        Assert.assertEquals(Code.UPDATE_OK, result1.getCode());
        String commonStaffId2 = "1";
        String state2 = "1";
        Result result2 = commonStaffService.selectAndCheck(commonStaffId2, state2);
        Assert.assertEquals(Code.UPDATE_OK, result2.getCode());
    }

    @After
    public void end() {
        System.out.println("测试结束!!");
    }
}
