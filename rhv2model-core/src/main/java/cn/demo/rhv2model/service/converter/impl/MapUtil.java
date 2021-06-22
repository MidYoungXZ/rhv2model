package cn.demo.rhv2model.service.converter.impl;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program rhv2model
 * @description:
 * @author: liuyafei
 * @create: 2020/03/05 13:47
 */
@Slf4j
public class MapUtil {

    public static Map<String, String> enumMap;

    public  String[] rhTypes ;

    {
        enumMap = new HashMap<String, String>();
        String enumString = getFileContent("json/EnumJson.json");
        enumMap=(Map) JSON.parse(enumString);
        rhTypes = getFileContent("json/TypesTxt.txt").split("###");

    }



    public   String getFileContent(String filePath){
        try {
            StringBuffer stringBuffer=new StringBuffer();
            ClassPathResource fileRource = new ClassPathResource(filePath);
            InputStream is = fileRource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = null;
            while((data = br.readLine()) != null) {
                stringBuffer.append(data);
            }
            br.close();
            isr.close();
            is.close();
           return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getItemIdByItemName2(String groupId, String itemName) {

        if (groupId == null || itemName == null) {
            return null;
        }

        for (int i = 0; i < rhTypes.length; i++) {
            String[] rhtype = rhTypes[i].split("\\|");
            if (groupId.equals(rhtype[0]) && itemName.equals(rhtype[3])) {
                return rhtype[2];
            }

        }

        return null;
    }

    public Map<String, String> getEnumMapByJson() {
        return enumMap;

    }

    public static Set<String> getAmount(String str){
        Set<String> set = new HashSet<>();
        String reg = "\"-?\\d+(,-?\\d+)*\"";
        Pattern p=Pattern.compile(reg);
        Matcher m=p.matcher(str);
        while(m.find()) {
            if(m.group().indexOf(",")>-1){
                set.add(m.group());
            }

        }
        return set;

    }
    public static String filterAmount(String str){
        String result=str;
        try{
            log.info("3 date:{}",new Date());
            Set<String> amount = getAmount(str);
            log.info("3.5 date:{}",new Date());
            for(Iterator<String>   it = amount.iterator();  it.hasNext();){
                String msg=it.next().toString();
                result=result.replaceAll(msg,msg.replace(",",""));
            }
            log.info("4 date:{}",new Date());
            result = result.replace("--","");
            log.info("5 date:{}",new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }


}
