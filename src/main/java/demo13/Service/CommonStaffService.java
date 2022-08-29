package demo13.Service;

import demo13.controller.Result;

/**
 * @author 25043
 */
public interface CommonStaffService {
    /**
     * 普通员工登录
     * 用户名@param userName
     * 密码@param password
     * 消息提示@return
     */
    Result commonStaffLogin(String userName, String password);

    /**
     * 查询所有
     * 查询集合@return
     */
    Result selectAll();

    /**
     * 查询的集合
     * 普通员工的id@param commonStaffId
     * 状态@param state
     * 查询集合@return
     */
    Result selectAndCheck(String commonStaffId, String state);
}
