package demo13.dao;


import demo13.po.Curator;
import demo13.po.User;

/**
 * @author 25043
 */
public interface CuratorDao {
    /**
     * 查询员工
     * 用户名@param userName
     * 密码@param password
     * 对象@return
     */
    User getUser(String userName, String password);

    /**
     * 确定馆长对象
     * 馆长id@param userId
     * 馆长@return
     */
    Curator getCuratorById(int userId);

    /**
     * 拉黑
     * 被拉黑员工@param commonStaffId
     * 修改状态@param state
     * 拉黑@return
     */
    int black(String commonStaffId, String state);
}
