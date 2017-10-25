/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.forecastdashboard.logic;

import com.stpl.app.forecastdashboard.dao.ForecastDashboardDAO;
import com.stpl.app.forecastdashboard.dao.impl.ForecastDashboardDAOImpl;
import com.stpl.app.forecastdashboard.dto.TaskManagementDTO;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nandhakumar
 */
public class TaskManagementLogic {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(TaskManagementLogic.class);
    /**
     * The dao.
     */
    private ForecastDashboardDAO dao = new ForecastDashboardDAOImpl();
    Map<String, String> userMap = new HashMap<String, String>();

    public List<TaskManagementDTO> getRecentApprovedProjection(String type) throws SystemException {
//        List<ProjectionMaster> list = new ArrayList<ProjectionMaster>();
//        List<TaskManagementDTO> resultslist = new ArrayList<TaskManagementDTO>();
//        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
//        String approvedProj = "SELECT TOP 4 pm.PROJECTION_NAME,pm.PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME,pm.CUSTOMER_HIERARCHY_LEVEL, "
//                + " hd_prod.HIERARCHY_NAME AS prod_name, pm.PRODUCT_HIERARCHY_LEVEL,pm.CREATED_DATE,pm.MODIFIED_DATE,pm.CREATED_BY,pm.PROJECTION_MASTER_SID "
//                + " FROM   PROJECTION_MASTER pm JOIN WORKFLOW_MASTER wm ON pm.PROJECTION_MASTER_SID = wm.PROJECTION_MASTER_SID"
//                + " AND WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID FROM   HELPER_TABLE WHERE  LIST_NAME = 'WorkFlowStatus'  AND DESCRIPTION = 'Approved')"
//                + " LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = pm.CUSTOMER_HIERARCHY_SID"
//                + " LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = pm.PRODUCT_HIERARCHY_SID"
//                + " WHERE pm.CREATED_BY='" + userId + "'  and pm.FORECASTING_TYPE='Non Mandated' ORDER  BY wm.MODIFIED_DATE DESC";
//        list = dao.getResultList(approvedProj);
//        if (!list.isEmpty()) {
//            resultslist = getCustomizedResults(list);
//        }
//        return resultslist;
        return getSubmittedProjections("'Approved'",type);
    }

    public List<TaskManagementDTO> getRecentPendingProjection(String type) throws SystemException {
//        List<ProjectionMaster> list = new ArrayList<ProjectionMaster>();
//        List<TaskManagementDTO> resultslist = new ArrayList<TaskManagementDTO>();
//        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
//        String pendingProj = "SELECT TOP 4 pm.PROJECTION_NAME,pm.PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME,pm.CUSTOMER_HIERARCHY_LEVEL, "
//                + " hd_prod.HIERARCHY_NAME AS prod_name, pm.PRODUCT_HIERARCHY_LEVEL,pm.CREATED_DATE,pm.MODIFIED_DATE,pm.CREATED_BY,pm.PROJECTION_MASTER_SID "
//                + " FROM   PROJECTION_MASTER pm JOIN WORKFLOW_MASTER wm ON pm.PROJECTION_MASTER_SID = wm.PROJECTION_MASTER_SID"
//                + " AND WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID FROM   HELPER_TABLE WHERE  LIST_NAME = 'WorkFlowStatus'  AND DESCRIPTION = 'Pending')"
//                + " LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = pm.CUSTOMER_HIERARCHY_SID"
//                + " LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = pm.PRODUCT_HIERARCHY_SID"
//                + " WHERE pm.CREATED_BY='" + userId + "'  and pm.FORECASTING_TYPE='Non Mandated' ORDER  BY wm.CREATED_DATE DESC";
//        list = dao.getResultList(pendingProj);
//        if (!list.isEmpty()) {
//            resultslist = getCustomizedResults(list);
//        }
//        return resultslist;
        return getSubmittedProjections("'Pending'",type);
    }
    public List<TaskManagementDTO> getRejectedProjections(String type) throws SystemException {
//        List<ProjectionMaster> list = new ArrayList<ProjectionMaster>();
        List<TaskManagementDTO> resultslist = new ArrayList<TaskManagementDTO>();
//        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
//        String pendingProj = "SELECT TOP 4 pm.PROJECTION_NAME,pm.PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME,pm.CUSTOMER_HIERARCHY_LEVEL, "
//                + " hd_prod.HIERARCHY_NAME AS prod_name, pm.PRODUCT_HIERARCHY_LEVEL,pm.CREATED_DATE,pm.MODIFIED_DATE,pm.CREATED_BY,pm.PROJECTION_MASTER_SID "
//                + " FROM   PROJECTION_MASTER pm JOIN WORKFLOW_MASTER wm ON pm.PROJECTION_MASTER_SID = wm.PROJECTION_MASTER_SID"
//                + " AND WORKFLOW_STATUS_ID = (SELECT HELPER_TABLE_SID FROM   HELPER_TABLE WHERE  LIST_NAME = 'WorkFlowStatus'  AND DESCRIPTION = 'Rejected')"
//                + " LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = pm.CUSTOMER_HIERARCHY_SID"
//                + " LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = pm.PRODUCT_HIERARCHY_SID"
//                + " WHERE pm.CREATED_BY='" + userId + "'  and pm.FORECASTING_TYPE='Non Mandated' ORDER  BY wm.MODIFIED_DATE DESC";
//        list = dao.getResultList(pendingProj);
//        if (!list.isEmpty()) {
//            resultslist = getCustomizedResults(list);
//        }
//        return resultslist;
        return getSubmittedProjections("'Rejected'",type);
    }

//    public List<TaskManagementDTO> getTopSubmittedProjections()throws SystemException {
//        return getSubmittedProjections("'Rejected','Pending','Approved'","'Non Mandated','Mandated'");
//    }
    
    private List<TaskManagementDTO> getSubmittedProjections(String status,String type) throws SystemException {
        List<ProjectionMaster> list = new ArrayList<ProjectionMaster>();
        List<ProjectionMaster> totalList = new ArrayList<ProjectionMaster>();
         String pendingProj ="";
        List<TaskManagementDTO> resultslist = new ArrayList<TaskManagementDTO>();
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        if(!type.equals("'CFF'")){
         pendingProj = "SELECT TOP 7 pm.PROJECTION_NAME,pm.PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME,pm.CUSTOMER_HIERARCHY_LEVEL, "
                + " hd_prod.HIERARCHY_NAME AS prod_name, pm.PRODUCT_HIERARCHY_LEVEL,pm.CREATED_DATE,pm.MODIFIED_DATE,pm.CREATED_BY,pm.PROJECTION_MASTER_SID , HT.DESCRIPTION, pm.MODIFIED_BY, pm.FORECASTING_TYPE "
                + " FROM   PROJECTION_MASTER pm JOIN WORKFLOW_MASTER wm ON pm.PROJECTION_MASTER_SID = wm.PROJECTION_MASTER_SID"
//                + " AND wm.WORKFLOW_STATUS_ID in (SELECT HELPER_TABLE_SID FROM   HELPER_TABLE WHERE  LIST_NAME = 'WorkFlowStatus'  AND DESCRIPTION in ("+status+"))"
                + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=wm.WORKFLOW_STATUS_ID AND  HT.LIST_NAME = 'WorkFlowStatus' AND HT.DESCRIPTION in ("+status+") "
                + " LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = pm.CUSTOMER_HIERARCHY_SID"
                + " LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = pm.PRODUCT_HIERARCHY_SID"
                + " WHERE pm.CREATED_BY='" + userId + "'  and pm.FORECASTING_TYPE in ("+ type +") ORDER  BY wm.MODIFIED_DATE DESC";
        list = dao.getResultList(pendingProj);
        totalList.addAll(list);
        }
        if(type.contains("CFF") || type.equals("'CFF'")){
            String cff=StringUtils.EMPTY;
            if(type.equals("'CFF'")){
                cff="TOP 10";
            }else{
                  cff="TOP 3";
            }
         pendingProj="Select "+ cff +" CM.CFF_NAME AS PROJECTION_NAME , '' AS PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME AS HIERARCHY_NAME,CM.CUSTOMER_HIERARCHY_LEVEL,\n"
                + "                hd_prod.HIERARCHY_NAME AS prod_name, CM.PRODUCT_HIERARCHY_LEVEL,\n"
                + "CM.CREATED_DATE,CM.MODIFIED_DATE AS MODIFIED_DATE,CM.CREATED_BY AS CREATED_BY,CM.CFF_MASTER_SID AS PROJECTION_MASTER_SID, HT.DESCRIPTION,CM.MODIFIED_BY ,'CFF' AS FORECASTING_TYPE from CFF_MASTER CM join CFF_APPROVAL_DETAILS CAD ON CM.CFF_MASTER_SID=CAD.CFF_MASTER_SID\n"
               + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CAD.APPROVAL_STATUS AND  HT.LIST_NAME = 'WorkFlowStatus' AND HT.DESCRIPTION in ("+status+") "
                 + "                LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = CM.CUSTOMER_HIERARCHY_SID\n"
                + "                LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = CM.PRODUCT_HIERARCHY_SID"
                + " WHERE CM.CREATED_BY='" + userId + "'  ORDER  BY CM.MODIFIED_DATE DESC";
         list = dao.getResultList(pendingProj);
        totalList.addAll(list);
        }
        if (!totalList.isEmpty()) {
            resultslist = getCustomizedResults(totalList);
        }
        return resultslist;
    }
  
    public List<TaskManagementDTO> getProjectionsStatus(String status,String type,String subittedStatus) throws SystemException {
        List<ProjectionMaster> list = new ArrayList<ProjectionMaster>();
        List<ProjectionMaster> totalList = new ArrayList<ProjectionMaster>();
         String pendingProj ="";
        List<TaskManagementDTO> resultslist = new ArrayList<TaskManagementDTO>();
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        if(!type.equals("'CFF'")){
         pendingProj = "SELECT TOP 10 pm.PROJECTION_NAME,pm.PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME,pm.CUSTOMER_HIERARCHY_LEVEL, \n" +
"                  hd_prod.HIERARCHY_NAME AS prod_name, pm.PRODUCT_HIERARCHY_LEVEL,pm.CREATED_DATE,pm.MODIFIED_DATE,pm.CREATED_BY,pm.PROJECTION_MASTER_SID ,'"+subittedStatus+"' as DESCRIPTION, pm.MODIFIED_BY, pm.FORECASTING_TYPE \n" +
"                  FROM   PROJECTION_MASTER pm \n" +
"                  LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = pm.CUSTOMER_HIERARCHY_SID\n" +
"                  LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = pm.PRODUCT_HIERARCHY_SID\n" +
"                  WHERE  pm.FORECASTING_TYPE in ("+type+") and pm.IS_APPROVED='"+status+"' ORDER  BY pm.MODIFIED_DATE DESC;";
//            System.out.println("pendingProj:====123=======================>"+pendingProj);
        list = dao.getResultList(pendingProj);
        totalList.addAll(list);
        }
        if(type.contains("CFF") || type.equals("'CFF'")){
            String cff=StringUtils.EMPTY;
            if(type.equals("'CFF'")){
                cff="TOP 10";
            }else{
                  cff="TOP 3";
            }
         pendingProj="Select Top 2 CM.CFF_NAME AS PROJECTION_NAME , '' AS PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME AS HIERARCHY_NAME,CM.CUSTOMER_HIERARCHY_LEVEL,\n" +
"                                hd_prod.HIERARCHY_NAME AS prod_name, CM.PRODUCT_HIERARCHY_LEVEL,\n" +
"                                CM.CREATED_DATE,CM.MODIFIED_DATE AS MODIFIED_DATE,CM.CREATED_BY AS CREATED_BY,CM.CFF_MASTER_SID AS PROJECTION_MASTER_SID, '"+subittedStatus+"' AS DESCRIPTION,CM.MODIFIED_BY ,'CFF' AS FORECASTING_TYPE from CFF_MASTER CM \n" +
"                                LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = CM.CUSTOMER_HIERARCHY_SID\n" +
"                                LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = CM.PRODUCT_HIERARCHY_SID\n" +
"                                WHERE CM.INBOUND_STATUS='"+status+"'  ORDER  BY CM.MODIFIED_DATE DESC;";
//            System.out.println("pendingProj:====1234================================>"+pendingProj);
         list = dao.getResultList(pendingProj);
        totalList.addAll(list);
        }
        if (!totalList.isEmpty()) {
            resultslist = getCustomizedResults(totalList);
        }
        return resultslist;
    }
    
    
    
    public List<TaskManagementDTO> getForecastCFFRecentlySavedProjection(String status) throws SystemException {
        List<TaskManagementDTO> resultslist = new ArrayList<TaskManagementDTO>();
        List<ProjectionMaster> list = new ArrayList<ProjectionMaster>();
          List<ProjectionMaster> totalList = new ArrayList<ProjectionMaster>();
           String pendingProj=StringUtils.EMPTY;
             String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
          if(!status.equals("'CFF'")){
           pendingProj = "SELECT TOP 7  pm.PROJECTION_NAME,pm.PROJECTION_DESCRIPTION AS PROJECTION_DESCRIPTION,hd_cust.HIERARCHY_NAME ,pm.CUSTOMER_HIERARCHY_LEVEL  ,\n"
                + "                hd_prod.HIERARCHY_NAME AS prod_name, pm.PRODUCT_HIERARCHY_LEVEL ,pm.CREATED_DATE AS CREATED_DATE,pm.MODIFIED_DATE AS MODIFIED_DATE,pm.CREATED_BY AS CREATED_BY,pm.PROJECTION_MASTER_SID,STATUS='', pm.MODIFIED_BY, pm.FORECASTING_TYPE \n"
                + "                FROM   PROJECTION_MASTER pm \n"
                + "                LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = pm.CUSTOMER_HIERARCHY_SID\n"
                + "                LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = pm.PRODUCT_HIERARCHY_SID\n"
//               pm.SAVE_FLAG = 1 AND
                   + "                WHERE  pm.CREATED_BY='" + userId + "' and \n"
                + "pm.FORECASTING_TYPE in ("+status+") \n"
          + " ORDER  BY isnull(pm.MODIFIED_DATE,pm.CREATED_DATE) DESC ";
            list = dao.getResultList(pendingProj);    
              totalList.addAll(list);
          }
               if(status.contains("CFF") || status.equals("'CFF'")){
            String cff=StringUtils.EMPTY;
            if(status.equals("'CFF'")){
                cff="TOP 10";
            }else{
                  cff="TOP 3";
            }
         pendingProj = "Select "+cff+" CM.CFF_NAME AS PROJECTION_NAME,'' AS PROJECTION_DESCRIPTION , hd_cust.HIERARCHY_NAME AS HIERARCHY_NAME,CM.CUSTOMER_HIERARCHY_LEVEL,\n"
                + "                hd_prod.HIERARCHY_NAME AS prod_name, CM.PRODUCT_HIERARCHY_LEVEL,\n"
                + "CM.CREATED_DATE,CM.MODIFIED_DATE ,CM.CREATED_BY ,CM.CFF_MASTER_SID AS PROJECTION_MASTER_SID,STATUS='',CM.MODIFIED_BY ,'CFF' AS FORECASTING_TYPE from CFF_MASTER CM"
//                + " join CFF_APPROVAL_DETAILS CAD ON CM.CFF_MASTER_SID=CAD.CFF_MASTER_SID\n"

                + "                LEFT JOIN HIERARCHY_DEFINITION hd_cust ON hd_cust.HIERARCHY_DEFINITION_SID = CM.CUSTOMER_HIERARCHY_SID\n"
                + "                LEFT JOIN HIERARCHY_DEFINITION hd_prod ON hd_prod.HIERARCHY_DEFINITION_SID = CM.PRODUCT_HIERARCHY_SID"
                + " WHERE CM.CREATED_BY='" + userId + "' "
                + " ORDER  BY isnull(CM.MODIFIED_DATE,CM.CREATED_DATE) DESC ";
         list = dao.getResultList(pendingProj);    
              totalList.addAll(list);  
               }
        if (!totalList.isEmpty()) {
            resultslist = getCustomizedResults(totalList);
        }
        return resultslist;
    }
    

    private List<TaskManagementDTO> getCustomizedResults(List list) {
        try {
            getAllUsers();
        } catch (PortalException ex) {
            Logger.getLogger(TaskManagementLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(TaskManagementLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<TaskManagementDTO> results = new ArrayList<TaskManagementDTO>();
        for (Object list1 : list) {
            Object[] obj = (Object[]) list1;
            TaskManagementDTO dto = new TaskManagementDTO();
            dto.setForecastName(String.valueOf(obj[0]));
            dto.setForecastDesc(String.valueOf(obj[1]));
            dto.setCustomerHierarchy(String.valueOf(obj[2]));
            dto.setCustomerLevel(String.valueOf(obj[3]));
            dto.setProductHierarchy(String.valueOf(obj[4]));
            dto.setProductLevel(String.valueOf(obj[5]));
            dto.setCreatedDate(getFormattedDate(String.valueOf(obj[6])));

            if (obj[7] != null) {
                dto.setModifiedDate(getFormattedDate(String.valueOf(obj[7])));
            } else {
                dto.setModifiedDate("");
            }

            dto.setCreatedBy(getUserName(convertNullToEmpty(String.valueOf(obj[8]))));
            //  dto.setCreatedBy(String.valueOf(obj[8]));
            dto.setProjectionId(Integer.parseInt(String.valueOf(obj[9])));
            dto.setStatus(String.valueOf(obj[10]));
            dto.setModifiedBy(getUserName(convertNullToEmpty(String.valueOf(obj[11]))));
            if("Non Mandated".equals(String.valueOf(obj[12]))){
                dto.setForecastType("Commercial");
            }else if("Mandated".equals(String.valueOf(obj[12]))){
                dto.setForecastType("Government");
            }else{
                dto.setForecastType(convertNullToEmpty(String.valueOf(obj[12])));
            }
            results.add(dto);
        }
        return results;
    }

    public String getUserName(String userId) {
        String userName = StringUtils.EMPTY;
        if (userMap != null) {
            userName = userMap.get(userId);
        }
        return userName;
    }

    private String getFormattedDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            Date tempDate = simpleDateFormat.parse(date);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/YYYY");
            return outputDateFormat.format(tempDate);
        } catch (ParseException ex) {
            Logger.getLogger(TaskManagementLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || "null".equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    public Map<String, String> getAllUsers() throws PortalException, SystemException {
        List<Object> userList = new ArrayList<Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("userId"));
        productProjectionList.add(ProjectionFactoryUtil.property("firstName"));
        productProjectionList.add(ProjectionFactoryUtil.property("lastName"));
        query.setProjection(productProjectionList);
        try {
            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object array[] = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[0]), (String.valueOf(array[2]) + ", " + String.valueOf(array[1])));
            }
        } catch (Exception ex) {
            userList = null;

        }
        return userMap;

    }
}
