package github.veikkoroc.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/10/3 22:09
 */
@Slf4j
public class Test01 {
    public static void main(String[] args) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (int i = 0; i < 100; i++) {
            count1 = count1++;
            count2 = ++count2;
            count3++;
        }
        System.out.println();
        System.out.println("count1:"+ count1);
        System.out.println("count2:"+ count2);
        System.out.println("count3:"+ count3);
    }
}
