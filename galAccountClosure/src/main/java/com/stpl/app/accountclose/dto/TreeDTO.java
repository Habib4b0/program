/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import com.stpl.app.customtreecontainer.CustomTreeDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vigneshkanna
 */
public class TreeDTO extends CustomTreeDTO implements Comparator<TreeDTO> {

    private String levelValue = StringUtils.EMPTY;
    private Integer parent = 0;
    private Integer levelNo = 0;
    private String glComapnySid = StringUtils.EMPTY;
    private String contractSid = StringUtils.EMPTY;
    private String brandMasterSid = StringUtils.EMPTY;
    private String itemSid = StringUtils.EMPTY;
    private String rebateId = StringUtils.EMPTY;
    private String custGroupSid = StringUtils.EMPTY;
    private String prdGroupSid = StringUtils.EMPTY;
    private String comapnySid = StringUtils.EMPTY;
    private Integer startIndex = 0;
    private Integer offSet = 0;
    private String countFlag = StringUtils.EMPTY;
    private Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>();
    private String id = StringUtils.EMPTY;
    private String currRate = StringUtils.EMPTY;
    private String penRate = StringUtils.EMPTY;
    private String ccpSid = StringUtils.EMPTY;
    private Integer uncheckCount = 0;
    private Integer ccpCount = 0;
    private Integer treeLevelNo = 0;
    private String levelName = StringUtils.EMPTY;
    private String activeAdj = StringUtils.EMPTY;
    private String pendingAdj = StringUtils.EMPTY;
    private Boolean checkRecord = false;
    private String contractTypeSid = StringUtils.EMPTY;
    private String acctTypeSid = StringUtils.EMPTY;
    private String acctSubTypeSid = StringUtils.EMPTY;
    private Integer sessionId = 0;
    private String ComapnyMasterSid = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String AccountClosureMasterSid = StringUtils.EMPTY;
    private String therupticClassId = "0";
    private String ndc = "0";

    private String AccSidFromworkflow = "0";
    private String AccSidFromworkflowvalue = StringUtils.EMPTY;
    private String conAdj = StringUtils.EMPTY;

    private List compList = new ArrayList();
    private List selCompList = new ArrayList();


    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getActiveAdj() {
        return activeAdj;
    }

    public void setActiveAdj(String activeAdj) {
        this.activeAdj = activeAdj;
    }

    public String getPendingAdj() {
        return pendingAdj;
    }

    public void setPendingAdj(String pendingAdj) {
        this.pendingAdj = pendingAdj;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getGlComapnySid() {
        return glComapnySid;
    }

    public void setGlComapnySid(String glComapnySid) {
        this.glComapnySid = glComapnySid;
    }

    public String getContractSid() {
        return contractSid;
    }

    public void setContractSid(String contractSid) {
        this.contractSid = contractSid;
    }

    public String getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(String brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String getRebateId() {
        return rebateId;
    }

    public void setRebateId(String rebateId) {
        this.rebateId = rebateId;
    }

    public String getCustGroupSid() {
        return custGroupSid;
    }

    public void setCustGroupSid(String custGroupSid) {
        this.custGroupSid = custGroupSid;
    }

    public String getPrdGroupSid() {
        return prdGroupSid;
    }

    public void setPrdGroupSid(String prdGroupSid) {
        this.prdGroupSid = prdGroupSid;
    }

    public int compare(TreeDTO o1, TreeDTO o2) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public String getCountFlag() {
        return countFlag;
    }

    public void setCountFlag(String countFlag) {
        this.countFlag = countFlag;
    }

    public Map<Integer, Map<String, String>> getIdMainMap() {
        return idMainMap;
    }

    public void setIdMainMap(Map<Integer, Map<String, String>> idMainMap) {
        this.idMainMap = idMainMap;
    }

    public String getComapnySid() {
        return comapnySid;
    }

    public void setComapnySid(String comapnySid) {
        this.comapnySid = comapnySid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrRate() {
        return currRate;
    }

    public void setCurrRate(String currRate) {
        this.currRate = currRate;
    }

    public String getPenRate() {
        return penRate;
    }

    public void setPenRate(String penRate) {
        this.penRate = penRate;
    }

    public String getCcpSid() {
        return ccpSid;
    }

    public void setCcpSid(String ccpSid) {
        this.ccpSid = ccpSid;
    }

    public Integer getUncheckCount() {
        return uncheckCount;
    }

    public void setUncheckCount(Integer uncheckCount) {
        this.uncheckCount = uncheckCount;
    }

    public Integer getCcpCount() {
        return ccpCount;
    }

    public void setCcpCount(Integer ccpCount) {
        this.ccpCount = ccpCount;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getContractTypeSid() {
        return contractTypeSid;
    }

    public void setContractTypeSid(String contractTypeSid) {
        this.contractTypeSid = contractTypeSid;
    }

    public String getAcctTypeSid() {
        return acctTypeSid;
    }

    public void setAcctTypeSid(String acctTypeSid) {
        this.acctTypeSid = acctTypeSid;
    }

    public String getAcctSubTypeSid() {
        return acctSubTypeSid;
    }

    public void setAcctSubTypeSid(String acctSubTypeSid) {
        this.acctSubTypeSid = acctSubTypeSid;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getComapnyMasterSid() {
        return ComapnyMasterSid;
    }

    public void setComapnyMasterSid(String ComapnyMasterSid) {
        this.ComapnyMasterSid = ComapnyMasterSid;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getAccountClosureMasterSid() {
        return AccountClosureMasterSid;
    }

    public void setAccountClosureMasterSid(String AccountClosureMasterSid) {
        this.AccountClosureMasterSid = AccountClosureMasterSid;
    }

    public String getTherupticClassId() {
        return therupticClassId;
    }

    public void setTherupticClassId(String therupticClassId) {
        this.therupticClassId = therupticClassId;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }


    public String getAccSidFromworkflow() {
        return AccSidFromworkflow;
    }

    public void setAccSidFromworkflow(String AccSidFromworkflow) {
        this.AccSidFromworkflow = AccSidFromworkflow;
    }

    public String getAccSidFromworkflowvalue() {
        return AccSidFromworkflowvalue;
    }

    public void setAccSidFromworkflowvalue(String AccSidFromworkflowvalue) {
        this.AccSidFromworkflowvalue = AccSidFromworkflowvalue;
    }

    public String getConAdj() {
        return conAdj;
    }

    public void setConAdj(String conAdj) {
        this.conAdj = conAdj;
    }

    public List getCompList() {
        return compList;
    }

    public void setCompList(List compList) {
        this.compList = compList;
    }

    public List getSelCompList() {
        return selCompList;
    }

    public void setSelCompList(List selCompList) {
        this.selCompList = selCompList;
    }


}
