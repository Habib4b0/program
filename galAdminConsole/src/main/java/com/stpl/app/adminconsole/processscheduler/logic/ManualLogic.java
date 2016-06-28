/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic;

import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author satheesh.n
 */
public class ManualLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ManualLogic.class);

    public List getSearchResult(boolean count, int start, int offset, boolean scheduler) {
        LOGGER.info("Entering getSearchResult");
        try {
            String query = CommonUtil.workFlowQuery(start, offset, count, scheduler);
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (count) {
                return list;
            } else {
                return getCustomizedSchedulerProcessing(list);

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    private List getCustomizedSchedulerProcessing(List list) throws Exception {
        LOGGER.info("Entering getCustomizedSchedulerProcessing" + list.size());
        List<ProcessSchedulerDTO> returnList = new ArrayList<ProcessSchedulerDTO>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
                dto.setIsSelected(String.valueOf(obj[0]));
                dto.setProcessSid(Integer.valueOf(String.valueOf(obj[0])));
                dto.setProcessDisplayName(String.valueOf(obj[9]));
                if (obj[6] != null) {
                    dto.setManualLastRun(date.format(obj[6]));
                }
                dto.setProcessName(String.valueOf(obj[1]));
                dto.setScriptName(String.valueOf(obj[13]));
                returnList.add(dto);
            }
        }
        LOGGER.info("Ending getCustomizedSchedulerProcessing" + returnList.size());
        return returnList;
    }

}
