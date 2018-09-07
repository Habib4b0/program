/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.logic;

import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.ACTUAL_SALES;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.DASH_PROJECTED_SALES;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.PROJECTED_UNITS1;
import static com.stpl.app.gtnforecasting.salesprojection.logic.SalesLogic.UNITNODECIMAL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;

/**
 *
 * @author Nimisha.Rakesh
 */
public class NMSalesExcelLogic {

    public static final String DF_LEVEL_NUMBER = "dfLevelNumber";
    public static final String DF_LEVEL_NAME = "dfLevelName";
    private final Map<String, SalesRowDto> resultMap = new HashMap<>();
    private final List<String> hierarchyKeys = new ArrayList<>();
    private final CommonLogic commonLogic=new CommonLogic();
    private final List<String> hierarchyValues = new ArrayList<>();
    public static final Logger LOGGER = LoggerFactory.getLogger(NMSalesExcelLogic.class);
    
    public void getCustomizedExcelData(List<Object[]> rawList, ProjectionSelectionDTO projectionSelectionDTO, List historyColumn) {
        SessionDTO sessionDTO = projectionSelectionDTO.getSessionDTO();
        Character freq = projectionSelectionDTO.getFrequency().charAt(0);
        Map<String, List> hierarchyLevelDetails = !projectionSelectionDTO.isIsCustomHierarchy()? sessionDTO.getHierarchyLevelDetails() : sessionDTO.getSalesHierarchyLevelDetails();
        for (Iterator<Object[]> it = rawList.listIterator(); it.hasNext();) {
            Object[] obj = it.next();
            String key = obj[NumericConstants.ZERO].toString();
            String hierKey = key.substring(0,key.lastIndexOf('.'));
                key = key.substring(key.indexOf('-') + 1);
            String hierarchyNo=getHierarchyNumber(obj[NumericConstants.ZERO]);
            if(hierarchyLevelDetails.get(hierarchyNo.trim())!=null){
            String hierarchyIndicator = String.valueOf(hierarchyLevelDetails.get(hierarchyNo.trim()).get(4));
            SalesRowDto salesRowDto = resultMap.get(key);
            if (salesRowDto == null) {
                 getHierarchy(hierKey,projectionSelectionDTO);
                 getParentLevels(projectionSelectionDTO,obj);
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
    
    public String getHierarchy(String key, ProjectionSelectionDTO projectionSelection) {

        if (key.contains(".")) {
            String keyValue = key.substring(0, key.lastIndexOf('.'));
            int count = StringUtils.countMatches(key, ".");
            int forecastLevel = projectionSelection.getHierarchyIndicator().equalsIgnoreCase("C") ? Integer.parseInt(projectionSelection.getSessionDTO().getCustomerLevelNumber()) : Integer.parseInt(projectionSelection.getSessionDTO().getProductLevelNumber());
            if (!projectionSelection.isIsCustomHierarchy()) {
                if (count >= forecastLevel) {
                    hierarchyValues.add(keyValue);
                }
            } else {
                hierarchyValues.add(keyValue);
            }
            getHierarchy(keyValue, projectionSelection);
            return keyValue;
        }
        return StringUtils.EMPTY;
    }

    public void getParentLevels(ProjectionSelectionDTO projectionSelection,Object[] obj) {
        Map<String, List> hierarchyLevelDetails = !projectionSelection.isIsCustomHierarchy()? projectionSelection.getSessionDTO().getHierarchyLevelDetails() : projectionSelection.getSessionDTO().getSalesHierarchyLevelDetails();
        Collections.reverse(hierarchyValues);
        for (int i = 0; i < hierarchyValues.size(); i++) {
            StringBuilder keyValue = new StringBuilder(hierarchyValues.get(i)).append(".");
            StringBuilder hierKeyValue = new StringBuilder(keyValue.substring(keyValue.indexOf("-") + 1));
            SalesRowDto salesRowDto = resultMap.get(hierKeyValue.toString());
            if (salesRowDto == null) {
                salesRowDto = new SalesRowDto();
                getExcelFormatColumns(keyValue.toString(),  hierarchyLevelDetails, projectionSelection, salesRowDto);
                salesRowDto.addStringProperties(Constant.METHODOLOGY, StringUtils.isBlank(String.valueOf(obj[NumericConstants.TEN])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.TEN])) ? "-" : String.valueOf(obj[NumericConstants.TEN]));
                salesRowDto.addStringProperties(Constant.BASELINE, StringUtils.isBlank(String.valueOf(obj[NumericConstants.TWELVE])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWELVE])) ? "-" : String.valueOf(obj[NumericConstants.TWELVE]));
                resultMap.put(hierKeyValue.toString(), salesRowDto);
                hierarchykeys(hierKeyValue.toString());
            }
            }
        }

    private void setActualsProjectionValues(SalesRowDto salesRowDto, Character freq, Object[] obj, ProjectionSelectionDTO projectionSelectionDTO, List historyColumn, Map<String, List> hierarchyLevelDetails, String hierarchyIndicator) {
        try {
            if(obj[NumericConstants.ONE] == null){
            getExcelFormatColumns(getHierarchyNumber(obj[NumericConstants.ZERO]),hierarchyLevelDetails,projectionSelectionDTO,salesRowDto);
            return;
            }
            String header = commonLogic.getHeaderForExcel(freq, obj,StringUtils.EMPTY,"-");
            boolean isActuals = (Boolean) obj[NumericConstants.THREE];
            String hierarchyNo=getHierarchyNumber(obj[NumericConstants.ZERO]);
            salesRowDto.setHierarchyLevel(String.valueOf(hierarchyLevelDetails.get(hierarchyNo).get(1)));
            if (Constant.TRADINGPARTNER.equalsIgnoreCase(salesRowDto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(salesRowDto.getHierarchyLevel())) {
                salesRowDto.setGroup(String.valueOf(obj[NumericConstants.ELEVEN]));
            } else {
                salesRowDto.setGroup(StringUtils.EMPTY);
            }
             if (CommonUtil.isValueEligibleForLoading()) {
             
                getExcelFormatColumns(hierarchyNo,hierarchyLevelDetails,projectionSelectionDTO,salesRowDto);
            } else {
                salesRowDto.setLevelName(CommonUtil.getDisplayFormattedName(hierarchyNo,  hierarchyLevelDetails, projectionSelectionDTO.getDisplayFormat()));

            }
            salesRowDto.addStringProperties(Constant.METHODOLOGY, StringUtils.isBlank(String.valueOf(obj[NumericConstants.TEN])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.TEN])) ? "-" : String.valueOf(obj[NumericConstants.TEN]));
            salesRowDto.addStringProperties(Constant.BASELINE, StringUtils.isBlank(String.valueOf(obj[NumericConstants.TWELVE])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.TWELVE])) ? "-" : String.valueOf(obj[NumericConstants.TWELVE]));
            if (CommonUtil.isValueEligibleForLoading()) {
                salesRowDto.setSalesInclusion(obj[NumericConstants.THIRTEEN] != null ? String.valueOf(obj[NumericConstants.THIRTEEN]) : StringUtils.EMPTY);
            }

            if (historyColumn.contains(header) || (CommonUtil.isValueEligibleForLoading() && salesRowDto.getSalesInclusion().isEmpty())) {
                setActualsProjForSalesInclution(salesRowDto, isActuals, header);
            } else {
                setActualsProj(salesRowDto, isActuals, header, projectionSelectionDTO, obj);
            }
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }
    }

    

    private void setActualsProjForSalesInclution(SalesRowDto salesRowDto, boolean isActuals, String header) {
        if (!isActuals) {
        	
            salesRowDto.addStringProperties(header + ACTUAL_SALES, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + Constant.ACTUAL_UNITS1, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-HistoryProjectedSales", StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-HistoryProjectedUnits", StringUtils.EMPTY);

        } else {
        	
            salesRowDto.addStringProperties(header + DASH_PROJECTED_SALES, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + PROJECTED_UNITS1, StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-ProductGrowth", StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-AccountGrowth", StringUtils.EMPTY);
            //Added For PG_SUM and AG_SUM column
            salesRowDto.addStringProperties(header + "-ProductGrowthSum", StringUtils.EMPTY);
            salesRowDto.addStringProperties(header + "-AccountGrowthSum", StringUtils.EMPTY);

        }
    }

    private void setActualsProj(SalesRowDto salesRowDto, boolean isActuals, String header, ProjectionSelectionDTO projectionSelectionDTO, Object[] obj) {
        if (!isActuals) {
        	
            salesRowDto.addStringProperties(header + ACTUAL_SALES, CommonUtil.getConversionFormattedValue(projectionSelectionDTO, obj[NumericConstants.EIGHT], true));
            salesRowDto.addStringProperties(header + Constant.ACTUAL_UNITS1, String.valueOf(UNITNODECIMAL.format(obj[NumericConstants.NINE] == null ? 0 : obj[NumericConstants.NINE])));
            salesRowDto.addStringProperties(header + "-HistoryProjectedSales", String.valueOf(0));
            salesRowDto.addStringProperties(header + "-HistoryProjectedUnits", String.valueOf(0));

        } else {
            salesRowDto.addStringProperties(header + DASH_PROJECTED_SALES, CommonUtil.getConversionFormattedValue(projectionSelectionDTO, obj[NumericConstants.SIX], true));
            salesRowDto.addStringProperties(header + PROJECTED_UNITS1, String.valueOf(UNITNODECIMAL.format(obj[NumericConstants.SEVEN] == null ? 0 : obj[NumericConstants.SEVEN])));
            
            //Made some changes here
            salesRowDto.addStringProperties(header + "-ProductGrowth", String.valueOf(obj[NumericConstants.FIVE] == null ? 0 : obj[NumericConstants.FIVE]));
            salesRowDto.addStringProperties(header + "-AccountGrowth", String.valueOf(obj[NumericConstants.FOUR] == null ? 0 : obj[NumericConstants.FOUR]));
            //Added For PG_SUM and AG_SUM column
            salesRowDto.addStringProperties(header + "-ProductGrowthSum", String.valueOf(obj[NumericConstants.FIVE] == null ? 0 : obj[NumericConstants.FIVE]));
            salesRowDto.addStringProperties(header + "-AccountGrowthSum", String.valueOf(obj[NumericConstants.FOUR] == null ? 0 : obj[NumericConstants.FOUR]));
            
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
    
    public void getExcelFormatColumns(String hierarchyNo,Map<String, List> hierarchyLevelDetails,ProjectionSelectionDTO projectionSelectionDTO,SalesRowDto salesRowDto ){
           List<String> levelName = CommonUtil.getFormattedDisplayName(hierarchyNo,  hierarchyLevelDetails, projectionSelectionDTO.getSessionDTO(), projectionSelectionDTO.getDisplayFormat());
                salesRowDto.setLevelName(levelName.toString());
                if (projectionSelectionDTO.getDisplayFormat().length == 1 && projectionSelectionDTO.getDisplayFormat().length > 0) {
                    int index = (int) projectionSelectionDTO.getDisplayFormat()[0];
                    if (index == 0) {
                        salesRowDto.addStringProperties(DF_LEVEL_NUMBER, levelName.get(0));
                    } else {
                        salesRowDto.addStringProperties(DF_LEVEL_NAME, levelName.get(0));
                    }
                } else {
                    salesRowDto.addStringProperties(DF_LEVEL_NAME, levelName.get(0));
                    salesRowDto.addStringProperties(DF_LEVEL_NUMBER, levelName.get(0));
                     if (levelName.size() == 2) {
                    salesRowDto.addStringProperties(DF_LEVEL_NAME, levelName.get(1));
                    salesRowDto.addStringProperties(DF_LEVEL_NUMBER, levelName.get(0));
                     }
                }
    }

}
