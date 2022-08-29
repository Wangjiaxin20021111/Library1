package demo13.po;

/**
 * @author 25043
 */
public class User {
    /**
     * 普通用户
     */
    private int id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户身份
     */
    private int identity;
    /**
     * 内嵌对象普通员工
     */
    private CommonStaff commonStaff;
    /**
     * 内嵌对象馆长
     */
    private Curator curator;

    public CommonStaff getCommonStaff() {
        return commonStaff;
    }

    public Curator getCurator() {
        return curator;
    }

    public void setCurator(Curator curator) {
        this.curator = curator;
    }

    public void setCommonStaff(CommonStaff commonStaff) {
        this.commonStaff = commonStaff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", identity=" + identity +
                '}';
    }
}
