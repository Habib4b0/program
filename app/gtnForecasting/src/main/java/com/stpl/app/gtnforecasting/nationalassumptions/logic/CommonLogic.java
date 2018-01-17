/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;


import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.NACommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.NADataSelectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.NACommonResultsDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.NADataSelectionDAOImpl;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.PhsQueryUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.ui.form.FcpResults;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.NaProjectionSelection;
import com.stpl.app.service.NaProjectionSelectionLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.QueryUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nadhiya
 */
public class CommonLogic {
     public static final Logger LOGGER = LoggerFactory.getLogger(CommonLogic.class);
       
      public static NADataSelectionDAO dataSelection = new NADataSelectionDAOImpl();
        public void saveProjectionSelection(Map map, int projectionID, String screenName) throws PortalException {
        List<NaProjectionSelection> list = new ArrayList<>();
        DynamicQuery query = NaProjectionSelectionLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("naProjMasterSid", projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        try {
            list = NaProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list.isEmpty()) {
                PhsQueryUtils.saveSelection(map, projectionID, screenName, Constant.SAVE);
            } else {
                PhsQueryUtils.saveSelection(map, projectionID, screenName, Constant.UPDATE);
            }
            
        } catch (PortalException | SystemException ex) {
              LOGGER.error(ex.getMessage());
        }
    }
        /** Get projection selection
     * 
     * @param projectionId
     * @param screenName
     * @return 
     */
    public static Map<Object, Object> getProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        DynamicQuery query = NaProjectionSelectionLocalServiceUtil.dynamicQuery();
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
            }
            return map;
        } catch (SystemException ex) {
             LOGGER.error(ex.getMessage());
        }
        return null;
    }
      public Object saveTempToMain(SessionDTO session)  {                     
        Map<String, Object> input = new HashMap<>();
        input.put("?PID",session.getProjectionId());
        return tempOperation(input, "na.saveToMainTable",session);
    }
       public Object saveBaseYeaToItemMaster(SessionDTO session) {
        Map<String, Object> input = new HashMap<>();
        input.put("?PID", session.getProjectionId());
        return tempOperation(input, "UpdateBaseYearAdustments",session);
    }
      public Object saveTempNDCToMain(SessionDTO session)  { 
        Map<String, Object> input = new HashMap<>();
        return tempOperation(input, "na.saveTempNdcToMainTable",session);
    }
      public Object deleteTempBySession(SessionDTO session)  {
        Map<String, Object> input = new HashMap<>();
        return tempOperation(input, "na.dleteTemp",session);
    }
      public Object tempOperation(final Map<String, Object> input, final String queryName,SessionDTO session) {
      
        try {
            LOGGER.debug("Entering tempOperation method ");
            NACommonResultsDAO DAO = new NACommonResultsDAOImpl();
            LOGGER.debug("Query Name : " + queryName);
             String customSql = SQlUtil.getQuery(getClass(),queryName);
            for (String key : input.keySet()) {
                LOGGER.debug("Key : " + key);
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
           
            LOGGER.debug("End of tempOperation method");
            Object temp =DAO.executeBulkUpdateQuery(QueryUtil.replaceTableNames(customSql,session.getCurrentTableNames())) ;
            return temp;
        } catch (PortalException | SystemException e) {
           
            LOGGER.error(e.getMessage());
            return null;
        }
    }
        public Object tempInsert(SessionDTO session) {
        Map<String, Object> input = new HashMap<>();
        input.put("?PID", session.getProjectionId());
        return tempOperation(input, "na.tempInsert",session);
    }

    public Object clearTemp(SessionDTO session)  {
        Map<String, Object> input = new HashMap<>();
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
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(FcpResults.class.getName()).error( StringUtils.EMPTY, ex);
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

        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(FcpResults.class.getName()).error( StringUtils.EMPTY, ex);
        }
        return ht;

    }
        
}
