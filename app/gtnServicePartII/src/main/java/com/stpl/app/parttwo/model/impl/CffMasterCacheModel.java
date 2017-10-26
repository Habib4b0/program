package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CffMaster in entity cache.
 *
 * @author
 * @see CffMaster
 * @generated
 */
public class CffMasterCacheModel implements CacheModel<CffMaster>,
    Externalizable {
    public int productHierarchyLevel;
    public long activeFromDate;
    public int cffType;
    public boolean cffOfficial;
    public int cffMasterSid;
    public int productHierVersionNo;
    public long activeToDate;
    public int customerHierVersionNo;
    public long modifiedDate;
    public int customerHierarchyLevel;
    public int productHierarchySid;
    public String cffName;
    public int customerHierarchyInnerLevel;
    public long createdDate;
    public int createdBy;
    public int customerHierarchySid;
    public int companyGroupSid;
    public int prodRelationshipBuilderSid;
    public int modifiedBy;
    public String inboundStatus;
    public int productHierarchyInnerLevel;
    public int itemGroupSid;
    public int custRelationshipBuilderSid;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(49);

        sb.append("{productHierarchyLevel=");
        sb.append(productHierarchyLevel);
        sb.append(", activeFromDate=");
        sb.append(activeFromDate);
        sb.append(", cffType=");
        sb.append(cffType);
        sb.append(", cffOfficial=");
        sb.append(cffOfficial);
        sb.append(", cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", productHierVersionNo=");
        sb.append(productHierVersionNo);
        sb.append(", activeToDate=");
        sb.append(activeToDate);
        sb.append(", customerHierVersionNo=");
        sb.append(customerHierVersionNo);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", customerHierarchyLevel=");
        sb.append(customerHierarchyLevel);
        sb.append(", productHierarchySid=");
        sb.append(productHierarchySid);
        sb.append(", cffName=");
        sb.append(cffName);
        sb.append(", customerHierarchyInnerLevel=");
        sb.append(customerHierarchyInnerLevel);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", customerHierarchySid=");
        sb.append(customerHierarchySid);
        sb.append(", companyGroupSid=");
        sb.append(companyGroupSid);
        sb.append(", prodRelationshipBuilderSid=");
        sb.append(prodRelationshipBuilderSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", productHierarchyInnerLevel=");
        sb.append(productHierarchyInnerLevel);
        sb.append(", itemGroupSid=");
        sb.append(itemGroupSid);
        sb.append(", custRelationshipBuilderSid=");
        sb.append(custRelationshipBuilderSid);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffMaster toEntityModel() {
        CffMasterImpl cffMasterImpl = new CffMasterImpl();

        cffMasterImpl.setProductHierarchyLevel(productHierarchyLevel);

        if (activeFromDate == Long.MIN_VALUE) {
            cffMasterImpl.setActiveFromDate(null);
        } else {
            cffMasterImpl.setActiveFromDate(new Date(activeFromDate));
        }

        cffMasterImpl.setCffType(cffType);
        cffMasterImpl.setCffOfficial(cffOfficial);
        cffMasterImpl.setCffMasterSid(cffMasterSid);
        cffMasterImpl.setProductHierVersionNo(productHierVersionNo);

        if (activeToDate == Long.MIN_VALUE) {
            cffMasterImpl.setActiveToDate(null);
        } else {
            cffMasterImpl.setActiveToDate(new Date(activeToDate));
        }

        cffMasterImpl.setCustomerHierVersionNo(customerHierVersionNo);

        if (modifiedDate == Long.MIN_VALUE) {
            cffMasterImpl.setModifiedDate(null);
        } else {
            cffMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        cffMasterImpl.setCustomerHierarchyLevel(customerHierarchyLevel);
        cffMasterImpl.setProductHierarchySid(productHierarchySid);

        if (cffName == null) {
            cffMasterImpl.setCffName(StringPool.BLANK);
        } else {
            cffMasterImpl.setCffName(cffName);
        }

        cffMasterImpl.setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);

        if (createdDate == Long.MIN_VALUE) {
            cffMasterImpl.setCreatedDate(null);
        } else {
            cffMasterImpl.setCreatedDate(new Date(createdDate));
        }

        cffMasterImpl.setCreatedBy(createdBy);
        cffMasterImpl.setCustomerHierarchySid(customerHierarchySid);
        cffMasterImpl.setCompanyGroupSid(companyGroupSid);
        cffMasterImpl.setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
        cffMasterImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            cffMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            cffMasterImpl.setInboundStatus(inboundStatus);
        }

        cffMasterImpl.setProductHierarchyInnerLevel(productHierarchyInnerLevel);
        cffMasterImpl.setItemGroupSid(itemGroupSid);
        cffMasterImpl.setCustRelationshipBuilderSid(custRelationshipBuilderSid);
        cffMasterImpl.setCompanyMasterSid(companyMasterSid);

        cffMasterImpl.resetOriginalValues();

        return cffMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        productHierarchyLevel = objectInput.readInt();
        activeFromDate = objectInput.readLong();
        cffType = objectInput.readInt();
        cffOfficial = objectInput.readBoolean();
        cffMasterSid = objectInput.readInt();
        productHierVersionNo = objectInput.readInt();
        activeToDate = objectInput.readLong();
        customerHierVersionNo = objectInput.readInt();
        modifiedDate = objectInput.readLong();
        customerHierarchyLevel = objectInput.readInt();
        productHierarchySid = objectInput.readInt();
        cffName = objectInput.readUTF();
        customerHierarchyInnerLevel = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        customerHierarchySid = objectInput.readInt();
        companyGroupSid = objectInput.readInt();
        prodRelationshipBuilderSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        productHierarchyInnerLevel = objectInput.readInt();
        itemGroupSid = objectInput.readInt();
        custRelationshipBuilderSid = objectInput.readInt();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(productHierarchyLevel);
        objectOutput.writeLong(activeFromDate);
        objectOutput.writeInt(cffType);
        objectOutput.writeBoolean(cffOfficial);
        objectOutput.writeInt(cffMasterSid);
        objectOutput.writeInt(productHierVersionNo);
        objectOutput.writeLong(activeToDate);
        objectOutput.writeInt(customerHierVersionNo);
        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(customerHierarchyLevel);
        objectOutput.writeInt(productHierarchySid);

        if (cffName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cffName);
        }

        objectOutput.writeInt(customerHierarchyInnerLevel);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(customerHierarchySid);
        objectOutput.writeInt(companyGroupSid);
        objectOutput.writeInt(prodRelationshipBuilderSid);
        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(productHierarchyInnerLevel);
        objectOutput.writeInt(itemGroupSid);
        objectOutput.writeInt(custRelationshipBuilderSid);
        objectOutput.writeInt(companyMasterSid);
    }
}
