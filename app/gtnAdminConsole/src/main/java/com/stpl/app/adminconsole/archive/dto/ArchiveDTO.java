package com.stpl.app.adminconsole.archive.dto;

import java.io.Serializable;

import com.stpl.app.adminconsole.util.ConstantsUtils;

public class ArchiveDTO implements Serializable {

    private static final long serialVersionUID = -3428912577454893783L;

    private String table = ConstantsUtils.EMPTY;

    private String column = ConstantsUtils.EMPTY;

    private String tableName = ConstantsUtils.EMPTY;

    private String fieldName = ConstantsUtils.EMPTY;

    private String value = ConstantsUtils.EMPTY;

    public String getTable() {
        return table;
    }

    public void setTable(final String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(final String column) {
        this.column = column;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
