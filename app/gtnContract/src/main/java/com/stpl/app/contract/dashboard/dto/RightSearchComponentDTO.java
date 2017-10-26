/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shrihariharan
 */
public class RightSearchComponentDTO {
        private String rightContractId=StringUtils.EMPTY;
	private String rightContractNo=StringUtils.EMPTY;
	private String rightContractName=StringUtils.EMPTY;
	private HelperDTO rightContractType;	
	private Date rightContractStartDate;
	private Date rightContractEndDate;
	private String rightContractHolderNo=StringUtils.EMPTY;
	private String rightContractHolderName=StringUtils.EMPTY;
	private String rightCfpId=StringUtils.EMPTY;
	private String rightCfpNo=StringUtils.EMPTY;
	private String rightCfpName=StringUtils.EMPTY;
	private HelperDTO rightCfpType;
        private String rightCompanyId=StringUtils.EMPTY;
	private String rightCompanyNo=StringUtils.EMPTY;
	private String rightCompanyName=StringUtils.EMPTY;
	private HelperDTO rightCompanyType;
	private String rightCompanyCategory=StringUtils.EMPTY;
	private String rightIfpId=StringUtils.EMPTY;
	private String rightIfpNo=StringUtils.EMPTY;
	private String rightIfpName=StringUtils.EMPTY;
	private HelperDTO rightIfpType;
        private String rightIfpItemId=StringUtils.EMPTY;
	private String rightIfpItemNo=StringUtils.EMPTY;
	private String rightIfpItemName=StringUtils.EMPTY;
	private HelperDTO rightIfpTherapeuticClass;
	private HelperDTO rightIfpBrandName;
	private String rightPsId=StringUtils.EMPTY;
	private String rightPsNo=StringUtils.EMPTY;
	private String rightPsName=StringUtils.EMPTY;
	private HelperDTO rightPsType;
	private String rightPsItemNo=StringUtils.EMPTY;
	private String rightPsItemName=StringUtils.EMPTY;
	private HelperDTO rightPsTherapeuticClass;
	private HelperDTO rightPsBrandName;
	private String rightRsId=StringUtils.EMPTY;
	private String rightRsNo=StringUtils.EMPTY;
	private String rightRsName=StringUtils.EMPTY;
	private HelperDTO rightRsType;
	private String rightRsItemNo=StringUtils.EMPTY;
	private String rightRsItemName=StringUtils.EMPTY;
	private HelperDTO rightRsProgramCategory;
	private HelperDTO rightRsBrandName;

        public String getRightCompanyId() {
            return rightCompanyId;
        }

        public void setRightCompanyId(String rightCompanyId) {
            this.rightCompanyId = rightCompanyId;
        }

        public String getRightIfpItemId() {
            return rightIfpItemId;
        }

        public void setRightIfpItemId(String rightIfpItemId) {
            this.rightIfpItemId = rightIfpItemId;
        }
	
        public String getRightContractId() {
		return rightContractId;
	}
	public void setRightContractId(String rightContractId) {
		this.rightContractId = rightContractId;
	}
	public String getRightContractNo() {
		return rightContractNo;
	}
	public void setRightContractNo(String rightContractNo) {
		this.rightContractNo = rightContractNo;
	}
	public String getRightContractName() {
		return rightContractName;
	}
	public void setRightContractName(String rightContractName) {
		this.rightContractName = rightContractName;
	}
	public HelperDTO getRightContractType() {
		return rightContractType;
	}
	public void setRightContractType(HelperDTO rightContractType) {
		this.rightContractType = rightContractType;
	}
	public Date getRightContractStartDate() {
		return rightContractStartDate;
	}
	public void setRightContractStartDate(Date rightContractStartDate) {
		this.rightContractStartDate = rightContractStartDate;
	}
	public Date getRightContractEndDate() {
		return rightContractEndDate;
	}
	public void setRightContractEndDate(Date rightContractEndDate) {
		this.rightContractEndDate = rightContractEndDate;
	}
	public String getRightContractHolderNo() {
		return rightContractHolderNo;
	}
	public void setRightContractHolderNo(String rightContractHolderNo) {
		this.rightContractHolderNo = rightContractHolderNo;
	}
	public String getRightContractHolderName() {
		return rightContractHolderName;
	}
	public void setRightContractHolderName(String rightContractHolderName) {
		this.rightContractHolderName = rightContractHolderName;
	}
	public String getRightCfpId() {
		return rightCfpId;
	}
	public void setRightCfpId(String rightCfpId) {
		this.rightCfpId = rightCfpId;
	}
	public String getRightCfpNo() {
		return rightCfpNo;
	}
	public void setRightCfpNo(String rightCfpNo) {
		this.rightCfpNo = rightCfpNo;
	}
	public String getRightCfpName() {
		return rightCfpName;
	}
	public void setRightCfpName(String rightCfpName) {
		this.rightCfpName = rightCfpName;
	}
	public HelperDTO getRightCfpType() {
		return rightCfpType;
	}
	public void setRightCfpType(HelperDTO rightCfpType) {
		this.rightCfpType = rightCfpType;
	}
	public String getRightCompanyNo() {
		return rightCompanyNo;
	}
	public void setRightCompanyNo(String rightCompanyNo) {
		this.rightCompanyNo = rightCompanyNo;
	}
	public String getRightCompanyName() {
		return rightCompanyName;
	}
	public void setRightCompanyName(String rightCompanyName) {
		this.rightCompanyName = rightCompanyName;
	}
	public HelperDTO getRightCompanyType() {
		return rightCompanyType;
	}
	public void setRightCompanyType(HelperDTO rightCompanyType) {
		this.rightCompanyType = rightCompanyType;
	}
	public String getRightCompanyCategory() {
		return rightCompanyCategory;
	}
	public void setRightCompanyCategory(String rightCompanyCategory) {
		this.rightCompanyCategory = rightCompanyCategory;
	}
	public String getRightIfpId() {
		return rightIfpId;
	}
	public void setRightIfpId(String rightIfpId) {
		this.rightIfpId = rightIfpId;
	}
	public String getRightIfpNo() {
		return rightIfpNo;
	}
	public void setRightIfpNo(String rightIfpNo) {
		this.rightIfpNo = rightIfpNo;
	}
	public String getRightIfpName() {
		return rightIfpName;
	}
	public void setRightIfpName(String rightIfpName) {
		this.rightIfpName = rightIfpName;
	}
	public HelperDTO getRightIfpType() {
		return rightIfpType;
	}
	public void setRightIfpType(HelperDTO rightIfpType) {
		this.rightIfpType = rightIfpType;
	}
	public String getRightIfpItemNo() {
		return rightIfpItemNo;
	}
	public void setRightIfpItemNo(String rightIfpItemNo) {
		this.rightIfpItemNo = rightIfpItemNo;
	}
	public String getRightIfpItemName() {
		return rightIfpItemName;
	}
	public void setRightIfpItemName(String rightIfpItemName) {
		this.rightIfpItemName = rightIfpItemName;
	}
	public HelperDTO getRightIfpTherapeuticClass() {
		return rightIfpTherapeuticClass;
	}
	public void setRightIfpTherapeuticClass(HelperDTO rightIfpTherapeuticClass) {
		this.rightIfpTherapeuticClass = rightIfpTherapeuticClass;
	}
	public HelperDTO getRightIfpBrandName() {
		return rightIfpBrandName;
	}
	public void setRightIfpBrandName(HelperDTO rightIfpBrandName) {
		this.rightIfpBrandName = rightIfpBrandName;
	}
	public String getRightPsId() {
		return rightPsId;
	}
	public void setRightPsId(String rightPsId) {
		this.rightPsId = rightPsId;
	}
	public String getRightPsNo() {
		return rightPsNo;
	}
	public void setRightPsNo(String rightPsNo) {
		this.rightPsNo = rightPsNo;
	}
	public String getRightPsName() {
		return rightPsName;
	}
	public void setRightPsName(String rightPsName) {
		this.rightPsName = rightPsName;
	}
	public HelperDTO getRightPsType() {
		return rightPsType;
	}
	public void setRightPsType(HelperDTO rightPsType) {
		this.rightPsType = rightPsType;
	}
	public String getRightPsItemNo() {
		return rightPsItemNo;
	}
	public void setRightPsItemNo(String rightPsItemNo) {
		this.rightPsItemNo = rightPsItemNo;
	}
	public String getRightPsItemName() {
		return rightPsItemName;
	}
	public void setRightPsItemName(String rightPsItemName) {
		this.rightPsItemName = rightPsItemName;
	}
	public HelperDTO getRightPsTherapeuticClass() {
		return rightPsTherapeuticClass;
	}
	public void setRightPsTherapeuticClass(HelperDTO rightPsTherapeuticClass) {
		this.rightPsTherapeuticClass = rightPsTherapeuticClass;
	}
	public HelperDTO getRightPsBrandName() {
		return rightPsBrandName;
	}
	public void setRightPsBrandName(HelperDTO rightPsBrandName) {
		this.rightPsBrandName = rightPsBrandName;
	}
	public String getRightRsId() {
		return rightRsId;
	}
	public void setRightRsId(String rightRsId) {
		this.rightRsId = rightRsId;
	}
	public String getRightRsNo() {
		return rightRsNo;
	}
	public void setRightRsNo(String rightRsNo) {
		this.rightRsNo = rightRsNo;
	}
	public String getRightRsName() {
		return rightRsName;
	}
	public void setRightRsName(String rightRsName) {
		this.rightRsName = rightRsName;
	}
	public HelperDTO getRightRsType() {
		return rightRsType;
	}
	public void setRightRsType(HelperDTO rightRsType) {
		this.rightRsType = rightRsType;
	}
	public String getRightRsItemNo() {
		return rightRsItemNo;
	}
	public void setRightRsItemNo(String rightRsItemNo) {
		this.rightRsItemNo = rightRsItemNo;
	}
	public String getRightRsItemName() {
		return rightRsItemName;
	}
	public void setRightRsItemName(String rightRsItemName) {
		this.rightRsItemName = rightRsItemName;
	}
	public HelperDTO getRightRsProgramCategory() {
		return rightRsProgramCategory;
	}
	public void setRightRsProgramCategory(HelperDTO rightRsProgramCategory) {
		this.rightRsProgramCategory = rightRsProgramCategory;
	}
	public HelperDTO getRightRsBrandName() {
		return rightRsBrandName;
	}
	public void setRightRsBrandName(HelperDTO rightRsBrandName) {
		this.rightRsBrandName = rightRsBrandName;
	}
	
}
