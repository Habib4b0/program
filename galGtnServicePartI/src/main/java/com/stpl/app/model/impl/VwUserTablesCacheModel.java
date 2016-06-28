package com.stpl.app.model.impl;

import com.stpl.app.model.VwUserTables;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VwUserTables in entity cache.
 *
 * @author
 * @see VwUserTables
 * @generated
 */
public class VwUserTablesCacheModel implements CacheModel<VwUserTables>,
    Externalizable {
    public int uniqueId;
    public String tableName;
    public String columnName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{uniqueId=");
        sb.append(uniqueId);
        sb.append(", tableName=");
        sb.append(tableName);
        sb.append(", columnName=");
        sb.append(columnName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwUserTables toEntityModel() {
        VwUserTablesImpl vwUserTablesImpl = new VwUserTablesImpl();

        vwUserTablesImpl.setUniqueId(uniqueId);

        if (tableName == null) {
            vwUserTablesImpl.setTableName(StringPool.BLANK);
        } else {
            vwUserTablesImpl.setTableName(tableName);
        }

        if (columnName == null) {
            vwUserTablesImpl.setColumnName(StringPool.BLANK);
        } else {
            vwUserTablesImpl.setColumnName(columnName);
        }

        vwUserTablesImpl.resetOriginalValues();

        return vwUserTablesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        uniqueId = objectInput.readInt();
        tableName = objectInput.readUTF();
        columnName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(uniqueId);

        if (tableName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(tableName);
        }

        if (columnName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(columnName);
        }
    }
}
