package demo13.po;

/**
 * @author 25043
 */
public class CommonStaff {
    /**
     * 普通员工id
     */
    private int commonStaffId;
    /**
     * 普通员工名称
     */
    private String name;
    /**
     * 普通员工性别
     */
    private int sex;
    /**
     * 普通员工电话
     */
    private String telephone;
    /**
     * 普通员工的对应用户id
     */
    private int userId;
    /**
     * 当前账号状态
     */
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCommonStaffId() {
        return commonStaffId;
    }

    public void setCommonStaffId(int commonStaffId) {
        this.commonStaffId = commonStaffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CommonStaff{" +
                "commonStaffId=" + commonStaffId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", telephone='" + telephone + '\'' +
                ", userId=" + userId +
                '}';
    }
}
