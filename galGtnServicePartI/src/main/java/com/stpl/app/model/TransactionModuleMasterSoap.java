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
public class TransactionModuleMasterSoap implements Serializable {
    private int _transactionModuleMasterSid;
    private String _invalidTableName;
    private String _tableName;
    private String _moduleName;
    private String _tabName;

    public TransactionModuleMasterSoap() {
    }

    public static TransactionModuleMasterSoap toSoapModel(
        TransactionModuleMaster model) {
        TransactionModuleMasterSoap soapModel = new TransactionModuleMasterSoap();

        soapModel.setTransactionModuleMasterSid(model.getTransactionModuleMasterSid());
        soapModel.setInvalidTableName(model.getInvalidTableName());
        soapModel.setTableName(model.getTableName());
        soapModel.setModuleName(model.getModuleName());
        soapModel.setTabName(model.getTabName());

        return soapModel;
    }

    public static TransactionModuleMasterSoap[] toSoapModels(
        TransactionModuleMaster[] models) {
        TransactionModuleMasterSoap[] soapModels = new TransactionModuleMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static TransactionModuleMasterSoap[][] toSoapModels(
        TransactionModuleMaster[][] models) {
        TransactionModuleMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new TransactionModuleMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new TransactionModuleMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static TransactionModuleMasterSoap[] toSoapModels(
        List<TransactionModuleMaster> models) {
        List<TransactionModuleMasterSoap> soapModels = new ArrayList<TransactionModuleMasterSoap>(models.size());

        for (TransactionModuleMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new TransactionModuleMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _transactionModuleMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setTransactionModuleMasterSid(pk);
    }

    public int getTransactionModuleMasterSid() {
        return _transactionModuleMasterSid;
    }

    public void setTransactionModuleMasterSid(int transactionModuleMasterSid) {
        _transactionModuleMasterSid = transactionModuleMasterSid;
    }

    public String getInvalidTableName() {
        return _invalidTableName;
    }

    public void setInvalidTableName(String invalidTableName) {
        _invalidTableName = invalidTableName;
    }

    public String getTableName() {
        return _tableName;
    }

    public void setTableName(String tableName) {
        _tableName = tableName;
    }

    public String getModuleName() {
        return _moduleName;
    }

    public void setModuleName(String moduleName) {
        _moduleName = moduleName;
    }

    public String getTabName() {
        return _tabName;
    }

    public void setTabName(String tabName) {
        _tabName = tabName;
    }
}
