package github.veikkoroc.test;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 20:02
 */
public class PersonTest {
    public static void main(String[] args) {
        Person person1 = new Person();
        person1.setName("迪丽热巴 ");
        person1.setAge(18);
        System.out.println(person1);//包含get、set、无参构造、toString
        //Person person2 = new Person("古力娜扎",20);
    }
}
