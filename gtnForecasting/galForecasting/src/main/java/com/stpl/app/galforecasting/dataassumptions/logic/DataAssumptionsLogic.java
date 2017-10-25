/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dataassumptions.logic;

import com.stpl.app.galforecasting.dataassumptions.dto.DataAssumptionDTO;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import com.stpl.app.galforecasting.utils.AbstractFilterLogic;
import com.stpl.app.utils.QueryUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptionsLogic {

    public List getCustomizedData(List<Object[]> list) {
        List<DataAssumptionDTO> finalList = new ArrayList<>();
        try {
            for (int i = 0; i < list.size(); i++) {
                final DataAssumptionDTO dto = new DataAssumptionDTO();
                final Object[] object = (Object[]) list.get(i);

                dto.setActiveFileName(checkAndReturnString(object[1]));
                dto.setActiveFileCompany(checkAndReturnString(object[2]));
                dto.setActiveFileBussinessUnit(checkAndReturnString(object[NumericConstants.THREE]));
                dto.setActiveFiletype(checkAndReturnString(object[NumericConstants.FOUR]));
                dto.setActiveFileVersion(checkAndReturnString(object[NumericConstants.FIVE]));
                dto.setActiveFileFromDate(checkAndReturnString(object[NumericConstants.SIX]));

                finalList.add(dto);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public List getDataAssumption(int start, int end, boolean isCount, Set<Container.Filter> searchCriteria, final List<SortByColumn> sortByColumns, SessionDTO sessionDTO) throws PortalException, SystemException, ParseException {
        List returnList = null;
        StringBuilder sql = new StringBuilder();

        Map<String, Object> input = new HashMap<>();
        StringBuilder filterQString = AbstractFilterLogic.getInstance().filterQueryGenerator(searchCriteria, getQueryMap());
        System.out.println("filterQString = " + filterQString.toString());
        if (filterQString != null) {
            input.put("[$RECORD_FILTER]", filterQString.toString().replace("where", "AND"));
        } else {
            input.put("[$RECORD_FILTER]", StringUtils.EMPTY);
        }
        if (isCount) {
            String query = QueryUtils.getAppQuery(input, "currentFile_count").replace("[@PROJECTION_MASTERID]", String.valueOf(sessionDTO.getProjectionId()));
            sql.append(query);
            
        } else {

            StringBuilder orderBy = AbstractFilterLogic.getInstance().orderByQueryDefaultOrder(sortByColumns, getQueryMap(),"FILE_MANAGEMENT_SID");
            System.out.println("orderBy = " + orderBy.toString());
            if (orderBy != null) {
                input.put("[$ORDER_BY]", orderBy.toString());
            } else {
                input.put("[$ORDER_BY]", StringUtils.EMPTY);
            }
            System.out.println("start = " + start);
            System.out.println("end = " + end);
            input.put("[$START]", start);
            input.put("[$END]", end);
            String query = QueryUtils.getAppQuery(input, "currentFile").replace("[@PROJECTION_MASTERID]", String.valueOf(sessionDTO.getProjectionId()));
            sql.append(query);
        }

        System.out.println("final query --> " + sql.toString());
        returnList = HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        return returnList;
    }

    private Map<String, String> getQueryMap() {
        Map<String, String> map = new HashMap<>();
        map.put("fileManagementSid", "FILE_MANAGEMENT_SID");
        map.put("activeFileName", "FORECAST_NAME");
        map.put("activeFileCompany", "COMPANY");
        map.put("activeFileBussinessUnit", "BUSINESS_UNIT");
        map.put("activeFiletype", "FILE_TYPE");
        map.put("activeFileVersion", "FORECAST_VERSION");
        map.put("activeFileFromDate", "ACTIVE_FROM");
        return map;
    }

}
