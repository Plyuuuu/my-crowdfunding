package github.veikkoroc.crowd.test;

import github.veikkoroc.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/30 16:34
 */
public class CrowdUtilTest {
    @Test
    public void testMD5(){
        System.out.println(CrowdUtil.md5("123123"));
    }
}
