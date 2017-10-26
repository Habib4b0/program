/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dataassumptions.logic;

import com.stpl.app.gtnforecasting.dataassumptions.dto.DataAssumptionDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import com.stpl.app.gtnforecasting.utils.AbstractFilterLogic;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.utils.QueryUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptionsLogic {
    
   SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    public List getCustomizedData(List<Object[]> list) {
        List<DataAssumptionDTO> finalList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
            try {
                final DataAssumptionDTO dto = new DataAssumptionDTO();
                final Object[] object = (Object[]) list.get(i);

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
                    dto.setActiveFileFromPeriodString("\t" + formatter.format(object[NumericConstants.SEVEN])+"\t");
                }
                if (object[NumericConstants.EIGHT] != null) {
                    dto.setActiveFileToPeriodString("\t" + formatter.format(object[NumericConstants.EIGHT]) + "\t");
                }
                finalList.add(dto);
            } catch (Exception ex) {
                Logger.getLogger(DataAssumptionsLogic.class.getName()).log(Level.SEVERE, null, ex);
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

    public List getDataAssumption(int start, int end, boolean isCount, Set<Container.Filter> searchCriteria, final List<SortByColumn> sortByColumns, SessionDTO sessionDTO) throws PortalException, SystemException, ParseException {
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

}
