package com.stpl.app.model.impl;

import com.stpl.app.model.ProjectionMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProjectionMaster in entity cache.
 *
 * @author
 * @see ProjectionMaster
 * @generated
 */
public class ProjectionMasterCacheModel implements CacheModel<ProjectionMaster>,
    Externalizable {
    public int productHierarchyLevel;
    public boolean saveFlag;
    public String projectionName;
    public long toDate;
    public int projectionMasterSid;
    public String forecastingType;
    public int productHierVersionNo;
    public int customerHierVersionNo;
    public long modifiedDate;
    public int customerHierarchyLevel;
    public long fromDate;
    public String productHierarchySid;
    public long createdDate;
    public int createdBy;
    public String customerHierarchySid;
    public String companyGroupSid;
    public boolean brandType;
    public int modifiedBy;
    public String projectionDescription;
    public String isApproved;
    public String itemGroupSid;
    public String companyMasterSid;
    public int customerHierarchyInnerLevel;
    public int productHierarchyInnerLevel;
    public String custRelationshipBuilderSid;
    public String prodRelationshipBuilderSid;
    public int discountType;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(55);

        sb.append("{productHierarchyLevel=");
        sb.append(productHierarchyLevel);
        sb.append(", saveFlag=");
        sb.append(saveFlag);
        sb.append(", projectionName=");
        sb.append(projectionName);
        sb.append(", toDate=");
        sb.append(toDate);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", forecastingType=");
        sb.append(forecastingType);
        sb.append(", productHierVersionNo=");
        sb.append(productHierVersionNo);
        sb.append(", customerHierVersionNo=");
        sb.append(customerHierVersionNo);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", customerHierarchyLevel=");
        sb.append(customerHierarchyLevel);
        sb.append(", fromDate=");
        sb.append(fromDate);
        sb.append(", productHierarchySid=");
        sb.append(productHierarchySid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", customerHierarchySid=");
        sb.append(customerHierarchySid);
        sb.append(", companyGroupSid=");
        sb.append(companyGroupSid);
        sb.append(", brandType=");
        sb.append(brandType);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", projectionDescription=");
        sb.append(projectionDescription);
        sb.append(", isApproved=");
        sb.append(isApproved);
        sb.append(", itemGroupSid=");
        sb.append(itemGroupSid);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append(", customerHierarchyInnerLevel=");
        sb.append(customerHierarchyInnerLevel);
        sb.append(", productHierarchyInnerLevel=");
        sb.append(productHierarchyInnerLevel);
        sb.append(", custRelationshipBuilderSid=");
        sb.append(custRelationshipBuilderSid);
        sb.append(", prodRelationshipBuilderSid=");
        sb.append(prodRelationshipBuilderSid);
        sb.append(", discountType=");
        sb.append(discountType);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProjectionMaster toEntityModel() {
        ProjectionMasterImpl projectionMasterImpl = new ProjectionMasterImpl();

        projectionMasterImpl.setProductHierarchyLevel(productHierarchyLevel);
        projectionMasterImpl.setSaveFlag(saveFlag);

        if (projectionName == null) {
            projectionMasterImpl.setProjectionName(StringPool.BLANK);
        } else {
            projectionMasterImpl.setProjectionName(projectionName);
        }

        if (toDate == Long.MIN_VALUE) {
            projectionMasterImpl.setToDate(null);
        } else {
            projectionMasterImpl.setToDate(new Date(toDate));
        }

        projectionMasterImpl.setProjectionMasterSid(projectionMasterSid);

        if (forecastingType == null) {
            projectionMasterImpl.setForecastingType(StringPool.BLANK);
        } else {
            projectionMasterImpl.setForecastingType(forecastingType);
        }

        projectionMasterImpl.setProductHierVersionNo(productHierVersionNo);
        projectionMasterImpl.setCustomerHierVersionNo(customerHierVersionNo);

        if (modifiedDate == Long.MIN_VALUE) {
            projectionMasterImpl.setModifiedDate(null);
        } else {
            projectionMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        projectionMasterImpl.setCustomerHierarchyLevel(customerHierarchyLevel);

        if (fromDate == Long.MIN_VALUE) {
            projectionMasterImpl.setFromDate(null);
        } else {
            projectionMasterImpl.setFromDate(new Date(fromDate));
        }

        if (productHierarchySid == null) {
            projectionMasterImpl.setProductHierarchySid(StringPool.BLANK);
        } else {
            projectionMasterImpl.setProductHierarchySid(productHierarchySid);
        }

        if (createdDate == Long.MIN_VALUE) {
            projectionMasterImpl.setCreatedDate(null);
        } else {
            projectionMasterImpl.setCreatedDate(new Date(createdDate));
        }

        projectionMasterImpl.setCreatedBy(createdBy);

        if (customerHierarchySid == null) {
            projectionMasterImpl.setCustomerHierarchySid(StringPool.BLANK);
        } else {
            projectionMasterImpl.setCustomerHierarchySid(customerHierarchySid);
        }

        if (companyGroupSid == null) {
            projectionMasterImpl.setCompanyGroupSid(StringPool.BLANK);
        } else {
            projectionMasterImpl.setCompanyGroupSid(companyGroupSid);
        }

        projectionMasterImpl.setBrandType(brandType);
        projectionMasterImpl.setModifiedBy(modifiedBy);

        if (projectionDescription == null) {
            projectionMasterImpl.setProjectionDescription(StringPool.BLANK);
        } else {
            projectionMasterImpl.setProjectionDescription(projectionDescription);
        }

        if (isApproved == null) {
            projectionMasterImpl.setIsApproved(StringPool.BLANK);
        } else {
            projectionMasterImpl.setIsApproved(isApproved);
        }

        if (itemGroupSid == null) {
            projectionMasterImpl.setItemGroupSid(StringPool.BLANK);
        } else {
            projectionMasterImpl.setItemGroupSid(itemGroupSid);
        }

        if (companyMasterSid == null) {
            projectionMasterImpl.setCompanyMasterSid(StringPool.BLANK);
        } else {
            projectionMasterImpl.setCompanyMasterSid(companyMasterSid);
        }

        projectionMasterImpl.setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
        projectionMasterImpl.setProductHierarchyInnerLevel(productHierarchyInnerLevel);

        if (custRelationshipBuilderSid == null) {
            projectionMasterImpl.setCustRelationshipBuilderSid(StringPool.BLANK);
        } else {
            projectionMasterImpl.setCustRelationshipBuilderSid(custRelationshipBuilderSid);
        }

        if (prodRelationshipBuilderSid == null) {
            projectionMasterImpl.setProdRelationshipBuilderSid(StringPool.BLANK);
        } else {
            projectionMasterImpl.setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
        }

        projectionMasterImpl.setDiscountType(discountType);

        projectionMasterImpl.resetOriginalValues();

        return projectionMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        productHierarchyLevel = objectInput.readInt();
        saveFlag = objectInput.readBoolean();
        projectionName = objectInput.readUTF();
        toDate = objectInput.readLong();
        projectionMasterSid = objectInput.readInt();
        forecastingType = objectInput.readUTF();
        productHierVersionNo = objectInput.readInt();
        customerHierVersionNo = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        customerHierarchyLevel = objectInput.readInt();
        fromDate = objectInput.readLong();
        productHierarchySid = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        customerHierarchySid = objectInput.readUTF();
        companyGroupSid = objectInput.readUTF();
        brandType = objectInput.readBoolean();
        modifiedBy = objectInput.readInt();
        projectionDescription = objectInput.readUTF();
        isApproved = objectInput.readUTF();
        itemGroupSid = objectInput.readUTF();
        companyMasterSid = objectInput.readUTF();
        customerHierarchyInnerLevel = objectInput.readInt();
        productHierarchyInnerLevel = objectInput.readInt();
        custRelationshipBuilderSid = objectInput.readUTF();
        prodRelationshipBuilderSid = objectInput.readUTF();
        discountType = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(productHierarchyLevel);
        objectOutput.writeBoolean(saveFlag);

        if (projectionName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(projectionName);
        }

        objectOutput.writeLong(toDate);
        objectOutput.writeInt(projectionMasterSid);

        if (forecastingType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastingType);
        }

        objectOutput.writeInt(productHierVersionNo);
        objectOutput.writeInt(customerHierVersionNo);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(customerHierarchyLevel);
        objectOutput.writeLong(fromDate);

        if (productHierarchySid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(productHierarchySid);
        }

        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);

        if (customerHierarchySid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerHierarchySid);
        }

        if (companyGroupSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyGroupSid);
        }

        objectOutput.writeBoolean(brandType);
        objectOutput.writeInt(modifiedBy);

        if (projectionDescription == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(projectionDescription);
        }

        if (isApproved == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isApproved);
        }

        if (itemGroupSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemGroupSid);
        }

        if (companyMasterSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyMasterSid);
        }

        objectOutput.writeInt(customerHierarchyInnerLevel);
        objectOutput.writeInt(productHierarchyInnerLevel);

        if (custRelationshipBuilderSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(custRelationshipBuilderSid);
        }

        if (prodRelationshipBuilderSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(prodRelationshipBuilderSid);
        }

        objectOutput.writeInt(discountType);
    }
}
