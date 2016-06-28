package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class VwUserTablesSoap implements Serializable {
    private int _uniqueId;
    private String _tableName;
    private String _columnName;

    public VwUserTablesSoap() {
    }

    public static VwUserTablesSoap toSoapModel(VwUserTables model) {
        VwUserTablesSoap soapModel = new VwUserTablesSoap();

        soapModel.setUniqueId(model.getUniqueId());
        soapModel.setTableName(model.getTableName());
        soapModel.setColumnName(model.getColumnName());

        return soapModel;
    }

    public static VwUserTablesSoap[] toSoapModels(VwUserTables[] models) {
        VwUserTablesSoap[] soapModels = new VwUserTablesSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static VwUserTablesSoap[][] toSoapModels(VwUserTables[][] models) {
        VwUserTablesSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new VwUserTablesSoap[models.length][models[0].length];
        } else {
            soapModels = new VwUserTablesSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static VwUserTablesSoap[] toSoapModels(List<VwUserTables> models) {
        List<VwUserTablesSoap> soapModels = new ArrayList<VwUserTablesSoap>(models.size());

        for (VwUserTables model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new VwUserTablesSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _uniqueId;
    }

    public void setPrimaryKey(int pk) {
        setUniqueId(pk);
    }

    public int getUniqueId() {
        return _uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        _uniqueId = uniqueId;
    }

    public String getTableName() {
        return _tableName;
    }

    public void setTableName(String tableName) {
        _tableName = tableName;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }
}
