package github.veikkoroc.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Veikko Roc
 * @version 1.0
 * @date 2020/9/29 19:22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamData {
    private List<Integer> array;
}
