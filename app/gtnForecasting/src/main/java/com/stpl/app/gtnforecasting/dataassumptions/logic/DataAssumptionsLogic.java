/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dataassumptions.logic;

import com.stpl.app.gtnforecasting.dataassumptions.dto.DataAssumptionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AbstractFilterLogic;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.vaadin.v7.data.Container;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptionsLogic {

    private boolean isAllowedToSubmit = false;
    private List<Object[]> tempProjectionFileDetails = null;
    private SessionDTO session ;
    private final Map<Integer,String> fileNameList = new HashMap<>();
    public static final String S_FLAG = "S";
    public static final String D_FLAG = "D";
    public static final String C_FLAG = "C";

    private final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public DataAssumptionsLogic() {
    }

    public List getCustomizedData(List<Object[]> list) {
        List<DataAssumptionDTO> finalList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            try {
                final DataAssumptionDTO dto = new DataAssumptionDTO();
                final Object[] object = list.get(i);

                dto.setActiveFileName(checkAndReturnString(object[1]));
                dto.setActiveFileCompany(checkAndReturnString(object[2]));
                dto.setActiveFileBusinessUnit(checkAndReturnString(object[NumericConstants.THREE]));
                dto.setActiveFiletype(checkAndReturnString(object[NumericConstants.FOUR]));
                dto.setActiveFileVersion(checkAndReturnString(object[NumericConstants.FIVE]));
                dto.setActiveFileFromDate(checkAndReturnDate(object[NumericConstants.SIX]));
                dto.setActiveFileFromPeriod(checkAndReturnDate(object[NumericConstants.SEVEN]));
                dto.setActiveFileToPeriod(checkAndReturnDate(object[NumericConstants.EIGHT]));
                if (object[NumericConstants.SIX] != null) {
                    dto.setActiveFileFromDateString("\t" + formatter.format(object[NumericConstants.SIX]) + "\t");
                }
                if (object[NumericConstants.SEVEN] != null) {
                    dto.setActiveFileFromPeriodString("\t" + formatter.format(object[NumericConstants.SEVEN]) + "\t");
                }
                if (object[NumericConstants.EIGHT] != null) {
                    dto.setActiveFileToPeriodString("\t" + formatter.format(object[NumericConstants.EIGHT]) + "\t");
                }
                finalList.add(dto);
            } catch (Exception ex) {
                LoggerFactory.getLogger(DataAssumptionsLogic.class.getName()).error(StringUtils.EMPTY, ex);
            }
        }
        return finalList;
    }

    private String checkAndReturnString(Object source) {
        if (!"null".equals(source)) {
            return String.valueOf(source);
        } else {
            return StringUtils.EMPTY;
        }
    }

    private Date checkAndReturnDate(Object source) {
        if (source != null) {
            return (Date) source;
        } else {
            return null;
        }
    }

    public List getDataAssumption(int start, int end, boolean isCount, Set<Container.Filter> searchCriteria, final List<SortByColumn> sortByColumns, SessionDTO sessionDTO) {
        List returnList = null;
        StringBuilder sql = new StringBuilder();

        Map<String, Object> input = new HashMap<>();
        StringBuilder filterQString = AbstractFilterLogic.getInstance().filterQueryDefaultOrder(searchCriteria, getQueryMap());
        if (filterQString != null) {
            input.put("[$RECORD_FILTER]", filterQString.toString());
        } else {
            input.put("[$RECORD_FILTER]", StringUtils.EMPTY);
        }
        if (isCount) {
            String query = QueryUtils.getAppQuery(input, "currentFile_count").replace("[@PROJECTION_MASTERID]", String.valueOf(sessionDTO.getProjectionId()));
            query = query.replace("[$WORKFLOWSTATUS]", StringUtils.isBlank(sessionDTO.getWorkflowStatus()) && Constant.VIEW.equals(sessionDTO.getAction()) ? sessionDTO.getAction() : String.valueOf(sessionDTO.getWorkflowStatus()));
            sql.append(query);
        } else {
            StringBuilder orderBy = AbstractFilterLogic.getInstance().orderByQueryDefaultOrder(sortByColumns, getQueryMap(), "FILE_MANAGEMENT_SID");
            if (orderBy != null) {
                input.put("[$ORDER_BY]", orderBy.toString());
            } else {
                input.put("[$ORDER_BY]", StringUtils.EMPTY);
            }
            input.put("[$START]", start);
            input.put("[$END]", end);
            String query = QueryUtils.getAppQuery(input, "currentFile").replace("[@PROJECTION_MASTERID]", String.valueOf(sessionDTO.getProjectionId()));
            query = query.replace("[$WORKFLOWSTATUS]", StringUtils.isBlank(sessionDTO.getWorkflowStatus()) && Constant.VIEW.equals(sessionDTO.getAction()) ? sessionDTO.getAction() : String.valueOf(sessionDTO.getWorkflowStatus()))
                    .replace("[$WHERECOND]", StringUtils.isBlank(filterQString.toString()) ? " WHERE " : " AND ");
            sql.append(query);
        }

        returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        return returnList;
    }

    private Map<String, String> getQueryMap() {
        Map<String, String> map = new HashMap<>();
        map.put("fileManagementSid", "FILE_MANAGEMENT_SID");
        map.put("activeFileName", "FORECAST_NAME");
        map.put("activeFileCompany", "COMPANY");
        map.put("activeFileBusinessUnit", "BUSINESS_UNIT");
        map.put("activeFiletype", "FILE_TYPE");
        map.put("activeFileVersion", "FORECAST_VERSION");
        map.put("activeFileFromDate", "ACTIVE_FROM");
        map.put("activeFileFromPeriod", "FROM_PERIOD");
        map.put("activeFileToPeriod", "TO_PERIOD");
        return map;
    }

    private void validationForSubmit() {
        getTempPFDFiles();

        if (tempProjectionFileDetails != null && !tempProjectionFileDetails.isEmpty()) {
            Object[] pfd = tempProjectionFileDetails.get(0);
            if (S_FLAG.equals(String.valueOf(pfd[3])) && (boolean) pfd[4]) {
                isAllowedToSubmit = true;
                session.setIsSalesCalculated(true);
            } else {
                isAllowedToSubmit = false;
                return;
            }
            if (tempProjectionFileDetails.size() > 1) {
                pfd = tempProjectionFileDetails.get(1);
                if ((D_FLAG.equals(String.valueOf(pfd[3])) && (boolean) pfd[4]) || (C_FLAG.equals(String.valueOf(pfd[3])) && (boolean) pfd[4])) {
                    isAllowedToSubmit = true;
                    session.setIsDiscountCalculated(true);
                } else if(Constant.ADD_FULL_SMALL.equalsIgnoreCase(session.getAction())){
                    isAllowedToSubmit = true;
                }else{
                    isAllowedToSubmit = false;
                }
            } else {
                isAllowedToSubmit = false;
            }
        }
    }

    public void getLatestFilesList() {
        String query = SQlUtil.getQuery("getCurrentActiveFiles");
        query = query.replace("@PROJECTIONID", String.valueOf(session.getProjectionId()));
        List<Object[]> latestFiles = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        if (latestFiles != null && !latestFiles.isEmpty()) {
            Map<String, Object[]> fileMap = new HashMap<>();
            for (Object[] latestFile : latestFiles) {
                fileMap.put(String.valueOf(latestFile[4]), latestFile);
            }
            session.getLatestProjectionFileDetails().putAll(fileMap);
        }
    }

    public boolean isIsAllowedToSubmit() {
        validationForSubmit();
        return isAllowedToSubmit;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }
    
    /**
     * This method returns list of values from the session table
     */
    public void getTempPFDFiles() {
        String query = SQlUtil.getQuery("getTempPFD");
        query = query.replace("@PROJECTIONID", String.valueOf(session.getProjectionId()));
        tempProjectionFileDetails = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }
    
    /**
     * This method decides whether Sales is calculated or not
     */
    public void isSalesCalculatedAlready() {
        getTempPFDFiles();
        loadFileMap();
        if (tempProjectionFileDetails != null) {
            if (tempProjectionFileDetails.size() > 1) {
                Object[] obj = tempProjectionFileDetails.get(0);
                Object[] obj1 = tempProjectionFileDetails.get(1);
                if (S_FLAG.equals(String.valueOf(obj[3]))) {
                    session.setIsSalesCalculated((boolean) obj[4]);
                    session.setFileNameUsedInSales(fileNameList.get((Integer) obj[2]));
                } else if (S_FLAG.equals(String.valueOf(obj1[3]))) {
                    session.setIsSalesCalculated((boolean) obj1[4]);
                    session.setFileNameUsedInSales(fileNameList.get((Integer) obj1[2]));
                } else {
                    session.setIsSalesCalculated((boolean) obj1[4]);
                    session.setFileNameUsedInSales(fileNameList.get((Integer) obj[2]));
                }
            } else if (tempProjectionFileDetails.size() == 1) {
                Object[] obj = tempProjectionFileDetails.get(0);
                if (S_FLAG.equals(String.valueOf(obj[3]))) {
                    session.setIsSalesCalculated((boolean) obj[4]);
                }
                session.setFileNameUsedInSales(fileNameList.get((Integer) obj[2]));
            }
        }
    }
    
    /**
     * This method returns whether calculation can be done in discount projection
     * @param obj
     * @return 
     */
    public boolean discountCanbeCalculated(Object[] obj) {
        getTempPFDFiles();
        if (tempProjectionFileDetails != null && !tempProjectionFileDetails.isEmpty()) {
            boolean discountCanBeCalculated = false;
            Object[] obj1 = tempProjectionFileDetails.get(0);
            if (obj[1].equals(obj1[2])) {
                String query = SQlUtil.getQuery("QUERY_TO_CHECK_ISSALES_CALCULATED_ALREADY").replace("@PROJECTION_ID", String.valueOf(session.getProjectionId()));
                List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
                if (list != null && !list.isEmpty()) {
                    discountCanBeCalculated = Integer.parseInt(String.valueOf(list.get(0))) == 1;
                }
                if (!discountCanBeCalculated) {
                    return obj[0].equals(obj1[1]);
                }
                return true;
            } else {
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method used to check whether discount projection has been loaded based on the sales projection
     * "D" flag Indicates discount and "C" flag indicates discount projection with Contract details methodology
     * @return 
     */
    public boolean isDiscountLoaded(){
        getTempPFDFiles();
        if (tempProjectionFileDetails != null && !tempProjectionFileDetails.isEmpty()) {
            for (int i = 0; i < tempProjectionFileDetails.size(); i++) {
                Object[] obj = tempProjectionFileDetails.get(i);
                if (D_FLAG.equals(String.valueOf(obj[3])) || C_FLAG.equals(String.valueOf(obj[3]))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Method will load File Name and Sid from HelperTable
     */
    public void loadFileMap() {
        if (fileNameList.isEmpty()) {
            String query = SQlUtil.getQuery("getFileTypeList");
            List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = list.get(i);
                    fileNameList.put((Integer) obj[0], String.valueOf(obj[1]));
                }
            }
        }
    }
    
}
