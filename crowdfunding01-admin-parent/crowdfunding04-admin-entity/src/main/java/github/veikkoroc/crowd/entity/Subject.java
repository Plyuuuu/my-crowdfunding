package github.veikkoroc.crowd.entity;

import lombok.*;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 19:54
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Subject {
    private String subjectName;
    private Integer subjectScore;
}
