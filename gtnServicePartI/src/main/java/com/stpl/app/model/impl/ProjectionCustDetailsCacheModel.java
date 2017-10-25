package com.stpl.app.model.impl;

import com.stpl.app.model.ProjectionCustDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectionCustDetails in entity cache.
 *
 * @author
 * @see ProjectionCustDetails
 * @generated
 */
public class ProjectionCustDetailsCacheModel implements CacheModel<ProjectionCustDetails>,
    Externalizable {
    public String contractName;
    public String customerName;
    public int customerDetailsId;
    public String costCenter;
    public String customerAlias;
    public String subLedgerCode;
    public int projectionId;
    public String marketType;
    public String contractNo;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{contractName=");
        sb.append(contractName);
        sb.append(", customerName=");
        sb.append(customerName);
        sb.append(", customerDetailsId=");
        sb.append(customerDetailsId);
        sb.append(", costCenter=");
        sb.append(costCenter);
        sb.append(", customerAlias=");
        sb.append(customerAlias);
        sb.append(", subLedgerCode=");
        sb.append(subLedgerCode);
        sb.append(", projectionId=");
        sb.append(projectionId);
        sb.append(", marketType=");
        sb.append(marketType);
        sb.append(", contractNo=");
        sb.append(contractNo);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProjectionCustDetails toEntityModel() {
        ProjectionCustDetailsImpl projectionCustDetailsImpl = new ProjectionCustDetailsImpl();

        if (contractName == null) {
            projectionCustDetailsImpl.setContractName(StringPool.BLANK);
        } else {
            projectionCustDetailsImpl.setContractName(contractName);
        }

        if (customerName == null) {
            projectionCustDetailsImpl.setCustomerName(StringPool.BLANK);
        } else {
            projectionCustDetailsImpl.setCustomerName(customerName);
        }

        projectionCustDetailsImpl.setCustomerDetailsId(customerDetailsId);

        if (costCenter == null) {
            projectionCustDetailsImpl.setCostCenter(StringPool.BLANK);
        } else {
            projectionCustDetailsImpl.setCostCenter(costCenter);
        }

        if (customerAlias == null) {
            projectionCustDetailsImpl.setCustomerAlias(StringPool.BLANK);
        } else {
            projectionCustDetailsImpl.setCustomerAlias(customerAlias);
        }

        if (subLedgerCode == null) {
            projectionCustDetailsImpl.setSubLedgerCode(StringPool.BLANK);
        } else {
            projectionCustDetailsImpl.setSubLedgerCode(subLedgerCode);
        }

        projectionCustDetailsImpl.setProjectionId(projectionId);

        if (marketType == null) {
            projectionCustDetailsImpl.setMarketType(StringPool.BLANK);
        } else {
            projectionCustDetailsImpl.setMarketType(marketType);
        }

        if (contractNo == null) {
            projectionCustDetailsImpl.setContractNo(StringPool.BLANK);
        } else {
            projectionCustDetailsImpl.setContractNo(contractNo);
        }

        projectionCustDetailsImpl.resetOriginalValues();

        return projectionCustDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        contractName = objectInput.readUTF();
        customerName = objectInput.readUTF();
        customerDetailsId = objectInput.readInt();
        costCenter = objectInput.readUTF();
        customerAlias = objectInput.readUTF();
        subLedgerCode = objectInput.readUTF();
        projectionId = objectInput.readInt();
        marketType = objectInput.readUTF();
        contractNo = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (contractName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractName);
        }

        if (customerName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerName);
        }

        objectOutput.writeInt(customerDetailsId);

        if (costCenter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(costCenter);
        }

        if (customerAlias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(customerAlias);
        }

        if (subLedgerCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subLedgerCode);
        }

        objectOutput.writeInt(projectionId);

        if (marketType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketType);
        }

        if (contractNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractNo);
        }
    }
}
