package cn.demo.rhv2model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RhModelRes {

    //拒绝原因
    private String rigidRefuse;

    //收入评分
    private String rhGradeModel1;

    //资格评分
    private String rhGradeModel2;

    //负债评分
    private String rhGradeModel3;

    //白户评分
    private String rhGradeModel4;

    //版本
    private String version;

    private String inputValue;
}
