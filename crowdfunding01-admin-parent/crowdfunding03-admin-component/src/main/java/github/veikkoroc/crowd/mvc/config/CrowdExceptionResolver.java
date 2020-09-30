package github.veikkoroc.crowd.mvc.config;

import com.google.gson.Gson;
import github.veikkoroc.crowd.constant.CrowdConstant;
import github.veikkoroc.crowd.exception.LoginFailedException;
import github.veikkoroc.crowd.util.CrowdUtil;
import github.veikkoroc.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ControllerAdvice 这是一个增强的 Controller。使用这个 Controller ，可以实现三个方面的功能：
 *
 *   全局异常处理
 *   全局数据绑定
 *   全局数据预处理
 *
 *   这里表示单签类是基于注解的异常处理类
 *
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/30 8:55
 */

@ControllerAdvice
public class CrowdExceptionResolver {

    /**
     * 异常处理公共方法
     *
     * @param viewName
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    private ModelAndView commonResolve(
            //viewName异常处理完成要去的页面
            String viewName,Exception e,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1、判断当前请求类型
        boolean requestType = CrowdUtil.judgeRequestType(request);

        //2、如果是Ajax请求
        if (requestType){
            //3.创建RequestEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(e.getMessage());
            //4、创建gson转成Json
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);

            //5、把json字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            //6、由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;

        }
        //7、如果不是ajax请求,则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //8、将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,e);
        //9、设置对应视图名称
        modelAndView.setViewName(viewName);
        //10、返回ModelAndView对象

        return modelAndView;
    }

    public ModelAndView resolveIOException(IOException e,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        return commonResolve(viewName,e,request,response);
    }



    /**
     * 映射空指针异常
     *
     * @ExceptionHandler将一个具体的异常类型和一个方法关联起来
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            //实际捕获的异常
            NullPointerException e,
            //当前请求的对象
            HttpServletRequest request,
            //当前返回对象
            HttpServletResponse response) throws IOException {

        //1、判断当前请求类型
        boolean requestType = CrowdUtil.judgeRequestType(request);

        //2、如果是Ajax请求
        if (requestType){
            //3.创建RequestEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(e.getMessage());
            //4、创建gson转成Json
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);

            //5、把json字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            //6、由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;

        }
        //7、如果不是ajax请求,则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //8、将Exception对象存入模型
        modelAndView.addObject("exception",e);
        //9、设置对应视图名称
        modelAndView.setViewName("system-error");
        //10、返回ModelAndView对象

        return modelAndView;
    }


    /**
     * 映射数学异常
     * @return
     */
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolveMathException(ArithmeticException e,HttpServletRequest request,HttpServletResponse response) throws IOException {

        //1、判断当前请求类型
        boolean requestType = CrowdUtil.judgeRequestType(request);

        //2、如果是Ajax请求
        if (requestType){
            //3.创建RequestEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(e.getMessage());
            //4、创建gson转成Json
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);

            //5、把json字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            //6、由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;

        }
        //7、如果不是ajax请求,则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        //8、将Exception对象存入模型
        modelAndView.addObject("exception",e);
        //9、设置对应视图名称
        modelAndView.setViewName("system-error");
        //10、返回ModelAndView对象

        return modelAndView;


    }
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException e,HttpServletRequest request,HttpServletResponse response) throws IOException {

        String viewName = "admin-login";


        return commonResolve(viewName,e,request,response);

    }

}
