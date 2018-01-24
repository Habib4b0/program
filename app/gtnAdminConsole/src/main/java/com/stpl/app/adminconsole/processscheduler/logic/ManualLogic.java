/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic;

import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author satheesh.n
 */
public class ManualLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ManualLogic.class);
    protected static final HashMap<String, String> COLUMN_NAME=new HashMap<String, String>();

    public List getSearchResult(boolean count, int start, int offset, boolean scheduler, final List<SortByColumn> orderByColumns) {
        LOGGER.debug("Entering getSearchResult");
        try {
            boolean asc = false;
            String columnName;
            String dbColumnName = StringUtils.EMPTY;
            loadDbColumnName();
            if(!count){
                for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                    columnName = orderByColumn.getName();
                    dbColumnName = getDBColumnName(columnName);
                    if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                        asc = false;
                    } else {
                        asc = true;
                    }
                }
            }
            String query = CommonUtil.workFlowQuery(start, offset, count, scheduler,dbColumnName,asc);
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (count) {
                return list;
            } else {
                return getCustomizedSchedulerProcessing(list);

            }
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }
    public static String getDBColumnName(String visibleColumnName) {
         return COLUMN_NAME.get(visibleColumnName);         
    } 
                    
   public static HashMap<String, String> loadDbColumnName() {       
       COLUMN_NAME.put("processDisplayName", "PROCESS_NAME");
       COLUMN_NAME.put("manualLastRun", "MANUAL_LAST_RUN");
       
       return COLUMN_NAME;
   }

    private List getCustomizedSchedulerProcessing(List list)  {
        
        List<ProcessSchedulerDTO> returnList = new ArrayList<>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        if (list != null && !list.isEmpty()) {
            LOGGER.debug("Entering getCustomizedSchedulerProcessing" + list.size());
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
                dto.setIsSelected(String.valueOf(obj[0]));
                dto.setProcessSid(Integer.valueOf(String.valueOf(obj[0])));
                dto.setProcessDisplayName(String.valueOf(obj[NumericConstants.NINE]));
                if (obj[NumericConstants.SIX] != null) {
                    dto.setManualLastRun(date.format(obj[NumericConstants.SIX]));
                }
                dto.setProcessName(String.valueOf(obj[1]));
                dto.setScriptName(String.valueOf(obj[NumericConstants.THIRTEEN]));
                returnList.add(dto);
            }
        }
        LOGGER.debug("Ending getCustomizedSchedulerProcessing" + returnList.size());
        return returnList;
    }

}
