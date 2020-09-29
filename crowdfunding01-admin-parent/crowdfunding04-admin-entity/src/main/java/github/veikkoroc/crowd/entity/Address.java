package github.veikkoroc.crowd.entity;

import lombok.*;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 19:52
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    private String province;
    private String city;
    private String street;


}
