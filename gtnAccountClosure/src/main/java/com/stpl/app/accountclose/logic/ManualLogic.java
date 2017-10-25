/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.logic;


import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dto.ProcessDTO;
import com.stpl.app.accountclose.utils.CommonUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;
/**
 *
 * @author hazi.s
 */
public class ManualLogic {
    
 /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessSchedulerLogic.class);
    
    BaseRateDAO dao = new BaseRateDAOImpl();

    public List getSearchResult(boolean count) {
        LOGGER.info("Entering getSearchResult");
        try{
        String query = CommonUtils.manualQuery();
        List list = (List)dao.executeSelectQuery(query);
        if(count){
            return list;
        }else{
            return getCustomizedSchedulerProcessing(list);
            
        }
        }catch(Exception ex){
            LOGGER.error(ex);
            return null;
        }
    }

    private List getCustomizedSchedulerProcessing(List list) throws Exception{
        LOGGER.info("Entering getCustomizedSchedulerProcessing"+ list.size());
        List<ProcessDTO> returnList = new ArrayList<ProcessDTO>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        if(list!=null && list.size()!=0){
            for(int i=0;i<list.size();i++){
                Object[] obj = (Object[]) list.get(i);
                ProcessDTO dto = new ProcessDTO();
                    dto.setProcessName(String.valueOf(obj[0]));
                    
                    dto.setLastRunDate(new Date(date.format(obj[1])));
                 
                returnList.add(dto);
            }
        }
        LOGGER.info("Ending getCustomizedSchedulerProcessing" + returnList.size());
        return returnList;
    }
    public void getLastRunUpdate(Integer processId){
    try{
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    String query="UPDATE dbo.WORKFLOW_PROFILE\n" +
"SET MODIFIED_DATE='"+format.format(new Date())+"'\n" +
"WHERE PROCESS_SID="+processId+";";
      dao.executeUpdateQuery(query);  
    }catch(Exception ex){
        LOGGER.error(ex);
    }
        
        
    }
    
}
