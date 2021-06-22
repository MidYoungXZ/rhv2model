package cn.demo.rhv2model.service;

import cn.demo.rhv2model.constant.RhInputStatusEnum;
import cn.demo.rhv2model.constant.RhInputTypeEnum;
import cn.demo.rhv2model.req.CrosRhModelReq;
import cn.demo.rhv2model.req.RhModelReq;
import cn.demo.rhv2model.resp.CrosRhModelRes;
import cn.demo.rhv2model.resp.RhGradeModelResp;
import cn.demo.rhv2model.resp.RhModelRes;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class RhModelService {

    private RhGradeModelService rhGradeModelService;

    public RhModelService() {
        rhGradeModelService = new RhGradeModelService();
    }

    public RhModelRes getRhModelRes(RhModelReq rhModelReq) {
        try {
            return rhGradeModelService.getRhModelRes(
                    rhModelReq.getInputType(), rhModelReq.getInput(), rhModelReq.getInputStatus());
        } catch (Exception e) {
            log.error("获取模型结果出错:" + JSONObject.toJSONString(rhModelReq), e);
            throw e;
        }
    }

    public RhGradeModelResp getRhGradeModelResp(RhModelReq rhModelReq) {
        try {
            RhModelRes rhModelResRes = rhGradeModelService.getRhModelRes(
                    rhModelReq.getInputType(), rhModelReq.getInput(), rhModelReq.getInputStatus());

            return RhGradeModelResp.builder()
                    .code("SUCCESS")
                    .rhModelRes(rhModelResRes)
                    .build();
        } catch (Exception e) {
            log.error("获取模型结果出错:" , e);

            return RhGradeModelResp.builder()
                    .code("FAIL")
                    .build();
        }
    }

    public CrosRhModelRes getCrosRhModelRes(CrosRhModelReq crosRhModelReq) {
        try {

            Base64.Decoder decoder = Base64.getDecoder();

            RhModelReq rhModelReq = RhModelReq.builder()
                    .inputType(RhInputTypeEnum.XML)
                    .input(new String(decoder.decode(crosRhModelReq.getPbcData()
                            .getBytes("UTF-8")), "UTF-8"))
                    .inputStatus("1".equals(crosRhModelReq.getResultCode()) ?
                            RhInputStatusEnum.SUCCESS : RhInputStatusEnum.NO_REPORT)
                    .build();

            RhModelRes rhModelResRes = rhGradeModelService.getRhModelRes(
                    rhModelReq.getInputType(), rhModelReq.getInput(), rhModelReq.getInputStatus());

            return CrosRhModelRes.builder()
                    .pbocQueryNo(crosRhModelReq.getPbocQueryNo())
                    .code("S000")
                    .data(JSONObject.toJSONString(rhModelResRes))
                    .build();
        } catch (Exception e) {
            log.error("获取模型结果出错:" + JSONObject.toJSONString(crosRhModelReq), e);
            return CrosRhModelRes.builder()
                    .pbocQueryNo(crosRhModelReq.getPbocQueryNo())
                    .code("F000")
                    .msg("系统忙不过来了")
                    .build();
        }
    }
}
