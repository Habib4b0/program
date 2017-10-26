package com.stpl.app.model.impl;

import com.stpl.app.model.TransactionModuleMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TransactionModuleMaster in entity cache.
 *
 * @author
 * @see TransactionModuleMaster
 * @generated
 */
public class TransactionModuleMasterCacheModel implements CacheModel<TransactionModuleMaster>,
    Externalizable {
    public int transactionModuleMasterSid;
    public String invalidTableName;
    public String tableName;
    public String moduleName;
    public String tabName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{transactionModuleMasterSid=");
        sb.append(transactionModuleMasterSid);
        sb.append(", invalidTableName=");
        sb.append(invalidTableName);
        sb.append(", tableName=");
        sb.append(tableName);
        sb.append(", moduleName=");
        sb.append(moduleName);
        sb.append(", tabName=");
        sb.append(tabName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public TransactionModuleMaster toEntityModel() {
        TransactionModuleMasterImpl transactionModuleMasterImpl = new TransactionModuleMasterImpl();

        transactionModuleMasterImpl.setTransactionModuleMasterSid(transactionModuleMasterSid);

        if (invalidTableName == null) {
            transactionModuleMasterImpl.setInvalidTableName(StringPool.BLANK);
        } else {
            transactionModuleMasterImpl.setInvalidTableName(invalidTableName);
        }

        if (tableName == null) {
            transactionModuleMasterImpl.setTableName(StringPool.BLANK);
        } else {
            transactionModuleMasterImpl.setTableName(tableName);
        }

        if (moduleName == null) {
            transactionModuleMasterImpl.setModuleName(StringPool.BLANK);
        } else {
            transactionModuleMasterImpl.setModuleName(moduleName);
        }

        if (tabName == null) {
            transactionModuleMasterImpl.setTabName(StringPool.BLANK);
        } else {
            transactionModuleMasterImpl.setTabName(tabName);
        }

        transactionModuleMasterImpl.resetOriginalValues();

        return transactionModuleMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        transactionModuleMasterSid = objectInput.readInt();
        invalidTableName = objectInput.readUTF();
        tableName = objectInput.readUTF();
        moduleName = objectInput.readUTF();
        tabName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(transactionModuleMasterSid);

        if (invalidTableName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(invalidTableName);
        }

        if (tableName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tableName);
        }

        if (moduleName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(moduleName);
        }

        if (tabName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tabName);
        }
    }
}
