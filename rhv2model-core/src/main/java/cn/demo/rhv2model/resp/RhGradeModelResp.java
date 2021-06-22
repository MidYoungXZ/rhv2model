package cn.demo.rhv2model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RhGradeModelResp {

    //状态码：SUCCESS、FAIL；返回FAIL需要重试
    private String code;

    private RhModelRes rhModelRes;
}

