
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

/**
 *
 * @author maheshj
 */
public class NMDiscountProjectionLogic {
    
    public String buildCCPCountQuery(boolean isCustomer,int projectionSid) {
        StringBuilder query = new StringBuilder();
        if (isCustomer) {
            query.append("SELECT Count(DISTINCT CCPD.Company_MASTER_SID) ");
        } else {
            query.append("SELECT Count(DISTINCT CCPD.ITEM_MASTER_SID) ");
        }
        query.append(" FROM   dbo.CCP_DETAILS CCPD  ");
        query.append(" JOIN dbo.PROJECTION_DETAILS PD  ");
        query.append(" ON PD.CCP_DETAILS_SID = CCPD.CCP_DETAILS_SID  ");
        query.append(" JOIN dbo.PROJECTION_MASTER PM  ");
        query.append("  ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID  ");
        query.append(" JOIN dbo.ST_NM_DISCOUNT_PROJ_MASTER DPM  ");
        query.append("  ON DPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID  ");
        query.append(" JOIN dbo.CCP_MAP CCPM  ");
        query.append("  ON CCPM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID  ");
        query.append("JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD  ");
        query.append("   ON RLD.RELATIONSHIP_LEVEL_SID = CCPM.RELATIONSHIP_LEVEL_SID  ");
        query.append("  JOIN dbo.RELATIONSHIP_BUILDER RB  ");
        query.append("    ON RB.RELATIONSHIP_BUILDER_SID = RLD.RELATIONSHIP_BUILDER_SID  ");
        query.append("   JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD  ");
        query.append("    ON RB.HIERARCHY_DEFINITION_SID = HLD.HIERARCHY_DEFINITION_SID  ");
        query.append(" WHERE  PM.PROJECTION_MASTER_SID = "+projectionSid+"  ");
          if (isCustomer) {

            query.append(" AND HLD.LEVEL_NAME IN ('Customer','Trading Partner','TRADING PARTNER')  ");

        } else {

            query.append(" AND HLD.LEVEL_NAME IN ( 'Item', 'NDC' )  ");
 
          }
    query.append(" AND DPM.CHECK_RECORD='1'");
            
     return query.toString();
    }
    
    
    
     public String buildCCPListQuery(boolean isCustomer,int projectionSid) {
        StringBuilder query = new StringBuilder();
      
        query.append(" SELECT DISTINCT CCPD.CCP_DETAILS_SID ");
      
        query.append(" FROM   dbo.CCP_DETAILS CCPD  ");
        query.append(" JOIN dbo.PROJECTION_DETAILS PD  ");
        query.append(" ON PD.CCP_DETAILS_SID = CCPD.CCP_DETAILS_SID  ");
        query.append(" JOIN dbo.PROJECTION_MASTER PM  ");
        query.append("  ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID  ");
        query.append(" JOIN dbo.ST_NM_DISCOUNT_PROJ_MASTER DPM  ");
        query.append("  ON DPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID  ");
        query.append(" JOIN dbo.CCP_MAP CCPM  ");
        query.append("  ON CCPM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID  ");
        query.append(" JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD  ");
        query.append("   ON RLD.RELATIONSHIP_LEVEL_SID = CCPM.RELATIONSHIP_LEVEL_SID  ");
        query.append("  JOIN dbo.RELATIONSHIP_BUILDER RB  ");
        query.append("    ON RB.RELATIONSHIP_BUILDER_SID = RLD.RELATIONSHIP_BUILDER_SID  ");
        query.append("   JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD  ");
        query.append("    ON RB.HIERARCHY_DEFINITION_SID = HLD.HIERARCHY_DEFINITION_SID  ");
        query.append(" WHERE  PM.PROJECTION_MASTER_SID = "+projectionSid+"  ");
         if (isCustomer) {
             query.append(" AND HLD.LEVEL_NAME IN ('Customer','Trading Partner','TRADING PARTNER')  ");

         } else {
             query.append(" AND HLD.LEVEL_NAME IN ( 'Item', 'NDC' )  ");
         }
       query.append(" AND DPM.CHECK_RECORD='1' ");
     
     return query.toString();
    }
    
    
    
    public int getCCPCount(String query) {
        int count = 0;
        List<Object> objList;
        objList = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);

        if (objList != null && !objList.isEmpty()) {
            Object ob = objList.get(0);
            count += Integer.parseInt(String.valueOf(ob));
        }
        return count;

    }
    
    public String getCCPList(String query, Set<Integer> totalccp,SessionDTO session) {
        StringBuilder ccpList = new StringBuilder(StringUtils.EMPTY);
       
          List<Integer> tempList = (List<Integer>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()), null, null);
        for (int ccpid : tempList) {
            totalccp.add(ccpid);
            if(ccpList.length()==0){
            ccpList.append(String.valueOf(ccpid));
            }else{
            ccpList.append(",");
            ccpList.append(String.valueOf(ccpid));
            }
        }
        return ccpList.toString();
    }
    
     public String buildActualValidateQuery(String cCPIds,int rsModelSid) {
        StringBuilder query = new StringBuilder();
      
         
         query.append("  DECLARE @CCP_DETAILS_SID VARCHAR(MAX) = '" + cCPIds + "' ");
         query.append(" ,@RS_MODEL_SID INT =" + rsModelSid);
         query.append("    ,@START_DATE DATETIME = DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0)   ");
         query.append("     ,@END_DATE DATETIME =  DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0)   ");
         query.append("  SELECT B.CCP_DETAILS_SID,R.RS_CONTRACT_SID,SUM(AM.AMOUNT) AMOUNT    ");
         query.append("  FROM  CCP_DETAILS B    ");
         query.append("  JOIN RS_CONTRACT R1 ON R1.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID   ");
         query.append("  JOIN RS_CONTRACT_DETAILS R2 ON R2.RS_CONTRACT_SID = R1.RS_CONTRACT_SID   ");
         query.append("  AND R2.ITEM_MASTER_SID = B.ITEM_MASTER_SID   ");
         query.append("  JOIN RS_CONTRACT R ON R.RS_CONTRACT_SID = R1.RS_CONTRACT_SID   ");
         query.append("  JOIN ACTUALS_MASTER AM ON AM.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID   ");
         query.append("  AND AM.ITEM_MASTER_SID = B.ITEM_MASTER_SID   ");
         query.append("  AND AM.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID   ");
         query.append("  AND R.RS_ID = AM.PROVISION_ID   ");
         query.append("  LEFT JOIN UDCS U ON U.MASTER_SID = R1.RS_CONTRACT_SID   ");
         query.append("  AND U.MASTER_TYPE = 'RS_CONTRACT'   ");
         query.append("  LEFT JOIN HELPER_TABLE H1 ON H1.HELPER_TABLE_SID = U.UDC2   ");
         query.append("  LEFT JOIN HELPER_TABLE H2 ON H2.HELPER_TABLE_SID = U.UDC3   ");
         query.append("  WHERE R1.INBOUND_STATUS <> 'D'   ");
         query.append("  AND R2.INBOUND_STATUS <> 'D'   ");
         query.append("  AND B.CCP_DETAILS_SID IN (   ");
         query.append("  SELECT TOKEN   ");
         query.append("  FROM UDF_SPLITSTRING(@CCP_DETAILS_SID, ',')   ");
         query.append("  )   ");
         query.append("  AND R.RS_CONTRACT_SID = @RS_MODEL_SID   ");
         query.append("  AND AM.ACCRUAL_ACTUAL_START_DATE >= @START_DATE   ");
         query.append("  AND AM.ACCRUAL_ACTUAL_END_DATE <=  DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0)   ");
         query.append("  GROUP BY B.CCP_DETAILS_SID   ");
         query.append("  ,R.RS_CONTRACT_SID   ");
         
     return query.toString();
    }
     public int getRsModelSid(String rsName){
     
       final DynamicQuery dynamicQuery = RsModelLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.eq("rsName", rsName));
        List<RsModel> rsModelDetailsList=new ArrayList<>();
        try {
        
           rsModelDetailsList= RsModelLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            LoggerFactory.getLogger(NMDiscountProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
     
     return rsModelDetailsList.get(0).getRsModelSid();
     
     }
    public String getZeroActualCCPList(String query,Set<Integer> totalccp,SessionDTO session) {
      StringBuilder ccpList=new StringBuilder(StringUtils.EMPTY);
       
          List<Object> tempList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()), null, null);
        for (Object list : tempList) {
            final Object[] obj = (Object[]) list;
            if (!(String.valueOf(obj[NumericConstants.TWO]).equals(Constant.DASH))) {
                BigDecimal big = new BigDecimal(String.valueOf(obj[NumericConstants.TWO]));
                totalccp.remove(big);
                if (ccpList.length() == 0) {
            ccpList.append(String.valueOf(obj[0]));
            }else{
            ccpList.append(",");
            ccpList.append(String.valueOf(obj[0]));
            }
            }
        }
        return ccpList.toString();
    }
    
   

 
}
