package cn.demo.rhv2model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CrosRhModelRes {

    private String pbocQueryNo;

    private String code;

    private String data;

    private String msg;
}
