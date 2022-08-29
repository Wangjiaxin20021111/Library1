package demo13.Service;

import demo13.controller.Result;

/**
 * @author 25043
 */
public interface CuratorService {
    /**
     * 馆长登录
     * 用户名@param userName
     * 密码@param password
     * 登录消息集合@return
     */
    Result login(String userName, String password);
}
