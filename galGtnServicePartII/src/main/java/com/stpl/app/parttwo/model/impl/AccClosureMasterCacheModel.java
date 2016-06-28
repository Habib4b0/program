package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AccClosureMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AccClosureMaster in entity cache.
 *
 * @author
 * @see AccClosureMaster
 * @generated
 */
public class AccClosureMasterCacheModel implements CacheModel<AccClosureMaster>,
    Externalizable {
    public boolean saveFlag;
    public String accountNo;
    public long toDate;
    public int itemMasterSid;
    public String description;
    public String reportName;
    public int rsType;
    public String productIdentifier;
    public long modifiedDate;
    public int workflowStatus;
    public String moduleType;
    public long fromDate;
    public int contractType;
    public int glCompanyMasterSid;
    public long createdDate;
    public int createdBy;
    public int contractMasterSid;
    public String accrualPeriod;
    public String companyGroupSid;
    public int accClosureMasterSid;
    public int rsCategory;
    public int adjustmentType;
    public int modifiedBy;
    public String itemGroupSid;
    public int rebateProgramType;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(51);

        sb.append("{saveFlag=");
        sb.append(saveFlag);
        sb.append(", accountNo=");
        sb.append(accountNo);
        sb.append(", toDate=");
        sb.append(toDate);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", description=");
        sb.append(description);
        sb.append(", reportName=");
        sb.append(reportName);
        sb.append(", rsType=");
        sb.append(rsType);
        sb.append(", productIdentifier=");
        sb.append(productIdentifier);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", workflowStatus=");
        sb.append(workflowStatus);
        sb.append(", moduleType=");
        sb.append(moduleType);
        sb.append(", fromDate=");
        sb.append(fromDate);
        sb.append(", contractType=");
        sb.append(contractType);
        sb.append(", glCompanyMasterSid=");
        sb.append(glCompanyMasterSid);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", accrualPeriod=");
        sb.append(accrualPeriod);
        sb.append(", companyGroupSid=");
        sb.append(companyGroupSid);
        sb.append(", accClosureMasterSid=");
        sb.append(accClosureMasterSid);
        sb.append(", rsCategory=");
        sb.append(rsCategory);
        sb.append(", adjustmentType=");
        sb.append(adjustmentType);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", itemGroupSid=");
        sb.append(itemGroupSid);
        sb.append(", rebateProgramType=");
        sb.append(rebateProgramType);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AccClosureMaster toEntityModel() {
        AccClosureMasterImpl accClosureMasterImpl = new AccClosureMasterImpl();

        accClosureMasterImpl.setSaveFlag(saveFlag);

        if (accountNo == null) {
            accClosureMasterImpl.setAccountNo(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setAccountNo(accountNo);
        }

        if (toDate == Long.MIN_VALUE) {
            accClosureMasterImpl.setToDate(null);
        } else {
            accClosureMasterImpl.setToDate(new Date(toDate));
        }

        accClosureMasterImpl.setItemMasterSid(itemMasterSid);

        if (description == null) {
            accClosureMasterImpl.setDescription(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setDescription(description);
        }

        if (reportName == null) {
            accClosureMasterImpl.setReportName(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setReportName(reportName);
        }

        accClosureMasterImpl.setRsType(rsType);

        if (productIdentifier == null) {
            accClosureMasterImpl.setProductIdentifier(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setProductIdentifier(productIdentifier);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            accClosureMasterImpl.setModifiedDate(null);
        } else {
            accClosureMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        accClosureMasterImpl.setWorkflowStatus(workflowStatus);

        if (moduleType == null) {
            accClosureMasterImpl.setModuleType(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setModuleType(moduleType);
        }

        if (fromDate == Long.MIN_VALUE) {
            accClosureMasterImpl.setFromDate(null);
        } else {
            accClosureMasterImpl.setFromDate(new Date(fromDate));
        }

        accClosureMasterImpl.setContractType(contractType);
        accClosureMasterImpl.setGlCompanyMasterSid(glCompanyMasterSid);

        if (createdDate == Long.MIN_VALUE) {
            accClosureMasterImpl.setCreatedDate(null);
        } else {
            accClosureMasterImpl.setCreatedDate(new Date(createdDate));
        }

        accClosureMasterImpl.setCreatedBy(createdBy);
        accClosureMasterImpl.setContractMasterSid(contractMasterSid);

        if (accrualPeriod == null) {
            accClosureMasterImpl.setAccrualPeriod(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setAccrualPeriod(accrualPeriod);
        }

        if (companyGroupSid == null) {
            accClosureMasterImpl.setCompanyGroupSid(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setCompanyGroupSid(companyGroupSid);
        }

        accClosureMasterImpl.setAccClosureMasterSid(accClosureMasterSid);
        accClosureMasterImpl.setRsCategory(rsCategory);
        accClosureMasterImpl.setAdjustmentType(adjustmentType);
        accClosureMasterImpl.setModifiedBy(modifiedBy);

        if (itemGroupSid == null) {
            accClosureMasterImpl.setItemGroupSid(StringPool.BLANK);
        } else {
            accClosureMasterImpl.setItemGroupSid(itemGroupSid);
        }

        accClosureMasterImpl.setRebateProgramType(rebateProgramType);

        accClosureMasterImpl.resetOriginalValues();

        return accClosureMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        saveFlag = objectInput.readBoolean();
        accountNo = objectInput.readUTF();
        toDate = objectInput.readLong();
        itemMasterSid = objectInput.readInt();
        description = objectInput.readUTF();
        reportName = objectInput.readUTF();
        rsType = objectInput.readInt();
        productIdentifier = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        workflowStatus = objectInput.readInt();
        moduleType = objectInput.readUTF();
        fromDate = objectInput.readLong();
        contractType = objectInput.readInt();
        glCompanyMasterSid = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        contractMasterSid = objectInput.readInt();
        accrualPeriod = objectInput.readUTF();
        companyGroupSid = objectInput.readUTF();
        accClosureMasterSid = objectInput.readInt();
        rsCategory = objectInput.readInt();
        adjustmentType = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        itemGroupSid = objectInput.readUTF();
        rebateProgramType = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(saveFlag);

        if (accountNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountNo);
        }

        objectOutput.writeLong(toDate);
        objectOutput.writeInt(itemMasterSid);

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        if (reportName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reportName);
        }

        objectOutput.writeInt(rsType);

        if (productIdentifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(productIdentifier);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(workflowStatus);

        if (moduleType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(moduleType);
        }

        objectOutput.writeLong(fromDate);
        objectOutput.writeInt(contractType);
        objectOutput.writeInt(glCompanyMasterSid);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(contractMasterSid);

        if (accrualPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accrualPeriod);
        }

        if (companyGroupSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyGroupSid);
        }

        objectOutput.writeInt(accClosureMasterSid);
        objectOutput.writeInt(rsCategory);
        objectOutput.writeInt(adjustmentType);
        objectOutput.writeInt(modifiedBy);

        if (itemGroupSid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemGroupSid);
        }

        objectOutput.writeInt(rebateProgramType);
    }
}
