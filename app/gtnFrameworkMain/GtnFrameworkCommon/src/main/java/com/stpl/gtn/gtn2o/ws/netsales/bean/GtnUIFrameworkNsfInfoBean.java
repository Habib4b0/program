/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.netsales.bean;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import java.util.ArrayList;

/**
 *
 * @author STPL
 */
public class GtnUIFrameworkNsfInfoBean {

	private String formulaId;
	private String formulaNo;
	private String formulaName;
	private Integer formulaCategory;

	private String contractSelection;
	private String formulaTypeDesc;
	private Integer formulaType;
	private Integer netSalesSid;
	private String netSalesRuleName;

	private Integer systemId;
        private boolean checkAll;
        private String columnId;
        private Object value;

	private List<GtnWebServiceSearchCriteria> availableContractSearchCriteriaList;

	public GtnUIFrameworkNsfInfoBean() {
		super();
	}

	public String getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	public String getFormulaNo() {
		return formulaNo;
	}

	public void setFormulaNo(String formulaNo) {
		this.formulaNo = formulaNo;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public Integer getFormulaCategory() {
		return formulaCategory;
	}

	public void setFormulaCategory(Integer formulaCategory) {
		this.formulaCategory = formulaCategory;
	}

	public String getContractSelection() {
		return contractSelection;
	}

	public void setContractSelection(String contractSelection) {
		this.contractSelection = contractSelection;
	}

	public String getFormulaTypeDesc() {
		return formulaTypeDesc;
	}

	public void setFormulaTypeDesc(String formulaTypeDesc) {
		this.formulaTypeDesc = formulaTypeDesc;
	}

	public Integer getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(Integer formulaType) {
		this.formulaType = formulaType;
	}

	public Integer getNetSalesSid() {
		return netSalesSid;
	}

	public void setNetSalesSid(Integer netSalesSid) {
		this.netSalesSid = netSalesSid;
	}

	public String getNetSalesRuleName() {
		return netSalesRuleName;
	}

	public void setNetSalesRuleName(String netSalesRuleName) {
		this.netSalesRuleName = netSalesRuleName;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public List<GtnWebServiceSearchCriteria> getAvailableContractSearchCriteriaList() {
		return availableContractSearchCriteriaList != null ? new ArrayList<>(availableContractSearchCriteriaList) : availableContractSearchCriteriaList;
	}

	public void setAvailableContractSearchCriteriaList(
			List<GtnWebServiceSearchCriteria> availableContractSearchCriteriaList) {
		this.availableContractSearchCriteriaList = (availableContractSearchCriteriaList != null ? new ArrayList<>(availableContractSearchCriteriaList) : availableContractSearchCriteriaList);
	}
        
          public boolean isCheckAll() {
                return checkAll;
        }

        public void setCheckAll(boolean checkAll) {
                this.checkAll = checkAll;
        }
        
        public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
        
        public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
