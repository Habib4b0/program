/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.pparesults.dto;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author pvinoth
 */
public class PPADetailsDTO implements Serializable {
    
    private PPAHelperDTO ppaHelperDTO=new PPAHelperDTO(0,Constant.SELECT_ONE,Constant.SELECT_ONE);
    private final HelperDTO defaultDTO = new HelperDTO(0, Constant.SELECT_ONE);
    private String period = StringUtils.EMPTY;
    private String priceProtectionPriceType = StringUtils.EMPTY;
    private String price = StringUtils.EMPTY;
    private String priceChange = StringUtils.EMPTY;
    private String map= StringUtils.EMPTY;
    private String totalDeductions = StringUtils.EMPTY;
    private String units = StringUtils.EMPTY;
    private String deductionPerUnit = StringUtils.EMPTY;
    private String netPrice = StringUtils.EMPTY;
    private String netMAP = StringUtils.EMPTY;
    private String priceProtectionAmountPerUnit = StringUtils.EMPTY;
    private String priceProtectionPercent = StringUtils.EMPTY;
    private String totalPriceProtectionDeduction = StringUtils.EMPTY;
    private String nep= StringUtils.EMPTY;
    private String nepFormula = StringUtils.EMPTY;
    private String priceToleranceType = StringUtils.EMPTY;
    private String priceTolerance = StringUtils.EMPTY;
    private String priceToleranceInterval = StringUtils.EMPTY;
    private String priceToleranceFrequency = StringUtils.EMPTY;
    private String maxIncrementalChange = StringUtils.EMPTY;
    private String resetEligible = StringUtils.EMPTY;
    private String resetType = StringUtils.EMPTY;
    private String resetDate = StringUtils.EMPTY;
    private String resetInterval= StringUtils.EMPTY;
    private String resetFrequency = StringUtils.EMPTY;
    private String netPriceType = StringUtils.EMPTY;
    private String netPriceTypeFormula = StringUtils.EMPTY;
    private HelperDTO contract = defaultDTO;
    private HelperDTO customer = defaultDTO;
    private HelperDTO brand = defaultDTO;

    private int projectionID ;
    private int selectedContract =0 ;
    private int selectedCustomer =0 ;
    private int selectedBrand =0 ;
    private int selectedItemMasterSid =0 ;
   
    private int item =0 ;
    private String startPeriod = StringUtils.EMPTY;
    private String endPeriod = StringUtils.EMPTY;
    private String rebateScheduleName = StringUtils.EMPTY;
   
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPriceProtectionPriceType() {
        return priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(String priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(String totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDeductionPerUnit() {
        return deductionPerUnit;
    }

    public void setDeductionPerUnit(String deductionPerUnit) {
        this.deductionPerUnit = deductionPerUnit;
    }

    public String getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(String netPrice) {
        this.netPrice = netPrice;
    }

    public String getNetMAP() {
        return netMAP;
    }

    public void setNetMAP(String netMAP) {
        this.netMAP = netMAP;
    }

    public String getPriceProtectionAmountPerUnit() {
        return priceProtectionAmountPerUnit;
    }

    public void setPriceProtectionAmountPerUnit(String priceProtectionAmountPerUnit) {
        this.priceProtectionAmountPerUnit = priceProtectionAmountPerUnit;
    }

    public String getPriceProtectionPercent() {
        return priceProtectionPercent;
    }

    public void setPriceProtectionPercent(String priceProtectionPercent) {
        this.priceProtectionPercent = priceProtectionPercent;
    }

    public String getTotalPriceProtectionDeduction() {
        return totalPriceProtectionDeduction;
    }

    public void setTotalPriceProtectionDeduction(String totalPriceProtectionDeduction) {
        this.totalPriceProtectionDeduction = totalPriceProtectionDeduction;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public String getNepFormula() {
        return nepFormula;
    }

    public void setNepFormula(String nepFormula) {
        this.nepFormula = nepFormula;
    }

    public String getPriceToleranceType() {
        return priceToleranceType;
    }

    public void setPriceToleranceType(String priceToleranceType) {
        this.priceToleranceType = priceToleranceType;
    }

    public String getPriceTolerance() {
        return priceTolerance;
    }

    public void setPriceTolerance(String priceTolerance) {
        this.priceTolerance = priceTolerance;
    }

    public String getPriceToleranceInterval() {
        return priceToleranceInterval;
    }

    public void setPriceToleranceInterval(String priceToleranceInterval) {
        this.priceToleranceInterval = priceToleranceInterval;
    }

    public String getPriceToleranceFrequency() {
        return priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(String priceToleranceFrequency) {
        this.priceToleranceFrequency = priceToleranceFrequency;
    }

    public String getMaxIncrementalChange() {
        return maxIncrementalChange;
    }

    public void setMaxIncrementalChange(String maxIncrementalChange) {
        this.maxIncrementalChange = maxIncrementalChange;
    }

    public String getResetEligible() {
        return resetEligible;
    }

    public void setResetEligible(String resetEligible) {
        this.resetEligible = resetEligible;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
    }

    public String getResetInterval() {
        return resetInterval;
    }

    public void setResetInterval(String resetInterval) {
        this.resetInterval = resetInterval;
    }

    public String getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public String getNetPriceType() {
        return netPriceType;
    }

    public void setNetPriceType(String netPriceType) {
        this.netPriceType = netPriceType;
    }

    public String getNetPriceTypeFormula() {
        return netPriceTypeFormula;
    }

    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        this.netPriceTypeFormula = netPriceTypeFormula;
    }

    public HelperDTO getContract() {
        return contract;
    }

    public void setContract(HelperDTO contract) {
        this.contract = contract;
    }

    public HelperDTO getCustomer() {
        return customer;
    }

    public void setCustomer(HelperDTO customer) {
        this.customer = customer;
    }

    public HelperDTO getBrand() {
        return brand;
    }

    public void setBrand(HelperDTO brand) {
        this.brand = brand;
    }

   
    public int getProjectionID() {
        return projectionID;
    }

    public void setProjectionID(int projectionID) {
        this.projectionID = projectionID;
    }

    public int getSelectedContract() {
        return selectedContract;
    }

    public void setSelectedContract(int selectedContract) {
        this.selectedContract = selectedContract;
    }

    public int getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(int selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public int getSelectedBrand() {
        return selectedBrand;
    }

    public void setSelectedBrand(int selectedBrand) {
        this.selectedBrand = selectedBrand;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public int getSelectedItemMasterSid() {
        return selectedItemMasterSid;
    }

    public void setSelectedItemMasterSid(int selectedItemMasterSid) {
        this.selectedItemMasterSid = selectedItemMasterSid;
    }
   public PPAHelperDTO getPpaHelperDTO() {
        return ppaHelperDTO;
    }

    public void setPpaHelperDTO(PPAHelperDTO ppaHelperDTO) {
        this.ppaHelperDTO = ppaHelperDTO;
    }

    public String getRebateScheduleName() {
        return rebateScheduleName;
    }

    public void setRebateScheduleName(String rebateScheduleName) {
        this.rebateScheduleName = rebateScheduleName;
    }
    
}
