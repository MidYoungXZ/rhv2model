package cn.demo.rhv2model.req;

import cn.demo.rhv2model.constant.RhInputStatusEnum;
import cn.demo.rhv2model.constant.RhInputTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RhModelReq {

    private RhInputTypeEnum inputType;

    private Object input;

    private RhInputStatusEnum inputStatus;
}
