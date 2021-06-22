package cn.demo.rhv2model.domain.personal;

import java.io.Serializable;
import java.util.List;

/**  
* Title: ReportMessage  
* Description:生成json所用对象
* @author huangzhong 
* @date 2019年10月21日  
*/ 
public class ReportMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Identification crt2_p_identification;//身份信息
	
	private List<OtherDocuments> crt2_p_otherdocuments;//身份标识
	
	private List<Identity> crt2_p_identity;//身份信息
	
	private List<PhoneInfo> crt2_p_phone;//电话信息
	
	private List<Spouse> crt2_p_spouse;//婚姻信息
	
	private List<Residence> crt2_p_residence;//居住信息
	
	private List<Professional> crt2_p_professional;//职业信息
	
	private List<ScoreElements> crt2_p_score;//评分信息
	
	private List<CreditTransaction> crt2_p_credittransaction;//信贷交易提示信息
	
	private List<Recourse> crt2_p_recourse;//被追偿汇总信息
	
	private List<BadDebt> crt2_p_baddebt;//呆账汇总信息
	
	private List<BeOverDue> crt2_p_beoverdue;//逾期（透支）汇总信息;
	
	private List<AcyclicLoan> crt2_p_acyclicloan;//非循环贷款汇总信息
	
	private List<RevolvingLoan> crt2_p_revolvingloan;//循环额度下贷款汇总信息
	
	private List<RevolvingLoanAccount> crt2_p_revolvingloanaccount;//循环贷账户汇总信息
	
	private List<DebitCard> crt2_p_debitcard;//贷记卡账户汇总信息
	
	private List<SemiCreditCard> crt2_p_semicreditcard;//准贷记卡账户汇总信息
	
	private List<TotalRelatedRepayment> crt2_p_totalrelatedrepayment;//相关还款责任汇总信息
	
	private List<Arrears> crt2_p_arrears;//后付费业务类型数量
	
	private List<PublicInformation> crt2_p_publicinformation;//公共信息概要信息
	
	private List<LastQuery> crt2_p_lastquery;//查询记录概要信息
	
	private List<QueryRecordSummary> crt2_p_queryrecordsummary;//查询记录汇总信息
	
	private List<CreditAgreement> crt2_p_creditagreement;//授信协议信息
	
	private List<CreditDeclarativeInformation> crt2_p_creditdeclarativeinfo;//标注及声明信息
	
	private List<EssentialInformation> crt2_p_essentialinformation;//借贷账户信息
	
	private List<TheLatestPerformance> crt2_p_thelatestperformance;//最新表现信息
	
	private List<LastMonthly> crt2_p_lastmonthly;//近一次月度表现信息
	
	private List<LastTwentyfourMonth> crt2_p_lasttwentyfourmonth;//最近24个月还款记录信息
	
	private List<LastFiveYears> crt2_p_lastfiveyears;//最近 5 年内历史表现信息
	
	private List<SpecialTransaction> crt2_p_specialtransaction;//特殊交易信息
	
	private List<SpecialEvents> crt2_p_specialevents;//特殊事件说明信息
	
	private List<LargeAmountSpecial> crt2_p_largeamountspecial;//大额专项分期信息
	
	private List<DeclarativeInformation> crt2_p_declarativeinfo;//标注及声明信息
	
	private List<RelatedRepaymentLiabilities> crt2_p_relrepayliabilities;//相关还款责任信息
	
	private List<RelatedDeclarativeInformation> crt2_p_relateddeclarativeinfo;//标注及声明信息
	
	private List<PostPaymentBusiness> crt2_p_postpaymentbusiness;//后付费业务信息单元
	
	private List<PostPaidDeclarativeInformation> crt2_p_postpaiddeclarativeinfo;//标注及声明信息
	
	private List<TaxArrear> crt2_p_taxarrear;//欠税记录信息单元
	
	private List<TaxArrearDeclarativeInformation> crt2_p_taxarreardeclinfo;//标注及声明信息
	
	private List<CivilJudgement> crt2_p_civiljudgement;//民事判决记录信息单元
	
	private List<CivilJudgementDeclarativeInformation> crt2_p_civiljudgementdeclinfo;//标注及声明信息
	
	private List<ForceExecution> crt2_p_forceexecution;//强制执行记录信息单元
	
	private List<ForceExecutionDeclarativeInformation> crt2_p_forceexecutiondeclinfo;//标注及声明信息
	
	private List<AdminPunishment> crt2_p_adminpunishment;//行政处罚记录信息单元
	
	private List<AdminPunishmentDeclarativeInformation> crt2_p_adminpunishdeclinfor;//标注及声明信息
	
	private List<AccFund> crt2_p_accfund;//住房公积金参缴记录信息
	
	private List<AccfundDeclarativeInformation> crt2_p_accfunddeclinfor;//标注及声明信息
	
	private List<Salvation> crt2_p_salvation;//低保救助记录信息
	
	private List<SalvationDeclarativeInformation> crt2_p_salvationdeclinfo;//标注及声明信息
	
	private List<Competence> crt2_p_competence;//执业资格记录信息
	
	private List<CompetenceDeclarativeInformation> crt2_p_competencedeclinfo;//标注及声明信息
	
	private List<AdminAward> crt2_p_adminaward;//行政奖励记录信息
	
	private List<AdminAwardDeclarativeInformation> crt2_p_adminawarddeclinfo;//标注及声明信息
	
	private List<OtherAnnotations> crt2_p_otherannotations;//标注及声明信息单元(其他)
	
	private List<QueryRecord> crt2_p_queryrecord;//查询记录信息单元

	public Identification getCrt2_p_identification() {
		return crt2_p_identification;
	}

	public void setCrt2_p_identification(Identification crt2_p_identification) {
		this.crt2_p_identification = crt2_p_identification;
	}

	public List<OtherDocuments> getCrt2_p_otherdocuments() {
		return crt2_p_otherdocuments;
	}

	public void setCrt2_p_otherdocuments(List<OtherDocuments> crt2_p_otherdocuments) {
		this.crt2_p_otherdocuments = crt2_p_otherdocuments;
	}

	public List<Identity> getCrt2_p_identity() {
		return crt2_p_identity;
	}

	public void setCrt2_p_identity(List<Identity> crt2_p_identity) {
		this.crt2_p_identity = crt2_p_identity;
	}

	public List<PhoneInfo> getCrt2_p_phone() {
		return crt2_p_phone;
	}

	public void setCrt2_p_phone(List<PhoneInfo> crt2_p_phone) {
		this.crt2_p_phone = crt2_p_phone;
	}

	public List<Spouse> getCrt2_p_spouse() {
		return crt2_p_spouse;
	}

	public void setCrt2_p_spouse(List<Spouse> crt2_p_spouse) {
		this.crt2_p_spouse = crt2_p_spouse;
	}

	public List<Residence> getCrt2_p_residence() {
		return crt2_p_residence;
	}

	public void setCrt2_p_residence(List<Residence> crt2_p_residence) {
		this.crt2_p_residence = crt2_p_residence;
	}

	public List<Professional> getCrt2_p_professional() {
		return crt2_p_professional;
	}

	public void setCrt2_p_professional(List<Professional> crt2_p_professional) {
		this.crt2_p_professional = crt2_p_professional;
	}

	public List<ScoreElements> getCrt2_p_score() {
		return crt2_p_score;
	}

	public void setCrt2_p_score(List<ScoreElements> crt2_p_score) {
		this.crt2_p_score = crt2_p_score;
	}

	public List<CreditTransaction> getCrt2_p_credittransaction() {
		return crt2_p_credittransaction;
	}

	public void setCrt2_p_credittransaction(List<CreditTransaction> crt2_p_credittransaction) {
		this.crt2_p_credittransaction = crt2_p_credittransaction;
	}

	public List<Recourse> getCrt2_p_recourse() {
		return crt2_p_recourse;
	}

	public void setCrt2_p_recourse(List<Recourse> crt2_p_recourse) {
		this.crt2_p_recourse = crt2_p_recourse;
	}

	public List<BadDebt> getCrt2_p_baddebt() {
		return crt2_p_baddebt;
	}

	public void setCrt2_p_baddebt(List<BadDebt> crt2_p_baddebt) {
		this.crt2_p_baddebt = crt2_p_baddebt;
	}

	public List<BeOverDue> getCrt2_p_beoverdue() {
		return crt2_p_beoverdue;
	}

	public void setCrt2_p_beoverdue(List<BeOverDue> crt2_p_beoverdue) {
		this.crt2_p_beoverdue = crt2_p_beoverdue;
	}

	public List<AcyclicLoan> getCrt2_p_acyclicloan() {
		return crt2_p_acyclicloan;
	}

	public void setCrt2_p_acyclicloan(List<AcyclicLoan> crt2_p_acyclicloan) {
		this.crt2_p_acyclicloan = crt2_p_acyclicloan;
	}

	public List<RevolvingLoan> getCrt2_p_revolvingloan() {
		return crt2_p_revolvingloan;
	}

	public void setCrt2_p_revolvingloan(List<RevolvingLoan> crt2_p_revolvingloan) {
		this.crt2_p_revolvingloan = crt2_p_revolvingloan;
	}

	public List<RevolvingLoanAccount> getCrt2_p_revolvingloanaccount() {
		return crt2_p_revolvingloanaccount;
	}

	public void setCrt2_p_revolvingloanaccount(List<RevolvingLoanAccount> crt2_p_revolvingloanaccount) {
		this.crt2_p_revolvingloanaccount = crt2_p_revolvingloanaccount;
	}

	public List<DebitCard> getCrt2_p_debitcard() {
		return crt2_p_debitcard;
	}

	public void setCrt2_p_debitcard(List<DebitCard> crt2_p_debitcard) {
		this.crt2_p_debitcard = crt2_p_debitcard;
	}

	public List<SemiCreditCard> getCrt2_p_semicreditcard() {
		return crt2_p_semicreditcard;
	}

	public void setCrt2_p_semicreditcard(List<SemiCreditCard> crt2_p_semicreditcard) {
		this.crt2_p_semicreditcard = crt2_p_semicreditcard;
	}

	public List<TotalRelatedRepayment> getCrt2_p_totalrelatedrepayment() {
		return crt2_p_totalrelatedrepayment;
	}

	public void setCrt2_p_totalrelatedrepayment(List<TotalRelatedRepayment> crt2_p_totalrelatedrepayment) {
		this.crt2_p_totalrelatedrepayment = crt2_p_totalrelatedrepayment;
	}

	public List<Arrears> getCrt2_p_arrears() {
		return crt2_p_arrears;
	}

	public void setCrt2_p_arrears(List<Arrears> crt2_p_arrears) {
		this.crt2_p_arrears = crt2_p_arrears;
	}

	public List<PublicInformation> getCrt2_p_publicinformation() {
		return crt2_p_publicinformation;
	}

	public void setCrt2_p_publicinformation(List<PublicInformation> crt2_p_publicinformation) {
		this.crt2_p_publicinformation = crt2_p_publicinformation;
	}

	public List<LastQuery> getCrt2_p_lastquery() {
		return crt2_p_lastquery;
	}

	public void setCrt2_p_lastquery(List<LastQuery> crt2_p_lastquery) {
		this.crt2_p_lastquery = crt2_p_lastquery;
	}

	public List<QueryRecordSummary> getCrt2_p_queryrecordsummary() {
		return crt2_p_queryrecordsummary;
	}

	public void setCrt2_p_queryrecordsummary(List<QueryRecordSummary> crt2_p_queryrecordsummary) {
		this.crt2_p_queryrecordsummary = crt2_p_queryrecordsummary;
	}

	public List<CreditAgreement> getCrt2_p_creditagreement() {
		return crt2_p_creditagreement;
	}

	public void setCrt2_p_creditagreement(List<CreditAgreement> crt2_p_creditagreement) {
		this.crt2_p_creditagreement = crt2_p_creditagreement;
	}

	public List<CreditDeclarativeInformation> getCrt2_p_creditdeclarativeinfo() {
		return crt2_p_creditdeclarativeinfo;
	}

	public void setCrt2_p_creditdeclarativeinfo(List<CreditDeclarativeInformation> crt2_p_creditdeclarativeinfo) {
		this.crt2_p_creditdeclarativeinfo = crt2_p_creditdeclarativeinfo;
	}

	public List<EssentialInformation> getCrt2_p_essentialinformation() {
		return crt2_p_essentialinformation;
	}

	public void setCrt2_p_essentialinformation(List<EssentialInformation> crt2_p_essentialinformation) {
		this.crt2_p_essentialinformation = crt2_p_essentialinformation;
	}

	public List<TheLatestPerformance> getCrt2_p_thelatestperformance() {
		return crt2_p_thelatestperformance;
	}

	public void setCrt2_p_thelatestperformance(List<TheLatestPerformance> crt2_p_thelatestperformance) {
		this.crt2_p_thelatestperformance = crt2_p_thelatestperformance;
	}

	public List<LastMonthly> getCrt2_p_lastmonthly() {
		return crt2_p_lastmonthly;
	}

	public void setCrt2_p_lastmonthly(List<LastMonthly> crt2_p_lastmonthly) {
		this.crt2_p_lastmonthly = crt2_p_lastmonthly;
	}

	public List<LastTwentyfourMonth> getCrt2_p_lasttwentyfourmonth() {
		return crt2_p_lasttwentyfourmonth;
	}

	public void setCrt2_p_lasttwentyfourmonth(List<LastTwentyfourMonth> crt2_p_lasttwentyfourmonth) {
		this.crt2_p_lasttwentyfourmonth = crt2_p_lasttwentyfourmonth;
	}

	public List<LastFiveYears> getCrt2_p_lastfiveyears() {
		return crt2_p_lastfiveyears;
	}

	public void setCrt2_p_lastfiveyears(List<LastFiveYears> crt2_p_lastfiveyears) {
		this.crt2_p_lastfiveyears = crt2_p_lastfiveyears;
	}

	public List<SpecialTransaction> getCrt2_p_specialtransaction() {
		return crt2_p_specialtransaction;
	}

	public void setCrt2_p_specialtransaction(List<SpecialTransaction> crt2_p_specialtransaction) {
		this.crt2_p_specialtransaction = crt2_p_specialtransaction;
	}

	public List<SpecialEvents> getCrt2_p_specialevents() {
		return crt2_p_specialevents;
	}

	public void setCrt2_p_specialevents(List<SpecialEvents> crt2_p_specialevents) {
		this.crt2_p_specialevents = crt2_p_specialevents;
	}

	public List<LargeAmountSpecial> getCrt2_p_largeamountspecial() {
		return crt2_p_largeamountspecial;
	}

	public void setCrt2_p_largeamountspecial(List<LargeAmountSpecial> crt2_p_largeamountspecial) {
		this.crt2_p_largeamountspecial = crt2_p_largeamountspecial;
	}

	public List<DeclarativeInformation> getCrt2_p_declarativeinfo() {
		return crt2_p_declarativeinfo;
	}

	public void setCrt2_p_declarativeinfo(List<DeclarativeInformation> crt2_p_declarativeinfo) {
		this.crt2_p_declarativeinfo = crt2_p_declarativeinfo;
	}

	public List<RelatedRepaymentLiabilities> getCrt2_p_relrepayliabilities() {
		return crt2_p_relrepayliabilities;
	}

	public void setCrt2_p_relrepayliabilities(List<RelatedRepaymentLiabilities> crt2_p_relrepayliabilities) {
		this.crt2_p_relrepayliabilities = crt2_p_relrepayliabilities;
	}

	public List<RelatedDeclarativeInformation> getCrt2_p_relateddeclarativeinfo() {
		return crt2_p_relateddeclarativeinfo;
	}

	public void setCrt2_p_relateddeclarativeinfo(List<RelatedDeclarativeInformation> crt2_p_relateddeclarativeinfo) {
		this.crt2_p_relateddeclarativeinfo = crt2_p_relateddeclarativeinfo;
	}

	public List<PostPaymentBusiness> getCrt2_p_postpaymentbusiness() {
		return crt2_p_postpaymentbusiness;
	}

	public void setCrt2_p_postpaymentbusiness(List<PostPaymentBusiness> crt2_p_postpaymentbusiness) {
		this.crt2_p_postpaymentbusiness = crt2_p_postpaymentbusiness;
	}

	public List<PostPaidDeclarativeInformation> getCrt2_p_postpaiddeclarativeinfo() {
		return crt2_p_postpaiddeclarativeinfo;
	}

	public void setCrt2_p_postpaiddeclarativeinfo(List<PostPaidDeclarativeInformation> crt2_p_postpaiddeclarativeinfo) {
		this.crt2_p_postpaiddeclarativeinfo = crt2_p_postpaiddeclarativeinfo;
	}

	public List<TaxArrear> getCrt2_p_taxarrear() {
		return crt2_p_taxarrear;
	}

	public void setCrt2_p_taxarrear(List<TaxArrear> crt2_p_taxarrear) {
		this.crt2_p_taxarrear = crt2_p_taxarrear;
	}

	public List<TaxArrearDeclarativeInformation> getCrt2_p_taxarreardeclinfo() {
		return crt2_p_taxarreardeclinfo;
	}

	public void setCrt2_p_taxarreardeclinfo(List<TaxArrearDeclarativeInformation> crt2_p_taxarreardeclinfo) {
		this.crt2_p_taxarreardeclinfo = crt2_p_taxarreardeclinfo;
	}

	public List<CivilJudgement> getCrt2_p_civiljudgement() {
		return crt2_p_civiljudgement;
	}

	public void setCrt2_p_civiljudgement(List<CivilJudgement> crt2_p_civiljudgement) {
		this.crt2_p_civiljudgement = crt2_p_civiljudgement;
	}

	public List<CivilJudgementDeclarativeInformation> getCrt2_p_civiljudgementdeclinfo() {
		return crt2_p_civiljudgementdeclinfo;
	}

	public void setCrt2_p_civiljudgementdeclinfo(List<CivilJudgementDeclarativeInformation> crt2_p_civiljudgementdeclinfo) {
		this.crt2_p_civiljudgementdeclinfo = crt2_p_civiljudgementdeclinfo;
	}

	public List<ForceExecution> getCrt2_p_forceexecution() {
		return crt2_p_forceexecution;
	}

	public void setCrt2_p_forceexecution(List<ForceExecution> crt2_p_forceexecution) {
		this.crt2_p_forceexecution = crt2_p_forceexecution;
	}

	public List<ForceExecutionDeclarativeInformation> getCrt2_p_forceexecutiondeclinfo() {
		return crt2_p_forceexecutiondeclinfo;
	}

	public void setCrt2_p_forceexecutiondeclinfo(List<ForceExecutionDeclarativeInformation> crt2_p_forceexecutiondeclinfo) {
		this.crt2_p_forceexecutiondeclinfo = crt2_p_forceexecutiondeclinfo;
	}

	public List<AdminPunishment> getCrt2_p_adminpunishment() {
		return crt2_p_adminpunishment;
	}

	public void setCrt2_p_adminpunishment(List<AdminPunishment> crt2_p_adminpunishment) {
		this.crt2_p_adminpunishment = crt2_p_adminpunishment;
	}

	public List<AdminPunishmentDeclarativeInformation> getCrt2_p_adminpunishdeclinfor() {
		return crt2_p_adminpunishdeclinfor;
	}

	public void setCrt2_p_adminpunishdeclinfor(List<AdminPunishmentDeclarativeInformation> crt2_p_adminpunishdeclinfor) {
		this.crt2_p_adminpunishdeclinfor = crt2_p_adminpunishdeclinfor;
	}

	public List<AccFund> getCrt2_p_accfund() {
		return crt2_p_accfund;
	}

	public void setCrt2_p_accfund(List<AccFund> crt2_p_accfund) {
		this.crt2_p_accfund = crt2_p_accfund;
	}

	public List<AccfundDeclarativeInformation> getCrt2_p_accfunddeclinfor() {
		return crt2_p_accfunddeclinfor;
	}

	public void setCrt2_p_accfunddeclinfor(List<AccfundDeclarativeInformation> crt2_p_accfunddeclinfor) {
		this.crt2_p_accfunddeclinfor = crt2_p_accfunddeclinfor;
	}

	public List<Salvation> getCrt2_p_salvation() {
		return crt2_p_salvation;
	}

	public void setCrt2_p_salvation(List<Salvation> crt2_p_salvation) {
		this.crt2_p_salvation = crt2_p_salvation;
	}

	public List<SalvationDeclarativeInformation> getCrt2_p_salvationdeclinfo() {
		return crt2_p_salvationdeclinfo;
	}

	public void setCrt2_p_salvationdeclinfo(List<SalvationDeclarativeInformation> crt2_p_salvationdeclinfo) {
		this.crt2_p_salvationdeclinfo = crt2_p_salvationdeclinfo;
	}

	public List<Competence> getCrt2_p_competence() {
		return crt2_p_competence;
	}

	public void setCrt2_p_competence(List<Competence> crt2_p_competence) {
		this.crt2_p_competence = crt2_p_competence;
	}

	public List<CompetenceDeclarativeInformation> getCrt2_p_competencedeclinfo() {
		return crt2_p_competencedeclinfo;
	}

	public void setCrt2_p_competencedeclinfo(List<CompetenceDeclarativeInformation> crt2_p_competencedeclinfo) {
		this.crt2_p_competencedeclinfo = crt2_p_competencedeclinfo;
	}

	public List<AdminAward> getCrt2_p_adminaward() {
		return crt2_p_adminaward;
	}

	public void setCrt2_p_adminaward(List<AdminAward> crt2_p_adminaward) {
		this.crt2_p_adminaward = crt2_p_adminaward;
	}

	public List<AdminAwardDeclarativeInformation> getCrt2_p_adminawarddeclinfo() {
		return crt2_p_adminawarddeclinfo;
	}

	public void setCrt2_p_adminawarddeclinfo(List<AdminAwardDeclarativeInformation> crt2_p_adminawarddeclinfo) {
		this.crt2_p_adminawarddeclinfo = crt2_p_adminawarddeclinfo;
	}

	public List<OtherAnnotations> getCrt2_p_otherannotations() {
		return crt2_p_otherannotations;
	}

	public void setCrt2_p_otherannotations(List<OtherAnnotations> crt2_p_otherannotations) {
		this.crt2_p_otherannotations = crt2_p_otherannotations;
	}

	public List<QueryRecord> getCrt2_p_queryrecord() {
		return crt2_p_queryrecord;
	}

	public void setCrt2_p_queryrecord(List<QueryRecord> crt2_p_queryrecord) {
		this.crt2_p_queryrecord = crt2_p_queryrecord;
	}
}
