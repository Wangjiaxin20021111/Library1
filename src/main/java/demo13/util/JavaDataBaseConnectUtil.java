package demo13.util;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 数据库连接类
 *
 * @author 25043
 */
public class JavaDataBaseConnectUtil {
    private static final Logger logger = LoggerUtil.getLogger();
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties prop = new Properties();
        try {
            //获取外部文件"jdbc.properties"的资源流
            prop.load(JavaDataBaseConnectUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            //从资源流里获取各个属性的值
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
        } catch (IOException e1) {
            e1.printStackTrace();
            logger.warning("配置文件加载异常:" + e1);
        }
        try {
            //加载数据库驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.warning("mysql驱动异常"+e);
        }
    }

    /**
     * get链接方法
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //获取数据库链接
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            e.printStackTrace();
            logger.warning("连接获取异常:" + e);
        }
        return conn;
    }

    /**
     * 关闭流的方法
     * 结果集@param res
     * 预编译对象@param pst
     * 连接@param conn
     */
    public static void close(ResultSet res, PreparedStatement pst, Connection conn) {
        if (null != res) {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.warning("连接关闭异常:" + e);
            }
        }
        if (null != pst) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.warning("连接关闭异常:" + e);
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.warning("连接关闭异常:" + e);
            }
        }
    }
}
