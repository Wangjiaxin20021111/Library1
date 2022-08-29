package demo13.controller;

/**
 * 返回前端对象的统一封装
 *
 * @author 25043
 */
public class Result {
    /**
     * 此数据为返回前端的数据data，可能是一个实体类，也有可能是一个list,map
     */
    private Object data;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 简要的消息提示
     */
    private String message;

    public Result(Integer code, Object data, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" + "data=" + data + ", code=" + code + ", message='" + message + '\'' + '}';
    }
}
