package demo13.Service.impl;


import demo13.Service.CommonStaffService;
import demo13.Constant.Code;
import demo13.controller.Result;
import demo13.dao.CommonStaffDao;
import demo13.dao.CuratorDao;
import demo13.dao.impl.CommonStaffDaoImpl;
import demo13.dao.impl.CuratorDaoImpl;
import demo13.po.CommonStaff;
import demo13.po.User;

import java.util.List;
import java.util.Objects;

import static demo13.Constant.ServiceConstant.*;

/**
 * @author 25043
 */
public class CommonStaffServiceImpl implements CommonStaffService {
    private static final CommonStaffServiceImpl INSTANCE = new CommonStaffServiceImpl();

    private CommonStaffServiceImpl() {
    }

    public static CommonStaffServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Result commonStaffLogin(String userName, String password) {
        //处理普通员工登录
        CuratorDao curatorDao = CuratorDaoImpl.getInstance();
        CommonStaffDao commonStaffDao = CommonStaffDaoImpl.getInstance();
        //查询数据库当中有没有这个user
        User user = curatorDao.getUser(userName, password);
        int userId;
        if (user != null && user.getIdentity() == COMMON_STAFF_IDENTITY) {
            userId = user.getId();
            //通过id查询
            CommonStaff commonStaff = commonStaffDao.getConmmonStaffById(userId);
            user.setCommonStaff(commonStaff);
            if (commonStaff.getState() == 0) {
                return new Result(Code.GET_ERR, user, "您的账号已经被封禁");
            }
            //登录成功的提示
            String msg = "恭喜你,普通管理员，登录成功!";
            //返回一个结果集
            return new Result(Code.GET_OK, user, msg);
        } else {
            return new Result(Code.GET_WRONG, null, "密码不正确");
        }
    }

    @Override
    public Result selectAll() {
        //查询所有员工
        CommonStaffDao commonStaffDao = CommonStaffDaoImpl.getInstance();
        List<CommonStaff> curatorList = commonStaffDao.selectAll();
        return new Result(Code.GET_OK, curatorList, "查询成功!");
    }

    @Override
    public Result selectAndCheck(String commonStaffId, String state) {
        //查询员工，拉黑员工
        CuratorDao curatorDao = CuratorDaoImpl.getInstance();

        int check = curatorDao.black(commonStaffId, state);
        //获取普通员工的信息
        CommonStaffDao commonStaffDao = CommonStaffDaoImpl.getInstance();
        List<CommonStaff> curatorList = commonStaffDao.selectAll();
        String msg;
        if (check == 1 && Objects.equals(state,USER_NOT_BLACK_STATE )) {
            msg = "解封成功";
            return new Result(Code.UPDATE_OK, curatorList, msg);
        } else if (check == 1 && Objects.equals(state, USER_BLACK_STATE)) {
            msg = "拉黑成功";
            return new Result(Code.UPDATE_OK, curatorList, msg);
        } else {
            msg = "操作失败";
            return new Result(Code.UPDATE_OK, curatorList, msg);
        }
    }
}
