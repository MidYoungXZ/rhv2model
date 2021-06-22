package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModelAge implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_age";

    @Override
    public Object apply(JSONObject jsonObject) {
        String result = "-999";

        String str = jsonObject.getString("crt2_p_identification");
        if(StringUtils.isBlank(str)){
            return result;
        }

        JSONObject identification = JSON.parseObject(str);
        String certtype = identification.getString("certtype");

        if("10".equals(certtype)){
            String certno = identification.getString("certno");
            if(StringUtils.isBlank(certno)||certno.length()!=18){
                return result;
            }
            String birth = certno.substring(6,12);

            try {
                Date birthDay = str2Date(birth);
                return String.valueOf(getAgeByBirth(birthDay));
            } catch (ParseException e) {
               return result;
            }
        }
        return result;
    }


    private  Date str2Date(String str) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.parse(str);
    }

    private  int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }


    @Override
    public String getParamKey() {
        return resultKey;
    }
}
