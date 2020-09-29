package github.veikkoroc.crowd.entity;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 19:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private Integer stuId;
    private String stuName;
    private Address address;
    private List<Subject> subjects;
    private Map<String,String> map;
}
