/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed
 */
public class ContractSelectionDTO implements Serializable {

    private String companyNo = StringUtils.EMPTY;
    private String companyName = StringUtils.EMPTY;
    private String companyType = StringUtils.EMPTY;
    private String companyCategory = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private String contractNo = StringUtils.EMPTY;
    private String contractName = StringUtils.EMPTY;
    private String contractHolder = StringUtils.EMPTY;
    private String rebateScheduleId = StringUtils.EMPTY;
    private String rebateScheduleName = StringUtils.EMPTY;
    private String rebateScheduleAlias = StringUtils.EMPTY;
    private String rebateScheduleNo = StringUtils.EMPTY;
    private String value = StringUtils.EMPTY;
    private String massField = StringUtils.EMPTY;
    private String rsNo = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private String rebateFrequency = StringUtils.EMPTY;
    private String start = StringUtils.EMPTY;
    private String rarType = StringUtils.EMPTY;
    private String basis = StringUtils.EMPTY;
    private String end = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String endDate = StringUtils.EMPTY;
    private String rebateScheduleType = StringUtils.EMPTY;
    private String rebateProgramType = StringUtils.EMPTY;
    private String rebateScheduleCategory = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String rarCategory = StringUtils.EMPTY;
    private String field = StringUtils.EMPTY;
    private String componentSelection = StringUtils.EMPTY;
    private String companyStartDate = StringUtils.EMPTY;
    private String companyEndDate = StringUtils.EMPTY;
    private String CFPname = StringUtils.EMPTY;
    private String IFPname = StringUtils.EMPTY;
    private String PSname = StringUtils.EMPTY;
    private String RSname = StringUtils.EMPTY;
    private List<String> companyMasterSids = new ArrayList<>();
    private List<String> phCompanyMasterSids = new ArrayList<>();
    private String moduleName = StringUtils.EMPTY;
    private String screenName = StringUtils.EMPTY;
    private int CFPId = 0;
    private int IFPId = 0;
    private int PSId = 0;
    private int RSId = 0;
    private boolean search = false;
    private boolean reset = false;
    private boolean searchInverse = false;
    private boolean transfer = false;
    private String cfpNo = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String psNo = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String sessionId = StringUtils.EMPTY;
    private String userid = StringUtils.EMPTY;

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getRebateScheduleId() {
        return rebateScheduleId;
    }

    public void setRebateScheduleId(String rebateScheduleId) {
        this.rebateScheduleId = rebateScheduleId;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }

    public String getRebateScheduleAlias() {
        return rebateScheduleAlias;
    }

    public void setRebateScheduleAlias(String rebateScheduleAlias) {
        this.rebateScheduleAlias = rebateScheduleAlias;
    }

    public String getRebateScheduleNo() {
        return rebateScheduleNo;
    }

    public void setRebateScheduleNo(String rebateScheduleNo) {
        this.rebateScheduleNo = rebateScheduleNo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassField() {
        return massField;
    }

    public void setMassField(String massField) {
        this.massField = massField;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRebateFrequency() {
        return rebateFrequency;
    }

    public void setRebateFrequency(String rebateFrequency) {
        this.rebateFrequency = rebateFrequency;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getRarType() {
        return rarType;
    }

    public void setRarType(String rarType) {
        this.rarType = rarType;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRebateScheduleType() {
        return rebateScheduleType;
    }

    public void setRebateScheduleType(String rebateScheduleType) {
        this.rebateScheduleType = rebateScheduleType;
    }

    public String getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(String rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public String getRebateScheduleCategory() {
        return rebateScheduleCategory;
    }

    public void setRebateScheduleCategory(String rebateScheduleCategory) {
        this.rebateScheduleCategory = rebateScheduleCategory;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getRarCategory() {
        return rarCategory;
    }

    public void setRarCategory(String rarCategory) {
        this.rarCategory = rarCategory;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getComponentSelection() {
        return componentSelection;
    }

    public void setComponentSelection(String componentSelection) {
        this.componentSelection = componentSelection;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyEndDate() {
        return companyEndDate;
    }

    public void setCompanyEndDate(String companyEndDate) {
        this.companyEndDate = companyEndDate;
    }

    public String getCFPname() {
        return CFPname;
    }

    public void setCFPname(String CFPname) {
        this.CFPname = CFPname;
    }

    public String getIFPname() {
        return IFPname;
    }

    public void setIFPname(String IFPname) {
        this.IFPname = IFPname;
    }

    public String getPSname() {
        return PSname;
    }

    public void setPSname(String PSname) {
        this.PSname = PSname;
    }

    public String getRSname() {
        return RSname;
    }

    public void setRSname(String RSname) {
        this.RSname = RSname;
    }

    public int getCFPId() {
        return CFPId;
    }

    public void setCFPId(int CFPId) {
        this.CFPId = CFPId;
    }

    public int getIFPId() {
        return IFPId;
    }

    public void setIFPId(int IFPId) {
        this.IFPId = IFPId;
    }

    public int getPSId() {
        return PSId;
    }

    public void setPSId(int PSId) {
        this.PSId = PSId;
    }

    public int getRSId() {
        return RSId;
    }

    public void setRSId(int RSId) {
        this.RSId = RSId;
    }

    public List<String> getCompanyMasterSids() {
        return companyMasterSids;
    }

    public void setCompanyMasterSids(List<String> companyMasterSids) {
        this.companyMasterSids = companyMasterSids;
    }

    public boolean isSearchInverse() {
        return searchInverse;
    }

    public void setSearchInverse(boolean searchInverse) {
        this.searchInverse = searchInverse;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getCfpNo() {
        return cfpNo;
    }

    public void setCfpNo(String cfpNo) {
        this.cfpNo = cfpNo;
    }

    public String getIfpNo() {
        return ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }

    public String getPsNo() {
        return psNo;
    }

    public void setPsNo(String psNo) {
        this.psNo = psNo;
    }

    public List<String> getPhCompanyMasterSids() {
        return phCompanyMasterSids;
    }

    public void setPhCompanyMasterSids(List<String> phCompanyMasterSids) {
        this.phCompanyMasterSids = phCompanyMasterSids;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;

    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    

}
