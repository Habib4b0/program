package com.stpl.app.service.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.RsModel;
import com.stpl.app.serviceUtils.CommonUtils;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

public class RsModelFinderImpl extends BasePersistenceImpl<RsModel> implements
		RsModelFinder {

	private static final Logger LOGGER = Logger
			.getLogger(RsModelFinderImpl.class);

	 public List findRSModel(String rsId, String rsNo,
            String rsName, String rsStatus, String rpType, String itemId, 
            String itemNo, String itemName, String rsType,Map<String, Object> parameters) {

        Session session = null;

        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = CustomSQLUtil
                    .get("com.rsModel.findRSModel");

            
            if (itemId.length() != 0) {
                itemId = itemNo.replace('*', '%');
                sql += " and im.itemId like '"
                        + itemId + "' ";
            }
            
            
            if (itemNo.length() != 0) {
                itemNo = itemNo.replace('*', '%');
                sql += " and im.itemNo like '"
                        + itemNo + "' ";
            }
            
            if (itemName.length() != 0) {
                itemName = itemName.replace('*', '%');
                sql += " and im.itemName like '"
                        + itemName + "' ";
            }

            if (rsId.length() != 0) {
                sql += " and rsm.rsId like '" + rsId + "' ";

            }

            if (rsNo.length() != 0) {
                sql += " and rsm.rsNo like '" + rsNo + "' ";

            }

            if (rsName.length() != 0) {
                sql += " and rsm.rsName like '" + rsName + "' ";
            }

            if (!"0".equals(rsStatus)&& !"".equals(rsStatus)) {
                sql += " and rsm.rsStatus='" + rsStatus + "' ";

            }

            if (!"0".equals(rpType) && !"".equals(rpType)) {
                sql += " and rsm.rebateProgramType='" + rpType + "' ";

            }
            
            if (!"0".equals(rsType) && !"".equals(rsType)) {
                sql += " and rsm.rsType='" + rsType + "' ";

            }
            if (parameters.get("rebateScheduleId") != null) {
                sql += " AND rsm.rsId like '";
                sql +=String.valueOf(parameters.get("rebateScheduleId"))+"' ";
            }
            
            if (parameters.get("rebateScheduleNo") != null) {
                sql += " AND rsm.rsNo like '";
                sql +=String.valueOf(parameters.get("rebateScheduleNo"))+"' ";
            }
            
            if (parameters.get("rebateScheduleName") != null) {
                sql += " AND rsm.rsName like '";
                sql +=String.valueOf(parameters.get("rebateScheduleName"))+"' ";
            }
            
            if (parameters.get("rebateScheduleStatus") != null) {
                sql += " AND rsm.rsStatus like '";
                sql +=String.valueOf(parameters.get("rebateScheduleStatus"))+"' ";
            }
             
            if (parameters.get("rebateScheduleType") != null) {
                sql += " AND rsm.rsType like '";
                sql +=String.valueOf(parameters.get("rebateScheduleType"))+"' ";
            }
            
            if (parameters.get("rebateProgramType") != null) {
                sql += " AND rsm.rebateProgramType like '";
                sql +=String.valueOf(parameters.get("rebateProgramType"))+"' ";
            }
            

            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

	public List findIFP(String ifpNumber, String ifpName,
            String ifpType, String ifpTypeString, String ifpStartDate,
            String ifpEndDate, String itemNo, String itemName,Map<String, Object> parameters) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            final String inputFormat = "EEE MMM dd HH:mm:ss z yyyy";
            final String outputFormat = "MM/dd/yyyy HH:mm:ss";
            session = openSession();

            sql = CustomSQLUtil
                    .get("com.rsModel.findIFP");

            
            
            if (itemNo.length() != 0) {
                itemNo = itemNo.replace('*', '%');
                sql += " and im.itemNo like '"
                        + itemNo + "' ";
            }
            
            if (itemName.length() != 0) {
                itemName = itemName.replace('*', '%');
                sql += " and im.itemName like '"
                        + itemName + "' ";
            }

            if (ifpNumber.length() != 0) {
                sql += " and ifp.ifpNo like '" + ifpNumber + "' ";

            }

            if (ifpName.length() != 0) {
                sql += " and ifp.ifpName like '" + ifpName + "' ";

            }

            if (ifpStartDate.length() != 0) {
                sql += " and ifp.ifpStartDate >= '" + CommonUtils.TimeStampConverter(inputFormat, ifpStartDate, outputFormat) + "' ";
            }
            
            if (ifpEndDate.length() != 0) {
                sql += " and ifp.ifpEndDate <= '" + CommonUtils.TimeStampConverter(inputFormat, ifpEndDate, outputFormat) + "' ";
            }

            if (!"0".equals(ifpTypeString)&& !"".equals(ifpTypeString)) {
                sql += " and ifp.ifpType='" + ifpTypeString + "' ";

            }

            
            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());;
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

    @SuppressWarnings("rawtypes")
    public List getItemDetailsOfIfp(String ifpId) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = CustomSQLUtil
                    .get("getItemDetailsOfIfp");

            if (ifpId.length() != 0) {
                sql += " AND IFD.IFP_MODEL_SID = "
                        + ifpId + " ";
            }

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

        @SuppressWarnings("rawtypes")
        public List getRebateScheduleDetails(int rebateScheduleSystemId, Object future1, Object future2) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = CustomSQLUtil
                    .get("getRebateScheduleDetails");

            if (rebateScheduleSystemId != 0) {
                sql = sql.replace("@RSD.RS_MODEL_SID", String.valueOf(rebateScheduleSystemId));
            }

            Map<String, Object> filterMap = (Map<String, Object>) future1;

            if (filterMap.get("Current") != null && filterMap.get("History") != null && filterMap.get("Future") != null) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN RSD.ITEM_REBATE_START_DATE AND ISNULL(RSD.ITEM_REBATE_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') ";
                sql += " OR " + " RSD.ITEM_REBATE_END_DATE < '" + filterMap.get("History") + "' ";
                sql += " OR " + " RSD.ITEM_REBATE_START_DATE > '" + filterMap.get("Future") + "' )";
            } else if ((filterMap.get("Current") != null && filterMap.get("History") != null) || (filterMap.get("History") != null && filterMap.get("Current") != null)) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN RSD.ITEM_REBATE_START_DATE AND ISNULL(RSD.ITEM_REBATE_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') OR RSD.ITEM_REBATE_END_DATE < '" + String.valueOf(filterMap.get("History")) + "') ";
            } else if ((filterMap.get("History") != null && filterMap.get("Future") != null) || (filterMap.get("Future") != null && filterMap.get("History") != null)) {
                sql += " AND (" + " RSD.ITEM_REBATE_END_DATE < '" + String.valueOf(filterMap.get("History")) + "' OR RSD.ITEM_REBATE_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "') ";
            } else if ((filterMap.get("Future") != null && filterMap.get("Current") != null) || (filterMap.get("Current") != null && filterMap.get("Future") != null)) {
                sql += " AND ( '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN RSD.ITEM_REBATE_START_DATE AND ISNULL(RSD.ITEM_REBATE_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') OR RSD.ITEM_REBATE_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "') ";
            } else if (filterMap.get("Current") != null) {
                sql += " AND '" + String.valueOf(filterMap.get("Current")) + "' BETWEEN RSD.ITEM_REBATE_START_DATE AND ISNULL(RSD.ITEM_REBATE_END_DATE,'" + String.valueOf(filterMap.get("Current")) + "') ";
            } else if (filterMap.get("History") != null) {
                sql += " AND " + " RSD.ITEM_REBATE_END_DATE < '" + String.valueOf(filterMap.get("History")) + "' ";
            } else if (filterMap.get("Future") != null) {
                sql += " AND " + " RSD.ITEM_REBATE_START_DATE > '" + String.valueOf(filterMap.get("Future")) + "' ";
            }
            
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                return sqlQuery.list();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(sql);
                return null;
            } finally {
                closeSession(session);
            }

        }

	@SuppressWarnings("rawtypes")
        public List getRebateScheduleDetailsUniqueCheck(String rebateScheduleId, String itemId, Date itemStartDate) {

            Session session = null;
            String sql = StringUtils.EMPTY;
            try {
                session = openSession();

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String date = df.format(itemStartDate);

                sql = "SELECT * FROM REBATE_SCHEDULE_DETAILS RSD WHERE RSD.REBATE_SCHEDULE_ID='" + rebateScheduleId + "'"
                        + " AND RSD.ITEM_ID='" + itemId + "' AND RSD.ITEM_START_DATE='" + date + "'";

                SQLQuery sqlQuery = session.createSQLQuery(sql);
                return sqlQuery.list();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                LOGGER.error(sql);
                return null;
            } finally {
                closeSession(session);
            }

        }

        @SuppressWarnings("rawtypes")
         public List getRebateScheduleDetailsUniqueCheckWithSysId(int rebateScheduleSystemId, String rebateScheduleId, String itemId, Date itemStartDate) {

             Session session = null;
             String sql = StringUtils.EMPTY;
             try {
                 session = openSession();

                 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                 String date = df.format(itemStartDate);

                 sql = "SELECT * FROM REBATE_SCHEDULE_DETAILS RSD WHERE RSD.REBATE_SCHEDULE_ID='" + rebateScheduleId + "'"
                         + " AND REBATE_SCHEDULE_SYSTEM_ID=" + rebateScheduleSystemId + " AND RSD.ITEM_ID='" + itemId + "' AND RSD.ITEM_START_DATE='" + date + "'";

                 SQLQuery sqlQuery = session.createSQLQuery(sql);
                 return sqlQuery.list();
             } catch (Exception e) {
                 LOGGER.error(e.getMessage());
                 LOGGER.error(sql);
                 return null;
             } finally {
                 closeSession(session);
             }

         }

    public List getParentRsList(String rebateSchId, String rebateSchNo, String rebateSchName, String rebateSchStatus, String rebateSchType, String rebateProgType, String itemId, String itemNo, String itemName, int start, int offset, String column, String orderBy, Map<String, Object> parameters, boolean isCount) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            if (StringUtils.isNotBlank(rebateSchId)) {

            } else {
                rebateSchId = "%";
            }
            if (orderBy != null) {

            } else {
                orderBy = "ASC";
            }

            sql = "SELECT DISTINCT rs.RS_MODEL_SID, rs.RS_ID, rs.RS_NO, rs.RS_NAME, rs.RS_STATUS, rs.RS_TYPE, rs.REBATE_PROGRAM_TYPE, htype.DESCRIPTION as rtype, hstatus.DESCRIPTION as rstatus, hpstatus.DESCRIPTION as rptype,hcategory.DESCRIPTION as rscategory,"
                    + "rs.RS_START_DATE,rs.RS_END_DATE,hdesignation.DESCRIPTION as rsdesignation,rs.PARENT_RS_ID,rs.PARENT_RS_NAME,rs.RECORD_LOCK_STATUS"
                    + " from RS_MODEL rs"
                    + " LEFT JOIN RS_DETAILS rsd on rsd.RS_MODEL_SID=rs.RS_MODEL_SID"
                    + " LEFT JOIN ITEM_MASTER im on im.ITEM_MASTER_SID=rsd.ITEM_MASTER_SID"
                    + " LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  rs.RS_TYPE"
                    + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  rs.RS_STATUS"
                    + " LEFT JOIN HELPER_TABLE hpstatus on hpstatus.HELPER_TABLE_SID =  rs.REBATE_PROGRAM_TYPE"
                    + " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID =  rs.RS_CATEGORY"
                    + " LEFT JOIN HELPER_TABLE hdesignation on hdesignation.HELPER_TABLE_SID =  rs.RS_DESIGNATION"
                    + " WHERE rs.INBOUND_STATUS != 'D' AND rs.RS_ID LIKE '" + rebateSchId + "'";

            if (StringUtils.isNotBlank(rebateSchNo) && StringUtils.isNotEmpty(rebateSchNo)) {
                sql += " AND rs.RS_NO LIKE '" + rebateSchNo + "'";

            }
            if (StringUtils.isNotBlank(rebateSchName) && StringUtils.isNotEmpty(rebateSchName)) {
                sql += " AND rs.RS_NAME LIKE '" + rebateSchName + "'";

            }
            if (rebateSchStatus != "0" && Integer.valueOf(rebateSchStatus) != 0) {
                sql += " AND rs.RS_STATUS = " + Integer.valueOf(rebateSchStatus);

            }
            if (rebateSchType != "0") {
                sql += " AND rs.RS_TYPE = " + Integer.valueOf(rebateSchType);

            }
            if (rebateProgType != "0") {
                sql += " AND rs.REBATE_PROGRAM_TYPE = " + Integer.valueOf(rebateProgType);
            }

            if (parameters.get("systemId") != null) {
                sql += " AND rs.RS_MODEL_SID like '";
                sql += String.valueOf(parameters.get("systemId") + "' ");
            }

            if (parameters.get("rebateScheduleId") != null) {
                sql += " AND rs.RS_ID like '";
                sql += String.valueOf(parameters.get("rebateScheduleId")) + "' ";
            }

            if (parameters.get("rebateScheduleNo") != null) {
                sql += " AND rs.RS_NO like '";
                sql += String.valueOf(parameters.get("rebateScheduleNo")) + "' ";
            }

            if (parameters.get("rebateScheduleName") != null) {
                sql += " AND rs.RS_NAME like '";
                sql += String.valueOf(parameters.get("rebateScheduleName")) + "' ";
            }

            if (parameters.get("rebateScheduleStatus") != null) {
                sql += " AND rs.RS_STATUS like '";
                sql += String.valueOf(parameters.get("rebateScheduleStatus")) + "' ";
            }

            if (parameters.get("rebateScheduleType") != null) {
                sql += " AND rs.RS_TYPE like '";
                sql += String.valueOf(parameters.get("rebateScheduleType")) + "' ";
            }

            if (parameters.get("parentId") != null) {
                sql += " AND rs.PARENT_RS_ID like '";
                sql += String.valueOf(parameters.get("parentId")) + "' ";
            }

            if (parameters.get("parentName") != null) {
                sql += " AND rs.PARENT_RS_NAME like '";
                sql += String.valueOf(parameters.get("parentName")) + "' ";
            }

            if (parameters.get("rsCategory") != null) {
                sql += " AND rs.RS_CATEGORY like '";
                sql += String.valueOf(parameters.get("rsCategory")) + "' ";
            }

            if (parameters.get("rsDesignation") != null) {
                sql += " AND rs.RS_DESIGNATION like '";
                sql += String.valueOf(parameters.get("rsDesignation")) + "' ";
            }

            if (parameters.get("rebateProgramType") != null) {
                sql += " AND rs.REBATE_PROGRAM_TYPE like '";
                sql += String.valueOf(parameters.get("rebateProgramType")) + "' ";
            }

            if (StringUtils.isNotBlank(itemId) && StringUtils.isNotEmpty(itemId)) {
                sql += " AND im.ITEM_ID LIKE '" + itemId + "'";
            }

            if (StringUtils.isNotBlank(itemNo) && StringUtils.isNotEmpty(itemNo)) {
                sql += " AND im.ITEM_NO LIKE '" + itemNo + "'";
            }

            if (StringUtils.isNotBlank(itemName) && StringUtils.isNotEmpty(itemName)) {
                sql += " AND im.ITEM_NAME LIKE '" + itemName + "'";
            }

            if (parameters.get("startDatefrom") != null && parameters.get("startDateto") != null) {
                String from = String.valueOf(parameters.get("startDatefrom"));
                String to = String.valueOf(parameters.get("startDateto"));
                sql += " AND RS_START_DATE BETWEEN '" + from + "' AND '" + to + "'";
            }

            if (parameters.get("endDatefrom") != null && parameters.get("endDateto") != null) {
                String from = String.valueOf(parameters.get("endDatefrom"));
                String to = String.valueOf(parameters.get("endDateto"));
                sql += " AND RS_END_DATE BETWEEN '" + from + "' AND '" + to + "'";
            }

            if (!isCount) {
                sql += " ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

	public List getIfpList(String ifpNumber,String ifpName, Object ifpType, String ifpStartDate, String ifpEndDate, String itemNo, String itemName, int start, int offset, String column,String orderBy,Map<String, Object> parameters)
    {
        Session session = null;
        String sql = StringUtils.EMPTY;
		try {
        session = openSession();
        
        final String inputFormat = "EEE MMM dd HH:mm:ss z yyyy";       
        final String outputFormat = "MM/dd/yyyy HH:mm:ss";
        
                if(StringUtils.isNotBlank(ifpNumber)){
                        
                }else{
                    ifpNumber="%";
                }
                if(orderBy!=null){
                     
                 }else{
                     orderBy="ASC";
                 }

	sql = "SELECT DISTINCT ifp.IFP_NO, ifp.IFP_NAME, ifp.IFP_TYPE, ifp.IFP_START_DATE, ifp.IFP_END_DATE, ifp.IFP_MODEL_SID, htype.DESCRIPTION as itype,"
                        + "hstatus.DESCRIPTION as istatus, ifp.IFP_ID,hcategory.DESCRIPTION as icategory"
                        +" from IFP_MODEL ifp JOIN IFP_DETAILS ifd on ifp.IFP_MODEL_SID=ifd.IFP_MODEL_SID JOIN ITEM_MASTER im on ifd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
                        +" LEFT JOIN HELPER_TABLE htype on ifp.IFP_TYPE​=htype.HELPER_TABLE_SID"
                        +" LEFT JOIN HELPER_TABLE hstatus on ifp.IFP_STATUS=hstatus.HELPER_TABLE_SID"
                        +" LEFT JOIN HELPER_TABLE hcategory on ifp.IFP_CATEGORY=hcategory.HELPER_TABLE_SID"
                        +" WHERE ifp.INBOUND_STATUS <> 'D' AND ifp.IFP_NO LIKE '"+ifpNumber+"'";
        
                     
       if(StringUtils.isNotBlank(ifpName)&&StringUtils.isNotEmpty(ifpName)){
               sql += " AND ifp.IFP_NAME LIKE '"+ifpName+"'";
                        
       }
       if(ifpType != null && !StringUtils.EMPTY.equals(ifpType.toString()) && !"0".equals(ifpType.toString())) {
               sql += " AND ifp.IFP_TYPE LIKE '"+ifpType+"'";
                        
       }
       if(ifpStartDate.length() != 0){
               sql += " AND ifp.IFP_START_DATE = '" + CommonUtils.TimeStampConverter(inputFormat, ifpStartDate, outputFormat) + "' ";
                        
       }
       if(ifpEndDate.length() != 0){
               sql += " AND ifp.IFP_END_DATE = '" + CommonUtils.TimeStampConverter(inputFormat, ifpEndDate, outputFormat) + "' ";
                        
       }
       if(StringUtils.isNotBlank(itemNo)&&StringUtils.isNotEmpty(itemNo)){
               sql += " AND ifp.IFP_ID LIKE '"+itemNo+"'";
                        
       }
//       if(StringUtils.isNotBlank(itemName)&&StringUtils.isNotEmpty(itemName)){
//               sql += " AND im.ITEM_NAME LIKE '"+itemName+"'";
//                        
//       }
       if (parameters.get("ifpCategorySearch") != null && !"null".equals(parameters.get("ifpCategorySearch")) && !"0".equals(parameters.get("ifpCategorySearch"))) {
                sql += " AND ifp.IFP_CATEGORY like '";
                sql +=String.valueOf(parameters.get("ifpCategorySearch"))+"' ";
            }
       if (parameters.get("ifpStatusSearch") != null && !"null".equals(parameters.get("ifpStatusSearch")) && !"0".equals(parameters.get("ifpStatusSearch"))) {
                sql += " AND ifp.IFP_STATUS like '";
                sql +=String.valueOf(parameters.get("ifpStatusSearch"))+"' ";
            }
       
        // "ifpNo", "ifpName", "ifpType", "ifpStartDate", "ifpEndDate"};
      //   ifp.IFP_TYPE, ifp.IFP_START_DATE, ifp.IFP_END_DATE,
       
       if (parameters.get("ifpId") != null) {
                sql += " AND ifp.IFP_ID like '";
                sql +=String.valueOf(parameters.get("ifpId"))+"' ";
            }
       
         if (parameters.get("ifpNo") != null) {
                sql += " AND ifp.IFP_NO like '";
                sql +=String.valueOf(parameters.get("ifpNo"))+"' ";
            }
            
            if (parameters.get("ifpName") != null) {
                sql += " AND ifp.IFP_NAME like '";
                sql +=String.valueOf(parameters.get("ifpName"))+"' ";
            }
            
            if (parameters.get("ifpType") != null) {
                sql += " AND ifp.IFP_TYPE like '";
                sql +=String.valueOf(parameters.get("ifpType"))+"' ";
            }
            
            if (parameters.get("ifpStatus") != null) {
                sql += " AND ifp.IFP_STATUS like '";
                sql +=String.valueOf(parameters.get("ifpStatus"))+"' ";
            }
            
            if (parameters.get("ifpCategory") != null) {
                sql += " AND ifp.IFP_CATEGORY like '";
                sql +=String.valueOf(parameters.get("ifpCategory"))+"' ";
            }
            
                    String ifpStartDateFrom = "";
                    String ifpStartDateTo = "";

                    if (parameters.containsKey("ifpStartDate-From")) {
                        ifpStartDateFrom = String.valueOf(parameters.get("ifpStartDate-From")).trim();
                    }
                    if (parameters.containsKey("ifpStartDate-To")) {
                        ifpStartDateTo = String.valueOf(parameters.get("ifpStartDate-To")).trim();
                    }

                    String ifpEndDateFrom = "";
                    String ifpEndDateTo = "";

                    if (parameters.containsKey("ifpEndDate-From")) {
                        ifpEndDateFrom = String.valueOf(parameters.get("ifpEndDate-From")).trim();
                    }
                    if (parameters.containsKey("ifpEndDate-To")) {
                        ifpEndDateTo = String.valueOf(parameters.get("ifpEndDate-To")).trim();
                    }
            
        if (!"".equals(ifpStartDateFrom)) {
              sql += "AND ifp.IFP_START_DATE >= '" + CommonUtils.TimeStampConverter(inputFormat, ifpStartDateFrom, outputFormat) + "'";
          }

          if (!"".equals(ifpStartDateTo)) {
              sql += "AND ifp.IFP_START_DATE <= '" + CommonUtils.TimeStampConverter(inputFormat, ifpStartDateTo, outputFormat) + "'";
          }

          if (!"".equals(ifpEndDateFrom)) {
              sql += "AND ifp.IFP_END_DATE >= '" + CommonUtils.TimeStampConverter(inputFormat, ifpEndDateFrom, outputFormat) + "'";
          }

          if (!"".equals(ifpEndDateTo)) {
              sql += "AND ifp.IFP_END_DATE <= '" + CommonUtils.TimeStampConverter(inputFormat, ifpEndDateTo, outputFormat)  + "'";
          }
           
         
       
       if(offset!=0){
               sql += " ORDER BY "+column+" "+orderBy+" OFFSET "+start+" ROWS FETCH NEXT "+offset+" ROWS ONLY";
       }
               SQLQuery sqlQuery = session.createSQLQuery(sql);
                 return sqlQuery.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return null;
		} finally {
			closeSession(session);
		}
                
                }

    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            session = openSession();

            if (query != null && StringUtils.isNotBlank(query)) {
                Query sqlQuery = session.createSQLQuery(query);
                returnList = sqlQuery.list();
            }

        } catch (ORMException e) {            
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
        }
        return returnList;
    }

    public Object executeUpdateQuery(List<StringBuilder> queryList, Object obj1, Object obj2) {
        Session session = null;
        try {
            session = openSession();
            for (StringBuilder builder : queryList) {
                Query sqlQuery = session.createSQLQuery(builder.toString());
                sqlQuery.executeUpdate();
            }
            session.flush();
            return true;
        } catch (ORMException ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryList.toString());
        } finally {
            closeSession(session);
            clearCache();
        }
        return false;
    }
        
    public Object executeUpdateQuery(String query, Object obj1, Object obj2) {
        Session session = null;
        int updateCount = 0;
        try {
            session = openSession();
            Query sqlQuery = session.createSQLQuery(query);
            updateCount = sqlQuery.executeUpdate();
            session.flush();
        } catch (ORMException ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
        }
        return updateCount;
    }

}
