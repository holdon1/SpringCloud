package org.example;
import lombok.Data;

@Data
public class Result<T> {
    private T data;
    private int code;
    private String message;

    public Result() {}
    public Result(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    /**
     * 成功返回，不包含数据
     * @return
     * @param <T>
     */
    public static<T> Result<T> success(){
        return new Result<>(null,200,"操作成功");
    }

    /**
     * 成功返回，包含数据
     * @param data
     * @return
     * @param <T>
     */
    public static<T> Result<T> success(T data){
        return new Result<>(data,200,"操作成功");
    }

    /**
     * 返回具体错误
     * @param code 状态码
     * @param message 错误信息
     * @return
     * @param <T>
     */
    public static<T> Result<T> error(int code, String message){
        return new Result<>(null,code,message);
    }

    /**
     * 默认返回信息
     * @param message
     * @return
     * @param <T>
     */
    public static<T> Result<T> error(String message){
        return new Result<>(null,500,message);
    }

}
