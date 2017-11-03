package com.stpl.app.gtnforecasting.displayformat.bean;

import org.apache.commons.lang.StringUtils;

public class GtnFrameworkDisplayFormatBean {

    private String tableName = StringUtils.EMPTY;
    private String columnName = StringUtils.EMPTY;
    private String selectedColumnName = StringUtils.EMPTY;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSelectedColumnName() {
        return selectedColumnName;
    }

    public void setSelectedColumnName(String selectedColumnName) {
        this.selectedColumnName = selectedColumnName;
    }

}
