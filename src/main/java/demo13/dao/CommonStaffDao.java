package demo13.dao;

import demo13.po.CommonStaff;

import java.util.List;

/**
 * @author 25043
 */
public interface CommonStaffDao {
    /**
     * 通过id查询普通员工
     * 普通员工的id@param userId
     * 普通员工对象@return
     */
    CommonStaff getConmmonStaffById(int userId);

    /**
     * 查询所有
     * 普通员工集合@return
     */
    List<CommonStaff> selectAll();
}
