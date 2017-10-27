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

    String companyNo = StringUtils.EMPTY;
    String companyName = StringUtils.EMPTY;
    String companyType = StringUtils.EMPTY;
    String companyCategory = StringUtils.EMPTY;
    String tradeClass = StringUtils.EMPTY;
    String contractNo = StringUtils.EMPTY;
    String contractName = StringUtils.EMPTY;
    String contractHolder = StringUtils.EMPTY;
    String rebateScheduleId = StringUtils.EMPTY;
    String rebateScheduleName = StringUtils.EMPTY;
    String rebateScheduleAlias = StringUtils.EMPTY;
    String rebateScheduleNo = StringUtils.EMPTY;
    String value = StringUtils.EMPTY;
    String massField = StringUtils.EMPTY;
    String rsNo = StringUtils.EMPTY;
    String status = StringUtils.EMPTY;
    String rebateFrequency = StringUtils.EMPTY;
    String start = StringUtils.EMPTY;
    String rarType = StringUtils.EMPTY;
    String basis = StringUtils.EMPTY;
    String end = StringUtils.EMPTY;
    String startDate = StringUtils.EMPTY;
    String endDate = StringUtils.EMPTY;
    String rebateScheduleType = StringUtils.EMPTY;
    String rebateProgramType = StringUtils.EMPTY;
    String rebateScheduleCategory =StringUtils.EMPTY;
    String marketType = StringUtils.EMPTY;
    String rarCategory = StringUtils.EMPTY;
    String field = StringUtils.EMPTY;
    String componentSelection = StringUtils.EMPTY;
    String companyStartDate = StringUtils.EMPTY;
    String companyEndDate = StringUtils.EMPTY;
    String CFPname = StringUtils.EMPTY;
    String IFPname = StringUtils.EMPTY;
    String PSname = StringUtils.EMPTY;
    String RSname = StringUtils.EMPTY;
    List<String> companyMasterSids = new ArrayList<>();
    List<String> phCompanyMasterSids = new ArrayList<>();

    String moduleName = StringUtils.EMPTY;
    String screenName = StringUtils.EMPTY;
    int CFPId = 0;
    int IFPId = 0;
    int PSId = 0;
    int RSId = 0;
    private boolean search = false;
    private boolean reset = false;
    private boolean searchInverse = false;
    private boolean transfer = false;
    private String cfpNo = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String psNo = StringUtils.EMPTY;
    private String ContractSid = StringUtils.EMPTY;
    private String Sessionid = StringUtils.EMPTY;
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
        return ContractSid;
    }

    public void setContractSid(String ContractSid) {
        this.ContractSid = ContractSid;
    }

    public String getSessionid() {
        return Sessionid;
    }

    public void setSessionid(String Sessionid) {
        this.Sessionid = Sessionid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    

}
