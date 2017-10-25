/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;


import com.stpl.app.gtnforecasting.dao.NACommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.NADataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.NACommonResultsDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.NADataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.PhsQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.form.FcpResults;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.NaProjectionSelection;
import com.stpl.app.service.NaProjectionSelectionLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
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
              LOGGER.error(ex);
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
             LOGGER.error(ex);
        }
        return null;
    }
      public Object saveTempToMain(SessionDTO session) throws SystemException {                     
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID",session.getProjectionId());
        return tempOperation(input, "na.saveToMainTable",session);
    }
       public Object saveBaseYeaToItemMaster(SessionDTO session) throws SystemException {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", session.getProjectionId());
        return tempOperation(input, "UpdateBaseYearAdustments",session);
    }
      public Object saveTempNDCToMain(SessionDTO session) throws SystemException { 
        Map<String, Object> input = new HashMap<String, Object>();
        return tempOperation(input, "na.saveTempNdcToMainTable",session);
    }
      public Object deleteTempBySession(SessionDTO session) throws SystemException {
        Map<String, Object> input = new HashMap<String, Object>();
        return tempOperation(input, "na.dleteTemp",session);
    }
      public Object tempOperation(final Map<String, Object> input, final String queryName,SessionDTO session) {
      
        try {
            LOGGER.debug("Entering tempOperation method ");
            NACommonResultsDAO DAO = new NACommonResultsDAOImpl();
            LOGGER.debug("Query Name : " + queryName);
             String customSql = CustomSQLUtil.get(queryName);
            for (String key : input.keySet()) {
                LOGGER.debug("Key : " + key);
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
           
            LOGGER.debug("End of tempOperation method");
            Object temp =DAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames())) ;
            return temp;
        } catch (Exception e) {
           
            LOGGER.error(e);
            return null;
        }
    }
        public Object tempInsert(SessionDTO session) throws SystemException{
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("?PID", session.getProjectionId());
        return tempOperation(input, "na.tempInsert",session);
    }

    public Object clearTemp(SessionDTO session) throws SystemException {
        Map<String, Object> input = new HashMap<String, Object>();
        return tempOperation(input, "na.clearTemp",session);
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
