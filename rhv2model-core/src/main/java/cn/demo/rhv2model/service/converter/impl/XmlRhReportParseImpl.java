package cn.demo.rhv2model.service.converter.impl;

import cn.demo.rhv2model.domain.personal.*;
import cn.demo.rhv2model.service.converter.RhInputParseConverter;
import cn.yxzmmm.rhv2model.domain.personal.*;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class XmlRhReportParseImpl implements RhInputParseConverter<String> {


    @Override
    public ReportMessage parseRhReport(String input) {
        return xmljsonString(input);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public ReportMessage xmljsonString(String xmlReport) {
        try {
            Document doc = null;
            doc = DocumentHelper.parseText(xmlReport);
            Element rootElt = doc.getRootElement();

            //报告头
            String reportCreateTime = null;

            //姓名
            String name = null;
            //证件类型
            String certType = null;
            //证件号码
            String certNo = null;
            String queryOrg = null;
            String reasonCode = null;

            String identityIdentification = null;

            String antiFraudSign = null;
            String antiFraudTelephone = null;
            String effectiveDate = null;
            String closingDate = null;

            String dissentingAnnotation = null;

            String reportSN = null;
            String queryTime = null;


            ReportMessage reportMessageData = new ReportMessage();

            Element prh = rootElt.element("PRH");
            Element pa01 = prh.element("PA01");
            Iterator pa01a = pa01.elementIterator("PA01A");
            if (pa01a.hasNext()) {
                while (pa01a.hasNext()) {
                    Element recordElt = (Element) pa01a.next();
                    //人行报告编号
                    reportSN = recordElt.elementTextTrim("PA01AI01");
                    //报告时间
                    reportCreateTime = recordElt.elementTextTrim("PA01AR01");
                }
            }
            Iterator pa01b = pa01.elementIterator("PA01B");
            if (pa01b.hasNext()) {
                while (pa01b.hasNext()) {
                    Element recordElt = (Element) pa01b.next();
                    //被查询者姓名
                    name = recordElt.elementTextTrim("PA01BQ01");
                    //被查询者证件类型
                    certType = recordElt.elementTextTrim("PA01BD01");
                    //被查询者证件号码
                    certNo = recordElt.elementTextTrim("PA01BI01");
                    //查询机构代码
                    queryOrg = recordElt.elementTextTrim("PA01BI02");
                    //查询原因代码
                    reasonCode = recordElt.elementTextTrim("PA01BD02");

                }
            }
            Iterator pa01c = pa01.elementIterator("PA01C");
            List<OtherDocuments> otherDocumentsList = new ArrayList<OtherDocuments>();
            if (pa01c.hasNext()) {
                while (pa01c.hasNext()) {
                    Element recordElt = (Element) pa01c.next();
                    //身份标识个数
                    identityIdentification = recordElt.elementTextTrim("PA01CS01");
                    Iterator pa01ch = recordElt.elementIterator("PA01CH");
                    if (pa01ch.hasNext()) {
                        while (pa01ch.hasNext()) {
                            Element recordEltxx = (Element) pa01ch.next();
                            //证件类型
                            String documentType = recordEltxx.elementTextTrim("PA01CD01");
                            //证件号码
                            String identificationNumber = recordEltxx.elementTextTrim("PA01CI01");

                            OtherDocuments otherDocuments = new OtherDocuments();
                            otherDocuments.setIdentityIdentification(identityIdentification);
                            otherDocuments.setDocumentType(documentType);
                            otherDocuments.setIdentificationNumber(identificationNumber);

                            otherDocumentsList.add(otherDocuments);
                        }
                    }

                }
            }

            reportMessageData.setCrt2_p_otherdocuments(otherDocumentsList);

            Iterator pa01d = pa01.elementIterator("PA01D");
            if (pa01d.hasNext()) {
                while (pa01d.hasNext()) {
                    Element recordElt = (Element) pa01d.next();
                    //防欺诈警示标志
                    antiFraudSign = recordElt.elementTextTrim("PA01DQ01");
                    //防欺诈警示联系电话
                    antiFraudTelephone = recordElt.elementTextTrim("PA01DQ02");
                    //防欺诈警示生效日期
                    effectiveDate = recordElt.elementTextTrim("PA01DR01");
                    //防欺诈警示截止日期
                    closingDate = recordElt.elementTextTrim("PA01DR02");
                }
            }
            Iterator pa01e = pa01.elementIterator("PA01E");
            if (pa01e.hasNext()) {
                while (pa01e.hasNext()) {
                    Element recordElt = (Element) pa01e.next();
                    //异议标注数目
                    dissentingAnnotation = recordElt.elementTextTrim("PA01ES01");
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (queryTime == null || queryTime.equals("")) {
                queryTime = sdf.format(new Date());
            }
            Identification identification = new Identification();
            identification.setReportSN(reportSN);
            identification.setQueryTime(queryTime);
            identification.setReportCreateTime(reportCreateTime);
            identification.setName(name);
            identification.setCerttype(certType);
            identification.setCertno(certNo);
            identification.setQueryOrg(queryOrg);
            identification.setReasonCode(reasonCode);
            identification.setOtherDocumentsList(otherDocumentsList);
            identification.setAntiFraudSign(antiFraudSign);
            identification.setAntiFraudTelephone(antiFraudTelephone);
            identification.setEffectiveDate(effectiveDate);
            identification.setClosingDate(closingDate);
            identification.setDissentingAnnotation(dissentingAnnotation);

            reportMessageData.setCrt2_p_identification(identification);

            //身份信息
            Element pim = rootElt.element("PIM");
            Element pb01 = pim.element("PB01");
            List<Identity> identityList = new ArrayList<Identity>();
            Iterator pbo1a = pb01.elementIterator("PB01A");
            if (pbo1a.hasNext()) {
                while (pbo1a.hasNext()) {
                    Element recordElt = (Element) pbo1a.next();
                    //性别
                    String gender = recordElt.elementTextTrim("PB01AD01");
                    //出生日期
                    String birthday = recordElt.elementTextTrim("PB01AR01");
                    //学历
                    String eduLevel = recordElt.elementTextTrim("PB01AD02");
                    //学位
                    String eduDegree = recordElt.elementTextTrim("PB01AD03");
                    //就业状况
                    String employmentStatus = recordElt.elementTextTrim("PB01AD04");
                    //邮箱
                    String email = recordElt.elementTextTrim("PB01AQ01");
                    //通讯地址
                    String postAddress = recordElt.elementTextTrim("PB01AQ02");
                    //国籍
                    String nationality = recordElt.elementTextTrim("PB01AD05");
                    //户籍地址
                    String householdAddress = recordElt.elementTextTrim("PB01AQ03");

                    Identity identity = new Identity();
                    identity.setGender(gender);
                    identity.setBirthday(birthday);
                    identity.setEduLevel(eduLevel);
                    identity.setEduDegree(eduDegree);
                    identity.setEmploymentStatus(employmentStatus);
                    identity.setEmail(email);
                    identity.setPostAddress(postAddress);
                    identity.setNationality(nationality);
                    identity.setHouseholdAddress(householdAddress);
                    identityList.add(identity);

                    reportMessageData.setCrt2_p_identity(identityList);
                }
            }

            Iterator pbo1b = pb01.elementIterator("PB01B");
            List<PhoneInfo> phoneInfoList = new ArrayList<PhoneInfo>();
            if (pbo1b.hasNext()) {
                while (pbo1b.hasNext()) {
                    Element recordElt = (Element) pbo1b.next();
                    //手机号码个数
                    String NumberMobile = recordElt.elementTextTrim("PB01BS01");
                    Iterator pb01bh = recordElt.elementIterator("PB01BH");
                    if (pb01bh.hasNext()) {
                        while (pb01bh.hasNext()) {
                            Element recordmoblie = (Element) pb01bh.next();
                            //手机号码
                            String Mobile = recordmoblie.elementTextTrim("PB01BQ01");
                            //信息更新日期
                            String GetTime = recordmoblie.elementTextTrim("PB01BR01");

                            PhoneInfo phoneInfo = new PhoneInfo();
                            phoneInfo.setNumberMobile(NumberMobile);
                            phoneInfo.setGetTime(GetTime);
                            phoneInfo.setMobile(Mobile);
                            phoneInfoList.add(phoneInfo);
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_phone(phoneInfoList);

            //婚姻信息
            Element pmm = rootElt.element("PMM");
            Iterator pb02 = pmm.elementIterator("PB02");
            List<Spouse> spouseList = new ArrayList<Spouse>();
            if (pb02.hasNext()) {
                while (pb02.hasNext()) {
                    Element recordElt = (Element) pb02.next();
                    //婚姻状况
                    String MaritalStatus = recordElt.elementTextTrim("PB020D01");
                    //配偶姓名
                    String Name = recordElt.elementTextTrim("PB020Q01");
                    //配偶证件类型
                    String Certtype = recordElt.elementTextTrim("PB020D02");
                    //配偶证件号码
                    String Certno = recordElt.elementTextTrim("PB020I01");
                    //配偶工作单位
                    String Employer = recordElt.elementTextTrim("PB020Q02");
                    //配偶联系电话
                    String TelephoneNo = recordElt.elementTextTrim("PB020Q03");

                    Spouse spouse = new Spouse();
                    spouse.setMaritalStatus(MaritalStatus);
                    spouse.setName(Name);
                    spouse.setCerttype(Certtype);
                    spouse.setCertno(Certno);
                    spouse.setEmployer(Employer);
                    spouse.setTelephoneNo(TelephoneNo);

                    spouseList.add(spouse);
                }
            }
            reportMessageData.setCrt2_p_spouse(spouseList);

            //居住信息
            Element prm = rootElt.element("PRM");
            Iterator pb03 = prm.elementIterator("PB03");
            //居住编号（暂时没有相关数据）
            List<Residence> residenceList = new ArrayList<>();
            if (pb03.hasNext()) {
                while (pb03.hasNext()) {
                    Element recordElt = (Element) pb03.next();
                    //居住状况
                    String ResidenceType = recordElt.elementTextTrim("PB030D01");
                    //居住地址
                    String Address = recordElt.elementTextTrim("PB030Q01");
                    //住宅电话
                    String HomePhone = recordElt.elementTextTrim("PB030Q02");
                    //信息更新日期
                    String GetTime = recordElt.elementTextTrim("PB030R01");

                    Residence residence = new Residence();
                    residence.setResidenceType(ResidenceType);
                    residence.setAddress(Address);
                    residence.setHomePhone(HomePhone);
                    residence.setGetTime(GetTime);

                    residenceList.add(residence);
                }
            }
            reportMessageData.setCrt2_p_residence(residenceList);

            //职业信息
            Element pom = rootElt.element("POM");
            Iterator pb04 = pom.elementIterator("PB04");
            List<Professional> professionalList = new ArrayList<Professional>();
            //居住编号（暂时没有相关数据）
            if (pb04.hasNext()) {
                while (pb04.hasNext()) {
                    Element recordElt = (Element) pb04.next();
                    //就业状况
                    String WorkStatus = recordElt.elementTextTrim("PB040D01");
                    //工作单位
                    String Employer = recordElt.elementTextTrim("PB040Q01");
                    //单位性质
                    String UnitProperties = recordElt.elementTextTrim("PB040D02");
                    //行业
                    String Industry = recordElt.elementTextTrim("PB040D03");
                    //单位地址
                    String EmployerAddress = recordElt.elementTextTrim("PB040Q02");
                    //单位电话
                    String WorkTelephone = recordElt.elementTextTrim("PB040Q03");
                    //职业
                    String Occupation = recordElt.elementTextTrim("PB040D04");
                    //职务
                    String Duty = recordElt.elementTextTrim("PB040D05");
                    //职称
                    String Title = recordElt.elementTextTrim("PB040D06");
                    //进入本单位年份
                    String StartYear = recordElt.elementTextTrim("PB040R01");
                    //信息更新日期
                    String GetTime = recordElt.elementTextTrim("PB040R02");

                    Professional professional = new Professional();
                    professional.setWorkStatus(WorkStatus);
                    professional.setEmployer(Employer);
                    professional.setUnitProperties(UnitProperties);
                    professional.setIndustry(Industry);
                    professional.setEmployerAddress(EmployerAddress);
                    professional.setWorkTelephone(WorkTelephone);
                    professional.setOccupation(Occupation);
                    professional.setDuty(Duty);
                    professional.setTitle(Title);
                    professional.setStartYear(StartYear);
                    professional.setGetTime(GetTime);

                    professionalList.add(professional);
                }
            }
            reportMessageData.setCrt2_p_professional(professionalList);

            //评分信息
            Element psm = rootElt.element("PSM");
            if(null!=psm){
                Iterator pc01 = psm.elementIterator("PC01");
                List<ScoreElements> scoreElementsList = new ArrayList<ScoreElements>();
                if (pc01.hasNext()) {
                    while (pc01.hasNext()) {
                        Element recordElt = (Element) pc01.next();
                        //数字解读
                        String DigitalInterpretation = recordElt.elementTextTrim("PC010Q01");
                        //相对位置
                        String RelativePosition = recordElt.elementTextTrim("PC010Q02");
                        //分数说明条数
                        String FractionalExplanationNumber = recordElt.elementTextTrim("PC010S01");
                        //分数说明
                        List projects = doc.selectNodes("Document/PSM/PC01/PC010D01");
                        Iterator it = projects.iterator();
                        while (it.hasNext()) {
                            Element elm = (Element) it.next();

                            ScoreElements scoreElements = new ScoreElements();
                            scoreElements.setDigitalInterpretation(DigitalInterpretation);
                            scoreElements.setRelativePosition(RelativePosition);
                            scoreElements.setFractionalExplanationNumber(FractionalExplanationNumber);

                            scoreElements.setFractionalExplanation(elm.getText());

                            if (!(scoreElements.getDigitalInterpretation() == null) || !(scoreElements.getRelativePosition() == null)
                                    || !(scoreElements.getFractionalExplanationNumber() == null) || !(scoreElements.getFractionalExplanation() == null)) {
                                scoreElementsList.add(scoreElements);
                            }
                        }
                    }
                }
                reportMessageData.setCrt2_p_score(scoreElementsList);
            }


            //信贷交易信息概要
            Element pco = rootElt.element("PCO");
            Element pc02 = pco.element("PC02");
            if (null != pc02) {
                Iterator pc02a = pc02.elementIterator("PC02A");
                List<CreditTransaction> creditTransactionList = new ArrayList<CreditTransaction>();
                if (pc02a.hasNext()) {
                    while (pc02a.hasNext()) {
                        Element recordElt = (Element) pc02a.next();
                        //账户数合计
                        String TotalAccountNumber = recordElt.elementTextTrim("PC02AS01");
                        //业务类型数量
                        String BusinessTypeNumber = recordElt.elementTextTrim("PC02AS02");

                        Iterator pc02ah = recordElt.elementIterator("PC02AH");
                        if (pc02ah.hasNext()) {
                            while (pc02ah.hasNext()) {
                                Element recordmoblie = (Element) pc02ah.next();
                                //业务类型
                                String BusinessType = recordmoblie.elementTextTrim("PC02AD01");
                                //业务大类
                                String BusinessClass = recordmoblie.elementTextTrim("PC02AD02");
                                //账户数
                                String AccountNumber = recordmoblie.elementTextTrim("PC02AS03");
                                //首笔业务发放月份
                                String FirstBusiness = recordmoblie.elementTextTrim("PC02AR01");

                                CreditTransaction creditTransaction = new CreditTransaction();
                                creditTransaction.setTotalAccountNumber(TotalAccountNumber);
                                creditTransaction.setBusinessTypeNumber(BusinessTypeNumber);
                                creditTransaction.setBusinessType(BusinessType);
                                creditTransaction.setBusinessClass(BusinessClass);
                                creditTransaction.setAccountNumber(AccountNumber);
                                creditTransaction.setFirstBusiness(FirstBusiness);

                                creditTransactionList.add(creditTransaction);
                            }
                        }
                    }
                }
                reportMessageData.setCrt2_p_credittransaction(creditTransactionList);

                //被追偿汇总信息
                List<Recourse> recourseList = new ArrayList<Recourse>();
                Iterator pc02b = pc02.elementIterator("PC02B");
                if (pc02b.hasNext()) {
                    while (pc02b.hasNext()) {
                        Element recordElt = (Element) pc02b.next();
                        //账户数合计
                        String TotalAccountNumber1 = recordElt.elementTextTrim("PC02BS01");
                        //余额合计
                        String BalanceOfBalance = recordElt.elementTextTrim("PC02BJ01");
                        //业务类型数量
                        String BusinessTypeNumber1 = recordElt.elementTextTrim("PC02BS02");

                        Iterator pc02bh = recordElt.elementIterator("PC02BH");
                        if (pc02bh.hasNext()) {
                            while (pc02bh.hasNext()) {
                                Element recordmoblie = (Element) pc02bh.next();
                                //业务类型
                                String BusinessType = recordmoblie.elementTextTrim("PC02BD01");
                                //账户数
                                String AccountNumber = recordmoblie.elementTextTrim("PC02BS03");
                                //余额
                                String Balance = recordmoblie.elementTextTrim("PC02BJ02");

                                Recourse recourse = new Recourse();
                                recourse.setTotalAccountNumber(TotalAccountNumber1);
                                recourse.setBalanceOfBalance(BalanceOfBalance);
                                recourse.setBusinessTypeNumber(BusinessTypeNumber1);
                                recourse.setBusinessType(BusinessType);
                                recourse.setAccountNumber(AccountNumber);
                                recourse.setBalance(Balance);
                                recourseList.add(recourse);
                            }
                        }
                    }
                }
                reportMessageData.setCrt2_p_recourse(recourseList);

                //呆账汇总信息
                Iterator pc02c = pc02.elementIterator("PC02C");
                List<BadDebt> badDebtList = new ArrayList<BadDebt>();
                if (pc02c.hasNext()) {
                    while (pc02c.hasNext()) {
                        Element recordElt = (Element) pc02c.next();
                        //账户数
                        String AccountNumber = recordElt.elementTextTrim("PC02CS01");
                        //余额
                        String Balance = recordElt.elementTextTrim("PC02CJ01");

                        BadDebt badDebt = new BadDebt();
                        badDebt.setAccountNumber(AccountNumber);
                        badDebt.setBalance(Balance);

                        badDebtList.add(badDebt);
                    }
                }
                reportMessageData.setCrt2_p_baddebt(badDebtList);

                //逾期（透支）汇总信息
                Iterator pc02d = pc02.elementIterator("PC02D");
                List<BeOverDue> beoverDueList = new ArrayList<BeOverDue>();
                if (pc02d.hasNext()) {
                    while (pc02d.hasNext()) {
                        Element recordElt = (Element) pc02d.next();
                        //业务类型数量
                        String BusinessTypeNumber3 = recordElt.elementTextTrim("PC02DS01");

                        Iterator pc02dh = recordElt.elementIterator("PC02DH");
                        if (pc02dh.hasNext()) {
                            while (pc02dh.hasNext()) {
                                Element recordNext = (Element) pc02dh.next();
                                //业务类型
                                String BusinessType3 = recordNext.elementTextTrim("PC02DD01");
                                //账户数
                                String AccountNumber3 = recordNext.elementTextTrim("PC02DS02");
                                //月份数
                                String MonthNumber = recordNext.elementTextTrim("PC02DS03");
                                //单月最高逾期（透支）总额
                                String MaximumOverdue = recordNext.elementTextTrim("PC02DJ01");
                                //最长逾期（透支）月数
                                String TheLongestOverdue = recordNext.elementTextTrim("PC02DS04");

                                BeOverDue beoverDue = new BeOverDue();
                                beoverDue.setBusinessTypeNumber(BusinessTypeNumber3);
                                beoverDue.setBusinessType(BusinessType3);
                                beoverDue.setAccountNumber(AccountNumber3);
                                beoverDue.setMonthNumber(MonthNumber);
                                beoverDue.setMaximumOverdue(MaximumOverdue);
                                beoverDue.setTheLongestOverdue(TheLongestOverdue);

                                beoverDueList.add(beoverDue);
                            }
                        }
                    }
                }
                reportMessageData.setCrt2_p_beoverdue(beoverDueList);

                //非循环贷款汇总信息
                Iterator pc02e = pc02.elementIterator("PC02E");
                List<AcyclicLoan> acyclicLoanList = new ArrayList<AcyclicLoan>();
                if (pc02e.hasNext()) {
                    while (pc02e.hasNext()) {
                        Element recordElt = (Element) pc02e.next();
                        //管理机构（法人）数
                        String ManagementAgency = recordElt.elementTextTrim("PC02ES01");
                        //账户数
                        String AccountNumber = recordElt.elementTextTrim("PC02ES02");
                        //授信总额
                        String TotalLoan = recordElt.elementTextTrim("PC02EJ01");
                        //余额
                        String Balance = recordElt.elementTextTrim("PC02EJ02");
                        //最近6个月平均应还款
                        String AverageRepayment = recordElt.elementTextTrim("PC02EJ03");

                        AcyclicLoan acyclicLoan = new AcyclicLoan();
                        acyclicLoan.setManagementAgency(ManagementAgency);
                        acyclicLoan.setAccountNumber(AccountNumber);
                        acyclicLoan.setTotalLoan(TotalLoan);
                        acyclicLoan.setBalance(Balance);
                        acyclicLoan.setAverageRepayment(AverageRepayment);
                        acyclicLoanList.add(acyclicLoan);
                    }
                }
                reportMessageData.setCrt2_p_acyclicloan(acyclicLoanList);

                //循环额度下贷款汇总信息
                Iterator pc02f = pc02.elementIterator("PC02F");
                List<RevolvingLoan> revolvingLoanList = new ArrayList<RevolvingLoan>();
                if (pc02f.hasNext()) {
                    while (pc02f.hasNext()) {
                        Element recordElt = (Element) pc02f.next();
                        //管理机构（法人）数
                        String ManagementAgency = recordElt.elementTextTrim("PC02FS01");
                        //账户数
                        String AccountNumber = recordElt.elementTextTrim("PC02FS02");
                        //授信总额
                        String TotalCredit = recordElt.elementTextTrim("PC02FJ01");
                        //余额
                        String Balance = recordElt.elementTextTrim("PC02FJ02");
                        //最近6个月平均应还款
                        String AverageRepayment = recordElt.elementTextTrim("PC02FJ03");

                        RevolvingLoan revolvingLoan = new RevolvingLoan();
                        revolvingLoan.setManagementAgency(ManagementAgency);
                        revolvingLoan.setAccountNumber(AccountNumber);
                        revolvingLoan.setTotalCredit(TotalCredit);
                        revolvingLoan.setBalance(Balance);
                        revolvingLoan.setAverageRepayment(AverageRepayment);
                        revolvingLoanList.add(revolvingLoan);
                    }
                }
                reportMessageData.setCrt2_p_revolvingloan(revolvingLoanList);

                //循环贷账户汇总信息
                Iterator pc02g = pc02.elementIterator("PC02G");
                List<RevolvingLoanAccount> revolvingLoanAccountList = new ArrayList<RevolvingLoanAccount>();
                if (pc02g.hasNext()) {
                    while (pc02g.hasNext()) {
                        Element recordElt = (Element) pc02g.next();
                        //管理机构（法人）数
                        String ManagementAgency = recordElt.elementTextTrim("PC02GS01");
                        //账户数
                        String AccountNumber = recordElt.elementTextTrim("PC02GS02");
                        //授信总额
                        String TotalCredit = recordElt.elementTextTrim("PC02GJ01");
                        //余额
                        String Balance = recordElt.elementTextTrim("PC02GJ02");
                        //最近6个月平均应还款
                        String AverageRepayment = recordElt.elementTextTrim("PC02GJ03");

                        RevolvingLoanAccount revolvingLoanAccount = new RevolvingLoanAccount();
                        revolvingLoanAccount.setManagementAgency(ManagementAgency);
                        revolvingLoanAccount.setAccountNumber(AccountNumber);
                        revolvingLoanAccount.setTotalCredit(TotalCredit);
                        revolvingLoanAccount.setBalance(Balance);
                        revolvingLoanAccount.setAverageRepayment(AverageRepayment);
                        revolvingLoanAccountList.add(revolvingLoanAccount);
                    }
                }
                reportMessageData.setCrt2_p_revolvingloanaccount(revolvingLoanAccountList);

                //贷记卡账户汇总信息
                Iterator pc02h = pc02.elementIterator("PC02H");
                List<DebitCard> debitCardList = new ArrayList<DebitCard>();
                if (pc02h.hasNext()) {
                    while (pc02h.hasNext()) {
                        Element recordElt = (Element) pc02h.next();
                        //管理机构（法人）数
                        String ManagementAgency = recordElt.elementTextTrim("PC02HS01");
                        //账户数
                        String AccountNumber = recordElt.elementTextTrim("PC02HS02");
                        //授信总额
                        String TotalCredit = recordElt.elementTextTrim("PC02HJ01");
                        //单家行最高授信额
                        String HighestCredit = recordElt.elementTextTrim("PC02HJ02");
                        //单家行最低授信额
                        String MinimumCredit = recordElt.elementTextTrim("PC02HJ03");
                        //已用额度
                        String AmountUsed = recordElt.elementTextTrim("PC02HJ04");
                        //最近6个月平均使用额度
                        String AverageRepayment = recordElt.elementTextTrim("PC02HJ05");

                        DebitCard debitCard = new DebitCard();
                        debitCard.setManagementAgency(ManagementAgency);
                        debitCard.setAccountNumber(AccountNumber);
                        debitCard.setTotalCredit(TotalCredit);
                        debitCard.setHighestCredit(HighestCredit);
                        debitCard.setMinimumCredit(MinimumCredit);
                        debitCard.setAmountUsed(AmountUsed);
                        debitCard.setAverageRepayment(AverageRepayment);
                        debitCardList.add(debitCard);
                    }
                }
                reportMessageData.setCrt2_p_debitcard(debitCardList);

                //准贷记卡账户汇总信息
                Iterator pc02i = pc02.elementIterator("PC02I");
                List<SemiCreditCard> semiCreditCardList = new ArrayList<SemiCreditCard>();
                if (pc02i.hasNext()) {
                    while (pc02i.hasNext()) {
                        Element recordElt = (Element) pc02i.next();
                        //发卡机构（法人）数
                        String CardIssuer = recordElt.elementTextTrim("PC02IS01");
                        //账户数
                        String AccountNumber = recordElt.elementTextTrim("PC02IS02");
                        //授信总额
                        String TotalCredit = recordElt.elementTextTrim("PC02IJ01");
                        //单家行最高授信额
                        String HighestCredit = recordElt.elementTextTrim("PC02IJ02");
                        //单家行最低授信额
                        String MinimumCredit = recordElt.elementTextTrim("PC02IJ03");
                        //透支余额
                        String AmountUsed = recordElt.elementTextTrim("PC02IJ04");
                        //最近6个月平均透支余额
                        String AverageRepayment = recordElt.elementTextTrim("PC02IJ05");

                        SemiCreditCard semiCreditCard = new SemiCreditCard();
                        semiCreditCard.setCardIssuer(CardIssuer);
                        semiCreditCard.setAccountNumber(AccountNumber);
                        semiCreditCard.setTotalCredit(TotalCredit);
                        semiCreditCard.setHighestCredit(HighestCredit);
                        semiCreditCard.setMinimumCredit(MinimumCredit);
                        semiCreditCard.setAmountUsed(AmountUsed);
                        semiCreditCard.setAverageRepayment(AverageRepayment);
                        semiCreditCardList.add(semiCreditCard);
                    }
                }
                reportMessageData.setCrt2_p_semicreditcard(semiCreditCardList);

                //相关还款责任汇总信息
                Iterator pc02k = pc02.elementIterator("PC02K");
                RelateAllList relateAllList = new RelateAllList();
                List<PerGuarantee> perGuaranteeList = new ArrayList<PerGuarantee>();
                List<EntGuarantee> entGuaranteeList = new ArrayList<EntGuarantee>();
                List<PerOtherRepay> perOtherRepayList = new ArrayList<PerOtherRepay>();
                List<EntOtherRepay> entOtherRepayList = new ArrayList<EntOtherRepay>();

                List<TotalRelatedRepayment> totalRelatedRepaymentList = new ArrayList<TotalRelatedRepayment>();

                if (pc02k.hasNext()) {
                    while (pc02k.hasNext()) {
                        Element recordElt = (Element) pc02k.next();
                        //相关还款责任个数
                        String RelatedRepayment = recordElt.elementTextTrim("PC02KS01");

                        Iterator pc02dh = recordElt.elementIterator("PC02KH");
                        if (pc02dh.hasNext()) {
                            while (pc02dh.hasNext()) {
                                Element recordNext = (Element) pc02dh.next();
                                //借款人身份类别
                                String BorrowerIdentityCategory = recordNext.elementTextTrim("PC02KD01");
                                //相关还款责任类型
                                String RepaymentResponsibility = recordNext.elementTextTrim("PC02KD02");
                                //账户数
                                String AccountNumber = recordNext.elementTextTrim("PC02KS02");
                                //还款责任金额
                                String RepaymentLiabilityLimit = recordNext.elementTextTrim("PC02KJ01");
                                //余额
                                String Balance = recordNext.elementTextTrim("PC02KJ02");

                                TotalRelatedRepayment totalRelatedRepayment = new TotalRelatedRepayment();
                                totalRelatedRepayment.setRelatedRepayment(RelatedRepayment);
                                totalRelatedRepayment.setBorrowerIdentityCategory(BorrowerIdentityCategory);
                                totalRelatedRepayment.setRepaymentResponsibility(RepaymentResponsibility);
                                totalRelatedRepayment.setAccountNumber(AccountNumber);
                                totalRelatedRepayment.setRepaymentLiabilityLimit(RepaymentLiabilityLimit);
                                totalRelatedRepayment.setBalance(Balance);

                                if ("1".equals(BorrowerIdentityCategory) && "1".equals(RepaymentResponsibility)) {
                                    PerGuarantee perGuarantee = new PerGuarantee();
                                    perGuarantee.setAccountNumer(AccountNumber);
                                    perGuarantee.setGuaranteeAmount(RepaymentLiabilityLimit);
                                    perGuarantee.setBalance(Balance);
                                    perGuaranteeList.add(perGuarantee);
                                } else if ("1".equals(BorrowerIdentityCategory) && "9".equals(RepaymentResponsibility)) {
                                    PerOtherRepay perOtherRepay = new PerOtherRepay();
                                    perOtherRepay.setAccountNumer(AccountNumber);
                                    perOtherRepay.setLiabilityLimit(RepaymentLiabilityLimit);
                                    perOtherRepay.setBalance(Balance);
                                    perOtherRepayList.add(perOtherRepay);
                                } else if ("2".equals(BorrowerIdentityCategory) && "1".equals(RepaymentResponsibility)) {
                                    EntGuarantee entGuarantee = new EntGuarantee();
                                    entGuarantee.setAccountNumer(AccountNumber);
                                    entGuarantee.setGuaranteeAmount(RepaymentLiabilityLimit);
                                    entGuarantee.setBalance(Balance);
                                    entGuaranteeList.add(entGuarantee);
                                } else if ("2".equals(BorrowerIdentityCategory) && "9".equals(RepaymentResponsibility)) {
                                    EntOtherRepay entOtherRepay = new EntOtherRepay();
                                    entOtherRepay.setAccountNumer(AccountNumber);
                                    entOtherRepay.setLiabilityLimit(RepaymentLiabilityLimit);
                                    entOtherRepay.setBalance(Balance);
                                    entOtherRepayList.add(entOtherRepay);
                                }
                                totalRelatedRepaymentList.add(totalRelatedRepayment);
                            }
                        }
                    }
                }
                relateAllList.setPerGuaranteeList(perGuaranteeList);
                relateAllList.setPerOtherRepayList(perOtherRepayList);
                relateAllList.setEntGuaranteeList(entGuaranteeList);
                relateAllList.setEntOtherRepayList(entOtherRepayList);
                reportMessageData.setCrt2_p_totalrelatedrepayment(totalRelatedRepaymentList);
            }


            //后付费业务欠费信息汇总
            Element pno = rootElt.element("PNO");
            Iterator pc03 = pno.elementIterator("PC03");
            List<Arrears> arrearsList = new ArrayList<Arrears>();
            if (pc03.hasNext()) {
                while (pc03.hasNext()) {
                    Element recordElt = (Element) pc03.next();
                    //后付费业务类型数量
                    String TypeOfPaymentBusiness = recordElt.elementTextTrim("PC030S01");

                    Iterator pc02dh = recordElt.elementIterator("PC030H");
                    if (pc02dh.hasNext()) {
                        while (pc02dh.hasNext()) {
                            Element recordNext = (Element) pc02dh.next();
                            //后付费业务类型
                            String PostPaymentBusiness = recordNext.elementTextTrim("PC030D01");
                            //欠费账户数
                            String ArrearsAccount = recordNext.elementTextTrim("PC030S02");
                            //欠费金额
                            String AmountOfArrears = recordNext.elementTextTrim("PC030J01");

                            Arrears arrears = new Arrears();
                            arrears.setTypeOfPaymentBusiness(TypeOfPaymentBusiness);
                            arrears.setPostPaymentBusiness(PostPaymentBusiness);
                            arrears.setArrearsAccount(ArrearsAccount);
                            arrears.setAmountOfArrears(AmountOfArrears);
                            arrearsList.add(arrears);
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_arrears(arrearsList);

            //公共信息概要信息
            Element ppo = rootElt.element("PPO");
            Iterator pc04 = ppo.elementIterator("PC04");
            List<PublicInformation> publicInformationList = new ArrayList<PublicInformation>();
            if (pc04.hasNext()) {
                while (pc04.hasNext()) {
                    Element recordElt = (Element) pc04.next();
                    //公共信息类型数量
                    String TypeOfPublicInformation = recordElt.elementTextTrim("PC040S01");

                    Iterator pc02dh = recordElt.elementIterator("PC040H");
                    if (pc02dh.hasNext()) {
                        while (pc02dh.hasNext()) {
                            Element recordNext = (Element) pc02dh.next();
                            //公共信息类型
                            String PublicInformation = recordNext.elementTextTrim("PC040D01");
                            //记录数
                            String RecordNumber = recordNext.elementTextTrim("PC040S02");
                            //涉及金额
                            String AmountInvolved = recordNext.elementTextTrim("PC040J01");

                            PublicInformation publicInformation = new PublicInformation();
                            publicInformation.setTypeOfPublicInformation(TypeOfPublicInformation);
                            publicInformation.setPublicInformation(PublicInformation);
                            publicInformation.setRecordNumber(RecordNumber);
                            publicInformation.setAmountInvolved(AmountInvolved);

                            publicInformationList.add(publicInformation);
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_publicinformation(publicInformationList);

            //查询记录概要信息
            Element pqo = rootElt.element("PQO");
            Element pc05 = pqo.element("PC05");
            Iterator pc05a = pc05.elementIterator("PC05A");
            List<LastQuery> lastQueryList = new ArrayList<LastQuery>();
            if (pc05a.hasNext()) {
                while (pc05a.hasNext()) {
                    Element recordElt = (Element) pc05a.next();
                    //上一次查询日期
                    String LastQueryDate = recordElt.elementTextTrim("PC05AR01");
                    //上一次查询机构机构类型
                    String LastQueryOrganizationType = recordElt.elementTextTrim("PC05AD01");
                    //上一次查询机构代码
                    String LastQueryCode = recordElt.elementTextTrim("PC05AI01");
                    //上一次查询原因
                    String LastQueryReason = recordElt.elementTextTrim("PC05AQ01");

                    LastQuery lastQuery = new LastQuery();
                    lastQuery.setLastQueryDate(LastQueryDate);
                    lastQuery.setLastQueryOrganizationType(LastQueryOrganizationType);
                    lastQuery.setLastQueryCode(LastQueryCode);
                    lastQuery.setLastQueryReason(LastQueryReason);

                    lastQueryList.add(lastQuery);
                }
            }
            reportMessageData.setCrt2_p_lastquery(lastQueryList);

            //查询记录汇总信息
            Iterator pc05b = pc05.elementIterator("PC05B");
            List<QueryRecordSummary> queryRecordSummaryList = new ArrayList<QueryRecordSummary>();
            if (pc05b.hasNext()) {
                while (pc05b.hasNext()) {
                    Element recordElt = (Element) pc05b.next();
                    //最近1个月内的查询机构数（贷款审批）
                    String LoanApproval = recordElt.elementTextTrim("PC05BS01");
                    //最近1个月内的查询机构数（信用卡审批）
                    String CreditCardApproval = recordElt.elementTextTrim("PC05BS02");
                    //最近1个月内的查询次数（贷款审批）
                    String LoanApprovalNumber = recordElt.elementTextTrim("PC05BS03");
                    //最近1个月内的查询次数（信用卡审批）
                    String CreditCardApprovalNumber = recordElt.elementTextTrim("PC05BS04");
                    //最近1个月内的查询次数（本人查询）
                    String IqueryTimes = recordElt.elementTextTrim("PC05BS05");
                    //最近2年内的查询次数（贷后管理）
                    String EnquiryAfterLoanManagement = recordElt.elementTextTrim("PC05BS06");
                    //最近2年内的查询次数（担保资格审查）
                    String GuaranteeQualificationExamination = recordElt.elementTextTrim("PC05BS07");
                    //最近2年内的查询次数（特约商户实名审查）
                    String SpecialMerchantEnquiries = recordElt.elementTextTrim("PC05BS08");

                    QueryRecordSummary queryRecordSummary = new QueryRecordSummary();
                    queryRecordSummary.setLoanApproval(LoanApproval);
                    queryRecordSummary.setCreditCardApproval(CreditCardApproval);
                    queryRecordSummary.setLoanApprovalNumber(LoanApprovalNumber);
                    queryRecordSummary.setCreditCardApprovalNumber(CreditCardApprovalNumber);
                    queryRecordSummary.setIqueryTimes(IqueryTimes);
                    queryRecordSummary.setEnquiryAfterLoanManagement(EnquiryAfterLoanManagement);
                    queryRecordSummary.setGuaranteeQualificationExamination(GuaranteeQualificationExamination);
                    queryRecordSummary.setSpecialMerchantEnquiries(SpecialMerchantEnquiries);
                    queryRecordSummaryList.add(queryRecordSummary);
                }
            }
            reportMessageData.setCrt2_p_queryrecordsummary(queryRecordSummaryList);

            //授信协议信息
            Element pca = rootElt.element("PCA");
            Iterator pd02 = pca.elementIterator("PD02");
            List<CreditAgreement> creditAgreementList = new ArrayList<>();
            if (pd02.hasNext()) {
                while (pd02.hasNext()) {
                    Element recordmoblie = (Element) pd02.next();
                    Iterator pd02a = recordmoblie.elementIterator("PD02A");
                    if (pd02a.hasNext()) {
                        CreditAgreement creditAgreement = new CreditAgreement();
                        while (pd02a.hasNext()) {
                            Element recordmmmm = (Element) pd02a.next();
                            //授信协议编号
                            String CreditAgreementNumber = recordmmmm.elementTextTrim("PD02AI01");
                            //业务管理机构类型
                            String BusinessManagementType = recordmmmm.elementTextTrim("PD02AD01");
                            //业务管理机构
                            String BusinessManagement = recordmmmm.elementTextTrim("PD02AI02");
                            //授信协议标识
                            String CreditAgreementMark = recordmmmm.elementTextTrim("PD02AI03");
                            //授信额度用途
                            String CreditLineUsage = recordmmmm.elementTextTrim("PD02AD02");
                            //授信额度
                            String CreditLine = recordmmmm.elementTextTrim("PD02AJ01");
                            //币种
                            String Currency = recordmmmm.elementTextTrim("PD02AD03");
                            //生效日期
                            String EffectiveDate = recordmmmm.elementTextTrim("PD02AR01");
                            //到期日期
                            String ExpirationDate = recordmmmm.elementTextTrim("PD02AR02");
                            //授信协议状态
                            String CreditAgreementStatus = recordmmmm.elementTextTrim("PD02AD04");
                            //授信限额
                            String CreditLimit = recordmmmm.elementTextTrim("PD02AJ03");
                            //授信限额编号
                            String CreditLimitNumber = recordmmmm.elementTextTrim("PD02AI04");
                            //已用额度
                            String AmountUsed = recordmmmm.elementTextTrim("PD02AJ04");

                            creditAgreement.setCreditAgreementNumber(CreditAgreementNumber);
                            creditAgreement.setBusinessManagementType(BusinessManagementType);
                            creditAgreement.setBusinessManagement(BusinessManagement);
                            creditAgreement.setCreditAgreementMark(CreditAgreementMark);
                            creditAgreement.setCreditLineUsage(CreditLineUsage);
                            creditAgreement.setCreditLine(CreditLine);
                            creditAgreement.setCurrency(Currency);
                            creditAgreement.setEffectiveDate(EffectiveDate);
                            creditAgreement.setExpirationDate(ExpirationDate);
                            creditAgreement.setCreditAgreementStatus(CreditAgreementStatus);
                            creditAgreement.setCreditLimit(CreditLimit);
                            creditAgreement.setCreditLimitNumber(CreditLimitNumber);
                            creditAgreement.setAmountUsed(AmountUsed);

                            creditAgreementList.add(creditAgreement);
                        }
                    }

                    //标注及声明信息
                    Iterator pd02z = recordmoblie.elementIterator("PD02Z");
                    List<CreditDeclarativeInformation> creditDeclarativeInformationList = new ArrayList<CreditDeclarativeInformation>();
                    if (pd02z.hasNext()) {
                        while (pd02z.hasNext()) {
                            Element recordmmmm = (Element) pd02z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PD02ZS01");
                            Iterator pd02h = recordmmmm.elementIterator("PD02ZH");
                            if (pd02h.hasNext()) {
                                while (pd02h.hasNext()) {
                                    Element recordxx = (Element) pd02h.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordxx.elementTextTrim("PD02ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordxx.elementTextTrim("PD02ZQ01");
                                    //添加日期
                                    String AddDate = recordxx.elementTextTrim("PD02ZR01");

                                    CreditDeclarativeInformation creditDeclarativeInformation = new CreditDeclarativeInformation();
                                    creditDeclarativeInformation.setDeclarations(Declarations);
                                    creditDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    creditDeclarativeInformation.setStatementContent(StatementContent);
                                    creditDeclarativeInformation.setAddDate(AddDate);
                                    creditDeclarativeInformationList.add(creditDeclarativeInformation);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_creditdeclarativeinfo(creditDeclarativeInformationList);
                    }
                }
            }
            reportMessageData.setCrt2_p_creditagreement(creditAgreementList);

            //借贷账户信息
            Element pda = rootElt.element("PDA");
            Iterator pd01 = pda.elementIterator("PD01");
            List essentialList = new ArrayList<>();
            List<EssentialInformation> essentialInformationList = new ArrayList<>();
            List<TheLatestPerformance> latestPerformanceList = new ArrayList<>();
            List<LastMonthly> lastMonthList = new ArrayList<LastMonthly>();
            List<LastTwentyfourMonth> lastTwentyfourMonthlyList = new ArrayList<>();
            List<LastFiveYears> lastFiveYearsDataList = new ArrayList<>();
            List<SpecialTransaction> specialTransactionDtaList = new ArrayList<>();
            List<SpecialEvents> specialEventsDataList = new ArrayList<>();
            List<LargeAmountSpecial> largeAmountSpecialDataList = new ArrayList<>();
            List<DeclarativeInformation> declarativeInformationDataList = new ArrayList<>();

            //基本信息
            if (pd01.hasNext()) {
                while (pd01.hasNext()) {
                    Map pd01Map = new HashMap<String, Object>();
                    Element recordmoblie = (Element) pd01.next();
                    Iterator pd01a = recordmoblie.elementIterator("PD01A");
                    String AccountNumber = null;
                    String AccountType = null;
                    if (pd01a.hasNext()) {
                        EssentialInformation essentialInformation = new EssentialInformation();
                        while (pd01a.hasNext()) {
                            Element recordmmmm = (Element) pd01a.next();
                            //账户编号
                            AccountNumber = recordmmmm.elementTextTrim("PD01AI01");
                            //账户类型
                            AccountType = recordmmmm.elementTextTrim("PD01AD01");
                            //业务管理机构类型
                            String BusinessInstitutions = recordmmmm.elementTextTrim("PD01AD02");
                            //业务管理机构代码
                            String BusinessCode = recordmmmm.elementTextTrim("PD01AI02");
                            //账户标识
                            String AccountIdentification = recordmmmm.elementTextTrim("PD01AI03");
                            //授信协议编号
                            String CreditAgreementNumbers = recordmmmm.elementTextTrim("PD01AI04");
                            //业务种类
                            String BusinessTypes = recordmmmm.elementTextTrim("PD01AD03");
                            //开立日期
                            String IssuanceDate = recordmmmm.elementTextTrim("PD01AR01");
                            //币种
                            String Currency = recordmmmm.elementTextTrim("PD01AD04");
                            //借款金额
                            String LoanAmount = recordmmmm.elementTextTrim("PD01AJ01");
                            //账户授信额度
                            String CreditLineOfAccount = recordmmmm.elementTextTrim("PD01AJ02");
                            //共享授信额度
                            String SharedCreditLine = recordmmmm.elementTextTrim("PD01AJ03");
                            //到期日期
                            String ExpirationDate = recordmmmm.elementTextTrim("PD01AR02");
                            //还款方式
                            String RepaymentMethod = recordmmmm.elementTextTrim("PD01AD05");
                            //还款频率
                            String RepaymentFrequency = recordmmmm.elementTextTrim("PD01AD06");
                            //还款期数
                            String RepaymentPeriod = recordmmmm.elementTextTrim("PD01AS01");
                            //担保方式
                            String GuaranteeMethod = recordmmmm.elementTextTrim("PD01AD07");
                            //贷款发放形式
                            String LoanIssuance = recordmmmm.elementTextTrim("PD01AD08");
                            //共同借款标志
                            String CommonBorrowingMark = recordmmmm.elementTextTrim("PD01AD09");
                            //债权转移时的还款状态
                            String RepaymentState = recordmmmm.elementTextTrim("PD01AD10");

                            essentialInformation.setAccountNumber(AccountNumber);
                            essentialInformation.setAccountType(AccountType);
                            essentialInformation.setBusinessInstitutions(BusinessInstitutions);
                            essentialInformation.setBusinessCode(BusinessCode);
                            essentialInformation.setAccountIdentification(AccountIdentification);
                            essentialInformation.setCreditAgreementNumber(CreditAgreementNumbers);
                            essentialInformation.setBusinessTypes(BusinessTypes);
                            essentialInformation.setIssuanceDate(IssuanceDate);
                            essentialInformation.setCurrency(Currency);
                            essentialInformation.setLoanAmount(LoanAmount);
                            essentialInformation.setCreditLineOfAccount(CreditLineOfAccount);
                            essentialInformation.setSharedCreditLine(SharedCreditLine);
                            essentialInformation.setExpirationDate(ExpirationDate);
                            essentialInformation.setRepaymentMethod(RepaymentMethod);
                            essentialInformation.setRepaymentFrequency(RepaymentFrequency);
                            essentialInformation.setRepaymentPeriod(RepaymentPeriod);
                            essentialInformation.setGuaranteeMethod(GuaranteeMethod);
                            essentialInformation.setLoanIssuance(LoanIssuance);
                            essentialInformation.setCommonBorrowingMark(CommonBorrowingMark);
                            essentialInformation.setRepaymentState(RepaymentState);

                            essentialInformationList.add(essentialInformation);
                        }
                        reportMessageData.setCrt2_p_essentialinformation(essentialInformationList);
                    }
                    //最新表现信息
                    Iterator pd01b = recordmoblie.elementIterator("PD01B");
                    List<TheLatestPerformance> theLatestPerformanceList = new ArrayList<TheLatestPerformance>();
                    if (pd01b.hasNext()) {
                        while (pd01b.hasNext()) {
                            Element recordmmmm = (Element) pd01b.next();
                            //账户状态
                            String AccountState = recordmmmm.elementTextTrim("PD01BD01");
                            //关闭日期
                            String ClosingDate = recordmmmm.elementTextTrim("PD01BR01");
                            //转出月份
                            String TransferMonth = recordmmmm.elementTextTrim("PD01BR04");
                            //余额
                            String Balance = recordmmmm.elementTextTrim("PD01BJ01");
                            //最近一次还款日期
                            String LastRepaymentDate = recordmmmm.elementTextTrim("PD01BR02");
                            //最近一次还款金额
                            String LatestRepaymentAmount = recordmmmm.elementTextTrim("PD01BJ02");
                            //五级分类
                            String Classification = recordmmmm.elementTextTrim("PD01BD03");
                            //还款状态
                            String RepaymentState = recordmmmm.elementTextTrim("PD01BD04");
                            //信息报告日期
                            String DateOfReport = recordmmmm.elementTextTrim("PD01BR03");

                            TheLatestPerformance theLatestPerformance = new TheLatestPerformance();
                            theLatestPerformance.setAccountNumber(AccountNumber);
                            theLatestPerformance.setAccountState(AccountState);
                            theLatestPerformance.setClosingDate(ClosingDate);
                            theLatestPerformance.setTransferMonth(TransferMonth);
                            theLatestPerformance.setBalance(Balance);
                            theLatestPerformance.setLastRepaymentDate(LastRepaymentDate);
                            theLatestPerformance.setLatestRepaymentAmount(LatestRepaymentAmount);
                            theLatestPerformance.setClassification(Classification);
                            theLatestPerformance.setRepaymentState(RepaymentState);
                            theLatestPerformance.setDateOfReport(DateOfReport);

                            theLatestPerformanceList.add(theLatestPerformance);
                            latestPerformanceList.add(theLatestPerformance);
                        }
                        reportMessageData.setCrt2_p_thelatestperformance(latestPerformanceList);
                    }
                    //近一次月度表现信息
                    Iterator pd01c = recordmoblie.elementIterator("PD01C");
                    List<LastMonthly> lastMonthlyList = new ArrayList<LastMonthly>();
                    if (pd01c.hasNext()) {
                        while (pd01c.hasNext()) {
                            Element recordmmmm = (Element) pd01c.next();
                            //月份
                            String Month = recordmmmm.elementTextTrim("PD01CR01");
                            //账户状态
                            String AccountState = recordmmmm.elementTextTrim("PD01CD01");
                            //余额
                            String Balance = recordmmmm.elementTextTrim("PD01CJ01");
                            //已用额度
                            String AmountUsed = recordmmmm.elementTextTrim("PD01CJ02");
                            //未出单的大额专项分期余额
                            String StagingBalance = recordmmmm.elementTextTrim("PD01CJ03");
                            //五级分类
                            String Classification = recordmmmm.elementTextTrim("PD01CD02");
                            //剩余还款期数
                            String RemainingRepayment = recordmmmm.elementTextTrim("PD01CS01");
                            //结算/应还款日
                            String SettlementDay = recordmmmm.elementTextTrim("PD01CR02");
                            //本月应还款
                            String Reimbursement = recordmmmm.elementTextTrim("PD01CJ04");
                            //本月实还款
                            String Repayments = recordmmmm.elementTextTrim("PD01CJ05");
                            //最后一次还款日期
                            String LastRepaymentDate = recordmmmm.elementTextTrim("PD01CR03");
                            //当前逾期数
                            String CurrentOverduePeriod = recordmmmm.elementTextTrim("PD01CS02");
                            //当前逾期总额
                            String CurrentOverdueTotal = recordmmmm.elementTextTrim("PD01CJ06");
                            //逾期 31—60 天未还本金
                            String Overdue31To60Amount = recordmmmm.elementTextTrim("PD01CJ07");
                            //逾期 61－90 天未还本金
                            String Overdue61To90Amount = recordmmmm.elementTextTrim("PD01CJ08");
                            //逾期 91－180 天未还本金
                            String Overdue91To180Amount = recordmmmm.elementTextTrim("PD01CJ09");
                            //逾期 180 天以上未还本金
                            String OverdueOver180Amount = recordmmmm.elementTextTrim("PD01CJ10");
                            //透支 180 天以上未付余额
                            String OverdraftDay = recordmmmm.elementTextTrim("PD01CJ11");
                            //最近 6 个月平均使用额度
                            String AverageUsageQuota = recordmmmm.elementTextTrim("PD01CJ12");
                            //最近 6 个月平均透支余额
                            String AverageOverdraftBalance = recordmmmm.elementTextTrim("PD01CJ13");
                            //最大使用额度
                            String AmountOfUse = recordmmmm.elementTextTrim("PD01CJ14");
                            //最大透支余额
                            String OverdraftBalance = recordmmmm.elementTextTrim("PD01CJ15");
                            //信息报告日期
                            String DateOfReport = recordmmmm.elementTextTrim("PD01CR04");

                            LastMonthly lastMonthly = new LastMonthly();
                            lastMonthly.setAccountNumber(AccountNumber);
                            lastMonthly.setMonth(Month);
                            lastMonthly.setAccountState(AccountState);
                            lastMonthly.setBalance(Balance);
                            lastMonthly.setAmountUsed(AmountUsed);
                            lastMonthly.setStagingBalance(StagingBalance);
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
                            lastMonthly.setOverdraftDay(OverdraftDay);
                            lastMonthly.setAverageUsageQuota(AverageUsageQuota);
                            lastMonthly.setAverageOverdraftBalance(AverageOverdraftBalance);
                            lastMonthly.setAmountOfUse(AmountOfUse);
                            lastMonthly.setOverdraftBalance(OverdraftBalance);
                            lastMonthly.setDateOfReport(DateOfReport);

                            lastMonthlyList.add(lastMonthly);
                            lastMonthList.add(lastMonthly);
                        }
                        reportMessageData.setCrt2_p_lastmonthly(lastMonthList);
                    }
                    //最近24个月还款记录信息
                    Iterator pd01d = recordmoblie.elementIterator("PD01D");
                    List<LastTwentyfourMonth> lastTwentyfourMonthList = new ArrayList<>();
                    if (pd01d.hasNext()) {
                        while (pd01d.hasNext()) {
                            Element recordmmmm = (Element) pd01d.next();
                            //起止年月
                            String BeginningYear = recordmmmm.elementTextTrim("PD01DR01");
                            //截止年月
                            String EndYear = recordmmmm.elementTextTrim("PD01DR02");

                            Iterator pd01dh = recordmmmm.elementIterator("PD01DH");
                            if (pd01dh.hasNext()) {
                                while (pd01dh.hasNext()) {
                                    Element recordnn = (Element) pd01dh.next();
                                    //月份
                                    String Month = recordnn.elementTextTrim("PD01DR03");
                                    //还款状态
                                    String RepaymentState = recordnn.elementTextTrim("PD01DD01");

                                    LastTwentyfourMonth lastTwentyfourMonth = new LastTwentyfourMonth();
                                    lastTwentyfourMonth.setAccountNumber(AccountNumber);
                                    lastTwentyfourMonth.setBeginningYear(BeginningYear);
                                    lastTwentyfourMonth.setEndYear(EndYear);
                                    lastTwentyfourMonth.setMonth(Month);
                                    lastTwentyfourMonth.setRepaymentState(RepaymentState);
                                    lastTwentyfourMonthList.add(lastTwentyfourMonth);
                                    lastTwentyfourMonthlyList.add(lastTwentyfourMonth);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_lasttwentyfourmonth(lastTwentyfourMonthlyList);
                    }
                    //最近 5 年内历史表现信息
                    Iterator pd01e = recordmoblie.elementIterator("PD01E");
                    List<LastFiveYears> lastFiveYearsList = new ArrayList<LastFiveYears>();
                    if (pd01e.hasNext()) {
                        while (pd01e.hasNext()) {
                            Element recordmmmm = (Element) pd01e.next();
                            //起止年月
                            String BeginningYear = recordmmmm.elementTextTrim("PD01ER01");
                            //截止年月
                            String EndYear = recordmmmm.elementTextTrim("PD01ER02");
                            //月数
                            String Mom = recordmmmm.elementTextTrim("PD01ES01");

                            Iterator pd01eh = recordmmmm.elementIterator("PD01EH");
                            if (pd01eh.hasNext()) {
                                while (pd01eh.hasNext()) {
                                    Element recordnn = (Element) pd01eh.next();
                                    //月份
                                    String Month = recordnn.elementTextTrim("PD01ER03");
                                    //还款状态
                                    String RepaymentState = recordnn.elementTextTrim("PD01ED01");
                                    //逾期（透支） 总额
                                    String TotalOverdue = recordnn.elementTextTrim("PD01EJ01");

                                    LastFiveYears lastFiveYears = new LastFiveYears();
                                    lastFiveYears.setAccountNumber(AccountNumber);
                                    lastFiveYears.setBeginningYear(BeginningYear);
                                    lastFiveYears.setEndYear(EndYear);
                                    lastFiveYears.setMom(Mom);
                                    lastFiveYears.setMonth(Month);
                                    lastFiveYears.setRepaymentState(RepaymentState);
                                    lastFiveYears.setTotalOverdue(TotalOverdue);
                                    lastFiveYearsList.add(lastFiveYears);
                                    lastFiveYearsDataList.add(lastFiveYears);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_lastfiveyears(lastFiveYearsDataList);
                    }
                    //特殊交易信息
                    Iterator pd01f = recordmoblie.elementIterator("PD01F");
                    List<SpecialTransaction> specialTransactionList = new ArrayList<SpecialTransaction>();
                    if (pd01f.hasNext()) {
                        while (pd01f.hasNext()) {
                            Element recordmmmm = (Element) pd01f.next();
                            //特殊交易个数
                            String SpecialTransactionNumber = recordmmmm.elementTextTrim("PD01FS01");

                            Iterator pd01fh = recordmmmm.elementIterator("PD01FH");
                            if (pd01fh.hasNext()) {
                                while (pd01fh.hasNext()) {
                                    Element recordnn = (Element) pd01fh.next();
                                    //特殊交易类型
                                    String SpecialTransactionType = recordnn.elementTextTrim("PD01FD01");
                                    //特殊交易发生日期
                                    String SpecialTransactionDate = recordnn.elementTextTrim("PD01FR01");
                                    //到期日期变更月数
                                    String ExpirationDate = recordnn.elementTextTrim("PD01FS02");
                                    //特殊交易发生金额
                                    String SpecialTransactionAmount = recordnn.elementTextTrim("PD01FJ01");
                                    //特殊交易明细记录
                                    String SpecialTransactionDetails = recordnn.elementTextTrim("PD01FQ01");

                                    SpecialTransaction specialTransaction = new SpecialTransaction();
                                    specialTransaction.setAccountNumber(AccountNumber);
                                    specialTransaction.setSpecialTransactionNumber(SpecialTransactionNumber);
                                    specialTransaction.setSpecialTransactionType(SpecialTransactionType);
                                    specialTransaction.setSpecialTransactionDate(SpecialTransactionDate);
                                    specialTransaction.setExpirationDate(ExpirationDate);
                                    specialTransaction.setSpecialTransactionAmount(SpecialTransactionAmount);
                                    specialTransaction.setSpecialTransactionDetails(SpecialTransactionDetails);

                                    specialTransactionList.add(specialTransaction);
                                    specialTransactionDtaList.add(specialTransaction);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_specialtransaction(specialTransactionDtaList);
                    }
                    //特殊事件说明信息
                    Iterator pd01g = recordmoblie.elementIterator("PD01G");
                    List<SpecialEvents> specialEventsList = new ArrayList<SpecialEvents>();
                    if (pd01g.hasNext()) {
                        while (pd01g.hasNext()) {
                            Element recordmmmm = (Element) pd01g.next();
                            //特殊事件说明个数
                            String SpecialEventDescription = recordmmmm.elementTextTrim("PD01GS01");

                            Iterator pd01gh = recordmmmm.elementIterator("PD01GH");
                            if (pd01gh.hasNext()) {
                                while (pd01gh.hasNext()) {
                                    Element recordnn = (Element) pd01gh.next();
                                    //特殊事件发生月份
                                    String SpecialEventsMonth = recordnn.elementTextTrim("PD01GR01");
                                    //特殊事件类型
                                    String SpecialEventType = recordnn.elementTextTrim("PD01GD01");

                                    SpecialEvents specialEvents = new SpecialEvents();
                                    specialEvents.setAccountNumber(AccountNumber);
                                    specialEvents.setSpecialEventDescription(SpecialEventDescription);
                                    specialEvents.setSpecialEventsMonth(SpecialEventsMonth);
                                    specialEvents.setSpecialEventType(SpecialEventType);

                                    specialEventsList.add(specialEvents);
                                    specialEventsDataList.add(specialEvents);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_specialevents(specialEventsDataList);
                    }
                    //大额专项分期信息
                    Iterator pd01h = recordmoblie.elementIterator("PD01H");
                    List<LargeAmountSpecial> largeAmountSpecialList = new ArrayList<LargeAmountSpecial>();
                    if (pd01h.hasNext()) {
                        while (pd01h.hasNext()) {
                            Element recordmmmm = (Element) pd01h.next();
                            //大额专项分期笔数
                            String StagingPenNumber = recordmmmm.elementTextTrim("PD01HS01");

                            Iterator pd01hh = recordmmmm.elementIterator("PD01HH");
                            if (pd01hh.hasNext()) {
                                while (pd01hh.hasNext()) {
                                    Element recordnn = (Element) pd01hh.next();
                                    //大额专项分期额度
                                    String InstallmentQuota = recordnn.elementTextTrim("PD01HJ01");
                                    //分期额度生效日期
                                    String EffectiveDate = recordnn.elementTextTrim("PD01HR01");
                                    //分期额度到期日期
                                    String ExpirationDate = recordnn.elementTextTrim("PD01HR02");
                                    //已用分期金额
                                    String AmountUsed = recordnn.elementTextTrim("PD01HJ02");

                                    LargeAmountSpecial largeAmountSpecial = new LargeAmountSpecial();
                                    largeAmountSpecial.setAccountNumber(AccountNumber);
                                    largeAmountSpecial.setStagingPenNumber(StagingPenNumber);
                                    largeAmountSpecial.setInstallmentQuota(InstallmentQuota);
                                    largeAmountSpecial.setEffectiveDate(EffectiveDate);
                                    largeAmountSpecial.setExpirationDate(ExpirationDate);
                                    largeAmountSpecial.setAmountUsed(AmountUsed);
                                    largeAmountSpecialList.add(largeAmountSpecial);
                                    largeAmountSpecialDataList.add(largeAmountSpecial);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_largeamountspecial(largeAmountSpecialDataList);
                    }
                    //标注及声明信息
                    Iterator pd01z = recordmoblie.elementIterator("PD01Z");
                    List<DeclarativeInformation> declarativeInformationList = new ArrayList<DeclarativeInformation>();
                    if (pd01z.hasNext()) {
                        while (pd01z.hasNext()) {
                            Element recordmmmm = (Element) pd01z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PD01ZS01");

                            Iterator pd01zh = recordmmmm.elementIterator("PD01ZH");
                            if (pd01zh.hasNext()) {
                                while (pd01zh.hasNext()) {
                                    Element recordnn = (Element) pd01zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PD01ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PD01ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PD01ZR01");

                                    DeclarativeInformation declarativeInformation = new DeclarativeInformation();
                                    declarativeInformation.setAccountNumber(AccountNumber);
                                    declarativeInformation.setDeclarations(Declarations);
                                    declarativeInformation.setDeclarationsType(DeclarationsType);
                                    declarativeInformation.setStatementContent(StatementContent);
                                    declarativeInformation.setAddDate(AddDate);

                                    declarativeInformationList.add(declarativeInformation);
                                    declarativeInformationDataList.add(declarativeInformation);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_declarativeinfo(declarativeInformationDataList);
                    }
                    essentialList.add(pd01Map);
                }
            }

            //相关还款责任信息
            Element pcr = rootElt.element("PCR");
            Iterator pd03 = pcr.elementIterator("PD03");
            List<RelatedRepaymentLiabilities> relatedRepaymentLiabilitiesList = new ArrayList<>();
            List<RelatedDeclarativeInformation> relatedDeclarativeInformationDataList = new ArrayList<RelatedDeclarativeInformation>();
            if (pd03.hasNext()) {
                while (pd03.hasNext()) {
                    Element recordmoblie = (Element) pd03.next();
                    Iterator pd03a = recordmoblie.elementIterator("PD03A");
                    if (pd03a.hasNext()) {
                        RelatedRepaymentLiabilities relatedRepaymentLiabilities = new RelatedRepaymentLiabilities();
                        while (pd03a.hasNext()) {
                            Element recordmmmm = (Element) pd03a.next();
                            //主借款人身份类别
                            String PrincipalBorrowerStatus = recordmmmm.elementTextTrim("PD03AD08");
                            //业务管理机构类型
                            String BusinessManagementType = recordmmmm.elementTextTrim("PD03AD01");
                            //业务管理机构
                            String BusinessManagement = recordmmmm.elementTextTrim("PD03AQ01");
                            //业务种类
                            String BusinessTypes = recordmmmm.elementTextTrim("PD03AD02");
                            //开立日期
                            String IssuanceDate = recordmmmm.elementTextTrim("PD03AR01");
                            //到期日期
                            String ExpirationDate = recordmmmm.elementTextTrim("PD03AR02");
                            //相关还款责任人类型
                            String RepaymentType = recordmmmm.elementTextTrim("PD03AD03");
                            //相关还款责任金额
                            String RepaymentLimit = recordmmmm.elementTextTrim("PD03AJ01");
                            //币种
                            String Currency = recordmmmm.elementTextTrim("PD03AD04");
                            //余额
                            String Balance = recordmmmm.elementTextTrim("PD03AJ02");
                            //五级分类
                            String FiveLevelClassification = recordmmmm.elementTextTrim("PD03AD05");
                            //账户类型
                            String AccountType = recordmmmm.elementTextTrim("PD03AD06");
                            //还款状态
                            String RepaymentState = recordmmmm.elementTextTrim("PD03AD07");
                            //逾期月数
                            String OverdueMonths = recordmmmm.elementTextTrim("PD03AS01");
                            //保证合同编号
                            String GuaranteeNumber = recordmmmm.elementTextTrim("PD03AQ02");
                            //String RelateRepayAmount=recordmmmm.elementTextTrim("PD03AJ01");//相关还款责任金额
                            //信息报告日期
                            String DateOfReport = recordmmmm.elementTextTrim("PD03AR03");

                            relatedRepaymentLiabilities.setPrincipalBorrowerStatus(PrincipalBorrowerStatus);
                            relatedRepaymentLiabilities.setBusinessManagementType(BusinessManagementType);
                            relatedRepaymentLiabilities.setBusinessManagement(BusinessManagement);
                            relatedRepaymentLiabilities.setBusinessTypes(BusinessTypes);
                            relatedRepaymentLiabilities.setIssuanceDate(IssuanceDate);
                            relatedRepaymentLiabilities.setExpirationDate(ExpirationDate);
                            relatedRepaymentLiabilities.setRepaymentType(RepaymentType);
                            relatedRepaymentLiabilities.setRepaymentLimit(RepaymentLimit);
                            relatedRepaymentLiabilities.setCurrency(Currency);
                            relatedRepaymentLiabilities.setBalance(Balance);
                            relatedRepaymentLiabilities.setFiveLevelClassification(FiveLevelClassification);
                            relatedRepaymentLiabilities.setAccountType(AccountType);
                            relatedRepaymentLiabilities.setRepaymentState(RepaymentState);
                            relatedRepaymentLiabilities.setOverdueMonths(OverdueMonths);
                            relatedRepaymentLiabilities.setGuaranteeNumber(GuaranteeNumber);

                            relatedRepaymentLiabilities.setDateOfReport(DateOfReport);
                            relatedRepaymentLiabilitiesList.add(relatedRepaymentLiabilities);
                        }
                        reportMessageData.setCrt2_p_relrepayliabilities(relatedRepaymentLiabilitiesList);
                    }
                    //标注及声明信息
                    Iterator pd03z = recordmoblie.elementIterator("PD03Z");
                    List<RelatedDeclarativeInformation> relatedDeclarativeInformationList = new ArrayList<RelatedDeclarativeInformation>();
                    if (pd03z.hasNext()) {
                        while (pd03z.hasNext()) {
                            Element recordmmmm = (Element) pd03z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PD03ZS01");

                            Iterator pd01zh = recordmmmm.elementIterator("PD03ZH");
                            if (pd01zh.hasNext()) {
                                while (pd01zh.hasNext()) {
                                    Element recordnn = (Element) pd01zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PD03ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PD03ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PD03ZR01");

                                    RelatedDeclarativeInformation relatedDeclarativeInformation = new RelatedDeclarativeInformation();
                                    relatedDeclarativeInformation.setDeclarations(Declarations);
                                    relatedDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    relatedDeclarativeInformation.setStatementContent(StatementContent);
                                    relatedDeclarativeInformation.setAddDate(AddDate);

                                    relatedDeclarativeInformationList.add(relatedDeclarativeInformation);
                                    relatedDeclarativeInformationDataList.add(relatedDeclarativeInformation);
                                }
                            }
                        }
                        reportMessageData.setCrt2_p_relateddeclarativeinfo(relatedDeclarativeInformationDataList);
                    }
                }
            }

            //后付费业务信息单元
            Element pnd = rootElt.element("PND");
            Iterator pe01 = pnd.elementIterator("PE01");
            List<PostPaymentBusiness> postPaymentBusinessDataList = new ArrayList<PostPaymentBusiness>();
            List<PostPaidDeclarativeInformation> postPaidDeclarativeInformationDataList = new ArrayList<PostPaidDeclarativeInformation>();
            if (pe01.hasNext()) {
                while (pe01.hasNext()) {
                    Element recordmoblie = (Element) pe01.next();
                    Iterator pe01a = recordmoblie.elementIterator("PE01A");
                    List<PostPaymentBusiness> postPaymentBusinessList = new ArrayList<PostPaymentBusiness>();
                    if (pe01a.hasNext()) {
                        while (pe01a.hasNext()) {
                            Element recordmmmm = (Element) pe01a.next();
                            //后付费账户类型
                            String PaymentAccountType = recordmmmm.elementTextTrim("PE01AD01");
                            //机构名称
                            String OrganizationName = recordmmmm.elementTextTrim("PE01AQ01");
                            //业务类型
                            String BusinessType = recordmmmm.elementTextTrim("PE01AD02");
                            //业务开通日期
                            String BusinessOpeningDate = recordmmmm.elementTextTrim("PE01AR01");
                            //当前缴费状态
                            String CurrentPaymentStatus = recordmmmm.elementTextTrim("PE01AD03");
                            //当前欠费金额
                            String CurrentArrearsAmount = recordmmmm.elementTextTrim("PE01AJ01");
                            //记账年月
                            String YearOfAccount = recordmmmm.elementTextTrim("PE01AR02");
                            //最近 24 个月缴费记录
                            String PaymentRecord = recordmmmm.elementTextTrim("PE01AQ02");

                            PostPaymentBusiness postPaymentBusiness = new PostPaymentBusiness();
                            postPaymentBusiness.setPaymentAccountType(PaymentAccountType);
                            postPaymentBusiness.setOrganizationName(OrganizationName);
                            postPaymentBusiness.setBusinessType(BusinessType);
                            postPaymentBusiness.setBusinessOpeningDate(BusinessOpeningDate);
                            postPaymentBusiness.setCurrentPaymentStatus(CurrentPaymentStatus);
                            postPaymentBusiness.setCurrentArrearsAmount(CurrentArrearsAmount);
                            postPaymentBusiness.setYearOfAccount(YearOfAccount);
                            postPaymentBusiness.setPaymentRecord(PaymentRecord);

                            postPaymentBusinessList.add(postPaymentBusiness);
                            postPaymentBusinessDataList.add(postPaymentBusiness);
                        }
                    }

                    //标注及声明信息
                    Iterator pe01z = recordmoblie.elementIterator("PE01Z");
                    List<PostPaidDeclarativeInformation> postPaidDeclarativeInformationList = new ArrayList<PostPaidDeclarativeInformation>();
                    if (pe01z.hasNext()) {
                        while (pe01z.hasNext()) {
                            Element recordmmmm = (Element) pe01z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PE01ZS01");

                            Iterator pe01zh = recordmmmm.elementIterator("PE01ZH");
                            if (pe01zh.hasNext()) {
                                while (pe01zh.hasNext()) {
                                    Element recordnn = (Element) pe01zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PE01ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PE01ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PE01ZR01");

                                    PostPaidDeclarativeInformation postPaidDeclarativeInformation = new PostPaidDeclarativeInformation();
                                    postPaidDeclarativeInformation.setDeclarations(Declarations);
                                    postPaidDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    postPaidDeclarativeInformation.setStatementContent(StatementContent);
                                    postPaidDeclarativeInformation.setAddDate(AddDate);

                                    postPaidDeclarativeInformationList.add(postPaidDeclarativeInformation);
                                    postPaidDeclarativeInformationDataList.add(postPaidDeclarativeInformation);
                                }
                            }
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_postpaymentbusiness(postPaymentBusinessDataList);
            reportMessageData.setCrt2_p_postpaiddeclarativeinfo(postPaidDeclarativeInformationDataList);

            //欠税记录信息单元
            Element pot = rootElt.element("POT");
            Iterator pf01 = pot.elementIterator("PF01");
            List<TaxArrear> taxArrList = new ArrayList<TaxArrear>();
            List<TaxArrearDeclarativeInformation> taxArrearDeclarativeInformationDataList = new ArrayList<TaxArrearDeclarativeInformation>();
            if (pf01.hasNext()) {
                while (pf01.hasNext()) {
                    Element recordmoblie = (Element) pf01.next();
                    Iterator pf01a = recordmoblie.elementIterator("PF01A");
                    List<TaxArrear> taxArrearList = new ArrayList<TaxArrear>();
                    if (pf01a.hasNext()) {
                        while (pf01a.hasNext()) {
                            Element recordmmmm = (Element) pf01a.next();
                            //主管税务机关
                            String Organname = recordmmmm.elementTextTrim("PF01AQ01");
                            //欠税总额
                            String TaxArreaAmount = recordmmmm.elementTextTrim("PF01AJ01");
                            //欠税统计日期
                            String Revenuedate = recordmmmm.elementTextTrim("PF01AR01");

                            TaxArrear taxArrear = new TaxArrear();
                            taxArrear.setOrganname(Organname);
                            taxArrear.setTaxArreaAmount(TaxArreaAmount);
                            taxArrear.setRevenuedate(Revenuedate);
                            taxArrearList.add(taxArrear);
                            taxArrList.add(taxArrear);
                        }
                    }
                    //标注及声明信息
                    Iterator pf01z = recordmoblie.elementIterator("PF01Z");
                    List<TaxArrearDeclarativeInformation> taxArrearDeclarativeInformationList = new ArrayList<TaxArrearDeclarativeInformation>();
                    if (pf01z.hasNext()) {
                        while (pf01z.hasNext()) {
                            Element recordmmmm = (Element) pf01z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF01ZS01");

                            Iterator pf01zh = recordmmmm.elementIterator("PF01ZH");
                            if (pf01zh.hasNext()) {
                                while (pf01zh.hasNext()) {
                                    Element recordnn = (Element) pf01zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF01ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF01ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF01ZR01");

                                    TaxArrearDeclarativeInformation taxArrearDeclarativeInformation = new TaxArrearDeclarativeInformation();
                                    taxArrearDeclarativeInformation.setDeclarations(Declarations);
                                    taxArrearDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    taxArrearDeclarativeInformation.setStatementContent(StatementContent);

                                    taxArrearDeclarativeInformation.setAddDate(AddDate);
                                    taxArrearDeclarativeInformationList.add(taxArrearDeclarativeInformation);
                                    taxArrearDeclarativeInformationDataList.add(taxArrearDeclarativeInformation);
                                }
                            }
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_taxarreardeclinfo(taxArrearDeclarativeInformationDataList);
            reportMessageData.setCrt2_p_taxarrear(taxArrList);

            //民事判决记录信息单元
            Element pcj = rootElt.element("PCJ");
            Iterator pf02 = pcj.elementIterator("PF02");
            List civilList = new ArrayList<>();
            List<CivilJudgement> civilJudgementDataList = new ArrayList<CivilJudgement>();
            List<CivilJudgementDeclarativeInformation> civilJudgementDeclarativeInformationDataList = new ArrayList<CivilJudgementDeclarativeInformation>();
            if (pf02.hasNext()) {
                while (pf02.hasNext()) {
                    Map pf02Map = new HashMap();
                    Element recordmoblie = (Element) pf02.next();
                    Iterator pf02a = recordmoblie.elementIterator("PF02A");
                    List<CivilJudgement> civilJudgementList = new ArrayList<CivilJudgement>();
                    if (pf02a.hasNext()) {
                        while (pf02a.hasNext()) {
                            Element recordmmmm = (Element) pf02a.next();
                            //立案法院
                            String Court = recordmmmm.elementTextTrim("PF02AQ01");
                            //案由
                            String CaseReason = recordmmmm.elementTextTrim("PF02AQ02");
                            //立案日期
                            String RegisterDate = recordmmmm.elementTextTrim("PF02AR01");
                            //结案方式
                            String ClosedType = recordmmmm.elementTextTrim("PF02AD01");
                            //判决/调解结果
                            String CaseResult = recordmmmm.elementTextTrim("PF02AQ03");
                            //判决/调解生效日期
                            String CaseValidatedate = recordmmmm.elementTextTrim("PF02AR02");
                            //诉讼标的
                            String SuitObject = recordmmmm.elementTextTrim("PF02AQ04");
                            //诉讼标的金额
                            String SuitObjectMoney = recordmmmm.elementTextTrim("PF02AJ01");

                            CivilJudgement civilJudgement = new CivilJudgement();
                            civilJudgement.setCourt(Court);
                            civilJudgement.setCaseReason(CaseReason);
                            civilJudgement.setRegisterDate(RegisterDate);
                            civilJudgement.setClosedType(ClosedType);
                            civilJudgement.setCaseResult(CaseResult);
                            civilJudgement.setCaseValidatedate(CaseValidatedate);
                            civilJudgement.setSuitObject(SuitObject);
                            civilJudgement.setSuitObjectMoney(SuitObjectMoney);

                            civilJudgementList.add(civilJudgement);
                            civilJudgementDataList.add(civilJudgement);
                        }
                        pf02Map.put("PF02A", civilJudgementList);
                    }
                    //标注及声明信息
                    Iterator pf02z = recordmoblie.elementIterator("PF02Z");
                    List<CivilJudgementDeclarativeInformation> civilJudgementDeclarativeInformationList = new ArrayList<CivilJudgementDeclarativeInformation>();
                    if (pf02z.hasNext()) {
                        while (pf02z.hasNext()) {
                            Element recordmmmm = (Element) pf02z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF02ZS01");

                            Iterator pf02zh = recordmmmm.elementIterator("PF02ZH");
                            if (pf02zh.hasNext()) {
                                while (pf02zh.hasNext()) {
                                    Element recordnn = (Element) pf02zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF02ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF02ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF02ZR01");

                                    CivilJudgementDeclarativeInformation civilJudgementDeclarativeInformation = new CivilJudgementDeclarativeInformation();
                                    civilJudgementDeclarativeInformation.setDeclarations(Declarations);
                                    civilJudgementDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    civilJudgementDeclarativeInformation.setStatementContent(StatementContent);
                                    civilJudgementDeclarativeInformation.setAddDate(AddDate);

                                    civilJudgementDeclarativeInformationList.add(civilJudgementDeclarativeInformation);
                                    civilJudgementDeclarativeInformationDataList.add(civilJudgementDeclarativeInformation);
                                }
                            }
                        }
                    }
                    civilList.add(pf02Map);
                }
            }
            reportMessageData.setCrt2_p_civiljudgement(civilJudgementDataList);
            reportMessageData.setCrt2_p_civiljudgementdeclinfo(civilJudgementDeclarativeInformationDataList);

            //强制执行记录信息单元
            Element pce = rootElt.element("PCE");
            Iterator pf03 = pce.elementIterator("PF03");
            List<ForceExecution> forceExecutionDataList = new ArrayList<ForceExecution>();
            List<ForceExecutionDeclarativeInformation> forceExecutionDeclarativeInformationDataList = new ArrayList<ForceExecutionDeclarativeInformation>();
            if (pf03.hasNext()) {
                while (pf03.hasNext()) {
                    Element recordmoblie = (Element) pf03.next();
                    Iterator pf03a = recordmoblie.elementIterator("PF03A");
                    List<ForceExecution> forceExecutionList = new ArrayList<ForceExecution>();
                    if (pf03a.hasNext()) {
                        while (pf03a.hasNext()) {
                            Element recordmmmm = (Element) pf03a.next();
                            //执行法院
                            String Court = recordmmmm.elementTextTrim("PF03AQ01");
                            //执行案由
                            String CaseReason = recordmmmm.elementTextTrim("PF03AQ02");
                            //立案日期
                            String RegisterDate = recordmmmm.elementTextTrim("PF03AR01");
                            //结案方式
                            String ClosedType = recordmmmm.elementTextTrim("PF03AD01");
                            //案件状态
                            String CaseState = recordmmmm.elementTextTrim("PF03AQ03");
                            //结案日期
                            String ClosedDate = recordmmmm.elementTextTrim("PF03AR02");
                            //申请执行标的
                            String EnforceObject = recordmmmm.elementTextTrim("PF03AQ04");
                            //申请执行标的金额
                            String EnforceObjectMoney = recordmmmm.elementTextTrim("PF03AJ01");
                            //已执行标的
                            String AlreadyEnforceObject = recordmmmm.elementTextTrim("PF03AQ05");
                            //已执行标的金额
                            String AlreadyEnforceObjectMoney = recordmmmm.elementTextTrim("PF03AJ02");


                            ForceExecution forceExecution = new ForceExecution();
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
                            forceExecutionDataList.add(forceExecution);
                        }
                    }
                    //标注及声明信息
                    Iterator pf03z = recordmoblie.elementIterator("PF03Z");
                    List<ForceExecutionDeclarativeInformation> forceExecutionDeclarativeInformationList = new ArrayList<ForceExecutionDeclarativeInformation>();
                    if (pf03z.hasNext()) {
                        while (pf03z.hasNext()) {
                            Element recordmmmm = (Element) pf03z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF03ZS01");

                            Iterator pf03zh = recordmmmm.elementIterator("PF03ZH");
                            if (pf03zh.hasNext()) {
                                while (pf03zh.hasNext()) {
                                    Element recordnn = (Element) pf03zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF03ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF03ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF03ZR01");

                                    ForceExecutionDeclarativeInformation forceExecutionDeclarativeInformation = new ForceExecutionDeclarativeInformation();
                                    forceExecutionDeclarativeInformation.setDeclarations(Declarations);
                                    forceExecutionDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    forceExecutionDeclarativeInformation.setStatementContent(StatementContent);
                                    forceExecutionDeclarativeInformation.setAddDate(AddDate);

                                    forceExecutionDeclarativeInformationList.add(forceExecutionDeclarativeInformation);
                                    forceExecutionDeclarativeInformationDataList.add(forceExecutionDeclarativeInformation);
                                }
                            }
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_forceexecution(forceExecutionDataList);
            reportMessageData.setCrt2_p_forceexecutiondeclinfo(forceExecutionDeclarativeInformationDataList);


            //行政处罚记录信息
            Element pap = rootElt.element("PAP");
            Iterator pf04 = pap.elementIterator("PF04");
            List<AdminPunishment> adminPunishmentDataList = new ArrayList<AdminPunishment>();
            List<AdminPunishmentDeclarativeInformation> adminPunishmentDeclarativeInformationDataList = new ArrayList<AdminPunishmentDeclarativeInformation>();
            if (pf04.hasNext()) {
                while (pf04.hasNext()) {
                    Element recordmoblie = (Element) pf04.next();
                    Iterator pf04a = recordmoblie.elementIterator("PF04A");
                    List<AdminPunishment> adminPunishmentList = new ArrayList<AdminPunishment>();
                    if (pf04a.hasNext()) {
                        while (pf04a.hasNext()) {
                            Element recordmmmm = (Element) pf04a.next();
                            //处罚机构
                            String Organname = recordmmmm.elementTextTrim("PF04AQ01");
                            //处罚内容
                            String Content = recordmmmm.elementTextTrim("PF04AQ02");
                            //处罚金额
                            String Money = recordmmmm.elementTextTrim("PF04AJ01");
                            //处罚生效日期
                            String BeginDate = recordmmmm.elementTextTrim("PF04AR01");
                            //处罚截止日期
                            String EndDate = recordmmmm.elementTextTrim("PF04AR02");
                            //行政复议结果
                            String Result = recordmmmm.elementTextTrim("PF04AQ03");

                            AdminPunishment adminPunishment = new AdminPunishment();
                            adminPunishment.setOrganname(Organname);
                            adminPunishment.setContent(Content);
                            adminPunishment.setMoney(Money);
                            adminPunishment.setBeginDate(BeginDate);
                            adminPunishment.setEndDate(EndDate);
                            adminPunishment.setResult(Result);
                            adminPunishmentList.add(adminPunishment);
                            adminPunishmentDataList.add(adminPunishment);
                        }
                    }
                    //标注及声明信息
                    Iterator pf04z = recordmoblie.elementIterator("PF04Z");
                    List<AdminPunishmentDeclarativeInformation> adminPunishmentDeclarativeInformationList = new ArrayList<AdminPunishmentDeclarativeInformation>();
                    if (pf04z.hasNext()) {
                        while (pf04z.hasNext()) {
                            Element recordmmmm = (Element) pf04z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF04ZS01");

                            Iterator pf04zh = recordmmmm.elementIterator("PF04ZH");
                            if (pf04zh.hasNext()) {
                                while (pf04zh.hasNext()) {
                                    Element recordnn = (Element) pf04zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF04ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF04ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF04ZR01");

                                    AdminPunishmentDeclarativeInformation adminPunishmentDeclarativeInformation = new AdminPunishmentDeclarativeInformation();
                                    adminPunishmentDeclarativeInformation.setDeclarations(Declarations);
                                    adminPunishmentDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    adminPunishmentDeclarativeInformation.setStatementContent(StatementContent);
                                    adminPunishmentDeclarativeInformation.setAddDate(AddDate);

                                    adminPunishmentDeclarativeInformationList.add(adminPunishmentDeclarativeInformation);
                                    adminPunishmentDeclarativeInformationDataList.add(adminPunishmentDeclarativeInformation);
                                }
                            }
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_adminpunishment(adminPunishmentDataList);
            reportMessageData.setCrt2_p_adminpunishdeclinfor(adminPunishmentDeclarativeInformationDataList);

            //住房公积金参缴记录信息段
            Element phf = rootElt.element("PHF");
            Iterator pf05 = phf.elementIterator("PF05");
            List<AccFund> accfundDataList = new ArrayList<AccFund>();
            List<AccfundDeclarativeInformation> accfundDeclarativeInformationDataList = new ArrayList<AccfundDeclarativeInformation>();
            if (pf05.hasNext()) {
                while (pf05.hasNext()) {
                    Element recordmoblie = (Element) pf05.next();
                    Iterator pf05a = recordmoblie.elementIterator("PF05A");
                    List<AccFund> accfundList = new ArrayList<AccFund>();
                    if (pf05a.hasNext()) {
                        while (pf05a.hasNext()) {
                            Element recordmmmm = (Element) pf05a.next();
                            //参缴地
                            String Area = recordmmmm.elementTextTrim("PF05AQ01");
                            //参缴日期
                            String RegisterDate = recordmmmm.elementTextTrim("PF05AR01");
                            //缴费状态
                            String State = recordmmmm.elementTextTrim("PF05AD01");
                            //初缴月份
                            String FirstMonth = recordmmmm.elementTextTrim("PF05AR02");
                            //缴至月份
                            String ToMonth = recordmmmm.elementTextTrim("PF05AR03");
                            //单位缴存比例
                            String ComPercent = recordmmmm.elementTextTrim("PF05AQ02");
                            //个人缴存比例
                            String OwnPercent = recordmmmm.elementTextTrim("PF05AQ03");
                            //月缴存额
                            String Pay = recordmmmm.elementTextTrim("PF05AJ01");
                            //缴费单位
                            String Organname = recordmmmm.elementTextTrim("PF05AQ04");
                            //信息更新日期
                            String GetTime = recordmmmm.elementTextTrim("PF05AR04");

                            AccFund accfund = new AccFund();
                            accfund.setArea(Area);
                            accfund.setRegisterDate(RegisterDate);
                            accfund.setState(State);
                            accfund.setFirstMonth(FirstMonth);
                            accfund.setToMonth(ToMonth);
                            accfund.setComPercent(ComPercent);
                            accfund.setOwnPercent(OwnPercent);
                            accfund.setPay(Pay);
                            accfund.setOrganname(Organname);
                            accfund.setGetTime(GetTime);

                            accfundList.add(accfund);
                            accfundDataList.add(accfund);
                        }
                    }
                    //标注及声明信息
                    Iterator pf05z = recordmoblie.elementIterator("PF05Z");
                    List<AccfundDeclarativeInformation> accfundDeclarativeInformationList = new ArrayList<AccfundDeclarativeInformation>();
                    if (pf05z.hasNext()) {
                        while (pf05z.hasNext()) {
                            Element recordmmmm = (Element) pf05z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF05ZS01");

                            Iterator pf05zh = recordmmmm.elementIterator("PF05ZH");
                            if (pf05zh.hasNext()) {
                                while (pf05zh.hasNext()) {
                                    Element recordnn = (Element) pf05zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF05ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF05ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF05ZR01");

                                    AccfundDeclarativeInformation accfundDeclarativeInformation = new AccfundDeclarativeInformation();
                                    accfundDeclarativeInformation.setDeclarations(Declarations);
                                    accfundDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    accfundDeclarativeInformation.setStatementContent(StatementContent);
                                    accfundDeclarativeInformation.setAddDate(AddDate);

                                    accfundDeclarativeInformationList.add(accfundDeclarativeInformation);
                                    accfundDeclarativeInformationDataList.add(accfundDeclarativeInformation);
                                }
                            }
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_accfund(accfundDataList);
            reportMessageData.setCrt2_p_accfunddeclinfor(accfundDeclarativeInformationDataList);

            //低保救助记录信息单元
            Element pbs = rootElt.element("PBS");
            Iterator pf06 = pbs.elementIterator("PF06");
            List<Salvation> salvationDataList = new ArrayList<Salvation>();
            List<SalvationDeclarativeInformation> salvationDeclarativeInformationDataList = new ArrayList<SalvationDeclarativeInformation>();
            if (pf06.hasNext()) {
                while (pf06.hasNext()) {
                    Element recordmoblie = (Element) pf06.next();
                    Iterator pf06a = recordmoblie.elementIterator("PF06A");
                    List<Salvation> salvationList = new ArrayList<Salvation>();
                    if (pf06a.hasNext()) {
                        while (pf06a.hasNext()) {
                            Element recordmmmm = (Element) pf06a.next();
                            //人员类别
                            String PersonnelType = recordmmmm.elementTextTrim("PF06AD01");
                            //所在地
                            String Area = recordmmmm.elementTextTrim("PF06AQ01");
                            //工作单位
                            String Organname = recordmmmm.elementTextTrim("PF06AQ02");
                            //家庭月收入
                            String Money = recordmmmm.elementTextTrim("PF06AQ03");
                            //申请日期
                            String RegisterDate = recordmmmm.elementTextTrim("PF06AR01");
                            //批准日期
                            String PassDate = recordmmmm.elementTextTrim("PF06AR02");
                            //信息更新日期
                            String GetTime = recordmmmm.elementTextTrim("PF06AR03");

                            Salvation salvation = new Salvation();
                            salvation.setPersonnelType(PersonnelType);
                            salvation.setArea(Area);
                            salvation.setOrganname(Organname);
                            salvation.setMoney(Money);
                            salvation.setRegisterDate(RegisterDate);
                            salvation.setPassDate(PassDate);
                            salvation.setGetTime(GetTime);

                            salvationList.add(salvation);
                            salvationDataList.add(salvation);
                        }
                    }

                    //标注及声明信息
                    Iterator pf06z = recordmoblie.elementIterator("PF06Z");
                    List<SalvationDeclarativeInformation> salvationDeclarativeInformationList = new ArrayList<SalvationDeclarativeInformation>();
                    if (pf06z.hasNext()) {
                        while (pf06z.hasNext()) {
                            Element recordmmmm = (Element) pf06z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF06ZS01");

                            Iterator pf06zh = recordmmmm.elementIterator("PF06ZH");
                            if (pf06zh.hasNext()) {
                                while (pf06zh.hasNext()) {
                                    Element recordnn = (Element) pf06zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF06ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF06ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF06ZR01");

                                    SalvationDeclarativeInformation salvationDeclarativeInformation = new SalvationDeclarativeInformation();
                                    salvationDeclarativeInformation.setDeclarations(Declarations);
                                    salvationDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    salvationDeclarativeInformation.setStatementContent(StatementContent);
                                    salvationDeclarativeInformation.setAddDate(AddDate);

                                    salvationDeclarativeInformationList.add(salvationDeclarativeInformation);
                                    salvationDeclarativeInformationDataList.add(salvationDeclarativeInformation);
                                }
                            }
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_salvation(salvationDataList);
            reportMessageData.setCrt2_p_salvationdeclinfo(salvationDeclarativeInformationDataList);

            //执业资格记录信息
            Element ppq = rootElt.element("PPQ");
            Iterator pf07 = ppq.elementIterator("PF07");
            List<Competence> competenceDataList = new ArrayList<Competence>();
            List<CompetenceDeclarativeInformation> competenceDeclarativeInformationDataList = new ArrayList<CompetenceDeclarativeInformation>();
            if (pf07.hasNext()) {
                while (pf07.hasNext()) {
                    Element recordmoblie = (Element) pf07.next();
                    Iterator pf07a = recordmoblie.elementIterator("PF07A");
                    List<Competence> competenceList = new ArrayList<Competence>();
                    if (pf07a.hasNext()) {
                        while (pf07a.hasNext()) {
                            Element recordmmmm = (Element) pf07a.next();
                            //执业资格名称
                            String CompetencyName = recordmmmm.elementTextTrim("PF07AQ01");
                            //颁发机构
                            String Organname = recordmmmm.elementTextTrim("PF07AQ02");
                            //等级
                            String Grade = recordmmmm.elementTextTrim("PF07AD01");
                            //机构所在地
                            String Area = recordmmmm.elementTextTrim("PF07AD02");
                            //获得年月
                            String AwardDate = recordmmmm.elementTextTrim("PF07AR01");
                            //到期年月
                            String EndDate = recordmmmm.elementTextTrim("PF07AR02");
                            //吊销年月
                            String RevokeDate = recordmmmm.elementTextTrim("PF07AR03");

                            Competence competence = new Competence();
                            competence.setCompetencyName(CompetencyName);
                            competence.setOrganname(Organname);
                            competence.setGrade(Grade);
                            competence.setArea(Area);
                            competence.setAwardDate(AwardDate);
                            competence.setEndDate(EndDate);
                            competence.setRevokeDate(RevokeDate);

                            competenceList.add(competence);
                            competenceDataList.add(competence);
                        }
                    }
                    //标注及声明信息
                    Iterator pf07z = recordmoblie.elementIterator("PF07Z");
                    List<CompetenceDeclarativeInformation> competenceDeclarativeInformationList = new ArrayList<CompetenceDeclarativeInformation>();
                    if (pf07z.hasNext()) {
                        while (pf07z.hasNext()) {
                            Element recordmmmm = (Element) pf07z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF07ZS01");

                            Iterator pf07zh = recordmmmm.elementIterator("PF07ZH");
                            if (pf07zh.hasNext()) {
                                while (pf07zh.hasNext()) {
                                    Element recordnn = (Element) pf07zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF07ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF07ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF07ZR01");

                                    CompetenceDeclarativeInformation competenceDeclarativeInformation = new CompetenceDeclarativeInformation();
                                    competenceDeclarativeInformation.setDeclarations(Declarations);
                                    competenceDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    competenceDeclarativeInformation.setStatementContent(StatementContent);
                                    competenceDeclarativeInformation.setAddDate(AddDate);

                                    competenceDeclarativeInformationList.add(competenceDeclarativeInformation);
                                    competenceDeclarativeInformationDataList.add(competenceDeclarativeInformation);
                                }
                            }
                        }
                    }
                }
            }
            reportMessageData.setCrt2_p_competence(competenceDataList);
            reportMessageData.setCrt2_p_competencedeclinfo(competenceDeclarativeInformationDataList);

            //行政奖励记录信息单元
            Element pah = rootElt.element("PAH");
            Iterator pf08 = pah.elementIterator("PF08");
            List<AdminAward> adminAwardDataList = new ArrayList<AdminAward>();
            List<AdminAwardDeclarativeInformation> adminAwardDeclarativeInformationDataList = new ArrayList<AdminAwardDeclarativeInformation>();
            if (pf08.hasNext()) {
                while (pf08.hasNext()) {
                    Map pf08Map = new HashMap();
                    Element recordmoblie = (Element) pf08.next();
                    Iterator pf08a = recordmoblie.elementIterator("PF08A");
                    List<AdminAward> adminAwardList = new ArrayList<AdminAward>();
                    if (pf08a.hasNext()) {
                        while (pf08a.hasNext()) {
                            Element recordmmmm = (Element) pf08a.next();
                            //奖励机构
                            String Organname = recordmmmm.elementTextTrim("PF08AQ01");
                            //奖励内容
                            String Content = recordmmmm.elementTextTrim("PF08AQ02");
                            //生效年月
                            String BeginDate = recordmmmm.elementTextTrim("PF08AR01");
                            //截止年月
                            String EndDate = recordmmmm.elementTextTrim("PF08AR02");

                            AdminAward adminAward = new AdminAward();
                            adminAward.setOrganname(Organname);
                            adminAward.setContent(Content);
                            adminAward.setBeginDate(BeginDate);
                            adminAward.setEndDate(EndDate);
                            adminAwardList.add(adminAward);
                            adminAwardDataList.add(adminAward);
                        }
                    }
                    //标注及声明信息
                    Iterator pf08z = recordmoblie.elementIterator("PF08Z");
                    List<AdminAwardDeclarativeInformation> adminAwardDeclarativeInformationList = new ArrayList<AdminAwardDeclarativeInformation>();
                    if (pf08z.hasNext()) {
                        while (pf08z.hasNext()) {
                            Element recordmmmm = (Element) pf08z.next();
                            //标注及声明个数
                            String Declarations = recordmmmm.elementTextTrim("PF08ZS01");

                            Iterator pf08zh = recordmmmm.elementIterator("PF08ZH");
                            if (pf08zh.hasNext()) {
                                while (pf08zh.hasNext()) {
                                    Element recordnn = (Element) pf08zh.next();
                                    //标注及声明类型
                                    String DeclarationsType = recordnn.elementTextTrim("PF08ZD01");
                                    //标注或声明内容
                                    String StatementContent = recordnn.elementTextTrim("PF08ZQ01");
                                    //添加日期
                                    String AddDate = recordnn.elementTextTrim("PF08ZR01");

                                    AdminAwardDeclarativeInformation adminAwardDeclarativeInformation = new AdminAwardDeclarativeInformation();
                                    adminAwardDeclarativeInformation.setDeclarations(Declarations);
                                    adminAwardDeclarativeInformation.setDeclarationsType(DeclarationsType);
                                    adminAwardDeclarativeInformation.setStatementContent(StatementContent);
                                    adminAwardDeclarativeInformation.setAddDate(AddDate);

                                    adminAwardDeclarativeInformationList.add(adminAwardDeclarativeInformation);
                                    adminAwardDeclarativeInformationDataList.add(adminAwardDeclarativeInformation);
                                }
                            }
                        }
                        pf08Map.put("PF08Z", adminAwardDeclarativeInformationList);
                    }
                }
            }
            reportMessageData.setCrt2_p_adminaward(adminAwardDataList);
            reportMessageData.setCrt2_p_adminawarddeclinfo(adminAwardDeclarativeInformationDataList);

            //标注及声明信息单元(其他)
            Element pos = rootElt.element("POS");
            Iterator pg010 = pos.elementIterator("PG01");
            List<OtherAnnotations> dissentingAnnotationsList = new ArrayList<OtherAnnotations>();
            List<OtherAnnotations> personalAnnotationsList = new ArrayList<OtherAnnotations>();
            List<OtherAnnotations> otherAnnotationsList = new ArrayList<OtherAnnotations>();
            //异议声明条数
            int dissentingAnnotationcount = 0;
            //本人声明条数
            int personalAnnotationcount = 0;
            if (pg010.hasNext()) {
                while (pg010.hasNext()) {
                    Element recordmoblie = (Element) pg010.next();
                    //对象类型
                    String ObjectType = recordmoblie.elementTextTrim("PG010D01");
                    //对象标识
                    String ObjectIdentification = recordmoblie.elementTextTrim("PG010D02");
                    //标注及声明类型个数
                    String DeclarationTypeNumber = recordmoblie.elementTextTrim("PG010S01");

                    Iterator pg010h = recordmoblie.elementIterator("PG010H");
                    if (pg010h.hasNext()) {
                        while (pg010h.hasNext()) {
                            Element recordnn = (Element) pg010h.next();
                            //标注及声明类型
                            String DeclarationsType = recordnn.elementTextTrim("PG010D03");
                            //标注或声明内容
                            String Declarations = recordnn.elementTextTrim("PG010Q01");
                            //添加日期
                            String AddDate = recordnn.elementTextTrim("PG010R01");

                            OtherAnnotations otherAnnotations = new OtherAnnotations();
                            otherAnnotations.setObjectType(ObjectType);
                            otherAnnotations.setObjectIdentification(ObjectIdentification);
                            otherAnnotations.setDeclarationTypeNumber(DeclarationTypeNumber);
                            otherAnnotations.setDeclarationsType(DeclarationsType);
                            otherAnnotations.setDeclarations(Declarations);
                            otherAnnotations.setAddDate(AddDate);
                            if ("1".equals(ObjectType)) {
                                dissentingAnnotationcount++;
                                dissentingAnnotationsList.add(otherAnnotations);
                            } else if ("3".equals(ObjectType)) {
                                personalAnnotationcount++;
                                personalAnnotationsList.add(otherAnnotations);
                            }
                            otherAnnotationsList.add(otherAnnotations);
                        }
                    }
                }
                AnnotationsList annotationsLists = new AnnotationsList();
                annotationsLists.setDissentingAnnotationcount(String.valueOf(dissentingAnnotationcount));
                annotationsLists.setPersonalAnnotationcount(String.valueOf(personalAnnotationcount));
                annotationsLists.setPersonalAnnotationsLists(personalAnnotationsList);
                annotationsLists.setDissentingAnnotationsLists(dissentingAnnotationsList);
            }
            reportMessageData.setCrt2_p_otherannotations(otherAnnotationsList);

            //查询记录信息单元
            Element poq = rootElt.element("POQ");
            Iterator ph010 = poq.elementIterator("PH01");
            List<QueryRecord> queryRecordList = new ArrayList<QueryRecord>();
            if (ph010.hasNext()) {
                while (ph010.hasNext()) {
                    Element recordmoblie = (Element) ph010.next();
                    //查询日期
                    String QueryDate = recordmoblie.elementTextTrim("PH010R01");
                    //查询机构类型
                    String InquiringOrganizationType = recordmoblie.elementTextTrim("PH010D01");
                    //查询机构
                    String InquiringOrganization = recordmoblie.elementTextTrim("PH010Q02");
                    //查询原因
                    String InquiryCause = recordmoblie.elementTextTrim("PH010Q03");

                    QueryRecord queryRecord = new QueryRecord();
                    queryRecord.setQueryDate(QueryDate);
                    queryRecord.setInquiringOrganizationType(InquiringOrganizationType);
                    queryRecord.setInquiringOrganization(InquiringOrganization);
                    queryRecord.setInquiryCause(InquiryCause);

                    queryRecordList.add(queryRecord);
                }
            }
            reportMessageData.setCrt2_p_queryrecord(queryRecordList);


            return reportMessageData;
        } catch (Exception e) {
            throw new RuntimeException("解析查询记录信息异常:" + xmlReport, e);
        }
    }
}
