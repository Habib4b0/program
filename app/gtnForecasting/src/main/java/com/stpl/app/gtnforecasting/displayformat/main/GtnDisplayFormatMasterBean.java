/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.displayformat.main;

import com.stpl.app.gtnforecasting.displayformat.bean.GtnFrameworkDisplayFormatBean;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jayaram.LeelaRam
 */
public final class GtnDisplayFormatMasterBean {

    private final List<GtnFrameworkDisplayFormatBean> displayFormatList = new ArrayList<>();

    public GtnDisplayFormatMasterBean() {
        insertGtnFrameworkDisplayFormatBean();
    }

    public void addDisplayFormatList(GtnFrameworkDisplayFormatBean format) {
        displayFormatList.add(format);
    }

    public void insertGtnFrameworkDisplayFormatBean() {
        String query = " SELECT TABLE_NAME, COLUMN_NAME, SELECTED_COLUMN_NAME FROM dbo.HIERARCHY_DISPLAY_FORMAT ";
        List<Object[]> results = (List<Object[]>)HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (Object[] format : results) {
            GtnFrameworkDisplayFormatBean formatBean = new GtnFrameworkDisplayFormatBean();
            formatBean.setTableName(format[0].toString());
            formatBean.setColumnName(format[1].toString());
            formatBean.setSelectedColumnName(format[2].toString());
            addDisplayFormatList(formatBean);
        }
    }

    public List<GtnFrameworkDisplayFormatBean> getDisplayFormatList() {
        return Collections.unmodifiableList(displayFormatList);
    }
}
