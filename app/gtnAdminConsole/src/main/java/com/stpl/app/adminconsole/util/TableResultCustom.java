/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

/**
 *
 * @author nimisha.rakesh
 */
public class TableResultCustom {

    private Object[] objResult;

    private String[] objResultHeader;

    public Object[] getObjResult() {
        final Object[] copyValue = objResult;

        return copyValue;

    }

    public void setObjResult(final Object... objResult) {
        this.objResult = objResult;
    }

    public String[] getObjResultHeader() {
        final String[] cpyResultHeader = objResultHeader;

        return cpyResultHeader;

    }

    public void setObjResultHeader(final String... objResultHeader) {
        this.objResultHeader = objResultHeader;
    }

}
