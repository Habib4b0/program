/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;


import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

/**
 *
 * @author maheshj
 */
public class SalesRowDto extends ExtMapDTO {


    private String levelName = StringUtils.EMPTY;
    private String actualLevel = StringUtils.EMPTY;
    private String group = StringUtils.EMPTY;
    String ccpid = "";

    private String customer = "empty";
    private String contract = "empty";
    private String product = "empty";

    private Integer levelNo = new Integer("0");
    private Integer treeLevelNo = new Integer("0");
    private Integer relationLevelSid = new Integer("0");
    private String hierarchyNo = "%";
    private String hierarchyLevel="empty";
    private String returnDetailsSid = StringUtils.EMPTY;
    private String lastCustomerHierarchyno="%";
    private String lastProductHierarchyno="%";
    
    private String hierarchyType="empty";
    
    private String parentHierarchyType="empty";
    private Integer uncheckCount=new Integer("0");
    private Integer parent = 0;
    private String parentLevelIndicator = StringUtils.EMPTY;
    private String levelIndicator = StringUtils.EMPTY;
    private String childLevelIndicator = StringUtils.EMPTY;
    private String levelValue = StringUtils.EMPTY;
    private String parentNode = StringUtils.EMPTY;
    private String hierarchyIndicator = StringUtils.EMPTY;
    private String log = StringUtils.EMPTY;
    private String closedDate = StringUtils.EMPTY;
    private String loeDate = StringUtils.EMPTY;
    private String productHierarchyNo = "%";
    private String customerHierarchyNo = "%";
    private String parentHierarchyNo = StringUtils.EMPTY;
    private Integer onExpandTotalRow = 0;
    private String expandedBrand = "%";
    private String expandedNDC = "%";
    private String expandedIndividualCustomer = "%";
    private String expandedCompany = "%";
    private String indexNo = "0";
    private String projectionDetailsSid = StringUtils.EMPTY;
    private String brandToSave = "%";
    private String ndcToSave = "%";
    private String contractToSave = "%";
    private String companyToSave = "%";
    private String checkRecordCount = "0";
    private String parentHierarchyIndicator = StringUtils.EMPTY;
    private String hierarchialLevelName = StringUtils.EMPTY;
    private String customerLevelNo = "%";
    private String itemSid = StringUtils.EMPTY;
    private String ndc11 = StringUtils.EMPTY;
    private String ndc8 = StringUtils.EMPTY;
    private String itemRlv = StringUtils.EMPTY;
    private String itemMapHno = StringUtils.EMPTY;
    private String productLevelNo = "%";
    private String ccpCount = "0";
    private String methodology=StringUtils.EMPTY;
    private String baseline=StringUtils.EMPTY;
    private String reHierarchyNo =StringUtils.EMPTY;

    public String getMethodology() {
        return methodology;
    }
    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }
    
    public String getActualLevel() {
        return actualLevel;
    }

    public void setActualLevel(String actualLevel) {
        this.actualLevel = actualLevel;
    }
    
    public String getHierarchyType() {
        return hierarchyType;
    }

    public void setHierarchyType(String hierarchyType) {
        this.hierarchyType = hierarchyType;
    }

    public String getParentHierarchyType() {
        return parentHierarchyType;
    }

    public void setParentHierarchyType(String parentHierarchyType) {
        this.parentHierarchyType = parentHierarchyType;
    }
    
    

    public String getLastCustomerHierarchyno() {
        return lastCustomerHierarchyno;
    }

    public void setLastCustomerHierarchyno(String lastCustomerHierarchyno) {
        this.lastCustomerHierarchyno = lastCustomerHierarchyno;
    }

    public String getLastProductHierarchyno() {
        return lastProductHierarchyno;
    }

    public void setLastProductHierarchyno(String lastProductHierarchyno) {
        this.lastProductHierarchyno = lastProductHierarchyno;
    }

     
    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    
    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }
    

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public Integer getRelationLevelSid() {
        return relationLevelSid;
    }

    public void setRelationLevelSid(Integer relationLevelSid) {
        this.relationLevelSid = relationLevelSid;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getCcpid() {
        return ccpid;
    }

    public void setCcpid(String ccpid) {
        this.ccpid = ccpid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }



    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getUncheckCount() {
        return uncheckCount;
    }

    public void setUncheckCount(Integer uncheckCount) {
        this.uncheckCount = uncheckCount;
    }
    


    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getParentLevelIndicator() {
        return parentLevelIndicator;
    }

    public void setParentLevelIndicator(String parentLevelIndicator) {
        this.parentLevelIndicator = parentLevelIndicator;
    }

    public String getLevelIndicator() {
        return levelIndicator;
    }

    public void setLevelIndicator(String levelIndicator) {
        this.levelIndicator = levelIndicator;
    }

    public String getChildLevelIndicator() {
        return childLevelIndicator;
    }

    public void setChildLevelIndicator(String childLevelIndicator) {
        this.childLevelIndicator = childLevelIndicator;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getProductHierarchyNo() {
        return productHierarchyNo;
    }

    public void setProductHierarchyNo(String productHierarchyNo) {
        this.productHierarchyNo = productHierarchyNo;
    }

    public String getCustomerHierarchyNo() {
        return customerHierarchyNo;
    }

    public void setCustomerHierarchyNo(String customerHierarchyNo) {
        this.customerHierarchyNo = customerHierarchyNo;
    }

    public String getParentHierarchyNo() {
        return parentHierarchyNo;
    }

    public void setParentHierarchyNo(String parentHierarchyNo) {
        this.parentHierarchyNo = parentHierarchyNo;
    }

    public Integer getOnExpandTotalRow() {
        return onExpandTotalRow;
    }

    public void setOnExpandTotalRow(Integer onExpandTotalRow) {
        this.onExpandTotalRow = onExpandTotalRow;
    }

    public String getExpandedBrand() {
        return expandedBrand;
    }

    public void setExpandedBrand(String expandedBrand) {
        this.expandedBrand = expandedBrand;
    }

    public String getExpandedNDC() {
        return expandedNDC;
    }

    public void setExpandedNDC(String expandedNDC) {
        this.expandedNDC = expandedNDC;
    }

    public String getExpandedIndividualCustomer() {
        return expandedIndividualCustomer;
    }

    public void setExpandedIndividualCustomer(String expandedIndividualCustomer) {
        this.expandedIndividualCustomer = expandedIndividualCustomer;
    }

    public String getExpandedCompany() {
        return expandedCompany;
    }

    public void setExpandedCompany(String expandedCompany) {
        this.expandedCompany = expandedCompany;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(String projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public String getBrandToSave() {
        return brandToSave;
    }

    public void setBrandToSave(String brandToSave) {
        this.brandToSave = brandToSave;
    }

    public String getNdcToSave() {
        return ndcToSave;
    }

    public void setNdcToSave(String ndcToSave) {
        this.ndcToSave = ndcToSave;
    }

    public String getContractToSave() {
        return contractToSave;
    }

    public void setContractToSave(String contractToSave) {
        this.contractToSave = contractToSave;
    }

    public String getCompanyToSave() {
        return companyToSave;
    }

    public void setCompanyToSave(String companyToSave) {
        this.companyToSave = companyToSave;
    }

    public String getCheckRecordCount() {
        return checkRecordCount;
    }

    public void setCheckRecordCount(String checkRecordCount) {
        this.checkRecordCount = checkRecordCount;
    }

    public String getParentHierarchyIndicator() {
        return parentHierarchyIndicator;
    }

    public void setParentHierarchyIndicator(String parentHierarchyIndicator) {
        this.parentHierarchyIndicator = parentHierarchyIndicator;
    }

    public String getHierarchialLevelName() {
        return hierarchialLevelName;
    }

    public void setHierarchialLevelName(String hierarchialLevelName) {
        this.hierarchialLevelName = hierarchialLevelName;
    }

    public String getCustomerLevelNo() {
        return customerLevelNo;
    }

    public void setCustomerLevelNo(String customerLevelNo) {
        this.customerLevelNo = customerLevelNo;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String getNdc11() {
        return ndc11;
    }

    public void setNdc11(String ndc11) {
        this.ndc11 = ndc11;
    }

    public String getNdc8() {
        return ndc8;
    }

    public void setNdc8(String ndc8) {
        this.ndc8 = ndc8;
    }

    public String getItemRlv() {
        return itemRlv;
    }

    public void setItemRlv(String itemRlv) {
        this.itemRlv = itemRlv;
    }

    public String getItemMapHno() {
        return itemMapHno;
    }

    public void setItemMapHno(String itemMapHno) {
        this.itemMapHno = itemMapHno;
    }

    public String getProductLevelNo() {
        return productLevelNo;
    }

    public void setProductLevelNo(String productLevelNo) {
        this.productLevelNo = productLevelNo;
    }

    public String getCcpCount() {
        return ccpCount;
    }

    public void setCcpCount(String ccpCount) {
        this.ccpCount = ccpCount;
    }

    public String getReturnDetailsSid() {
        return returnDetailsSid;
    }

    public void setReturnDetailsSid(String returnDetailsSid) {
        this.returnDetailsSid = returnDetailsSid;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getLoeDate() {
        return loeDate;
    }

    public void setLoeDate(String loeDate) {
        this.loeDate = loeDate;
    }

    public String getReHierarchyNo() {
        return reHierarchyNo;
    }

    public void setReHierarchyNo(String reHierarchyNo) {
        this.reHierarchyNo = reHierarchyNo;
    }

}
