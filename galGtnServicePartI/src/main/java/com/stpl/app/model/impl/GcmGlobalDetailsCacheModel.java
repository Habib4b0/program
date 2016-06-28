package com.stpl.app.model.impl;

import com.stpl.app.model.GcmGlobalDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GcmGlobalDetails in entity cache.
 *
 * @author
 * @see GcmGlobalDetails
 * @generated
 */
public class GcmGlobalDetailsCacheModel implements CacheModel<GcmGlobalDetails>,
    Externalizable {
    public String itemStatus;
    public String formulaMethodId;
    public String moduleName;
    public String paymentFrequency;
    public long endDate;
    public long cfpStartDate;
    public long priceProtectionStartDate;
    public String tempItemMasterSid;
    public String brandName;
    public long modifiedDate;
    public int contractMasterSid;
    public int modifiedBy;
    public String subModuleName;
    public String theraputicClass;
    public int gcmGlobalDetailsSid;
    public boolean checkRecord;
    public String paymentMethod;
    public long contractPriceEndDate;
    public int psContractSid;
    public long priceProtectionEndDate;
    public long startDate;
    public String screenName;
    public int rsContractSid;
    public String itemName;
    public String sessionId;
    public String cfpStatus;
    public int rsModelSid;
    public int cfpContractSid;
    public double price;
    public long tempEndDate;
    public int itemMasterSid;
    public String itemType;
    public String forecastingType;
    public String itemId;
    public double basePrice;
    public String status;
    public String formulaName;
    public int workflowMasterSid;
    public double priceTolerance;
    public int createdBy;
    public long createdDate;
    public long tempStartDate;
    public long cfpEndDate;
    public int psModelSid;
    public String formulaId;
    public String itemNo;
    public double contractPrice;
    public int ifpModelSid;
    public int priceToleranceType;
    public int rebateAmount;
    public int userId;
    public int projectionMasterSid;
    public long contractPriceStartDate;
    public int priceToleranceFrequency;
    public String ifpContractAttachedStatus;
    public int rebatePlanSystemId;
    public String rebatePlanName;
    public String calendar;
    public String pricingQualifierSid;
    public String tempStatus;
    public long itemRebateEndDate;
    public int priceToleranceInterval;
    public long itemRebateStartDate;
    public String operation;
    public int cfpModelSid;
    public int itemStatusSid;
    public int ifpContractSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(135);

        sb.append("{itemStatus=");
        sb.append(itemStatus);
        sb.append(", formulaMethodId=");
        sb.append(formulaMethodId);
        sb.append(", moduleName=");
        sb.append(moduleName);
        sb.append(", paymentFrequency=");
        sb.append(paymentFrequency);
        sb.append(", endDate=");
        sb.append(endDate);
        sb.append(", cfpStartDate=");
        sb.append(cfpStartDate);
        sb.append(", priceProtectionStartDate=");
        sb.append(priceProtectionStartDate);
        sb.append(", tempItemMasterSid=");
        sb.append(tempItemMasterSid);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", subModuleName=");
        sb.append(subModuleName);
        sb.append(", theraputicClass=");
        sb.append(theraputicClass);
        sb.append(", gcmGlobalDetailsSid=");
        sb.append(gcmGlobalDetailsSid);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", paymentMethod=");
        sb.append(paymentMethod);
        sb.append(", contractPriceEndDate=");
        sb.append(contractPriceEndDate);
        sb.append(", psContractSid=");
        sb.append(psContractSid);
        sb.append(", priceProtectionEndDate=");
        sb.append(priceProtectionEndDate);
        sb.append(", startDate=");
        sb.append(startDate);
        sb.append(", screenName=");
        sb.append(screenName);
        sb.append(", rsContractSid=");
        sb.append(rsContractSid);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", cfpStatus=");
        sb.append(cfpStatus);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append(", cfpContractSid=");
        sb.append(cfpContractSid);
        sb.append(", price=");
        sb.append(price);
        sb.append(", tempEndDate=");
        sb.append(tempEndDate);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", itemType=");
        sb.append(itemType);
        sb.append(", forecastingType=");
        sb.append(forecastingType);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", basePrice=");
        sb.append(basePrice);
        sb.append(", status=");
        sb.append(status);
        sb.append(", formulaName=");
        sb.append(formulaName);
        sb.append(", workflowMasterSid=");
        sb.append(workflowMasterSid);
        sb.append(", priceTolerance=");
        sb.append(priceTolerance);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", tempStartDate=");
        sb.append(tempStartDate);
        sb.append(", cfpEndDate=");
        sb.append(cfpEndDate);
        sb.append(", psModelSid=");
        sb.append(psModelSid);
        sb.append(", formulaId=");
        sb.append(formulaId);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", contractPrice=");
        sb.append(contractPrice);
        sb.append(", ifpModelSid=");
        sb.append(ifpModelSid);
        sb.append(", priceToleranceType=");
        sb.append(priceToleranceType);
        sb.append(", rebateAmount=");
        sb.append(rebateAmount);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", contractPriceStartDate=");
        sb.append(contractPriceStartDate);
        sb.append(", priceToleranceFrequency=");
        sb.append(priceToleranceFrequency);
        sb.append(", ifpContractAttachedStatus=");
        sb.append(ifpContractAttachedStatus);
        sb.append(", rebatePlanSystemId=");
        sb.append(rebatePlanSystemId);
        sb.append(", rebatePlanName=");
        sb.append(rebatePlanName);
        sb.append(", calendar=");
        sb.append(calendar);
        sb.append(", pricingQualifierSid=");
        sb.append(pricingQualifierSid);
        sb.append(", tempStatus=");
        sb.append(tempStatus);
        sb.append(", itemRebateEndDate=");
        sb.append(itemRebateEndDate);
        sb.append(", priceToleranceInterval=");
        sb.append(priceToleranceInterval);
        sb.append(", itemRebateStartDate=");
        sb.append(itemRebateStartDate);
        sb.append(", operation=");
        sb.append(operation);
        sb.append(", cfpModelSid=");
        sb.append(cfpModelSid);
        sb.append(", itemStatusSid=");
        sb.append(itemStatusSid);
        sb.append(", ifpContractSid=");
        sb.append(ifpContractSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public GcmGlobalDetails toEntityModel() {
        GcmGlobalDetailsImpl gcmGlobalDetailsImpl = new GcmGlobalDetailsImpl();

        if (itemStatus == null) {
            gcmGlobalDetailsImpl.setItemStatus(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setItemStatus(itemStatus);
        }

        if (formulaMethodId == null) {
            gcmGlobalDetailsImpl.setFormulaMethodId(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setFormulaMethodId(formulaMethodId);
        }

        if (moduleName == null) {
            gcmGlobalDetailsImpl.setModuleName(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setModuleName(moduleName);
        }

        if (paymentFrequency == null) {
            gcmGlobalDetailsImpl.setPaymentFrequency(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setPaymentFrequency(paymentFrequency);
        }

        if (endDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setEndDate(null);
        } else {
            gcmGlobalDetailsImpl.setEndDate(new Date(endDate));
        }

        if (cfpStartDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setCfpStartDate(null);
        } else {
            gcmGlobalDetailsImpl.setCfpStartDate(new Date(cfpStartDate));
        }

        if (priceProtectionStartDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setPriceProtectionStartDate(null);
        } else {
            gcmGlobalDetailsImpl.setPriceProtectionStartDate(new Date(
                    priceProtectionStartDate));
        }

        if (tempItemMasterSid == null) {
            gcmGlobalDetailsImpl.setTempItemMasterSid(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setTempItemMasterSid(tempItemMasterSid);
        }

        if (brandName == null) {
            gcmGlobalDetailsImpl.setBrandName(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setBrandName(brandName);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setModifiedDate(null);
        } else {
            gcmGlobalDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        gcmGlobalDetailsImpl.setContractMasterSid(contractMasterSid);
        gcmGlobalDetailsImpl.setModifiedBy(modifiedBy);

        if (subModuleName == null) {
            gcmGlobalDetailsImpl.setSubModuleName(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setSubModuleName(subModuleName);
        }

        if (theraputicClass == null) {
            gcmGlobalDetailsImpl.setTheraputicClass(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setTheraputicClass(theraputicClass);
        }

        gcmGlobalDetailsImpl.setGcmGlobalDetailsSid(gcmGlobalDetailsSid);
        gcmGlobalDetailsImpl.setCheckRecord(checkRecord);

        if (paymentMethod == null) {
            gcmGlobalDetailsImpl.setPaymentMethod(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setPaymentMethod(paymentMethod);
        }

        if (contractPriceEndDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setContractPriceEndDate(null);
        } else {
            gcmGlobalDetailsImpl.setContractPriceEndDate(new Date(
                    contractPriceEndDate));
        }

        gcmGlobalDetailsImpl.setPsContractSid(psContractSid);

        if (priceProtectionEndDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setPriceProtectionEndDate(null);
        } else {
            gcmGlobalDetailsImpl.setPriceProtectionEndDate(new Date(
                    priceProtectionEndDate));
        }

        if (startDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setStartDate(null);
        } else {
            gcmGlobalDetailsImpl.setStartDate(new Date(startDate));
        }

        if (screenName == null) {
            gcmGlobalDetailsImpl.setScreenName(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setScreenName(screenName);
        }

        gcmGlobalDetailsImpl.setRsContractSid(rsContractSid);

        if (itemName == null) {
            gcmGlobalDetailsImpl.setItemName(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setItemName(itemName);
        }

        if (sessionId == null) {
            gcmGlobalDetailsImpl.setSessionId(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setSessionId(sessionId);
        }

        if (cfpStatus == null) {
            gcmGlobalDetailsImpl.setCfpStatus(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setCfpStatus(cfpStatus);
        }

        gcmGlobalDetailsImpl.setRsModelSid(rsModelSid);
        gcmGlobalDetailsImpl.setCfpContractSid(cfpContractSid);
        gcmGlobalDetailsImpl.setPrice(price);

        if (tempEndDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setTempEndDate(null);
        } else {
            gcmGlobalDetailsImpl.setTempEndDate(new Date(tempEndDate));
        }

        gcmGlobalDetailsImpl.setItemMasterSid(itemMasterSid);

        if (itemType == null) {
            gcmGlobalDetailsImpl.setItemType(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setItemType(itemType);
        }

        if (forecastingType == null) {
            gcmGlobalDetailsImpl.setForecastingType(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setForecastingType(forecastingType);
        }

        if (itemId == null) {
            gcmGlobalDetailsImpl.setItemId(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setItemId(itemId);
        }

        gcmGlobalDetailsImpl.setBasePrice(basePrice);

        if (status == null) {
            gcmGlobalDetailsImpl.setStatus(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setStatus(status);
        }

        if (formulaName == null) {
            gcmGlobalDetailsImpl.setFormulaName(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setFormulaName(formulaName);
        }

        gcmGlobalDetailsImpl.setWorkflowMasterSid(workflowMasterSid);
        gcmGlobalDetailsImpl.setPriceTolerance(priceTolerance);
        gcmGlobalDetailsImpl.setCreatedBy(createdBy);

        if (createdDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setCreatedDate(null);
        } else {
            gcmGlobalDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        if (tempStartDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setTempStartDate(null);
        } else {
            gcmGlobalDetailsImpl.setTempStartDate(new Date(tempStartDate));
        }

        if (cfpEndDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setCfpEndDate(null);
        } else {
            gcmGlobalDetailsImpl.setCfpEndDate(new Date(cfpEndDate));
        }

        gcmGlobalDetailsImpl.setPsModelSid(psModelSid);

        if (formulaId == null) {
            gcmGlobalDetailsImpl.setFormulaId(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setFormulaId(formulaId);
        }

        if (itemNo == null) {
            gcmGlobalDetailsImpl.setItemNo(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setItemNo(itemNo);
        }

        gcmGlobalDetailsImpl.setContractPrice(contractPrice);
        gcmGlobalDetailsImpl.setIfpModelSid(ifpModelSid);
        gcmGlobalDetailsImpl.setPriceToleranceType(priceToleranceType);
        gcmGlobalDetailsImpl.setRebateAmount(rebateAmount);
        gcmGlobalDetailsImpl.setUserId(userId);
        gcmGlobalDetailsImpl.setProjectionMasterSid(projectionMasterSid);

        if (contractPriceStartDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setContractPriceStartDate(null);
        } else {
            gcmGlobalDetailsImpl.setContractPriceStartDate(new Date(
                    contractPriceStartDate));
        }

        gcmGlobalDetailsImpl.setPriceToleranceFrequency(priceToleranceFrequency);

        if (ifpContractAttachedStatus == null) {
            gcmGlobalDetailsImpl.setIfpContractAttachedStatus(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setIfpContractAttachedStatus(ifpContractAttachedStatus);
        }

        gcmGlobalDetailsImpl.setRebatePlanSystemId(rebatePlanSystemId);

        if (rebatePlanName == null) {
            gcmGlobalDetailsImpl.setRebatePlanName(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setRebatePlanName(rebatePlanName);
        }

        if (calendar == null) {
            gcmGlobalDetailsImpl.setCalendar(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setCalendar(calendar);
        }

        if (pricingQualifierSid == null) {
            gcmGlobalDetailsImpl.setPricingQualifierSid(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setPricingQualifierSid(pricingQualifierSid);
        }

        if (tempStatus == null) {
            gcmGlobalDetailsImpl.setTempStatus(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setTempStatus(tempStatus);
        }

        if (itemRebateEndDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setItemRebateEndDate(null);
        } else {
            gcmGlobalDetailsImpl.setItemRebateEndDate(new Date(
                    itemRebateEndDate));
        }

        gcmGlobalDetailsImpl.setPriceToleranceInterval(priceToleranceInterval);

        if (itemRebateStartDate == Long.MIN_VALUE) {
            gcmGlobalDetailsImpl.setItemRebateStartDate(null);
        } else {
            gcmGlobalDetailsImpl.setItemRebateStartDate(new Date(
                    itemRebateStartDate));
        }

        if (operation == null) {
            gcmGlobalDetailsImpl.setOperation(StringPool.BLANK);
        } else {
            gcmGlobalDetailsImpl.setOperation(operation);
        }

        gcmGlobalDetailsImpl.setCfpModelSid(cfpModelSid);
        gcmGlobalDetailsImpl.setItemStatusSid(itemStatusSid);
        gcmGlobalDetailsImpl.setIfpContractSid(ifpContractSid);

        gcmGlobalDetailsImpl.resetOriginalValues();

        return gcmGlobalDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemStatus = objectInput.readUTF();
        formulaMethodId = objectInput.readUTF();
        moduleName = objectInput.readUTF();
        paymentFrequency = objectInput.readUTF();
        endDate = objectInput.readLong();
        cfpStartDate = objectInput.readLong();
        priceProtectionStartDate = objectInput.readLong();
        tempItemMasterSid = objectInput.readUTF();
        brandName = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        contractMasterSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        subModuleName = objectInput.readUTF();
        theraputicClass = objectInput.readUTF();
        gcmGlobalDetailsSid = objectInput.readInt();
        checkRecord = objectInput.readBoolean();
        paymentMethod = objectInput.readUTF();
        contractPriceEndDate = objectInput.readLong();
        psContractSid = objectInput.readInt();
        priceProtectionEndDate = objectInput.readLong();
        startDate = objectInput.readLong();
        screenName = objectInput.readUTF();
        rsContractSid = objectInput.readInt();
        itemName = objectInput.readUTF();
        sessionId = objectInput.readUTF();
        cfpStatus = objectInput.readUTF();
        rsModelSid = objectInput.readInt();
        cfpContractSid = objectInput.readInt();
        price = objectInput.readDouble();
        tempEndDate = objectInput.readLong();
        itemMasterSid = objectInput.readInt();
        itemType = objectInput.readUTF();
        forecastingType = objectInput.readUTF();
        itemId = objectInput.readUTF();
        basePrice = objectInput.readDouble();
        status = objectInput.readUTF();
        formulaName = objectInput.readUTF();
        workflowMasterSid = objectInput.readInt();
        priceTolerance = objectInput.readDouble();
        createdBy = objectInput.readInt();
        createdDate = objectInput.readLong();
        tempStartDate = objectInput.readLong();
        cfpEndDate = objectInput.readLong();
        psModelSid = objectInput.readInt();
        formulaId = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        contractPrice = objectInput.readDouble();
        ifpModelSid = objectInput.readInt();
        priceToleranceType = objectInput.readInt();
        rebateAmount = objectInput.readInt();
        userId = objectInput.readInt();
        projectionMasterSid = objectInput.readInt();
        contractPriceStartDate = objectInput.readLong();
        priceToleranceFrequency = objectInput.readInt();
        ifpContractAttachedStatus = objectInput.readUTF();
        rebatePlanSystemId = objectInput.readInt();
        rebatePlanName = objectInput.readUTF();
        calendar = objectInput.readUTF();
        pricingQualifierSid = objectInput.readUTF();
        tempStatus = objectInput.readUTF();
        itemRebateEndDate = objectInput.readLong();
        priceToleranceInterval = objectInput.readInt();
        itemRebateStartDate = objectInput.readLong();
        operation = objectInput.readUTF();
        cfpModelSid = objectInput.readInt();
        itemStatusSid = objectInput.readInt();
        ifpContractSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (itemStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemStatus);
        }

        if (formulaMethodId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaMethodId);
        }

        if (moduleName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(moduleName);
        }

        if (paymentFrequency == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(paymentFrequency);
        }

        objectOutput.writeLong(endDate);
        objectOutput.writeLong(cfpStartDate);
        objectOutput.writeLong(priceProtectionStartDate);

        if (tempItemMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tempItemMasterSid);
        }

        if (brandName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandName);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(contractMasterSid);
        objectOutput.writeInt(modifiedBy);

        if (subModuleName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subModuleName);
        }

        if (theraputicClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(theraputicClass);
        }

        objectOutput.writeInt(gcmGlobalDetailsSid);
        objectOutput.writeBoolean(checkRecord);

        if (paymentMethod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(paymentMethod);
        }

        objectOutput.writeLong(contractPriceEndDate);
        objectOutput.writeInt(psContractSid);
        objectOutput.writeLong(priceProtectionEndDate);
        objectOutput.writeLong(startDate);

        if (screenName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(screenName);
        }

        objectOutput.writeInt(rsContractSid);

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        if (sessionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sessionId);
        }

        if (cfpStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cfpStatus);
        }

        objectOutput.writeInt(rsModelSid);
        objectOutput.writeInt(cfpContractSid);
        objectOutput.writeDouble(price);
        objectOutput.writeLong(tempEndDate);
        objectOutput.writeInt(itemMasterSid);

        if (itemType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemType);
        }

        if (forecastingType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastingType);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeDouble(basePrice);

        if (status == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(status);
        }

        if (formulaName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaName);
        }

        objectOutput.writeInt(workflowMasterSid);
        objectOutput.writeDouble(priceTolerance);
        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(createdDate);
        objectOutput.writeLong(tempStartDate);
        objectOutput.writeLong(cfpEndDate);
        objectOutput.writeInt(psModelSid);

        if (formulaId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(formulaId);
        }

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        objectOutput.writeDouble(contractPrice);
        objectOutput.writeInt(ifpModelSid);
        objectOutput.writeInt(priceToleranceType);
        objectOutput.writeInt(rebateAmount);
        objectOutput.writeInt(userId);
        objectOutput.writeInt(projectionMasterSid);
        objectOutput.writeLong(contractPriceStartDate);
        objectOutput.writeInt(priceToleranceFrequency);

        if (ifpContractAttachedStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpContractAttachedStatus);
        }

        objectOutput.writeInt(rebatePlanSystemId);

        if (rebatePlanName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rebatePlanName);
        }

        if (calendar == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calendar);
        }

        if (pricingQualifierSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pricingQualifierSid);
        }

        if (tempStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tempStatus);
        }

        objectOutput.writeLong(itemRebateEndDate);
        objectOutput.writeInt(priceToleranceInterval);
        objectOutput.writeLong(itemRebateStartDate);

        if (operation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(operation);
        }

        objectOutput.writeInt(cfpModelSid);
        objectOutput.writeInt(itemStatusSid);
        objectOutput.writeInt(ifpContractSid);
    }
}
