package github.veikkoroc.crowd.util;

import github.veikkoroc.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/30 8:32
 */
public class CrowdUtil {
    /**
     * 判断当前是否是ajax请求
     * @param request
     * @return  true 是ajax请求
     *          false 不是ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request){

        // 1.获取请求消息头
        String accept = request.getHeader("Accept");
        String header = request.getHeader("X-Request-With");


        return (accept!=null && accept.contains("application/json")) || (header!=null && header.equals("XMLHttpRequest"));
    }

    /**
     * MD5加密算法
     *
     * @param source 传入的字符串
     * @return 加密后的字符串
     */
    public static String md5(String source){
        //1 .判断source是否有效
        if (source == null || source.length() == 0){
            // 2、如果不是有效字符串，抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }


        try {
            // 3、获取MessageDigest对象,JDK提供的 MD 加密算法 :Digest(消化)
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            // 5.执行加密
            byte[] output = messageDigest.digest(input);

            // 6.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7.按照16进制将BigInteger的值转为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }


}
