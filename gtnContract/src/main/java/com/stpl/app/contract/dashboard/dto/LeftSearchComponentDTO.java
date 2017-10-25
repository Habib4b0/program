package com.stpl.app.contract.dashboard.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.stpl.ifs.util.HelperDTO;

/**
 * Search DTO object for Dashboard Component search
 * @author shrihariharan
 *
 */
public class LeftSearchComponentDTO {

	
	private String leftContractId=StringUtils.EMPTY;
	private String leftContractNo=StringUtils.EMPTY;
	private String leftContractName=StringUtils.EMPTY;
	private HelperDTO leftContractType;	
	private Date leftContractStartDate;
	private Date leftContractEndDate;
	private String leftContractHolderNo=StringUtils.EMPTY;
	private String leftContractHolderName=StringUtils.EMPTY;
	private String leftCfpId=StringUtils.EMPTY;
	private String leftCfpNo=StringUtils.EMPTY;
	private String leftCfpName=StringUtils.EMPTY;
	private HelperDTO leftCfpType;
	private String leftCompanyNo=StringUtils.EMPTY;
	private String leftCompanyName=StringUtils.EMPTY;
	private HelperDTO leftCompanyType;
	private String leftCompanyCategory=StringUtils.EMPTY;
	private String leftIfpId=StringUtils.EMPTY;
	private String leftIfpNo=StringUtils.EMPTY;
	private String leftIfpName=StringUtils.EMPTY;
	private HelperDTO leftIfpType;
	private String leftIfpItemNo=StringUtils.EMPTY;
	private String leftIfpItemName=StringUtils.EMPTY;
	private HelperDTO leftIfpTherapeuticClass;
	private HelperDTO leftIfpBrandName;
	private String leftPsId=StringUtils.EMPTY;
	private String leftPsNo=StringUtils.EMPTY;
	private String leftPsName=StringUtils.EMPTY;
	private HelperDTO leftPsType;
	private String leftPsItemNo=StringUtils.EMPTY;
	private String leftPsItemName=StringUtils.EMPTY;
	private HelperDTO leftPsTherapeuticClass;
	private HelperDTO leftPsBrandName;
	private String leftRsId=StringUtils.EMPTY;
	private String leftRsNo=StringUtils.EMPTY;
	private String leftRsName=StringUtils.EMPTY;
	private HelperDTO leftRsType;
	private String leftRsItemNo=StringUtils.EMPTY;
	private String leftRsItemName=StringUtils.EMPTY;
	private HelperDTO leftRsProgramCategory;
	private HelperDTO leftRsBrandName;
	
	
	public String getLeftContractId() {
		return leftContractId;
	}
	public void setLeftContractId(String leftContractId) {
		this.leftContractId = leftContractId;
	}
	public String getLeftContractNo() {
		return leftContractNo;
	}
	public void setLeftContractNo(String leftContractNo) {
		this.leftContractNo = leftContractNo;
	}
	public String getLeftContractName() {
		return leftContractName;
	}
	public void setLeftContractName(String leftContractName) {
		this.leftContractName = leftContractName;
	}
	public HelperDTO getLeftContractType() {
		return leftContractType;
	}
	public void setLeftContractType(HelperDTO leftContractType) {
		this.leftContractType = leftContractType;
	}
	public Date getLeftContractStartDate() {
		return leftContractStartDate;
	}
	public void setLeftContractStartDate(Date leftContractStartDate) {
		this.leftContractStartDate = leftContractStartDate;
	}
	public Date getLeftContractEndDate() {
		return leftContractEndDate;
	}
	public void setLeftContractEndDate(Date leftContractEndDate) {
		this.leftContractEndDate = leftContractEndDate;
	}
	public String getLeftContractHolderNo() {
		return leftContractHolderNo;
	}
	public void setLeftContractHolderNo(String leftContractHolderNo) {
		this.leftContractHolderNo = leftContractHolderNo;
	}
	public String getLeftContractHolderName() {
		return leftContractHolderName;
	}
	public void setLeftContractHolderName(String leftContractHolderName) {
		this.leftContractHolderName = leftContractHolderName;
	}
	public String getLeftCfpId() {
		return leftCfpId;
	}
	public void setLeftCfpId(String leftCfpId) {
		this.leftCfpId = leftCfpId;
	}
	public String getLeftCfpNo() {
		return leftCfpNo;
	}
	public void setLeftCfpNo(String leftCfpNo) {
		this.leftCfpNo = leftCfpNo;
	}
	public String getLeftCfpName() {
		return leftCfpName;
	}
	public void setLeftCfpName(String leftCfpName) {
		this.leftCfpName = leftCfpName;
	}
	public HelperDTO getLeftCfpType() {
		return leftCfpType;
	}
	public void setLeftCfpType(HelperDTO leftCfpType) {
		this.leftCfpType = leftCfpType;
	}
	public String getLeftCompanyNo() {
		return leftCompanyNo;
	}
	public void setLeftCompanyNo(String leftCompanyNo) {
		this.leftCompanyNo = leftCompanyNo;
	}
	public String getLeftCompanyName() {
		return leftCompanyName;
	}
	public void setLeftCompanyName(String leftCompanyName) {
		this.leftCompanyName = leftCompanyName;
	}
	public HelperDTO getLeftCompanyType() {
		return leftCompanyType;
	}
	public void setLeftCompanyType(HelperDTO leftCompanyType) {
		this.leftCompanyType = leftCompanyType;
	}
	public String getLeftCompanyCategory() {
		return leftCompanyCategory;
	}
	public void setLeftCompanyCategory(String leftCompanyCategory) {
		this.leftCompanyCategory = leftCompanyCategory;
	}
	public String getLeftIfpId() {
		return leftIfpId;
	}
	public void setLeftIfpId(String leftIfpId) {
		this.leftIfpId = leftIfpId;
	}
	public String getLeftIfpNo() {
		return leftIfpNo;
	}
	public void setLeftIfpNo(String leftIfpNo) {
		this.leftIfpNo = leftIfpNo;
	}
	public String getLeftIfpName() {
		return leftIfpName;
	}
	public void setLeftIfpName(String leftIfpName) {
		this.leftIfpName = leftIfpName;
	}
	public HelperDTO getLeftIfpType() {
		return leftIfpType;
	}
	public void setLeftIfpType(HelperDTO leftIfpType) {
		this.leftIfpType = leftIfpType;
	}
	public String getLeftIfpItemNo() {
		return leftIfpItemNo;
	}
	public void setLeftIfpItemNo(String leftIfpItemNo) {
		this.leftIfpItemNo = leftIfpItemNo;
	}
	public String getLeftIfpItemName() {
		return leftIfpItemName;
	}
	public void setLeftIfpItemName(String leftIfpItemName) {
		this.leftIfpItemName = leftIfpItemName;
	}
	public HelperDTO getLeftIfpTherapeuticClass() {
		return leftIfpTherapeuticClass;
	}
	public void setLeftIfpTherapeuticClass(HelperDTO leftIfpTherapeuticClass) {
		this.leftIfpTherapeuticClass = leftIfpTherapeuticClass;
	}
	public HelperDTO getLeftIfpBrandName() {
		return leftIfpBrandName;
	}
	public void setLeftIfpBrandName(HelperDTO leftIfpBrandName) {
		this.leftIfpBrandName = leftIfpBrandName;
	}
	public String getLeftPsId() {
		return leftPsId;
	}
	public void setLeftPsId(String leftPsId) {
		this.leftPsId = leftPsId;
	}
	public String getLeftPsNo() {
		return leftPsNo;
	}
	public void setLeftPsNo(String leftPsNo) {
		this.leftPsNo = leftPsNo;
	}
	public String getLeftPsName() {
		return leftPsName;
	}
	public void setLeftPsName(String leftPsName) {
		this.leftPsName = leftPsName;
	}
	public HelperDTO getLeftPsType() {
		return leftPsType;
	}
	public void setLeftPsType(HelperDTO leftPsType) {
		this.leftPsType = leftPsType;
	}
	public String getLeftPsItemNo() {
		return leftPsItemNo;
	}
	public void setLeftPsItemNo(String leftPsItemNo) {
		this.leftPsItemNo = leftPsItemNo;
	}
	public String getLeftPsItemName() {
		return leftPsItemName;
	}
	public void setLeftPsItemName(String leftPsItemName) {
		this.leftPsItemName = leftPsItemName;
	}
	public HelperDTO getLeftPsTherapeuticClass() {
		return leftPsTherapeuticClass;
	}
	public void setLeftPsTherapeuticClass(HelperDTO leftPsTherapeuticClass) {
		this.leftPsTherapeuticClass = leftPsTherapeuticClass;
	}
	public HelperDTO getLeftPsBrandName() {
		return leftPsBrandName;
	}
	public void setLeftPsBrandName(HelperDTO leftPsBrandName) {
		this.leftPsBrandName = leftPsBrandName;
	}
	public String getLeftRsId() {
		return leftRsId;
	}
	public void setLeftRsId(String leftRsId) {
		this.leftRsId = leftRsId;
	}
	public String getLeftRsNo() {
		return leftRsNo;
	}
	public void setLeftRsNo(String leftRsNo) {
		this.leftRsNo = leftRsNo;
	}
	public String getLeftRsName() {
		return leftRsName;
	}
	public void setLeftRsName(String leftRsName) {
		this.leftRsName = leftRsName;
	}
	public HelperDTO getLeftRsType() {
		return leftRsType;
	}
	public void setLeftRsType(HelperDTO leftRsType) {
		this.leftRsType = leftRsType;
	}
	public String getLeftRsItemNo() {
		return leftRsItemNo;
	}
	public void setLeftRsItemNo(String leftRsItemNo) {
		this.leftRsItemNo = leftRsItemNo;
	}
	public String getLeftRsItemName() {
		return leftRsItemName;
	}
	public void setLeftRsItemName(String leftRsItemName) {
		this.leftRsItemName = leftRsItemName;
	}
	public HelperDTO getLeftRsProgramCategory() {
		return leftRsProgramCategory;
	}
	public void setLeftRsProgramCategory(HelperDTO leftRsProgramCategory) {
		this.leftRsProgramCategory = leftRsProgramCategory;
	}
	public HelperDTO getLeftRsBrandName() {
		return leftRsBrandName;
	}
	public void setLeftRsBrandName(HelperDTO leftRsBrandName) {
		this.leftRsBrandName = leftRsBrandName;
	}
	
			
}
