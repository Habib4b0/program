/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.util;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsCommonQueryContants {

	private GtnWsCommonQueryContants() {
	}

	public static final String GTN_COMMON_NOTE_TAB_SELECT = "Select MASTER_TABLE_NAME,FILE_PATH,CREATED_DATE,CREATED_BY,MASTER_DATA_FILES_SID from MASTER_DATA_FILES where MASTER_TABLE_SID= ";
	public static final String GTN_COMMON_NOTE_TAB_INSERT = "INSERT INTO MASTER_DATA_FILES (MASTER_TABLE_SID, MASTER_TABLE_NAME, FILE_PATH, CREATED_DATE, CREATED_BY)";
	public static final String GTN_COMMON_NOTE_TAB_DELETE = "DELETE FROM MASTER_DATA_FILES WHERE MASTER_TABLE_SID=";
}
