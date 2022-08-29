package demo13.controller;

import demo13.Constant.Code;
import demo13.Exception.DaoException;
import demo13.util.LoggerUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @author 25043
 */
public class BaseServlet extends HttpServlet {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1，获取请求路径，整个的请求路径
        String uri = req.getRequestURI();
        //2,获取最后一段路径，截取方法名称
        int index = uri.lastIndexOf('/');
        //从斜杠往后的都是方法名称
        String methodName = uri.substring(index + 1);
        //this，谁调用我（this）所在方法，即：service方法，我代表谁，即：this代表谁
        //子类servlet由于继承了父类的service方法，因此在子类被执行时候，代表子类对象
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            //获取当前类以及其父类中的所有public修饰的方法，通过方法名称，找到对应的方法，接着传入类的非实例化对象
            //获取到一个方法：方法名称，参数列表：req,resp，方法参数类的对象
            //通过反射，获取方法对象，才能执行方法，方法名称，参数列表，通过方法名称，参数列表获取到对应的方法
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行对象的目标方法，this代表baseServlet的子类对象，返回值为方法的返回值
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | DaoException e) {
            logger.warning("BaseServlet发生异常:" + e );
            //自定义状态错误的页面
            req.getRequestDispatcher("/errState.html").forward(req,resp);
        }
    }
}
