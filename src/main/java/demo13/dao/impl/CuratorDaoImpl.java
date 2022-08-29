package demo13.dao.impl;

import demo13.dao.CuratorDao;
import demo13.po.Curator;
import demo13.po.User;

/**
 * @author 25043
 */
public class CuratorDaoImpl extends BaseDao implements CuratorDao {
    public static final CuratorDao INSTANCE = new CuratorDaoImpl();

    private CuratorDaoImpl() {

    }

    public static CuratorDao getInstance() {
        return INSTANCE;
    }

    @Override
    public User getUser(String userName, String password) {
        String sql = "select id,userName,password,identity from user where userName=? and password=?";
        return this.queryObject(sql, User.class, userName, password);
    }

    @Override
    public Curator getCuratorById(int userId) {
        String sql = "select curatorId,name,sex,phone,email,userId from curator where userId=?";
        return this.queryObject(sql, Curator.class, userId);
    }

    @Override
    public int black(String commonStaffId, String state) {
        String sql = "update commonStaff set state=? where commonStaffId=?";
        return this.help(sql, state, commonStaffId);
    }
}
