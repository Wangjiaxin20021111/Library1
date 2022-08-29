package Test;


import demo13.util.LoggerUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

public class LoggerTest {

    private Logger logger;

    @Before
    public void DaoDemoTestBefore(){
        logger=LoggerUtil.getLogger();
    }
    @Test
    public void test1() {

        //测试是否获取到logger对象
        System.out.println(logger);
        //测试运行效果
        logger.warning("测试LoggerUtil");
        logger.info("测试loggerInfo");
        //测试等级
        System.out.println(logger.getLevel());
    }
    @After
    public void end(){
        System.out.println("测试结束!!");
    }
}