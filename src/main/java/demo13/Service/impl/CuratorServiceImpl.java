package demo13.Service.impl;

import demo13.Service.CuratorService;
import demo13.Constant.Code;
import demo13.controller.Result;
import demo13.dao.CuratorDao;
import demo13.dao.impl.CuratorDaoImpl;
import demo13.po.Curator;
import demo13.po.User;

import static demo13.Constant.ServiceConstant.CURATOR_IDENTITY;

/**
 * @author 25043
 */
public class CuratorServiceImpl implements CuratorService {
    private static final CuratorServiceImpl INSTANCE = new CuratorServiceImpl();

    private CuratorServiceImpl() {
    }

    public static CuratorServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Result login(String userName, String password) {
        //处理馆长登录的业务
        CuratorDao curatorDao = CuratorDaoImpl.getInstance();
        //获取馆长对象
        User user = curatorDao.getUser(userName, password);
        if (user != null&&user.getIdentity()==CURATOR_IDENTITY) {
            int userId = user.getId();
            Curator curator = curatorDao.getCuratorById(userId);
            user.setCurator(curator);
            String msg = "恭喜你，馆长，登录成功!";
            return new Result(Code.GET_OK, user.getUserName(), msg);
        } else {
            //密码不正确的提示
            return new Result(Code.GET_ERR, null, "密码不正确");
        }
    }

}
