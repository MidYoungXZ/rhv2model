package cn.demo.rhv2model.service;

import cn.demo.rhv2model.constant.RhInputStatusEnum;
import cn.demo.rhv2model.constant.RhInputTypeEnum;
import cn.demo.rhv2model.constant.RhModelConstant;
import cn.demo.rhv2model.resp.RhModelRes;
import cn.demo.rhv2model.service.converter.RhInputParseConverter;
import cn.demo.rhv2model.service.converter.impl.HtmlRhReportParseImpl;
import cn.demo.rhv2model.service.converter.impl.XmlRhReportParseImpl;
import cn.demo.rhv2model.service.rule.RhModelRule;
import cn.demo.rhv2model.service.rule.impl.NanjingRhModelCodeRule;
import cn.demo.rhv2model.service.rule.impl.ZhongXinRhModelCodeRule;
import cn.demo.rhv2model.service.rhparam.RhParamProcessor;
import cn.demo.rhv2model.service.rule.impl.HaierRhModelCodeRule;
import cn.demo.rhv2model.service.rule.impl.HtmlRhModelCodeRule;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class RhGradeModelService {

    private Map<RhInputTypeEnum, RhInputParseConverter> rhInputParseConverterMap;

    private HtmlRhModelCodeRule htmlRhModelCodeRule;

    private ZhongXinRhModelCodeRule zhongXinRhModelCodeRule;

    private HaierRhModelCodeRule haierRhModelCodeRule;

    private NanjingRhModelCodeRule nanjingRhModelCodeRule;

    private RhParamProcessor rhParamProcessor;

    public RhGradeModelService() {
        rhInputParseConverterMap = new HashMap<>();
        rhInputParseConverterMap.put(RhInputTypeEnum.HTML, new HtmlRhReportParseImpl());
        rhInputParseConverterMap.put(RhInputTypeEnum.XML, new XmlRhReportParseImpl());
        rhInputParseConverterMap.put(RhInputTypeEnum.ZX_XML, new XmlRhReportParseImpl());
        rhInputParseConverterMap.put(RhInputTypeEnum.HE_XML, new XmlRhReportParseImpl());
        rhInputParseConverterMap.put(RhInputTypeEnum.NJ_XML, new XmlRhReportParseImpl());

        htmlRhModelCodeRule = new HtmlRhModelCodeRule();
        rhParamProcessor = new RhParamProcessor();
        zhongXinRhModelCodeRule = new ZhongXinRhModelCodeRule();
        haierRhModelCodeRule=new HaierRhModelCodeRule();
        nanjingRhModelCodeRule=new NanjingRhModelCodeRule();


    }

    public RhModelRes getRhModelRes(RhInputTypeEnum inputType, Object input, RhInputStatusEnum inputStatus) {
        log.info("request inputType:{}",inputType);
        Map<String, Object> rhParam = new HashMap<>();

        if (RhInputStatusEnum.NO_REPORT.equals(inputStatus)) {
            rhParam.put("HasRhReport", false);
        } else if (RhInputStatusEnum.FAIL.equals(inputStatus)) {
            return RhModelRes.builder()
                    .rigidRefuse("-1")
                    .rhGradeModel1("0")
                    .rhGradeModel2("0.000000")
                    .rhGradeModel3("0.0000")
                    .rhGradeModel4("0")
                    .version(RhModelConstant.VERSION)
                    .build();

        } else {
            rhParam = getRhParamMap(inputType, input);
        }

        //执行模型规则获取结果
        RhModelRes rhModelRes = getRhModelRule(inputType).getRhModelRes(rhParam);
        rhModelRes.setVersion(RhModelConstant.VERSION);
        rhModelRes.setInputValue(rhParam.toString());
        log.info("response inputType:{}",inputType);
        return rhModelRes;
    }

    public Map<String, Object> getRhParamMap(RhInputTypeEnum inputType, Object input) {

        //获取输入转换器
        JSONObject rhJson = getRhJsonObject(inputType, input);
        //衍生变量
        return rhParamProcessor.getRhParam(rhJson);
    }

    public JSONObject getRhJsonObject(RhInputTypeEnum inputType, Object input) {

        RhInputParseConverter inputParseConverter = getRhInputParseConverter(inputType);

        return (JSONObject) JSONObject.toJSON(inputParseConverter.parseRhReport(input));
    }

    private RhModelRule getRhModelRule(RhInputTypeEnum inputType) {
        if (RhInputTypeEnum.ZX_XML.equals(inputType)) {
            return zhongXinRhModelCodeRule;
        }

        if(RhInputTypeEnum.HE_XML.equals(inputType)){
            return haierRhModelCodeRule;
        }

        if(RhInputTypeEnum.NJ_XML.equals(inputType)){
            return nanjingRhModelCodeRule;
        }

        return htmlRhModelCodeRule;
    }

    private RhInputParseConverter getRhInputParseConverter(RhInputTypeEnum inputType) {
        RhInputParseConverter inputParseConverter = rhInputParseConverterMap.get(inputType);

        if (inputParseConverter != null) {
            return inputParseConverter;
        }

        throw new RuntimeException("不支持的输入类型:" + inputType);
    }
}
