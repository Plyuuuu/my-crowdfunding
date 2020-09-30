package github.veikkoroc.crowd.mvc.handler;

import github.veikkoroc.crowd.entity.Admin;
import github.veikkoroc.crowd.entity.ParamData;
import github.veikkoroc.crowd.entity.Student;
import github.veikkoroc.crowd.service.api.AdminService;
import github.veikkoroc.crowd.util.CrowdUtil;
import github.veikkoroc.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 17:50
 */
@Slf4j
@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;


    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request){

        //判断是否是ajax请求
        boolean isAjax = CrowdUtil.judgeRequestType(request);
        log.info("======================是ajax请求?:[{}]",isAjax);

        List<Admin> all = adminService.getAll();

        modelMap.addAttribute("adminList",all);

        System.out.println(10/0);

        //创建空指针异常
        //String a =null;
        //System.err.println(a.length());

        return "target";
    }

    /**
     * 返回字符串 需要使用 @ResponseBody
     *
     * 传过来的是
     * "data":{
     *           "array":[5,8,12]
     *        },
     * @param array
     * @return
     */
    @ResponseBody
    @RequestMapping("/send/array.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){
        for (Integer integer : array) {
            System.err.println("number = "+integer);
        }
        return "success";
    }

    /**
     * 传三个数组值过来，啥不用写
     * "data":{     //要发送的请求参数
     *           "array[0]":5,
     *           "array[1]":8,
     *           "array[2]":12
     *       },
     * @param paramData
     * @return
     */
    @ResponseBody
    @RequestMapping("/send/array/two.html")
    public String testReceiveArrayTow(ParamData paramData){
        List<Integer> array = paramData.getArray();
        for (Integer integer : array) {
            System.err.println("number = "+integer);
        }
        return "success";
    }

    /**
     * 传入的是json字符串
     *
     * @param array
     * @return
     */
    @ResponseBody
    @RequestMapping("/send/array/three.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array){
        for (Integer integer : array) {
         log.info("======================number[{}]",integer);
        }
        return "Success";
    }

    /**
     * 传入的是json字符串
     *
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping("/send/compose/object.json")
    public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student,HttpServletRequest request){
        //判断是否是ajax请求
        boolean isAjax = CrowdUtil.judgeRequestType(request);
        log.info("======================是ajax请求?:[{}]",isAjax);


        System.err.println("Student:"+student);

        return ResultEntity.successWithData(student);
    }


}
