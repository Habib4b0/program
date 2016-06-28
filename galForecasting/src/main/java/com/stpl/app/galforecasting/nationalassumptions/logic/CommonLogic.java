/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.logic;


import com.stpl.app.galforecasting.dao.NACommonResultsDAO;
import com.stpl.app.galforecasting.dao.NADataSelectionDAO;
import com.stpl.app.galforecasting.dao.impl.NACommonResultsDAOImpl;
import com.stpl.app.galforecasting.dao.impl.NADataSelectionDAOImpl;
import com.stpl.app.galforecasting.nationalassumptions.queryutils.PhsQueryUtils;
import com.stpl.app.galforecasting.nationalassumptions.ui.form.FcpResults;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.model.NaProjectionSelection;
import com.stpl.app.service.NaProjectionSelectionLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.jboss.logging.Logger;

/**
 *
 * @author Nadhiya
 */
public class CommonLogic {
     public static final Logger LOGGER = Logger.getLogger(CommonLogic.class);
    Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
       Long userId = Long.valueOf((String) VaadinSession.getCurrent()
               .getAttribute(Constant.USER_ID));
   int projectionId = (Integer) VaadinSession.getCurrent()
                        .getAttribute(Constant.PROJECTION_ID);
       
      public static NADataSelectionDAO dataSelection = new NADataSelectionDAOImpl();
        public void saveProjectionSelection(Map map, int projectionID, String screenName) throws PortalException {
        PhsQueryUtils phsQueryUtils = new PhsQueryUtils();
        List<NaProjectionSelection> list = new ArrayList<NaProjectionSelection>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NaProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq("naProjMasterSid", projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = NaProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                PhsQueryUtils.saveSelection(map, projectionID, screenName, Constant.SAVE);
            } else {
                phsQueryUtils.saveSelection(map, projectionID, screenName, Constant.UPDATE);
            }
            if(!list.isEmpty()){
                list = null;                
            }
        } catch (Exception ex) {
              LOGGER.info(ex.getMessage());
        }
    }
        /** Get projection selection
     * 
     * @param projectionId
     * @param screenName
     * @return 
     */
    public static Map<Object, Object> getProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NaProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq("naProjMasterSid", projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_NAME));
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_VALUES));
        query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            list = NaProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
                list = null;
            }
            return map;
        } catch (Exception ex) {
             LOGGER.info(ex.getMessage());
        }
        return null;
    }
      public Object saveTempToMain() throws SystemException, Exception {                     
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID",userId.intValue());
        input.put("?SID",sessionId);
        input.put("?PID",projectionId);
        return tempOperation(input, "na.saveToMainTable");
    }
       public Object saveBaseYeaToItemMaster() throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", userId.intValue());
        input.put("?SID", sessionId);
        input.put("?PID", projectionId);
        return tempOperation(input, "UpdateBaseYearAdustments");
    }
      public Object saveTempNDCToMain() throws SystemException, Exception { 
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID",userId.intValue());
        input.put("?SID",sessionId);
        return tempOperation(input, "na.saveTempNdcToMainTable");
    }
      public Object deleteTempBySession() throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", userId);
        input.put("?SID", sessionId);
        return tempOperation(input, "na.dleteTemp");
    }
      public Object tempOperation(final Map<String, Object> input, final String queryName) {
      
        try {
            LOGGER.info("Entering tempOperation method ");
            NACommonResultsDAO DAO = new NACommonResultsDAOImpl();
            LOGGER.info("Query Name : " + queryName);
             String customSql = CustomSQLUtil.get(queryName);
            for (String key : input.keySet()) {
                LOGGER.info("Key : " + key);
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
           
            LOGGER.info("End of tempOperation method");
            Object temp =DAO.executeBulkUpdateQuery(customSql) ;
            return temp;
        } catch (Exception e) {
           
            LOGGER.error(e.getMessage());
            return null;
        }
    }
        public Object tempInsert() throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", userId);
        input.put("?SID", sessionId);
        input.put("?PID", projectionId);
        String sessionDate= String.valueOf(VaadinSession.getCurrent().getAttribute("SessionDate"));
        input.put("?LD", sessionDate);
        return tempOperation(input, "na.tempInsert");
    }

    public Object clearTemp() throws SystemException, Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?UID", userId);
        input.put("?SID", sessionId);
        Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        tempDate.setDate(tempDate.getDate() - 2);
        input.put("?LD", fmt.format(tempDate));
        return tempOperation(input, "na.clearTemp");
    }
    public HelperDTO getBrand(int id) {

        NACommonResultsDAO DAO = new NACommonResultsDAOImpl();

        HelperDTO ht = new HelperDTO();
        try {
            String query = "Select distinct BM.BRAND_MASTER_SID,BM.BRAND_NAME from dbo.BRAND_MASTER BM join dbo.ITEM_MASTER IM on BM.BRAND_MASTER_SID= \n"
                    + "IM.BRAND_MASTER_SID where IM.ITEM_MASTER_SID = '" + id + "'";
            List result = (List) DAO.executeSelectQuery(query);
            if (result != null && !result.isEmpty()) {
                Object[] obj = (Object[]) result.get(0);
                ht.setId(Integer.valueOf(String.valueOf(obj[0])));
                ht.setDescription(String.valueOf(obj[1]));
            } else {
                ht = new HelperDTO(0, SELECT_ONE.getConstant());
            }
        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(FcpResults.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(FcpResults.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ht;

    }
    public HelperDTO getBrandForMedicaid(String ndc) {

        NACommonResultsDAO DAO = new NACommonResultsDAOImpl();

        HelperDTO ht = new HelperDTO();
        try {
            String query = "Select distinct BM.BRAND_MASTER_SID,BM.BRAND_NAME from dbo.BRAND_MASTER BM join dbo.ITEM_MASTER IM on BM.BRAND_MASTER_SID= \n"
                    + "IM.BRAND_MASTER_SID where IM.ndc9 = '" + ndc + "'";
            List result = (List) DAO.executeSelectQuery(query);
            if (result != null && !result.isEmpty()) {
                Object[] obj = (Object[]) result.get(0);
                ht.setId(Integer.valueOf(String.valueOf(obj[0])));
                ht.setDescription(String.valueOf(obj[1]));
            } else {
                ht = new HelperDTO(0, SELECT_ONE.getConstant());
            }

        } catch (PortalException ex) {
            java.util.logging.Logger.getLogger(FcpResults.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(FcpResults.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ht;

    }
        
}
