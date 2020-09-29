package github.veikkoroc.crowd.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 统一整个项目中Ajax请求返回的结果(未来也可以用于分布式架构各个模块间调用时返回统一类型)
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 20:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    /**
     * 请求处理成功且不需要返回数据的方法
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData(){

        return new ResultEntity<E>(SUCCESS,null,null);
    }

    /**
     * 请求处理成功，且需要返回数据的方法
     * @param data  要返回的数据
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithData(E data){
        return new ResultEntity<E>(SUCCESS,null,data);
    }

    /**
     * 请求处理失败
     * @param message  失败的消息
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> failed(String message){
        return new ResultEntity<E>(FAILED,message,null);
    }


    /**
     * 用来封装当前请求处理的结果是成功还是失败
     */
    private String result;

    /**
     * 请求失败时返回的错误信息
     */
    private String message;

    /**
     * 要返回的数据
     */
    private T data;

}
