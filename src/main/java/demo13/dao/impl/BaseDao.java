package demo13.dao.impl;


import demo13.Exception.DaoException;
import demo13.util.JavaDataBaseConnectUtil;
import demo13.util.LoggerUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;


/**对于增删改查方法的统一封装
 * @author 25043
 */
public class BaseDao {

    private static final Logger logger = LoggerUtil.getLogger();

    /**
     * 查询集合
     * sql语句@param sql
     * 传入的二进制字节码@param clazz
     * 参数数组@param args
     * 泛型@param <T>
     * 集合@return
     */
    public <T> ArrayList<T> queryList(String sql, Class<T> clazz, Object... args) {
        //创建泛型集合
        ArrayList<T> list = new ArrayList<>();
        ResultSet resultSet = getResultSet(sql, args);
        try {
            //获取结果集的信息
            //1、assert <boolean表达式> 如果<boolean表达式>为true，则程序继续执行。 如果为false，则程序抛出AssertionError，并终止执行。
            assert resultSet != null;
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取列数
            int colum = resultSetMetaData.getColumnCount();
            //遍历结果集
            while (resultSet.next()) {
                //通过字节码.getDeclaredConstructor()来获取构造器，并且通过newInstance()方法获取到一个对象
                T t = clazz.getDeclaredConstructor().newInstance();
                //为当前的对象属性赋值
                workForField(colum, t, resultSetMetaData, clazz, resultSet);
                //赋值完成之后添加到对应的集合当中
                list.add(t);
            }
            return list;
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException | AssertionError e) {
            e.printStackTrace();
            logger.warning("executeUpdate方法预编译sql语句异常或者" + e);
            throw new DaoException("executeUpdate方法预编译sql语句异常", e);
        } finally {
            JavaDataBaseConnectUtil.close(resultSet, null, null);
        }
    }

    /**
     * 为属性赋值
     * 列数@param colum
     * 被赋值的对象@param t
     * 获取结果集信息的类@param resultSetMetaData
     * 被赋值对象的二进制字节码对象@param clazz
     * 结果集@param resultSet
     * 泛型集合@param <T>
     * 异常@throws SQLException
     * SQL异常@throws NoSuchFieldException
     * 反射异常@throws IllegalAccessException
     */
    private <T> void workForField(int colum, T t, ResultSetMetaData resultSetMetaData, Class<T> clazz, ResultSet resultSet) throws SQLException, NoSuchFieldException, IllegalAccessException {


        for (int i = 0; i < colum; i++) {
            //获取列名称
            String columName = resultSetMetaData.getColumnLabel(i + 1);
            //获取列的属性columnValue值为列名，数据库的列名称，columnValue为数据库表中的数据
            Object columnValue = resultSet.getObject(columName);
            //如果没有此属性，会报异常：noSuchFieldException
            Field field = clazz.getDeclaredField(columName);
            //无视属性修饰符
            field.setAccessible(true);
            //设置属性值,t代表对应的对象,columValue代表对应的值
            field.set(t, columnValue);
        }
    }

    /**
     * 查询单个对象
     * 查询的sql语句@param sql
     * 类的二进制字节码文件@param clazz
     * 数组@param args
     * 泛型@param <T>
     * 被查询的对象@return
     */
    public <T> T queryObject(String sql, Class<T> clazz, Object... args) {
        ResultSet resultSet = getResultSet(sql, args);
        //创建泛型集合
        try {
            //获取结果集的信息
            assert resultSet != null;
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取列数
            int colum = resultSetMetaData.getColumnCount();
            //遍历结果集
            T t = null;
            while (resultSet.next()) {
                t = clazz.getDeclaredConstructor().newInstance();
                workForField(colum, t, resultSetMetaData, clazz, resultSet);
            }
            return t;
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException | AssertionError e) {
            e.printStackTrace();
            logger.warning("executeUpdate方法预编译sql语句异常：" + e);
            throw new DaoException("Dao层发生了sql语句异常", e);
        } finally {
            JavaDataBaseConnectUtil.close(resultSet, null, null);
        }
    }

    /**
     * 封装增删改的方法
     * sql语句@param sql
     * 可变参数数组@param args
     * 执行的行数@return
     */
    public int help(String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        Connection connection = JavaDataBaseConnectUtil.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            int i = 1;
            if (args.length > 0) {
                for (Object object : args) {
                    preparedStatement.setObject(i, object);
                    i++;
                }
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warning("executeUpdate方法预编译sql语句异常：" + e);
            throw new DaoException("Dao层发生了sql语句异常", e);
        } finally {
            JavaDataBaseConnectUtil.close(null, preparedStatement, connection);
        }
    }

    /**
     * 获取结果集
     * sql语句@param sql
     * 参数数组@param args
     * 结果集@return
     */
    private ResultSet getResultSet(String sql, Object... args) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            int i = 1;
            connection = JavaDataBaseConnectUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //遍历数组
            if (args.length > 0) {
                for (Object object : args) {
                    preparedStatement.setObject(i, object);
                    i++;
                }
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warning("executeUpdate方法预编译sql语句异常：" + e);
            throw new DaoException("Dao层发生了sql语句异常", e);
        }
    }
}
