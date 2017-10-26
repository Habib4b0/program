package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CustomerGtsActual;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CustomerGtsActual in entity cache.
 *
 * @author
 * @see CustomerGtsActual
 * @generated
 */
public class CustomerGtsActualCacheModel implements CacheModel<CustomerGtsActual>,
    Externalizable {
    public String parentAccountId;
    public String contractId;
    public String accountId;
    public int customerGtsActualSid;
    public long orderReceivedDate;
    public String itemId;
    public long modifiedDate;
    public double amount;
    public String orderNumber;
    public String organizationKey;
    public long invoiceDate;
    public int customerGtsActualIntfId;
    public long createdDate;
    public String createdBy;
    public String source;
    public String batchId;
    public String salesId;
    public String itemUom;
    public String inboundStatus;
    public String modifiedBy;
    public String invoiceNumber;
    public String lotNo;
    public String invoiceLineNumber;
    public double quantity;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(49);

        sb.append("{parentAccountId=");
        sb.append(parentAccountId);
        sb.append(", contractId=");
        sb.append(contractId);
        sb.append(", accountId=");
        sb.append(accountId);
        sb.append(", customerGtsActualSid=");
        sb.append(customerGtsActualSid);
        sb.append(", orderReceivedDate=");
        sb.append(orderReceivedDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", orderNumber=");
        sb.append(orderNumber);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", invoiceDate=");
        sb.append(invoiceDate);
        sb.append(", customerGtsActualIntfId=");
        sb.append(customerGtsActualIntfId);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", salesId=");
        sb.append(salesId);
        sb.append(", itemUom=");
        sb.append(itemUom);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", invoiceNumber=");
        sb.append(invoiceNumber);
        sb.append(", lotNo=");
        sb.append(lotNo);
        sb.append(", invoiceLineNumber=");
        sb.append(invoiceLineNumber);
        sb.append(", quantity=");
        sb.append(quantity);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CustomerGtsActual toEntityModel() {
        CustomerGtsActualImpl customerGtsActualImpl = new CustomerGtsActualImpl();

        if (parentAccountId == null) {
            customerGtsActualImpl.setParentAccountId(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setParentAccountId(parentAccountId);
        }

        if (contractId == null) {
            customerGtsActualImpl.setContractId(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setContractId(contractId);
        }

        if (accountId == null) {
            customerGtsActualImpl.setAccountId(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setAccountId(accountId);
        }

        customerGtsActualImpl.setCustomerGtsActualSid(customerGtsActualSid);

        if (orderReceivedDate == Long.MIN_VALUE) {
            customerGtsActualImpl.setOrderReceivedDate(null);
        } else {
            customerGtsActualImpl.setOrderReceivedDate(new Date(
                    orderReceivedDate));
        }

        if (itemId == null) {
            customerGtsActualImpl.setItemId(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            customerGtsActualImpl.setModifiedDate(null);
        } else {
            customerGtsActualImpl.setModifiedDate(new Date(modifiedDate));
        }

        customerGtsActualImpl.setAmount(amount);

        if (orderNumber == null) {
            customerGtsActualImpl.setOrderNumber(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setOrderNumber(orderNumber);
        }

        if (organizationKey == null) {
            customerGtsActualImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setOrganizationKey(organizationKey);
        }

        if (invoiceDate == Long.MIN_VALUE) {
            customerGtsActualImpl.setInvoiceDate(null);
        } else {
            customerGtsActualImpl.setInvoiceDate(new Date(invoiceDate));
        }

        customerGtsActualImpl.setCustomerGtsActualIntfId(customerGtsActualIntfId);

        if (createdDate == Long.MIN_VALUE) {
            customerGtsActualImpl.setCreatedDate(null);
        } else {
            customerGtsActualImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            customerGtsActualImpl.setCreatedBy(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setCreatedBy(createdBy);
        }

        if (source == null) {
            customerGtsActualImpl.setSource(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setSource(source);
        }

        if (batchId == null) {
            customerGtsActualImpl.setBatchId(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setBatchId(batchId);
        }

        if (salesId == null) {
            customerGtsActualImpl.setSalesId(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setSalesId(salesId);
        }

        if (itemUom == null) {
            customerGtsActualImpl.setItemUom(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setItemUom(itemUom);
        }

        if (inboundStatus == null) {
            customerGtsActualImpl.setInboundStatus(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setInboundStatus(inboundStatus);
        }

        if (modifiedBy == null) {
            customerGtsActualImpl.setModifiedBy(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setModifiedBy(modifiedBy);
        }

        if (invoiceNumber == null) {
            customerGtsActualImpl.setInvoiceNumber(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setInvoiceNumber(invoiceNumber);
        }

        if (lotNo == null) {
            customerGtsActualImpl.setLotNo(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setLotNo(lotNo);
        }

        if (invoiceLineNumber == null) {
            customerGtsActualImpl.setInvoiceLineNumber(StringPool.BLANK);
        } else {
            customerGtsActualImpl.setInvoiceLineNumber(invoiceLineNumber);
        }

        customerGtsActualImpl.setQuantity(quantity);

        customerGtsActualImpl.resetOriginalValues();

        return customerGtsActualImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        parentAccountId = objectInput.readUTF();
        contractId = objectInput.readUTF();
        accountId = objectInput.readUTF();
        customerGtsActualSid = objectInput.readInt();
        orderReceivedDate = objectInput.readLong();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        amount = objectInput.readDouble();
        orderNumber = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        invoiceDate = objectInput.readLong();
        customerGtsActualIntfId = objectInput.readInt();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        source = objectInput.readUTF();
        batchId = objectInput.readUTF();
        salesId = objectInput.readUTF();
        itemUom = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        invoiceNumber = objectInput.readUTF();
        lotNo = objectInput.readUTF();
        invoiceLineNumber = objectInput.readUTF();
        quantity = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (parentAccountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(parentAccountId);
        }

        if (contractId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractId);
        }

        if (accountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(accountId);
        }

        objectOutput.writeInt(customerGtsActualSid);
        objectOutput.writeLong(orderReceivedDate);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeDouble(amount);

        if (orderNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(orderNumber);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        objectOutput.writeLong(invoiceDate);
        objectOutput.writeInt(customerGtsActualIntfId);
        objectOutput.writeLong(createdDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (salesId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesId);
        }

        if (itemUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemUom);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (invoiceNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceNumber);
        }

        if (lotNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lotNo);
        }

        if (invoiceLineNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invoiceLineNumber);
        }

        objectOutput.writeDouble(quantity);
    }
}
