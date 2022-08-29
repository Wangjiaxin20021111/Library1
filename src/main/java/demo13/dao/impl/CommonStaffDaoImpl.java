package demo13.dao.impl;

import demo13.dao.CommonStaffDao;
import demo13.po.CommonStaff;

import java.util.List;

/**
 * @author 25043
 */
public class CommonStaffDaoImpl extends BaseDao implements CommonStaffDao {
    public static final CommonStaffDao INSTANCE = new CommonStaffDaoImpl();

    private CommonStaffDaoImpl() {

    }

    public static CommonStaffDao getInstance() {
        return INSTANCE;
    }

    @Override
    public CommonStaff getConmmonStaffById(int userId) {
        String sql = "select commonStaffId,name,sex,telephone,userId,state from commonStaff where userId=?";
        return this.queryObject(sql, CommonStaff.class, userId);
    }

    @Override
    public List<CommonStaff> selectAll() {
        String sql = "select commonStaffId,name,sex,telephone,userId,state from commonStaff";
        return this.queryList(sql, CommonStaff.class);
    }
}
