package cn.demo.rhv2model.service.converter.impl;

import cn.demo.rhv2model.domain.personal.*;
import cn.demo.rhv2model.service.converter.RhInputParseConverter;
import cn.yxzmmm.rhv2model.domain.personal.*;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class HtmlRhReportParseImpl implements RhInputParseConverter<String> {

    MapUtil mapUtil = new MapUtil();
    private Map<String, String> enumMap =  new HashMap<>();
    @Override
    public ReportMessage parseRhReport(String input) {
        String rhHtml=null;
        try{
            log.info("1 date:{}",new Date());
            rhHtml=new String(Base64.getDecoder().decode(input.getBytes("UTF-8")));
            log.info("2 date:{}",new Date());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("解析查询记录信息异常:", e);
        }
        return JSON.parseObject(MapUtil.filterAmount(JSON.toJSONString(htmljsonString(StringEscapeUtils.unescapeHtml(rhHtml)))),ReportMessage.class);
    }

    public Map<String, String> getEnumMap() {

        return mapUtil.getEnumMapByJson();
    }

    public ReportMessage htmljsonString(String htmlReport) {

        enumMap = getEnumMap();

        ReportMessage reportMessageData = new ReportMessage();
        List<EssentialInformation> essentialInformationList = new ArrayList<>();
        List<TheLatestPerformance> theLatestPerformancelist = new ArrayList<>();
        List<LastMonthly> lastMonthlyList = new ArrayList<>();
        Document doc = null;

        try {
            doc = Jsoup.parse(htmlReport, "UTF-8");
            getIdentification(doc,reportMessageData); // 报告头
            getIdentity(doc,reportMessageData); // 身份信息
            getPhone(doc,reportMessageData); // 手机号码信息
            getSpouse(doc,reportMessageData); // 婚姻信息
            getResidence(doc,reportMessageData); // 居住信息
            getProfessional(doc,reportMessageData); // 职业信息
            getScore(doc,reportMessageData); // 评分信息
            getCreditTransaction(doc,reportMessageData); // 信贷交易提示信息
            getRecourse(doc,reportMessageData); // 被追偿汇总信息
            getBadDebt(doc,reportMessageData); // 呆账汇总信息
            getBeOverdue(doc,reportMessageData); // 逾期（透支）汇总信息
            getAcyclicLoan(doc,reportMessageData); // 非循环贷款汇总信息
            getRevolvingLoan(doc,reportMessageData); // 循环额度下贷款汇总信息
            getRevolvingLoanAccount(doc,reportMessageData); // 循环贷账户汇总信息
            getDebitCard(doc,reportMessageData); // 贷记卡账户汇总信息
            getSemiCreditCard(doc,reportMessageData); // 准贷记卡账户汇总信息
            getTotalRelatedRepayment(doc,reportMessageData); // 相关还款责任汇总信息
            getArrears(doc,reportMessageData); // 后付费业务欠费信息
            getPublicInformation(doc,reportMessageData); // 公共信息概要信息
            getLastQuery(doc,reportMessageData); // 上一次查询记录信息
            getQueryRecordSummary(doc,reportMessageData); // 查询记录概要信息
            getEssentialInformationC1(doc,essentialInformationList);  //基本信息段  C1
            getEssentialInformationD1(doc,essentialInformationList); // 基本信息段 D1
            getEssentialInformationR4(doc,essentialInformationList); // 基本信息段 R4
            getEssentialInformationR1(doc,essentialInformationList); // 基本信息段 R1
            getEssentialInformationR2(doc,essentialInformationList); // 基本信息段 R2
            getEssentialInformationR3(doc,essentialInformationList); // 基本信息段 R3

            getTheLatestPerformanceC1(doc,theLatestPerformancelist);//最新表现信息 C1
            getTheLatestPerformanceD1(doc,theLatestPerformancelist); // 最新表现信息 D1
            getTheLatestPerformanceR4(doc,theLatestPerformancelist); // 最新表现信息 R4
            getTheLatestPerformanceR1(doc,theLatestPerformancelist); // 最新表现信息 R1
            getTheLatestPerformanceR2(doc,theLatestPerformancelist); // 最新表现信息 R2
            getTheLatestPerformanceR3(doc,theLatestPerformancelist); // 最新表现信息 R3

            getLastMonthlyD1(doc,lastMonthlyList); // 最近一次月度表现信息 D1
            getLastMonthlyR4(doc,lastMonthlyList); // 最近一次月度表现信息 R4
            getLastMonthlyR1(doc,lastMonthlyList); // 最近一次月度表现信息 R1
            getLastMonthlyR2(doc,lastMonthlyList); // 最近一次月度表现信息 R2
            getLastMonthlyR3(doc,lastMonthlyList); // 最近一次月度表现信息 R3
            getLastFiveYears(doc,reportMessageData); //最近5年内的历史表现信息
            getLastTwentyfourMonth(doc,reportMessageData);//最近 24 个月还款状态信息

            getSpecialTransaction(doc,reportMessageData); // 特殊交易信息
            getSpecialEvents(doc,reportMessageData);     //特殊事件说明信息
            getLargeAmountSpecial(doc,reportMessageData); // 大额专项分期信息
            getDeclarativeInfo(doc,reportMessageData); // 标注及声明信息
            getCreditAgreement(doc,reportMessageData); // 授信协议基本信息
            getRelrepayliabilities(doc,reportMessageData); // 相关还款责任信息

            getPostPaymentBusiness(doc,reportMessageData); // 后付费业务信息

            getTaxArrear(doc,reportMessageData); // 欠税记录

            getCivilJudgement(doc,reportMessageData); // 民事判决记录
            getForceExecution(doc,reportMessageData); // 强制执行记录
            getAdminPunishment(doc,reportMessageData); // 行政处罚记录
            getAccFund(doc,reportMessageData); // 住房公积金参缴记录
            getSalvation(doc,reportMessageData); // 低保救助记录
            getCompetence(doc,reportMessageData); // 执业资格记录
            getAdminAward(doc,reportMessageData); // 行政奖励记录
            getOtherAnnotations(doc,reportMessageData); // 标注及声明信息
            getQueryRecord(doc,reportMessageData); // 查询记录信息

            reportMessageData.setCrt2_p_essentialinformation(essentialInformationList);
            reportMessageData.setCrt2_p_thelatestperformance(theLatestPerformancelist);
            reportMessageData.setCrt2_p_lastmonthly(lastMonthlyList);


        } catch (Exception e) {
            throw new RuntimeException("获取输入项异常:", e);
        }
        //log.info("reportMessageData="+JSON.toJSONString(reportMessageData));
        return reportMessageData;
    }

    public void getIdentification(Document document,ReportMessage reportMessageData) {
        Element personHead = document.select("table.g-tab-bor").first();
        // 获取报告编号
        String ReportSn = personHead.selectFirst("tbody").selectFirst("tr").selectFirst("td").text().substring(5, 27);
        // 获取查询时间
        Date date = new Date();
        String Querytime = date.toString();
        // 获取报告时间
        String ReportCreateTime = personHead.select("tbody").select("tr").first().select("td").get(1).text()
                .substring(5, 24);
        // 获取被查询者姓名
        String Name = personHead.select("tbody").select("tr").get(2).select("td").get(0).text();
        // 获取被查询者证件类型
        String CerttypeDesc = personHead.select("tbody").select("tr").get(2).select("td").get(1).text();
        String Certtype = mapUtil.getItemIdByItemName2("p_certType",CerttypeDesc);
        // 获取被查询者证件号码
        String Certno = personHead.select("tbody").select("tr").get(2).select("td").get(2).text();
        // 获取查询机构
        String QueryOrgDesc = personHead.select("tbody").select("tr").get(2).select("td").get(3).text();
        String QueryOrg = mapUtil.getItemIdByItemName2("p_queryOrg",QueryOrgDesc);
        // 获取查询原因
        String ReasonCodeDesc = personHead.select("tbody").select("tr").get(2).select("td").get(4).text();
        String ReasonCode = mapUtil.getItemIdByItemName2("queryDetailReason",ReasonCodeDesc);
        Elements personHead2 = document.select("table.g-tab-bor").select(":contains(防欺诈警示)");
        // 防欺诈警示
        String AntiFraudSign = null;
        // 防欺诈联系电话
        String AntiFraudTelephone = null;
        // 防欺诈警示生效日期
        String EffectiveDate = null;
        // 防欺诈警示截止日期
        String ClosingDate = null;
        // 异议标注数目
        String DissentingAnnotation = null;
        if (personHead2.size() != 0) {
            AntiFraudSign = "1";
            AntiFraudTelephone = personHead2.select("tbody").select("tr").get(1).select("td").text().substring(19, 30);
            EffectiveDate = personHead2.select("tbody").select("tr").get(3).select("td").get(0).text();
            ClosingDate = personHead2.select("tbody").select("tr").get(3).select("td").get(1).text();
        } else {
            AntiFraudSign = "0";
        }
        Elements personHead3 = document.select("table.g-tab-bor").select(":contains(异议信息提示)");
        DissentingAnnotation = personHead3.select("tbody").select("tr").get(1).select("td").text();
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(DissentingAnnotation);
        String str = m.replaceAll("");
        // set
        Identification identification = new Identification();
        identification.setReportId(ReportSn);
        identification.setReportSN(ReportSn);
        identification.setQueryTime(Querytime);
        identification.setReportCreateTime(ReportCreateTime);
        identification.setCertno(Certno);
        identification.setName(Name);
        identification.setCerttype(Certtype);
        identification.setQueryOrg(QueryOrg);
        identification.setReasonCode(ReasonCode);
        identification.setAntiFraudSign(AntiFraudSign);
        identification.setAntiFraudTelephone(AntiFraudTelephone);
        identification.setEffectiveDate(EffectiveDate);
        identification.setClosingDate(ClosingDate);
        identification.setDissentingAnnotation(str);
        reportMessageData.setCrt2_p_identification(identification);

        log.debug("==============解析报告头 :[{}]",identification);
    }

    public void getIdentity(Document document,ReportMessage reportMessageData) {
        // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
        // 个人信息1
        Elements personIdentity = document.select("table.g-tab-bor").select("table.f-tab-nomargin")
                .select(":contains(性别)").select(":contains(学历)").select(":contains(就业状况)");
        List<Identity> identityList = new ArrayList<>();
        if(personIdentity.size()>0){
            // 性别
            String GenderDesc = personIdentity.select("tbody").select("tr").get(1).select("td").get(0).text();
            String Gender = mapUtil.getItemIdByItemName2("p_gender",GenderDesc);
            // 出生日期
            String Birthday = personIdentity.select("tbody").select("tr").get(1).select("td").get(1).text();

            //婚姻状况
            String maritalStatusDesc = personIdentity.select("tbody").select("tr").get(1).select("td").get(2).text();
            String maritalStatus = mapUtil.getItemIdByItemName2("p_maritalState",maritalStatusDesc);

            // 学历
            String EduLevelDesc = personIdentity.select("tbody").select("tr").get(1).select("td").get(3).text();
            String EduLevel = mapUtil.getItemIdByItemName2("p_eduLevel",EduLevelDesc);

            // 学位
            String EduDegreeDesc = personIdentity.select("tbody").select("tr").get(1).select("td").get(4).text();
            String EduDegree = mapUtil.getItemIdByItemName2("p_eduDegree",EduDegreeDesc);
            // 电子邮箱
            String Email = personIdentity.select("tbody").select("tr").get(1).select("td").get(7).text();
            // 国籍
            String NationalityDesc = personIdentity.select("tbody").select("tr").get(1).select("td").get(6).text();
            String Nationality = mapUtil.getItemIdByItemName2("p_nationality",NationalityDesc);
            // 就业状况
            String EmploymentStatusDesc = personIdentity.select("tbody").select("tr").get(1).select("td").get(5).text();
            String EmploymentStatus = mapUtil.getItemIdByItemName2("p_employmentStatus",EmploymentStatusDesc);
            // 个人信息2
            Elements personIdentity2 = document.select("table.g-subtab-bor").select(":contains(通讯地址)")
                    .select(":contains(户籍地址)");
            // 通讯地址
            String PostAddress = personIdentity2.select("tbody").select("tr").get(1).select("td").get(0).text();
            // 户籍地址
            String HouseholdAddress = personIdentity2.select("tbody").select("tr").get(1).select("td").get(1).text();
            // set
            Identity identity = new Identity();
            identity.setReportId(ReportId);
            identity.setGender(Gender);
            identity.setBirthday(Birthday);
            identity.setEduLevel(EduLevel);
            identity.setEduDegree(EduDegree);
            identity.setPostAddress(PostAddress);
            identity.setEmail(Email);
            identity.setNationality(Nationality);
            identity.setEmploymentStatus(EmploymentStatus);
            identity.setHouseholdAddress(HouseholdAddress);
            identity.setMaritalStatus(maritalStatus);
//			identity.setGenderDesc(GenderDesc);
//			identity.setEduLevelDesc(EduLevelDesc);
//			identity.setEduDegreeDesc(EduDegreeDesc);
//			identity.setEmploymentStatusDesc(EmploymentStatusDesc);

            identityList.add(identity);
            log.debug("==============解析身份信息：[{}]",identity);
        }else{
            log.debug("==============身份信息为空：");
        }
        reportMessageData.setCrt2_p_identity(identityList);
    }
    public void getPhone(Document document,ReportMessage reportMessageData) {

        // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
        // 手机号码信息
        Elements Phones = document.select("table.g-subtab-bor").select(":contains(手机号码)").select(":contains(信息更新日期)");
        Elements ph = Phones.select("tbody>tr");
        if (Phones.size() > 0) {
            List<PhoneInfo> phoneList = new ArrayList<>();
            // 手机个数
            String NumberMobile = Integer.toString(Phones.select("tbody > *").size() - 1);
            PhoneInfo phone = new PhoneInfo();
            for (int i = 1; i < ph.size(); i++) {
                Element Phone = ph.get(i);
                // 手机号码
                String Mobile = Phone.select("td").get(1).text();
                // 信息更新日
                String GetTime = Phone.select("td").get(2).text();
                // set
                phone.setReportId(ReportId);
                phone.setMobile(Mobile);
                phone.setNumberMobile(NumberMobile);
                phone.setGetTime(GetTime);

                phoneList.add(phone);
            }
            reportMessageData.setCrt2_p_phone(phoneList);
            log.debug("================解析手机号码信息：[{}]", phoneList);
        } else {
            log.debug("================手机号码信息为空：[{}]");
        }
    }

        public void getSpouse(Document document,ReportMessage reportMessageData) {
            // 婚姻状况
            String MaritalStatusDesc = null;
            String MaritalStatus = null;
            // 配偶姓名
            String Name = null;
            // 配偶证件类型
            String CerttypeDesc = null;
            String Certtype = null;
            // 配偶证件号码
            String Certno = null;
            // 配偶工作单位
            String Employer = null;
            // 配偶联系电话
            String TelephoneNo = null;
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 配偶信息
            Elements Spouse = document.select("table.g-tab-bor").select("table.f-tab-nomargin").select(":contains(姓名)")
                    .select(":contains(工作单位)").select(":contains(联系电话)");
            // 婚姻状况
            if (Spouse.size() != 0) {
                List<Spouse> spouseList = new ArrayList<>();
                MaritalStatusDesc = "已婚";
                MaritalStatus = mapUtil.getItemIdByItemName2("p_maritalState",MaritalStatusDesc);
                Name = Spouse.select("tbody").select("tr").get(1).select("td").get(0).text();
                CerttypeDesc = Spouse.select("tbody").select("tr").get(1).select("td").get(1).text();
                Certtype = mapUtil.getItemIdByItemName2("p_certType",CerttypeDesc);
                Certno = Spouse.select("tbody").select("tr").get(1).select("td").get(2).text();
                Employer = Spouse.select("tbody").select("tr").get(1).select("td").get(3).text();
                TelephoneNo = Spouse.select("tbody").select("tr").get(1).select("td").get(4).text();
                // set
                Spouse spouse = new Spouse();
                spouse.setReportId(ReportId);
                spouse.setMaritalStatus(MaritalStatus);
                spouse.setName(Name);
                spouse.setCerttype(Certtype);
                spouse.setCertno(Certno);
                spouse.setEmployer(Employer);
                spouse.setTelephoneNo(TelephoneNo);

                spouseList.add(spouse);

                reportMessageData.setCrt2_p_spouse(spouseList);
                log.debug("==========解析婚姻信息：[{}]",spouse);
            }else{

                List<Spouse> spouseList = new ArrayList<>();
                Spouse spouse = new Spouse();
                spouse.setMaritalStatus(reportMessageData.getCrt2_p_identity().get(0).getMaritalStatus());
                spouseList.add(spouse);

                reportMessageData.setCrt2_p_spouse(spouseList);

                log.debug("==========婚姻信息为空：[{}]");
            }
        }

        /**
         * Residence表 居住信息
         *
         * @param document
         */
        public void getResidence(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 居住信息
            Elements Residences = document.select("table.g-tab-bor").select("table.f-tab-nomargin").select(":contains(编号)")
                    .select(":contains(居住地址)").select(":contains(住宅电话)").select(":contains(居住状况)");
            Elements re = Residences.select("tbody>tr");
            if(Residences.size()>0){
                List<Residence> residenceList = new ArrayList<>();
                for (int i = 1; i < re.size(); i++) {
                    Element Residence = re.get(i);
                    // 居住地址
                    String Address = Residence.select("td").get(1).text();
                    // 居住状况
                    String ResidenceTypeDesc = Residence.select("td").get(3).text();
                    String ResidenceType = mapUtil.getItemIdByItemName2("p_residenceType",ResidenceTypeDesc);
                    // 信息更新日期
                    String GetTime = Residence.select("td").get(4).text();
                    // 住宅电话
                    String HomePhone = Residence.select("td").get(2).text();
                    // set
                    Residence residence = new Residence();
                    residence.setReportId(ReportId);
                    residence.setAddress(Address);
                    residence.setResidenceType(ResidenceType);
                    residence.setGetTime(GetTime);
                    residence.setHomePhone(HomePhone);

                    residenceList.add(residence);
                }
                reportMessageData.setCrt2_p_residence(residenceList);
                log.debug("==========解析居住信息：[{}]",residenceList);
            }else{
                log.debug("==========居住信息为空：[{}]");
            }

        }

        /**
         * Professional表 职业信息
         *
         * @param document
         */
        public void getProfessional(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 职业信息1
            Elements Professional1s = document.select("table.g-tab-bor").select("table.f-tab-nomargin")
                    .select(":contains(工作单位)").select(":contains(工作单位)").select(":contains(单位地址)").select(":contains(单位电话)")
                    .select("tbody>tr");
            // 职业信息2
            Elements Professional2s = document.select("table.g-subtab-bor").select(":contains(职业)").select(":contains(行业)")
                    .select(":contains(职务)").select(":contains(职称)").select(":contains(进入本单位年份)").select("tbody>tr");

            if(Professional1s.size()>0){
                List<Professional> professionalList = new ArrayList<>();
                for (int i = 1; i < Professional1s.size(); i++) {
                    Element Professional1 = Professional1s.get(i);
                    // 获取工作单位
                    String Employer = Professional1.select("td").get(1).text();
                    // 获取单位性质
                    String UnitPropertiesDesc = Professional1.select("td").get(2).text();
                    String UnitProperties = mapUtil.getItemIdByItemName2("p_unitProperties",UnitPropertiesDesc);
                    // 获取单位地址
                    String EmployerAddress = Professional1.select("td").get(3).text();
                    // 获取单位电话
                    String WorkTelephone = Professional1.select("td").get(4).text();
                    Element Professional2 = Professional2s.get(i);
                    // 获取职业
                    String OccupationDesc = Professional2.select("td").get(1).text();
                    String Occupation = mapUtil.getItemIdByItemName2("p_occupation",OccupationDesc);
                    // 获取行业
                    String IndustryDesc = Professional2.select("td").get(2).text();
                    String Industry = mapUtil.getItemIdByItemName2("p_industry",IndustryDesc);
                    // 职务
                    String DutyDesc = Professional2.select("td").get(3).text();
                    String Duty = mapUtil.getItemIdByItemName2("p_duty",DutyDesc);
                    // 获取职称
                    String TitleDesc = Professional2.select("td").get(4).text();
                    String Title = mapUtil.getItemIdByItemName2("p_title",TitleDesc);
                    // 获取进入本单位年份
                    String StartYear = Professional2.select("td").get(5).text();
                    // 获取信息更新日期
                    String GetTime = Professional2.select("td").get(6).text();
                    // 获取就业状况
                    //

                    Professional professional = new Professional();
                    professional.setReportId(ReportId);
                    professional.setEmployer(Employer);
                    professional.setUnitProperties(UnitProperties);
                    professional.setEmployerAddress(EmployerAddress);
                    professional.setWorkTelephone(WorkTelephone);
                    professional.setOccupation(Occupation);
                    professional.setIndustry(Industry);
                    professional.setDuty(Duty);
                    professional.setTitle(Title);
                    professional.setStartYear(StartYear);
                    professional.setGetTime(GetTime);

                    professionalList.add(professional);
                }
                reportMessageData.setCrt2_p_professional(professionalList);
                log.debug("============解析职业信息：[{}]",professionalList);
            }else{
                log.debug("============职业信息为空：[{}]");
            }

        }

        /**
         * Score表 评分信息
         *
         * @param document
         */
        public void getScore(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 获取评分信息
            Elements Scores = document.select("table.g-tab-bor").select("table.f-tab-nomargin").select(":contains(数字解读)")
                    .select(":contains(相对位置)").select(":contains(说明)").select("tbody>tr");
            if(Scores.size()>0){
                List<ScoreElements> scoreList = new ArrayList<>();
                for (int i = 1; i < Scores.size(); i++) {
                    Element Score = Scores.get(1);
                    // 数字解读
                    String DigitalInterpretation = Score.select("td").get(0).text();
                    // 相对位置
                    String RelativePosition = Score.select("td").get(1).text();
                    // 分数说明条数
                    String FractionalExplanationNumber = Integer.toString(Scores.size() - 1);
                    // 分数说明
                    String str = Scores.get(i).select("td").last().text();
                    String FractionalExplanationDesc = str.substring(str.indexOf("：") + 1, str.length());
                    String FractionalExplanation = mapUtil.getItemIdByItemName2("p_fractional_influence",FractionalExplanationDesc);
                    ScoreElements score = new ScoreElements();
                    score.setReportId(ReportId);
                    score.setDigitalInterpretation(DigitalInterpretation);
                    score.setRelativePosition(RelativePosition);
                    score.setFractionalExplanationNumber(FractionalExplanationNumber);
                    score.setFractionalExplanation(FractionalExplanation);

                    scoreList.add(score);
                }
                reportMessageData.setCrt2_p_score(scoreList);
                log.debug("============解析评分信息：[{}]",scoreList);
            }else{
                log.debug("============评分信息为空：[{}]");
            }

        }

        /**
         * CreditTransaction表 信贷交易提示信息
         *
         * @param document
         */
        public void getCreditTransaction(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 获取信贷交易提示信息
            Elements CreditTransactions = document.select("table.g-tab-bor").select("table.f-tab-nomargin")
                    .select(":contains(业务类型)").select(":contains(账户数)").select(":contains(首笔业务发放月份)").select("tbody>tr");
            String temp = "";
            if(CreditTransactions.size()>0){
                List<CreditTransaction> creditTransactionList = new ArrayList<>();
                for (int i = 1; i < CreditTransactions.size() - 2; i++) {
                    CreditTransaction creditTransaction = new CreditTransaction();
                    Element CreditTransaction = CreditTransactions.get(i);
                    // 账户数合计
                    String TotalAccountNumber = CreditTransactions.last().select("td").get(0).text();
                    // 业务类型数量
                    String BusinessTypeNumber = Integer.toString(CreditTransactions.size() - 3);
                    // 业务类型
                    String BusinessTypeDesc = CreditTransaction.select("th").last().text();
                    String BusinessType = mapUtil.getItemIdByItemName2("p_type_credit",BusinessTypeDesc);
                    // 业务大类
                    String BusinessClass = CreditTransaction.select("th[rowspan]").text();
                    if (BusinessClass.length() == 0) {
                        BusinessClass = temp;
                    }
                    temp = BusinessClass;
                    BusinessClass = mapUtil.getItemIdByItemName2("p_large_credit",BusinessClass);
                    // 账户数
                    String AccountNumber = CreditTransaction.select("td").get(0).text();
                    // 首笔业务发放月份
                    String FirstBusiness = CreditTransaction.select("td").get(1).text();
                    creditTransaction.setReportId(ReportId);
                    creditTransaction.setAccountNumber(AccountNumber);
                    creditTransaction.setBusinessTypeNumber(BusinessTypeNumber);
                    creditTransaction.setBusinessType(BusinessType);
                    creditTransaction.setBusinessClass(BusinessClass);
                    creditTransaction.setTotalAccountNumber(TotalAccountNumber);
                    creditTransaction.setFirstBusiness(FirstBusiness);

                    creditTransactionList.add(creditTransaction);
                }
                CreditTransaction creditTransaction = new CreditTransaction();
                Element CreditTransaction = CreditTransactions.get(CreditTransactions.size() - 2);
                // 账户数合计
                String TotalAccountNumber = CreditTransactions.last().select("td").get(0).text();
                // 业务类型数量
                String BusinessTypeNumber = Integer.toString(CreditTransactions.size() - 3);
                // 业务类型
                String BusinessTypeDesc = CreditTransaction.select("td").get(0).text();
                String BusinessType = mapUtil.getItemIdByItemName2("p_type_credit",BusinessTypeDesc);

                // 业务大类
                String BusinessClass = CreditTransaction.select("th").text();
                BusinessClass=mapUtil.getItemIdByItemName2("p_large_credit",BusinessClass);
                // 账户数
                String AccountNumber = CreditTransaction.select("td").get(1).text();
                // 首笔业务发放月份
                String FirstBusiness = CreditTransaction.select("td").get(2).text();
                creditTransaction.setReportId(ReportId);
                creditTransaction.setAccountNumber(AccountNumber);
                creditTransaction.setBusinessTypeNumber(BusinessTypeNumber);
                creditTransaction.setBusinessType(BusinessType);
                creditTransaction.setBusinessClass(BusinessClass);
                creditTransaction.setTotalAccountNumber(TotalAccountNumber);
                creditTransaction.setFirstBusiness(FirstBusiness);

                creditTransactionList.add(creditTransaction);
                reportMessageData.setCrt2_p_credittransaction(creditTransactionList);
                log.debug("===========解析信贷交易提示信息：[{}]",creditTransactionList);
            }else{
                log.debug("===========信贷交易提示信息为空：[{}]");
            }

        }

        /**
         * Recourse表 被追偿汇总信息
         *
         * @param document
         */
        public void getRecourse(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            Elements Recourses = document.select("table:contains(被追偿信息汇总)").select("tbody>tr");

            if(Recourses.size()>0){
                List<Recourse> recourseList = new ArrayList<>();
                for (int i = 2; i < Recourses.size() - 1; i++) {
                    // 账户数合计
                    String TotalAccountNumber = Recourses.get(Recourses.size() - 1).select("td").get(0).text();
                    // 余额合计
                    String BalanceOfBalance = Recourses.get(Recourses.size() - 1).select("td").get(1).text();
                    // 业务类型数量
                    String BusinessTypeNumber = Integer.toString(Recourses.size() - 3);
                    // 业务类型
                    String BusinessTypeDesc = Recourses.get(i).select("th").text();
                    String BusinessType = mapUtil.getItemIdByItemName2("p_recourse_summary",BusinessTypeDesc);
                    // 账户数
                    String AccountNumber = Recourses.get(i).select("td").get(0).text();
                    // 余额
                    String Balance = Recourses.get(i).select("td").get(1).text();
                    Recourse recourse = new Recourse();
                    recourse.setReportId(ReportId);
                    recourse.setTotalAccountNumber(TotalAccountNumber);
                    recourse.setBalanceOfBalance(BalanceOfBalance);
                    recourse.setBusinessTypeNumber(BusinessTypeNumber);
                    recourse.setBusinessType(BusinessType);
                    recourse.setAccountNumber(AccountNumber);
                    recourse.setBalance(Balance);

                    recourseList.add(recourse);
                }
                reportMessageData.setCrt2_p_recourse(recourseList);
                log.debug("============解析被追偿汇总信息：[{}]",recourseList);
            }else{
                log.debug("============被追偿汇总信息为空：[{}]");
            }

        }

        /**
         * BadDebt表 呆账汇总信息
         *
         * @param document
         */
        public void getBadDebt(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            Elements BadDebt = document.select("table.g-tab-bor").select("table.f-tab-nomargin")
                    .select(":contains(呆账信息汇总)");
            if(BadDebt.size()>0){
                List<BadDebt> badDebtList = new ArrayList<>();
                BadDebt badDebt = new BadDebt();
                // 账户数
                String AccountNumber = BadDebt.select("tbody>tr>td").get(0).text();
                // 余额
                String Balance = BadDebt.select("tbody>tr>td").get(1).text();

                badDebt.setReportId(ReportId);
                badDebt.setAccountNumber(AccountNumber);
                badDebt.setBalance(Balance);

                badDebtList.add(badDebt);

                reportMessageData.setCrt2_p_baddebt(badDebtList);
                log.debug("===========解析呆账汇总信息：[{}]",badDebt);
            }else{
                log.debug("===========呆账汇总信息为空：[{}]");
            }

        }

        /**
         * BeOverdue 逾期（透支）汇总信息
         *
         * @param document
         */
        public void getBeOverdue(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 逾期（透支）汇总信息
            Elements BeOverdues = document.select("table.g-tab-bor").select("table.f-tab-nomargin")
                    .select("table:contains(逾期（透支）信息汇总)").select("tbody>tr");
            if(BeOverdues.size()>0){
                List<BeOverDue> beOverdueList = new ArrayList<>();
                for (int i = 2; i < BeOverdues.size(); i++) {
                    BeOverDue beOverdue = new BeOverDue();
                    Element BeOverdue = BeOverdues.get(i);
                    // 业务类型数量
                    String BusinessTypeNumber = Integer.toString(BeOverdues.size() - 2);
                    // 业务类型
                    String BusinessTypeDesc = BeOverdue.select("th").get(0).text();
                    String BusinessType = mapUtil.getItemIdByItemName2("p_overdue_summary",BusinessTypeDesc);
                    // 账户数
                    String AccountNumber = BeOverdue.select("td").get(0).text();
                    // 月份数
                    String MonthNumber = BeOverdue.select("td").get(1).text();
                    // 单月最高逾期（透支）总额
                    String MaximumOverdue = BeOverdue.select("td").get(2).text();
                    // 最长逾期（透支）月数
                    String TheLongestOverdue = BeOverdue.select("td").get(3).text();

                    beOverdue.setReportId(ReportId);
                    beOverdue.setBusinessTypeNumber(BusinessTypeNumber);
                    beOverdue.setBusinessType(BusinessType);
                    beOverdue.setAccountNumber(AccountNumber);
                    beOverdue.setMonthNumber(MonthNumber);
                    beOverdue.setMaximumOverdue(MaximumOverdue);
                    beOverdue.setTheLongestOverdue(TheLongestOverdue);

                    beOverdueList.add(beOverdue);
                }
                reportMessageData.setCrt2_p_beoverdue(beOverdueList);
                log.debug("==============解析逾期（透支）汇总信息：[{}]",beOverdueList);
            }else{
                log.debug("==============逾期（透支）汇总信息为空：[{}]");
            }


        }

        /**
         * AcyclicLoan 非循环贷款汇总信息
         *
         * @param document
         */
        public void getAcyclicLoan(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 非循环贷款汇总信息
            Elements AcyclicLoan = document.select("table:contains(非循环贷账户信息汇总)");
            if(AcyclicLoan.size()>0){
                List<AcyclicLoan> acyclicLoanList = new ArrayList<>();
                AcyclicLoan acyclicLoan = new AcyclicLoan();
                // 管理机构（法人）数
                String ManagementAgency = AcyclicLoan.select("tbody>tr").get(2).select("td").get(0).text();
                // 账户数
                String AccountNumber = AcyclicLoan.select("tbody>tr").get(2).select("td").get(1).text();
                // 借款总额
                String TotalLoan = AcyclicLoan.select("tbody>tr").get(2).select("td").get(2).text();
                // 余额
                String Balance = AcyclicLoan.select("tbody>tr").get(2).select("td").get(3).text();
                // 最近6个月平均应还款
                String AverageRepayment = AcyclicLoan.select("tbody>tr").get(2).select("td").get(4).text();

                acyclicLoan.setReportId(ReportId);
                acyclicLoan.setManagementAgency(ManagementAgency);
                acyclicLoan.setAccountNumber(AccountNumber);
                acyclicLoan.setTotalLoan(TotalLoan);
                acyclicLoan.setBalance(Balance);
                acyclicLoan.setAverageRepayment(AverageRepayment);

                acyclicLoanList.add(acyclicLoan);
                reportMessageData.setCrt2_p_acyclicloan(acyclicLoanList);
                log.debug("=============解析非循环贷款汇总信息：[{}]",acyclicLoan);
            }else{
                log.debug("=============非循环贷款汇总信息为空：[{}]");
            }

        }

        /**
         * RevolvingLoan 循环额度下贷款汇总信息
         *
         * @param document
         */
        public void getRevolvingLoan(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 循环额度下贷款汇总信息
            Elements RevolvingLoan = document.select("table:contains(循环额度下分账户信息汇总)");
            if(RevolvingLoan.size()>0){
                List<RevolvingLoan> revolvingLoanList = new ArrayList<>();
                RevolvingLoan revolvingLoan = new RevolvingLoan();
                // 管理机构（法人）数
                String ManagementAgency = RevolvingLoan.select("tbody>tr").get(2).select("td").get(0).text();
                // 账户数
                String AccountNumber = RevolvingLoan.select("tbody>tr").get(2).select("td").get(1).text();
                // 授信总额
                String TotalCredit = RevolvingLoan.select("tbody>tr").get(2).select("td").get(2).text();
                // 余额
                String Balance = RevolvingLoan.select("tbody>tr").get(2).select("td").get(3).text();
                // 最近6个月平均应还款
                String AverageRepayment = RevolvingLoan.select("tbody>tr").get(2).select("td").get(4).text();

                revolvingLoan.setReportId(ReportId);
                revolvingLoan.setManagementAgency(ManagementAgency);
                revolvingLoan.setAccountNumber(AccountNumber);
                revolvingLoan.setTotalCredit(TotalCredit);
                revolvingLoan.setBalance(Balance);
                revolvingLoan.setAverageRepayment(AverageRepayment);

                revolvingLoanList.add(revolvingLoan);

                reportMessageData.setCrt2_p_revolvingloan(revolvingLoanList);
                log.debug("=============解析循环额度下贷款汇总信息：[{}]",revolvingLoan);
            }else{
                log.debug("=============循环额度下贷款汇总信息为空：[{}]");
            }

        }

        /**
         * RevolvingLoanAccount 循环贷账户汇总信息
         *
         * @param document
         */
        public void getRevolvingLoanAccount(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 循环贷账户汇总信息
            Elements select = document.select("table:contains(循环贷账户信息汇总)");
            if (select == null || select.size()<= 1) {
                reportMessageData.setCrt2_p_revolvingloanaccount(new ArrayList<>());
                return;
            }

            Element RevolvingLoanAccount = select.get(1);
            if(RevolvingLoanAccount!=null){
                List<RevolvingLoanAccount> revolvingLoanAccountList = new ArrayList<>();
                RevolvingLoanAccount revolvingLoanAccount = new RevolvingLoanAccount();
                // 管理机构（法人）数
                String ManagementAgency = RevolvingLoanAccount.select("tbody>tr").get(2).select("td").get(0).text();
                // 账户数
                String AccountNumber = RevolvingLoanAccount.select("tbody>tr").get(2).select("td").get(1).text();
                // 授信总额
                String TotalCredit = RevolvingLoanAccount.select("tbody>tr").get(2).select("td").get(2).text();
                // 余额
                String Balance = RevolvingLoanAccount.select("tbody>tr").get(2).select("td").get(3).text();
                // 最近6个月平均应还款
                String AverageRepayment = RevolvingLoanAccount.select("tbody>tr").get(2).select("td").get(4).text();

                revolvingLoanAccount.setReportId(ReportId);
                revolvingLoanAccount.setManagementAgency(ManagementAgency);
                revolvingLoanAccount.setAccountNumber(AccountNumber);
                revolvingLoanAccount.setTotalCredit(TotalCredit);
                revolvingLoanAccount.setBalance(Balance);
                revolvingLoanAccount.setAverageRepayment(AverageRepayment);

                revolvingLoanAccountList.add(revolvingLoanAccount);

                reportMessageData.setCrt2_p_revolvingloanaccount(revolvingLoanAccountList);
                log.debug("=============解析循环贷账户汇总信息：[{}]",revolvingLoanAccount);
            }else{
                log.debug("=============循环贷账户汇总信息为空：[{}]");
            }

        }

        /**
         * DebitCard 贷记卡账户汇总信息
         *
         * @param document
         */
        public void getDebitCard(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 贷记卡账户汇总信息
            Elements DebitCard = document.select("table:contains(贷记卡账户信息汇总)").select("table:contains(已用额度)");
            if(DebitCard.size()>0){
                List<DebitCard> debitCardList = new ArrayList<>();
                DebitCard debitCard = new DebitCard();
                // 管理机构（法人）数
                String ManagementAgency = DebitCard.select("tbody>tr").get(2).select("td").get(0).text();
                // 账户数
                String AccountNumber = DebitCard.select("tbody>tr").get(2).select("td").get(1).text();
                // 授信总额
                String TotalCredit = DebitCard.select("tbody>tr").get(2).select("td").get(2).text();
                // 单家机构最高授信额
                String HighestCredit = DebitCard.select("tbody>tr").get(2).select("td").get(3).text();
                // 单家机构最低授信额
                String MinimumCredit = DebitCard.select("tbody>tr").get(2).select("td").get(4).text();
                // 已用额度
                String AmountUsed = DebitCard.select("tbody>tr").get(2).select("td").get(5).text();
                // 最近6个月平均应还款
                String AverageRepayment = DebitCard.select("tbody>tr").get(2).select("td").get(6).text();

                debitCard.setReportId(ReportId);
                debitCard.setManagementAgency(ManagementAgency);
                debitCard.setAccountNumber(AccountNumber);
                debitCard.setTotalCredit(TotalCredit);
                debitCard.setHighestCredit(HighestCredit);
                debitCard.setMinimumCredit(MinimumCredit);
                debitCard.setAmountUsed(AmountUsed);
                debitCard.setAverageRepayment(AverageRepayment);

                debitCardList.add(debitCard);

                reportMessageData.setCrt2_p_debitcard(debitCardList);
                log.debug("=============解析贷记卡账户汇总信息：[{}]",debitCard);
            }else{
                log.debug("=============贷记卡账户汇总信息为空：[{}]");
            }

        }

        /**
         * SemiCreditCard 准贷记卡账户汇总信息
         *
         * @param document
         */
        public void getSemiCreditCard(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 准贷记卡账户汇总信息
            Elements SemiCreditCard = document.select("table:contains(贷记卡账户信息汇总)").select("table:contains(透支余额)");
            if(SemiCreditCard.size()>0){
                List<SemiCreditCard> semiCreditCardList = new ArrayList<>();
                SemiCreditCard semiCreditCard = new SemiCreditCard();
                // 发卡机构（法人）数
                String CardIssuer = SemiCreditCard.select("tbody>tr").get(2).select("td").get(0).text();
                // 账户数
                String AccountNumber = SemiCreditCard.select("tbody>tr").get(2).select("td").get(1).text();
                // 授信总额
                String TotalCredit = SemiCreditCard.select("tbody>tr").get(2).select("td").get(2).text();
                // 单家机构最高授信额
                String HighestCredit = SemiCreditCard.select("tbody>tr").get(2).select("td").get(3).text();
                // 单家机构最低授信额
                String MinimumCredit = SemiCreditCard.select("tbody>tr").get(2).select("td").get(4).text();
                // 透支余额
                String AmountUsed = SemiCreditCard.select("tbody>tr").get(2).select("td").get(5).text();
                // 最近6个月平均应还款
                String AverageRepayment = SemiCreditCard.select("tbody>tr").get(2).select("td").get(6).text();

                semiCreditCard.setReportId(ReportId);
                semiCreditCard.setCardIssuer(CardIssuer);
                semiCreditCard.setAccountNumber(AccountNumber);
                semiCreditCard.setTotalCredit(TotalCredit);
                semiCreditCard.setHighestCredit(HighestCredit);
                semiCreditCard.setMinimumCredit(MinimumCredit);
                semiCreditCard.setAmountUsed(AmountUsed);
                semiCreditCard.setAverageRepayment(AverageRepayment);

                semiCreditCardList.add(semiCreditCard);

                reportMessageData.setCrt2_p_semicreditcard(semiCreditCardList);
                log.debug("=============解析准贷记卡账户汇总信息：[{}]",semiCreditCard);
            }else{
                log.debug("=============准贷记卡账户汇总信息为空：[{}]");
            }

        }

        /**
         * TotalRelatedRepayment 相关还款责任汇总信息
         *
         * @param document
         */
        public void getTotalRelatedRepayment(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 相关还款责任汇总信息
            Elements TotalRelatedRepayment = document.select("table:contains(相关还款责任信息汇总)").select("tbody>tr");

            if(TotalRelatedRepayment.size()>0){
                List<TotalRelatedRepayment> totalRelatedRepaymentList = new ArrayList<>();
                int size = (TotalRelatedRepayment.size() - 1) / 4;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < 2; j++) {
                        TotalRelatedRepayment totalRelatedRepayment = new TotalRelatedRepayment();
                        // 相关还款责任个数
                        String RelatedRepayment = Integer.toString((TotalRelatedRepayment.size() - 1) / 4);
                        // 借款人身份类别
                        String BorrowerIdentityCategory = TotalRelatedRepayment.get(4 * i + 1).select("th").text();
                        if (BorrowerIdentityCategory.equals("为个人")) {
                            BorrowerIdentityCategory = "自然人";
                        } else {
                            BorrowerIdentityCategory = "组织机构";
                        }
                        BorrowerIdentityCategory = mapUtil.getItemIdByItemName2("entIdeCla",BorrowerIdentityCategory);
                        // 相关还款责任类型
                        String RepaymentResponsibility = TotalRelatedRepayment.get(4 * i + 2).select("th").get(j).text();
                        RepaymentResponsibility = mapUtil.getItemIdByItemName2("p_credit_repayment",RepaymentResponsibility);
                        // 账户数
                        String AccountNumber = TotalRelatedRepayment.get(4 * i + 4).select("td").get(0 + 3 * j).text();
                        // 还款责任限额
                        String RepaymentLiabilityLimit = TotalRelatedRepayment.get(4 * i + 4).select("td").get(1 + 3 * j)
                                .text();
                        // 余额
                        String Balance = TotalRelatedRepayment.get(4 * i + 4).select("td").get(2 + 3 * j).text();
                        totalRelatedRepayment.setReportId(ReportId);
                        totalRelatedRepayment.setRelatedRepayment(RelatedRepayment);
                        totalRelatedRepayment.setBorrowerIdentityCategory(BorrowerIdentityCategory);
                        totalRelatedRepayment.setRepaymentResponsibility(RepaymentResponsibility);
                        totalRelatedRepayment.setAccountNumber(AccountNumber);
                        totalRelatedRepayment.setRepaymentLiabilityLimit(RepaymentLiabilityLimit);
                        totalRelatedRepayment.setBalance(Balance);

                        totalRelatedRepaymentList.add(totalRelatedRepayment);
                    }
                }
                reportMessageData.setCrt2_p_totalrelatedrepayment(totalRelatedRepaymentList);
                log.debug("=============解析相关还款责任汇总信息：[{}]",totalRelatedRepaymentList);
            }else{
                log.debug("=============相关还款责任汇总信息为空：[{}]");
            }
        }

        /**
         * Arrears 后付费业务欠费信息
         *
         * @param document
         */
        public void getArrears(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 后付费业务欠费信息
            Elements Arrearss = document.select("table:contains(后付费业务欠费信息汇总)").select("tbody>tr");

            if(Arrearss.size()>0){
                List<Arrears> arrearsList = new ArrayList<>();
                for (int i = 2; i < Arrearss.size(); i++) {
                    Arrears arrears = new Arrears();
                    Element Arrears = Arrearss.get(i);
                    // 后付费业务类型数量
                    String TypeOfPaymentBusiness = Integer.toString(Arrearss.size() - 2);
                    // 后付费业务类型
                    String PostPaymentBusinessDesc = Arrears.select("th").text();
                    String PostPaymentBusiness = mapUtil.getItemIdByItemName2("p_postpaid",PostPaymentBusinessDesc);
                    // 欠费账户数
                    String ArrearsAccount = Arrears.select("td").get(0).text();
                    // 欠费金额
                    String AmountOfArrears = Arrears.select("td").get(1).text();
                    arrears.setReportId(ReportId);
                    arrears.setTypeOfPaymentBusiness(TypeOfPaymentBusiness);
                    arrears.setPostPaymentBusiness(PostPaymentBusiness);
                    arrears.setArrearsAccount(ArrearsAccount);
                    arrears.setAmountOfArrears(AmountOfArrears);

                    arrearsList.add(arrears);
                }
                reportMessageData.setCrt2_p_arrears(arrearsList);
                log.debug("=============解析后付费业务欠费信息：[{}]",arrearsList);
            }else{
                log.debug("=============后付费业务欠费信息为空：[{}]");
            }

        }

        /**
         * PublicInformation 公共信息概要信息
         *
         * @param document
         */
        public void getPublicInformation(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 公共信息概要信息
            Elements PublicInformationss = document.select("table:contains(公共信息汇总)").select("tbody>tr");

            if(PublicInformationss.size()>0){
                List<PublicInformation> publicInformationList = new ArrayList<>();
                for (int i = 2; i < PublicInformationss.size(); i++) {
                    PublicInformation publicInformation = new PublicInformation();
                    Element PublicInformation = PublicInformationss.get(i);
                    // 公共信息类型数量
                    String TypeOfPublicInformation = Integer.toString(PublicInformationss.size() - 2);
                    // 公共信息类型
                    String PublicInformationsDesc = PublicInformation.select("th").text();
                    PublicInformationsDesc=PublicInformationsDesc.replace("信息","记录");
                    String PublicInformations = mapUtil.getItemIdByItemName2("p_public_information",PublicInformationsDesc);
                    // 记录数
                    String RecordNumber = PublicInformation.select("td").get(0).text();
                    // 涉及金额
                    String AmountInvolved = PublicInformation.select("td").get(1).text();
                    publicInformation.setReportId(ReportId);
                    publicInformation.setTypeOfPublicInformation(TypeOfPublicInformation);
                    publicInformation.setPublicInformation(PublicInformations);
                    publicInformation.setRecordNumber(RecordNumber);
                    publicInformation.setAmountInvolved(AmountInvolved);

                    publicInformationList.add(publicInformation);
                }
                reportMessageData.setCrt2_p_publicinformation(publicInformationList);
                log.debug("=============解析公共信息概要信息：[{}]",publicInformationList);
            }else{
                log.debug("=============公共信息概要信息为空：[{}]");
            }


        }

        /**
         * LastQuery 上一次查询记录信息
         *
         * @param document
         */
        public void getLastQuery(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 上一次查询记录信息
            Elements LastQuery = document.select("table:contains(上一次查询记录)");
            if(LastQuery.size()>0){
                List<LastQuery> lastQueryList = new ArrayList<>();
                LastQuery lastQuery = new LastQuery();
                // 上一次查询日期
                String LastQueryDate = LastQuery.select("tbody>tr").get(1).select("td").get(0).text();
                // 上一次查询机构机构类型
                String LastQueryOrganizationType = LastQuery.select("tbody>tr").get(1).select("td").get(1).text();
                String LastQueryOrganizationDesc = LastQueryOrganizationType;
                if (LastQueryOrganizationType.indexOf("\"") > 0) {
                    String str1 = LastQueryOrganizationType.substring(0, LastQueryOrganizationType.lastIndexOf("\"") - 3);
                    str1 = mapUtil.getItemIdByItemName2("entBusiManagOrg",str1);
                    // 上一次查询机构代码
                    String LastQueryCode = LastQuery.select("tbody>tr").get(1).select("td").get(1).text();
                    String str2 = LastQueryCode.substring(LastQueryCode.indexOf("\"") + 1, LastQueryCode.lastIndexOf("\""));
                    lastQuery.setLastQueryOrganizationType(str1);
                    lastQuery.setLastQueryCode(str2);
                } else {
                    lastQuery.setLastQueryOrganizationType(LastQueryOrganizationType);
                    lastQuery.setLastQueryCode("");
                }

                // 上一次查询原因
                String LastQueryReasonDesc = LastQuery.select("tbody>tr").get(1).select("td").get(2).text();
                String LastQueryReason = mapUtil.getItemIdByItemName2("queryDetailReason",LastQueryReasonDesc);

                lastQuery.setReportId(ReportId);
                lastQuery.setLastQueryDate(LastQueryDate);

                lastQuery.setLastQueryReason(LastQueryReason);

                lastQueryList.add(lastQuery);

                reportMessageData.setCrt2_p_lastquery(lastQueryList);
                log.debug("=============解析上一次查询记录信息：[{}]",lastQuery);
            }else{
                log.debug("=============上一次查询记录信息为空：[{}]");
            }

        }

        /**
         * QueryRecordSummary 查询记录概要信息
         *
         * @param document
         */
        public void getQueryRecordSummary(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 查询记录概要信息
            Elements QueryRecordSummary = document.select("table:contains(最近1个月内的查询机构数)");
            if(QueryRecordSummary.size()>0){
                List<cn.demo.rhv2model.domain.personal.QueryRecordSummary> queryRecordSummaryList = new ArrayList<>();
                QueryRecordSummary queryRecordSummary = new QueryRecordSummary();
                // 最近 1 个月内的查询机构数（贷款审批）
                String LoanApproval = QueryRecordSummary.select("tbody>tr").get(2).select("td").get(0).text();
                // 最近 1 个月内的查询机构数（信用卡审批）
                String CreditCardApproval = QueryRecordSummary.select("tbody>tr").get(2).select("td").get(1).text();
                // 最近 1 个月内的查询次数（贷款审批）
                String LoanApprovalNumber = QueryRecordSummary.select("tbody>tr").get(2).select("td").get(2).text();
                // 最近 1 个月内的查询次数（信用卡审批）
                String CreditCardApprovalNumber = QueryRecordSummary.select("tbody>tr").get(2).select("td").get(3).text();
                // 最近 1 个月内的查询次数（本人查询）
                String IqueryTimes = QueryRecordSummary.select("tbody>tr").get(2).select("td").get(4).text();
                // 最近 2 年内的查询次数（贷后管理）
                String EnquiryAfterLoanManagement = QueryRecordSummary.select("tbody>tr").get(2).select("td").get(5).text();
                // 最近 2 年内的查询次数（担保资格审查）
                String QualificationExaminationtionExamination = QueryRecordSummary.select("tbody>tr").get(2).select("td")
                        .get(6).text();
                // 最近 2 年内的查询次数（特约商户实名审查）
                String SpecialMerchantEnquiries = QueryRecordSummary.select("tbody>tr").get(2).select("td").get(7).text();

                queryRecordSummary.setReportId(ReportId);
                queryRecordSummary.setLoanApproval(LoanApproval);
                queryRecordSummary.setCreditCardApproval(CreditCardApproval);
                queryRecordSummary.setLoanApprovalNumber(LoanApprovalNumber);
                queryRecordSummary.setCreditCardApprovalNumber(CreditCardApprovalNumber);
                queryRecordSummary.setIqueryTimes(IqueryTimes);
                queryRecordSummary.setEnquiryAfterLoanManagement(EnquiryAfterLoanManagement);
                queryRecordSummary.setGuaranteeQualificationExamination(QualificationExaminationtionExamination);
                queryRecordSummary.setSpecialMerchantEnquiries(SpecialMerchantEnquiries);

                queryRecordSummaryList.add(queryRecordSummary);

                reportMessageData.setCrt2_p_queryrecordsummary(queryRecordSummaryList);
                log.debug("=============解析记录概要信息：[{}]",queryRecordSummary);
            }else{
                log.debug("=============记录概要信息为空：[{}]");
            }

        }


        // 基本信息段 C1账户-催收账户
        public void getEssentialInformationC1(Document document,List<EssentialInformation> essentialInformationList){
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            String body = document.toString();
            if (body.indexOf("（一）被追偿信息") == -1) {
                return;
            }

            body = body.substring(body.indexOf("（一）被追偿信息"), body.indexOf("（二）非循环贷账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements EssentialInformationC1s = getTest.select("table:contains(管理机构)").select("table:contains(业务种类)");
            Elements divs = getTest.select("div");
            //拥有渲染html时的账户编号
            int number = 1;
            if(EssentialInformationC1s.size()>0){
                for(int i = 0; i < EssentialInformationC1s.size(); i++){
                    EssentialInformation essentialInformation = new EssentialInformation();
                    Element EssentialInformationC1 = EssentialInformationC1s.get(i);
                    Element div = divs.get(i);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "被追偿信息_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "被追偿信息_" + aaa.trim();
                    }
                    String AccountNumberDesc = Integer.toString(number++);
                    // 账户类型
                    String AccountType = "C1";
                    // 业务管理机构类型
                    String BusinessInstitutions = EssentialInformationC1.select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessInstitutionsDesc = BusinessInstitutions;
                    String str1 = BusinessInstitutions.substring(0, BusinessInstitutions.lastIndexOf("\"") - 3);
                    str1 = mapUtil.getItemIdByItemName2("p_org_type",str1);
                    // 业务管理机构代码
                    String BusinessCode = EssentialInformationC1.select("tbody>tr").get(1).select("td").get(0).text();
                    String str2 = BusinessCode.substring(BusinessCode.indexOf("\"") + 1, BusinessCode.lastIndexOf("\""));
                    //业务种类
                    String BusinessTypesDesc = EssentialInformationC1.select("tbody>tr").get(1).select("td").get(1).text();
                    String BusinessTypes = mapUtil.getItemIdByItemName2("p_lending_business",BusinessTypesDesc);
                    //开立日期
                    String IssuanceDate = EssentialInformationC1.select("tbody>tr").get(1).select("td").get(2).text();
                    //借款金额
                    String LoanAmount = EssentialInformationC1.select("tbody>tr").get(1).select("td").get(3).text();
                    //债权转移时的还款状态
                    String RepaymentStateDesc = EssentialInformationC1.select("tbody>tr").get(1).select("td").get(4).text();
                    String RepaymentState = mapUtil.getItemIdByItemName2("p_debt_repayment",RepaymentStateDesc);

                    essentialInformation.setReportId(ReportId);
                    essentialInformation.setAccountNumber(AccountNumber);
                    essentialInformation.setAccountType(AccountType);
                    essentialInformation.setBusinessInstitutions(str1);
                    essentialInformation.setBusinessCode(str2);
                    essentialInformation.setBusinessTypes(BusinessTypes);
                    essentialInformation.setIssuanceDate(IssuanceDate);
                    essentialInformation.setLoanAmount(LoanAmount);
                    essentialInformation.setRepaymentState(RepaymentState);

                    essentialInformationList.add(essentialInformation);
                }
                log.debug("=============解析基本信息段C1账户：[{}]");
            }else{
                log.debug("=============基本信息段C1账户为空：[{}]");
            }

        }

        // 基本信息段 D1账户-非循环贷账户
        public void getEssentialInformationD1(Document document,List<EssentialInformation> essentialInformationList) {

            // 获取报告编号
		    String ReportId = document.select("table.g-tab-bor")
                    .first().selectFirst("tbody").selectFirst("tr").selectFirst("td").text().substring(5, 27);

            // 信贷交易明细
            Elements select = document.select("div.m-repbody");
            if (select == null || select.size() < 3) {
                return;
            }

            Element CreditDetail = select.get(2);
            // 非循环贷账户D1
            Elements D1 = CreditDetail.select("div.t2").select("div.f-mgtop").select("div:contains(非循环贷账户)");


            if (D1.size() == 0) {
                log.debug("=============基本信息段D1账户为空：[{}]");
            } else {
                // 以下代码为截取非循环贷账户的所有表
                String temp = document.select("div:containsOwn(非循环贷账户)").text();
                String temp2 = document.select("div:containsOwn(循环额度下分账户)").text();
                if(StringUtils.isBlank(temp)||StringUtils.isBlank(temp2)){
                    return;
                }
                String html = document.toString().substring(document.toString().indexOf(temp),
                        document.toString().indexOf(temp2));
                Document dd = Jsoup.parse(html);
                Elements D1Table = dd.select("table:contains(管理机构)");
                //拥有渲染html时的账户编号
                int number = 1;
                for (int i = 0; i < D1Table.size(); i++) {
                    EssentialInformation essentialInformation = new EssentialInformation();
                    // 账户编号
                    // String AccountNumber = D1.next().text();
                    String aaa = dd.select("div").get(i).text();
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "非循环贷账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "非循环贷账户_" + aaa.trim();
                    }
                    String AccountNumberDesc = Integer.toString(number++);
                    // 账户类型
                    String AccountType = "D1";
                    // 业务管理机构类型
                    String BusinessInstitutions = D1Table.get(i).select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessInstitutionsDesc = BusinessInstitutions;
                    String str1 = BusinessInstitutions.substring(0, BusinessInstitutions.lastIndexOf("\"") - 3);
                    str1 = mapUtil.getItemIdByItemName2("p_org_type",str1);
                    // 业务管理机构代码
                    String BusinessCode = D1Table.get(i).select("tbody>tr").get(1).select("td").get(0).text();
                    String str2 = BusinessCode.substring(BusinessCode.indexOf("\"") + 1, BusinessCode.lastIndexOf("\""));
                    // 账户标识
                    String AccountIdentification = D1Table.get(i).select("tbody>tr").get(1).select("td").get(1).text();
                    // 授信协议编号
                    String CreditAgreementNumber = null;
                    if (aaa.indexOf("授信协议标识") != -1) {
                        CreditAgreementNumber = aaa.substring(12, 16);
                    } else {
                        CreditAgreementNumber = null;
                    }
                    // 业务种类
                    String BusinessTypesDesc = D1Table.get(i).select("tbody>tr").get(3).select("td").get(0).text();
                    String BusinessTypes = mapUtil.getItemIdByItemName2("p_lending_business",BusinessTypesDesc);
                    // 开立日期
                    String IssuanceDate = D1Table.get(i).select("tbody>tr").get(1).select("td").get(2).text();
                    // 币种
                    String CurrencyDesc = D1Table.get(i).select("tbody>tr").get(1).select("td").get(5).text();
                    if (CurrencyDesc.equals("人民币元")) {
                        CurrencyDesc = "人民币";
                    }
                    String Currency = mapUtil.getItemIdByItemName2("entCurrency",CurrencyDesc);
                    // 借款金额
                    String LoanAmount = D1Table.get(i).select("tbody>tr").get(1).select("td").get(4).text();

                    // 到期日期
                    String ExpirationDate = D1Table.get(i).select("tbody>tr").get(1).select("td").get(3).text();
                    // 还款方式
                    String RepaymentMethodDesc = D1Table.get(i).select("tbody>tr").get(3).select("td").get(4).text();
                    String RepaymentMethod = mapUtil.getItemIdByItemName2("p_repayment_method",RepaymentMethodDesc);
                    // 还款频率
                    String RepaymentFrequencyDesc = D1Table.get(i).select("tbody>tr").get(3).select("td").get(3).text();
                    String RepaymentFrequency = mapUtil.getItemIdByItemName2("p_repayment_frequency",RepaymentFrequencyDesc);
                    // 还款期数
                    String RepaymentPeriod = D1Table.get(i).select("tbody>tr").get(3).select("td").get(2).text();
                    // 担保方式
                    String GuaranteeMethodDesc = D1Table.get(i).select("tbody>tr").get(3).select("td").get(1).text();
                    String GuaranteeMethod = mapUtil.getItemIdByItemName2("p_guarantee_method",GuaranteeMethodDesc);
                    // 贷款发放形式
                    String LoanIssuanceDesc;
                    if (aaa.indexOf("其他机构转入") != -1) {
                        LoanIssuanceDesc = aaa.substring(aaa.lastIndexOf("（") + 1, aaa.lastIndexOf("）"));
                    } else {
                        LoanIssuanceDesc = null;
                    }
                    String LoanIssuance = mapUtil.getItemIdByItemName2("p_form_distribution",LoanIssuanceDesc);
                    // 共同借款标志
                    String CommonBorrowingMarkDesc = D1Table.get(i).select("tbody>tr").get(3).select("td").get(5).text();
                    String CommonBorrowingMark = mapUtil.getItemIdByItemName2("p_loan_sign",CommonBorrowingMarkDesc);
                    // 债权转移时的还款状态(D1账户无此字段)

                    essentialInformation.setReportId(ReportId);
                    essentialInformation.setAccountNumber(AccountNumber);
                    essentialInformation.setAccountType(AccountType);
                    essentialInformation.setBusinessInstitutions(str1);
                    essentialInformation.setBusinessCode(str2);
                    essentialInformation.setAccountIdentification(AccountIdentification);
                    essentialInformation.setCreditAgreementNumber(CreditAgreementNumber);
                    essentialInformation.setBusinessTypes(BusinessTypes);
                    essentialInformation.setIssuanceDate(IssuanceDate);
                    essentialInformation.setCurrency(Currency);
                    essentialInformation.setLoanAmount(LoanAmount);
                    essentialInformation.setExpirationDate(ExpirationDate);
                    essentialInformation.setRepaymentMethod(RepaymentMethod);
                    essentialInformation.setRepaymentFrequency(RepaymentFrequency);
                    essentialInformation.setRepaymentPeriod(RepaymentPeriod);
                    essentialInformation.setGuaranteeMethod(GuaranteeMethod);
                    essentialInformation.setLoanIssuance(LoanIssuance);
                    essentialInformation.setCommonBorrowingMark(CommonBorrowingMark);

                    essentialInformationList.add(essentialInformation);
                }
                log.debug("=============查询基本信息段D1账户：[{}]");
            }

        }

        // 基本信息段 R4-循环额度下分账户
        public void getEssentialInformationR4(Document document,List<EssentialInformation> essentialInformationList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            String body = document.toString();
            if (body.indexOf("（三）循环额度下分账户") == -1) {
                return;
            }

            body = body.substring(body.indexOf("（三）循环额度下分账户"), body.indexOf("（四）循环贷账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements EssentialInformationR4s = getTest.select("table:contains(管理机构)").select("table:contains(账户标识)");
            Elements divs = getTest.select("div");
            //用于渲染html时的账户编号
            int number = 1;
            if(EssentialInformationR4s.size()>0){
                for (int i = 0; i < EssentialInformationR4s.size(); i++) {
                    EssentialInformation essentialInformation = new EssentialInformation();
                    Element EssentialInformationR4 = EssentialInformationR4s.get(i);
                    Element div = divs.get(i);
                    String aaa = div.text();

                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环额度下分账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "循环额度下分账户_" + aaa;
                    }
                    String AccountNumberDesc = Integer.toString(number++);
                    // 账户类型
                    String AccountType = "R4";
                    // 业务管理机构类型
                    String BusinessInstitutions = EssentialInformationR4.select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessInstitutionsDesc = BusinessInstitutions;
                    String str1 = BusinessInstitutions.substring(0, BusinessInstitutions.lastIndexOf("\"") - 3);
                    str1 = mapUtil.getItemIdByItemName2("p_org_type",str1);
                    // 业务管理机构代码
                    String BusinessCode = EssentialInformationR4.select("tbody>tr").get(1).select("td").get(0).text();
                    String str2 = BusinessCode.substring(BusinessCode.indexOf("\"") + 1, BusinessCode.lastIndexOf("\""));
                    // 账户标识
                    String AccountIdentification = EssentialInformationR4.select("tbody>tr").get(1).select("td").get(1).text();
                    // 授信协议编号
                    String CreditAgreementNumber = null;
                    if (aaa.indexOf("授信协议标识") != -1) {
                        CreditAgreementNumber = aaa.substring(aaa.indexOf("：") + 1, aaa.indexOf("）"));
                    } else {
                        CreditAgreementNumber = null;
                    }
                    // 业务种类
                    String BusinessTypesDesc = EssentialInformationR4.select("tbody>tr").get(3).select("td").get(0).text();
                    String BusinessTypes = mapUtil.getItemIdByItemName2("p_lending_business",BusinessTypesDesc);
                    // 开立日期
                    String IssuanceDate = EssentialInformationR4.select("tbody>tr").get(1).select("td").get(2).text();
                    // 币种
                    String CurrencyDesc = EssentialInformationR4.select("tbody>tr").get(1).select("td").get(5).text();
                    if (CurrencyDesc.equals("人民币元")) {
                        CurrencyDesc = "人民币";
                    }
                    String Currency = mapUtil.getItemIdByItemName2("entCurrency",CurrencyDesc);
                    // 借款金额
                    String LoanAmount = EssentialInformationR4.select("tbody>tr").get(1).select("td").get(4).text();
                    // 账户授信额度
                    // 共享授信额度
                    // 到期日期
                    String ExpirationDate = EssentialInformationR4.select("tbody>tr").get(1).select("td").get(3).text();
                    // 还款方式
                    String RepaymentMethodDesc = EssentialInformationR4.select("tbody>tr").get(3).select("td").get(4).text();
                    String RepaymentMethod = mapUtil.getItemIdByItemName2("p_repayment_method",RepaymentMethodDesc);
                    // 还款频率
                    String RepaymentFrequencyDesc = EssentialInformationR4.select("tbody>tr").get(3).select("td").get(3).text();
                    String RepaymentFrequency = mapUtil.getItemIdByItemName2("p_repayment_frequency",RepaymentFrequencyDesc);
                    // 还款期数
                    String RepaymentPeriod = EssentialInformationR4.select("tbody>tr").get(3).select("td").get(2).text();
                    // 担保方式
                    String GuaranteeMethodDesc = EssentialInformationR4.select("tbody>tr").get(3).select("td").get(1).text();
                    String GuaranteeMethod = mapUtil.getItemIdByItemName2("p_guarantee_method",GuaranteeMethodDesc);
                    // 贷款发放形式
                    String LoanIssuance = null;
                    if (aaa.contains("新增") != false && aaa.contains("其他机构转入") != false) {
                        LoanIssuance = aaa.substring(aaa.lastIndexOf("（") + 1, aaa.lastIndexOf("）"));
                    } else {
                        LoanIssuance = null;
                    }
                    LoanIssuance = mapUtil.getItemIdByItemName2("p_form_distribution",LoanIssuance);
                    // 共同借款标志
                    String CommonBorrowingMarkDesc = EssentialInformationR4.select("tbody>tr").get(3).select("td").get(5).text();
                    String CommonBorrowingMark = mapUtil.getItemIdByItemName2("p_loan_sign",CommonBorrowingMarkDesc);
                    // 债权转移时的还款状态(R4账户无此字段)

                    essentialInformation.setReportId(ReportId);
                    essentialInformation.setAccountNumber(AccountNumber);
                    essentialInformation.setAccountType(AccountType);
                    essentialInformation.setBusinessInstitutions(str1);
                    essentialInformation.setBusinessCode(str2);
                    essentialInformation.setAccountIdentification(AccountIdentification);
                    essentialInformation.setCreditAgreementNumber(CreditAgreementNumber);
                    essentialInformation.setBusinessTypes(BusinessTypes);
                    essentialInformation.setIssuanceDate(IssuanceDate);
                    essentialInformation.setCurrency(Currency);
                    essentialInformation.setLoanAmount(LoanAmount);
                    essentialInformation.setExpirationDate(ExpirationDate);
                    essentialInformation.setRepaymentMethod(RepaymentMethod);
                    essentialInformation.setRepaymentFrequency(RepaymentFrequency);
                    essentialInformation.setRepaymentPeriod(RepaymentPeriod);
                    essentialInformation.setGuaranteeMethod(GuaranteeMethod);
                    essentialInformation.setLoanIssuance(LoanIssuance);
                    essentialInformation.setCommonBorrowingMark(CommonBorrowingMark);

                    essentialInformationList.add(essentialInformation);
                }
                log.debug("=============解析基本信息段R4账户：[{}]");
            }else{
                log.debug("=============基本信息段R4账户为空：[{}]");
            }

        }

        // 基本信息段 R1-循环贷账户
        public void getEssentialInformationR1(Document document,List<EssentialInformation> essentialInformationList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（四）循环贷账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（四）循环贷账户"), body.indexOf("（五）贷记卡账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements EssentialInformationR1s = getTest.select("table:contains(管理机构)").select("table:contains(账户标识)");
            Elements divs = getTest.select("div");
            //用于渲染html时的账户编号
            int number = 1;
            if(EssentialInformationR1s.size()>0){
                for (int i = 0; i < EssentialInformationR1s.size(); i++) {
                    EssentialInformation essentialInformation = new EssentialInformation();
                    Element EssentialInformationR1 = EssentialInformationR1s.get(i);
                    Element div = divs.get(i);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环贷账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "循环贷账户_" + aaa;
                    }
                    String AccountNumberDesc = Integer.toString(number++);
                    // 账户类型
                    String AccountType = "R1";
                    // 业务管理机构类型
                    String BusinessInstitutions = EssentialInformationR1.select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessInstitutionsDesc = BusinessInstitutions;
                    String str1 = BusinessInstitutions.substring(0, BusinessInstitutions.lastIndexOf("\"") - 3);
                    str1 = mapUtil.getItemIdByItemName2("p_org_type",str1);
                    // 业务管理机构代码
                    String BusinessCode = EssentialInformationR1.select("tbody>tr").get(1).select("td").get(0).text();
                    String str2 = BusinessCode.substring(BusinessCode.indexOf("\"") + 1, BusinessCode.lastIndexOf("\""));
                    // 账户标识
                    String AccountIdentification = EssentialInformationR1.select("tbody>tr").get(1).select("td").get(1).text();
                    // 授信协议编号
                    String CreditAgreementNumber = null;
                    if (aaa.indexOf("授信协议标识") != -1) {
                        CreditAgreementNumber = aaa.substring(aaa.indexOf("：") + 1, aaa.indexOf("）"));
                    } else {
                        CreditAgreementNumber = null;
                    }
                    // 业务种类
                    String BusinessTypesDesc = EssentialInformationR1.select("tbody>tr").get(3).select("td").get(0).text();
                    String BusinessTypes = mapUtil.getItemIdByItemName2("p_lending_business",BusinessTypesDesc);
                    // 开立日期
                    String IssuanceDate = EssentialInformationR1.select("tbody>tr").get(1).select("td").get(2).text();
                    // 币种
                    String CurrencyDesc = EssentialInformationR1.select("tbody>tr").get(1).select("td").get(5).text();
                    if (CurrencyDesc.equals("人民币元")) {
                        CurrencyDesc = "人民币";
                    }
                    String Currency = mapUtil.getItemIdByItemName2("entCurrency",CurrencyDesc);
                    // 借款金额
                    // 账户授信额度
                    String CreditLineOfAccount = EssentialInformationR1.select("tbody>tr").get(1).select("td").get(4).text();
                    // 共享授信额度
                    // 到期日期
                    String ExpirationDate = EssentialInformationR1.select("tbody>tr").get(1).select("td").get(3).text();
                    // 还款方式
                    String RepaymentMethodDesc = EssentialInformationR1.select("tbody>tr").get(3).select("td").get(4).text();
                    String RepaymentMethod = mapUtil.getItemIdByItemName2("p_repayment_method",RepaymentMethodDesc);
                    // 还款频率
                    String RepaymentFrequencyDesc = EssentialInformationR1.select("tbody>tr").get(3).select("td").get(3).text();
                    String RepaymentFrequency = mapUtil.getItemIdByItemName2("p_repayment_frequency",RepaymentFrequencyDesc);
                    // 还款期数
                    String RepaymentPeriod = EssentialInformationR1.select("tbody>tr").get(3).select("td").get(2).text();
                    // 担保方式
                    String GuaranteeMethodDesc = EssentialInformationR1.select("tbody>tr").get(3).select("td").get(1).text();
                    String GuaranteeMethod = mapUtil.getItemIdByItemName2("p_guarantee_method",GuaranteeMethodDesc);
                    // 贷款发放形式
                    String LoanIssuance = null;
                    if (aaa.contains("新增") != false || aaa.contains("其他机构转入") != false) {
                        LoanIssuance = aaa.substring(aaa.lastIndexOf("（") + 1, aaa.lastIndexOf("）"));
                    } else {
                        LoanIssuance = null;
                    }
                    LoanIssuance = mapUtil.getItemIdByItemName2("p_form_distribution",LoanIssuance);
                    // 共同借款标志
                    String CommonBorrowingMarkDesc = EssentialInformationR1.select("tbody>tr").get(3).select("td").get(5).text();
                    String CommonBorrowingMark = mapUtil.getItemIdByItemName2("p_loan_sign",CommonBorrowingMarkDesc);
                    // 债权转移时的还款状态(R4账户无此字段)

                    essentialInformation.setReportId(ReportId);
                    essentialInformation.setAccountNumber(AccountNumber);
//				essentialInformation.setAccountnumberdesc(AccountNumberDesc);
                    essentialInformation.setAccountType(AccountType);
                    essentialInformation.setBusinessInstitutions(str1);
                    essentialInformation.setBusinessCode(str2);
                    essentialInformation.setAccountIdentification(AccountIdentification);
                    essentialInformation.setCreditAgreementNumber(CreditAgreementNumber);
                    essentialInformation.setBusinessTypes(BusinessTypes);
                    essentialInformation.setIssuanceDate(IssuanceDate);
                    essentialInformation.setCurrency(Currency);
                    essentialInformation.setExpirationDate(ExpirationDate);
                    essentialInformation.setRepaymentMethod(RepaymentMethod);
                    essentialInformation.setRepaymentFrequency(RepaymentFrequency);
                    essentialInformation.setRepaymentPeriod(RepaymentPeriod);
                    essentialInformation.setGuaranteeMethod(GuaranteeMethod);
                    essentialInformation.setLoanIssuance(LoanIssuance);
                    essentialInformation.setCommonBorrowingMark(CommonBorrowingMark);
                    essentialInformation.setCreditLineOfAccount(CreditLineOfAccount);

                    essentialInformationList.add(essentialInformation);
                }
                log.debug("=============解析基本信息段R1账户：[{}]");
            }else{
                log.debug("=============基本信息段R1账户为空：[{}]");
            }

        }

        // 基本信息段 R2-贷记卡账户
        public void getEssentialInformationR2(Document document,List<EssentialInformation> essentialInformationList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（五）贷记卡账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（五）贷记卡账户"), body.indexOf("（六）准贷记卡账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements EssentialInformationR2s = getTest.select("table:contains(发卡机构)").select("table:contains(账户标识)");
            Elements divs = getTest.select("div");
            //用于渲染html时的账户编号
            int number = 1;
            if(EssentialInformationR2s.size()>0){
                for (int i = 0; i < EssentialInformationR2s.size(); i++) {
                    EssentialInformation essentialInformation = new EssentialInformation();
                    Element EssentialInformationR2 = EssentialInformationR2s.get(i);
                    Element div = divs.get(i);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa;
                    }
                    String AccountNumberDesc = Integer.toString(number++);
                    // 账户类型
                    String AccountType = "R2";
                    // 业务管理机构类型
                    String BusinessInstitutions = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessInstitutionsDesc = BusinessInstitutions;
                    String str1 = BusinessInstitutions.substring(0, BusinessInstitutions.lastIndexOf("\"") - 3);
                    str1 = mapUtil.getItemIdByItemName2("p_org_type",str1);
                    // 业务管理机构代码
                    String BusinessCode = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(0).text();
                    String str2 = BusinessCode.substring(BusinessCode.indexOf("\"") + 1, BusinessCode.lastIndexOf("\""));
                    // 账户标识
                    String AccountIdentification = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(1).text();
                    // 授信协议编号
                    String CreditAgreementNumber = null;
                    if (aaa.indexOf("授信协议标识") != -1) {
                        CreditAgreementNumber = aaa.substring(aaa.indexOf("：") + 1, aaa.indexOf("）"));
                    } else {
                        CreditAgreementNumber = null;
                    }
                    // 业务种类
                    String BusinessTypesDesc = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(6).text();
                    String BusinessTypes = mapUtil.getItemIdByItemName2("p_lending_business",BusinessTypesDesc);
                    // 开立日期
                    String IssuanceDate = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(2).text();
                    // 币种
                    String CurrencyDesc = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(5).text();
                    if (CurrencyDesc.equals("人民币元")) {
                        CurrencyDesc = "人民币";
                    }
                    String Currency = mapUtil.getItemIdByItemName2("entCurrency",CurrencyDesc);
                    // 借款金额
                    // 账户授信额度
                    String CreditLineOfAccount = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(3).text();
                    // 共享授信额度
                    String SharedCreditLine = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(4).text();
                    // 到期日期
                    // String ExpirationDate
                    // 还款方式
                    // String RepaymentMethod
                    // 还款频率
                    // String RepaymentFrequency
                    // 还款期数
                    // String RepaymentPeriod
                    // 担保方式
                    String GuaranteeMethodDesc = EssentialInformationR2.select("tbody>tr").get(1).select("td").get(7).text();
                    String GuaranteeMethod = mapUtil.getItemIdByItemName2("p_guarantee_method",GuaranteeMethodDesc);
                    // 贷款发放形式
                    String LoanIssuance = null;
                    if (aaa.contains("新增") != false || aaa.contains("其他机构转入") != false) {
                        LoanIssuance = aaa.substring(aaa.lastIndexOf("（") + 1, aaa.lastIndexOf("）"));
                    } else {
                        LoanIssuance = null;
                    }
                    LoanIssuance = mapUtil.getItemIdByItemName2("p_form_distribution",LoanIssuance);
                    // 共同借款标志
                    // String CommonBorrowingMark
                    // 债权转移时的还款状态(R4账户无此字段)
                    // String RepaymentState

                    essentialInformation.setReportId(ReportId);
                    essentialInformation.setAccountNumber(AccountNumber);
                    essentialInformation.setAccountType(AccountType);
                    essentialInformation.setBusinessInstitutions(str1);
                    essentialInformation.setBusinessCode(str2);
                    essentialInformation.setAccountIdentification(AccountIdentification);
                    essentialInformation.setCreditAgreementNumber(CreditAgreementNumber);
                    essentialInformation.setBusinessTypes(BusinessTypes);
                    essentialInformation.setIssuanceDate(IssuanceDate);
                    essentialInformation.setCurrency(Currency);
                    essentialInformation.setGuaranteeMethod(GuaranteeMethod);
                    essentialInformation.setLoanIssuance(LoanIssuance);
                    essentialInformation.setCreditLineOfAccount(CreditLineOfAccount);
                    essentialInformation.setSharedCreditLine(SharedCreditLine);

                    essentialInformationList.add(essentialInformation);
                }
                log.debug("=============解析基本信息段R2账户：[{}]");
            }else{
                log.debug("=============基本信息段R2账户为空：[{}]");
            }

        }

        // 基本信息段 R3-准贷记卡账户
        public void getEssentialInformationR3(Document document,List<EssentialInformation> essentialInformationList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（六）准贷记卡账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（六）准贷记卡账户"), body.indexOf("（七）相关还款责任信息"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements EssentialInformationR3s = getTest.select("table:contains(发卡机构)").select("table:contains(账户标识)");
            Elements divs = getTest.select("div");
            //用于渲染html时的账户编号
            int number = 1;
            if(EssentialInformationR3s.size()>0){
                for (int i = 0; i < EssentialInformationR3s.size(); i++) {
                    EssentialInformation essentialInformation = new EssentialInformation();
                    Element EssentialInformationR3 = EssentialInformationR3s.get(i);
                    Element div = divs.get(i);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "准贷记卡账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "准贷记卡账户_" + aaa;
                    }
                    String AccountNumberDesc = Integer.toString(number++);
                    // 账户类型
                    String AccountType = "R3";
                    // 业务管理机构类型
                    String BusinessInstitutions = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessInstitutionsDesc = BusinessInstitutions;
                    String str1 = BusinessInstitutions.substring(0, BusinessInstitutions.lastIndexOf("\"") - 3);
                    str1 = mapUtil.getItemIdByItemName2("p_org_type",str1);
                    // 业务管理机构代码
                    String BusinessCode = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(0).text();
                    String str2 = BusinessCode.substring(BusinessCode.indexOf("\"") + 1, BusinessCode.lastIndexOf("\""));
                    // 账户标识
                    String AccountIdentification = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(1).text();
                    // 授信协议编号
                    String CreditAgreementNumber = null;
                    if (aaa.indexOf("授信协议标识") != -1) {
                        CreditAgreementNumber = aaa.substring(aaa.indexOf("：") + 1, aaa.indexOf("）"));
                    } else {
                        CreditAgreementNumber = null;
                    }
                    // 业务种类
                    // String BusinessTypes
                    // 开立日期
                    String IssuanceDate = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(2).text();
                    // 币种
                    String CurrencyDesc = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(5).text();
                    if (CurrencyDesc.equals("人民币元")) {
                        CurrencyDesc = "人民币";
                    }
                    String Currency = mapUtil.getItemIdByItemName2("entCurrency",CurrencyDesc);
                    // 借款金额
                    // 账户授信额度
                    String CreditLineOfAccount = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(3).text();
                    // 共享授信额度
                    String SharedCreditLine = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(4).text();
                    // 到期日期
                    // String ExpirationDate
                    // 还款方式
                    // String RepaymentMethod
                    // 还款频率
                    // String RepaymentFrequency
                    // 还款期数
                    // String RepaymentPeriod
                    // 担保方式
                    String GuaranteeMethodDesc = EssentialInformationR3.select("tbody>tr").get(1).select("td").get(6).text();
                    String GuaranteeMethod = mapUtil.getItemIdByItemName2("p_guarantee_method",GuaranteeMethodDesc);
                    // 贷款发放形式
                    String LoanIssuance = null;
                    if (aaa.contains("新增") != false || aaa.contains("其他机构转入") != false) {
                        LoanIssuance = aaa.substring(aaa.lastIndexOf("（") + 1, aaa.lastIndexOf("）"));
                    } else {
                        LoanIssuance = null;
                    }
                    mapUtil.getItemIdByItemName2("p_form_distribution",LoanIssuance);
                    // 共同借款标志
                    // String CommonBorrowingMark
                    // 债权转移时的还款状态(R4账户无此字段)
                    // String RepaymentState

                    essentialInformation.setReportId(ReportId);
                    essentialInformation.setAccountNumber(AccountNumber);
                    essentialInformation.setAccountType(AccountType);
                    essentialInformation.setBusinessInstitutions(str1);
                    essentialInformation.setBusinessCode(str2);
                    essentialInformation.setAccountIdentification(AccountIdentification);
                    essentialInformation.setCreditAgreementNumber(CreditAgreementNumber);

                    essentialInformation.setIssuanceDate(IssuanceDate);
                    essentialInformation.setCurrency(Currency);
                    essentialInformation.setGuaranteeMethod(GuaranteeMethod);
                    essentialInformation.setLoanIssuance(LoanIssuance);
                    essentialInformation.setCreditLineOfAccount(CreditLineOfAccount);
                    essentialInformation.setSharedCreditLine(SharedCreditLine);

                    essentialInformationList.add(essentialInformation);
                }
                log.debug("=============解析基本信息段R3账户：[{}]");
            }else{
                log.debug("=============基本信息段R3账户为空：[{}]");
            }
        }

        // C1账户
        public void getTheLatestPerformanceC1(Document document,List<TheLatestPerformance> theLatestPerformancelist){
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（一）被追偿信息") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（一）被追偿信息"), body.indexOf("（二）非循环贷账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements TheLatestPerformanceC1s = getTest.select("table:contains(账户状态)").select("table:contains(催收)");
            Elements TheLatestPerformanceC1s2 = getTest.select("table:contains(账户状态)").select("table:contains(结束)");

            //账户状态为催收时
            if(TheLatestPerformanceC1s.size()>0){
                for (int i = 0; i < TheLatestPerformanceC1s.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceC1 = TheLatestPerformanceC1s.get(i);
                    Element div = TheLatestPerformanceC1.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "被追偿信息_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "被追偿信息_" + aaa;
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceC1.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_c1_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = null;
                    // 余额
                    String Balance = TheLatestPerformanceC1.select("tbody>tr").get(2).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceC1.select("tbody>tr").get(2).select("td").get(2).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceC1.select("tbody>tr").get(0).select("th").text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);


                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance .setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息C1账户：[{}]");
            }else {
                log.debug("=============最新表现信息C1账户为空：[{}]");
            }
            //账户状态为结束时
            if(TheLatestPerformanceC1s2.size()>0){
                for (int i = 0; i < TheLatestPerformanceC1s2.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceC1 = TheLatestPerformanceC1s2.get(i);
                    Element div = TheLatestPerformanceC1.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "被追偿信息_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "被追偿信息_" + aaa;
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceC1.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_c1_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = TheLatestPerformanceC1.select("tbody>tr").get(2).select("td").get(1).text();;
                    // 最近一次还款日期
                    String LastRepaymentDate = ClosingDate;
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceC1.select("tbody>tr").get(0).select("th").text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);


                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息C1账户：[{}]");
            }else {
                log.debug("=============最新表现信息C1账户为空：[{}]");
            }
        }

        // D1账户
        public void getTheLatestPerformanceD1(Document document,List<TheLatestPerformance> theLatestPerformancelist) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（二）非循环贷账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（二）非循环贷账户"), body.indexOf("（三）循环额度下分账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            //呆账element
            Elements TheLatestPerformanceD1As = getTest.select("table:contains(账户状态)").select("table:contains(呆账)");
            //其他还款状态的element
            Elements TheLatestPerformanceD1s = getTest.select("table:contains(账户状态)").select("table:contains(五级分类)");
            //其他还款状态+有最新还款记录的element
            Elements TheLatestPerformanceD1specs = TheLatestPerformanceD1s.next().next().select("table:contains(的最新还款记录)");
		/*for (Element TheLatestPerformanceD1spec: TheLatestPerformanceD1specs) {
			if(TheLatestPerformanceD1spec.select("table:contains(的最新还款记录)").size()!=0){
				System.out.println(TheLatestPerformanceD1spec);
			}
		}*/
            //System.out.println(TheLatestPerformanceD1specs.select("table:contains(的最新还款记录)").get(0));
            //结清element
            Elements TheLatestPerformanceD1Cs = getTest.select("table:contains(账户状态)").select("table:contains(结清)");
            //账户状态为呆账时
            if(TheLatestPerformanceD1As.size()>0){
                for(int i = 0; i < TheLatestPerformanceD1As.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceD1A = TheLatestPerformanceD1As.get(i);
                    Element div = TheLatestPerformanceD1A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "非循环贷账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "非循环贷账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceD1A.select("tbody>tr").get(6).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_d1_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = TheLatestPerformanceD1A.select("tbody>tr").get(6).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceD1A.select("tbody>tr").get(6).select("td").get(2).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceD1A.select("tbody>tr").get(4).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息D1账户：[{}]");
            }else{
                log.debug("=============最新表现信息D1账户为空：[{}]");
            }


            //账户状态除呆账和结清外时
            if(TheLatestPerformanceD1s.size()>0){
                for (int i = 0; i < TheLatestPerformanceD1s.size(); i++) {
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceD1 = TheLatestPerformanceD1s.get(i);
                    if(TheLatestPerformanceD1s.get(i).nextElementSibling().nextElementSibling().toString().indexOf("的最新还款记录")==-1){
                        Element div = TheLatestPerformanceD1.previousElementSiblings().select("div").get(0);
                        String aaa = div.text();
                        // 账户编号
                        String AccountNumber = null;
                        if (aaa.length() > 5) {
                            AccountNumber = "非循环贷账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                        } else {
                            AccountNumber = "非循环贷账户_" + aaa.trim();
                        }
                        // 账户状态
                        String AccountStateDesc = TheLatestPerformanceD1.select("tbody>tr").get(2).select("td").get(0).text();
                        String AccountState = mapUtil.getItemIdByItemName2("p_d1_loan_state",AccountStateDesc);
                        // 关闭日期
                        String ClosingDate = null;
                        // 转出月份
                        String TransferMonth = null;
                        if (AccountState.equals("转出") == true) {
                            TransferMonth = "不为空";
                        } else {
                            TransferMonth = null;
                        }
                        // 余额
                        String Balance = TheLatestPerformanceD1.select("tbody>tr").get(2).select("td").get(2).text();
                        // 最近一次还款日期
                        String LastRepaymentDate = TheLatestPerformanceD1.select("tbody>tr").get(2).select("td").get(7).text();
                        // 最近一次还款金额
                        // 五级分类
                        String Classification = TheLatestPerformanceD1.select("tbody>tr").get(2).select("td").get(1).text();
                        Classification = enumMap.get(Classification);
                        // 还款状态
                        String RepaymentState = TheLatestPerformanceD1.select("tbody>tr").get(2).select("td").get(4).text();
                        //RepaymentState = enumMap.get(RepaymentState);
                        // 信息报告日期
                        String DateOfReport = TheLatestPerformanceD1.select("tbody>tr").get(0).select("th").text();
                        DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                        theLatestPerformance.setReportId(ReportId);
                        theLatestPerformance.setAccountNumber(AccountNumber);
                        theLatestPerformance.setAccountState(AccountState);
                        theLatestPerformance.setClosingDate(ClosingDate);
                        theLatestPerformance.setTransferMonth(TransferMonth);
                        theLatestPerformance.setBalance(Balance);
                        theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                        theLatestPerformance.setClassification(Classification);
                        theLatestPerformance.setDateOfReport(DateOfReport);
                        theLatestPerformance.setRepaymentState(RepaymentState);

                        theLatestPerformancelist.add(theLatestPerformance);

                    }
                }
                log.debug("=============解析最新表现信息D1账户：[{}]");
            }else{
                log.debug("=============最新表现信息D1账户为空：[{}]");
            }

            //其他还款状态+有最新还款记录
            if(TheLatestPerformanceD1specs.size()>0){
                for(int i = 0;i<TheLatestPerformanceD1specs.size(); i++){
                    //此处解析新添加内容2020-01-21
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceD1spec = TheLatestPerformanceD1specs.get(i);
                    Element div = TheLatestPerformanceD1spec.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "非循环贷账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "非循环贷账户_" + aaa.trim();
                    }
                    //账户编号
                    // 五级分类
                    String ClassificationDesc = TheLatestPerformanceD1spec.select("tbody>tr").get(2).select("td").get(0).text();
                    String Classification = mapUtil.getItemIdByItemName2("p_five_level_classification",ClassificationDesc);
                    // 余额
                    String Balance = TheLatestPerformanceD1spec.select("tbody>tr").get(2).select("td").get(1).text();
                    //还款日期
                    String LastRepaymentDate = TheLatestPerformanceD1spec.select("tbody>tr").get(2).select("td").get(2).text();
                    //还款金额
                    String LatestRepaymentAmount = TheLatestPerformanceD1spec.select("tbody>tr").get(2).select("td").get(3).text();
                    //还款状态
                    String RepaymentState  = TheLatestPerformanceD1spec.select("tbody>tr").get(2).select("td").get(4).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceD1spec.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(0,4)+"-"+DateOfReport.substring(5,7)+"-"+DateOfReport.substring(8,10);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setClassification(Classification);
                    theLatestPerformance.setRepaymentState(RepaymentState);
                    theLatestPerformance.setLatestRepaymentAmount(LatestRepaymentAmount);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);



                }
            }

            //账户状态为结清时
            if(TheLatestPerformanceD1Cs.size()>0){
                for(int i = 0; i < TheLatestPerformanceD1Cs.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceD1C = TheLatestPerformanceD1Cs.get(i);
                    Element div = TheLatestPerformanceD1C.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "非循环贷账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "非循环贷账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceD1C.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_d1_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = TheLatestPerformanceD1C.select("tbody>tr").get(2).select("td").get(1).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceD1C.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息D1账户：[{}]");
            }else{
                log.debug("=============最新表现信息D1账户为空：[{}]");
            }


        }

        // R4账户
        public void getTheLatestPerformanceR4(Document document,List<TheLatestPerformance> theLatestPerformancelist) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（三）循环额度下分账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（三）循环额度下分账户"), body.indexOf("（四）循环贷账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            //呆账element
            Elements TheLatestPerformanceR4As = getTest.select("table:contains(账户状态)").select("table:contains(呆账)");

            Elements TheLatestPerformanceR4Bs = getTest.select("table:contains(账户状态)").select("table:contains(五级分类)");
            //结清element
            Elements TheLatestPerformanceR4Cs = getTest.select("table:contains(账户状态)").select("table:contains(结清)");

            //账户状态为呆账时
            if(TheLatestPerformanceR4As.size()>0){
                for(int i = 0; i < TheLatestPerformanceR4As.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR4A = TheLatestPerformanceR4As.get(i);
                    Element div = TheLatestPerformanceR4A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环额度下分账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "循环额度下分账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR4A.select("tbody>tr").get(6).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r4_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = TheLatestPerformanceR4A.select("tbody>tr").get(6).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR4A.select("tbody>tr").get(6).select("td").get(2).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR4A.select("tbody>tr").get(4).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R4账户：[{}]");
            }else{
                log.debug("=============最新表现信息R4账户为空：[{}]");
            }

            //账户状态除呆账和结清外时
            if(TheLatestPerformanceR4Bs.size()>0){
                for (int i = 0; i < TheLatestPerformanceR4Bs.size(); i++) {
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR4B = TheLatestPerformanceR4Bs.get(i);
                    Element div = TheLatestPerformanceR4B.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环额度下分账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "循环额度下分账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR4B.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r4_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = null;
                    // 转出月份
                    // 余额
                    String Balance = TheLatestPerformanceR4B.select("tbody>tr").get(2).select("td").get(2).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR4B.select("tbody>tr").get(2).select("td").get(7).text();
                    // 最近一次还款金额
                    // 五级分类
                    String Classification = TheLatestPerformanceR4B.select("tbody>tr").get(2).select("td").get(1).text();
                    Classification = mapUtil.getItemIdByItemName2("p_five_level_classification",Classification);
                    // 还款状态
                    //String RepaymentState = TheLatestPerformanceR4B.select("tbody>tr").get(2).select("td").get(4).text();
                    //RepaymentState = enumMap.get(RepaymentState);
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR4B.select("tbody>tr").get(0).select("th").text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setClassification(Classification);
                    theLatestPerformance.setDateOfReport(DateOfReport);
//				theLatestPerformance.setR4AccountStateDesc(AccountStateDesc);
                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R4账户：[{}]");
            }else{
                log.debug("=============最新表现信息R4账户为空：[{}]");
            }

            //账户状态为结清时
            if(TheLatestPerformanceR4Cs.size()>0){
                for(int i = 0; i < TheLatestPerformanceR4Cs.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR4C = TheLatestPerformanceR4Cs.get(i);
                    Element div = TheLatestPerformanceR4C.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环额度下分账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "循环额度下分账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR4C.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r4_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = TheLatestPerformanceR4C.select("tbody>tr").get(2).select("td").get(1).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR4C.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R4账户：[{}]");
            }else{
                log.debug("=============最新表现信息R4账户为空：[{}]");
            }


        }

        // R1账户
        public void getTheLatestPerformanceR1(Document document,List<TheLatestPerformance> theLatestPerformancelist) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（四）循环贷账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（四）循环贷账户"), body.indexOf("（五）贷记卡账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            //账户状态为呆账时的element
            Elements TheLatestPerformanceR1As = getTest.select("table:contains(账户状态)").select("table:contains(呆账)");
            //账户状态除呆账和结清时的element
            Elements TheLatestPerformanceR1Bs = getTest.select("table:contains(账户状态)").select("table:contains(五级分类)");
            //账户状态为结清时的element
            Elements TheLatestPerformanceR1Cs = getTest.select("table:contains(账户状态)").select("table:contains(结清)");

            //账户状态为呆账时
            if(TheLatestPerformanceR1As.size()>0){
                for(int i = 0; i < TheLatestPerformanceR1As.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR1A = TheLatestPerformanceR1As.get(i);
                    Element div = TheLatestPerformanceR1A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环贷账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "循环贷账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR1A.select("tbody>tr").get(6).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r1_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = TheLatestPerformanceR1A.select("tbody>tr").get(6).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR1A.select("tbody>tr").get(6).select("td").get(2).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR1A.select("tbody>tr").get(4).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R1账户：[{}]");
            }else{
                log.debug("=============最新表现信息R1账户为空：[{}]");
            }


            //账户状态除呆账和结清时
            if(TheLatestPerformanceR1Bs.size()>0){
                for (int i = 0; i < TheLatestPerformanceR1Bs.size(); i++) {
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR1B = TheLatestPerformanceR1Bs.get(i);
                    Element div = TheLatestPerformanceR1B.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环贷账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "循环贷账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR1B.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r1_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = null;
                    // 转出月份
                    // 余额
                    String Balance = TheLatestPerformanceR1B.select("tbody>tr").get(2).select("td").get(2).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR1B.select("tbody>tr").get(2).select("td").get(7).text();
                    // 最近一次还款金额
                    // 五级分类
                    String Classification = TheLatestPerformanceR1B.select("tbody>tr").get(2).select("td").get(1).text();
                    Classification = mapUtil.getItemIdByItemName2("p_five_level_classification",Classification);
                    // 还款状态
                    //String RepaymentState = TheLatestPerformanceR1B.select("tbody>tr").get(2).select("td").get(4).text();
                    //RepaymentState = enumMap.get(RepaymentState);
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR1B.select("tbody>tr").get(0).select("th").text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setClassification(Classification);
                    theLatestPerformance.setDateOfReport(DateOfReport);


                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R1账户：[{}]");
            }else{
                log.debug("=============最新表现信息R1账户为空：[{}]");
            }

            //账户状态为结清时
            if(TheLatestPerformanceR1Cs.size()>0){
                for(int i = 0; i < TheLatestPerformanceR1Cs.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR1C = TheLatestPerformanceR1Cs.get(i);
                    Element div = TheLatestPerformanceR1C.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环贷账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "循环贷账户_" + aaa.trim()
                        ;
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR1C.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r1_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = TheLatestPerformanceR1C.select("tbody>tr").get(2).select("td").get(1).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR1C.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R1账户：[{}]");
            }else{
                log.debug("=============最新表现信息R1账户为空：[{}]");
            }


        }

        // R2账户
        public void getTheLatestPerformanceR2(Document document,List<TheLatestPerformance> theLatestPerformancelist) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（五）贷记卡账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（五）贷记卡账户"), body.indexOf("（六）准贷记卡账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            //账户状态为呆账时的element
            Elements TheLatestPerformanceR2As = getTest.select("table:contains(账户状态)").select("table:contains(呆账)");
            //账户状态除呆账和销户时的element
            Elements TheLatestPerformanceR2B1s = getTest.select("table:contains(账户状态)").select("table:contains(已用额度)");
            Elements TheLatestPerformanceR2B2s = getTest.select("table:contains(最近一次还款日期)").select("table:contains(账单日)");
            //账户状态为销户时的element
            Elements TheLatestPerformanceR2Cs = getTest.select("table:contains(账户状态)").select("table:contains(销户)");
            //账户状态为未激活时element
            Elements TheLatestPerformanceR2Ds = getTest.select("table:contains(账户状态为)");

            //账户状态为呆账时
            if(TheLatestPerformanceR2As.size()>0){
                for(int i = 0; i < TheLatestPerformanceR2As.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR2A = TheLatestPerformanceR2As.get(i);
                    Element div = TheLatestPerformanceR2A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR2A.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = TheLatestPerformanceR2A.select("tbody>tr").get(2).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR2A.select("tbody>tr").get(2).select("td").get(2).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR2A.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R2账户：[{}]");
            }else{
                log.debug("=============最新表现信息R2账户为空：[{}]");
            }

            //账户状态为除呆账和销户时
            if(TheLatestPerformanceR2B1s.size()>0){
                for (int i = 0; i < TheLatestPerformanceR2B1s.size(); i++) {
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR2B1 = TheLatestPerformanceR2B1s.get(i);
                    Element TheLatestPerformanceR2B2 = TheLatestPerformanceR2B2s.get(i);
                    Element div = TheLatestPerformanceR2B1.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();

                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR2B1.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = null;
                    // 转出月份
                    // 余额
                    String Balance = TheLatestPerformanceR2B1.select("tbody>tr").get(2).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR2B2.select("tbody>tr").get(1).select("td").get(3).text();
                    // 最近一次还款金额
                    // 还款状态
                    //String RepaymentState = TheLatestPerformanceR2B.select("tbody>tr").get(2).select("td").get(3).text();
                    //RepaymentState = enumMap.get(RepaymentState);
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR2B1.select("tbody>tr").get(0).select("th").text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setBalance(Balance);

                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R2账户：[{}]");
            }else{
                log.debug("=============最新表现信息R2账户为空：[{}]");
            }

            //账户状态为销户时
            if(TheLatestPerformanceR2Cs.size()>0){
                for(int i = 0; i < TheLatestPerformanceR2Cs.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR2C = TheLatestPerformanceR2Cs.get(i);
                    Element div = TheLatestPerformanceR2C.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR2C.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = TheLatestPerformanceR2C.select("tbody>tr").get(2).select("td").get(1).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR2C.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R2账户：[{}]");
            }else{
                log.debug("=============最新表现信息R2账户为空：[{}]");
            }

            //账户状态为未激活时
            if(TheLatestPerformanceR2Ds.size()>0){
                for(int i=0;i<TheLatestPerformanceR2Ds.size();i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR2D = TheLatestPerformanceR2Ds.get(i);
                    Element div = TheLatestPerformanceR2D.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = "未激活";
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 信息报告日期
                    String bbb = TheLatestPerformanceR2D.select("tbody>tr>th").text();
                    String DateOfReport = bbb.substring(0,bbb.indexOf("，"));
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R2账户：[{}]");
            }else{
                log.debug("=============最新表现信息R2账户为空：[{}]");
            }
        }

        // R3账户
        public void getTheLatestPerformanceR3(Document document,List<TheLatestPerformance> theLatestPerformancelist) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（六）准贷记卡账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（六）准贷记卡账户"), body.indexOf("（七）相关还款责任信息"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            //账户状态为呆账时的element
            Elements TheLatestPerformanceR3As = getTest.select("table:contains(账户状态)").select("table:contains(呆账)");
            //账户状态为除呆账和销户时的element
            Elements TheLatestPerformanceR3Bs = getTest.select("table:contains(账户状态)").select("table:contains(账单日)");
            //账户状态为销户时的element
            Elements TheLatestPerformanceR3Cs = getTest.select("table:contains(账户状态)").select("table:contains(销户)");
            //账户状态为未激活时element
            Elements TheLatestPerformanceR3Ds = getTest.select("table:contains(账户状态为)");

            //账户状态为呆账时
            if(TheLatestPerformanceR3As.size()>0){
                for(int i = 0; i < TheLatestPerformanceR3As.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR3A = TheLatestPerformanceR3As.get(i);
                    Element div = TheLatestPerformanceR3A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "准贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "准贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR3A.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = TheLatestPerformanceR3A.select("tbody>tr").get(2).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR3A.select("tbody>tr").get(2).select("td").get(2).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR3A.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);


                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R3账户：[{}]");
            }else{
                log.debug("=============最新表现信息R3账户为空：[{}]");
            }

            //账户状态为除呆账和销户时
            if(TheLatestPerformanceR3Bs.size()>0){
                for (int i = 0; i < TheLatestPerformanceR3Bs.size(); i++) {
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR3B = TheLatestPerformanceR3Bs.get(i);
                    Element div = TheLatestPerformanceR3B.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "准贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "准贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR3B.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = null;
                    // 转出月份
                    // 余额
                    String Balance = TheLatestPerformanceR3B.select("tbody>tr").get(2).select("td").get(1).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = TheLatestPerformanceR3B.select("tbody>tr").get(2).select("td").get(6).text();
                    // 最近一次还款金额
                    //String LatestRepaymentAmount = TheLatestPerformanceR3B.select("tbody>tr").get(2).select("td").get(2).text();
                    // 还款状态 B2
                    //String RepaymentState = TheLatestPerformanceR3B.select("tbody>tr").get(2).select("td").get(3).text();
                    //RepaymentState = enumMap.get(RepaymentState);
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR3B.select("tbody>tr").get(0).select("th").text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setBalance(Balance);
                    theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R3账户：[{}]");
            }else{
                log.debug("=============最新表现信息R3账户为空：[{}]");
            }

            //账户状态为销户时
            if(TheLatestPerformanceR3Cs.size()>0){
                for(int i = 0; i < TheLatestPerformanceR3Cs.size(); i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR3C = TheLatestPerformanceR3Cs.get(i);
                    Element div = TheLatestPerformanceR3C.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = TheLatestPerformanceR3C.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 关闭日期
                    String ClosingDate = TheLatestPerformanceR3C.select("tbody>tr").get(2).select("td").get(1).text();
                    // 信息报告日期
                    String DateOfReport = TheLatestPerformanceR3C.select("tbody>tr").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setClosingDate(ClosingDate);
                    theLatestPerformance.setDateOfReport(DateOfReport);
//				theLatestPerformance.setR2ORR3AccountStateDesc(AccountStateDesc);
                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R3账户：[{}]");
            }else{
                log.debug("=============最新表现信息R3账户为空：[{}]");
            }

            //账户状态为未激活时
            if(TheLatestPerformanceR3Ds.size()>0){
                for(int i=0;i<TheLatestPerformanceR3Ds.size();i++){
                    TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                    Element TheLatestPerformanceR3D = TheLatestPerformanceR3Ds.get(i);
                    Element div = TheLatestPerformanceR3D.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf("（")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa.trim();
                    }
                    // 账户状态
                    String AccountStateDesc = "未激活";
                    String 	AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 信息报告日期
                    String bbb = TheLatestPerformanceR3D.select("tbody>tr>th").text();
                    String DateOfReport = bbb.substring(0,bbb.indexOf("，"));
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    theLatestPerformance.setReportId(ReportId);
                    theLatestPerformance.setAccountNumber(AccountNumber);
                    theLatestPerformance.setAccountState(AccountState);
                    theLatestPerformance.setDateOfReport(DateOfReport);

                    theLatestPerformancelist.add(theLatestPerformance);
                }
                log.debug("=============解析最新表现信息R3账户：[{}]");
            }else{
                log.debug("=============最新表现信息R3账户为空：[{}]");
            }


        }



        // D1账户
        public void getLastMonthlyD1(Document document,List<LastMonthly> lastMonthlyList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（二）非循环贷账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（二）非循环贷账户"), body.indexOf("（三）循环额度下分账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements LastMonthlyD1As = getTest.select("table:contains(五级分类)").select("table:contains(账户状态)");
            Elements LastMonthlyD1Bs = getTest.select("table:contains(当前逾期期数)");
            if(LastMonthlyD1As.size()>0){
                for (int i = 0; i < LastMonthlyD1As.size(); i++) {
                    Element LastMonthlyD1A = LastMonthlyD1As.get(i);
                    Element LastMonthlyD1B = LastMonthlyD1Bs.get(i);
                    Element div = LastMonthlyD1A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();

                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "非循环贷账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "非循环贷账户_" + aaa.trim();
                    }
                    // 月份
                    String Month = LastMonthlyD1A.select("tbody>tr").get(0).select("th").get(0).text().substring(2, 10);
                    // 账户状态
                    String AccountStateDesc = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_d1_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(2).text();
                    // 五级分类
                    String ClassificationDesc = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(1).text();
                    String Classification = mapUtil.getItemIdByItemName2("p_five_level_classification",ClassificationDesc);
                    // 剩余还款期数
                    String RemainingRepayment = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(3).text();
                    // 结算/应还款日
                    String SettlementDay = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(5).text();
                    // 本月应还款
                    String Reimbursement = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(4).text();
                    // 本月实还款
                    String Repayments = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(6).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = LastMonthlyD1A.select("tbody>tr").get(2).select("td").get(7).text();
                    // 当前逾期期数
                    String CurrentOverduePeriod = LastMonthlyD1B.select("tbody>tr").get(1).select("td").get(0).text();
                    // 当前逾期总额
                    String CurrentOverdueTotal = LastMonthlyD1B.select("tbody>tr").get(1).select("td").get(1).text();
                    // 逾期 31—60天 未还本金
                    String Overdue31To60Amount = LastMonthlyD1B.select("tbody>tr").get(1).select("td").get(2).text();
                    // 逾期 61—90天 未还本金
                    String Overdue61To90Amount = LastMonthlyD1B.select("tbody>tr").get(1).select("td").get(3).text();
                    // 逾期 91—180天 未还本金
                    String Overdue91To180Amount = LastMonthlyD1B.select("tbody>tr").get(1).select("td").get(4).text();
                    // 逾期 180天以上 未还本金
                    String OverdueOver180Amount = LastMonthlyD1B.select("tbody>tr").get(1).select("td").get(5).text();
                    // 信息报告日期
                    String DateOfReport = LastMonthlyD1A.select("tbody>tr").get(0).select("th").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);


                    LastMonthly lastMonthly = new LastMonthly();
                    lastMonthly.setReportId(ReportId);
                    lastMonthly.setAccountNumber(AccountNumber);
                    lastMonthly.setMonth(Month);
                    lastMonthly.setAccountState(AccountState);
                    lastMonthly.setBalance(Balance);
                    lastMonthly.setClassification(Classification);
                    lastMonthly.setRemainingRepayment(RemainingRepayment);
                    lastMonthly.setSettlementDay(SettlementDay);
                    lastMonthly.setReimbursement(Reimbursement);
                    lastMonthly.setRepayments(Repayments);
                    lastMonthly.setLastRepaymentDate(LastRepaymentDate);
                    lastMonthly.setCurrentOverduePeriod(CurrentOverduePeriod);
                    lastMonthly.setCurrentOverdueTotal(CurrentOverdueTotal);
                    lastMonthly.setOverdue31To60Amount(Overdue31To60Amount);
                    lastMonthly.setOverdue61To90Amount(Overdue61To90Amount);
                    lastMonthly.setOverdue91To180Amount(Overdue91To180Amount);
                    lastMonthly.setOverdueOver180Amount(OverdueOver180Amount);
                    lastMonthly.setDateOfReport(DateOfReport);

                    lastMonthlyList.add(lastMonthly);

                }
                log.debug("=============解析最近一次月度表现信息D1账户：[{}]");
            }else{
                log.debug("=============最近一次月度表现信息D1账户为空：[{}]");
            }
        }

        // R4账户
        public void getLastMonthlyR4(Document document,List<LastMonthly> lastMonthlyList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（三）循环额度下分账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（三）循环额度下分账户"), body.indexOf("（四）循环贷账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements LastMonthlyR4As = getTest.select("table:contains(五级分类)").select("table:contains(账户状态)");
            Elements LastMonthlyR4Bs = getTest.select("table:contains(当前逾期期数)");
            if(LastMonthlyR4As.size()>0){
                for (int i = 0; i < LastMonthlyR4As.size(); i++) {
                    Element LastMonthlyR4A = LastMonthlyR4As.get(i);
                    Element LastMonthlyR4B = LastMonthlyR4Bs.get(i);
                    Element div = LastMonthlyR4A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环额度下分账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "循环额度下分账户_" + aaa.trim();
                    }

                    // 月份
                    String Month = LastMonthlyR4A.select("tbody>tr").get(0).select("th").get(0).text().substring(2, 10);
                    // 账户状态
                    String AccountStateDesc = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r4_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(2).text();
                    // 五级分类
                    String ClassificationDesc = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(1).text();
                    String Classification = mapUtil.getItemIdByItemName2("p_five_level_classification",ClassificationDesc);
                    // 剩余还款期数
                    String RemainingRepayment = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(3).text();
                    // 结算/应还款日
                    String SettlementDay = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(5).text();
                    // 本月应还款
                    String Reimbursement = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(4).text();
                    // 本月实还款
                    String Repayments = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(6).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = LastMonthlyR4A.select("tbody>tr").get(2).select("td").get(7).text();
                    // 当前逾期期数
                    String CurrentOverduePeriod = LastMonthlyR4B.select("tbody>tr").get(1).select("td").get(0).text();
                    // 当前逾期总额
                    String CurrentOverdueTotal = LastMonthlyR4B.select("tbody>tr").get(1).select("td").get(1).text();
                    // 逾期 31—60天 未还本金
                    String Overdue31To60Amount = LastMonthlyR4B.select("tbody>tr").get(1).select("td").get(2).text();
                    // 逾期 61—90天 未还本金
                    String Overdue61To90Amount = LastMonthlyR4B.select("tbody>tr").get(1).select("td").get(3).text();
                    // 逾期 91—180天 未还本金
                    String Overdue91To180Amount = LastMonthlyR4B.select("tbody>tr").get(1).select("td").get(4).text();
                    // 逾期 180天以上 未还本金
                    String OverdueOver180Amount = LastMonthlyR4B.select("tbody>tr").get(1).select("td").get(5).text();
                    // 信息报告日期
                    String DateOfReport = LastMonthlyR4A.select("tbody>tr").get(0).select("th").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    LastMonthly lastMonthly = new LastMonthly();
                    lastMonthly.setReportId(ReportId);
                    lastMonthly.setAccountNumber(AccountNumber);
                    lastMonthly.setMonth(Month);
                    lastMonthly.setAccountState(AccountState);
                    lastMonthly.setBalance(Balance);
                    lastMonthly.setClassification(Classification);
                    lastMonthly.setRemainingRepayment(RemainingRepayment);
                    lastMonthly.setSettlementDay(SettlementDay);
                    lastMonthly.setReimbursement(Reimbursement);
                    lastMonthly.setRepayments(Repayments);
                    lastMonthly.setLastRepaymentDate(LastRepaymentDate);
                    lastMonthly.setCurrentOverduePeriod(CurrentOverduePeriod);
                    lastMonthly.setCurrentOverdueTotal(CurrentOverdueTotal);
                    lastMonthly.setOverdue31To60Amount(Overdue31To60Amount);
                    lastMonthly.setOverdue61To90Amount(Overdue61To90Amount);
                    lastMonthly.setOverdue91To180Amount(Overdue91To180Amount);
                    lastMonthly.setOverdueOver180Amount(OverdueOver180Amount);
                    lastMonthly.setDateOfReport(DateOfReport);

                    lastMonthlyList.add(lastMonthly);

                }
                log.debug("=============解析最近一次月度表现信息R4账户：[{}]");
            }else{
                log.debug("=============最近一次月度表现信息R4账户为空：[{}]");
            }
        }

        // R1账户
        public void getLastMonthlyR1(Document document,List<LastMonthly> lastMonthlyList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（四）循环贷账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（四）循环贷账户"), body.indexOf("（五）贷记卡账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements LastMonthlyR1As = getTest.select("table:contains(五级分类)").select("table:contains(账户状态)");
            Elements LastMonthlyR1Bs = getTest.select("table:contains(当前逾期期数)");
            if(LastMonthlyR1As.size()>0){
                for (int i = 0; i < LastMonthlyR1As.size(); i++) {
                    Element LastMonthlyR1A = LastMonthlyR1As.get(i);
                    Element LastMonthlyR1B = LastMonthlyR1Bs.get(i);
                    Element div = LastMonthlyR1A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "循环贷账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "循环贷账户_" + aaa.trim();
                    }
                    // 月份
                    String Month = LastMonthlyR1A.select("tbody>tr").get(0).select("th").get(0).text().substring(2, 10);
                    // 账户状态
                    String AccountStateDesc = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r1_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(2).text();
                    // 五级分类
                    String ClassificationDesc = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(1).text();
                    String Classification = mapUtil.getItemIdByItemName2("p_five_level_classification",ClassificationDesc);
                    // 剩余还款期数
                    String RemainingRepayment = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(3).text();
                    // 结算/应还款日
                    String SettlementDay = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(5).text();
                    // 本月应还款
                    String Reimbursement = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(4).text();
                    // 本月实还款
                    String Repayments = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(6).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = LastMonthlyR1A.select("tbody>tr").get(2).select("td").get(7).text();
                    // 当前逾期期数
                    String CurrentOverduePeriod = LastMonthlyR1B.select("tbody>tr").get(1).select("td").get(0).text();
                    // 当前逾期总额
                    String CurrentOverdueTotal = LastMonthlyR1B.select("tbody>tr").get(1).select("td").get(1).text();
                    // 逾期 31—60天 未还本金
                    String Overdue31To60Amount = LastMonthlyR1B.select("tbody>tr").get(1).select("td").get(2).text();
                    // 逾期 61—90天 未还本金
                    String Overdue61To90Amount = LastMonthlyR1B.select("tbody>tr").get(1).select("td").get(3).text();
                    // 逾期 91—180天 未还本金
                    String Overdue91To180Amount = LastMonthlyR1B.select("tbody>tr").get(1).select("td").get(4).text();
                    // 逾期 180天以上 未还本金
                    String OverdueOver180Amount = LastMonthlyR1B.select("tbody>tr").get(1).select("td").get(5).text();
                    // 信息报告日期
                    String DateOfReport = LastMonthlyR1A.select("tbody>tr").get(0).select("th").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    LastMonthly lastMonthly = new LastMonthly();
                    lastMonthly.setReportId(ReportId);
                    lastMonthly.setAccountNumber(AccountNumber);
                    lastMonthly.setMonth(Month);
                    lastMonthly.setAccountState(AccountState);
                    lastMonthly.setBalance(Balance);
                    lastMonthly.setClassification(Classification);
                    lastMonthly.setRemainingRepayment(RemainingRepayment);
                    lastMonthly.setSettlementDay(SettlementDay);
                    lastMonthly.setReimbursement(Reimbursement);
                    lastMonthly.setRepayments(Repayments);
                    lastMonthly.setLastRepaymentDate(LastRepaymentDate);
                    lastMonthly.setCurrentOverduePeriod(CurrentOverduePeriod);
                    lastMonthly.setCurrentOverdueTotal(CurrentOverdueTotal);
                    lastMonthly.setOverdue31To60Amount(Overdue31To60Amount);
                    lastMonthly.setOverdue61To90Amount(Overdue61To90Amount);
                    lastMonthly.setOverdue91To180Amount(Overdue91To180Amount);
                    lastMonthly.setOverdueOver180Amount(OverdueOver180Amount);
                    lastMonthly.setDateOfReport(DateOfReport);

                    lastMonthlyList.add(lastMonthly);

                }
                log.debug("=============解析最近一次月度表现信息R1账户：[{}]");
            }else{
                log.debug("=============最近一次月度表现信息R1账户为空：[{}]");
            }


        }

        // R2账户
        public void getLastMonthlyR2(Document document,List<LastMonthly> lastMonthlyList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（五）贷记卡账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（五）贷记卡账户"), body.indexOf("（六）准贷记卡账户"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements LastMonthlyR2As = getTest.select("table:contains(账户状态)").select("table:contains(剩余分期期数)");
            Elements LastMonthlyR2Bs = getTest.select("table:contains(账单日)");
            if(LastMonthlyR2As.size()>0){
                for (int i = 0; i < LastMonthlyR2As.size(); i++) {
                    Element LastMonthlyR2A = LastMonthlyR2As.get(i);
                    Element LastMonthlyR2B = LastMonthlyR2Bs.get(i);
                    Element div = LastMonthlyR2A.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "贷记卡账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "贷记卡账户_" + aaa.trim();
                    }
                    // 月份
                    String Month = LastMonthlyR2A.select("tbody>tr").get(0).select("th").get(0).text().substring(2, 10);
                    // 账户状态
                    String AccountStateDesc = LastMonthlyR2A.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 余额
                    String Balance = LastMonthlyR2A.select("tbody>tr").get(2).select("td").get(1).text();
                    // 已用额度
                    String AmountUsed = LastMonthlyR2A.select("tbody>tr").get(2).select("td").get(2).text();
                    // 未出单的大额专项分期余额
                    String StagingBalance = LastMonthlyR2A.select("tbody>tr").get(2).select("td").get(3).text();
                    // 剩余还款期数
                    String RemainingRepayment = LastMonthlyR2A.select("tbody>tr").get(2).select("td").get(4).text();
                    // 结算/应还款日(R2账单日)
                    String SettlementDay = LastMonthlyR2B.select("tbody>tr").get(1).select("td").get(0).text();
                    // 本月应还款
                    String Reimbursement = LastMonthlyR2B.select("tbody>tr").get(1).select("td").get(1).text();
                    // 本月实还款
                    String Repayments = LastMonthlyR2B.select("tbody>tr").get(1).select("td").get(2).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = LastMonthlyR2B.select("tbody>tr").get(1).select("td").get(3).text();
                    // 当前逾期期数
                    String CurrentOverduePeriod = LastMonthlyR2B.select("tbody>tr").get(1).select("td").get(4).text();
                    // 当前逾期总额
                    String CurrentOverdueTotal = LastMonthlyR2B.select("tbody>tr").get(1).select("td").get(5).text();
                    // 最近 6 个月平均使用额度
                    String AverageUsageQuota = LastMonthlyR2A.select("tbody>tr").get(2).select("td").get(5).text();
                    // 最大使用额度
                    String AmountOfUse = LastMonthlyR2A.select("tbody>tr").get(2).select("td").get(6).text();
                    // 信息报告日期
                    String DateOfReport = LastMonthlyR2A.select("tbody>tr").get(0).select("th").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    LastMonthly lastMonthly = new LastMonthly();
                    lastMonthly.setAccountNumber(AccountNumber);
                    lastMonthly.setAmountUsed(AmountUsed);
                    lastMonthly.setStagingBalance(StagingBalance);
                    lastMonthly.setLastRepaymentDate(LastRepaymentDate);
                    lastMonthly.setAverageUsageQuota(AverageUsageQuota);
                    lastMonthly.setAmountOfUse(AmountOfUse);
                    lastMonthly.setReportId(ReportId);
                    lastMonthly.setMonth(Month);
                    lastMonthly.setAccountState(AccountState);
                    lastMonthly.setBalance(Balance);
                    lastMonthly.setRemainingRepayment(RemainingRepayment);
                    lastMonthly.setSettlementDay(SettlementDay);
                    lastMonthly.setReimbursement(Reimbursement);
                    lastMonthly.setRepayments(Repayments);
                    lastMonthly.setCurrentOverduePeriod(CurrentOverduePeriod);
                    lastMonthly.setCurrentOverdueTotal(CurrentOverdueTotal);
                    lastMonthly.setDateOfReport(DateOfReport);

                    lastMonthlyList.add(lastMonthly);

                }
                log.debug("=============解析最近一次月度表现信息R2账户：[{}]");
            }else{
                log.debug("=============最近一次月度表现信息R2账户为空：[{}]");
            }


        }

        // R3账户
        public void getLastMonthlyR3(Document document,List<LastMonthly> lastMonthlyList) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            String body = document.toString();
            if (body.indexOf("（六）准贷记卡账户") == -1) {
                return;
            }
            body = body.substring(body.indexOf("（六）准贷记卡账户"), body.indexOf("（七）相关还款责任信息"));
            Elements getTest = Jsoup.parse(body).getAllElements();
            Elements LastMonthlyR3s = getTest.select("table:contains(账户状态)").select("table:contains(账单日)");
            if(LastMonthlyR3s.size()>0){
                for (int i = 0; i < LastMonthlyR3s.size(); i++) {
                    Element LastMonthlyR3 = LastMonthlyR3s.get(i);
                    Element div = LastMonthlyR3.previousElementSiblings().select("div").get(0);
                    String aaa = div.text();
                    // 账户编号
                    String AccountNumber = null;
                    if (aaa.length() > 5) {
                        AccountNumber = "准贷记卡账户_" + aaa.substring(0, aaa.indexOf(" ")).trim();
                    } else {
                        AccountNumber = "准贷记卡账户_" + aaa.trim();
                    }
                    // 月份
                    String Month = LastMonthlyR3.select("tbody>tr").get(0).select("th").get(0).text().substring(2, 10);
                    // 账户状态
                    String AccountStateDesc = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(0).text();
                    String AccountState = mapUtil.getItemIdByItemName2("p_r2orr3_loan_state",AccountStateDesc);
                    // 透支余额
                    String Balance = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(1).text();
                    // 结算/应还款日（账单日）
                    String SettlementDay = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(4).text();
                    // 本月实还款
                    String Repayments = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(5).text();
                    // 最近一次还款日期
                    String LastRepaymentDate = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(6).text();
                    // 透支 180 天以上
                    String OverdraftDay = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(7).text();
                    // 最近 6 个月平均透支余额
                    String AverageOverdraftBalance = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(2).text();
                    // 最大透支余额
                    String OverdraftBalance = LastMonthlyR3.select("tbody>tr").get(2).select("td").get(3).text();
                    //信息报告日期
                    String DateOfReport = LastMonthlyR3.select("tbody>tr").get(0).select("th").get(0).text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);

                    LastMonthly lastMonthly = new LastMonthly();

                    lastMonthly.setAccountNumber(AccountNumber);
                    lastMonthly.setLastRepaymentDate(LastRepaymentDate);
                    lastMonthly.setOverdraftDay(OverdraftDay);
                    lastMonthly.setReportId(ReportId);
                    lastMonthly.setMonth(Month);
                    lastMonthly.setAccountState(AccountState);
                    lastMonthly.setBalance(Balance);
                    lastMonthly.setSettlementDay(SettlementDay);
                    lastMonthly.setAverageOverdraftBalance(AverageOverdraftBalance);
                    lastMonthly.setOverdraftBalance(OverdraftBalance);
                    lastMonthly.setRepayments(Repayments);
                    lastMonthly.setDateOfReport(DateOfReport);

                    lastMonthlyList.add(lastMonthly);

                }
                log.debug("=============解析最近一次月度表现信息R3账户：[{}]");
            }else{
                log.debug("=============最近一次月度表现信息R3账户为空：[{}]");
            }
        }

        /**
         * LastTwentyfourMonth 最近 24 个月还款状态信息
         */
        public void getLastTwentyfourMonth(Document document,ReportMessage reportMessageData) {
            List<LastFiveYears> list  = reportMessageData.getCrt2_p_lastfiveyears();
            if(CollectionUtils.isEmpty(list)){
                return;
            }
            List<LastTwentyfourMonth> lastTwentyfourMonths = new ArrayList<>();
            Map<String, List<LastFiveYears>> collect =list.stream().collect(Collectors.groupingBy(LastFiveYears::getAccountNumber));
            for (Map.Entry<String, List<LastFiveYears>> stringListEntry : collect.entrySet()) {
                List<LastFiveYears> collect1 = stringListEntry.getValue().stream().sorted((s1, s2) -> s2.getMonth().compareTo(s1.getMonth())).collect(Collectors.toList());
                int month=collect1.size()>24?24:collect1.size();
                for(int i=0;i<month;i++){
                    lastTwentyfourMonths.add(JSON.parseObject(JSON.toJSONString(collect1.get(i)),LastTwentyfourMonth.class));
                }
            }
            reportMessageData.setCrt2_p_lasttwentyfourmonth(lastTwentyfourMonths);
        }

        /**
         * LastFiveYears 最近 5 年内的历史表现信息
         */
        public void getLastFiveYears(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            Elements LastFiveYearss = document.select("table:contains(的还款记录 )");
//		System.out.println(LastFiveYearss.size());
//		System.out.println(LastFiveYearss.get(0));

            if(LastFiveYearss.size()>0){
                List<LastFiveYears> lastFiveYearsList = new ArrayList<>();
//			List<HtmlFiveYearList> htmlFiveYearListList = new ArrayList<>();

                for (int i = 0; i < LastFiveYearss.size(); i++) {
                    List<LastFiveYears> lastFiveYearsListInsert=new ArrayList<LastFiveYears>();
                    Element LastFiveYears = LastFiveYearss.get(i);
                    Element div = LastFiveYears.previousElementSiblings().select("div").first();
                    // 账户编号
                    Element div2 = LastFiveYears.previousElementSiblings().select("div.t2").select("div.f-mgtop").first();
                    String AccountNumber = null;
                    if (div.text().length() > 5) {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_"
                                + div.text().substring(0, div.text().indexOf("（"));
                    } else {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_" + div.text();
                    }
                    // 起始年月
                    String str = LastFiveYears.select("table>tbody>tr").get(0).select("th").text();
                    String BeginningYear = str.substring(0, str.indexOf("—"));
                    BeginningYear = BeginningYear.substring(0,4)+"-"+BeginningYear.substring(5,7);
                    // 截止年月
                    String EndYear = str.substring(str.indexOf("—") + 1, str.indexOf("的"));
                    EndYear = EndYear.substring(0,4)+"-"+EndYear.substring(5,7);
                    // 月数
                    Elements Moms = LastFiveYears.select("table>tbody>tr>td");
                    int num = 0;
                    for (int j = 0; j < Moms.size(); j++) {
                        if (Moms.get(j).text().length() != 0) {
                            num++;
                        }
                        num = num + 0;
                    }
                    String Mom = Integer.toString(num / 2);
                    // 月份
                    String Month = null;
                    // 还款状态
                    String RepaymentState = null;
                    // 逾期（透支）总额
                    String TotalOverdue = null;
                    String yea = "";

                    for (int y = 2; y < LastFiveYears.select("table>tbody>tr").size(); y += 2) {
                        if (y % 2 == 0) {
                            yea = LastFiveYears.select("table>tbody>tr").get(y).select("th").text();
                        } else {
                            continue;
                        }
                        int mont = 1;
                        for (int m = 0; m < 12; m++) {
                            String mon = LastFiveYears.select("table>tbody>tr").get(y).select("td").get(m).text();
                            if (!("".equals(mon))) {
                                mont++;
                            } else {
                                mont++;
                                continue;
                            }
                            //Month = yea + Integer.toString(mont - 1);
                            Month = yea +"-"+String.format("%02d",mont-1);
                            RepaymentState = LastFiveYears.select("table>tbody>tr").get(y).select("td").get(m).text();
                            TotalOverdue = LastFiveYears.select("table>tbody>tr").get(y + 1).select("td").get(m).text();
                            LastFiveYears lastFiveYears = new LastFiveYears();
                            lastFiveYears.setReportId(ReportId);
                            lastFiveYears.setAccountNumber(AccountNumber);
                            lastFiveYears.setBeginningYear(BeginningYear);
                            lastFiveYears.setEndYear(EndYear);
                            lastFiveYears.setMom(Mom);
                            lastFiveYears.setMonth(Month);
                            lastFiveYears.setRepaymentState(RepaymentState);
                            lastFiveYears.setTotalOverdue(TotalOverdue);

                            lastFiveYearsList.add(lastFiveYears);
                            lastFiveYearsListInsert.add(lastFiveYears);
                        }
                    }
                    reportMessageData.setCrt2_p_lastfiveyears(lastFiveYearsList);
                }
                log.debug("=============解析最近 5 年内的历史表现信息：[{}]",lastFiveYearsList);
            }else{
                log.debug("=============最近 5 年内的历史表现信息为空：[{}]");
            }
        }

        /**
         * SpecialTransaction 特殊交易信息
         *
         * @param document
         */
        public void getSpecialTransaction(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            Elements SpecialTransactions = document.select("table:contains(特殊交易类型)");


            if(SpecialTransactions.size()>0){
                List<SpecialTransaction> specialTransactionList = new ArrayList<>();

                // 特殊交易个数
                int count = 0;
                for(int i=0; i<SpecialTransactions.size(); i++){
                    Elements SpecialTransaction = SpecialTransactions.get(i).select("tbody>tr");
                    for(int j = 1;j<SpecialTransaction.size(); j++){
                        count++;
                    }
                }
                String SpecialTransactionNumber = Integer.toString(count);



                for(int i=0; i<SpecialTransactions.size(); i++){

                    Element SpecialTransactionbo =SpecialTransactions.get(i);
                    Elements SpecialTransaction = SpecialTransactions.get(i).select("tbody>tr");
                    // 账户编号
                    Element div1 = SpecialTransactionbo.previousElementSiblings().select("div.tnt").select("div.f-tleft")
                            .first();
                    Element div2 = SpecialTransactionbo.previousElementSiblings().select("div.t2").select("div.f-mgtop").first();

                    String AccountNumber = null;
                    if (div1.text().indexOf("授信协议标识") != -1) {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_"
                                + div1.text().substring(0, div1.text().indexOf("（"));
                    } else {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_" + div1.text();
                    }
                    for(int j = 1;j<SpecialTransaction.size(); j++){
                        SpecialTransaction specialTransaction = new SpecialTransaction();
                        Element SpecialTransaction1 =  SpecialTransaction.get(j);

                        // 特殊交易类型
                        String SpecialTransactionTypeDesc = SpecialTransaction1.select("td").get(0).text();
                        String SpecialTransactionType = mapUtil.getItemIdByItemName2("p_special_lending",SpecialTransactionTypeDesc);
                        // 特殊交易发生日期
                        String SpecialTransactionDate = SpecialTransaction1.select("td").get(1).text();
                        // 到期日期变更月数
                        String ExpirationDate = SpecialTransaction1.select("td").get(2).text();
                        // 特殊交易发生金额
                        String SpecialTransactionAmount = SpecialTransaction1.select("td").get(3).text();
                        // 特殊交易明细记录
                        String SpecialTransactionDetails = SpecialTransaction1.select("td").get(4).text();

                        specialTransaction.setReportId(ReportId);
                        specialTransaction.setSpecialTransactionNumber(SpecialTransactionNumber);
                        specialTransaction.setAccountNumber(AccountNumber);
                        specialTransaction.setSpecialTransactionType(SpecialTransactionType);
                        specialTransaction.setSpecialTransactionDate(SpecialTransactionDate);
                        specialTransaction.setExpirationDate(ExpirationDate);
                        specialTransaction.setSpecialTransactionAmount(SpecialTransactionAmount);
                        specialTransaction.setSpecialTransactionDetails(SpecialTransactionDetails);

                        specialTransactionList.add(specialTransaction);
                    }
                }
                reportMessageData.setCrt2_p_specialtransaction(specialTransactionList);
                log.debug("=============解析特殊交易信息：[{}]",specialTransactionList);
            }else{
                log.debug("=============特殊交易信息为空：[{}]");
            }
        }

        /**
         * SpecialEvents 特殊事件说明信息
         */
        public void getSpecialEvents(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            Elements SpecialEventss = document.select("table:contains(特殊事件说明)");
            if(SpecialEventss.size()>0){
                List<SpecialEvents> SpecialEventsList = new ArrayList<>();
                for (int i = 0; i < SpecialEventss.size(); i++) {
                    SpecialEvents specialEvents = new SpecialEvents();
                    Element SpecialEvents = SpecialEventss.get(i);
                    Element div = SpecialEvents.previousElementSiblings().select("div").first();
                    // 账户编号
                    Element div2 = SpecialEvents.previousElementSiblings().select("div.t2").select("div.f-mgtop").first();
                    String AccountNumber = null;
                    if (div.text().length() > 5) {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_"
                                + div.text().substring(0, div.text().indexOf("（"));
                    } else {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_" + div.text();
                    }
                    // 特殊事件说明个数
                    String SpecialEventDescription = Integer.toString(SpecialEventss.size());
                    // 特殊事件发生月份
                    String str = SpecialEvents.select("table>tbody>tr").get(1).text();
                    String SpecialEventsMonth = str.substring(3, str.indexOf("月") + 1);
                    // 特殊事件类型
                    String SpecialEventTypeDesc = str.substring(str.indexOf("月") + 1, str.length() - 1);
                    String SpecialEventType = mapUtil.getItemIdByItemName2("p_loan_event",SpecialEventTypeDesc);

                    specialEvents.setReportId(ReportId);
                    specialEvents.setAccountNumber(AccountNumber);
                    specialEvents.setSpecialEventDescription(SpecialEventDescription);
                    specialEvents.setSpecialEventsMonth(SpecialEventsMonth);
                    specialEvents.setSpecialEventType(SpecialEventType);

                    SpecialEventsList.add(specialEvents);
                }
                reportMessageData.setCrt2_p_specialevents(SpecialEventsList);
                log.debug("=============解析特殊事件说明信息：[{}]",SpecialEventsList);
            }else{
                log.debug("=============特殊事件说明信息为空：[{}]");
            }

        }

        /**
         * LargeAmountSpecial 大额专项分期信息
         *
         * @param document
         */
        public void getLargeAmountSpecial(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 大额专项分期信息
            Elements LargeAmountSpecials = document.select("table:contains(大额专项分期信息)");
            if(LargeAmountSpecials.size()>0){
                List<LargeAmountSpecial> largeAmountSpecialList = new ArrayList<>();
                for (int i = 0; i < LargeAmountSpecials.size(); i++) {
                    LargeAmountSpecial largeAmountSpecial = new LargeAmountSpecial();
                    Element LargeAmountSpecial = LargeAmountSpecials.get(i);
                    // 账户编号
                    Element div1 = LargeAmountSpecial.previousElementSiblings().select("div.tnt").first();
                    Element div2 = LargeAmountSpecial.previousElementSiblings().select("div.t2").select("div.f-mgtop").first();
                    String AccountNumber = null;
                    if (div1.text().indexOf("授信协议标识") != -1) {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_"
                                + div1.text().substring(0, div1.text().indexOf("（"));
                    } else {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_" + div1.text();
                    }
                    // 大额专项分期笔数
                    String StagingPenNumber = Integer.toString(LargeAmountSpecials.size());
                    // 大额专项分期额度
                    String InstallmentQuota = LargeAmountSpecial.select("tbody>tr").get(2).select("td").get(0).text();
                    // 分期额度生效日期
                    String EffectiveDate = LargeAmountSpecial.select("tbody>tr").get(2).select("td").get(1).text();
                    // 分期额度到期日期
                    String ExpirationDate = LargeAmountSpecial.select("tbody>tr").get(2).select("td").get(2).text();
                    // 已用分期金额
                    String AmountUsed = LargeAmountSpecial.select("tbody>tr").get(2).select("td").get(3).text();

                    largeAmountSpecial.setReportId(ReportId);
                    largeAmountSpecial.setAccountNumber(AccountNumber);
                    largeAmountSpecial.setStagingPenNumber(StagingPenNumber);
                    largeAmountSpecial.setInstallmentQuota(InstallmentQuota);
                    largeAmountSpecial.setEffectiveDate(EffectiveDate);
                    largeAmountSpecial.setExpirationDate(ExpirationDate);
                    largeAmountSpecial.setAmountUsed(AmountUsed);

                    largeAmountSpecialList.add(largeAmountSpecial);
                }
                reportMessageData.setCrt2_p_largeamountspecial(largeAmountSpecialList);
                log.debug("=============解析大额专项分期信息：[{}]",largeAmountSpecialList);
            }else{
                log.debug("=============大额专项分期信息为空：[{}]");
            }

        }

        /**
         * DeclarativeInfo 标注及声明信息
         *
         * @param document
         */
        public void getDeclarativeInfo(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 标注及声明信息
            Elements DeclarativeInfovs = document.select("div.m-repbody").select("div:contains(三 信贷交易信息明细)")
                    .select("table.g-subtab-bor").select("table:contains(标注)");
            if(DeclarativeInfovs.size()>0){
                List<DeclarativeInformation> declarativeInfoList = new ArrayList<>();
                for (int i = 0; i < DeclarativeInfovs.size(); i++) {
                    DeclarativeInformation declarativeInfo = new DeclarativeInformation();
                    Element DeclarativeInfov = DeclarativeInfovs.get(i);
                    // 账户编号
                    Element div1 = DeclarativeInfov.previousElementSiblings().select("div.tnt").first();
                    Element div2 = DeclarativeInfov.previousElementSiblings().select("div.t2").select("div.f-mgtop").first();
                    String AccountNumber = null;
                    if (div1.text().indexOf("授信协议标识") != -1) {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_"
                                + div1.text().substring(0, div1.text().indexOf("（"));
                    } else {
                        AccountNumber = div2.text().substring(3, div2.text().length()) + "_" + div1.text();
                        ;
                    }
                    // 标注及声明个数
                    String Declarations = Integer.toString(DeclarativeInfovs.size());
                    // 标注及声明类型
                    String DeclarationsTypeDesc = DeclarativeInfov.select("tbody>tr").get(0).select("th").get(0).text();
                    String DeclarationsType = mapUtil.getItemIdByItemName2("p_annotation_declaration",DeclarationsTypeDesc);
                    // 标注或声明内容
                    String StatementContent = DeclarativeInfov.select("tbody>tr").get(1).select("td").get(0).text();

                    declarativeInfo.setReportId(ReportId);
                    declarativeInfo.setAccountNumber(AccountNumber);
                    declarativeInfo.setDeclarations(Declarations);
                    declarativeInfo.setDeclarationsType(DeclarationsType);
                    declarativeInfo.setStatementContent(StatementContent);

                    declarativeInfoList.add(declarativeInfo);
                }
                reportMessageData.setCrt2_p_declarativeinfo(declarativeInfoList);
                log.debug("=============解析标注及声明信息：[{}]",declarativeInfoList);
            }else{
                log.debug("=============标注及声明信息为空：[{}]");
            }

        }

        /**
         * CreditAgreement 授信协议基本信息
         *
         * @param document
         */
        public void getCreditAgreement(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
            String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
                    .selectFirst("td").text().substring(5, 27);

            // 授信协议基本信息
            Elements CreditAgreements = document.select("div.t2").select("div.f-mgtop").select("div:contains(授信协议信息)")
                    .nextAll("table.g-tab-bor");
            Elements divs = document.select("div.t2").select("div.f-mgtop").select("div:contains(授信协议信息)")
                    .nextAll("div.tnt");
            if(CreditAgreements.size()>0){
                List<CreditAgreement> creditAgreementList = new ArrayList<>();
                for (int i = 0; i < CreditAgreements.size(); i++) {
                    CreditAgreement creditAgreement = new CreditAgreement();
                    Element CreditAgreement = CreditAgreements.get(i);
                    // 授信协议编号
                    String CreditAgreementNumber = divs.get(i).text();
                    // 业务管理机构类型
                    String str = CreditAgreement.select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessManagementDesc = str;
                    String BusinessManagementType = str.substring(0, str.lastIndexOf("\"") - 3);
                    BusinessManagementType = mapUtil.getItemIdByItemName2("p_d1_loan_state",BusinessManagementType);
                    // 业务管理机构
                    String BusinessManagement = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
                    // 授信协议标识
                    String CreditAgreementMark = CreditAgreement.select("tbody>tr").get(1).select("td").get(1).text();
                    // 授信额度用途
                    String CreditLineUsageDesc = CreditAgreement.select("tbody>tr").get(1).select("td").get(4).text();
                    String CreditLineUsage = mapUtil.getItemIdByItemName2("p_credit_line",CreditLineUsageDesc);
                    // 授信额度
                    String CreditLine = CreditAgreement.select("tbody>tr").get(3).select("td").get(0).text();
                    // 币种
                    String CurrencyDesc = CreditAgreement.select("tbody>tr").get(3).select("td").get(4).text();
                    if (CurrencyDesc.equals("人民币元")) {
                        CurrencyDesc = "人民币";
                    }
                    String Currency = mapUtil.getItemIdByItemName2("entCurrency",CurrencyDesc);
                    // 生效日期
                    String EffectiveDate = CreditAgreement.select("tbody>tr").get(1).select("td").get(2).text();
                    // 到期日期
                    String ExpirationDate = CreditAgreement.select("tbody>tr").get(1).select("td").get(3).text();
                    // 授信限额
                    String CreditLimit = CreditAgreement.select("tbody>tr").get(3).select("td").get(1).text();
                    // 授信限额编号
                    String Creditlimitnumber = CreditAgreement.select("tbody>tr").get(3).select("td").get(2).text();
                    // 已用额度
                    String AmountUsed = CreditAgreement.select("tbody>tr").get(3).select("td").get(3).text();
                    // 授信协议状态
                    String CreditAgreementStatus = "有效";
                    CreditAgreementStatus = mapUtil.getItemIdByItemName2("p_credit_status",CreditAgreementStatus);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                    try {
                        Date date = dateFormat.parse(ExpirationDate);
                        Date nowDate = new Date(System.currentTimeMillis());
                        if (!date.after(nowDate)) {
                            CreditAgreementStatus = "到期/失效";
                            CreditAgreementStatus = mapUtil.getItemIdByItemName2("p_credit_status",CreditAgreementStatus);
                        }
                    } catch (ParseException e) {
                    }

                    creditAgreement.setReportId(ReportId);
                    creditAgreement.setCreditAgreementNumber(CreditAgreementNumber);
                    creditAgreement.setBusinessManagementType(BusinessManagementType);
                    creditAgreement.setBusinessManagement(BusinessManagement);
                    creditAgreement.setCreditAgreementMark(CreditAgreementMark);
                    creditAgreement.setCreditLineUsage(CreditLineUsage);
                    creditAgreement.setCreditLine(CreditLine);
                    creditAgreement.setCurrency(Currency);
                    creditAgreement.setEffectiveDate(EffectiveDate);
                    creditAgreement.setExpirationDate(ExpirationDate);
                    creditAgreement.setCreditLimit(CreditLimit);
                    creditAgreement.setCreditLimitNumber(Creditlimitnumber);
                    creditAgreement.setAmountUsed(AmountUsed);
                    creditAgreement.setCreditAgreementStatus(CreditAgreementStatus);

                    creditAgreementList.add(creditAgreement);
                }
                reportMessageData.setCrt2_p_creditagreement(creditAgreementList);
                log.debug("=============解析授信协议基本信息：[{}]",creditAgreementList);
            }else{
                log.debug("=============授信协议基本信息为空：[{}]");
            }
        }

        /**
         * Relrepayliabilities 相关还款责任信息
         *
         * @param document
         */
        public void getRelrepayliabilities(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 相关还款责任信息
            Elements Relrepayliabilitiess = document.select("div.t2").select("div.f-mgtop").select("div:contains(相关还款责任信息)")
                    .nextAll("table.g-tab-bor").select("table.f-tab-fix");
            Elements Relrepayliabilitiess2 = document.select("div.t2").select("div.f-mgtop")
                    .select("div:contains(相关还款责任信息)").nextAll("table.g-subtab-bor").select("table.f-tab-fix");


            if(Relrepayliabilitiess.size()>0){
                List<RelatedRepaymentLiabilities> relrepayliabilitiessList = new ArrayList<>();
                for (int i = 0; i < Relrepayliabilitiess.size(); i++) {
                    RelatedRepaymentLiabilities relrepayliabilitiess = new RelatedRepaymentLiabilities();
                    Element Relrepayliabilities = Relrepayliabilitiess.get(i);
                    Element Relrepayliabilities2 = Relrepayliabilitiess2.get(i);
                    // 主借款人身份类别
                    String PrincipalBorrowerStatus = Relrepayliabilities.previousElementSiblings().select("div.t2").first()
                            .text();
                    String PrincipalBorrowerStatusDesc = "";
                    if (PrincipalBorrowerStatus.contains("个人")) {
                        PrincipalBorrowerStatus = "1";
                        PrincipalBorrowerStatusDesc = "个人";
                    }
                    if (PrincipalBorrowerStatus.contains("企业")) {
                        PrincipalBorrowerStatus = "2";
                        PrincipalBorrowerStatusDesc = "企业";
                    }
                    // 业务管理机构类型
                    String str = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(0).text();
                    String BusinessManagementType = str.substring(0, str.lastIndexOf("\"") - 3);
                    BusinessManagementType = mapUtil.getItemIdByItemName2("p_org_type",BusinessManagementType);
                    // 业务管理机构
                    String BusinessManagementDesc = str;
                    String BusinessManagement = str.substring(str.indexOf("\"") + 1, str.lastIndexOf("\""));
                    // 业务种类
                    String BusinessTypesDesc = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(1).text();
                    String BusinessTypes = mapUtil.getItemIdByItemName2("p_enterprose_lending",BusinessTypesDesc);
                    // 开立日期
                    String IssuanceDate = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(2).text();
                    // 到期日期
                    String ExpirationDate = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(3).text();
                    // 相关还款责任人类型
                    String RepaymentTypeDesc = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(4).text();
                    String RepaymentType = mapUtil.getItemIdByItemName2("p_related_payers",RepaymentTypeDesc);
                    // 相关还款责任限额
                    String RepaymentLimit = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(5).text();
                    // 币种
                    String CurrencyDesc = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(6).text();
                    if (CurrencyDesc.equals("人民币元")) {
                        CurrencyDesc = "人民币";
                    }
                    String Currency = mapUtil.getItemIdByItemName2("entCurrency",CurrencyDesc);
                    //
                    String Balance = Relrepayliabilities2.select("tbody>tr").get(2).select("td").get(0).text();
                    // 五级分类
                    String FiveLevelClassificationDesc = Relrepayliabilities2.select("tbody>tr").get(2).select("td").get(1).text();
                    String FiveLevelClassification = mapUtil.getItemIdByItemName2("账户类型p_five_level_classification",FiveLevelClassificationDesc);
                    //
                    String AccountType = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(1).text();
                    // 还款状态
                    String RepaymentStateDesc = null;
                    String RepaymentState = null;
                    if(PrincipalBorrowerStatus.equals("1")){
                        RepaymentStateDesc = Relrepayliabilities2.select("tbody>tr").get(2).select("td").get(2).text();
                        RepaymentState = mapUtil.getItemIdByItemName2("p_related_paystate",RepaymentStateDesc);
                    }else{
                        RepaymentState = "";
                    }

                    // 保证合同编号
                    String GuaranteeNumber = Relrepayliabilities.select("tbody>tr").get(1).select("td").get(7).text();
                    // 逾期月数
                    String OverdueMonths = null;
                    if(PrincipalBorrowerStatus.equals("2")){
                        OverdueMonths = Relrepayliabilities2.select("tbody>tr").get(2).select("td").get(2).text();
                    }else{
                        OverdueMonths = "";
                    }
                    // 信息报告日期
                    String DateOfReport = Relrepayliabilities2.select("tbody>tr").get(0).select("th").text();
                    DateOfReport=DateOfReport.substring(2,6)+"-"+DateOfReport.substring(7,9)+"-"+DateOfReport.substring(10,12);


                    relrepayliabilitiess.setReportId(ReportId);
                    relrepayliabilitiess.setPrincipalBorrowerStatus(PrincipalBorrowerStatus);
//				relrepayliabilitiess.setPrincipalborrowerstatusdesc(PrincipalBorrowerStatusDesc);//没有转码之前，转码后要修改
                    relrepayliabilitiess.setBusinessManagementType(BusinessManagementType);
                    relrepayliabilitiess.setBusinessManagement(BusinessManagement);
                    relrepayliabilitiess.setBusinessTypes(BusinessTypes);
                    relrepayliabilitiess.setIssuanceDate(IssuanceDate);
                    relrepayliabilitiess.setExpirationDate(ExpirationDate);
                    relrepayliabilitiess.setRepaymentType(RepaymentType);
                    relrepayliabilitiess.setRepaymentLimit(RepaymentLimit);
                    relrepayliabilitiess.setCurrency(Currency);
                    relrepayliabilitiess.setBalance(Balance);
                    relrepayliabilitiess.setFiveLevelClassification(FiveLevelClassification);
                    relrepayliabilitiess.setAccountType(AccountType);
                    relrepayliabilitiess.setRepaymentState(RepaymentState);
                    relrepayliabilitiess.setGuaranteeNumber(GuaranteeNumber);
                    relrepayliabilitiess.setOverdueMonths(OverdueMonths);
                    relrepayliabilitiess.setDateOfReport(DateOfReport);

                    relrepayliabilitiessList.add(relrepayliabilitiess);
                }
                reportMessageData.setCrt2_p_relrepayliabilities(relrepayliabilitiessList);
                log.debug("=============解析相关还款责任信息：[{}]",relrepayliabilitiessList);
            }else{
                log.debug("=============相关还款责任信息为空：[{}]");
            }


        }

        /**
         * PostPaymentBusiness 后付费业务信息
         *
         * @param document
         */
        public void getPostPaymentBusiness(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 后付费业务信息
            Elements PostPaymentBusiness = document.select("div.t2").select("div.f-mgtop").select("div:contains(后付费记录)")
                    .nextAll("table:contains(业务开通日期)");
            if(PostPaymentBusiness.size()>0){
                List<PostPaymentBusiness> postPaymentBusinessList = new ArrayList<>();
                for (int i = 0; i < PostPaymentBusiness.size(); i++) {
                    PostPaymentBusiness postPaymentBusiness = new PostPaymentBusiness();
                    Element PostPaymentBusines = PostPaymentBusiness.get(i);
                    Element PaymentRecordTable = PostPaymentBusines.nextElementSibling();
                    // 后付费账户类型
                    String PaymentAccountType = null;
                    // 机构名称
                    String OrganizationName = PostPaymentBusines.select("tbody>tr").get(1).select("td").get(0).text();
                    // 业务类型
                    String BusinessTypeDesc = PostPaymentBusines.select("tbody>tr").get(1).select("td").get(1).text();
                    String BusinessType = mapUtil.getItemIdByItemName2("p_payment_service",BusinessTypeDesc);
                    // 业务开通日期
                    String BusinessOpeningDate = PostPaymentBusines.select("tbody>tr").get(1).select("td").get(2).text();
                    // 当前缴费状态
                    String CurrentPaymentStatusDesc = PostPaymentBusines.select("tbody>tr").get(1).select("td").get(3).text();
                    String CurrentPaymentStatus = mapUtil.getItemIdByItemName2("p_payment_state",CurrentPaymentStatusDesc);
                    // 当前欠费金额
                    String CurrentArrearsAmount = PostPaymentBusines.select("tbody>tr").get(1).select("td").get(4).text();
                    // 记账年月
                    String YearOfAccount = PostPaymentBusines.select("tbody>tr").get(1).select("td").get(5).text();
                    // 最近 24 个月缴费记录
                    String PaymentRecord = null;
                    //起始、终止年月
                    String BeginYear = null;
                    String BeginMonth = null;
                    String EndYear = null;
                    String EndMonth = null;

                    if (PaymentRecordTable.select("table:contains(缴费记录)").size()>0){
					/*Elements PaymentRecordTr = PaymentRecordTable.select("tr:has(td)");
					StringBuilder stringBuilder = new StringBuilder();
					for (int x=PaymentRecordTr.size()-1;x>=0;x--){
						Elements PaymentRecordTd = PaymentRecordTr.get(x).select("td");
						for (int j=0;j<PaymentRecordTd.size();j++){
							if (PaymentRecordTd.get(j).text()==null||PaymentRecordTd.get(j).text().equals("")){
								stringBuilder.append("-");
							}
							stringBuilder.append(PaymentRecordTd.get(j).text());
						}
					}
					PaymentRecord = stringBuilder.toString();*/
                        if(PaymentRecordTable.select("tbody>tr").size()==5){
                            Element PaymentRecord1 = PaymentRecordTable.select("tbody>tr").get(4);
                            Element PaymentRecord2 = PaymentRecordTable.select("tbody>tr").get(3);
                            Element PaymentRecord3 = PaymentRecordTable.select("tbody>tr").get(2);
                            String PaymentRecords = PaymentRecord1.select("tr>td").text()+PaymentRecord2.select("tr>td").text()+PaymentRecord3.select("tr>td").text();
                            PaymentRecord = PaymentRecords.replace(" ","");

                        }
                        if(PaymentRecordTable.select("tbody>tr").size()==4){
                            Element PaymentRecord1 = PaymentRecordTable.select("tbody>tr").get(3);
                            Element PaymentRecord2 = PaymentRecordTable.select("tbody>tr").get(2);
                            String PaymentRecords = PaymentRecord1.select("tr>td").text()+PaymentRecord2.select("tr>td").text();
                            PaymentRecord = PaymentRecords.replace(" ","");
                        }
                        if(PaymentRecordTable.select("tbody>tr").size()==3){
                            Element PaymentRecord1 = PaymentRecordTable.select("tbody>tr").get(2);
                            String PaymentRecords = PaymentRecord1.select("tr>td").text();
                            PaymentRecord = PaymentRecords.replace(" ","");
                        }
                        String date = PaymentRecordTable.select("tbody>tr").get(0).text();

                        Pattern pattern = Pattern.compile("(?<!\\d)\\d{2}(?!\\d)");
                        Matcher matcher = pattern.matcher(date);
                        matcher.find();
                        BeginMonth = matcher.group();
                        matcher.find();
                        EndMonth = matcher.group();

                        Pattern pattern1 = Pattern.compile("\\d{4}");
                        Matcher matcher1 = pattern1.matcher(date);
                        matcher1.find();
                        BeginYear = matcher1.group();
                        matcher1.find();
                        EndYear = matcher1.group();

                    }else{
                        PaymentRecord = "";
                    }

                    //起始、终止年月
                    postPaymentBusiness.setReportId(ReportId);
                    postPaymentBusiness.setPaymentAccountType(PaymentAccountType);
                    postPaymentBusiness.setOrganizationName(OrganizationName);
                    postPaymentBusiness.setBusinessType(BusinessType);
                    postPaymentBusiness.setBusinessOpeningDate(BusinessOpeningDate);
                    postPaymentBusiness.setCurrentPaymentStatus(CurrentPaymentStatus);
                    postPaymentBusiness.setCurrentArrearsAmount(CurrentArrearsAmount);
                    postPaymentBusiness.setYearOfAccount(YearOfAccount);
                    postPaymentBusiness.setPaymentRecord(PaymentRecord);
                    if(null!=EndYear&&null!=EndMonth) {
                        postPaymentBusiness.setYearOfAccount(EndYear+"-"+EndMonth);
                    }

                    postPaymentBusinessList.add(postPaymentBusiness);
                }
                reportMessageData.setCrt2_p_postpaymentbusiness(postPaymentBusinessList);
                log.debug("=============解析后付费业务信息：[{}]",postPaymentBusinessList);
            }else{
                log.debug("=============后付费业务信息为空：[{}]");
            }

        }

        /**
         * TaxArrear 欠税记录
         *
         * @param document
         */
        public void getTaxArrear(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 欠税记录
            Elements TaxArrear = document.select("table:contains(主管税务机关)");
            if(TaxArrear.size()>0){
                List<TaxArrear> taxArrearList = new ArrayList<>();
                for (int i = 0; i < TaxArrear.select("tbody>tr").size() - 1; i++) {
                    TaxArrear taxArrear = new TaxArrear();
                    // 主管税务机关
                    String Organname = TaxArrear.select("tbody>tr").get(i + 1).select("td").get(1).text();
                    // 欠税总额
                    String TaxArreaAmount = TaxArrear.select("tbody>tr").get(i + 1).select("td").get(2).text();
                    // 欠税统计日期
                    String Revenuedate = TaxArrear.select("tbody>tr").get(i + 1).select("td").get(3).text();

                    taxArrear.setReportId(ReportId);
                    taxArrear.setOrganname(Organname);
                    taxArrear.setTaxArreaAmount(TaxArreaAmount);
                    taxArrear.setRevenuedate(Revenuedate);

                    taxArrearList.add(taxArrear);
                }
                reportMessageData.setCrt2_p_taxarrear(taxArrearList);
                log.debug("=============解析欠税记录：[{}]",taxArrearList);
            }else{
                log.debug("=============欠税记录为空：[{}]");
            }

        }

        /**
         * TaxArrearDeclInfo 标注及声明（欠费）
         *
         * @param document
         */
        public void getTaxArrearDeclInfo(Document document,ReportMessage reportMessageData) {

        }

        /**
         * CivilJudgement 民事判决记录
         *
         * @param document
         */
        // @Test
        public void getCivilJudgement(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 民事判决记录1
            Elements CivilJudgement1 = document.select("table:contains(立案法院)");
            // 民事判决记录2
            Elements CivilJudgement2 = document.select("table:contains(判决/调解结果)");
            if (CivilJudgement1.size() > 0) {
                List<CivilJudgement> civilJudgementList = new ArrayList<>();
                for (int i = 0; i < CivilJudgement1.select("tbody>tr").size() - 1; i++) {
                    CivilJudgement civilJudgement = new CivilJudgement();
                    // 立案法院
                    String Court = CivilJudgement1.select("tbody>tr").get(1).select("td").get(1).text();
                    // 案由
                    String CaseReason = CivilJudgement1.select("tbody>tr").get(1).select("td").get(2).text();
                    // 立案日期
                    String RegisterDate = CivilJudgement1.select("tbody>tr").get(1).select("td").get(3).text();
                    // 结案方式
                    String ClosedTypeDesc = CivilJudgement1.select("tbody>tr").get(1).select("td").get(4).text();
                    String ClosedType = mapUtil.getItemIdByItemName2("p_civil_judgment",ClosedTypeDesc);

                    // 判决/调解结果
                    String CaseResult = CivilJudgement2.select("tbody>tr").get(1).select("td").get(1).text();
                    // 判决/调解生效日期
                    String CaseValidatedate = CivilJudgement2.select("tbody>tr").get(1).select("td").get(2).text();
                    // 诉讼标的
                    String SuitObject = CivilJudgement2.select("tbody>tr").get(1).select("td").get(3).text();
                    // 诉讼标的金额
                    String SuitObjectMoney = CivilJudgement2.select("tbody>tr").get(1).select("td").get(4).text();

                    civilJudgement.setReportId(ReportId);
                    civilJudgement.setCourt(Court);
                    civilJudgement.setCaseReason(CaseReason);
                    civilJudgement.setRegisterDate(RegisterDate);
                    civilJudgement.setClosedType(ClosedType);
                    civilJudgement.setCaseResult(CaseResult);
                    civilJudgement.setCaseValidatedate(CaseValidatedate);
                    civilJudgement.setSuitObject(SuitObject);
                    civilJudgement.setSuitObjectMoney(SuitObjectMoney);

                    civilJudgementList.add(civilJudgement);
                }
                reportMessageData.setCrt2_p_civiljudgement(civilJudgementList);
                log.debug("=============解析民事判决记录：[{}]",civilJudgementList);
            }else{
                log.debug("=============民事判决记录为空：[{}]");
            }


        }

        /**
         * CivilJudgementdeclInfo 标注及声明（民事判决）
         *
         * @param document
         */
        public void getCivilJudgementdeclInfo(Document document,ReportMessage reportMessageData) {

        }

        /**
         * ForceExecution 强制执行记录
         *
         * @param document
         */
        public void getForceExecution(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 强制执行记录
            Elements ForceExecution1 = document.select("table:contains(执行法院)").select("table:contains(执行案由)");
            Elements ForceExecution2 = document.select("table:contains(案件状态)");
            if(ForceExecution1.size()>0){
                List<ForceExecution> forceExecutionList = new ArrayList<>();
                for (int i = 0; i < ForceExecution1.select("Tbody>tr").size() - 1; i++) {
                    try{
                        ForceExecution forceExecution = new ForceExecution();

                        // 执行法院
                        String Court = ForceExecution1.select("Tbody>tr").get(i + 1).select("td").get(1).text();
                        // 执行案由
                        String CaseReason = ForceExecution1.select("Tbody>tr").get(i + 1).select("td").get(2).text();
                        // 立案日期
                        String RegisterDate = ForceExecution1.select("Tbody>tr").get(i + 1).select("td").get(3).text();
                        // 结案方式
                        String ClosedTypeDesc = ForceExecution1.select("Tbody>tr").get(i + 1).select("td").get(4).text();
                        String ClosedType = mapUtil.getItemIdByItemName2("p_enforcement",ClosedTypeDesc);
                        // 案件状态
                        String CaseState = ForceExecution2.select("Tbody>tr").get(i + 1).select("td").get(1).text();
                        // 结案日期
                        String ClosedDate = ForceExecution2.select("Tbody>tr").get(i + 1).select("td").get(2).text();
                        // 申请执行标的
                        String EnforceObject = ForceExecution2.select("Tbody>tr").get(i + 1).select("td").get(3).text();
                        // 申请执行标的金额
                        String EnforceObjectMoney = ForceExecution2.select("Tbody>tr").get(i + 1).select("td").get(4).text();
                        // 已执行标的
                        String AlreadyEnforceObject = ForceExecution2.select("Tbody>tr").get(i + 1).select("td").get(5).text();
                        // 已执行标的金额
                        String AlreadyEnforceObjectMoney = ForceExecution2.select("Tbody>tr").get(i + 1).select("td").get(6).text();

                        forceExecution.setReportId(ReportId);
                        forceExecution.setCourt(Court);
                        forceExecution.setCaseReason(CaseReason);
                        forceExecution.setRegisterDate(RegisterDate);
                        forceExecution.setClosedType(ClosedType);
                        forceExecution.setCaseState(CaseState);
                        forceExecution.setClosedDate(ClosedDate);
                        forceExecution.setEnforceObject(EnforceObject);
                        forceExecution.setEnforceObjectMoney(EnforceObjectMoney);
                        forceExecution.setAlreadyEnforceObject(AlreadyEnforceObject);
                        forceExecution.setAlreadyEnforceObjectMoney(AlreadyEnforceObjectMoney);

                        forceExecutionList.add(forceExecution);
                    }catch (Exception e){
                        log.error(e.getMessage());
                    }

                }
                reportMessageData.setCrt2_p_forceexecution(forceExecutionList);
                log.debug("=============解析强制执行记录：[{}]",forceExecutionList);
            }else{
                log.debug("=============强制执行记录为空：[{}]");
            }


        }

        /**
         * ForceExecutionDeclInfo 标注及声明（强制执行）
         *
         * @param document
         */
        public void getForceExecutionDeclInfo(Document document,ReportMessage reportMessageData) {

        }

        /**
         * AdminPunishment 行政处罚记录
         *
         * @param document
         */
        public void getAdminPunishment(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 行政处罚记录
            Elements AdminPunishment = document.select("table:contains(处罚机构)");
            if(AdminPunishment.size()>0){
                List<AdminPunishment> adminPunishmentList = new ArrayList<>();
                for (int i = 0; i < AdminPunishment.select("Tbody>tr").size() - 1; i++) {
                    AdminPunishment adminPunishment = new AdminPunishment();
                    // 处罚机构
                    String Organname = AdminPunishment.select("Tbody>tr").get(i + 1).select("td").get(1).text();
                    // 处罚内容
                    String Content = AdminPunishment.select("Tbody>tr").get(i + 1).select("td").get(2).text();
                    // 处罚金额
                    String Money = AdminPunishment.select("Tbody>tr").get(i + 1).select("td").get(3).text();
                    // 处罚生效日期
                    String BeginDate = AdminPunishment.select("Tbody>tr").get(i + 1).select("td").get(4).text();
                    // 处罚截止日期
                    String EndDate = AdminPunishment.select("Tbody>tr").get(i + 1).select("td").get(5).text();
                    // 行政复议结果
                    String Result = AdminPunishment.select("Tbody>tr").get(i + 1).select("td").get(6).text();

                    adminPunishment.setReportId(ReportId);
                    adminPunishment.setOrganname(Organname);
                    adminPunishment.setContent(Content);
                    adminPunishment.setMoney(Money);
                    adminPunishment.setBeginDate(BeginDate);
                    adminPunishment.setEndDate(EndDate);
                    adminPunishment.setResult(Result);

                    adminPunishmentList.add(adminPunishment);
                }
                reportMessageData.setCrt2_p_adminpunishment(adminPunishmentList);
                log.debug("=============解析行政处罚记录：[{}]",adminPunishmentList);
            }else{
                log.debug("=============行政处罚记录为空：[{}]");
            }
        }

        /**
         * AdminPunishDeclInfor 标注及声明（行政处罚）
         *
         * @param document
         */
        public void getAdminPunishDeclInfor(Document document,ReportMessage reportMessageData) {

        }

        /**
         * AccFund 住房公积金参缴记录
         *
         * @param document
         */
        public void getAccFund(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 住房公积金参缴记录
            Elements AccFunds = document.select("table:contains(参缴地)");
            if(AccFunds.size()>0){
                List<AccFund> accFundList = new ArrayList<>();
                for (int i = 0; i < AccFunds.size(); i++) {
                    Element AccFund = AccFunds.get(i);
                    // 参缴地
                    String Area = AccFund.select("tbody>tr").get(1).select("td").get(0).text();
                    // 参缴日期
                    String RegisterDate = AccFund.select("tbody>tr").get(1).select("td").get(1).text();
                    // 初缴月份
                    String FirstMonth = AccFund.select("tbody>tr").get(1).select("td").get(2).text();
                    // 缴至月份
                    String ToMonth = AccFund.select("tbody>tr").get(1).select("td").get(3).text();
                    // 缴费状态
                    String StateDesc = AccFund.select("tbody>tr").get(1).select("td").get(4).text();
                    String State = mapUtil.getItemIdByItemName2("p_housing_payment",StateDesc);
                    // 月缴存额
                    String Pay = AccFund.select("tbody>tr").get(1).select("td").get(5).text();
                    // 个人缴存比例
                    String OwnPercent = AccFund.select("tbody>tr").get(1).select("td").get(6).text();
                    // 单位缴存比例
                    String ComPercent = AccFund.select("tbody>tr").get(1).select("td").get(7).text();
                    // 缴费单位
                    String Organname = AccFund.select("tbody>tr").get(3).select("td").get(0).text();
                    // 信息更新日期
                    String GetTime = AccFund.select("tbody>tr").get(3).select("td").get(1).text();

                    AccFund accFund = new AccFund();
                    accFund.setReportId(ReportId);
                    accFund.setArea(Area);
                    accFund.setRegisterDate(RegisterDate);
                    accFund.setFirstMonth(FirstMonth);
                    accFund.setToMonth(ToMonth);
                    accFund.setState(State);
                    accFund.setPay(Pay);
                    accFund.setOwnPercent(OwnPercent);
                    accFund.setComPercent(ComPercent);
                    accFund.setOrganname(Organname);
                    accFund.setGetTime(GetTime);

                    accFundList.add(accFund);
                }
                reportMessageData.setCrt2_p_accfund(accFundList);
                log.debug("=============解析住房公积金参缴记录：[{}]",accFundList);
            }else{
                log.debug("=============住房公积金参缴记录为空：[{}]");
            }

        }

        /**
         * AccfundDeclInfor 标注及声明（住房公积金）
         *
         * @param document
         */
        public void getAccfundDeclInfor(Document document,ReportMessage reportMessageData) {

        }

        /**
         * Salvation 低保救助记录
         *
         * @param document
         */
        public void getSalvation(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 低保救助记录
            Elements Salvation = document.select("table:contains(人员类别)");
            if(Salvation.size()>0){
                List<Salvation> salvationList = new ArrayList<>();
                for (int i = 0; i < Salvation.select("tbody>tr").size() - 1; i++) {
                    Salvation salvation = new Salvation();
                    // 人员类别
                    String PersonnelTypeDesc = Salvation.select("tbody>tr").get(i + 1).select("td").get(1).text();
                    String PersonnelType = mapUtil.getItemIdByItemName2("p_subsistence_allowances",PersonnelTypeDesc);
                    // 所在地
                    String Area = Salvation.select("tbody>tr").get(i + 1).select("td").get(2).text();
                    // 工作单位
                    String Organname = Salvation.select("tbody>tr").get(i + 1).select("td").get(3).text();
                    // 家庭月收入
                    String Money = Salvation.select("tbody>tr").get(i + 1).select("td").get(4).text();
                    // 申请日期
                    String RegisterDate = Salvation.select("tbody>tr").get(i + 1).select("td").get(5).text();
                    // 批准日期
                    String PassDate = Salvation.select("tbody>tr").get(i + 1).select("td").get(6).text();
                    // 信息更新日期
                    String GetTime = Salvation.select("tbody>tr").get(i + 1).select("td").get(7).text();

                    salvation.setReportId(ReportId);
                    salvation.setPersonnelType(PersonnelType);
                    salvation.setArea(Area);
                    salvation.setOrganname(Organname);
                    salvation.setMoney(Money);
                    salvation.setRegisterDate(RegisterDate);
                    salvation.setPassDate(PassDate);
                    salvation.setGetTime(GetTime);

                    salvationList.add(salvation);
                }
                reportMessageData.setCrt2_p_salvation(salvationList);
                log.debug("=============解析低保救助记录：[{}]",salvationList);
            }else{
                log.debug("=============低保救助记录为空：[{}]");
            }
        }

        /**
         * SalvationDeclInfo 标注及声明（低保救助）
         *
         * @param document
         */
        public void getSalvationDeclInfo(Document document,ReportMessage reportMessageData) {

        }

        /**
         * Competence 执业资格记录
         *
         * @param document
         */
        public void getCompetence(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 执业资格记录
            Elements Competence = document.select("table:contains(执业资格名称)");
            if(Competence.size()>0){
                List<Competence> competenceList = new ArrayList<>();
                for (int i = 0; i < Competence.select("tbody>tr").size() - 1; i++) {
                    Competence competence = new Competence();
                    // 执业资格名称
                    String CompetencyName = Competence.select("tbody>tr").get(i + 1).select("td").get(1).text();
                    // 等级
                    String GradeDesc = Competence.select("tbody>tr").get(i + 1).select("td").get(2).text();
                    String Grade = mapUtil.getItemIdByItemName2("p_professional_qualification",GradeDesc);
                    // 获得日期
                    String AwardDate = Competence.select("tbody>tr").get(i + 1).select("td").get(3).text();
                    // 到期日期
                    String EndDate = Competence.select("tbody>tr").get(i + 1).select("td").get(4).text();
                    // 吊销日期
                    String RevokeDate = Competence.select("tbody>tr").get(i + 1).select("td").get(5).text();
                    // 颁发机构
                    String Organname = Competence.select("tbody>tr").get(i + 1).select("td").get(6).text();
                    // 机构所在地
                    String Area = Competence.select("tbody>tr").get(i + 1).select("td").get(7).text();

                    competence.setReportId(ReportId);
                    competence.setCompetencyName(CompetencyName);
                    competence.setGrade(Grade);
                    competence.setAwardDate(AwardDate);
                    competence.setEndDate(EndDate);
                    competence.setRevokeDate(RevokeDate);
                    competence.setOrganname(Organname);
                    competence.setArea(Area);

                    competenceList.add(competence);
                }
                reportMessageData.setCrt2_p_competence(competenceList);
                log.debug("=============解析执业资格记录：[{}]",competenceList);
            }else{
                log.debug("=============执业资格记录为空：[{}]");
            }
        }

        /**
         * CompetenceDeclInfo 标注及声明（执业资格）
         *
         * @param document
         */
        public void getCompetenceDeclInfo(Document document,ReportMessage reportMessageData) {

        }

        /**
         * AdminAward 行政奖励记录
         *
         * @param document
         */
        public void getAdminAward(Document document,ReportMessage reportMessageData) {

            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);
            // 行政奖励记录
            Elements AdminAward = document.select("table:contains(奖励机构)");
            if(AdminAward.size()>0){
                List<AdminAward> adminAwardList = new ArrayList<>();
                for (int i = 0; i < AdminAward.select("tbody>tr").size() - 1; i++) {
                    AdminAward adminAward = new AdminAward();
                    // 奖励机构
                    String Organname = AdminAward.select("tbody>tr").get(i + 1).select("td").get(1).text();
                    // 奖励内容
                    String Content = AdminAward.select("tbody>tr").get(i + 1).select("td").get(2).text();
                    // 生效日期
                    String BeginDate = AdminAward.select("tbody>tr").get(i + 1).select("td").get(3).text();
                    // 截止日期
                    String EndDate = AdminAward.select("tbody>tr").get(i + 1).select("td").get(4).text();
                    adminAward.setReportId(ReportId);
                    adminAward.setOrganname(Organname);
                    adminAward.setContent(Content);
                    adminAward.setBeginDate(BeginDate);
                    adminAward.setEndDate(EndDate);

                    adminAwardList.add(adminAward);
                }
                reportMessageData.setCrt2_p_adminaward(adminAwardList);
                log.debug("=============解析行政奖励记录：[{}]",adminAwardList);
            }else{
                log.debug("=============行政奖励记录为空：[{}]");
            }

        }

        /**
         * AdminAwarddeclInfo 标注及声明（行政奖励）
         *
         * @param document
         */
        public void getAdminAwarddeclInfo(Document document,ReportMessage reportMessageData) {

        }

        /**
         * OtherAnnotations 标注及声明信息
         *
         * @param document
         */
        public void getOtherAnnotations(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
            String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
                    .selectFirst("td").text().substring(5, 27);
            // 标注及声明信息
            Elements OtherAnnotations = document.select("table:contains(标注内容)");
            // 对象类型
            // 对象标识
            // 标注及声明类型个数
            // 标注及声明类型
            // 标注或声明内容
            // 添加日期

        }

        /**
         * QueryRecord 查询记录信息
         *
         * @param document
         */
        public void getQueryRecord(Document document,ReportMessage reportMessageData) {
            // 获取报告编号
		String ReportId = document.select("table.g-tab-bor").first().selectFirst("tbody").selectFirst("tr")
				.selectFirst("td").text().substring(5, 27);

            // 查询记录信息
            Elements QueryRecord = document.select("table:contains(查询日期)");
            if(QueryRecord.size()>0){
                List<QueryRecord> queryRecordList = new ArrayList<>();
                for (int i = 0; i < QueryRecord.select("tbody>tr").size() - 1; i++) {
                    QueryRecord queryRecord = new QueryRecord();
                    // 查询日期
                    if(QueryRecord.select("tbody>tr").get(i + 1).select("td").size() <3){
                        continue;
                    }
                    String QueryDate = QueryRecord.select("tbody>tr").get(i + 1).select("td").get(1).text();
                    String aaa = QueryRecord.select("tbody>tr").get(i + 1).select("td").get(2).text();
                    String InquiringOrganizationDesc = aaa;
                    // 查询机构类型
                    String InquiringOrganizationType = null;
                    // 查询机构
                    String InquiringOrganization = null;
                    if (aaa.contains("\"")) {
                        InquiringOrganizationType = aaa.substring(0, aaa.indexOf("\""));
                        InquiringOrganization = aaa.substring(aaa.indexOf("\"") + 1, aaa.lastIndexOf("\"")).trim();
                    } else {
                        // 查询机构
                        InquiringOrganization = aaa.trim();
                    }
                    InquiringOrganizationType = mapUtil.getItemIdByItemName2("p_org_type",InquiringOrganizationType);
                    // 查询原因
                    String InquiryCauseDesc = QueryRecord.select("tbody>tr").get(i + 1).select("td").get(3).text();
                    String InquiryCause = mapUtil.getItemIdByItemName2("p_queryReason",InquiryCauseDesc);

                    queryRecord.setReportId(ReportId);
                    queryRecord.setQueryDate(QueryDate);
                    queryRecord.setInquiringOrganizationType(InquiringOrganizationType);
                    queryRecord.setInquiringOrganization(InquiringOrganization);
                    queryRecord.setInquiryCause(InquiryCause);

                    queryRecordList.add(queryRecord);
                }
                reportMessageData.setCrt2_p_queryrecord(queryRecordList);
                log.debug("=============解析查询记录信息：[{}]",queryRecordList);
            }else{
                log.debug("=============查询记录信息为空：[{}]");
            }

        }


}
