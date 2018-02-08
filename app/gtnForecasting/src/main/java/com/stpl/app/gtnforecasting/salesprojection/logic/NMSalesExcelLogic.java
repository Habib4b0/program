/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.ACTUAL_SALES;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.PROJECTED_SALES;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.PROJECTED_UNITS1;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.UNITNODECIMAL;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.UNITTWODECIMAL;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Nimisha.Rakesh
 */
public class NMSalesExcelLogic {

    private final Map<String, SalesRowDto> resultMap = new HashMap<>();
    private final List<String> hierarchyKeys = new ArrayList<>();
    private final CommonLogic commonLogic=new CommonLogic();
    
    public void getCustomizedExcelData(List<Object[]> rawList, ProjectionSelectionDTO projectionSelectionDTO, List historyColumn) {
        SessionDTO sessionDTO = projectionSelectionDTO.getSessionDTO();
        Character freq = projectionSelectionDTO.getFrequency().charAt(0);
        Map<String, List> hierarchyLevelDetails = sessionDTO.getHierarchyLevelDetails();
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key = obj[NumericConstants.ZERO].toString();
            key = key.substring(key.indexOf('-') + 1);
            String hierarchyNo=getHierarchyNumber(obj[NumericConstants.ZERO]);
            if(hierarchyLevelDetails.get(hierarchyNo.trim())!=null){
            String hierarchyIndicator = String.valueOf(hierarchyLevelDetails.get(hierarchyNo.trim()).get(4));
            SalesRowDto salesRowDto = resultMap.get(key);
            if (salesRowDto == null) {
                //To check condition total or details values
                salesRowDto = new SalesRowDto();
                setActualsProjectionValues(salesRowDto, freq, obj, projectionSelectionDTO, historyColumn, hierarchyLevelDetails,hierarchyIndicator);
                resultMap.put(key, salesRowDto);
                hierarchykeys(key);
            } else {
                setActualsProjectionValues(salesRowDto, freq, obj, projectionSelectionDTO, historyColumn, hierarchyLevelDetails,hierarchyIndicator);
            }
            }

        }
    }

    private void setActualsProjectionValues(SalesRowDto salesRowDto, Character freq, Object[] obj, ProjectionSelectionDTO projectionSelectionDTO, List historyColumn, Map<String, List> hierarchyLevelDetails, String hierarchyIndicator) {
        try {
            String header = commonLogic.getHeaderForExcel(freq, obj,StringUtils.EMPTY);
            boolean isActuals = (Boolean) obj[NumericConstants.THREE];
            String hierarchyNo=getHierarchyNumber(obj[NumericConstants.ZERO]);
            salesRowDto.setParentHierarchyNo(String.valueOf(obj[NumericConstants.FOUR]).trim());
            salesRowDto.setHierarchyLevel(String.valueOf(hierarchyLevelDetails.get(hierarchyNo).get(1)));
            if (Constant.TRADINGPARTNER.equalsIgnoreCase(salesRowDto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(salesRowDto.getHierarchyLevel())) {
                salesRowDto.setGroup(String.valueOf(obj[NumericConstants.TWELVE]));
            } else {
                salesRowDto.setGroup(StringUtils.EMPTY);
            }
            salesRowDto.setLevelName(CommonUtil.getDisplayFormattedName(hierarchyNo, hierarchyIndicator, hierarchyLevelDetails, projectionSelectionDTO.getSessionDTO(), projectionSelectionDTO.getDisplayFormat()));
            salesRowDto.addStringProperties(Constant.METHODOLOGY, StringUtils.isBlank(String.valueOf(obj[NumericConstants.ELEVEN])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.ELEVEN])) ? "-" : String.valueOf(obj[NumericConstants.ELEVEN]));
            salesRowDto.addStringProperties(Constant.BASELINE, StringUtils.isBlank(String.valueOf(obj[NumericConstants.THIRTEEN])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.THIRTEEN])) ? "-" : String.valueOf(obj[NumericConstants.THIRTEEN]));
            if (CommonUtil.isValueEligibleForLoading()) {
                salesRowDto.setSalesInclusion(obj[NumericConstants.FOURTEEN] != null ? String.valueOf(obj[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);
            }

            if (historyColumn.contains(header) || (CommonUtil.isValueEligibleForLoading() && salesRowDto.getSalesInclusion().isEmpty())) {
                setActualsProjForSalesInclution(salesRowDto, isActuals, header);
            } else {
                setActualsProj(salesRowDto, isActuals, header, projectionSelectionDTO, obj);
            }
        } catch (Exception e) {
           
        }
    }

    

    private void setActualsProjForSalesInclution(SalesRowDto salesRowDto, boolean isActuals, String header) {
        if (isActuals) {
            salesRowDto.addStringProperties(header + ACTUAL_SALES, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + Constant.ACTUAL_UNITS1, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-HistoryProjectedSales", StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-HistoryProjectedUnits", StringUtils.EMPTY);

        } else {
            salesRowDto.addStringProperties(header + PROJECTED_SALES, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + PROJECTED_UNITS1, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-ProductGrowth", StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-AccountGrowth", StringUtils.EMPTY);

        }
    }

    private void setActualsProj(SalesRowDto salesRowDto, boolean isActuals, String header, ProjectionSelectionDTO projectionSelectionDTO, Object[] obj) {
        if (isActuals) {
            salesRowDto.addStringProperties(header + ACTUAL_SALES, CommonUtil.getConversionFormattedValue(projectionSelectionDTO, obj[NumericConstants.NINE], true));
            salesRowDto.addStringProperties(header + Constant.ACTUAL_UNITS1, String.valueOf(UNITNODECIMAL.format(obj[NumericConstants.TEN] == null ? 0 : obj[NumericConstants.TEN])));
            salesRowDto.addStringProperties(header + "-HistoryProjectedSales", String.valueOf(0));
            salesRowDto.addStringProperties(header + "-HistoryProjectedUnits", String.valueOf(0));

        } else {
            salesRowDto.addStringProperties(header + PROJECTED_SALES, CommonUtil.getConversionFormattedValue(projectionSelectionDTO, obj[NumericConstants.SEVEN], true));
            salesRowDto.addStringProperties(header + PROJECTED_UNITS1, String.valueOf(UNITNODECIMAL.format(obj[NumericConstants.EIGHT] == null ? 0 : obj[NumericConstants.EIGHT])));
            salesRowDto.addStringProperties(header + "-ProductGrowth", String.valueOf(UNITTWODECIMAL.format(obj[NumericConstants.SIX] == null ? 0 : obj[NumericConstants.SIX])) + Constant.PERCENT);
            salesRowDto.addStringProperties(header + "-AccountGrowth", String.valueOf(UNITTWODECIMAL.format(obj[NumericConstants.FIVE] == null ? 0 : obj[NumericConstants.FIVE])) + Constant.PERCENT);
        }
    }

    private void hierarchykeys(String key) {
        hierarchyKeys.add(key);
    }

    public Map<String, SalesRowDto> getResultMap() {
        return resultMap;
    }

    public List<String> getHierarchyKeys() {
        return hierarchyKeys;
    }

    private String getHierarchyNumber(Object hierarchyNumber) {
        String hierarchyNo = String.valueOf(hierarchyNumber).trim();
            hierarchyNo = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0].trim() : hierarchyNo;
            return hierarchyNo;
    }

}
