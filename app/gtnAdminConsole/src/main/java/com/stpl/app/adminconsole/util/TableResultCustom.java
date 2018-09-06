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
    
    public TableResultCustom(){
    	super();
    }

    public Object[] getObjResult() {

        return objResult;

    }

    public void setObjResult(final Object... objResult) {
        this.objResult = objResult == null ? objResult : objResult.clone();
    }

    public String[] getObjResultHeader() {
        return objResultHeader;

    }

    public void setObjResultHeader(final String... objResultHeader) {
        this.objResultHeader = objResultHeader == null ? objResultHeader : objResultHeader.clone();
    }

}
