package com.stpl.app.model.impl;

import com.stpl.app.model.ProjectionProdDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectionProdDetails in entity cache.
 *
 * @author
 * @see ProjectionProdDetails
 * @generated
 */
public class ProjectionProdDetailsCacheModel implements CacheModel<ProjectionProdDetails>,
    Externalizable {
    public String productName;
    public String costCenter;
    public String productNo;
    public String subLedgerCode;
    public int productDetailsId;
    public String brandName;
    public int projectionId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{productName=");
        sb.append(productName);
        sb.append(", costCenter=");
        sb.append(costCenter);
        sb.append(", productNo=");
        sb.append(productNo);
        sb.append(", subLedgerCode=");
        sb.append(subLedgerCode);
        sb.append(", productDetailsId=");
        sb.append(productDetailsId);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", projectionId=");
        sb.append(projectionId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProjectionProdDetails toEntityModel() {
        ProjectionProdDetailsImpl projectionProdDetailsImpl = new ProjectionProdDetailsImpl();

        if (productName == null) {
            projectionProdDetailsImpl.setProductName(StringPool.BLANK);
        } else {
            projectionProdDetailsImpl.setProductName(productName);
        }

        if (costCenter == null) {
            projectionProdDetailsImpl.setCostCenter(StringPool.BLANK);
        } else {
            projectionProdDetailsImpl.setCostCenter(costCenter);
        }

        if (productNo == null) {
            projectionProdDetailsImpl.setProductNo(StringPool.BLANK);
        } else {
            projectionProdDetailsImpl.setProductNo(productNo);
        }

        if (subLedgerCode == null) {
            projectionProdDetailsImpl.setSubLedgerCode(StringPool.BLANK);
        } else {
            projectionProdDetailsImpl.setSubLedgerCode(subLedgerCode);
        }

        projectionProdDetailsImpl.setProductDetailsId(productDetailsId);

        if (brandName == null) {
            projectionProdDetailsImpl.setBrandName(StringPool.BLANK);
        } else {
            projectionProdDetailsImpl.setBrandName(brandName);
        }

        projectionProdDetailsImpl.setProjectionId(projectionId);

        projectionProdDetailsImpl.resetOriginalValues();

        return projectionProdDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        productName = objectInput.readUTF();
        costCenter = objectInput.readUTF();
        productNo = objectInput.readUTF();
        subLedgerCode = objectInput.readUTF();
        productDetailsId = objectInput.readInt();
        brandName = objectInput.readUTF();
        projectionId = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (productName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(productName);
        }

        if (costCenter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(costCenter);
        }

        if (productNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(productNo);
        }

        if (subLedgerCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(subLedgerCode);
        }

        objectOutput.writeInt(productDetailsId);

        if (brandName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandName);
        }

        objectOutput.writeInt(projectionId);
    }
}
