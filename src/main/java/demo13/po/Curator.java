package demo13.po;

/**
 * @author 25043
 */
public class Curator {
    /**
     * 员工id
     */
    private int curatorId;
    /**
     * 员工名称
     */
    private String name;
    /**
     * 员工性别
     */
    private int sex;
    /**
     * 员工电话
     */
    private String phone;
    /**
     * 员工邮箱
     */
    private String email;
    /**
     * 员工id
     */
    private int userId;

    public int getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(int curatorId) {
        this.curatorId = curatorId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Curator{" + "curatorId=" + curatorId + ", name='" + name + '\'' + ", sex=" + sex + ", phone='" + phone + '\'' + ", email='" + email + '\'' + ", userId=" + userId + '}';
    }
}
