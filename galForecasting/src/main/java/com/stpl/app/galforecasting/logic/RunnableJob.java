/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dto.SaveDTO;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class RunnableJob implements Runnable {
    int projectionId=0;
    int userId=0;
    int sessionId=0;
    String procedureCall=StringUtils.EMPTY;
    String propertyId=StringUtils.EMPTY;
    Object valueOf=null;
    String hirarechyNo=StringUtils.EMPTY;
    String group=StringUtils.EMPTY;
    SessionDTO session=null;
    ProjectionSelectionDTO selection=null;
    private String table=StringUtils.EMPTY;
    
    public RunnableJob(final int projectionId, final int userId, final int sessionId, final String procedureCall) {
        this.projectionId = projectionId;
        this.userId = userId;
        this.sessionId = sessionId;
        this.procedureCall = procedureCall;
    }

    public RunnableJob(final String propertyId, final Object valueOf, final String hirarechyNo, final String group, int projectionId, 
            SessionDTO session, ProjectionSelectionDTO selection, final String procedureCall,String table) {
        this.propertyId = propertyId;
        this.valueOf = valueOf;
        this.hirarechyNo = hirarechyNo;
        this.group = group;
        this.projectionId = projectionId;
        this.session = session;
        this.selection = selection;
        this.procedureCall = procedureCall;
        this.table=table;
    }
    @Override
    public void run() {
//        synchronized (this) {
            if (Constant.PROCEDURE_CALL.equals(procedureCall)) {
                Object[] input = {String.valueOf(projectionId), String.valueOf(userId), String.valueOf(sessionId)};
                CommonLogic.callProcedure("PRC_NM_PPA_PROJECTION", input);
               
            } else if ("savePPAProjection".equals(procedureCall)) {
                SaveDTO savedto = new SaveDTO();
                savedto.setPropertyId(propertyId);
                savedto.setValue(valueOf);
                savedto.setHirarechyNo(hirarechyNo);
                savedto.setTable(table);
//              String group = String.valueOf(groupFilterDdlb.getValue() == null ? Constant.PERCENT : groupFilterDdlb.getValue()).replace(Constant.PPA, StringUtils.EMPTY);
                group = group.equals(Constant.ALL_GROUP) ? Constant.PERCENT : group;
                savedto.setGroup(group);
                PPAProjectionLogic.savePPAProjection(projectionId, session, savedto, selection);
                
            }
        }
//    }
    
}
