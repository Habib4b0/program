/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class RunnableJob implements Runnable {
    private int projectionId=0;
    private int userId=0;
    private int sessionId=0;
    private String procedureCall=StringUtils.EMPTY;
    private String propertyId=StringUtils.EMPTY;
    private Object valueOf=null;
    private String hirarechyNo=StringUtils.EMPTY;
    private String group=StringUtils.EMPTY;
    private SessionDTO session=null;
    private ProjectionSelectionDTO selection=null;
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
            if (Constant.PROCEDURE_CALL.equals(procedureCall)) {
                Object[] input = {String.valueOf(projectionId), String.valueOf(userId), String.valueOf(sessionId)};
                CommonLogic.callProcedure("PRC_NM_PPA_PROJECTION", input);
               
            } else if ("savePPAProjection".equals(procedureCall)) {
                SaveDTO savedto = new SaveDTO();
                savedto.setPropertyId(propertyId);
                savedto.setValue(valueOf);
                savedto.setHirarechyNo(hirarechyNo);
                savedto.setTable(table);
                group = group.equals(Constant.ALL_GROUP) ? Constant.PERCENT : group;
                savedto.setGroup(group);
                PPAProjectionLogic.savePPAProjection(projectionId, savedto, selection);
                
            }
        }
    
}
