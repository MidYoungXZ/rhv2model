package cn.demo.rhv2model.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CrosRhModelReq {

    private String pbocQueryNo;

    private String modelName;

    private String extData;

    private String pbcData;

    private String resultCode;
}
